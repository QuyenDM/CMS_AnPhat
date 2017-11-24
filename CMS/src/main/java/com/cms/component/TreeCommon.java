/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.component;

import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.cms.common.basedto.TreeNote;
import com.cms.utils.BuildTreeModel;
import com.cms.utils.DataUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author truongbx3 createdate 18/04/2015
 */
public class TreeCommon extends CustomComponent {

    private Set<TreeNote> checked;
    private HashMap<String, String> lstDefaulNote;
    private List<?> lstTreeInput = new ArrayList<>();
    public boolean viewRoot = true;
    private BuildTreeModel treeModel;
    public Tree tree;
    Class objClass;
    public Set<TreeNote> checkedParent;
    HierarchicalContainer createTreeContentDemo;
    VerticalLayout layout = new VerticalLayout();

    public TreeCommon(String treeName) {

        setCompositionRoot(layout);
    }

    public TreeCommon() {

        setCompositionRoot(layout);
    }

    public void setDataSource(String treeName, Object rootTree, List<?> lstTree, boolean viewRoot, String name, String code, String parent) {
        setDataSource(treeName, rootTree, lstTree, null, viewRoot, name, code, parent);

    }

    public void setDataSource(String treeName, Object rootTree, List<?> lstTree, String name, String code, String parent) {
        setDataSource(treeName, rootTree, lstTree, null, true, name, code, parent);
    }

    public void setDataSource(String treeName, Object rootTree, List<?> lstTree, List<String> lstDefaul, boolean viewRoot, String name, String code, String parent) {
        if (!DataUtil.isStringNullOrEmpty(treeName)) {
            tree = new Tree(treeName);
        } else {
            tree = new Tree();
        }

        layout.removeAllComponents();
        tree.addStyleName("checkboxed");
//        tree.setImmediate(true);
        // Only allow clicks
        tree.setSelectable(false);
        layout.addComponent(tree);
        tree.setItemCaptionMode(AbstractSelect.ITEM_CAPTION_MODE_PROPERTY);
        tree.setItemCaptionPropertyId("caption");
        checkedParent = new HashSet<>();
        checked = new HashSet<>();
        lstDefaulNote = new HashMap<>();
        lstTreeInput = lstTree;
        createTreeContentDemo = new HierarchicalContainer();

        this.viewRoot = viewRoot;
        convertListDefaultToHasmap(lstDefaul);
        TreeNote treenote = new TreeNote();
        treeModel = new BuildTreeModel(name, code, parent);
        try {
            treeModel.setRootForTree(rootTree);
            treenote = treeModel.setContainerDataSource(lstTree);
        } catch (NoSuchMethodException ex) {
            System.out.println(" set container has errors");
        }

        setValueTree(treenote);
        markParentDefault();
        for (Object item : tree.getItemIds()) {
            tree.expandItem(item);
        }
    }

    private void setValueTree(TreeNote lstObject) {

        createTreeContentDemo = createTreeContent(lstObject);
        tree.setContainerDataSource(createTreeContentDemo);
        tree.getItem(this);
        tree.addListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
//                kiem tra cac note con note nao duoc check hay khong
//                boolean isNoteCheck = true;
                Collection<?> treeChild = null;
                treeChild = tree.getChildren(event.getItemId());
                TreeNote eventObject = (TreeNote) event.getItemId();
                iteratorTree(treeChild, eventObject, tree, false);

                tree.markAsDirty();

            }
        }
        );

        final Tree.ItemStyleGenerator gen = new Tree.ItemStyleGenerator() {
            private static final long serialVersionUID = -7016120138582433243L;

            @Override
            public String getStyle(Tree source, Object itemId) {
                if (checked.contains(itemId) || checkedParent.contains(itemId)) {
                    return "checked";
                } else {
                    return "unchecked";
                }
            }
        };

        tree.setItemStyleGenerator(gen);

    }

    /*
     iter: cac lan de quy se khong check note con
     cac note con phai duoc xoa hoac tick theo note cha
     */
    private void iteratorTree(Collection<?> treeChild, TreeNote event, Tree tree, boolean isfirst) {
        //                kiem tra cac note con note nao duoc check hay khong

//         lần đầu;
        boolean isNoteCheck = isfirst;
        if ((treeChild == null) && checked.contains(event)) {
            checked.remove(event);
            removeParent(event, tree);
        } else if ((treeChild == null) && !checked.contains(event)) {
            checked.add(event);
            markParent(event, tree);
        } else {
            isNoteCheck = checkChild(event, tree);

//                    clear
            if (!isNoteCheck) {

                tree.collapseItemsRecursively(event);
                checkedParent.remove(event);
                removeParent(event, tree);
                removeAllChild(tree, event);
            }
//                    mark
            if (isNoteCheck) {
                markParent(event, tree);
                tree.expandItemsRecursively(event);
                checkedParent.add(event);
                markAllChild(tree, event);
            }

        }

    }

    private HierarchicalContainer createTreeContent(TreeNote inventory) {

        HierarchicalContainer container = new HierarchicalContainer();
        // A property that holds the caption is needed for ITEM_CAPTION_MODE_PROPERTY
        container.addContainerProperty("caption", String.class, "");
        tree.expandItemsRecursively(inventory);
//        tree.expandItem(inventory);
//        view root
        if (viewRoot) {
            container.addItem(inventory);
            container.getItem(inventory).getItemProperty("caption").setValue(inventory.getName());
        }
        putData(inventory.getLstChild(), inventory, container);
        return container;
    }

    private void putData(List<TreeNote> data, TreeNote parent, HierarchicalContainer container) {
        for (TreeNote item : data) {
//           find note default 
            findTreeNoteDefault(item);
            // Support both ITEM_CAPTION_MODE_ID and ITEM_CAPTION_MODE_PROPERTY
//            container.addContainerProperty("caption", String.class, "");
            container.addItem(item);
            container.getItem(item).getItemProperty("caption").setValue(item.getName());
            container.getItem(item);
            container.setParent(item, parent);
            if (item.getLstChild().size() > 0) {
                putData(item.getLstChild(), item, container);
            } else {
                container.setChildrenAllowed(item, false);
            }

        }
    }
