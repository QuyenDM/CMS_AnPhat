/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;

/**
 *
 * @author tiepnv6
 */
public class Constants {

    /**
     * Common
     */
    public final static String PARAM_SEPERATOR = ":_:";
    public static final long FILE_SIZE_IMPORT = 5242880;
    public static final String LANGUAGE = "com.vcs.properties.language";
    public static final String CAS = "com.vcs.properties.cas";
    public static final String PASSWORD_DEFAULT = "password_default";
    public static final String ACTIVE = "1";
    public static final String DEACTIVE = "0";
    public static final String ITEM_NAME = "itemName";
    public static final String ITEM_CODE = "itemCode";
    public static final String PAGE_SIZE_DEFAULT_5 = "5";
    public static final String PAGE_SIZE_DEFAULT_10 = "10";
    public static final String PAGE_SIZE_DEFAULT_15 = "15";
    public static final String PAGE_SIZE_DEFAULT_20 = "20";
    public static final String PAGE_SIZE_DEFAULT_25 = "25";
    public static final String[] PAGE_SIZE_LIST = {"5", "10", "15", "20", "25"};
    public static final String STT = "STT";
    public static final String ADD = "add";
    public static final String EDIT = "edit";
    public static final String COPY = "copy";
    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR_EXISTED = "ERROR_EXISTED";
    public static final String FAIL = "FAIL";
    public static final String CODE = "code";
    public static final String NAME = "name";
    public static final String TYPE = "type";
    public static final String ADDRESS = "address";
    public static final String STATUS = "status";
    public static final String TEMP = "temp";
    public static final String ASC = "asc";
    public static final String DESC = "desc";
    public static final String STOCK_REPLACE = "@stock";
    public static final String ORDER_REPLACE = "@order";
    public static final String LENGTH = "@length";
    public static final String GOODS_CODE_REPLACE = "@goodsCode";
    public static final String ROLE = "role";
    public static final String CHECK_BOX = "";
    public static final String USERNAME = "userName";
    public static final String PASS = "pass";
    public static final int TIMEOUT = 1800;
    public static final String SIGNIN = "signIn";
    public static final String STYLE_NAME = "custom-feildset";

    public static final String CHECKBOX_COLUMN = "selected";

    public final class FORMATFILE {

        public final static String EXCEL_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        public final static String EXCEL_XLS = "application/vnd.ms-excel";
        public final static String WORD_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        public final static String WORD_DOC = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    }

    public static class SURFACE_TYPE {

        public static final String POLISHED = "P";
    }

    public static class OPERATOR {

        public static final String NAME_EQUAL = "NAME_EQUAL";
        public static final String NAME_LESS_EQUAL = "NAME_LESS_EQUAL";
        public static final String NAME_GREATER_EQUAL = "NAME_GREATER_EQUAL";
        public static final String NAME_NOT_EQUAL = "NAME_NOT_EQUAL";
        public static final String NAME_LESS = "NAME_LESS";
        public static final String NAME_GREATER = "NAME_GREATER";
        public static final String NAME_LIKE = "NAME_LIKE";
        public static final String NAME_IN = "NAME_IN";
        public static final String NAME_OR = "NAME_OR";
        public static final String NAME_AND = "NAME_AND";
    }

    public static class QUALITY {

        public static final String A1 = "A1";
        public static final String B = "B";
        public static final String C = "C";
        public static final String A3 = "A3";
        public static final String A4 = "A4";
        public static final String A2 = "A2";
        public static final String ML = "ML";
        public static final String CL = "CL";
        public static final String MS = "MS";
        public static final String LM = "LM";
        public static final String BTP = "BTP";
        public static final String B3 = "B3";
        public static final String MLVT = "ML-VT";
        public static final String MLMS = "ML-MS";
        public static final String DC = "DC";
        public static final String A3VT = "A3-VT";
        public static final String A4VT = "A4-VT";
    }

    public static class TYPEWS {

        public static final String TYPE_NUMBER = "LONG,INTEGER,SHORT,BYTE,INT,DOUBLE,FLOAT";
        public static final String TYPE_STRING = "STRING";
        public static final String NUMBER = "NUMBER";
        public static final String NUMBER_DOUBLE = "DOUBLE";

        public static final String TYPE_DATE = "DATE";
        public static final String TYPE_TIME = "TIME";
    }

    public static class TYPES {

        public final static String TEXT = "1";
        public final static String PARAMGRAPH_TEXT = "2";
        public final static String MULTIPLE_CHOICE = "3";
        public final static String CHECK_BOXES = "4";
        public final static String COMBOBOX = "5";
        public final static String DATE = "6";
        public final static String TIME = "7";
    }

    public final class ERROR {

        public static final String UPDATE_FAIL = "updateFail";
        public static final String ERROR_EXISTED = "ERROR_EXISTED";
        public static final String ERROR_SYSTEM = "ERROR_SYSTEM";
    }

    public final class ERROR_STAFFSTOCK {

        public static final String UPDATE_FAIL = "updateFail";
        public static final String ERROR_EXISTED = "ERROR_STAFFSTOCK_EXISTED";
        public static final String ERROR_SYSTEM = "ERROR_SYSTEM";
    }

    public final class ROLES {

        public static final String ADMIN = "1";
        public static final String LEAD = "2";
        public static final String STAFF = "3";
    }

    public final class STATISTIC_TYPE {

        public static final String SELF = "1";
        public static final String REPRESENT = "2";
    }

