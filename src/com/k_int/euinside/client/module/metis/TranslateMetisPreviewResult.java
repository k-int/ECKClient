package com.k_int.euinside.client.module.metis;

import org.apache.http.HttpStatus;

import com.k_int.euinside.client.http.TranslateHTTPResult;

public class TranslateMetisPreviewResult extends TranslateHTTPResult<MetisPreviewResult> {
	// A simple instance that does the work
	static final TranslateMetisPreviewResult instance = new TranslateMetisPreviewResult();

	@Override
	public boolean isResultAcceptable(int responseCode) {
		boolean acceptable = false;
		switch (responseCode) {
			case HttpStatus.SC_OK:
			case HttpStatus.SC_UNPROCESSABLE_ENTITY:
				acceptable = true;
				break;
		}
		return(acceptable);
	}
}
