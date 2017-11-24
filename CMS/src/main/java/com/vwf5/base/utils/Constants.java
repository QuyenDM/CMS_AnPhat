/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vwf5.base.utils;

import java.io.File;
import java.math.BigDecimal;

/**
 * Khi merge cac phan he vao thi can co mot COMMON COnstants class.
 *
 * @author Khuong Dao
 */
public class Constants {

    public final static String PCK_CBS_BILLING = "cbs_billing";
    public final static String STATUS_ACTIVE = "1";
    public final static String DOWNLOAD_TYPE_FTP = "1";
    public final static String DOWNLOAD_TYPE_SFTP = "2";
    public final static Long MIN_USABLE_SPACE = 1L;
    public final static int SFTP_PORT = 22;
    public final static String FTP_SEPARATOR = "/";
    public static Integer QUEUE_CAPACITY = Integer.valueOf(20000);
    public static int NUMBER_IMPORT_THREAD_DEFAULT = 10;
    public static Long TIME_QUEUE_SLEEP_BEFORE_BEFORE_REMOVE_FILE = 60L;
    public static String QUEUE_DIRECTORY = "queue";
    public static String FORMAT_ERROR_DIRECTORY = "formaterror";
    public static Long READ_FILE_THREAD_SLEEP_ON_FULL = Long.valueOf(5000L);
    public static Long NUMBER_CDR_TO_RUN_GC = 1000000L;
    public static Long TIME_SLEEP_PROCESS_LOCKED_DATA = 5000L;
    public static Long TIME_WAIT_FILE_IMPORT_FROM_QUEUE = 180000L;
    public static Long TIME_QUEUE_COMMIT_BEFORE_FULL = 30000L;
    public static Long TIME_QUEUE_SLEEP_WAIT_FILE = 5000L;
    public static final int STRING_TYPE = 1;
    public static final int NUMBER_TYPE = 2;
    public static final int DATE_TYPE = 3;
    public static final int DOUBLE_TYPE = 4;
    public static final BigDecimal NUM_CALL = new BigDecimal("4");
    public static final String PROCESS_TYPE_IMPORT = "IMPORT";
    public static final String PROCESS_TYPE_UPDATE = "UPDATE";
    public static final String ENDS_WITH = "ENDSWITH";
    public static final String SEPARATOR = ";";
    public static final int FIRST_TIME = 1;
    public static final int ROWS_PER_PAGE = 0;
    public static final int TO_QUEUE_SUCCESS = 3;
    public static final int FILE_TYPE_ERROR_CONTENT = 2;
    public static final int FILE_TYPE_CORRECT = 3;
    public static final int FILE_TYPE_PREPARE = 4;
    public static final int PROCESS_STATUS_SUCCESS = 1;
    public static int IMPORT_ERROR_OVER_TIME = 3;
    public static final int PROCESS_STATUS_UNSUCCESS = 0;
    public static final int FILE_IMPORT_FROM_QUEUE_ERROR = 5;
    public static final String WRITE_FILE_QUEUE_IMPORT = "READ_FILE_TO_IMPORT_QUEUE";
    public static final String SEPARATOR_OF_FILE_NAME = "_";
    public static final int LENGTH_OF_SEQUENCE = 10;
    public static String IMPORT_ERROR_DIRECTORY = "importerrovertime";
    public static String IMPORT_MANUAL_FILE_ERROR_PATH = "/u01/app/dinhvv/BillingImportManual/";
//    public static String IMPORT_MANUAL_FILE_ERROR_PATH = "D:\\ImportManual";
    public static final String CONFIGURATION_GENERAL = ".." + File.separator + "conf" + File.separator + "GeneralInfo.properties";
    public static boolean IMPORT_READ_FILE_MULTI_PROCESS = true;
    public static final String CDR_PACKAGE = "com.viettel.billing.cdr.";
    public static final String PREFIX = "stream2file";
    public static final String SUFFIX = ".tmp";
    public static final String CDR_CONFIG_PATH = File.separator + "resources" + File.separator + "cdrConfig" + File.separator;
    public static final String LOCAL_INVOICE_PATH = "InvoiceRepository";
    public static final String X1 = "X1";
    public static Long BILL_CYCLE_ID = 0L;
    public static final int CONST_0 = 0;
    public static final int CONST_1 = 1;
    public static final String TELECOM_SERVICE_ADSL = "ADSL";
    public static final String P_ERROR_CODE = "p_error_code";
    public static final String P_ERROR_DESCRIPTION = "p_error_description";
    public static final String USER_PAYMENT = "PAYMENT";// Used as username for calling OCS Service in processes

