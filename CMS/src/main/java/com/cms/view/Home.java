package com.cms.view;

import com.anphat.customer.controller.CustomerManagementOnePageController;
import com.anphat.customer.ui.SearchCustomerForm;
import com.anphat.statistics.controller.StatisticsStaffPointUploadController;
import com.anphat.statistics.ui.StatisticStaffPointUploadDialog;
import com.cms.component.CommonFunctionTableFilter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.cms.component.CommonOnePanelUI;
import com.cms.component.GridManyButton;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.StaffDTO;
import com.cms.dto.StatisticStaffPointDTO;
import com.cms.dto.StatusQuantityDTO;
import com.cms.login.ws.WSCustomerStatus;
import com.cms.login.ws.WSStatisticStaffPoint;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.DateUtil;
import static com.google.gwt.thirdparty.javascript.rhino.head.ScriptRuntime.name;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Home extends CommonOnePanelUI implements View {

    private TabSheet tabSheet;
    private VerticalLayout stasticPointLayout;
    private VerticalLayout statusQuantityLayout;
    private CommonTableFilterPanel tblStatistic;
    private CommonTableFilterPanel tblPoints;
    private final LinkedHashMap<String, CustomTable.Align> HEADER
            = BundleUtils.getHeadersFilter("statisticStaffPoint.header");
    private final LinkedHashMap<String, CustomTable.Align> HEADER_STATUS_QUANTITY
            = BundleUtils.getHeadersFilter("status.quatity.header");
    private List<AppParamsDTO> lstCustomerStatus;
    private List<StatisticStaffPointDTO> lstStatisticStaffPoint;
    private List<StatusQuantityDTO> lstStatusQuantity;
    private BeanItemContainer beanContainer;
    private BeanItemContainer beanContainerStatistic;
    private final String CAPTION = BundleUtils.getString("caption.tbl.statistic.staff.point");
    private StaffDTO staff;
    private Button btnUpload;
    private GridLayout gridCaption;
    private Label highestPoint;
    private Label lowestPoint;
    private Label totalPoint;
    private final String HIGHEST_POINT = BundleUtils.getString("label.highest.point");
    private final String LOWEST_POINT = BundleUtils.getString("label.lowest.point");
    private final String TOTAL_POINT = BundleUtils.getString("label.total.point");
    private Map<String, String> mapCustStatus;

    public Home() {
        initHome();
    }

    private void initHome() {
        buidTabSheet();
        buildTabStatusQuantity();
        buildTabPointLayout();
    }

    private void buidTabSheet() {
        tabSheet = new TabSheet();
        tabSheet.setHeight(100.0f, Unit.PERCENTAGE);
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        mainLayout.addComponent(tabSheet);
    }

    private void buildTabStatusQuantity() {
        statusQuantityLayout = new VerticalLayout();
        buildStatisticTable();
        statusQuantityLayout.addComponent(tblStatistic);
        tabSheet.addTab(statusQuantityLayout, "Thống kê trạng thái");
    }

    private void buildTabPointLayout() {
        stasticPointLayout = new VerticalLayout();
        buildStatisticStaffPointTable();
        buildGridThongke();
//        setStatisticsData(lstStatisticStaffPoint);
//        stasticPointLayout.addComponent(tblStatistic);
        stasticPointLayout.addComponent(gridCaption);
        stasticPointLayout.setComponentAlignment(gridCaption, Alignment.MIDDLE_CENTER);
        stasticPointLayout.addComponent(tblPoints);
        staff = (StaffDTO) VaadinSession.getCurrent().getAttribute("staff");
        if (DataUtil.isAdmin(staff)) {
            addBtnUpload();
        }
        reloadTableAfterUpload();
        tabSheet.addTab(stasticPointLayout, "Thống kê điểm");
    }

    private void buildGridThongke() {
        String captionTable = "<h2 style='color: green;'>BẢNG THỐNG KÊ ĐIỂM THÁNG "
                + DateUtil.getMonth(new Date()) + " NĂM " + (2000 + DateUtil.getYY(new Date())) + "</h2>";
        Label caption = new Label(captionTable, ContentMode.HTML);
        caption.addStyleName("label-caption-center");
        highestPoint = new Label();
        highestPoint.setContentMode(ContentMode.HTML);
        lowestPoint = new Label();
        lowestPoint.setContentMode(ContentMode.HTML);
        totalPoint = new Label();
        totalPoint.setContentMode(ContentMode.HTML);

        gridCaption = new GridLayout(1, 4);
        gridCaption.setWidth("80%");
        gridCaption.addComponent(caption, 0, 0);
        gridCaption.setComponentAlignment(caption, Alignment.MIDDLE_CENTER);
        gridCaption.addComponent(highestPoint, 0, 1);
        gridCaption.addComponent(lowestPoint, 0, 2);
        gridCaption.addComponent(totalPoint, 0, 3);
        gridCaption.setComponentAlignment(totalPoint, Alignment.MIDDLE_RIGHT);
    }

    private void buildStatisticStaffPointTable() {
        tblPoints = new CommonTableFilterPanel();
        tblPoints.getToolbar().setVisible(false);
        beanContainer = new BeanItemContainer<>(StatisticStaffPointDTO.class);
        CommonFunctionTableFilter.initTable(tblPoints, HEADER, beanContainer, CAPTION, 10, "statisticStaffPoint");
        setStyleForTableColumn("totalPoint");
    }

    private void buildStatisticTable() {

        tblStatistic = new CommonTableFilterPanel();
        tblStatistic.getToolbar().setVisible(false);
        beanContainerStatistic = new BeanItemContainer<>(StatusQuantityDTO.class);
        tblStatistic.getMainTable().addGeneratedColumn("quantityLink", new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, Object itemId, Object columnId) {
                final StatusQuantityDTO s = (StatusQuantityDTO) itemId;
                Button btn = new Button(s.getQuantity());
                btn.addStyleName("v-button-link");
                btn.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
//                        Navigator navigator = UI.getCurrent().getNavigator();
//                        navigator.navigateTo("customerManagementOnePage.vt" + "/" + s.getStatus());
//                        addTabSheetStatus(s);
                        getUI().getPage().open("http://192.168.0.76:8080/CMS/#!customerManagementOnePage.vt"+ "/" + s.getStatus(), "_blank");
                    }

                });
                return btn;
            }
        });
        tblStatistic.getMainTable().addGeneratedColumn("quantityLinkS", new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, Object itemId, Object columnId) {
                final StatusQuantityDTO s = (StatusQuantityDTO) itemId;
                Button btn = new Button(s.getQuantity());
                btn.addStyleName("v-button-link");
                btn.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
//                        Navigator navigator = UI.getCurrent().getNavigator();
//                        navigator.navigateTo("customerManagementOnePage.vt" + "/" + s.getStatus());
                        addTabSheetStatus(s);
//                        getUI().getPage().open("http://27.118.26.11:8080/CMS/#!customerManagementOnePage.vt"+ "/" + s.getStatus(), "_blank");
                    }

                });
                return btn;
            }
        });
        CommonFunctionTableFilter.initTable(tblStatistic, HEADER_STATUS_QUANTITY, beanContainerStatistic,
                BundleUtils.getString("statistic.status.quantity.caption"), -1, "statisticStaffPoint");
        tblStatistic.getMainTable().setColumnWidth("statusName", 200);
        tblStatistic.getMainTable().setColumnWidth("quantityLink", 100);
        tblStatistic.getMainTable().setColumnWidth("quantityLinkS", 200);
    }

    public void fillDataToTable() {
        beanContainer.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstStatisticStaffPoint)) {
            beanContainer.addAll(lstStatisticStaffPoint);
        }
        CommonFunctionTableFilter.refreshTable(tblPoints, HEADER, beanContainer);

        beanContainerStatistic.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstStatusQuantity)) {
            beanContainerStatistic.addAll(lstStatusQuantity);
        }
        CommonFunctionTableFilter.refreshTable(tblStatistic, HEADER_STATUS_QUANTITY, beanContainerStatistic);
    }

    private void getListDatas() {
        try {
            lstCustomerStatus = DataUtil.getListApParams(Constants.APP_PARAMS.CUSTOMER_SERVICE_STATUS);
            mapCustStatus
                    = DataUtil.buildHasmap(lstCustomerStatus, Constants.APP_PARAMS.PAR_CODE,
                            Constants.APP_PARAMS.PAR_NAME);
            String staffCode = null;
            if (!DataUtil.isAdmin(staff)) {
                staffCode = staff.getCode();
            }
            lstStatusQuantity = WSCustomerStatus.getStatusQuantity(staffCode);
            if (!DataUtil.isListNullOrEmpty(lstStatusQuantity)) {
                for (StatusQuantityDTO s : lstStatusQuantity) {
                    s.setStatusName(mapCustStatus.get(s.getStatus()));
                }
            }
            lstStatisticStaffPoint = WSStatisticStaffPoint.getListStatisticStaffPointDTO(new StatisticStaffPointDTO(), 0, 100, "desc", "totalPoint");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setStatisticsData(List<StatisticStaffPointDTO> lstStatisticStaffPointDTOs) {
        if (!DataUtil.isListNullOrEmpty(lstStatisticStaffPoint)) {
            int size = lstStatisticStaffPointDTOs.size();
            if (size > 1) {
                StatisticStaffPointDTO highestStaff = lstStatisticStaffPointDTOs.get(0);
                StatisticStaffPointDTO lowestStaff = lstStatisticStaffPointDTOs.get(size - 1);
                Double dTotalPoint = sumTotalPoint(lstStatisticStaffPointDTOs);
                highestPoint.setValue(String.format(HIGHEST_POINT,
                        new Object[]{highestStaff.getStaffName(),
                            Double.parseDouble(highestStaff.getTotalPoint())}));
                lowestPoint.setValue(String.format(LOWEST_POINT,
                        new Object[]{lowestStaff.getStaffName(),
                            Double.parseDouble(lowestStaff.getTotalPoint())}));
                totalPoint.setValue(
                        String.format(TOTAL_POINT, new Object[]{dTotalPoint}));
            }
        }
    }

    public void reloadTableAfterUpload() {
        getListDatas();
        fillDataToTable();
        setStatisticsData(lstStatisticStaffPoint);
    }

    private Double sumTotalPoint(List<StatisticStaffPointDTO> lstStatisticStaffPointDTOs) {
        Double dTotalPoint = 0D;
        for (StatisticStaffPointDTO s : lstStatisticStaffPointDTOs) {
            dTotalPoint += Double.parseDouble(s.getTotalPoint());
        }
        return dTotalPoint;
    }

    private void addBtnUpload() {
        GridManyButton gridUpload = new GridManyButton(new String[]{Constants.BUTTON_UPLOAD});
        btnUpload = gridUpload.getBtnCommon().get(0);
        stasticPointLayout.addComponent(gridUpload);
        clickButtonUploadListener();
    }

    private void clickButtonUploadListener() {
        btnUpload.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                StatisticStaffPointUploadDialog dialog = new StatisticStaffPointUploadDialog();
                StatisticsStaffPointUploadController controller
                        = new StatisticsStaffPointUploadController(dialog, tblPoints);
                controller.initComponents();
                dialog.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent e) {
                        reloadTableAfterUpload();
                    }
                });
                UI.getCurrent().addWindow(dialog);
                CommonUtils.enableButtonAfterClick(event);
            }
        });
    }

    private void setStyleForTableColumn(final String column) {
        // Set cell style generator
        tblPoints.getMainTable().setCellStyleGenerator(new CustomTable.CellStyleGenerator() {
            @Override
            public String getStyle(CustomTable source, Object itemId, Object propertyId) {
                if (column.equals(propertyId)) {
                    return "red";
                } else {
                    return null;
                }
            }
        });
    }

    private void addTabSheetStatus(StatusQuantityDTO s) {
        if (!isTabHasExisted(s)) {
            VerticalLayout statusTab = buildStatusTab(s);
            tabSheet.addTab(statusTab, s.getStatusName());
            tabSheet.setSelectedTab(statusTab);
        }
    }

    private boolean isTabHasExisted(StatusQuantityDTO s) {
        Iterator<Component> i = tabSheet.iterator();
        while (i.hasNext()) {
            Component c = (Component) i.next();
            Tab tab = tabSheet.getTab(c);
            if (s.getStatusName().equals(tab.getCaption())) {
                tabSheet.setSelectedTab(c);
                return true;
            }
        }
        return false;
    }

    private VerticalLayout buildStatusTab(StatusQuantityDTO s) {
        VerticalLayout statusLayout = new VerticalLayout();
        statusLayout.setWidth("100%");
        statusLayout.setHeight("100%");
        statusLayout.setImmediate(true);

        SearchCustomerForm searchCustomerForm;
        CommonTableFilterPanel tblPanelCustomers;
        searchCustomerForm = new SearchCustomerForm();
        statusLayout.addComponent(searchCustomerForm);
        tblPanelCustomers = new CommonTableFilterPanel();
        tblPanelCustomers.getToolbar().setVisible(false);
        statusLayout.addComponent(tblPanelCustomers);

        CustomerManagementOnePageController c = 
                new CustomerManagementOnePageController(searchCustomerForm, tblPanelCustomers);
        c.initController();
        c.doSearch(s.getStatus());
        return statusLayout;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
