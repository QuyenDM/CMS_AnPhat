/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.component;

import com.jensjansson.pagedtable.PagedTableContainer;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Reindeer;
import com.cms.utils.BundleUtils;
import com.cms.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thienng1 nodyfy by truongbx3 20/04/2015
 */
public class CustomPageTable extends Table {

    private static final long serialVersionUID = 6881455780158545828L;
    public NativeSelect itemsPerPageSelect = new NativeSelect();
    public Button previous;
    Label separatorTotal = new Label("", ContentMode.HTML);
    boolean resizePage = true;
    private String pageSizeDefault;

    public interface PageChangeListener {

        public void pageChanged(PagedTableChangeEvent event);
    }

    public class PagedTableChangeEvent {

        final CustomPageTable table;

        public PagedTableChangeEvent(CustomPageTable table) {
            this.table = table;
        }

        public CustomPageTable getTable() {
            return table;
        }

        public int getCurrentPage() {
            return table.getCurrentPage();
        }

        public int getTotalAmountOfPages() {
            return table.getTotalAmountOfPages();
        }
    }

    private List<PageChangeListener> listeners = null;

    private PagedTableContainer container;

    public CustomPageTable() {
        this(null);
    }

    public CustomPageTable(String caption) {
        super(caption);
        setPageLength(25);
        addStyleName("pagedtable");
    }

