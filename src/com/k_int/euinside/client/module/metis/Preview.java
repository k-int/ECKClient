package com.k_int.euinside.client.module.metis;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.InputSource;

import eu.europeana.corelib.utils.EuropeanaUriUtils;
import eu.europeana.metis.preview.rest.client.PreviewClient;
import eu.europeana.metis.preview.service.ExtendedValidationResult;

import com.k_int.euinside.client.module.CommandLineArguments;

public class Preview {
	private static Log log = LogFactory.getLog(Preview.class);

	private static final String PREFIX_EDM = "edm";
	private static final String PREFIX_RDF = "rdf";

	private static final String XPATH_EDM_IDENTIFIER = "/rdf:RDF/edm:ProvidedCHO/@rdf:about";

	private static final String URI_EDM = "http://www.europeana.eu/schemas/edm/";
	private static final String URI_RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

	private static final String FILE_EXTENSION_XML = ".xml";
	private static final String FILE_EXTENSION_ZIP = ".zip";

	private static final String PATH_SEPARATOR_UNIX    = "/";
	private static final String PATH_SEPARATOR_WINDOWS = "\\";

	private static final String IDENTIFIER_SEPARATOR = "_";

	/** The directory used for creating the zip files */
	private static String tempDirectory = "/tmp"; 

	/** The base url for the metis preview api */ 
	private static String baseURL = "http://metis-preview-test.cfapps.io";

	/** The preview client that does the work for us */
	private static PreviewClient previewClient = null;

	/** The base value to be used for the collection id */
	private static String collectionIdPrefix = "eckClient";

	/** The counter to be used to ensure the collection id will be unique from this client */
	private static long identifier = 0;

	/** The XPath instance we will be using to extract the record id */
	private static XPath xpathRecordId = null;

	/** The XPath expression used to extract the record id */
	private static XPathExpression xpathExpressionRecordId = null;

	/** The zip file we will be sending to metis */
	File zipFile = null;

	/** The identifier that will be used for this collection */
	String collectionIdentifier = null;

	/** The result record that will be returned */
	ResultRecord resultRecord = null;

	static {
		// Initialise our xpath object
		xpathRecordId = XPathFactory.newInstance().newXPath();
		xpathRecordId.setNamespaceContext(new NamespaceContext() {
    	    public String getNamespaceURI(String prefix) {
    	    	switch (prefix) {
	    		case PREFIX_EDM:
	    			return(URI_EDM);

    	    		case PREFIX_RDF:
    	    			return(URI_RDF);

    	    		default:
            	        return XMLConstants.NULL_NS_URI;
    	    	}
    	    }

    	    // This method isn't necessary for XPath processing.
    	    public String getPrefix(String uri) {
    	        throw new UnsupportedOperationException();
    	    }

    	    // This method isn't necessary for XPath processing either.
    	    public Iterator<String> getPrefixes(String uri) {
    	        throw new UnsupportedOperationException();
    	    }
    	});

    	// now compile the expression
		try {
			xpathExpressionRecordId = xpathRecordId.compile(XPATH_EDM_IDENTIFIER);
		} catch (Exception e) {
			log.error("exception while compiling the record id expression", e);
		}
	}

	/**
	 * Set the base url to the Metis preview service
	 * 
	 * @param baseURL the base url to the metis preview service (default: http://metis-preview-test.cfapps.io)
	 */
	public static void setURL(String baseURL) {
		if (!StringUtils.isEmpty(baseURL)) {
			Preview.baseURL = baseURL;
		}
	}

	/**
	 * Set the temporary directory to be used for creating zip files.
	 * If the directory does not exist it will attempt to create it.
	 * 
	 * @param tempDirectory the temporary directory that will be used (default: /tmp)
	 */
	public static void setTemporaryDirectory(String tempDirectory) {
		if (!StringUtils.isEmpty(tempDirectory)) {
			Preview.tempDirectory = tempDirectory;

			// If we do not have a trailing slash then we need to add one
			if (!tempDirectory.endsWith(PATH_SEPARATOR_UNIX) && !tempDirectory.endsWith(PATH_SEPARATOR_WINDOWS)) {
				// Append a trailing slash
				Preview.tempDirectory += File.separator;
			}

			try {
				(new File(Preview.tempDirectory)).mkdirs();
			} catch (Exception e) {
				log.error("Metis Preview: Exception thrown while trying to create the temporary directory", e);
			}
		}
	}

