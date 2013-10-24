package com.k_int.euinside.client.module.sword.client;


import org.purl.sword.client.PostMessage;

/**
 * The message containing all the information required by the HTTP request to communicate with a SWORD server.
 * We are extending the default PostMessage in order to support pushing of byte[] .
 */
public class PostMessageByte extends PostMessage {
        private byte[] record;

        public String getFileName() {
                return fileName;
        }

        public void setFileName( String fileName ) {
                this.fileName = fileName;
        }

        private String fileName;

        public byte[] getRecord() {
                return record;
        }

        public void setRecord( byte[] rec ) {
                record = rec;
        }


}