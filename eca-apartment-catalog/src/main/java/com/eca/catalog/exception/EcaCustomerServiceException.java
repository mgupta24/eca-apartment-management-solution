package com.eca.catalog.exception;

public class EcaCustomerServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EcaCustomerServiceException() {
		super();
	}

	public EcaCustomerServiceException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public EcaCustomerServiceException(String msg) {
		super(msg);
	}

	public EcaCustomerServiceException(Throwable cause) {
		super(cause);
	}

}