    /**
     *
     */
    public final class PERSISTENT_UNIT {

        public final static String PUWMS = "PUWMS";
        public final static String PUCMS = "PUCMS";
    }

    /**
     *
     */
    public final class ORDER_GOOD_LOCAT {

        public final static String ID = "id";
    }

    /**
     *
     */
    public final class ORDER_GOOD_DETAIL {

        public final static String ID = "id";
        public final static String STATUS_0 = "0"; // 
    }

    /**
     *
     */
    public final class AREA {

        public static final String ID = "id";
        public static final String AREA = "area";
        public static final String CODE = "code";
        public static final String NAME = "name";
        public static final String WARD_NAME = "wardName";
        public static final String WARD_ID = "wardId";
        public final static String PROVINCE_NAME = "provinceName";
        public final static String PROVINCE_ID = "provinceId";
        public final static String DISTRICT_NAME = "districtName";
        public final static String DISTRICT_ID = "districtId";
        public final static String TYPE_PROVINCE = "1";
        public final static String TYPE_DISTRICT = "2";
        public final static String TYPE_WARD = "3";
        public final static String TYPE = "typeName";
        public final static String STATUS = "statusName";
    }

    /**
     *
     */
    public final class ORDER_GOODS_DETAIL {

        public final static String GOODSTYPENAME = "goodsTypeName";
        public final static String GOODSCODE = "goodsCode";
        public final static String GOODSNAME = "goodsName";
        public final static String AMOUNT = "amount";
        public final static String WEIGHT = "weight";
        public final static String GOODSUNITNAME = "goodsUnitName";
        public final static String ORDER_ID = "orderId";
    }

    /**
     *
     */
    public final class TABLE_HEADER_LANG {

        public final static String GOODS_DETAIL_SERIAL = "wms.common.columnheader.goodsdetailserial";
        public final static String ORDER_GOODS_DETAIL = "wms.common.columnheader.ordergoodsdetail";
        public final static String GOODS = "wms.common.columnheader.goods";
        public final static String STOCK_TRANS_DETAIL = "wms.common.columnheader.stocktransdetail";
        public final static String STOCK_TRANS_SERIAL = "wms.common.columnheader.stocktransserial";
        public final static String ZONE = "wms.common.columnheader.zone";
        public final static String ORDERS = "wms.common.columnheader.orders";
        public final static String CUSTOMER = "cms.common.columnheader.customers";
        public final static String ACCOUNT_BANK = "cms.common.columnheader.accountbank";
        public final static String SERVICE = "wms.common.columnheader.service";
        public final static String SALE_POLICY = "sale.policy";
        public final static String SALE_POLICY_CUSTOMER = "sale.policy.customer";
        public final static String SALE_POLICY_CUSTOMER_LEFT = "sale.policy.customer.left.header";
        public final static String SALE_POLICY_CUSTOMER_RIGHT = "sale.policy.customer.right.header";
        public final static String SALE_POLICY_SPECIFIC_CUSTOMER = "sale.policy.specificCust.header";
    }

    /**
     *
     */
    public final class STAFF {

        public static final String CODE = "code";
        public static final String NAME = "name";
        public static final String BIRTH_DATE = "birthDate";
        public static final String EMAIL = "email";
        public static final String TEL_NUMBER = "telNumber";
        public static final String DEPT_ID = "deptId";
        public static final String DEPT_NAME = "deptName";
        public static final String STAFF_TYPE = "staffType";
        public static final String STAFF_TYPE_NAME = "staffTypeName";
        public static final String STATUS = "status";
        public static final String STATUS_NAME = "statusName";
        public static final String VALID = "1";
        public static final String INVALID = "0";
        public static final String STAFF_TYPE_3 = "3";
    }

    public final class DEPARTMENT {

        public static final String DEPT_ID = "deptId";
        public static final String CODE = "code";
        public static final String NAME = "name";
        public static final String PARENT_DEPT_ID = "parentDeptId";
        public static final String PARENT_DEPT_NAME = "parentDeptName";
        public static final String ADDRESS = "address";
        public static final String EMAIL = "email";
        public static final String TEL_NUMBER = "telNumber";
        public static final String FAX = "fax";
        public static final String DEPT_TYPE = "deptType";
        public static final String CONTACT_NAME = "contactName";
        public static final String DESCRIPTION = "description";
        public static final String STATUS = "status";
        public static final String STATUS_NAME = "statusName";
        public static final String DEPT_PATH = "deptPath";
        public static final String CREATE_DATE = "createDate";
        public static final String COMMON_STATUS = "COMMON_STATUS";
        public static final String STAFF_TYPE = "STAFF_TYPE";
        public static final String VALID = "1";
        public static final String INVALID = "0";
    }

    /**
     *
     */
    public final class STOCK {

        public static final String ID = "stockId";
        public static final String CODE = "code";
        public static final String NAME = "name";
        public static final String TYPE = "type";
        public static final String ADDRESS = "address";
        public static final String STATUS = "status";
        public static final String STATUS_NAME = "statusName";
        public static final String OWNER_TYPE_1 = "1";
        public static final String LAYER = "T";
        public static final int CELL_LENGTH = 2;

    }

    /**
     *
     */
    public final class EXPLOITATION {

        public static final String CODE = "code";
        public static final String NAME = "name";
        public static final String TYPE = "type";
        public static final String GOODSTYPELIST = "goodsTypeList";
        public static final String STATUS = "status";
    }

    public final class ACCOUNT_BANK {

