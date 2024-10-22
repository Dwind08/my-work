package com.dwind.mywork.service;

import com.dwind.mywork.pojo.Customer;

public class CodeFormat {


    public static void main(String[] args) {
        String customName = "zhangsan";
        String idNo = "123456";
//        Customer customerInfo = new Customer();
        Customer customerInfo = null;
//        customerInfo.setTycCompanyId("12354");

        String url = "court/orgentout?name=" + customName + "&idNo=" + idNo + "&id=" +
                (customerInfo != null ? customerInfo.getTycCompanyId() : idNo);

        String tycId = customerInfo != null ? customerInfo.getTycCompanyId() : idNo;

        String url2 = "court/orgentout?name=" + customName + "&idNo=" + idNo + "&id=" + tycId;

        System.out.println(url);
        System.out.println(url2);
    }


}