    /**
     * thiendn1: WebService Config
     */
    public final class WEB_SERVICE_CONS {

        public final static String SEPERATE_URL = "/";
        public final static String SEPERATE_OPERATOR = ":";
        public final static String ARG_HEADER = "$headerArgument$";
        public final static String ARG_BODY = "$argument$";
    }

    public final class SEQ_CONSTANTS {

        public final static String ruleMapSeq = "RT_RULE_MAP_SEQ";
        public final static String ruleMapDetailSeq = "RT_RULE_MAP_DETAIL_SEQ";
        public final static String ruleSeq = "RT_RULE_SEQ";
        public final static String actionSeq = "RT_ACTION_SEQ";
        public final static String expressionSeq = "RT_EXPRESSION_SEQ";
        public final static String subExpressionSeq = "RT_SUB_EXPRESSION_SEQ";
    }

    public final class PRODUCT_CATEGORY_STATUS {

        public final static String ACTIVE_STATUS = "1";
    }

    public final class PRICE_TYPE_STATUS {

        public final static String ACTIVE_STATUS = "1";
    }

    public final class DATA_TYPE {

        public final static String INTEGER = "Integer";
        public final static String LONG = "Long";
        public final static String DOUBLE = "Double";
        public final static String DATE = "Date";
        public final static String STRING = "String";
        public final static String BOOLEAN = "Boolean";
    }
    /**
     * constants group item for combobox on form
     */
    public final static String LANGUAGE_SORT = "LANGUAGE_SORT";

    public static class PARAM_CONSTANTS {

        public final static String CHAR_VALUE_TYPE = "CHAR_VALUE_TYPE";
        public final static String UNIT_MEASURE = "UNIT_MEASURE";
        public final static String CURRENCY_TYPE = "CURRENCY_TYPE";
    }
    public final static String EVENT_GROUP = "ONEOFF";
    public final static String SUCCESS_CODE = "00";
    public final static String PCK_VCBS_PAYMENT = "PCK_VCBS_PAYMENT";
    public final static String PCK_PAYMENT_SUB = "PCK_PAYMENT_SUB";
    public final static String PCK_VCBS_CANCEL_PAYMENT = "PCK_VCBS_CANCEL_PAYMENT";
    public final static String PCK_VCBS_DEPLOY_PAYMENT = "PCK_VCBS_DEPLOY_PAYMENT";
    public final static String PCK_BLOCK_TOOL = "PCK_BLOCK_TOOL";
    public final static String PCK_VAT_DECLARATION = "PCK_VAT_DECLARATION";
    public final static String VIEW_ACCOUNT_DEBIT_NO_HOT_CHARGE = "1";
    public final static String VIEW_ACCOUNT_DEBIT_WITH_HOT_CHARGE = "2";
    public static final String TRANSFER_ERROR_CODE_KEY = "p_error_code";
    public static final String TRANSFER_ERROR_DESCRIPTION_KEY = "p_error_description";
    public static final String SUCCESS = "00";
    public static final String TRANSFER_RESULT_KEY = "result";
    public static final String TRANSFER_PREPAID_KEY = "prepaid_amount";
    public static final String TRANSFER_DESCRIPTION_KEY = "description";

    public static class BILL_CYCLE {

        public final static String STATUS_NEW = "0";
        public final static String STATUS_PENDING = "2";
        public final static Long PARENT_NODE = 0L;

        public final static int CONST_0 = 0;
        public final static int CONST_1 = 1;
        public final static String STATUS = "0";
        public final static String STATUS_1 = "1";
    }

    public static class APP_DOMAIN {