        public final static String CODE = "id";
        public final static String STATUS = "status";
        public final static String ACCOUNT_NUMBER = "accountNumber";
        public final static String DESCRIPTION = "description";
        public final static String CUST_ID = "custId";
        public final static String BANK_ID = "bankId";
        public final static String CUST_NAME = "custName";
        public final static String BANK_NAME = "bankName";
    }

    /**
     *
     */
    public final class SALEPOLICY {

        public final static String ID = "id";
        public final static String CODE = "code";
        public final static String NAME = "name";
        public final static String SERVICE_GROUP = "serviceGroup";
        public final static String STATUS = "status";
        public final static String ISSUE_DATE = "issueDate";
        public final static String EXPIRY_DATE = "expiryDate";
        public final static String APPLY_TIMES = "applyTimes";
        public final static String CUST_TYPE_ID_LIST = "custTypeIdList";
        public final static String PRICE_FACTOR_DETAIL_ID = "priceFactorDetailId";
        public final static String AREA_ID_LIST = "areaIdList";
    }

    /**
     * *
     */
    public final class CONTRACT {

        public final static String CONTRACTNO = "contractNo";
        public final static String CONTRACTCODE = "code";
        public final static String CONTRACTID = "contractId";
        public final static String CONTRACTNAME = "name";
        public final static String CUSTOMERCODE = "custCode";
        public final static String CUSTOMER = "custName";
        public final static String CONTRACTVALUE = "contractValue";
        public final static String CONTRACTSIGNDATE = "contractSignDate";
        public final static String CONTRACTSTATUS = "status";
        public final static String CONTRACTTYPE = "contractType";
        public final static String FROMDATE = "startDate";
        public final static String TODATE = "endDate";
    }

    /**
     *
     */
    public final class CONTRACTAPPENDIX {

        public final static String CONTRACT_ID = "contractId";
        public final static String APPENDIX_NO = "contractAppendixNo";
        public final static String NAME = "contractAppendixName";
        public final static String POLICY_CODE = "policyCode";
        public final static String POLICY_NAME = "policyName";
        public final static String FROM_DATE = "contractAppendixFromDate";
        public final static String TO_DATE = "contractAppendixToDate";
        public final static String SERVICE_GROUP = "serviceGroup";

    }

    //
    public final class POLICY_APPENDIX {

        public final static String APPENDIX_NO = "appendixNo";
        public final static String APPENDIX_NAME = "name";
        public final static String POLICY_CODE = "code";
        public final static String POLICY_NAME = "name";
        public final static String FROM_DATE = "fromDate";
        public final static String TO_DATE = "toDate";
    }

    /**
     *
     */
    public final class ZONES {

        public final static String CODE = "code";
        public final static String NAME = "name";
        public final static String REAL_VOLUME = "realVolume";
        public final static String TYPE = "type";
        public final static String TYPE_NAME = "typeName";
        public final static String STATUS = "status";
    }

    /**
     *
     */
    public final class REASON {

        public final static String REASON_ID = "reasonId";
        public final static String REASON_ERROR_UPDATE_TOTALS = "3";
    }

    public final class SHADE {

        public final static String CODE = "code";
        public final static String NAME = "code";
        public final static String DESCRIPTION = "description";
    }

    public final class STATUS_SERIAL {

        public final static String STATUS_0 = "0"; // tao moi
        public final static String STATUS_1 = "1"; // chuyen mai
        public final static String STATUS_2 = "2"; // dang thuc hien mai
        public final static String STATUS_3 = "3"; // chuyen kcs1
        public final static String STATUS_4 = "4"; // da thanh pham
        public final static String STATUS_5 = "5"; // da phai sua
        public final static String STATUS_6 = "6"; // da sua lai
        public final static String STATUS_7 = "7"; // mai lai
        public final static String STATUS_8 = "8"; // huy
        public final static String STATUS_9 = "9"; // trong kho
        public final static String STATUS_10 = "10"; // đã xuất
        public final static String STATUS_11 = "11"; // kho hang 
    }

    public final class STATUS_CHANGE {

        public final static String STATUS_1 = "1"; // thanh pham
        public final static String STATUS_2 = "2"; // phai sua
        public final static String STATUS_3 = "3"; // mai lai
        public final static String STATUS_4 = "4"; // huy
    }

    /**
     *
     */
    public final class APP_PARAMS {

        //
        public final static String CUSTOMER_CONTACT_REGENCY = "CUSTOMER_CONTACT_REGENCY";
        public final static String PROVIDER = "PROVIDER";
        public final static String MAX_SEARCH = "MAX_SEARCH";
        public final static String STOCK_TYPE = "STOCK_TYPE";
        public final static String ACTION_STATUS = "ACTION_STATUS";
        public final static String ZONE_TYPE = "ZONE_TYPE";
        public final static String AREA_STATUS = "AREA_STATUS";
        public final static String AREA_TYPE = "AREA_TYPE";
        public final static String CUSTOMER_TYPE = "CUSTOMER_TYPE";
        public final static String CUSTOMER_STATUS = "CUSTOMER_STATUS";
        public final static String BUSINESS_TYPE = "BUSINESS_TYPE";
        public final static String STATUS_TYPE = "STATUS_TYPE";
        public final static String GOODS_GROUP = "GOODS_GROUP";
        public final static String GOODS_TYPE = "GOODS_TYPE";
        public final static String UNIT_GOODS = "UNIT_GOODS";
        public final static String UNIT_TYPE = "UNIT_TYPE";
        public final static String APPLY_AREA = "APPLY_AREA";
        public final static String PAR_CODE = "parCode";
        public final static String PAR_NAME = "parName";
        public final static String ORDER_STATUS = "ORDER_STATUS";
        public final static String SERVICE_GROUP = "SERVICE_GROUP";
        public final static String COMMON_STATUS = "COMMON_STATUS";
        public final static String RENT_METHOD = "RENT_METHOD";
        public final static String PAR_TYPE = "parType";
        public final static String PAR_ORDER = "parOrder";
        public final static String BARCODE_SIZE = "BARCODE_SIZE";
        public final static String DESCRIPTION = "description";

