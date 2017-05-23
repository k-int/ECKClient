package com.k_int.euinside.client.module.metis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

// The following class is a copy od eu.europeana.corelib.utils.EuropeanaUriUtils
// To many jar files caused clashes, so have just extracted the class
public class MetisUriUtils {
	// The jar pulled in a few to many libraries, so just ripped out the code
    private static final String METIS_REPLACEMENT_CHARACTER = "_";
 
    public static String createEuropeanaId(String collectionId, String recordId) {
       return "/" + sanitizeCollectionId(collectionId) + "/" + sanitizeRecordId(recordId);
    }
 
    private static String sanitizeRecordId(String recordId) {
       recordId = StringUtils.startsWith(recordId, "http://")?StringUtils.substringAfter(StringUtils.substringAfter(recordId, "http://"), "/"):recordId;
       Pattern pattern = Pattern.compile("[^a-zA-Z0-9_]");
       Matcher matcher = pattern.matcher(recordId);
       recordId = matcher.replaceAll(METIS_REPLACEMENT_CHARACTER);
       return(recordId);
    }
 
    private static String sanitizeCollectionId(String collectionId) {
       Pattern pattern = Pattern.compile("[a-zA-Z]");
       Matcher matcher = pattern.matcher(collectionId.substring(collectionId.length() - 1, collectionId.length()));
       return(matcher.find()?StringUtils.substring(collectionId, 0, collectionId.length() - 1):collectionId);
    }
}
