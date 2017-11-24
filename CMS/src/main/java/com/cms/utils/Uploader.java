/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.MouseEvents;
import com.vaadin.server.FileResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Image;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import com.wcs.wcslib.vaadin.widget.multifileupload.ui.MultiFileUpload;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.UploadFinishedHandler;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.UploadStateWindow;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 *
 * Khai bao: Uploader uploader = new Uploader();
 *
 * Doc cac url: uploader.getUrl();
 *
 * @author dungbv7
 */
public class Uploader extends CustomComponent {

    MultiFileUpload multiUpload;
    String folder = "VAADIN/UPLOAD";
    String prefixName = "";
    final VerticalLayout layout = new VerticalLayout();
    Table table = new Table();
    BeanItemContainer<String> containerLink;
    final ArrayList<Link> links = new ArrayList<>();
    ArrayList<String> url = new ArrayList<>();
    String path = BundleUtils.getStringCas("path_import_order");
    List<File> lstFile = new ArrayList<>();

    public Uploader() {
        table.setImmediate(true);
//        table.setWidth("100%");
        table.setVisible(false);
        table.setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);
        table.addContainerProperty("File", String.class, null);
        containerLink = new BeanItemContainer<>(String.class);
        table.addGeneratedColumn("", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(Table source, final Object itemId, Object columnId) {
                ThemeResource iconVi = new ThemeResource("img/icon_delete.png");
                Image image = new Image(null, iconVi);
                image.addClickListener(new MouseEvents.ClickListener() {
                    @Override
                    public void click(MouseEvents.ClickEvent event) {
                        Item row1 = table.getItem(itemId);
                        String fileName = (String) row1.getItemProperty("File").getValue();
                        url.remove(prefixName + fileName);
                        table.removeItem(itemId);
                        table.setPageLength(table.getItemIds().size());
                        table.refreshRowCache();
                        Collection coll = table.getItemIds();
                        if (coll == null || coll.isEmpty()) {
                            table.setVisible(false);
                        }
                    }
                });
                return image;
            }
        });
        buildLayout();
        addUpload();
    }

    private void buildLayout() {
        setCompositionRoot(layout);
    }

    public MultiFileUpload getMultiUpload() {
        return multiUpload;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public ArrayList<Link> getLinks() {
        return links;
    }

    public ArrayList<String> getUrl() {
        return url;
    }

    private void addUpload() {
        UploadStateWindow window = new UploadStateWindow();
//        final String path = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();

        UploadFinishedHandler handler = new UploadFinishedHandler() {
            @Override
            public void handleFile(InputStream in, String fileName, String mimeType, long l) {
                try {
                    final String saltPath = buildPath();
                    File dir = new File(path);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    File file = new File(path + prefixName + fileName);
                    try (FileOutputStream fos = new FileOutputStream(file)) {
                        fos.write(IOUtils.toByteArray(in));
                        fos.close();
                    }

                    Link link = new Link(fileName, new FileResource(file));
//                    layout.addComponent(link);
                    links.add(link);
                    if (!url.contains(prefixName + fileName)) {
                        url.add(prefixName + fileName);
                        // Add a row the hard way
                        table.setVisible(true);
                        Object newItemId = table.addItem();
                        Item row1 = table.getItem(newItemId);
                        row1.getItemProperty("File").setValue(fileName);
                        table.setPageLength(table.getItemIds().size());
                    } else {
                        Notification.show(BundleUtils.getString("fileExist") + fileName, Notification.Type.TRAY_NOTIFICATION);
                    }

//                    table.addItem(new Object[]{fileName});
                } catch (IOException ex) {
                }
            }
        };
        multiUpload = new MultiFileUpload(handler, window);
//        multiUpload.setCaption("Multile upload");
//        multiUpload.setPanelCaption("Upload panel");
//        multiUpload.setAcceptedMimeTypes(DataUtil.getListAcceptFile());
        multiUpload.setMaxFileSize(2048000);
        multiUpload.getSmartUpload().setUploadButtonCaptions(BundleUtils.getString("chooseAttachFile"), BundleUtils.getString("chooseAttachFile"));
        layout.addComponent(multiUpload);
        layout.addComponent(table);

    }

    // get List file save db 
    public String getLstFileSave() {
        String strLstFileName = "";
        Collection<?> itemIDS = table.getItemIds();
        if (!itemIDS.isEmpty()) {
            for (Object itemID : itemIDS) {
                Property property = table.getContainerProperty(itemID, "File");
                Object data = property.getValue();
                strLstFileName = strLstFileName + prefixName + data.toString() + ";";
            }
            strLstFileName = strLstFileName.substring(0, strLstFileName.length() - 1);
        }
        return strLstFileName;
    }

    public String buildPath() {
        return RandomStringUtils.randomAlphabetic(15)
                + "/" + RandomStringUtils.randomAlphabetic(15)
                + "/" + RandomStringUtils.randomAlphabetic(15);
    }

    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
}
