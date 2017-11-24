/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vfw5.base.dto;

import com.cms.dto.StockGoodsSerialInforDTO;
import java.util.List;

/**
 *
 * @author thienng1
 */
public class ExtendResultDTO extends ResultDTO {

    private List<StockGoodsSerialInforDTO> lstStockGoodsSerialInforDTO;

    public ExtendResultDTO() {
        super();
    }

    public List<StockGoodsSerialInforDTO> getLstStockGoodsSerialInforDTO() {
        return lstStockGoodsSerialInforDTO;
    }

    public void setLstStockGoodsSerialInforDTO(List<StockGoodsSerialInforDTO> lstStockGoodsSerialInforDTO) {
        this.lstStockGoodsSerialInforDTO = lstStockGoodsSerialInforDTO;
    }

}
