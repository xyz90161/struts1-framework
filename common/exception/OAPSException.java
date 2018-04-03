package com.foyatech.oaps.struts.Action.common.exception;

/**
 * OAPS�t�ο��~�B�z
 * @author equalhsiao
 * 2018/03/21
 */
public class OAPSException extends CommonException{

    
    /**
     * 
     */
    private static final long serialVersionUID = -2051291972522439443L;


    public OAPSException(OAPSEnum e) {
        this.errorCode = e.getErrorCode();
        System.out.println("errorCode:"+errorCode);
        this.errorMessage = e.getErrorMessage();
        System.out.println("errorMessage:"+errorMessage);
    }
    
    
    public static enum OAPSEnum {
        OAPS_DB_CONNECTION_ERROR("9998","OAPS DB�s�u���~,���p��OAPS�޲z��"),
        OAPS_SYSTEM_UNDEFINED_ERROR("9999","�t�Υ��w�q���~");
        private final String errorCode;
        private final String errorMessage;
        
        private OAPSEnum(String errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }
        public String getErrorCode() {
            return errorCode;
        }
        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
