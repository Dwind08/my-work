package com.dwind.mywork.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CarVehicleCode implements Serializable {
    /**
     * 输入车工号
     */
    String SERNR;

}