	/**
	 * Sets the collection prefix that will be used
	 * 
	 * @param collectionIdPrefix the collection prefix will be used (default: eckClient)
	 */
	public static void setCollectionIdPrefix(String collectionIdPrefix) {
		if (!StringUtils.isEmpty(collectionIdPrefix)) {
			Preview.collectionIdPrefix = collectionIdPrefix;
		}
	}

	/**
	 * Retrieve the instance of the metis client that we are using
	 * 
	 * @return an instance of the metis preview client
	 */
	private PreviewClient getMetisPreviewClient() {
		// We do not initialise on startup as that causes knock on problems in the metis preview client
		if (previewClient == null) {
			previewClient = new PreviewClient(baseURL);
		}
		return(previewClient);
	}

	/**
	 * Constructs a new Preview object
	 */
	public Preview() {
		this(null, null);
	}

	/**
	 * Constructs a new Preview object with a collectionIDentifier
	 * 
	 * @param collectionIdentifier the identifier to be used for this collection
	 */
	public Preview(String collectionIdentifier) {
		initialise(null, collectionIdentifier);
	}

	/**
	 * Constructs a new Preview object with the name of a zip file and a collection identifier
	 * 
	 * @param zipFilename the full path to the zip file that is to be sent to metis
	 * @param collectionIdentifier the identifier to be used for this collection
	 */
	public Preview(String zipFilename, String collectionIdentifier) {
		initialise(zipFilename, collectionIdentifier);
	}

	/**
	 * Initialises this instance with a collection identifier
	 * 
	 * @param collectionIdentifier the identifier to be used for this collection
	 */
	private void initialise(String collectionIdentifier) {
		initialise(null, collectionIdentifier);
	}

	/**
	 * Initialises this instance with a the full path to a zip file and a collection identifier
	 * 
	 * @param zipFilename the full path to the zip file that is to be sent to metis
	 * @param collectionIdentifier the identifier to be used for this collection
	 */
	private void initialise(String zipFilename, String collectionIdentifier) {
		// Generate the collection identifier
		this.collectionIdentifier = collectionIdentifier;
		generateCollectionIdentifier();

		// Setup the zip file
		if (StringUtils.isEmpty(zipFilename)) {
			zipFile = null;
		} else {
			zipFile = new File(zipFilename);
		}

		// Finally generate ourselces a result record
		resultRecord = new ResultRecord();
	}