    public HorizontalLayout createControls(String pageSizeDefault) {
        this.pageSizeDefault = pageSizeDefault;
        separatorTotal.setImmediate(true);
        Label itemsPerPageLabel = new Label(BundleUtils.getString("common.table.numberRecord"));

        for (String numberSize : Constants.PAGE_SIZE_LIST) {
            itemsPerPageSelect.addItem(numberSize);
        }
        itemsPerPageSelect.setImmediate(true);
        itemsPerPageSelect.setNullSelectionAllowed(false);
        itemsPerPageSelect.setWidth("50px");
        itemsPerPageSelect.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = -2255853716069800092L;

            @Override
            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                setPageLength(Integer.valueOf(String.valueOf(event
                        .getProperty().getValue())));
                firePagedChangedEvent();
            }
        });
        itemsPerPageSelect.select(pageSizeDefault);
        Label pageLabel = new Label(BundleUtils.getString("common.table.page"), ContentMode.HTML);
        final TextField currentPageTextField = new TextField();
        currentPageTextField.setValue(String.valueOf(getCurrentPage()));
        currentPageTextField.setConverter(Integer.class);
        currentPageTextField.addValidator(new IntegerRangeValidator("Wrong page number", 1, getTotalAmountOfPages()));
        Label separatorLabel = new Label("&nbsp;/&nbsp;", ContentMode.HTML);
        final Label totalPagesLabel = new Label(
                String.valueOf(getTotalAmountOfPages()), ContentMode.HTML);
        currentPageTextField.setStyleName(Reindeer.TEXTFIELD_SMALL);
        currentPageTextField.setImmediate(true);
        currentPageTextField.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = -2255853716069800092L;

            @Override
            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                currentPageTextField.removeAllValidators();
                currentPageTextField.addValidator(new IntegerRangeValidator("Wrong page number", 1, getTotalAmountOfPages()));
                if (Integer.valueOf(String
                        .valueOf(currentPageTextField.getValue())) <= getTotalAmountOfPages()) {
                    int page = Integer.valueOf(String
                            .valueOf(currentPageTextField.getValue()));
                    setCurrentPage(page);
                }
            }
        });
        pageLabel.setWidth(null);
        currentPageTextField.setWidth("30px");
        separatorLabel.setWidth(null);
        totalPagesLabel.setWidth(null);

        HorizontalLayout controlBar = new HorizontalLayout();
        HorizontalLayout pageSize = new HorizontalLayout();
        HorizontalLayout pageManagement = new HorizontalLayout();
        final Button first = new Button("<<", new Button.ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                setCurrentPage(0);
            }
        });
        previous = new Button("<", new Button.ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                previousPage();
            }
        });
        final Button next = new Button(">", new Button.ClickListener() {
            private static final long serialVersionUID = -1927138212640638452L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                nextPage();
            }
        });
        final Button last = new Button(">>", new Button.ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                setCurrentPage(getTotalAmountOfPages());
            }
        });
        first.setStyleName(Reindeer.BUTTON_LINK);
        previous.setStyleName(Reindeer.BUTTON_LINK);
        next.setStyleName(Reindeer.BUTTON_LINK);
        last.setStyleName(Reindeer.BUTTON_LINK);

        itemsPerPageLabel.addStyleName("pagedtable-itemsperpagecaption");
        itemsPerPageSelect.addStyleName("pagedtable-itemsperpagecombobox");
        pageLabel.addStyleName("pagedtable-pagecaption");
        currentPageTextField.addStyleName("pagedtable-pagefield");
        separatorLabel.addStyleName("pagedtable-separator");
        separatorTotal.addStyleName("pagedtable-separator");
        totalPagesLabel.addStyleName("pagedtable-total");
        first.addStyleName("pagedtable-first");
        previous.addStyleName("pagedtable-previous");
        next.addStyleName("pagedtable-next");
        last.addStyleName("pagedtable-last");

        itemsPerPageLabel.addStyleName("pagedtable-label");
        itemsPerPageSelect.addStyleName("pagedtable-combobox");
        pageLabel.addStyleName("pagedtable-label");
        currentPageTextField.addStyleName("pagedtable-label");
        separatorLabel.addStyleName("pagedtable-label");
        separatorTotal.addStyleName("pagedtable-label");
        totalPagesLabel.addStyleName("pagedtable-label");
        first.addStyleName("pagedtable-button");
        previous.addStyleName("pagedtable-button");
        next.addStyleName("pagedtable-button");
        last.addStyleName("pagedtable-button");

        pageSize.addComponent(itemsPerPageLabel);
        pageSize.addComponent(itemsPerPageSelect);
        Label separator = new Label("&nbsp;/&nbsp;", ContentMode.HTML);
        pageSize.addComponent(separator);
        pageSize.addComponent(separatorTotal);
        pageSize.setComponentAlignment(itemsPerPageLabel, Alignment.MIDDLE_LEFT);
        pageSize.setComponentAlignment(itemsPerPageSelect,
                Alignment.MIDDLE_LEFT);
        pageSize.setComponentAlignment(separator, Alignment.MIDDLE_LEFT);
        pageSize.setComponentAlignment(separatorTotal, Alignment.MIDDLE_LEFT);
        pageSize.setSpacing(true);
        pageManagement.addComponent(first);
        pageManagement.addComponent(previous);
        pageManagement.addComponent(pageLabel);
        pageManagement.addComponent(currentPageTextField);
        pageManagement.addComponent(separatorLabel);
        pageManagement.addComponent(totalPagesLabel);
        pageManagement.addComponent(next);
        pageManagement.addComponent(last);
        pageManagement.setComponentAlignment(first, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(previous, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(pageLabel, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(currentPageTextField,
                Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(separatorLabel,
                Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(totalPagesLabel,
                Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(next, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(last, Alignment.MIDDLE_LEFT);
        pageManagement.setWidth(null);
        pageManagement.setSpacing(true);
        controlBar.addComponent(pageSize);
        controlBar.addComponent(pageManagement);
        controlBar.setComponentAlignment(pageManagement,
                Alignment.MIDDLE_CENTER);
        controlBar.setWidth("100%");
        controlBar.setExpandRatio(pageSize, 1);
        addListener(new PageChangeListener() {
            @Override
            public void pageChanged(PagedTableChangeEvent event) {
                int abc = event.getCurrentPage();
                first.setEnabled(container.getStartIndex() > 0);
                previous.setEnabled(container.getStartIndex() > 0);
                next.setEnabled(container.getStartIndex() < container
                        .getRealSize() - getPageLength());
                last.setEnabled(container.getStartIndex() < container
                        .getRealSize() - getPageLength());
                currentPageTextField.setValue(String.valueOf(getCurrentPage()));
                totalPagesLabel.setValue(String.valueOf(getTotalAmountOfPages()));
                if (resizePage) {
                    int lenght = getPageLength();
                    int a = lenght % 5;
                    int b = lenght / 5;
                    if (a != 0) {
                        int pageLenght = 5 * (b + 1);
                        itemsPerPageSelect.setValue(pageLenght);
                    }
                }
                separatorTotal.setValue(String.valueOf(container.getRealSize()));
            }
        });
        return controlBar;
    }

    @Override
    public Container.Indexed getContainerDataSource() {
        return container;
    }

    @Override
    public void setContainerDataSource(Container newDataSource) {
        if (!(newDataSource instanceof Container.Indexed)
                || !(newDataSource instanceof Container.Filterable)) {
            throw new IllegalArgumentException(
                    "PagedFilteringTable can only use containers that implement Container.Indexed AND Container.Filterable");
        }
        PagedTableContainer pagedTableContainer = new PagedTableContainer((Indexed) newDataSource);
        container = pagedTableContainer;
        if (newDataSource.size() < getPageLength()) {
            setPageLength(newDataSource.size() == 0 ? 3 : newDataSource.size());
            resizePage = false;
        } else if (pageSizeDefault != null && newDataSource.size() <= Integer.parseInt(pageSizeDefault)) {
            setPageLength(newDataSource.size());
            resizePage = false;
        } else if (pageSizeDefault != null && newDataSource.size() > Integer.parseInt(pageSizeDefault)) {
            setPageLength(Integer.parseInt(pageSizeDefault));
            resizePage = true;
        } else {
            int le = getPageLength();
            if (le != 0) {
                setPageLength(le);
            } else {
                setPageLength(5);
            }
            resizePage = true;
        }
        if (separatorTotal != null && container != null) {
            separatorTotal.setValue(String.valueOf(container.getRealSize()));
        }

        super.setContainerDataSource(pagedTableContainer);
        firePagedChangedEvent();
    }
// set index first of page
    // gia tri truyen vao la index dau tien cua page

    private void setPageFirstIndex(int firstIndex) {
        if (container != null) {
            if (firstIndex <= 0) {
                firstIndex = 0;
            }
            if (firstIndex > container.getRealSize() - 1) {
                int size = container.getRealSize() - 1;
                int pages = 0;
                if (getPageLength() != 0) {
                    pages = (int) Math.floor(0.0 + size / getPageLength());
                }
                firstIndex = pages * getPageLength();
            }
            container.setStartIndex(firstIndex);
            setCurrentPageFirstItemIndex(firstIndex);
            containerItemSetChange(new Container.ItemSetChangeEvent() {
                private static final long serialVersionUID = -5083660879306951876L;

                @Override
                public Container getContainer() {
                    return container;
                }
            });
            if (alwaysRecalculateColumnWidths) {
                for (Object columnId : container.getContainerPropertyIds()) {
                    setColumnWidth(columnId, -1);
                }
            }
            firePagedChangedEvent();
        }
    }

    private void firePagedChangedEvent() {
        if (listeners != null) {
            PagedTableChangeEvent event = new PagedTableChangeEvent(this);
            for (PageChangeListener listener : listeners) {
                listener.pageChanged(event);
            }
        }
    }

    public void resetPage() {
        resizePage = true;
        firePagedChangedEvent();
    }

    @Override
    public void setPageLength(int pageLength) {
        if (pageLength >= 0) {
            int countData = container.getContainer().size() - container.getStartIndex();
            if (countData < pageLength) {
                pageLength = (countData == 0) ? 1 : countData;
            }

            container.setPageLength(pageLength);
            super.setPageLength(pageLength);

        }
    }

    public void nextPage() {
        setPageFirstIndex(container.getStartIndex() + getPageLength());
//        container.removeAllItems();

    }
// trang truoc bang starindex

    public void previousPage() {
        setPageFirstIndex(container.getStartIndex() - getPageLength());
    }

    public int getCurrentPage() {
        double pageLength = getPageLength();
        int page = (int) Math.floor((double) container.getStartIndex()
                / pageLength) + 1;
        if (page < 1) {
            page = 1;
        }
        return page;
    }

    public void setCurrentPage(int page) {
        int newIndex = (page - 1) * getPageLength();
        if (newIndex < 0) {
            newIndex = 0;
        }
        if (newIndex >= 0 && newIndex != container.getStartIndex()) {
            setPageFirstIndex(newIndex);
        }
    }

    public int getTotalAmountOfPages() {
        int size = container.getContainer().size();
//        double pageLength = getPageLength();
        Double pageLength = Double.parseDouble(itemsPerPageSelect.getValue().toString());
        String pagesize = itemsPerPageSelect.getValue().toString();
        if (pagesize == null) {
            pagesize = "1";
        }
//        setPageLength(Integer.parseInt(pagesize));
        int pageCount = (int) Math.ceil(size / pageLength);
        if (pageCount < 1) {
            pageCount = 1;
        }
        return pageCount;
    }

    public void addListener(PageChangeListener listener) {
        if (listeners == null) {
            listeners = new ArrayList<PageChangeListener>();
        }
        listeners.add(listener);
    }

    public void removeListener(PageChangeListener listener) {
        if (listeners == null) {
            listeners = new ArrayList<PageChangeListener>();
        }
        listeners.remove(listener);
    }

    public void setAlwaysRecalculateColumnWidths(
            boolean alwaysRecalculateColumnWidths) {
        this.alwaysRecalculateColumnWidths = alwaysRecalculateColumnWidths;
    }

}
