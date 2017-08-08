package com.lovecws.mumu.common.core.exception;

public class MumuLimitException extends RuntimeException {

	private static final long serialVersionUID = -8863391854740321517L;

	public MumuLimitException() {
		super();
	}

	public MumuLimitException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public MumuLimitException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public MumuLimitException(String arg0) {
		super(arg0);
	}

	public MumuLimitException(Throwable arg0) {
		super(arg0);
	}

}