        //service hungkv
        public final static String SERVICE_TYPE = "SERVICE_TYPE";
        public final static String SERVICE_STATUS = "SERVICE_STATUS";
        public final static String SERVICE_AREA_APPLY = "APPLY_AREA";
        public final static String PRICING_TYPE = "PRICING_TYPE";
        public final static String CONTRACT_TYPE = "CONTRACT_TYPE";
        public final static String BILL_CYCLE_TYPE = "BILL_CYCLE_TYPE";
        public final static String PAY_METHOD = "PAY_METHOD";
        public final static String CONTRACT_STATUS = "CONTRACT_STATUS";
        public final static String ORDER_TYPE = "ORDER_SERVICE_TYPE";
        //duylb1
        public final static String PARTNER_TYPE = "PARTNER_TYPE";
        public final static String REASON_TYPE = "REASON_TYPE";
        public final static String VAT = "VAT";
        public final static String GOODS_STATE = "GOODS_STATE";
        public final static String GOODS_STATE_1 = "1";
        public final static String GOODS_STATE_2 = "2";
        public final static String APPLY_OBJECT_TYPE = "APPLY_OBJECT_TYPE";
        public final static String EXPLOITATION_TYPE = "EXPLOITATION_TYPE";
        public final static String STAFF_TYPE = "STAFF_TYPE";
        public final static String DEPT_TYPE = "DEPT_TYPE";
        //
        public final static String STOCK_STAFF_TYPE = "STOCK_STAFF_TYPE";
        public final static String TYPE_RECEIVE_SHIPPING = "TYPE_RECEIVE_SHIPPING";
        public final static String IMPORT_CUSTOMER = "IMPORT_CUSTOMER";
        public final static String CUSTOMER_SERVICE_STATUS = "CUSTOMER_SERVICE_STATUS";
        public final static String TYPE_CONTRACT = "TYPE_CONTRACT";
    }

    //Button
    public static String BUTTON_EXPORT = BundleUtils.getString("common.button.export");
    public static String BUTTON_CANCEL = BundleUtils.getString("common.button.cancel");
    public static String BUTTON_SEARCH = BundleUtils.getString("common.button.search");
    public static String BUTTON_INSERT = BundleUtils.getString("common.button.insert");
    public static String BUTTON_UPDATE = BundleUtils.getString("common.button.update");
    public static String BUTTON_DELETE = BundleUtils.getString("common.button.delete");
    public static String BUTTON_COPY = BundleUtils.getString("common.button.copy");
    public static String BUTTON_SAVE = BundleUtils.getString("common.button.save");
    public static String BUTTON_REFRESH = BundleUtils.getString("common.button.refresh");
    public static String BUTTON_RECORD = BundleUtils.getString("common.button.record");
    public static String BUTTON_CLOSE = BundleUtils.getString("common.button.close");
    public static String BUTTON_EXIT = BundleUtils.getString("common.button.exit");
    public static String BUTTON_EDIT = BundleUtils.getString("common.button.edit");
    public static String BUTTON_DEFAULT = BundleUtils.getString("utils.goodspack.default");
    public static String BUTTON_UPLOAD = BundleUtils.getString("common.button.upload");
    public static String BUTTON_DETAIL = BundleUtils.getString("common.button.detail");
    public static String BUTTON_ADD = BundleUtils.getString("common.button.add");

    public final class PRODUCT_TYPE {

        public final static String TP = "TP";
        public final static String BTP = "BTP";
        public final static String PP = "PP";
    }

    //Hongdq
    public final class ORDER {

        public final static String CODE_IN_ORDER_SEQ = "CODE_IN_ORDER_SEQ";
        public final static String CODE_OUT_ORDER_SEQ = "CODE_OUT_ORDER_SEQ";
        public final static String ORDER_IMPORT_CODE_TEM = "ORDIMP";
        public final static String ORDER_EXPORT_CODE_TEM = "ORDEXP";
        public final static String ORDER_LOCATION = "orderLocation";
        public final static String ORDER_LOCATION_EXPORT = "orderLocation.export";
        public final static String LOCATION = "location";
        public final static String ORDER_GOODS = "goods";
        public final static String ORDER_CODE = "orderCode";
        public final static String CONTENT = "content";
        public final static String CREATE_DATETIME = "createDatetime";
        public final static String CUSTNAME = "custName";
        public final static String STATUS_NAME = "orderStatusName";
        public final static String STOCK = "orderStockId";
        public final static String STOCK_NAME = "orderStockName";
        public final static String WARDNAME = "wardName";
        public final static String DISPLAY_NAME = "displayName";
        public final static String DISTRICT = "districtName";
        public final static String PROVINCE = "provinceName";
        public final static String TEL = "telNumber";
        public final static String ORDER_DATETIME = "orderDatetime";
        public final static String IE_EXPECT_DATETIME = "ieExpectDate";
        public final static String EXPECT_DATETIME = "expectDate";
// 
        public final static String GOODS_TYPE = "goodsTypeName";
        public final static String GOODS_CODE = "goodsCode";
        public final static String GOODS_NAME = "goodsName";
        public final static String AMOUNT = "amount";
        public final static String GOODS_UNIT = "goodsUnitName";
        public final static String GOODS_PACKING = "goodsPackingId";
        public final static String GOODS_PACKING_CODE = "goodsPackingCode";
        public final static String GOODS_STATE_NAME = "goodsStateName";
        public final static String WEIGHT = "weight";
        public final static String VOLUME = "volume";
        public final static String ORDER_ID = "orderId";
        public final static String ORDER_STATUS_DESTROY = "0";//Trang thai huy yeu cau
        public final static String ORDER_STATUS_NEW = "1"; //Trang thai yeu cau moi
        public final static String ORDER_STATUS_SENT = "2";//Trang thai Yeu cau da gui cho VTP
        public final static String ORDER_STATUS_SENT_TRANS_DEPT = "3";//Trang thai Yeu cau da gui cho BPDV
        public final static String ORDER_TYPE_IMPORT = "1";//YC nhap kho
        public final static String ORDER_TYPE_EXPORT = "2";//YC xuat kho
    }