        public final static String TYPE_BLOCK_TREE_STATUS = "BLOCK_TREE_STATUS";
        public final static String BLOCK_TABLE_TYPE = "BLOCK_TABLE_TYPE";
        public final static String COMMON_STATUS = "COMMON_STATUS";
        public final static String ADJUSTMENT_POLICY = "ADJUSTMENT_POLICY";
        public final static String TRANSACTION_ADJUSTMENT_TYPE = "TRANSACTION_ADJUSTMENT_TYPE";
        public final static String TRANSACTION_ADJUSTMENT_STATUS = "TRANSACTION_ADJUSTMENT_STATUS";
        public final static String BLOCK_TEMPLATE_TYPE = "BLOCK_TEMPLATE_TYPE";
        public final static String BLOCK_PARAMETER_TYPE = "BLOCK_PARAMETER_TYPE";
        public final static String REASON_TYPE = "REASON_TYPE";
        public final static String BLOCK_ACTION = "BLOCK_ACTION";
        public final static String DELAY_TYPE = "DELAY_TYPE";
        public final static String DELAY_LIST = "DELAY_LIST";
        public final static String UPDATE_PREPAID_TO_OCS = "UPDATE_PREPAID_TO_OCS";
        public final static String GET_PREPAID_FROM_OCS = "GET_PREPAID_FROM_OCS";
        public final static String BLOCK_VALUE_TYPE = "BLOCK_VALUE_TYPE";
        public final static String PAYMENT_STATUS = "PAYMENT_STATUS";

        public final static String TYPE_WARNING_MESSAGE_TYPE = "WARNING_MESSAGE_TYPE";
        public final static String TYPE_WARNING_PARTENT_SIGN = "WARNING_PARTENT_SIGN";
        public final static String TYPE_WARNING_PARAMETER_TYPE = "WARNING_PARAMETER_TYPE";
        public final static String TYPE_WARNING_EMAIL_TYPE = "WARNING_EMAIL_TYPE";
        public final static String TYPE_TOOL_PROM_ATT_TABLE = "TOOL_PROM_ATT_TABLE";

        public final static String LENGTH_UNIT = "BILL_CYCLE_UNIT";
        public final static String BILL_CYCLE_STATUS = "BILL_CYCLE_STATUS";
        public final static String DOC_ATT_TYPE = "DOC_ATT_TYPE";
        public final static String TAG_TYPE = "TAG_TYPE";
        public final static String DOCUMENT_EXPORT_TYPE = "DOCUMENT_EXPORT_TYPE";
        public final static String EXPORT_DOC_TYPE = "EXPORT_DOC_TYPE";
        public final static String BILL_ITEM_TABLE = "BILL_ITEM_TABLE";
        public static final String TYPE_PROMOTION_LEVEL = "PROMOTION_LEVEL";
        public static final String TYPE_TOOL_PROMOTION_TYPE = "TOOL_PROMOTION_TYPE";
        public static final String TYPE_TOOL_PROM_EFFECT_FIELD = "TOOL_PROM_EFFECT_FIELD";
        public static final String TYPE_MONEY_TYPE = "MONEY_TYPE";
        public static final String TYPE_MONEY_UNIT = "MONEY_UNIT";
        public static final String TOOL_PROMOTION_DEFINE_TYPE = "TOOL_PROMOTION_DEFINE_TYPE";
        public static final String PATTERN_MODULE = "PATTERN_MODULE";
        public static final String MAPPING_DATA_TYPE = "MAPPING_DATA_TYPE";
        public static final String BLOCK_CYCLE_WARNING_TYPE = "BLOCK_CYCLE_WARNING_TYPE";
        public static final String CLOSE_CYCLE_WARNING_TYPE = "CLOSE_CYCLE_WARNING_TYPE";

        public static final String STATUS_ACTIVE = "1";
        public static final String STATUS_INACTIVE = "0";
        public static final String CLASS_NAME = "AP_DOMAIN";
        public static final String SUB_ACC_TYPE = "SUB_ACC_TYPE";

    }

    public static class BLOCK_STEP {

        public final static String STEP_TYPE_WARNING = "1";
        public final static String STEP_TYPE_BLOCK = "2";
    }

