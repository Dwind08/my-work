package com.dwind.mywork.pojo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class TransactionDetailsForEmailTest {

    @Test
    void getBusFlag() {

    }

    @Test
    void testToString() {
        TransactionDetailsForEmail transactionDetailsForEmail = new TransactionDetailsForEmail();
        transactionDetailsForEmail.setUnMatchedFlowSize("12");
        transactionDetailsForEmail.setMatchedFlowSize("324078");

        log.info("未匹配流水&未核销导出信息: {}", transactionDetailsForEmail);
        System.out.println(transactionDetailsForEmail);
    }

    @Test
    void testToString1() {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(DateUtil.parse("2024-02-15 15:23:23", "yyyy-MM-dd HH:mm:ss"));
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(new Date());
        List<Date> datesBetweenTwoDates = getDatesBetweenTwoDates(startCal, endCal);
        System.out.println(datesBetweenTwoDates.size());

    }

    private static List<Date> getDatesBetweenTwoDates(Calendar startCal, Calendar endCal) {
        List<Date> datesInRange = new ArrayList<>();

        // 时
        startCal.set(Calendar.HOUR_OF_DAY, 0);
        // 分
        startCal.set(Calendar.MINUTE, 0);
        // 秒
        startCal.set(Calendar.SECOND, 0);
        // 毫秒
        startCal.set(Calendar.MILLISECOND, 0);

        while (!startCal.after(endCal)) {


            datesInRange.add(new Date(startCal.getTime().getTime()));

            startCal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return datesInRange;
    }

    @Test
    void testToString2() {
        Group creatGroup = new Group();
        Customer lch = new Customer("lch", "22");
        Customer jack = new Customer("jack", "18");
        Customer bond = new Customer("bond", "45");
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(lch);
        customers.add(jack);
        customers.add(bond);
        SysUser sysUser1 = new SysUser();
        sysUser1.setNickname("admin");
        ArrayList<SysUser> sysUsers = new ArrayList<>();
        sysUsers.add(sysUser1);
        creatGroup.setCustomers(customers);
        creatGroup.setUsers(sysUsers);
        creatGroup.setCustomerName("龙皇异次元");

//        List<Customer> customers1 = List.copyOf(customers);
        ArrayList<Customer> customers2 = new ArrayList<>(customers);
        Group forGroup = new Group();
        customers2.remove(1);
        forGroup.setCustomers(customers2);
        forGroup.setAddress("郑州");
        forGroup.setCustomerName("天羽屠龙舞");
//        BeanUtils.copyProperties(forGroup, creatGroup, BeanUtils.get);
//        BeanUtils.copyProperties(forGroup,creatGroup);
        System.out.println(creatGroup);
        System.out.println("$$$$");
        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreNullValue(true);
        BeanUtil.copyProperties(forGroup,creatGroup, copyOptions);
        System.out.println(creatGroup);

    }

}