    public final class STOCK_HINT {

        public final static String STOCK_CODE = "stockCode";
        public final static String STOCK_NAME = "stockName";
        public final static String STOCK_ADDRESS = "stockAddress";
        public final static String RESPON = "respon";
        public final static String ORIGINAL = "original";
        public final static String USED_VOLUMN = "usedVolumn";
        public final static String FREE_BY_REAL = "freeByReal";
        public final static String BY_PLAN = "byPlan";
        public final static String FREE_BY_PLAN = "freeByPlan";
        public final static String UNIT_TYPE = "unitType";
    }

    public final class EMPLOYEE {

        public static final String EMPLOYEE_CODE = "code";
        public static final String EMPLOYEE_NAME = "name";
        public static final String EMAIL = "email";
        public static final String PHONE_NUMBER = "phoneNumber";
        public static final String BIRTH_DATE = "birthDate";
        public static final String UNIT = "unitIdName";
    }

    public final class GOODS_PACK {

        public final static String GOODS_ID = "goodsId";//YC xuat kho
        public static final String PACKING_CODE = "code";
        public static final String PACKING_NUMBER = "packingNumber";
        public static final String PALLET_NUMBER = "palletNumber";
        public static final String UNIT_TYPE = "unitType";
        public static final String UNIT_NAME = "unitTypeName";
        public static final String PACKING_SIZE = "packingSize";
        public static final String PACKING_WEIGHT = "packingWeight";
        public static final String PACKING_VOLUME = "packingVolume";
        public static final String DEFAULT = "packingDefault";
    }

    //
    public final class GOODS_TOTAL {

        public static final String CUST_ID = "custId";
        public static final String OWNER_ID = "ownerId";
        public static final String AMOUNT_ISSUE = "amountIssue";
    }

    public final class GOODS_PACK_DETAILS {

        public static final String GOODSID = "goodsID";
        public static final String GOODS_GROUP = "goodsGroup";
        public static final String GOODS_CODE = "goodsCode";
        public static final String GOODS_NAME = "goodsName";
        public static final String PACK_CODE = "packCode";
        public static final String PACK_DEFAULT = "packDefault";
        public static final String PACK_SIZE = "packSize";
        public static final String PACK_UNIT = "packUnit";
        public static final String PACK_WEIGHT = "packWeight";
        public static final String PACK_VOLUME = "packVolume";
        public static final String PALLET_NUMBER = "palletNumber";
    }

    public final class GOODS_SET {

        public static final String GOODS_CODE = "goodsCode";
        public static final String GOODS_NAME = "goodsName";
        public static final String GOODS_TYPE = "goodsType";
        public static final String GOODS_UNIT = "goodsUnit";
        public static final String GOODS_GROUP = "goodsGroup";
        public static final String QUANTITY = "quantity";

    }

    public final class OPTION_GROUP {

        public final static String ITEM_0 = "0";
        public final static String ITEM_ = "1";
        public final static String ITEM_2 = "2";
        public final static String ITEM_3 = "3";
        public final static String ITEM_4 = "4";
        public final static String ITEM_5s = "5";
    }

    public final class GOODSDTO {

        public final static String GOODSTYPE = "goodsType";
        public final static String GOODSCODE = "code";
        public final static String GOODSNAME = "name";
        public final static String NUMBER = "0";
        public final static String UNITTYPE = "unitType";
        public final static String WEIGHT = "weight";
    }

    public final class STAFFDTO {

        public final static String NAME = "name";
    }

    public final class SERVICES {

        public final static String CODE = "code";
        public final static String NAME = "name";
        public final static String TOTAL = "total";
        public final static String UNIT_TYPE = "type";

        public static final String SEQ = "seq";
        public static final String SERVICE_GROUP = "serviceGroupName";
        public static final String SERVICE_TYPE = "typeName";
        public static final String SERVICE_CODE = "code";
        public static final String SERVICE_NAME = "name";
        public static final String SERVICE_ISSUEDATE = "issueDate";
        public static final String SERVICE_EXPIRYDATE = "expiryDate";
        public static final String SERVICE_STATUS = "statusName";
        public static final String SERVICE_DESCRIPTION = "description";
        public static final String SERVICE_APPLY_AREA = "applyAreaType";

