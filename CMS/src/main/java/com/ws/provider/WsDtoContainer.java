package com.ws.provider;

//import com.viettel.gnoc.incident.dto.BaseDTO;

import com.cms.common.basedto.BaseDTO;
import java.util.List;

/**
 * Created by thiendn1 on 4/1/2015.
 */
public class WsDtoContainer {
    private List<BaseDTO> list;

    public List<BaseDTO> getList() {
        return list;
    }

    public void setList(List<BaseDTO> list) {
        this.list = list;
    }
}
