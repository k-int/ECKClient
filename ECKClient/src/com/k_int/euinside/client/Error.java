package com.k_int.euinside.client;

public enum Error {
	NONE(),
	ACCEPTED(),
	ENCODEING(),
	IO_EXCEPTION(),
	CLIENT_PROTOCOL_EXCEPTION(),
	UNABLE_TO_READ_FILE(),
	UNKNOWN_FILE_TYPE(),
	HTTP_ERROR();
	
	
	private Error() {
	}
}
