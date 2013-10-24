package com.k_int.euinside.client.module.sword;

import com.k_int.euinside.client.module.sword.client.PostMessageByte;
import com.k_int.euinside.client.module.sword.client.SWClient;
import org.purl.sword.base.DepositResponse;
import org.purl.sword.base.SWORDErrorDocument;
import org.purl.sword.base.SWORDException;
import org.purl.sword.base.ServiceDocument;
import org.purl.sword.client.ClientConstants;
import org.purl.sword.client.SWORDClientException;

import java.net.MalformedURLException;
import java.net.URL;

public class SWORDPush {

        private String serverLoc;
        private String username;
        private String password;
        private String onBehalfOf;
        private String collectionId;
        private ServiceDocument serviceDoc;
        private SWClient swordClient;
        private PostMessageByte postMessage;
        private int successCount = 0;
        private int errorCount = 0;

        public static final String DATA_TYPE_XML = "application/xml";
        public static final String DATA_TYPE_ZIP = "application/zip";

        public SWORDPush( String serverLoc, String username, String password, String onBehalfOf, String collectionId ) {
                this.serverLoc = serverLoc;
                this.username = username;
                this.password = password;
                this.onBehalfOf = onBehalfOf;
                this.collectionId = collectionId;
        }

        /**
         * For testing purposes.
         */
        public SWORDPush() {
                this.serverLoc = "http://localhost:8080/dpp/sword/";
                this.username = "cgadmin";
                this.password = "cgadmin";
                this.onBehalfOf = "2484";
                this.collectionId = "2484";
        }


        /**
         * Initialize a SWORD client using the details provided on SWORDPush constructor. Client will
         * be used for executing dataPush methods. A default PostMessage is generated as well.
         *
         * @return If successful, the SWORDClient will be returned.
         */
        public SWClient initializeSWClient() throws SWORDClientException, MalformedURLException {
                if ( swordClient != null ) return swordClient;

                String swordLocation = serverLoc + collectionId;
                SWClient swordClient = new SWClient();

                initialiseServer( swordLocation, username, password, swordClient );

                if ( username != null && username.length() > 0
                        && password != null
                        && password.length() > 0 ) {

                        swordClient.setCredentials( username, password );
                } else {
                        swordClient.clearCredentials();
                }

                // get the service document. This will give some parameters asked for deposit
                serviceDoc = swordClient.getServiceDocument( serverLoc + "servicedocument", onBehalfOf );

                this.swordClient = swordClient;
                // A default post message. Data will be added during pushData procedure.
                postMessage = createMessage( swordLocation, onBehalfOf, serviceDoc );

                return swordClient;
        }

        /**
         * Push data to the SWORD server, using the details provided in SWORDPush constructor.
         * A default PostMessage is used, including data and dataType provided
         *
         * @param data     The byte array that will be pushed to the SWORD server
         * @param dataType The type of data within byte array. dpp SWORD server accepts xml and zip.
         * @return A String representation of any errors that occurred during the push process.
         */

        public String pushData( byte[] data, String dataType ) {
                if ( swordClient == null ) {
                        try {
                                initializeSWClient();
                        } catch ( SWORDClientException e ) {
                                return "Error initializing SWClient: " + e.getMessage();
                        } catch ( MalformedURLException e ) {
                                return "Error initializing SWClient: " + e.getMessage();
                        }
                }

                StringBuilder result = new StringBuilder( "" );

                try {
                        postMessage.setRecord( data );
                        postMessage.setFiletype( dataType );
                        DepositResponse response = swordClient.postFile( postMessage );
                        if ( response.getHttpResponse() == 201 || response.getHttpResponse() == 202 ) {
                                successCount++;
                        } else {
                                errorCount++;
                                try {
                                        SWORDErrorDocument errorDoc = response.getErrorDocument();
                                        result.append( " The Error URI is: " ).
                                                append( errorDoc.getErrorURI() ).
                                                append( " Summary is: " ).
                                                append( errorDoc.getSummary() ).
                                                append( "\n" );
                                } catch ( SWORDException se ) {
                                        result.append( se.getMessage() ).append( "\n" );
                                }
                        }
                } catch ( SWORDClientException e ) {
                        result.append( "There was an error: " ).append( e.getMessage() );
                }
                return result.toString();

        }

        /**
         * Push data to the SWORD server, using the details provided in SWORDPush constructor.
         * A default PostMessage is used, including data, and dataType provided.
         *
         * @param data     A byte array that will be pushed to the SWORD server
         * @param dataType The type of data within byte array. dpp SWORD server accepts xml and zip.
         * @param fileName Name of the file being deposited. Used to identify which records return each error.
         * @return A String representation of any errors that occurred during the push process.
         */

        public String pushData( byte[] data, String dataType, String fileName ) {
                if ( swordClient == null ) {
                        try {
                                initializeSWClient();
                        } catch ( SWORDClientException e ) {
                                return "Error initializing SWClient: " + e.getMessage();
                        } catch ( MalformedURLException e ) {
                                return "Error initializing SWClient: " + e.getMessage();
                        }
                }
                StringBuilder result = new StringBuilder( "" );

                try {
                        postMessage.setRecord( data );
                        postMessage.setFiletype( dataType );
                        postMessage.setFileName( fileName );
                        DepositResponse response = swordClient.postFile( postMessage );
                        if ( response.getHttpResponse() == 201 || response.getHttpResponse() == 202 ) {
                                successCount++;
                        } else {
                                errorCount++;
                                try {
                                        SWORDErrorDocument errorDoc = response.getErrorDocument();
                                        if ( fileName != null ) {    // On PostMessage this will cause error, as getFileName tries to parse filePath.
                                                result.append( "Error with file: " ).
                                                        append( fileName );
                                        }
                                        result.append( " The Error URI is: " ).
                                                append( errorDoc.getErrorURI() ).
                                                append( " Summary is: " ).
                                                append( errorDoc.getSummary() ).
                                                append( "\n" );
                                } catch ( SWORDException se ) {
                                        result.append( se.getMessage() ).append( "\n" );
                                }
                        }
                } catch ( SWORDClientException e ) {
                        result.append( "There was an error: " ).append( e.getMessage() );
                }
                return result.toString();

        }

