package com.foyatech.oaps.struts.Action.common.exception;

public class CommonException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5202449057642470451L;

	protected String errorCode;
	protected String errorMessage;
    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
	
}
