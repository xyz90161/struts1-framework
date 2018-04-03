package com.foyatech.oaps.struts.Action.common.exception;

import org.apache.log4j.Logger;

/**
 * IBS黑名單錯誤列表
 * @author equalhsiao
 * 2018/03/20
 */
public class IBSException extends CommonException{
    private static Logger logger = Logger.getLogger(IBSException.class);
    private static final long serialVersionUID = 6840971591613566123L;
//    private String errorCode;
//    private String errorMsg;
    public IBSException(IBSEnum e) {
        this.errorCode = e.getErrorCode();
        this.errorMessage = e.getErrorMessage();
        logger.error("errorCode:"+errorCode+",errorMessage:"+errorMessage);
    }
    public static enum IBSEnum {
        IBS_USER_NULL_ERROR("0001","請輸入使用者"),
        IBS_TN_NULL_ERROR("0002","請輸入TN"),
        IBS_USER_MAPPING_ERROR("0003", "使用者錯誤"),
        IBS_TNTYPE_NULL_ERROR("0004","請輸入TN type"),
        IBS_TNTYPE_UNDEFINED_ERROR("0005","TN type錯誤"),
        IBS_TN_NOT_FOUND_ERROR("0006","找不到此筆TN"),
        IBS_SWITCHID_NULL_ERROR("0007","請輸入Switch ID"),
        IBS_SBCLASS_NULL_ERROR("0008","請輸入SB Class"),
        IBS_OUTGRE_NULL_ERROR("0009","請輸入outgre"),
        IBS_CITY_NULL_ERROR("0010","請輸入city"),
        IBS_MOBILEROUTE_NULL_ERROR("0011","請輸入mobileroute"),
        IBS_TN_FORMAT_ERROR("0012","TN格式錯誤"),
        IBS_ZIPCODE_NULL_ERROR("0013","請輸入zipcode"),
        IBS_CREATE_ORDER_ERROR("0014","IBS建立聯單錯誤 請聯絡OAPS管理人員"),
        IBS_CPCNBR_NULL_ERROR("0015","請輸入cpcnbr"),
        IBS_CPCNBR_FORMAT_ERROR("0016","cpcnbr格式錯誤"),
        IBS_QUERY_COUNT_ERROR("0017","查詢資料數量發生錯誤,請聯絡OAPS管理人員"),
        IBS_QUERY_NOT_FOUND("0018","查無此聯單"),
        IBS_TDMPOTS_API_ERROR("0019","call TDM POTS發生錯誤"),
        IBS_OUTGRE_UNDEFINED_ERROR("0020","沒有定義的OUT_GRE"),
        IBS_OUTGRE_NOT_CLK("0021","沒有定義國際限撥(outgre第三碼必須為0)")
        ;
        // DUPLICATE_USER(1, "This user already exists.");
        private final String errorCode;
        private final String errorMessage;

        private IBSEnum(String errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public String getErrorCode() {
            return errorCode;
        }

        @Override
        public String toString() {
            return errorCode + ": " + errorMessage;
        }
    }
}