        public final static String SERVICE_GROUP_STOCK = "3";// nhom dv kho
        public final static String ORDER_TYPE_IMPORT = "1";// 
        public final static String ORDER_TYPE_EXPORT = "2";// 

    }

    public final class CONTRACT_APPENDIX_STOCK {

        public final static String CONTRACT_APPENDIX_ID = "stockCode";
        public final static String CONTRACT_APPENDIX_NAME = "stockName";
        public final static String SQUARE = "square";
    }

    public final class HASMAP_GENERAL_INFO {

        public final static String ORDER_ID = "orderId";
        public final static String ORDER_DATETIME = "orderDatetime";
        public final static String PROVINCE_ID = "provinceId";
        public final static String CUSTID = "custId";
        public final static String CUSTCODE = "custCode";
        public final static String CUSTNAME = "custName";
        public final static String ORDERTYPE = "orderType";
        public final static String ORDER_CODE = "orderCode";
        public final static String ORDER_USER = "orderUser";
        public final static String ORDER_STOCK_ID = "orderStockId";
        public final static String IE_EXPECTED_DATETIME = "ieExpectDate";
        public final static String STORAGE_DATETIME = "storageDatetime";
        public final static String PAKING_LIST_NO = "packingListNo";
        public final static String INVOICE_NO = "invoiceNo";
        public final static String PURCHARSE_NO = "purchaseOrderNo";
        public final static String ORDER_STATUS = "orderStatus";
        public final static String ATTACH_FILE_LIST = "attachFileList";
        public final static String CREATE_DATETIME = "createDatetime";
        public final static String NOTE = "note";
        public final static String CONTENT = "content";

    }

    public final class SERVICE_GROUP {

        public final static String SERVICE_GROUP = "serviceGroup";
        public final static String TOTAL = "total";
        public final static String UNIT_TYPE = "unitType";

    }

    /*
     DUYOT_MODEL
    
     */
    public final class ORDER_ACTION {

        public final static String ID = "id";
        public final static String CODE = "code";
        public final static String ACTION_TYPE = "actionType";
        public final static String ORDER_TYPE = "orderType";
        public final static String FINISHORDERDATE = "finishOrderString";
        public final static String CONTENT = "content";
        public final static String ORDER_STOCK_ID = "orderStockId";
        public final static String STATUS = "status";
        public final static String REQUIRE_VOLUMN = "requireVolume";
        public final static String REQUIRE_WEIGHT = "requireWeight";
        public final static String ORDER_ID_LIST = "orderIdList";
        public final static String ORDER_CODE = "orderCode";

    }

    public final class WINDOW_SIZE {
//        public  static Float Size_1200 = 1200f;
//        public  static Float Size_850 = 850f;
//        public  static Float Size_650 = 650f;

    }

    //QuyenDM_Model
    public final class PRICE_FACTOR_DETAIL {

        public final static String CHECK = "Check";
        public final static String CODE = "code";
        public final static String PRICE_FACTOR_ELEMENT_ID_LIST = "priceFactorElementIdList";
        public final static String ELEMENT_ID = "elementIds";
        public final static String STATUS = "status";
        public final static String ID = "id";
        public final static String FROM_DATE = "fromDate";
        public final static String TO_DATE = "toDate";
        public final static String SERVICE_GROUP = "serviceGroup";
        public final static String STRING_STATUS = "stringStatus";
    }

    /**
     * hungkv
     */
    public final class PRICE_FACTORS {

        public final static String CODE = "code";
        public final static String NAME = "name";
        public final static String TYPE = "type";
        public final static String TABLE_NAME = "tableName";
        public final static String STATUS = "status";
        public final static String SERVICES_GROUP = "serviceGroup";
        public final static String IS_TREE_DATA = "isTreeData";

        public final static String GENERAL_TABLE = "1";
        public final static String OPTION_DEFAULT = "1";
    }

    public final class STAFF_STOCK {

        public final static String CODE = "stockCode";
        public final static String NAME = "stockName";
        public final static String STATUS = "statusName";
    }

    //QuyenDM
    public final class SERVICE_PRICE_DETAIL {

        public final static String CODE = "code";
        public final static String SERVICE_PRICE_ID = "servicePriceId";
        public final static String ID = "id";
        public final static String VALUE_FROM = "valueFrom";
        public final static String VALUE_TO = "valueTo";
        public final static String UNIT_TYPE_NAME = "unitTypeName";
        public final static String PRICE_MIN = "priceMin";
        public final static String PRICE = "price";
    }
    //Cac hang so
    public final static int INT_100 = Integer.MAX_VALUE;
    public final static int INT_1000 = 1000;
    public final static int INT_0 = 0;
    public final static int INT_1 = 1;
    public final static String ALL = "all";
    public final static String NULL = "";
    //
    public static final String CURRENT_STAFF_INFO = "CURRENT_STAFF_INFO";
    public static final String USER_TOKEN = "USER_TOKEN";

    public final class TABLE_HEADER_BUTTON {

        public final static String CHOICE = "choice";
        public final static String BTN_ADD = "btnAdd";
        public final static String BTN_DELETE = "btnDelete";
        public final static String BTN_EDIT = "btnEdit";
    }

    public final class COMMON {