        /**
         * Push data to the SWORD server, using the details provided in SWORDPush constructor.
         * A PostMessageByte should be provided, which is forwarded to SWORD server
         *
         * @param customMessage A PostMessage containing details about deposit configuration
         * @return A textual representation of any errors that occurred during the push process.
         */

        public String pushData( PostMessageByte customMessage ) {
                if ( swordClient == null ) {
                        try {
                                initializeSWClient();
                        } catch ( SWORDClientException e ) {
                                return "Error initializing SWClient: " + e.getMessage();
                        } catch ( MalformedURLException e ) {
                                return "Error initializing SWClient: " + e.getMessage();
                        }
                }
                StringBuilder result = new StringBuilder( "" );

                try {
                        DepositResponse response = swordClient.postFile( customMessage );
                        if ( response.getHttpResponse() == 201 || response.getHttpResponse() == 202 ) {
                                successCount++;
                        } else {
                                errorCount++;
                                try {
                                        SWORDErrorDocument errorDoc = response.getErrorDocument();
                                        if ( customMessage.getFileName() != null ) {// On PostMessage this will cause error, as getFileName tries to parse filePath.
                                                result.append( "Error with file: " ).
                                                        append( customMessage.getFileName() );
                                        }
                                        result.append( " The Error URI is: " ).
                                                append( errorDoc.getErrorURI() ).
                                                append( " Summary is: " ).
                                                append( errorDoc.getSummary() ).
                                                append( "\n" );
                                } catch ( SWORDException se ) {
                                        result.append( se.getMessage() ).append( "\n" );
                                }
                        }
                } catch ( SWORDClientException e ) {
                        result.append( "There was an error: " ).append( e.getMessage() );
                }
                return result.toString();

        }

        public int getSuccessful() {
                return successCount;

        }

        public int getFailed() {
                return errorCount;
        }


        /**
         * Creates a PostMessage, which contains all the information that will be included in the HTTP request.
         * SWORD library formats these information to be read by a SWORD server.
         *
         * @param location
         * @param onBehalfOf
         * @param serviceDoc
         * @return
         */
        private PostMessageByte createMessage( String location, String onBehalfOf, ServiceDocument serviceDoc ) {

                PostMessageByte message = new PostMessageByte();
                message.setDestination( location );
                message.setVerbose( serviceDoc.getService().isVerbose() );
                message.setOnBehalfOf( onBehalfOf );
                message.setNoOp( serviceDoc.getService().isNoOp() );
                message.setUserAgent( ClientConstants.SERVICE_NAME );  // this should be edited.

                // Not sure about following parameters.
//              message.setUseMD5(false);
//              message.setChecksumError(false);   True if the item should include a checksum error.
                message.setCorruptRequest( false );  // True if the item should corrupt the POST header.

                return message;

        }


        /**
         * Initialise the server connection information. If there is a username and
         * password, the basic credentials will also be set. Otherwise, the
         * credentials will be cleared.
         *
         * @param location The location to connect to. This is a URL, of the format,
         *                 http://a.host.com:port/. The host name and port number will be
         *                 extracted. If the port is not specified, a default port of 80
         *                 will be used.
         * @param username The username. If this is null or an empty string, the basic
         *                 credentials will be cleared.
         * @param password The password. If this is null or an empty string, the basic
         *                 credentials will be cleared.
         * @throws java.net.MalformedURLException if there is an error processing the URL.
         */
        private void initialiseServer( String location, String username,
                                       String password, SWClient swordClient ) throws MalformedURLException {
                URL url = new URL( location );
                int port = url.getPort();
                if ( port == -1 ) {
                        port = 80;
                }

                swordClient.setServer( url.getHost(), port );

                if ( username != null && username.length() > 0 && password != null
                        && password.length() > 0 ) {
                        swordClient.setCredentials( username, password );
                } else {
                        swordClient.clearCredentials();
                }
                swordClient.setUserAgent( ClientConstants.SERVICE_NAME );
        }

        public String getServerLoc() {
                return serverLoc;
        }

        public void setServerLoc( String serverLoc ) {
                this.serverLoc = serverLoc;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername( String username ) {
                this.username = username;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword( String password ) {
                this.password = password;
        }

        public String getOnBehalfOf() {
                return onBehalfOf;
        }

        public void setOnBehalfOf( String onBehalfOf ) {
                this.onBehalfOf = onBehalfOf;
        }

        public String getCollectionId() {
                return collectionId;
        }

        public void setCollectionId( String collectionId ) {
                this.collectionId = collectionId;
        }

        public PostMessageByte getPostMessage() {
                return postMessage;
        }

        public void setPostMessage( PostMessageByte postMessage ) {
                this.postMessage = postMessage;
        }
}
