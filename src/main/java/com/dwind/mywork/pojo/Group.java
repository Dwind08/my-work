package com.dwind.mywork.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Group {
    private List<Customer> customers;
    private List<SysUser> users;
    private String address;
    private String customerName;
}