        public final static String STATUS = "strStatus";
        public final static String ISSUE_DATE = "issueDate";
        public final static String EXPIRE_DATE = "expiryDate";
        public final static String CODE = "code";
        public final static String ID = "id";
        public final static String UNITID = "unitId";
        public final static String NAME = "name";
        public final static String PARENT_ID = "parentId";
        public final static String STR_1 = "1";
        public final static String STR_0 = "0";
        public final static String STR_2 = "2";
        public final static String STR_3 = "3";
        public final static String VALIDITY = "Hiệu lực";
        public final static String EXPIRE = "Hết hiệu lực";
        public final static String TYPE = "type";
        public final static String DATE_PATERN = "dd/MM/yyyy";
        public final static String ID_SEPARATOR = ";";
        public final static String HH_MM_SS_DD_MM_YYYY = "hh:MM:ss dd-MM-yyyy";
    }

    public final class ICON {

        public final static String HOME = "img/home_icon.png";
        public final static String ADD = "img/add-icon.png";
        public final static String DELETE = "img/icon_delete.png";
        public final static String SEARCH = "img/search.png";
        public final static String RESET = "img/reset.png";
        public final static String RUN = "img/run.png";
        public final static String PRINTER = "img/printer.png";
        public final static String EDIT = "img/edit-icon.png";
        public final static String UPDATE = "img/edit-icon.png";
        public final static String FORWARD = "img/forward.png";
        public final static String DETAIL = "img/icon-detail.png";
        public final static String IMPORT = "img/icon-import.png";
        public final static String EXIT = "img/exit-icon.png";
        public final static String CANCEL = "img/cancel.png";
        public final static String SAVE = "img/icon-save.png";
        public final static String COPY = "img/copy-icon.png";
        public final static String DOC = "img/doc.png";
        public final static String DOCX = "img/docx.png";
        public final static String ACCEPT = "img/accept-icon.png";
        public final static String V_LINK = "v-button-link";
        public final static String V_LINK_LEFT = "v-button-link-left";
        public final static String EXPORT_EXCEL = "img/report_export.png";
        public final static String PDF = "img/pdf.png";
        public final static String HTML = "img/html.jpg";

    }

    public final class CAPTION {

        public final static String CUSTOMER = "customer";
        public final static String TERM_INFORMATION = "term.information";
        public final static String LIST_CUSTOMER = "goods.list.fieldset.listcustomer";
        public final static String SPECIFIC_CUSTOMER = "sale.policy.specificCust.caption";
        public final static String CUSTOMER_CONTACT = "customer.contact";
        public final static String CUSTOMER_CARE_HISTORY = "customerCareHistoryForm";
        public final static String CUSTOMER_STATUS = "customerStatusForm";
    }

    public final class STYLE {

        public final static String CUSTOM_FIELDSET = "custom-feildset";
    }

    public final static String COMMA = ",";
    public final static String DETAIL = "common.detail";
    public final static String CUSTOMER_BIRT = "customers";

    public final static class BIRT_EXPORT {

        public final static String HTML = "1";
        public final static String EXCEL = "2";
        public final static String PDF = "3";
    }

    public final class FILE_NAME {

        public final static String LIST_STAFF = "Danh_sach_nhan_vien.xls";
    }
    public final static String PATH_TEMPLATE = BundleUtils.getStringCas("path_template");
    public final static String PATH_UPLOAD = BundleUtils.getStringCas("path_upload");
    public final static String PATH_EXPORT = BundleUtils.getStringCas("path_export");

    public final class CUSTOMER_STATUS {

        public static final String CUST_ID = "CUST_ID";
        public static final String TAX_CODE = "TAX_CODE";
        public static final String SERVICE = "SERVICE";
        public static final String STAFF_ID = "STAFF_ID";
        public static final String STAFF_CODE = "STAFF_CODE";
        public static final String STATUS = "STATUS";
        public static final String LAST_UPDATED = "LAST_UPDATED";
        public static final String CREATED_DATE = "CREATED_DATE";
    }

    public final class CUSTOMER {

        public static final String CUST_ID = "CUST_ID";
        public static final String ACCOUNT_NO = "ACCOUNT_NO";
        public static final String ADDRESS = "ADDRESS";
        public static final String AGENCY = "AGENCY";
        public static final String BANK_NAME = "BANK_NAME";
        public static final String CUST_TYPE = "CUST_TYPE";
        public static final String DEPLOYADDRESS = "DEPLOYADDRESS";
        public static final String EMAIL = "EMAIL";
        public static final String FAX = "FAX";
        public static final String NAME = "NAME";
        public static final String PROVINCE_ID = "PROVINCE_ID";
        public static final String STATUS = "STATUS";
        public static final String TEL_NUMBER = "TEL_NUMBER";
        public static final String OFFICEADDRESS = "OFFICEADDRESS";
        public static final String DESCRIPTION = "DESCRIPTION";
        public static final String MINE_NAME = "MINE_NAME";
        public static final String REPRESENTATIVE_ID = "REPRESENTATIVE_ID";
        public static final String REPRESENTATIVE_NAME = "REPRESENTATIVE_NAME";
        public static final String STAFF_NAME = "STAFF_NAME";
        public static final String TAXAUTHORITY = "TAXAUTHORITY";
        public static final String TAX_DEPARTMENT = "TAX_DEPARTMENT";
        public final static String CODE = "code";
        public static final String CUST_TYPE_NAME = "custTypeName";
        public static final String BUSS_TYPE_LIST = "bussTypeList";
        public static final String BUSS_TYPE_LIST_NAME = "bussTypeListName";
        public static final String STATUS_NAME = "statusName";
        public static final String TELNUMBER = "telNumber";
        public static final String ADDRESS_REGISTRY = "addressRegistry";
        public static final String ADDRESS_DEPLOY = "addressDeploy";
        public static final String IMPORT_COMMON_INFO = "1";
        public static final String IMPORT_TERM_INFO = "2";
    }

