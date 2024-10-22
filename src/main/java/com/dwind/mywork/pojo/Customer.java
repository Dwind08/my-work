package com.dwind.mywork.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    private String customerName;

    private String tycCompanyId;

    public String getCustomerName() {
        return customerName;
    }

    public Customer setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public String getTycCompanyId() {
        return tycCompanyId;
    }

    public Customer setTycCompanyId(String tycCompanyId) {
        this.tycCompanyId = tycCompanyId;
        return this;
    }
}
