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
//    time put
    public static final int TIMEOUT = 60000;
    // file size import
    public static final long FILE_SIZE_IMPORT = 5242880;
    public static final int FILE_SIZE_IMPORT_MB = 5;
    public static final int SCALE_4 = 4;
    public static final int SCALE_2 = 2;
    public static final int SCALE_0 = 0;
    public static final int SERIAL_LIMIT = 19;

//  
    public static final String LOGINWSO2 = "loginWso2";
    public static final String LANGUAGE = "language";
    public static final String CAS = "cas";
    public static final String ACTIVE = "1";
    public static final String DEACTIVE = "0";
    public static final String ITEM_NAME = "itemName";
    public static final String ITEM_CODE = "itemCode";
    public static final String PAGE_SIZE_DEFAULT_5 = "5";
    public static final String PAGE_SIZE_DEFAULT_10 = "10";
    public static final String PAGE_SIZE_DEFAULT_15 = "15";
    public static final String PAGE_SIZE_DEFAULT_20 = "20";
    public static final String PAGE_SIZE_DEFAULT_25 = "25";
    public static final String STT = "STT";
    public static final String[] PAGE_SIZE_LIST = {"5", "10", "15", "20", "25"};
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
    public static final String CHECKBOX_COLUMN = "selected";
    public static final String DELETE = "del";
    public static final String STYLE_BOLD_TEXT = "v-label-bold";
    public static final String UPDATED = "UPDATED";
    public final class ERROR {

        public static final String UPDATE_FAIL = "updateFail";
        public static final String ERROR_EXISTED = "ERROR_EXISTED";
        public static final String GOODS_ERROR_EXISTED = "GOODS_ERROR_EXISTED";
        public static final String ERROR_SYSTEM = "ERROR_SYSTEM";
    }

    public final class ERROR_STAFFSTOCK {

        public static final String UPDATE_FAIL = "updateFail";
        public static final String ERROR_EXISTED = "ERROR_STAFFSTOCK_EXISTED";
        public static final String ERROR_SYSTEM = "ERROR_SYSTEM";
    }

      public final class ENCRIPT {
        public static final String USERCREATEORDER = "lZLYxI7+vinuPXjYAb6xxicZLtz9xvNya26u58My3Sk=";
        public static final String PASSWORDCREATEORDER = "S6AmyMpe4fItGwS0FNfldb1+nuqexSBcC75I6CxjcVc=";
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
        public final static String STATUS_0 = "0"; // moi tao yeu cau
        public final static String STATUS_1 = "1"; // da nhap/xuat
    }

    public final class PASSWORD {

        public final static String USER_WS = "Vd+cfX+izzX2Gki0pBFQNOQaXa2gXoph";
        public final static String PASS_WS = "kydKQ/6rLN/XqZrCImE0GdQaI4ghHySj";
        public final static String USER_SOLR = "YqDO2uxPgoaoPYCVwtaVJg==";
        public final static String PASS_SOLR = "O9KrhFlahekS1+JVLr8/7A==";
    }

    /**
     *
     */
    public final class AREA {

        public static final String ID = "id";
        public static final String AREA = "area";
        public static final String CODE = "code";
        public static final String NAME = "name";
        public static final String DEPENDENT = "dependent";
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
        public final static String ROOT_ID = "1";
        public final static String AREA_TYPE_1 = "1";
        public final static String AREA_TYPE_2 = "2";
        public final static String AREA_TYPE_3 = "3";
        public final static String DESCRIPTION = "description";
        public final static String PATH = "path";
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
        public final static String VOLUME = "volume";
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
        public static final String TYPE_NAME = "typeName";
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

    /**
     *
     */
    public final class CUSTOMER {

        public final static String CODE = "code";
        public final static String NAME = "name";
        public static final String ADDRESS = "address";
        public static final String ADDRESS_REGISTRY_DISPLAY = "addressRegistryDisplay";
        public static final String AGENCY = "agency";
        public static final String CUST_TYPE = "custType";
        public static final String CUST_TYPE_NAME = "custTypeName";
        public static final String BUSS_TYPE_LIST = "bussTypeList";
        public static final String BUSS_TYPE_LIST_NAME = "bussTypeListName";
        public static final String STATUS = "status";
        public static final String STATUS_NAME = "statusName";
        public static final String TELNUMBER = "telNumber";
        public final static String CUST_ID = "custId";
        public static final String EMAIL = "email";
        public static final String FAX = "fax";
        public static final String ADDRESS_REGISTRY = "addressRegistry";
        public static final String ADDRESS_DEPLOY = "addressDeploy";
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
        public final static String TYPE_EXPORT = "2";
        public final static String TYPE_IMPORT = "1";
        public final static String TYPE_EXPORT_REASON = "6";
        public final static String TRANSFER = "7";
        public final static String STATUS_ACTIVE = "1";
        public final static String STATUS_INACTIVE = "0";
        public final static String REASON_TYPE = "REASON_TYPE";
        public final static String CODE = "code";
        public final static String NAME = "name";
        public final static String STATUS = "status";
        public final static String TYPE = "reasonType";
    }

    /**
     *
     */
    public final class APP_PARAMS {

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

    }
    //Hongdq
    public final class ORDER {

        public final static String ORDER_CODE = "orderCode";
        public final static String ORDER_USER = "orderUser";
        public final static String ORDER_ID = "orderId";
        public final static String CONTENT = "content";
        public final static String ORDER_DATETIME = "orderDatetime";
        public final static String CUSTNAME = "custName";

        public final static String IEEXPECT_DATE = "ieExpectDate";
        public final static String ORDER_STOCK_ID = "orderStockId";
        public final static String ORDER_STOCK_NAME = "orderStockIdName";

        public final static String ORDER_STATUS_REJECT = "5";//Da tu choi
        public final static String ORDER_STATUS_CANCEL = "0";//Da huy
        public final static String ORDER_STATUS_SENT_BPDV = "3";//Da gui 
        public final static String ORDER_TYPE_IMPORT = "1";//Nhap kho
        public final static String ORDER_TYPE_EXPORT = "2";//Xuat kho
        public final static String ORDER_STATUS_CREATED_COMMAND = "4";//Da lap lenh

        public final static String ORDER_IEEXPECTDATE = "ieExpectDate";
        public final static String ORDER_STATUS = "orderStatus";
        public final static String ORDER_STATUS_NAME = "orderStatusName";

        public final static String ORDER_STATUS_NOT_CREATE_PLAN = "1";
        public final static String ORDER_STATUS_CREATE_PLAN = "2";
        public final static String ORDER_STATUS_EXPORTED = "3";
        public final static String REJECT_STATUS = "5";

        public final static String ORDER_STATUS_NOT_EDIT_PLAN = "1";
        public final static String ORDER_STATUS_EDIT_PLAN = "2";
        public final static String STATUS_IMPORTED_EXPORTED = "6";
        public final static String ORDER_BY_VTN = "2";
        public final static String ORDER_CUST_CODE_VTN = "VTN";
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

    public final class GOODS {

        public static final String GOODS_ID = "goodsId";
        public static final String GOODS_GROUP = "goodsGroup";
        public static final String GOODS_TYPE = "goodsType";
        public static final String GOODS_CODE = "code";
        public static final String GOODS_NAME = "name";
        public static final String GOODS_UNIT = "unitType";
        public static final String STATUS = "status";
        public static final String DETAILS = "details";
        public static final String NO_SERIAL = "0";
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
        public final static String STATUS_NAME = "statusName";
        public final static String SERVICES_GROUP = "serviceGroup";
        public final static String SERVICES_GROUP_NAME = "serviceGroupName";
        public final static String IS_TREE_DATA = "isTreeData";

        public final static String GENERAL_TABLE = "1";
        public final static String OPTION_DEFAULT = "1";
    }

    public final class STAFF_STOCK {

        public final static String CODE = "stockCode";
        public final static String NAME = "stockName";
        public final static String STATUS = "statusName";
    }

    public final class PARTNER {

        public final static String PARTNER_TYPE = "type";
        public final static String PARTNER_NAME = "name";
        public final static String PARTNER_STATUS = "status";
        public final static String PARTNER_ADDRESS = "address";
        public final static String CODE = "code";
        public final static String NAME = "name";
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
        public final static String DEPENDENT = "dependent";
    }

    public final class COMMON {

        public final static String STATUS = "strStatus";
        public final static String ISSUE_DATE = "issueDate";
        public final static String EXPIRE_DATE = "expiryDate";
        public final static String CODE = "code";
        public final static String ID = "id";
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

        public final static String ADD = "img/add_icon.png";
        public final static String DELETE = "img/delete_icon.png";
        public final static String SEARCH = "img/search_icon.png";
        public final static String UPLOAD = "img/icon-upload.png";
        public final static String RESET = "img/reset_icon.png";
        public final static String RUN = "img/config_button.png";
        public final static String PRINTER = "img/printer.png";
        public final static String EDIT = "img/update_icon.png";
        public final static String UPDATE = "img/update_icon.png";
        public final static String FORWARD = "img/foward_icon.png";
        public final static String DETAIL = "img/icon-detail.png";
        public final static String IMPORT = "img/import_excel.png";
        public final static String EXPORT = "img/export_excel.png";
        public final static String CONFIG = "img/config_button.png";
        public final static String EXIT = "img/exit-icon.png";
        public final static String CANCEL = "img/cancel_icon.png";
        public final static String SAVE = "img/save_icon.png";
        public final static String COPY = "img/copy_icon.png";
        public final static String ACCEPT = "img/check_icon.png";
        public final static String V_LINK = "v-button-link";
        public final static String V_LINK_LEFT = "v-button-link-left";
        public final static String SEND = "img/foward_icon.png";
        public final static String ROLLBACK = "img/rollback.png";
        public final static String HOME = "img/home_icon.png";
        public final static String PREVIOUS = "img/previous_icon.png";
        public final static String NEXT = "img/next_icon.png";

    }

    public final class CAPTION {

        public final static String LIST_CUSTOMER = "goods.list.fieldset.listcustomer";
        public final static String SPECIFIC_CUSTOMER = "sale.policy.specificCust.caption";
    }

    public final class FORMATFILE {

        public final static String EXCEL_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        public final static String EXCEL_XLS = "application/vnd.ms-excel";
        public final static String WORD_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        public final static String WORD_DOC = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    }

    public final class REGEX {

        public final static String REGEX_PHONE = "^\\(?(\\d{3,4})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
        public final static String REGEX_EMAIL = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        public final static String REGEX_NUMBER = "[0-9]+";
        public final static String REGEX_CHAR_NUM = "^[a-zA-Z0-9 _-]+$";
    }
    public final static String COMMA = ",";
    public final static String DETAIL = "common.detail";

    public final class IS_TREE_DATA {

        public final static String YES = "1";
        public final static String NO = "0";
    }

    public final class FILE_NAME {

        public final static String LIST_STAFF = "Danh_sach_nhan_vien.xls";
    }
}