	/**
	 * Creates a temporary zip file for the supplied record
	 * 
	 * @param record the record a zip file needs creating for 
	 */
	private void createZipFile(String record) {
		OutputStream outputStream = null;
		ZipOutputStream zipStream = null;

		try {
			// create a temporary zip file
			zipFile = new File(tempDirectory + collectionIdentifier + FILE_EXTENSION_ZIP);
			outputStream = new FileOutputStream(zipFile);
			zipStream = new ZipOutputStream(outputStream);
	
			// add this record into the zip file
			ZipEntry zipEntry = new ZipEntry(collectionIdentifier + FILE_EXTENSION_XML);
			zipStream.putNextEntry(zipEntry);
			byte[] recordData = record.getBytes(StandardCharsets.UTF_8);
			zipStream.write(recordData, 0, recordData.length);
			zipEntry.setSize(recordData.length);
			zipStream.closeEntry();
		} catch (Exception e) {
			log.error("Metis Preview: Exception thrown creating zip file for EDM record", e);
			zipFile = null;
		} finally {
			try {
				// Close the streams
				if (zipStream != null) {
					zipStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (Exception e) {
				log.error("Exception thrown while closing streams after creating zip file", e);
			}
		}
	}

	/**
	 * Generates the collection identifier to be passed to metis if we have not been supplied one
	 */
	private synchronized void generateCollectionIdentifier() {
		// If we have been passed a collection id then we will use that
		if (StringUtils.isEmpty(collectionIdentifier)) {
			StringBuffer buffer = new StringBuffer(collectionIdPrefix);

			// Add the current milliseconds to it
			buffer.append(IDENTIFIER_SEPARATOR).append(System.currentTimeMillis());

			// Now add our internal identifier to it
			identifier++;
			buffer.append(IDENTIFIER_SEPARATOR).append(identifier);

			// Return the calculated collection id
			collectionIdentifier = buffer.toString();
		}
	}

	/**
	 * Sends the zip file to metis
	 */
	private void sendZip() {
		// Have we been supplied a zip file
		if (zipFile == null) {
			resultRecord.setMessage("No file supplied to send to the metis preview service");
		} else {
			// Does the file exist
			if (zipFile.exists()) {
				try {
					// Now we can ask the preview client to send it for us, we assume EDM External and null for the crosswalk, whatever that is
					ExtendedValidationResult metisResult = getMetisPreviewClient().previewRecords(zipFile, collectionIdentifier, true, null);
					if (metisResult == null) {
						// At the moment we do not get an error message back from metis if it fails, so give it a generic one
						resultRecord.setMessage("Failed to send file to the metis preview service, please contact the system administrator");
						log.error("Failed to send file \"" + zipFile.getAbsolutePath() + "\" to metis");
					} else {
						resultRecord.setRecordURLs(getRecordURLs(metisResult.getPortalUrl()));
						resultRecord.setExpiryDate(metisResult.getDate());
						resultRecord.setResult(metisResult.isSuccess());
						resultRecord.setMessage(metisResult.getPortalUrl());
					}
				} catch (Exception e) {
					log.error("Exception thrown while sending to metis for preview: " + zipFile.getAbsolutePath(), e);
					resultRecord.setMessage(e.getMessage());
				}
			} else {
				resultRecord.setMessage("File \"" + zipFile.getAbsolutePath() + "\" does not exist");
			}
		}
	}

	/**
	 * Generates the record urls for each record in the zip file
	 * Metis will be changed to do this, so hopefully this will become redundant
	 * 
	 * @param collectionPortalURL the URL to preview the collection on metis
	 * 
	 * @return A list of all the urls to the records contained in the zip file 
	 */
	private List<String> getRecordURLs(String collectionPortalURL) {
		/// Metis is going to be changed so it returns these, so we shouldn't need to generate them
		ArrayList<String> recordURLs = new ArrayList<String>();

		// If collection portal url is not set then we will not bother
		if (!StringUtils.isEmpty(collectionPortalURL)) {
			InputStream inputStream = null;
			ZipInputStream zipStream = null;
			try {
				// Determine the URL prefix
				String urlPrefix = StringUtils.substringBefore(collectionPortalURL, "search?");
				urlPrefix += "record";

				// Now determine the url suffix
				String urlSuffix = ".html?api_url=" + URLEncoder.encode(StringUtils.substringBefore(collectionPortalURL, "/portal").replace("portal", "api") + "/api", StandardCharsets.UTF_8.name());
				urlSuffix += "?q=" + StringUtils.substringAfter(collectionPortalURL, "q=");
				if (collectionPortalURL.endsWith("*")) {
					urlSuffix = urlSuffix.substring(0, urlSuffix.length() - 1);
				}

				// Attempt to open the zip file and go through all the entries
				inputStream = new FileInputStream(zipFile);
				zipStream = new ZipInputStream(inputStream);

				// Loop through all the entries 
            	byte [] buffer = new byte[4096];
	            while (zipStream.getNextEntry() != null) {
	            	// We cannot rely on the size int he entry, so we read it into a byte output stream
	            	ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
	            	int bytesRead = 1;
	            	while (bytesRead > 0) { 
	            		bytesRead = zipStream.read(buffer);
	            		if (bytesRead > 0) {
	            			byteOutputStream.write(buffer, 0, bytesRead);
	            		}
	            	}
	            	String extractedString = byteOutputStream.toString(StandardCharsets.UTF_8.name());
	            	InputSource inputSource = new InputSource(new StringReader(extractedString));
	            	String recordId = xpathExpressionRecordId.evaluate(inputSource);
	            	String europeanaRecordId = EuropeanaUriUtils.createEuropeanaId(collectionIdentifier, recordId);
	            	String url = urlPrefix + europeanaRecordId + urlSuffix;
	            	recordURLs.add(url);
	            	zipStream.closeEntry();
	            }
			} catch (Exception e) {
				log.error("Exception thrown while generating the record URLs", e);
			} finally {
	            // close the streams
				try {
					if (zipStream != null) {
						zipStream.close();
					}
					if (inputStream != null) {
						inputStream.close();
					}
				} catch (Exception e) {
					log.error("Error closing streams after generating record urls", e);
				}
			}
		}

		return(recordURLs);
	}

	/**
	 * Passes the record to metis for previewing
	 * 
	 * @param record the record to be displayed
	 * 
	 * @return The result of passing the record to metis or an error if one occurred 
	 */
	private ResultRecord previewRecordInternal(String record) {
		try {
			// First thing we need to do is turn the record into a zip file
			createZipFile(record);

			// Now send it to metis
			sendZip();

			// We have now finished with the zip file delete it as we created it
			try {
				zipFile.delete();
			} catch (Exception e) {
				// We do not want to generate an error in the result record for this, just put it in th elog file
				log.error("Exception thrown while trying to delete temporary zip file: " + zipFile.getAbsolutePath(), e);
			}
		} catch (Exception e) {
			log.error("Metis Preview: Exception thrown qhile trying to preview an EDM record", e);
			resultRecord.setMessage(e.getMessage());
		}

		// return the result to the caller, we will probably create our own class for this
		return(resultRecord);
	}

	/**
	 * Passes the zip file to metis for previewing
	 * 
	 * @return The result of passing the record to metis or an error if one occurred 
	 */
	private ResultRecord previewRecordZipInternal() {
		// Lets attempt to verify that this file is a valid zip file before we pass it on
		if (zipFile == null) {
			resultRecord.setMessage("No filename supplied to Preview.previewRecordZip");
		} else {
			try {
				// Attempt to open the zip file and go through all the entries
				InputStream inputStream = new FileInputStream(zipFile);
				ZipInputStream zipStream = new ZipInputStream(inputStream);

				// Loop through all the entries 
	            ZipEntry zipEntry = null;
	            while((zipEntry = zipStream.getNextEntry()) != null) {
	            	log.info("File name: " + zipEntry.getName());
	            	zipStream.closeEntry();
	            }

	            // close the streams
	            zipStream.close();
	            inputStream.close();

	            // If we havn't hit an exception, we will assume that it is a valid zip file
				sendZip();
			} catch (Exception e) {
				// If we get here we treat it as not a valid zip file
				resultRecord.setMessage("\"" + zipFile.getAbsolutePath() + "\" is not a valid zip file");
			}
		}
		return(resultRecord);
	}

	/**
	 * Passes the file to metis for previewing
	 * 
	 * @param filename the full path to the file that contains an EDM record to be previewed
	 * 
	 * @return The result of passing the record to metis or an error if one occurred 
	 */
	private ResultRecord previewRecordFileInternal(String filename) {
		try {
			String edmrecord = FileUtils.readFileToString(new File(filename), StandardCharsets.UTF_8);
			if (!StringUtils.isEmpty(edmrecord)) {
				previewRecordInternal(edmrecord);
			}
		} catch (Exception e) {
			log.error("Metis Preview: Failed to read file: \"" + filename + "\"", e);
			resultRecord.setMessage("Unable to read file: \"" + filename + "\"");
		}

		// return the result to the caller
		return(resultRecord);
	}

	/**
	 * Passes the record to metis for previewing, automatically generating a collection identifier
	 * 
	 * @param record the record to be previewed
	 *
	 * @return The result of passing the record to metis or an error if one occurred 
	 */
	public static ResultRecord previewRecord(String record) {
		return(previewRecord(record, null));
	}

	/**
	 * Passes the record to metis for previewing
	 * 
	 * @param record the record to be previewed
	 * @param collectionIdentifier the identifier to be used for this collection
	 *
	 * @return The result of passing the record to metis or an error if one occurred 
	 */
	public static ResultRecord previewRecord(String record, String collectionIdentifier) {
		return((new Preview(collectionIdentifier)).previewRecordInternal(record));
	}

	/**
	 * Passes the record to metis for previewing, automatically generating a collection identifier
	 * 
	 * @param record the record to be previewed
	 *
	 * @return The result of passing the record to metis or an error if one occurred 
	 */
	public ResultRecord preview(String record) {
		return(preview(record, null));
	}

	/**
	 * Passes the record to metis for previewing
	 * 
	 * @param record the record to be previewed
	 * @param collectionIdentifier the identifier to be used for this collection
	 *
	 * @return The result of passing the record to metis or an error if one occurred 
	 */
	public ResultRecord preview(String record, String collectionIdentifier) {
		initialise(collectionIdentifier);
		return(previewRecordInternal(record));
	}

	/**
	 * Passes the file to metis for previewing, automatically generating a collection identifier
	 * 
	 * @param filename the full path to the file that contains an EDM record to be previewed
	 *
	 * @return The result of passing the record to metis or an error if one occurred 
	 */
	public static ResultRecord previewRecordFile(String filename) {
		return(previewRecordFile(filename, null));
	}

	/**
	 * Passes the file to metis for previewing
	 * 
	 * @param filename the full path to the file that contains an EDM record to be previewed
	 * @param collectionIdentifier the identifier to be used for this collection
	 *
	 * @return The result of passing the record to metis or an error if one occurred 
	 */
	public static ResultRecord previewRecordFile(String filename, String collectionIdentifier) {
		return((new Preview(collectionIdentifier)).previewRecordFileInternal(filename));
	}

	/**
	 * Passes the file to metis for previewing, automatically generating a collection identifier
	 * 
	 * @param filename the full path to the file that contains an EDM record to be previewed
	 *
	 * @return The result of passing the record to metis or an error if one occurred 
	 */
	public ResultRecord previewFile(String filename) {
		return(previewFile(filename, null));
	}

	/**
	 * Passes the file to metis for previewing
	 * 
	 * @param filename the full path to the file that contains an EDM record to be previewed
	 * @param collectionIdentifier the identifier to be used for this collection
	 *
	 * @return The result of passing the record to metis or an error if one occurred 
	 */
	public ResultRecord previewFile(String filename, String collectionIdentifier) {
		initialise(collectionIdentifier);
		return(previewRecordFileInternal(filename));
	}

	/**
	 * Passes the file to metis for previewing, automatically generating a collection identifier
	 * 
	 * @param filename the full path to the zip file that contains one or more EDM records to be previewed
	 *
	 * @return The result of passing the record to metis or an error if one occurred 
	 */
	public static ResultRecord previewRecordZip(String filename) {
		return(previewRecordZip(filename, null));
	}

	/**
	 * Passes the file to metis for previewing
	 * 
	 * @param filename the full path to the zip file that contains one or more EDM records to be previewed
	 * @param collectionIdentifier the identifier to be used for this collection
	 *
	 * @return The result of passing the record to metis or an error if one occurred 
	 */
	public static ResultRecord previewRecordZip(String filename, String collectionIdentifier) {
		return((new Preview(filename, collectionIdentifier)).previewRecordZipInternal());
	}

	/**
	 * Passes the file to metis for previewing, automatically generating a collection identifier
	 * 
	 * @param filename the full path to the zip file that contains one or more EDM records to be previewed
	 *
	 * @return The result of passing the record to metis or an error if one occurred 
	 */
	public ResultRecord previewZip(String filename) {
		return(previewZip(filename, null));
	}

	/**
	 * Passes the file to metis for previewing
	 * 
	 * @param filename the full path to the zip file that contains one or more EDM records to be previewed
	 * @param collectionIdentifier the identifier to be used for this collection
	 *
	 * @return The result of passing the record to metis or an error if one occurred 
	 */
	public ResultRecord previewZip(String filename, String collectionIdentifier) {
		initialise(filename, collectionIdentifier);
		return(previewRecordZipInternal());
	}

	/**
	 * Exercises the methods with the supplied parameters
	 * 
	 * @param args The parameters passed in on the command line, valid parameters are:<br/><br/>
	 *  <table class="arguments">
	 * 	    <col width="15%"/>
	 *      <col width="85%"/>
	 * 		<tr><td>-filename</td><td>The name of a file that contains an edm record to be validated</td></tr>
	 * 		<tr><td>-metisBaseURL</td><td>The base url to the metis preview service</td></tr>
	 * 		<tr><td>-tempDirectory</td><td>The temporary directory to be used for the zip files (default /tmp)</td></tr>
     *  </table>
	 */
	public static void main(String [] args)
	{
		CommandLineArguments arguments = new CommandLineArguments(args, true);
		if (arguments.getFilenames().isEmpty()) {
			log.error("No filenames supplied for preview");
		} else {
			setCollectionIdPrefix(arguments.getCollectionId());
			setTemporaryDirectory(arguments.getTempDirectory());
			setURL(arguments.getMetisBaseURL());
			for (String filename : arguments.getFilenames()) {
				System.out.println("Previewing file: " + filename);
				ResultRecord result = null;
				if (filename.toLowerCase().endsWith(FILE_EXTENSION_ZIP)) {
					result = previewRecordZip(filename);
				} else {
					result = previewRecordFile(filename);
				}
		
				if (result == null) {
					log.error("Failed to validate file");
				} else {
					log.info("Result from preview");
					log.info(result.toString());
				}
			}
		}
	}
}