    public static class BLOCK_DATE_DETAIL {

        public final static String STATUS_READLY = "1";
        public final static Long PARENT_NODE = 0L;
    }

    public static class BLOCK_DETAIL {

        public final static String STATUS_DRAFF = "0";
        public final static String STATUS_READLY = "1";
        public final static String STATUS_RUNNING = "2";
        public final static String STATUS_ERROR = "3";
        public final static String STATUS_SUCCESS = "4";
        public final static String STATUS_KILLED = "5";
        public final static String STATUS_READLY_BLOCK = "6";
        public final static String STATUS_RUNNING_BLOCK = "7";
        public final static String STATUS_COMPLETE_BLOCK = "8";
        public final static String STATUS_ERROR_BLOCK = "9";
    }

    public static class PAYMENT_RULE {

        public final static String STATUS_INACTIVE = "0";
        public final static String STATUS_ACTIVE = "1";
        public final static String IS_DEFAULT = "1";
        public final static String IS_NOT_DEFAULT = "0";
    }

    public static class BLOCK_UNBLOCK_DETAIL {

        public final static String ERROR_NO_INFO = "ACCOUNT INFORMATION NOT FOUND";
        public final static String ACT_STATUS_ERROR = "ACC_STATUS ERROR";
        public final static String BLOCK_IMPOSIBLE = "NOT ALLOW TO BLOCK";
        public final static String UNBLOCK_IMPOSIBLE = "NOT ALLOW TO UNBLOCK";
        public final static String BLOCK_FAIL_IN_LOG = "ERROR IN LOGGING PROCESS";
        public final static String ERROR_INVALID_BARRING_TYPE = "INVALID BARRING TYPE";
        public final static String ERROR_NOT_ENOUGH = "NOT ENOUGH INFORMATION";
        public final static String ERROR_DUPLICATE = "DUPLICATED ISDN";
        public final static String ERROR_ISDN_WRONG_FORMAT = "INVALID ISDN";
        public final static String ERROR_TOO_MANY = "TOO MANY COLUMNS";
        public final static String ERROR_IS_NORMAL = "SUBSCRIBER IS NORMAL";
        public final static String BLOCK_TYPE_NOT_VALID = "Can not change current block type to the next block type";
        public final static String ERROR_CANT_UPDATE_STATUS = "Unable to update new status for account";
        public final static String STATUS_BIT1_1 = "block 1 way by customer request";
        public final static String STATUS_BIT1_2 = "block 2 ways by customer request";
        public final static String STATUS_BIT2_1 = "block 1 way by debt";
        public final static String STATUS_BIT2_2 = "block 2 ways by debt";
        public final static String STATUS_BIT3_1 = "block 1 way by red warning";
        public final static String STATUS_BIT3_2 = "block 2 ways by red warning";
        public final static String STATUS_NORMAL = "normal";
    }

    public static class BLOCK_RULE {

        public static final String LOG_TYPE_INSERT = "1";
        public static final String LOG_TYPE_UPDATE = "2";
        public static final String LOG_TYPE_DELETE = "3";

        public static final String LOG_MESSAGE_ACTION_VALUE_INSERT = "insert block_action_value_use: block_action_value_use_id = ";
        public static final String LOG_MESSAGE_ACTION_VALUE_UPDATE = "update block_action_value_use: block_action_value_use_id = ";

        public static final String LOG_MESSAGE_STEP_INSERT = "insert block_step: block_step_id = ";
        public static final String LOG_MESSAGE_STEP_UPDATE = "update block_step: block_step_id = ";
        public static final String LOG_MESSAGE_STEP_DELETE = "update block_step SET status = '0' WHERE block_step_id = ";

        public static final String LOG_MESSAGE_RULE_INSERT = "insert block_rule : block_rule_id = ";
        public static final String LOG_MESSAGE_RULE_UPDATE = "update block_rule : block_rule_id = ";
        public static final String LOG_MESSAGE_RULE_DELETE = "update block_rule SET status = '0' WHERE block_rule_id = ";

    }

    public static class PAYMENT_REQUEST_STATUS {