    public final class MAP_STAFF_CUSTOMER {

        public static final String STAFF_ID = "STAFF_ID";
        public static final String STAFF_CODE = "STAFF_CODE";
        public static final String STAFF_NAME = "STAFF_NAME";
        public static final String CUST_TAX_CODE = "CUST_TAX_CODE";
        public static final String CUST_NAME = "CUST_NAME";
        public static final String STAFF_TYPE = "STAFF_TYPE";
    }

    public final class MAP_STAFF_ROLES {

        public static final String STAFF_ID = "STAFF_ID";
        public static final String ROLE_ID = "ROLE_ID";
        public static final String STATUS = "STATUS";
    }

    public final class CATEGORY_LIST {

        public static final String ID = "id";
        public static final String CODE = "code";
        public static final String NAME = "name";
        public static final String RECEIVED_DATE = "receivedDate";
        public static final String END_DATE = "endDate";
        public static final String DESCRIPTION = "description";
        public static final String CREATOR = "creator";
        public static final String CUST_QUANTITY = "custQuantity";
    }

    public final class TAXAUTHORITY {

        public static final String MA_CQT = "maCqt";
        public static final String TEN_CQT = "tenCqt";
        public static final String MA_TINH = "maTinh";
        public static final String MA_QUAN_HUYEN = "maQuanHuyen";
        public static final String STATUS = "status";
    }

    public final class CONFIRM {
        
    }
    public final class REPORT {
        public static final String NAME = "#tencongty";
        public static final String TAX_CODE = "#masothue";
        public static final String FAX = "#fax";
        public static final String EMAIL = "#emailcoquan";
        public static final String TEL_NUMBER = "#sodienthoaiban";
        public static final String OFFICE_ADDRESS = "#diachitruso";
        public static final String DEPLOY_ADDRESS = "#diachigiaodich";
        public static final String TAX_DEPARTMENT = "#coquanquanlythue";
        
        public static final String NGUOI_LIENHE = "#nguoilienhe";
        public static final String CHUCVU_NGUOI_LIENHE = "#chucvunguoilienhe";
        public static final String SDT_NGUOI_LIENHE = "#sodienthoailienhe";
        public static final String EMAIL_NGUOI_LIENHE = "#emaillienhe";
        
        public static final String CMND = "#cmnd";
        public static final String NGAY_CAP_CMND = "#ngaycapcmnd";
        public static final String NOI_CAP = "#noicap";
        public static final String NGUOI_DAIDIEN = "#nguoidaidien";
        public static final String CHUVU_NGUOI_DAIDIEN = "#chucvunguoidaidien";
        public static final String SDT_NGUOI_DAIDIEN = "#sodienthoaidaidien";
        public static final String EMAIL_NGUOI_DAIDIEN = "#emaildaidien";
        
        public static final String PROVIDER = "provider";
        public static final String HINHTHUC = "hinhthuc";
        public static final String GOICUOC = "goicuoc";
        public static final String SOTIEN = "#sotien";
        public static final String CHIETKHAU = "#chietkhau";
        public static final String FILE_NAME = "fileName";
        public static final String CONTRACT_TEMPLATE_CODE = "templateCode";
        public static final String CONTRACT_TEMPLATE_PATH = "templatePath";
        public static final String NOTES = "notes";
        public static final String DISCOUNT_BACK = "discountBack";
        
    }
    
    
    public final class CARE_HIS_REPORT {
        public static final String KNM = "";
        public static final String KLL = "#masothue";
        public static final String SAI_TT = "#fax";
        public static final String TEL_NUMBER = "#sodienthoaiban";
        public static final String OFFICE_ADDRESS = "#diachitruso";
        public static final String DEPLOY_ADDRESS = "#diachigiaodich";
        public static final String TAX_DEPARTMENT = "#coquanquanlythue";
        
        public static final String NGUOI_LIENHE = "#nguoilienhe";        
        public static final String CHUCVU_NGUOI_LIENHE = "#chucvunguoilienhe";
        public static final String SDT_NGUOI_LIENHE = "#sodienthoailienhe";
        public static final String EMAIL_NGUOI_LIENHE = "#emaillienhe";
        
        public static final String CMND = "#cmnd";
        public static final String NGAY_CAP_CMND = "#ngaycapcmnd";
        public static final String NOI_CAP = "#noicap";
        public static final String NGUOI_DAIDIEN = "#nguoidaidien";
        public static final String CHUVU_NGUOI_DAIDIEN = "#chucvunguoidaidien";
        public static final String SDT_NGUOI_DAIDIEN = "#sodienthoaidaidien";
        public static final String EMAIL_NGUOI_DAIDIEN = "#emaildaidien";
        
        public static final String PROVIDER = "provider";
        public static final String HINHTHUC = "hinhthuc";
        public static final String GOICUOC = "goicuoc";
        public static final String SOTIEN = "sotien";
        public static final String CHIETKHAU = "chietkhau";
        public static final String FILE_NAME = "fileName";
        public static final String CONTRACT_TEMPLATE_CODE = "templateCode";
        public static final String CONTRACT_TEMPLATE_PATH = "templatePath";
        public static final String NOTES = "notes";
        public static final String DISCOUNT_BACK = "discountBack";
        
    }
    
    
}
