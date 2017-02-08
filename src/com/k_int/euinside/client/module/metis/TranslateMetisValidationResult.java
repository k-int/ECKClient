package com.k_int.euinside.client.module.metis;

import org.apache.http.HttpStatus;

import com.k_int.euinside.client.http.TranslateHTTPResult;

public class TranslateMetisValidationResult extends TranslateHTTPResult<MetisValidationResult> {
	// A simple instance that does the work
	static final TranslateMetisValidationResult instance = new TranslateMetisValidationResult();

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