        public final static String STATUS_BACKDATE = "0";
        public final static String STATUS_NORMAL = "1";
        public final static String STATUS_SUSPEND = "2";
    }

    public static class TRANSACTION_STATUS {

        public final static String DESTROYED = "0";//Destroyed
        public final static String RECEIVED = "1";//Received
        public final static String WAITING = "2"; //Waiting
        public final static String CONFIRMED = "3"; //Confirmed
    }

    public static class CONFIRM_STATUS {

        public final static int CONFIRMED = 1;
        public final static int UNCONFIRMED = 0;
    }

    public static class SERVICE_RESULT_STATUS {

        public final static int NO_DATA_FOUND = 0;
        public final static int ERROR_PROCESS = -1;
    }

    public static class SERVICE_RECEIVE_DATE_STATUS {

        public final static int LESS_START_BILL_CYCLE = -1;
        public final static int VALID = 0;
        public final static int GREATER_SYSDATE = 1;
    }

    public static class SUBSCRIBER {

        public static final String CLASS_NAME = "SUBSCRIBER";
        public static final String STATUS_ACTIVE = "3";
        public final static String STATUS_INACTIVE = "0";
    }

    public static class AP_DOMAIN {

        public static final String STATUS_ACTIVE = "1";
        public static final String STATUS_INACITIVE = "0";
        public static final String CLASS_NAME = "AP_DOMAIN";
    }

    public static class MAPPING_TEMPLATE_TABLE {

        public static final String STATUS_ACTIVE = "1";
        public static final String STATUS_INACITIVE = "0";
        public static final String CLASS_NAME = "MAPPING_TEMPLATE_TABLE";
    }

    public static class REQUEST_PARAMETER_USE {

        public static final String STATUS_ACTIVE = "1";
        public static final String STATUS_INACITIVE = "0";
        public static final String CLASS_NAME = "REQUEST_PARAMETER_USE";
    }

    public static class OUTPUT_TABLE {

        public static final String STATUS_ACTIVE = "1";
        public static final String STATUS_INACITIVE = "0";
        public static final String CLASS_NAME = "OUTPUT_TABLE";
    }

    public static class ACCOUNT_PAYMENT {

        public static final String HOTCHARGE_MODE_1 = "1";
        public static final String HOTCHARGE_MODE_2 = "2";
    }

    public static class WEB_SERVICE_BILLING {

        public static final String URL = "http://10.58.71.130:8031/BillingWebService/billingService";
        public static final String NAME_SPACE = "http://webservice.billing.viettel.com/";
        public static final int TIMEOUT = 50000;
    }

    public static class WEB_SERVICE_SALE {

        public static final String URL = "http://10.58.71.129:9020/SaleWebservice/bpm/saleService";
        public static final String NAME_SPACE = "http://webservice.sale.viettel.com/";
        public static final Long ACTION_ID = 3L;
        public static final int TIMEOUT = 50000;
    }

    public static class WEB_SERVICE_OCS {

        public static final String URL = "http://10.60.19.23:9898/wsocs";
        public static final String NAME_SPACE = "xmlns:wsoc=\"http://www.vietteltelecom.vn/wsocs\"";
        public static final String USER_NAME = "hungtv";
        public static final String PASSWORD = "123456";
        public static final String PAYMENT_FUNCTION = "wsoc:Payment";
        public static final String DEDUCT_FUNCTION = "wsoc:Deduct";
        public static final String QUERY_ACCOUNT_BALANCE = "wsoc:QueryAcctBal";
        public static final String PREFIX_ASDN = "84";
        public static final String ONEWAYBLOCK = "wsoc:OnewayBlock4Due";
        public static final String TWOWAYBLOCK = "wsoc:TwowayBlock4Due";
        public static final String ONEWAYUNBLOCK = "wsoc:Reactivation4OnewayBlockDebt";
        public static final String TWOWAYUNBLOCK = "wsoc:Reactivation4TwowayBlockDebt";
        public static final int TIMEOUT = 50000;
    }

    public static class SUB_ACCOUNT_DEBIT_TAX {

