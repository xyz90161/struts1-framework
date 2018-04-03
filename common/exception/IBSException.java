package com.foyatech.oaps.struts.Action.common.exception;

import org.apache.log4j.Logger;

/**
 * IBS�¦W����~�C��
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
        IBS_USER_NULL_ERROR("0001","�п�J�ϥΪ�"),
        IBS_TN_NULL_ERROR("0002","�п�JTN"),
        IBS_USER_MAPPING_ERROR("0003", "�ϥΪ̿��~"),
        IBS_TNTYPE_NULL_ERROR("0004","�п�JTN type"),
        IBS_TNTYPE_UNDEFINED_ERROR("0005","TN type���~"),
        IBS_TN_NOT_FOUND_ERROR("0006","�䤣�즹��TN"),
        IBS_SWITCHID_NULL_ERROR("0007","�п�JSwitch ID"),
        IBS_SBCLASS_NULL_ERROR("0008","�п�JSB Class"),
        IBS_OUTGRE_NULL_ERROR("0009","�п�Joutgre"),
        IBS_CITY_NULL_ERROR("0010","�п�Jcity"),
        IBS_MOBILEROUTE_NULL_ERROR("0011","�п�Jmobileroute"),
        IBS_TN_FORMAT_ERROR("0012","TN�榡���~"),
        IBS_ZIPCODE_NULL_ERROR("0013","�п�Jzipcode"),
        IBS_CREATE_ORDER_ERROR("0014","IBS�إ��p����~ ���p��OAPS�޲z�H��"),
        IBS_CPCNBR_NULL_ERROR("0015","�п�Jcpcnbr"),
        IBS_CPCNBR_FORMAT_ERROR("0016","cpcnbr�榡���~"),
        IBS_QUERY_COUNT_ERROR("0017","�d�߸�Ƽƶq�o�Ϳ��~,���p��OAPS�޲z�H��"),
        IBS_QUERY_NOT_FOUND("0018","�d�L���p��"),
        IBS_TDMPOTS_API_ERROR("0019","call TDM POTS�o�Ϳ��~"),
        IBS_OUTGRE_UNDEFINED_ERROR("0020","�S���w�q��OUT_GRE"),
        IBS_OUTGRE_NOT_CLK("0021","�S���w�q��ڭ���(outgre�ĤT�X������0)")
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
