/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;


import com.cms.common.basedto.TreeNote;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TiepNV6
 */
public class BuildTreeModel {

    String name;
    String code;
    String parent;
    public TreeNote treenote = new TreeNote();

    public BuildTreeModel( String name, String code, String parent) {
        this.name = name;
        this.code = code;
        this.parent = parent;
    }

    public void setRootForTree(Object object) throws NoSuchMethodException {
        HashMap<String, String> hasmap = getValueFromObject(object);
        treenote = new TreeNote( hasmap.get("code"),hasmap.get("name"));
    }

    public TreeNote setContainerDataSource(List<?> demo) throws NoSuchMethodException {

        treenote.setLstChild(getTreeModelFromObject(treenote.getId(), demo));
        return treenote;
    }

    public List<TreeNote> getTreeModelFromObject(String parentCode, List<?> demo) throws NoSuchMethodException {
        List<TreeNote> lstTree = new ArrayList<>();
        for (Object demo1 : demo) {
            HashMap<String, String> hasmap = getValueFromObject(demo1);
            if (parentCode.equalsIgnoreCase(hasmap.get("parent"))) {
                lstTree.add(new TreeNote(hasmap.get("code"),hasmap.get("name"), getTreeModelFromObject(hasmap.get("code"), demo)));
            }
        }
        return lstTree;
    }

    public HashMap<String, String> getValueFromObject(Object obj) throws NoSuchMethodException {
        HashMap<String, String> hmap = new HashMap<>();

        try {
            Class<?> c = obj.getClass();
            Method mdGetName = c.getMethod(DataUtil.getGetterOfColumn(name));
            Method mdGetCode = c.getMethod(DataUtil.getGetterOfColumn(code));
            Method mdGetParent = c.getMethod(DataUtil.getGetterOfColumn(parent));
            hmap.put("name", (String) mdGetName.invoke(obj));
            hmap.put("code", (String) mdGetCode.invoke(obj));
            hmap.put("parent", (String) mdGetParent.invoke(obj));

        } catch (IllegalAccessException ex) {
            Logger.getLogger(BuildTreeModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(BuildTreeModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(BuildTreeModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hmap;
    }
     public String getCodeObject(Object obj) throws NoSuchMethodException {
        
         String codeValue = "";

        try {
            Class<?> c = obj.getClass();
            Method mdGetCode = c.getMethod(DataUtil.getGetterOfColumn(code));
            codeValue = (String) mdGetCode.invoke(obj);
            return codeValue;

        } catch (IllegalAccessException ex) {
            Logger.getLogger(BuildTreeModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(BuildTreeModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(BuildTreeModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return codeValue;
    }
    
    public List<Object> getResultSet(List<Object> lstData , Set<TreeNote> setTree){
        if(setTree == null ||setTree.isEmpty() ){
            return null;
        }
        
        
       List<Object> lstObject = new ArrayList<>();
        HashMap<String , String>  lstHashmap = new HashMap<>();
        lstHashmap = convertTreeNoteToHashmap(setTree);
        for (Object item : lstData) {
           try {
               if (lstHashmap.get(getCodeObject(item)) != null) {
                   lstObject.add(item);
               }
           } catch (NoSuchMethodException ex) {
               Logger.getLogger(BuildTreeModel.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
       return lstObject;
    }
     
    public HashMap<String , String> convertTreeNoteToHashmap(Set<TreeNote> setTree){
      HashMap<String,String> hmReturn = new HashMap<>();
        for (TreeNote item : setTree) {
            hmReturn.put(item.getId(), item.getName());
        }
      
      return hmReturn;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

}