        public static final String TAX_TYPE_PERCENT = "1";
        public static final String TAX_TYPE_PERCENT_SIGN = "%";
        public static final String CLASS_NAME = "SUB_ACCOUNT_DEBIT_TAX";
    }

    public static class BLOCK_TABLE {

        public static final String SCHEMA_PAYMENT = "CBS_PAYMENT";
        public static final String STATUS_ACTIVE = "1";
        public static final String CLASS_NAME = "BLOCK_TABLE";
    }

    public static class WARNING_MESSAGE {

        public static final String STATUS_ACTIVE = "1";
        public static final String STATUS_INACTIVE = "0";
    }

    public static class WARNING_EMAIL {

        public static final String STATUS_ACTIVE = "1";
        public static final String STATUS_INACTIVE = "0";
    }

    public static class WARNING_MESSAGE_CONFIG {

        public static final String MODE_1 = "1";
    }

    public static class WARNING_EMAIL_CONFIG {

        public static final String MODE_1 = "1";
    }

    public static class REASON {

        public final static String ADJUSTMENT_SINGLE = "ADJUSTMENT_SINGLE";
        public static final String STATUS_ACTIVE = "1";
        public static final String STATUS_INACTIVE = "0";
    }

    //---------------------
    public static class FORM_MODE {

        public static final String ADD = "add";
        public static final String UPDATE = "update";
    }

    public static class SQL_TYPE {

        public final static int SaveTraceFileImportError = 1;
        public final static int UpdateTraceFileImpErr = 2;
        public final static int SaveTraceFileInfo = 3;
        public final static int DeleteTraceFileInfo = 4;
        public final static int UpdateTraceFileInfo_StatusNull = 5;
        public final static int UpdateTraceFileInfo_Status_Not_Null = 6;
        public final static int GetTraceFileImportErr = 7;
    }

    public static class AP_PARAM {

        public final static String TYPE_BACKUP_STYLE = "BACKUP_STYLE";
        public final static String TYPE_DOWNLOAD_TYPE = "DOWNLOAD_TYPE";
        public final static String CALCULATED_BY = "CALCULATED_BY";
        public final static String COMMON_STATUS = "COMMON_STATUS";
        public final static String CHARGE_REFERENCE = "CHARGE_REFERENCE";
        public final static String PROM_DEPENDENCE = "PROM_DEPENDENCE";
        public final static String PROM_CHARGE_BY = "PROM_CHARGE_BY";
        public final static String PROMOTION_TYPE = "PROMOTION_TYPE";
        public final static String PROMOTION_LEVEL = "PROMOTION_LEVEL";
        public final static String BILL_ITEM_TABLE = "BILL_ITEM_TABLE";
        public final static String ADJUSTMENT_TYPE = "ADJUSTMENT_TYPE";
        public final static String ADJUSTMENT_POLICY = "ADJUSTMENT_POLICY";
        public final static String PENALTY = "PENALTY";
        public final static String COMPENSATION = "COMPENSATION";
        public final static String RATE_EQUAL = "RATE_EQUAL";

    }

    public static class INVOICE_TYPE {

        public final static String TYPE_INVOICE = "INVOICE";
        public final static String TYPE_BILLING = "BILLING";
        public final static String TYPE_INVOICE_BILLING = "INVOICE_BILLING";
        public final static String TYPE_VAT_DECLARATION = "VAT_DECLARATION";
        public final static int CONST_0 = 0;
        public final static int CONST_1 = 1;
        public final static int CONNECTION_DB = 3;
        public final static String STATUS = "1";

    }

    public static class PROMOTION {

        public final static String STATUS_ACTIVE = "1";
    }

    public static class PROMOTION_CONSTANTS {

        public final static String ADJUSTMENT_NEGATIVE = "0";
        public final static String ADJUSTMENT_POSITIVE = "1";
        public final static String KIND_ACCUMULATIVE = "1";
        public final static String KIND_NON_ACCUMULATIVE = "0";
        /**
         * Money of Promotion/Adjustment
         */
        public static final String MONEY_TYPE_1 = "1";
        /**
         * Remainder of money of Promotion/Adjustment
         */
        public static final String MONEY_TYPE_2 = "2";