// exit note not check return true

    public boolean checkChild(TreeNote event, Tree tree) {

        boolean check = false;
        Collection<?> treeChildCh = null;
        treeChildCh = tree.getChildren(event);
        for (Iterator<?> it = treeChildCh.iterator(); it.hasNext();) {
            TreeNote obj = new TreeNote();
            obj = (TreeNote) it.next();
            if (!checked.contains(obj) && !checkedParent.contains(obj)) {
                check = true;
                return check;
            }
            if (tree.getChildren(obj) != null) {
                check = checkChild(obj, tree);
                if (check) {
                    return check;
                }
            }

        }
        return check;
    }
// check mark
//neu khong co anh em nao dk danh dau tra ve true

    public boolean checkMarkParent(TreeNote event, Tree tree) {

        boolean check = true;
//        get all sibling ob note;

        Collection<?> sibling = null;
        sibling = tree.getChildren(event);
        if (sibling == null) {
            return true;
        }
        for (Iterator<?> it = sibling.iterator(); it.hasNext();) {
            TreeNote obj = new TreeNote();
            obj = (TreeNote) it.next();
            if (checked.contains(obj) || checkedParent.contains(obj)) {
                check = false;
                return check;
            }
        }
        return check;
    }

    private void markParent(TreeNote event, Tree tree) {
        if (hasParent(event, tree)) {
            TreeNote parent = new TreeNote();
            parent = (TreeNote) tree.getParent(event);
            if (!checkedParent.contains(parent)) {
                checkedParent.add(parent);
            }

            markParent(parent, tree);

        }

    }

    private void removeParent(TreeNote event, Tree tree) {
        if (hasParent(event, tree)) {
            TreeNote parent = new TreeNote();
            parent = (TreeNote) tree.getParent(event);
            if (checkedParent.contains(parent) && checkMarkParent(parent, tree)) {
                checkedParent.remove(parent);
            }

            removeParent(parent, tree);

        }
    }

    private boolean hasParent(Object child, Tree tree) {
        Object parent = new Object();
        parent = tree.getParent(child);
        if (parent == null) {
            return false;
        }
        return true;
    }

//
    private void removeAllChild(Tree tree, TreeNote event) {
        Collection<?> treeChildCh = null;
        treeChildCh = tree.getChildren(event);
        if (treeChildCh == null) {
            return;
        }
        for (Iterator<?> it = treeChildCh.iterator(); it.hasNext();) {
            TreeNote obj = (TreeNote) it.next();
            checked.remove(obj);
            checkedParent.remove(obj);
            removeAllChild(tree, obj);
//
        }
    }

    private void markAllChild(Tree tree, TreeNote event) {
        Collection<?> treeChildCh = null;
        treeChildCh = tree.getChildren(event);
        if (treeChildCh == null) {
            return;
        }
        for (Iterator<?> it = treeChildCh.iterator(); it.hasNext();) {
            TreeNote obj = (TreeNote) it.next();
            if (obj.getLstChild().size() == 0) {
                checked.add(obj);
            } else {
                checkedParent.add(obj);
                markAllChild(tree, obj);
            }
        }
    }

    private HierarchicalContainer getCreateTreeContentDemo() {
        return createTreeContentDemo;
    }

    private void setCreateTreeContentDemo(HierarchicalContainer createTreeContentDemo) {
        this.createTreeContentDemo = createTreeContentDemo;
    }

    public Set<TreeNote> getChecked() {
        return checked;
    }

    private void setChecked(Set<TreeNote> checked) {
        this.checked = checked;
    }

    public Set<TreeNote> getCheckedParent() {
        return checkedParent;
    }

    private void setCheckedParent(Set<TreeNote> checkedParent) {
        this.checkedParent = checkedParent;
    }

    private void findTreeNoteDefault(TreeNote treenote) {

//        not mark parent note
        if (treenote.getLstChild().size() != 0) {
            return;
        }
        if (lstDefaulNote.get(treenote.getId()) != null) {
            checked.add(treenote);
        }

    }

    private void convertListDefaultToHasmap(List<String> lstDefaul) {
        if (lstDefaul == null || lstDefaul.size() == 0) {
            return;
        }
        for (String item : lstDefaul) {
            lstDefaulNote.put(item, "1");
        }

    }

    private void markParentDefault() {
        for (TreeNote item : checked) {
            markParent(item, tree);
            tree.expandItem(item);
        }
        for (TreeNote item : checkedParent) {
            tree.expandItem(item);
        }
    }

    public List<?> getValue() {
        return treeModel.getResultSet((List<Object>) lstTreeInput, checked);
    }

    public List<?> getAllValues() {
        List<Object> lstReturns = treeModel.getResultSet((List<Object>) lstTreeInput, checked);
        lstReturns.addAll(treeModel.getResultSet((List<Object>) lstTreeInput, checkedParent));
        return lstReturns;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public void setTreeEnabled(boolean isEnable) {
        tree.setEnabled(isEnable);
    }
}
