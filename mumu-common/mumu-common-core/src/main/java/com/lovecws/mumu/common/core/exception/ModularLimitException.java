package com.lovecws.mumu.common.core.exception;

public class ModularLimitException extends RuntimeException {

	private static final long serialVersionUID = -8863391854740321517L;

	public ModularLimitException() {
		super();
	}

	public ModularLimitException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ModularLimitException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ModularLimitException(String arg0) {
		super(arg0);
	}

	public ModularLimitException(Throwable arg0) {
		super(arg0);
	}

}