        public static final String PROM_UNIT_MONEY = "MONEY";
        public static final String PROM_UNIT_PERCENT = "PERCENT";
    }

    public static class TOOL_PROM_USAGE_DETAIL {

        public final static String STATUS_ACTIVE = "1";
        public final static String STATUS_INACTIVE = "0";
        public final static String CLASS_NAME = "TOOL_PROM_USAGE_DETAIL";
    }

    public static class TOOL_PROM_USAGE_TOTAL {

        public final static long STATUS_ACTIVE = 1L;
        public final static long STATUS_INACTIVE = 0L;
        public final static String CLASS_NAME = "TOOL_PROM_USAGE_TOTAL";
    }

    public static class TOOL_PROM_USAGE_TOTAL_DETAIL {

        public final static String STATUS_ACTIVE = "1";
        public final static String STATUS_INACTIVE = "0";
        public final static String CLASS_NAME = "TOOL_PROM_USAGE_TOTAL_DETAIL";
    }

    public static class INVOICE_TAG {

        public final static int CONST_0 = 0;
        public final static int CONST_1 = 1;
        public final static int CONNECTION_DB = 3;
        public final static String STATUS = "1";
        public final static String TAG_NAME = "INVOICEBATCH";
        public final static String STRING_0 = "0";
        public final static String STRING_1 = "1";
    }

    public static class APP_LOG {

        public final static String INSERT = "1";
        public final static String TAG_INVOICE = "INVOICE_TAG";
        public final static String INVOICE_ATRRIBUTE = "INVOICE_ATRRIBUTE";
        public final static String INVOICE_EXPORT_CONFIG = "INVOICE_EXPORT_CONFIG";
        public final static String INVOICE_TYPE_PATTERN = "INVOICE_TYPE_PATTERN";
        public final static String UPDATE = "2";
        public final static String DELETE = "3";
    }

    public static class INVOICE_EXPORT_CONFIG {

        public final static int CONST_0 = 0;
        public final static int CONST_1 = 1;
        public final static String STATUS = "1";
    }

    public static class ACCOUNT {

        public final static String STATUS_ACTIVE = "1";
        public final static String STATUS_INACTIVE = "0";
        public final static String CLASS_NAME = "ACCOUNT";
    }

    public static class INVOICE_STATUS {

        public final static String GROUP_STAGE = "Stage";
        public final static String GROUP_PRODUCE = "Produce";
        public final static String GROUP_SIGNING = "Signing";
        public final static String GROUP_EXPORTTOFINANCE = "ExportToFinance";

        public final static String CREATED = "Created";
        public final static String UNPRODUCE = "UnProduce";
        public final static String UNSIGNED = "UnSigned";
        public final static String UNSENT = "UnSent";

    }

    public static class TRACE_FILE_INFO {

        public final static Short STATUS_SUCESS = 1;
        public final static Short STATUS_NOT_DONE = 0;
        public final static Short STATUS_TYPE_INVALID_FORMAT = 0;
        public final static Short STATUS_TYPE_MISS_SEQ = 1;
        public final static Short STATUS_TYPE_MISS_CONTENT = 2;
        public final static Short STATUS_TYPE_SUCESS = 3;
        public final static Short STATUS_TYPE_STOP_SUDDENLY = 4;
    }

    public static class BACKUP_STYLE {

        public final static String RENAME = "RENAME";
        public final static String DELETE = "DELETE";
        public final static String MOVE = "MOVE";
        public final static String MOVE_DAILY = "MOVE_DAILY";
        public final static String MOVE_MONTHLY = "MOVE_MONTHLY";
        public final static String MOVE_YEARLY = "MOVE_YEARLY";
        public final static String MOVE_AND_RENAME = "MOVE_AND_RENAME";
    }

    public static class TOOL_VALUE {

        public final static String ATT_TABLE = "ATT_TABLE";
        public final static String TABLE_NAME = "BILL_ITEMS";
        public final static String PROMOTION_LEVEL = "PROMOTION_LEVEL";
        public final static String PROMOTION_TYPE = "PROMOTION_TYPE";
        public final static String MONEY_TYPE = "MONEY_TYPE";
        public final static String MONEY_UNIT = "MONEY_UNIT";
        public final static String STATUS_ACTIVE = "1";
    }

