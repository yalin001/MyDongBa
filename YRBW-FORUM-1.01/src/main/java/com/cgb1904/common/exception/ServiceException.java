package com.cgb1904.common.exception;

public class ServiceException extends RuntimeException{

	private static final long serialVersionUID = 8247253086242302993L;
	public ServiceException() {
		super();
	}
	public ServiceException(String message) {
		super(message);
	}
	public ServiceException(Throwable cause) {
		super(cause);
	}
}
