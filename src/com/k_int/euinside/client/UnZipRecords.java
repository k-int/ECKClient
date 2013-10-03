package com.k_int.euinside.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Worker class for unzipping byte arrays
 */
public class UnZipRecords {
	private Log log = LogFactory.getLog(UnZipRecords.class);
	private ByteArrayInputStream dataInputStream;
	private ZipInputStream zipInputStream;

	/**
	 * Construvtor
	 * @param data The zip file as a byte array
	 */
	public UnZipRecords(byte [] data) {
		dataInputStream = new ByteArrayInputStream(data);
		zipInputStream = new ZipInputStream(dataInputStream); 
	}

	/**
	 * Retrieves the next entry in the zip file as a byte array
	 * 
	 * @param name A StingBuffer that will contain the name of this entry or null if we are not interested in the name 
	 * 
	 * @return The entry as a byte array or null if there are no more entries
	 */
	public byte [] getNextEntry(StringBuffer name) {
		byte [] unZippedEntry = null;
		if (name != null) {
			name.setLength(0);
		}
		try {
			ZipEntry entry = zipInputStream.getNextEntry();
			if (entry != null) {
				if (name != null) {
					name.append(entry.getName());
				}
				ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
				byte [] buffer = new byte[4096];
				int bytesRead = 0;
				while ((bytesRead = zipInputStream.read(buffer, 0, 4096)) > 0) {
					byteStream.write(buffer, 0, bytesRead);
				}
				unZippedEntry = byteStream.toByteArray();
			}
		} catch (IOException e) {
			log.error("IOException unzipping data", e);
		}
		return(unZippedEntry);
	}
}