    public static class BILL_ITEMS {

        public final static String STATUS_ACTIVE = "1";
        public final static String STATUS_INACTIVE = "0";
        public final static String CLASS_NAME = "BILL_ITEMS";
    }

    public static class TOOL_PROMOTION {

        public static final String STATUS_ACTIVE = "1";
        public static final String CLASS_NAME = "TOOL_PROMOTION";
    }

    public static class TOOL_PROM_USAGE {

        public static final Long STATUS_ACTIVE = 1L;
        public static final Long STATUS_INACTIVE = 0L;
        public static final String CLASS_NAME = "TOOL_PROM_USAGE";
    }

    public static class PACKAGES {

        public final static String CBS_BILLING = "CBS_BILLING";
    }

    public static class FUNCTION {

        public final static String PCK_TOOL_PROMOTION = "pck_tool_promotion";
    }

    public static class PATTERN {

        public final static String CLASS_NAME = "PATTERN";
        public final static String STATUS_ACTIVE = "1";
        public final static String STATUS_INACTIVE = "0";
        public final static String INV_FILE_NAME = "INV_FILE_NAME";
    }

    public static class CLOSE_BOOK_GROUP {

        public final static int LENGTH_SEARCH = 5;
        public final static String GROUP_TYPE_REST = "2";
        public final static String GROUP_TYPE_CONDITION = "1";
        public final static char GROUP_TYPE_MANUAL = '0';
        public final static char GROUP_TYPE_VALUE = '0';
        public final static char PROCESS_TYPE_AUTO = '1';
        public final static char PROCESS_TYPE_MANUAL = '0';
        public final static int CONNECTION_DB = 3;
        public final static int CONST_0 = 0;
        public final static int CONST_1 = 1;
        public final static String STATUS = "0";
        public final static String STATUS_1 = "1";
    }

    public static class ADJUSTMENT_ACCOUNT_TEMPLATE {

        public final static String COL_NO = "NO";
        public final static String COL_ACCOUNT_NUMBER = "ACCOUNT_NUMBER";
        public final static String COL_TYPE = "TYPE";
        public final static String COL_AMOUNT = "AMOUNT";
    }

    public static class ADJUSTMENT_SUBSCRIBER_TEMPLATE {

        public final static String COL_NO = "NO";
        public final static String COL_ISDN = "ISDN";
        public final static String COL_TYPE = "TYPE";
        public final static String COL_AMOUNT = "AMOUNT";
    }

    public static class ADJUSTMENT_TYPE {

        public final static String ADJUSTMENT = "ADJUSTMENT";
        public final static String COMPENSATION = "COMPENSATION";
        public final static String PENALTY = "PENALTY";
    }

    public static class MONEY_UNIT {

        public final static String MONEY = "MONEY";
        public final static String PERCENT = "PERCENT";
    }

    public static class ACCOUNT_ATT_VALUE {

        public final static String ADJ_APPLIED_AMOUNT = "ADJ_APPLIED_AMOUNT";
        public final static String ADJ_APPLIED_UNIT = "ADJ_APPLIED_UNIT";
        public final static String STATUS_ACTIVE = "1";
        public final static String FIXED_UNIT = "fixed";
        public final static String PERCENT_UNIT = "%";
    }

    public static class TELECOM_SERVICE {

        public final static String CLASS_NAME = "TELECOM_SERVICE";
        public final static Long STATUS_ACTIVE = 1L;
        public final static String PRE_PAID = "1";
        public final static String POST_PAID = "2";
    }

    public static class MAPPING_DATA_CONFIG {

        public final static String STATUS_ACTIVE = "1";
        public final static String CLASS_NAME = "MAPPING_DATA_CONFIG";
    }

    public static class PREPAID_PAYMENT{

        public final static String ADD = "1";
        public final static String DEDUCT = "3";
    }

    public static class ERROR {

        public final static String SUCCESS= "0";
        public final static String ERROR_1 = "1";
    }

}
