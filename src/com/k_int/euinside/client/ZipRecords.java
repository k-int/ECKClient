package com.k_int.euinside.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Worker class for zipping byte arrays
 */
public class ZipRecords {
	private Log log = LogFactory.getLog(ZipRecords.class);
	private ByteArrayOutputStream byteStream = null;
	private ZipOutputStream zipStream = null;
	
	public ZipRecords() {
		initialise();
	}

	/**
	 * Initialise the class for zipping a fresh set of records
	 */
	public void initialise() {
		if (byteStream == null) {
			byteStream = new ByteArrayOutputStream();
		}
		byteStream.reset();
		zipStream = new ZipOutputStream(byteStream);
	}

	/**
	 * Retrieves the actual zip file
	 * 
	 * @return The zip file as a byte array
	 */
	public byte [] getZip() {
		try {
			zipStream.finish();
		} catch (IOException e) {
			// highly unlikely this will be thrown as we are just doing this in memory, but log it anyway
			log.error("IOException thrown while finishing the zip file", e);
		}
		zipStream = null;
		return(byteStream.toByteArray());
	}

	/**
	 * Adds a byte array to the zip file
	 * 
	 * @param name The name of this entry
	 * @param data The byte array that is to be added
	 */
	public void addEntry(String name, byte [] data) {
        try {
			zipStream.putNextEntry(new ZipEntry(name));
	        zipStream.write(data, 0, data.length);
	        zipStream.closeEntry();
		} catch (IOException e) {
			// Just log it
			log.info("IOException thrown while adding data to the zip file", e);
		}
	}
}
