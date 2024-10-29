package com.dwind.mywork.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64Decoder;
import com.dwind.mywork.util.NumberCalUtil;
import com.dwind.mywork.util.TextReader;
import jodd.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.dwind.mywork.pojo.AddReview;
import com.dwind.mywork.pojo.CarVehicleCode;
import com.dwind.mywork.pojo.Customer;
import com.dwind.mywork.pojo.Group;
import com.dwind.mywork.pojo.SysUser;
import com.dwind.mywork.pojo.Worker;
import com.dwind.mywork.util.SM2Util;
import jodd.util.StringUtil;
import org.apache.coyote.http11.Constants;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.coyote.http11.Constants.a;

class CodeFormatTest {

    @Test
    void main1() {
        String str = "a,b,c,,,";
        String[] ary = str.split(",");
        //预期大于3，结果是3
        System.out.println(ary.length);
        System.out.println(ary[2]);

    }

    @Test
    void main2(){
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
//        a.add("3");
        System.out.println(a);
        for (String temp : a) {
            if("3".equals(temp)) {
                a.remove(temp);
            }
        }
        System.out.println(a);


    }

    @Test
    void main3() {
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        System.out.println(a);
        Iterator<String> it = a.iterator();
        while (it.hasNext()) {
            String temp = it.next();
            if ("2".equals(temp)) {
                it.remove();
            }
        }
        System.out.println(a);

    }

    @Test
    void test1(){
        ArrayList<String> arrayList = new ArrayList<>();
        int size = arrayList.size();
        System.out.println(size);
        System.out.println("#####");
        arrayList = null;
        size = arrayList.size();
        System.out.println(size);
    }

    @Test
    void test2(){
        Integer value3 = Integer.valueOf("3");
        Integer three = Integer.valueOf("3");
        if (three == value3){
            System.out.println("相等");
        }else{
            System.out.println("wrong");
        }
        System.out.println("%%%%%%%");
        Integer value345 = Integer.valueOf("345");
        Integer three345 = Integer.valueOf("345");
        if (value345 == three345){
            System.out.println("相等");
        }else{
            System.out.println("wrong");
        }
    }

    @Test
    void test3_0(){
        double a = 1;
        System.out.println(a);
        double addOn = 3;
        System.out.println(addOn);
        double add = a / addOn;
        double multiply = add * addOn;
        System.out.println(multiply);
        System.out.println(add);
    }

    @Test
    void test3_1(){
        double a = 1;
        double b = 0.9;
        System.out.println(a - b);
    }

    @Test
    void test3(){
        BigDecimal bigDecimal = new BigDecimal("1");
        System.out.println(bigDecimal);
        BigDecimal addOn = new BigDecimal("3");
        System.out.println(addOn);
        BigDecimal add = bigDecimal.divide(addOn, 100,RoundingMode.HALF_DOWN);
        BigDecimal multiply = add.multiply(addOn);
        System.out.println(multiply);
        System.out.println(add);
    }

    @Test
    void test4(){
        List<Customer> numbers = Arrays.asList(
                new Customer().setTycCompanyId("1").setCustomerName("张小"),
                new Customer().setTycCompanyId("2").setCustomerName("李大"),
                new Customer().setTycCompanyId("3"),
                new Customer().setTycCompanyId("4").setCustomerName("胡望三")
        );
        String collect = numbers.stream().map(customer -> customer.getCustomerName() == null ? "":customer.getCustomerName()).collect(Collectors.joining(","));
//        String collect = numbers.stream().map(Customer::getCustomerName).collect(Collectors.joining(", "));
        System.out.println(collect);
    }

    @Test
    void test5(){
//        String sapFileName = "20231229044jzjg.txt";
//        String sapBankFileName = sapFileName.replace("jzjg","jz");
//
//        System.out.println(sapBankFileName);

        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> listAnHe = new ArrayList<>();
        listAnHe.add("我是李春辉\n他是谁");
        list.add("流水号@交易名称@公司代码@凭证类型@交易日期@交易日期@货币代码@汇率@流水号@行号@记账代码@特殊总账标识@总分类科目@供应商代码@SAP客户号@主资产号@次级资产号@事务代码@金额@税码@到期日@起息日@成本中心@内部订单@利润中心@分配编号@项目文本@原因代码@反记账标识");
        list.addAll(listAnHe);
        System.out.println(list);
        if (listAnHe.isEmpty()){
            System.out.println("安和is empty");
        }
        System.out.println("list的size: " + list.size());

        LocalDate yesterday = LocalDate.now().minusDays(1);
        String format = LocalDateTimeUtil.format(yesterday, "yyyyMMdd");
        System.out.println(format);
    }


    @Test
    void test6(){
        String password = DigestUtils.md5DigestAsHex("lichq324".getBytes());
        // ddcc5b0a1b2cdb70fac998012ef63945
        System.out.println(password);
        String string = UUID.randomUUID().toString().replace("-","");
        //8a311d1ee4f8480b925218b65fba20ef
        System.out.println(string);
//        sid-4585AD07-FC53-4841-9537-A95F04B537B6


    }

    @Test
    void test7(){
        String messageTheme = "融资业务归档消息提醒";
        if (StrUtil.isNotEmpty("orderInfo.getOrderNo()") && StrUtil.isNotEmpty("orderInfo.getCustomerName()")){
            messageTheme = "【融资业务归档】" + "orderInfo.getOrderNo()" + "orderInfo.getCustomerName()" + "的" + messageTheme;
        }
        System.out.println(messageTheme);
    }

    @Test
    void test8() {
        HashMap<String, Object> variablesMap = new HashMap<>();
        List<String> nodeList = new ArrayList<>();
        JSONObject person = new JSONObject();
        //CBP-9885  客户经理复审节点处理人取值错误，应取结清流程发起时选择的经办人
        SysUser sysUser = new SysUser();
        sysUser.setId(1234L).setEmpNo("23454676").setNickname("账务");
        if (sysUser != null) {
            person.put("id", sysUser.getId());
            person.put("no", sysUser.getEmpNo());
            person.put("name", sysUser.getNickname());
            person.put("type", "person");
            nodeList.add(person.toJSONString());
            variablesMap.put("managerNo", nodeList);
            person.put("id", sysUser.getId().toString());
            person.put("type", "USER");
            ArrayList<String> operatorList =  new ArrayList<>() ;
            operatorList.add(person.toJSONString());
            variablesMap.put("operator", operatorList);
            person.put("type", "person");
            ArrayList<String> operatorList2 =  new ArrayList<>() ;
            operatorList2.add(person.toJSONString());
            variablesMap.put("operator2", operatorList2);
            variablesMap.put("operator3", person.toJSONString());
        }
        System.out.println(variablesMap.get("managerNo"));
        System.out.println(variablesMap.get("operator"));
        System.out.println(variablesMap.get("operator2"));
        System.out.println(variablesMap.get("operator3"));
    }
    @Test
    void test9() {
        String processLink = "";
        String processId = "d1815955-b9b8-11ee-a7e2-005056af7842";
        String settleId = "1749680817228398593";
        processLink = "http://anhe.yutong.com/process-notify?processId=" + processId + "&id=" + settleId;

        System.out.println(processLink);

    }

    @Test
    void test10() {
        SysUser sysUser = new SysUser();
        String name = sysUser.getNickname();
        if (StringUtil.isBlank(name)){
            System.out.println("这个是空值");
        }else {
            System.out.println("haha");
        }
        System.out.println(name);
    }

    @Test
    void test11() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String key = "pk_fmp_login_KEY";
        String offset = "pk_fmp_login__IV";
        String code = "gegQcK/MDxx89yKc2QMdMQ==";
        byte[] decode = Base64Decoder.decode(code);
        String passwordDecode = new String(decode);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE,
                new SecretKeySpec(key.getBytes(StandardCharsets.US_ASCII), "AES"),
                new IvParameterSpec(offset.getBytes(StandardCharsets.US_ASCII)));
        byte[] finalBytes = cipher.doFinal(decode);
        String password = new String(finalBytes);

        System.out.println(code);
        System.out.println(password);
    }


    @Test
    void test12() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        // 时
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        // 分
        calendar.set(Calendar.MINUTE, 0);
        // 秒
        calendar.set(Calendar.SECOND, 0);
        // 毫秒
        calendar.set(Calendar.MILLISECOND, 0);

        long time = calendar.getTime().getTime();
        Date date = new Date(time);

        String format = DateUtil.format(date, "YYYY-MM-dd HH-mm-ss");
        System.out.println(format);
        DateTime parse = DateUtil.parse(format, "YYYY-MM-dd HH-mm-ss");
        System.out.println(parse);

        System.out.println(date);
        System.out.println(calendar);
    }


    @Test
    void test13() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("我是");
        arrayList.add("jintian");
        String join = String.join(",", arrayList);
        System.out.println(join);

    }
    @Test

    void test14() {
        BigDecimal bigDecimal = new BigDecimal("4554632432432436767676787798724.565786");
        String value = bigDecimal.toString();
        System.out.println(value);

    }


    @Test
    void test15() {
        Worker worker = new Worker();
        Customer lch = new Customer("lch", "23434III");
        Customer zhs = new Customer("zhs", "23434III");
        Customer qin = new Customer("qin", "23434III");
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(lch);
        customers.add(zhs);
        customers.add(qin);
        worker.setCustomers(customers);
        worker.setCustomerName("空山不见人");
        Customer myName = new Customer();
        BeanUtil.copyProperties(worker, myName);
        Group group = new Group();
        BeanUtil.copyProperties(worker, group);

        System.out.println(group);
        System.out.println(myName);


    }

    @Test
    void test16() {
        Integer a = 2;
        if (a == 2){
            System.out.println("hh");
        }else{
            System.out.println("nnnnaaaaaa");
        }

    }



    @Test
    void test17() {
        ArrayList<String> nameList = new ArrayList<>();
        String a = null;

        Customer customer = new Customer();
        nameList.add(customer.getCustomerName());
        a = String.join("，", nameList);
        System.out.println(a);

    }
    @Test
    void test18() {
        BigDecimal a = new BigDecimal("10.89");
        System.out.println( a);
        BigDecimal b = new BigDecimal(10.89);
        System.out.println(b);

    }
    @Test
    void test19() {
        SysUser sysUser = new SysUser();
        ArrayList<String> arrayList = new ArrayList<>();
        String nickname = sysUser.getNickname();
        String username = sysUser.getUsername();
        System.out.println(nickname);
        System.out.println(username);

        arrayList.add(sysUser.getNickname());

        arrayList.add(sysUser.getUsername());
        arrayList.add("lch");
        arrayList.add("lch2");

        String join = String.join(",", arrayList);
        System.out.println(join);

        if (arrayList.contains(null)){
            System.out.println("yes");
        }else {
            System.out.println("No");
        }

    }

    @Test
    void test20() {
        SysUser sysUser = new SysUser();
        ArrayList<String> arrayList = new ArrayList<>();
        String nickname = sysUser.getNickname();
        String username = sysUser.getUsername();
        System.out.println(nickname);
        System.out.println(username);
        sysUser.setNickname("root,3434, adm,woshishui");
        arrayList.addAll(Arrays.stream(sysUser.getNickname().split(",")).collect(Collectors.toList()));

        arrayList.add(sysUser.getUsername());
        arrayList.add("lch");
        arrayList.add("lch2");

        String join = String.join(",", arrayList);
        System.out.println(join);

        if (arrayList.contains(null)){
            System.out.println("yes");
        }else {
            System.out.println("No");
        }

    }

    @Test
    void test21() {
        HashMap<String, String> hashMap = new HashMap<>();
        String a  = "";
        hashMap.put("city", null);
        hashMap.put("name", a);
        if (hashMap.get("city") == null) {
            System.out.println("空数据");
            hashMap.put("city", "");
        }
        System.out.println(hashMap.get("city"));
        System.out.println(hashMap.get("name"));

    }

    @Test
    void test21_1() {
        HashMap<String, String> hashMap = new HashMap<>();
        Customer customer = new Customer();
        String a  = "";
        hashMap.put("city", null);
        hashMap.put("name", "null");
        customer.setCustomerName(hashMap.get("city"));
        customer.setCustomerName(null);
        if (hashMap.get("city") == null) {
            System.out.println("空数据");
            hashMap.put("city", "");
        }
        System.out.println(hashMap.get("city"));
        System.out.println(hashMap.get("name"));

    }
    @Test
    void timeTest1() {
        Date date = new Date(1722408989554L);
        String format = DateUtil.format(date, DatePattern.NORM_DATETIME_MS_PATTERN);
        System.out.println(format);

    }
    @Test
    void test25() {
        BigDecimal bigDecimal = new BigDecimal("69");
        BigDecimal bigDecimal1 = new BigDecimal("100");
        BigDecimal div = NumberUtil.div(bigDecimal, bigDecimal1).setScale(2,RoundingMode.UP);
        System.out.println(div);



    }

    @Test
    void test26() {
        ArrayList<String> arrayList = new ArrayList<>();
        String join = String.join(StrUtil.COMMA, arrayList);
        System.out.println(join);

        arrayList.add(null);
        join = String.join(StrUtil.COMMA, arrayList);
        System.out.println(join);

        ArrayList<String> list = new ArrayList<>();
        List<String> collect = list.stream().map(item -> item.trim()).collect(Collectors.toList());
        join = String.join(StrUtil.COMMA, collect);
        System.out.println(join);

        list = new ArrayList<>();
        list.add("jewrkj ");
        list.add(" jew434rkj ");
        collect = list.stream().map(item -> item.trim()).collect(Collectors.toList());
        join = String.join(StrUtil.COMMA, collect);
        System.out.println(join);


//        join = String.join(StrUtil.COMMA, null);
        System.out.println(join);



    }


//    @Test
//    void test27() throws Exception {
//        ArrayList<String> arrayList = new ArrayList<>();
//        String join = String.join(StrUtil.COMMA, arrayList);
//        System.out.println(join);
//
//        String password = "Mysql.123";
//        String[] arr = com.alibaba.druid.filter.config.ConfigTools.genKeyPair(512);
//        System.out.println("privateKey:" + arr[0]);
//        System.out.println("publicKey:" + arr[1]);
//        System.out.println("password:" + com.alibaba.druid.filter.config.ConfigTools.encrypt(arr[0], password));
//
//        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIT/XD3FfEnH8IjSTlyKG7e5Nwn/lX3fEA9UGLuH5UM8q03CzK31k+R12S3nFwGXv2hKiPxvjDw2oFP7A5fm5f0CAwEAAQ==";
//        String passEncode = "aiMN9wqQKXGoHlUv9U5jgfst6OCAKEOWdlllYLBUL/vXzcicpArNkfl/+9XX+fwhOAj1KBONBf2njjEI1gNEdg==";
//        String decrypt = ConfigTools.decrypt(publicKey, passEncode);
//        System.out.println("解密后的密钥" + decrypt);
//    }

    @Test
    void test28() {
        String regexURL="\"url\":\"(https?://[^\"]+)\"";
        System.out.println(regexURL);



    }

    @Test
    void test29() {
        String regexURL="${coustomr}的";
        List<String> split = StrUtil.split(regexURL, "${coustomr}");
        System.out.println(split);


    }





    @Test
    void test30() {
        ArrayList<CarVehicleCode> carVehicleCodes = new ArrayList<>();
        carVehicleCodes.add(new CarVehicleCode("213323kk"));
        carVehicleCodes.add(new CarVehicleCode("32hghd"));
        carVehicleCodes.add(new CarVehicleCode("67jk678"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("T_INB",carVehicleCodes);
        System.out.println(jsonObject);


    }


    @Test
    void test31() {
        String publicKey = "046655632A5DFACF5B7244593E1D0E55FD86F89F61A9FD3B9394D7424F3CAFA2F1C786CE7F73AC5F491BFE99E46809F0BA09BB70F3AEDA7FA87A05006B53725780";
        String privateKey = "DE15EB85F7B47906D0ED1771F76DDB2EAB809D080E9AA4FBE8C6C8D6AC203DA3";

        String requestData = "{\"downRatio\":\"200000\",\"carUscCode\":\"91420684MA49G2YF4Y\",\"legalTel\":\"18585856565\",\"fileName\":\"/zhaoshang_bank/AC112408190245101/43c4e9361993467a9f0f92ac9c6067a5.zip\",\"orderNo\":\"AC112408190245101\",\"accBankName\":\"招商银行股份有限公司郑州分行\",\"financeAmt\":\"450000\",\"accNo\":\"602380135910001\",\"coreUscCode\":\"91410000170001401D\",\"accName\":\"宇通客车股份有限公司\",\"carCompanyLegalName\":\"康大千\",\"orderAmt\":\"1\",\"carCompany\":\"湖北冠兴汽车服务有限公司\",\"totalPeriod\":\"36\",\"financTerm\":\"36\",\"rate\":\"3.45\",\"details\":[{\"carName\":\"宇通车辆\",\"vin\":\"LZYTBTE61N1001850\",\"carModel\":\"ZK6117HT61\"}]}";


        // 请求数据拼接：  报文体
        byte[] requestDataBytes = requestData.getBytes(StandardCharsets.UTF_8);

        System.out.println("报文" + requestData);


        // 生成签名
        byte[] signature = SM2Util.sign(privateKey, requestDataBytes);

        byte[] signBytes = SM2Util.encodeDERSignature(signature);

        String sign = Base64.encodeBase64String(SM2Util.encodeDERSignature(signature));

        System.out.println("签名" + sign);


        // 报文体加密
        byte[] encryptedData = SM2Util.encrypt(publicKey, requestDataBytes);

        String encryptString = Base64.encodeBase64String(encryptedData);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", encryptString);
        System.out.println("jsonObject" + jsonObject.toJSONString());
        jsonObject.put("sign", sign);
        System.out.println("jsonObject" + jsonObject);
        String jsonString = jsonObject.toJSONString();
        System.out.println("jsonObject" + jsonObject.toJSONString());

        System.out.println("加密结果" + encryptString);

        byte[] encodeBytes = encryptedData;

        byte[] decrypt = SM2Util.decrypt(privateKey, encodeBytes);

        String deCodeString = new String(decrypt);

        System.out.println("解密结果" + deCodeString);

        byte[] signTo = sign.getBytes(StandardCharsets.UTF_8);
        byte[] decode = java.util.Base64.getDecoder().decode(sign);
        byte[] signBaseBytes = Base64.decodeBase64(sign);

        boolean b = SM2Util.sm2WithSm3VerifyANS1(publicKey, encryptedData, signBytes);


    }

    @Test
    void test31_1() {
        String publicKey = "046655632A5DFACF5B7244593E1D0E55FD86F89F61A9FD3B9394D7424F3CAFA2F1C786CE7F73AC5F491BFE99E46809F0BA09BB70F3AEDA7FA87A05006B53725780";
        String privateKey = "DE15EB85F7B47906D0ED1771F76DDB2EAB809D080E9AA4FBE8C6C8D6AC203DA3";

        String requestData = "{\"downRatio\":\"200000\",\"carUscCode\":\"91420684MA49G2YF4Y\",\"legalTel\":\"18585856565\",\"fileName\":\"/zhaoshang_bank/AC112408190245101/43c4e9361993467a9f0f92ac9c6067a5.zip\",\"orderNo\":\"AC112408190245101\",\"accBankName\":\"招商银行股份有限公司郑州分行\",\"financeAmt\":\"450000\",\"accNo\":\"602380135910001\",\"coreUscCode\":\"91410000170001401D\",\"accName\":\"宇通客车股份有限公司\",\"carCompanyLegalName\":\"康大千\",\"orderAmt\":\"1\",\"carCompany\":\"湖北冠兴汽车服务有限公司\",\"totalPeriod\":\"36\",\"financTerm\":\"36\",\"rate\":\"3.45\",\"details\":[{\"carName\":\"宇通车辆\",\"vin\":\"LZYTBTE61N1001850\",\"carModel\":\"ZK6117HT61\"}]}";

        String data = "BJiusVClRiq/mhWrOJKxt88VYeS7jiEvKIR8K1JxmZfeQ848i/5Dc48S5rpkxdowMeW2zFuoavuNYhUi1qngRNU85FgR8zTKIHHFbjx29va2DRzdRrHzp5Va9N/kTyjXx9Rz8aX4B23B2Pc0Xx6owV4zk4Cq5Vluh0BZ558kqGL0d74ZNuI6qAig+YPaHZrr/YUx+l0Mes5cF6ggvOjVbfM8HjaNR70kscJnvALKjQdXiuMbCVEFwZWGQjvfEMpRJkz8LUGiI5bxYQxgRBG/+zoU9FdVWHXJv3kqUU1Ok+rUdqJkAnFBLrXD6sngCYhzmcMIJqqUNdARSM7ZL0lUtShIsm4k8me8FvkVNGjAOpEoWxB8/6UPy0GzEC+aqawb3k2Z06lDakTEQoIXVW/12M4CMy59onH8Znd6JI2LkHVSp0ZJTliadHr2sHS0wzT4eyBGoLVK9aAYOVBQtJ5+ohCB+lPZIGr483wLcnJUB2byMAQd/DS3b+CCyW3OnqTBDQxQ8dZ+TAWFjmeWLGfqsRUDpJAYnEdtoHDEu93iw6oQfc55VJijUH5bah74NRdHhoeQ8zs1fSFgnZjwz8NJdbgPJglztNxFPYqiL9ftJ0Yd65gQWnJX1Tj3vYqEXNMzVVTR5vievvsySmHBHEPshPkIJKOfedE5VtvmEnTHsLiFfFjOjyDrdy1iJfzZqHjBnw09W2TxOCHNxzNbite7S5dysjTzYs9Bj2QZcbALNJ9qXll+vVx+8dFNJx0efusA7uoeY6mw2vfF5b2sLIq5h0zxnmyJf76g910lReuazk9oFSCSH8pKV4rXhJhDrvTR/z/Q5N52t9R9HMbTqie+G/W/YigmDZjeuCOarDqr5KTge3tZYeTUx1R74gzn45FDmbIYz/ggAuLGYplCcBs6h/bK6UMGr2J3KOMcOWz7Rui3a6JgO4XdHZWcfJHe7Q==";

        String signS = "MEUCIC74Sl7oVze6XUN/Cj87XclR1nqZpzT7vJVHAGA7WRWjAiEAtrU41U7PXvDpCHnEXrkvG4tRK1aPFI2FIwk+8drn8+0=";

        byte[] decrypt1 = SM2Util.decrypt(privateKey, Base64.decodeBase64(data));
        String s = new String(decrypt1);
        System.out.println("解密结果" + s);

        byte[] signB = Base64.decodeBase64(signS);

        boolean b1 = SM2Util.sm2WithSm3VerifyANS1(publicKey, decrypt1, signB);
        System.out.println("验签结果" + b1);


        // 请求数据拼接：  报文体
        byte[] requestDataBytes = requestData.getBytes(StandardCharsets.UTF_8);

        System.out.println("报文" + requestData);


        // 生成签名
        byte[] signature = SM2Util.sign(privateKey, requestDataBytes);

        byte[] signBytes = SM2Util.encodeDERSignature(signature);

        String sign = Base64.encodeBase64String(SM2Util.encodeDERSignature(signature));

        System.out.println("签名" + sign);


        // 报文体加密
        byte[] encryptedData = SM2Util.encrypt(publicKey, requestDataBytes);

        String encryptString = Base64.encodeBase64String(encryptedData);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", encryptedData);
        jsonObject.put("sign", sign);
        String jsonString = jsonObject.toJSONString();
        System.out.println("jsonObject" + jsonObject.toJSONString());

        System.out.println("加密结果" + encryptString);

        byte[] encodeBytes = encryptedData;

        byte[] decrypt = SM2Util.decrypt(privateKey, encodeBytes);

        String deCodeString = new String(decrypt);

        System.out.println("解密结果" + deCodeString);

        byte[] signTo = sign.getBytes(StandardCharsets.UTF_8);
        byte[] decode = java.util.Base64.getDecoder().decode(sign);
        byte[] signBaseBytes = Base64.decodeBase64(sign);

        boolean b = SM2Util.sm2WithSm3VerifyANS1(publicKey, decrypt, signBytes);


    }

    @Test
    void test31_2() {
        String publicKey = "04D6878664923C9162FD8326D44EFA7DB1F5699F719DB0E48540D6C38E7F53054E2618DD3FA50415281DEAD665C385886FC9AE6148CB683B7EC72F54A8173AB689";
        String privateKey = "DE15EB85F7B47906D0ED1771F76DDB2EAB809D080E9AA4FBE8C6C8D6AC203DA3";

        String requestData = "{\"downRatio\":\"200000\",\"carUscCode\":\"91420684MA49G2YF4Y\",\"legalTel\":\"18585856565\",\"fileName\":\"/zhaoshang_bank/AC112408190245101/43c4e9361993467a9f0f92ac9c6067a5.zip\",\"orderNo\":\"AC112408190245101\",\"accBankName\":\"招商银行股份有限公司郑州分行\",\"financeAmt\":\"450000\",\"accNo\":\"602380135910001\",\"coreUscCode\":\"91410000170001401D\",\"accName\":\"宇通客车股份有限公司\",\"carCompanyLegalName\":\"康大千\",\"orderAmt\":\"1\",\"carCompany\":\"湖北冠兴汽车服务有限公司\",\"totalPeriod\":\"36\",\"financTerm\":\"36\",\"rate\":\"3.45\",\"details\":[{\"carName\":\"宇通车辆\",\"vin\":\"LZYTBTE61N1001850\",\"carModel\":\"ZK6117HT61\"}]}";

        String data = "BN2/5FsJXT1s7TCm7BgcqdZ6bYRyKDcCE593dKq4MH9D0BLFmDD42nV4I7Xpc++Z8T9cwZGPVubELVrkidPBTW0iqEenL+7L2oHwhj4mwnInPA1c1vo0tbrhxXsDD/44222V0JJJfafjl6wt8K+EH3x1ga7qs15n99nUwRiTve0qRGW1k752tbrySaWlqLLCEkvFY0fJRmzg";

        String signS = "MEUCIQCy7ezV7kH6Y55bWQk9VLh7oH0YPTdT65X7L7KR1tUW6AIgXMzb49uPTOo2+hOgDh8mjDhHWkCWQNV9A1EHGUyCPPs=";

        byte[] decrypt1 = SM2Util.decrypt(privateKey, Base64.decodeBase64(data));
        String s = new String(decrypt1);
        System.out.println("解密结果" + s);

        byte[] signB = Base64.decodeBase64(signS);

        boolean b1 = SM2Util.sm2WithSm3VerifyANS1(publicKey, decrypt1, signB);
        System.out.println("验签结果" + b1);

    }

    @Test
    void test31_3() {
        String publicKey = "046655632A5DFACF5B7244593E1D0E55FD86F89F61A9FD3B9394D7424F3CAFA2F1C786CE7F73AC5F491BFE99E46809F0BA09BB70F3AEDA7FA87A05006B53725780";
        String privateKey = "DE15EB85F7B47906D0ED1771F76DDB2EAB809D080E9AA4FBE8C6C8D6AC203DA3";

        String requestData = "{\"downRatio\":\"200000\",\"carUscCode\":\"91420684MA49G2YF4Y\",\"legalTel\":\"18585856565\",\"fileName\":\"/zhaoshang_bank/AC112408190245101/43c4e9361993467a9f0f92ac9c6067a5.zip\",\"orderNo\":\"AC112408190245101\",\"accBankName\":\"招商银行股份有限公司郑州分行\",\"financeAmt\":\"450000\",\"accNo\":\"602380135910001\",\"coreUscCode\":\"91410000170001401D\",\"accName\":\"宇通客车股份有限公司\",\"carCompanyLegalName\":\"康大千\",\"orderAmt\":\"1\",\"carCompany\":\"湖北冠兴汽车服务有限公司\",\"totalPeriod\":\"36\",\"financTerm\":\"36\",\"rate\":\"3.45\",\"details\":[{\"carName\":\"宇通车辆\",\"vin\":\"LZYTBTE61N1001850\",\"carModel\":\"ZK6117HT61\"}]}";

        String data = "BJiusVClRiq/mhWrOJKxt88VYeS7jiEvKIR8K1JxmZfeQ848i/5Dc48S5rpkxdowMeW2zFuoavuNYhUi1qngRNU85FgR8zTKIHHFbjx29va2DRzdRrHzp5Va9N/kTyjXx9Rz8aX4B23B2Pc0Xx6owV4zk4Cq5Vluh0BZ558kqGL0d74ZNuI6qAig+YPaHZrr/YUx+l0Mes5cF6ggvOjVbfM8HjaNR70kscJnvALKjQdXiuMbCVEFwZWGQjvfEMpRJkz8LUGiI5bxYQxgRBG/+zoU9FdVWHXJv3kqUU1Ok+rUdqJkAnFBLrXD6sngCYhzmcMIJqqUNdARSM7ZL0lUtShIsm4k8me8FvkVNGjAOpEoWxB8/6UPy0GzEC+aqawb3k2Z06lDakTEQoIXVW/12M4CMy59onH8Znd6JI2LkHVSp0ZJTliadHr2sHS0wzT4eyBGoLVK9aAYOVBQtJ5+ohCB+lPZIGr483wLcnJUB2byMAQd/DS3b+CCyW3OnqTBDQxQ8dZ+TAWFjmeWLGfqsRUDpJAYnEdtoHDEu93iw6oQfc55VJijUH5bah74NRdHhoeQ8zs1fSFgnZjwz8NJdbgPJglztNxFPYqiL9ftJ0Yd65gQWnJX1Tj3vYqEXNMzVVTR5vievvsySmHBHEPshPkIJKOfedE5VtvmEnTHsLiFfFjOjyDrdy1iJfzZqHjBnw09W2TxOCHNxzNbite7S5dysjTzYs9Bj2QZcbALNJ9qXll+vVx+8dFNJx0efusA7uoeY6mw2vfF5b2sLIq5h0zxnmyJf76g910lReuazk9oFSCSH8pKV4rXhJhDrvTR/z/Q5N52t9R9HMbTqie+G/W/YigmDZjeuCOarDqr5KTge3tZYeTUx1R74gzn45FDmbIYz/ggAuLGYplCcBs6h/bK6UMGr2J3KOMcOWz7Rui3a6JgO4XdHZWcfJHe7Q==";

        String signS = "MEUCIC74Sl7oVze6XUN/Cj87XclR1nqZpzT7vJVHAGA7WRWjAiEAtrU41U7PXvDpCHnEXrkvG4tRK1aPFI2FIwk+8drn8+0=";

        byte[] decrypt1 = SM2Util.decrypt(privateKey, Base64.decodeBase64(data));
        String s = new String(decrypt1);
        System.out.println("解密结果" + s);

        byte[] signB = Base64.decodeBase64(signS);

        boolean b1 = SM2Util.sm2WithSm3VerifyANS1(publicKey, decrypt1, signB);
        System.out.println("验签结果" + b1);




        // 请求数据拼接：  报文体
        byte[] requestDataBytes = requestData.getBytes(StandardCharsets.UTF_8);



        // 生成签名
        byte[] signature = SM2Util.sign(privateKey, requestDataBytes);
        String sign = Base64.encodeBase64String(SM2Util.encodeDERSignature(signature));

        System.out.println("签名" + sign);


        // 报文体加密
        byte[] encryptedData = SM2Util.encrypt(publicKey, requestDataBytes);

        String encryptString = Base64.encodeBase64String(encryptedData);
        System.out.println("加密结果" + encryptString);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", encryptString);
        jsonObject.put("sign", sign);
        String jsonString = jsonObject.toJSONString();
        System.out.println("jsonObject" + jsonObject.toJSONString());

//        jsonObject{"data":"BFmj8yccRUl4kSBCdrA7gf7vwQerkFtbdiFVW/+UjWROmkxD0HdxgZhd3E11CSwBFgOXL6aBnkBRrV3abir8JH9X03NgfQr6pug3qoAJJsmvgdXEB6dAWBNd0GqqPTSc+QK75N35NDgUeP8lNmTyOs0d2u+0OcNbpANijGQMW72RCBKIq1jt6u2mwHPQtQRi/AwjsKCwvZTQOKvMVdnwzuJNgIbbAA4n5aYC1E+HuV82rnXybqIq5ZqY/vlRASa3RE/w1huw4srb312ZcVndnOiyQfO1jqTj2ARtMb8PBJHnCSH2/rgppyZiAOMdyH1GCEImtGGa4CBFN62m/vpxFewN3J5+4oUNDqwEOUrq8eXzRzHDCpSuQm5TjRnG/u9XMOCRzMEg+vKnXqycIKjoUSmNDkSit5pyRy4TdeYYWFWg5rcjX2MzKH4zGV5IHoTRzMc11DV+wFzso5RTiqz23gc/feDxwM8ZmKIu87JP4199lG6vXbClRl2XBBqtd9InCrkK0U+65Gd5A7Sj4w6MUvFH+8PwoEx5bqmRvFGRkA9BwCyiaY/XwjuJeeZAehOu0ZNhb4tJvTQVoOGDEnA9JcdnChrQneXVL/ws/qlMY2s0ThNpgsbPdoJYui8JzCl3FQwMUZmCP7NtdgFG51ErRe3ND28/0Gp/4hdGG3nm2w5KLVfOmbxn2giSWQUe+gDOS+isuvW5ymYeu0VOudNtTUfjE4WT5+kSUGTkPyaCrk/blThh+OGEg6eB2+sVqO36U7+2PrHaThBMjvJwLB0xRlIuy+IrYnlPQ5PjNd0aE8sRqNNOIRSrfEEr1KzeSm+1kCbA8CxH/p0HEwyCqnC7Wuenjeg3V5+d1mdOdhoT90/PhfIkk/KBgmQfhypKHgdC77qDcNCp4aLkJU9GkTbKpr8k1TzEVODqN/FDwHQ2fE6FQynnGL2+1J55HhhK+A==","sign":"MEUCIQDaZ29YjKEj+iiWzxUV+I2GH2WyqQvm7N8Io8gW0TH8HgIgNv3eGkuXOZy1Natr0vK6OAhaGgaOtRshgdshJajTVCA="}
//        jsonObject{"data":"BMFuhNa0Uk3FjAoVegJsEzI8y9F7KGsL+fw/iXaAnLsoxLu7S0/NdDeN8/DbXbyVv0gDDX4XlEaq0tE3i/occ2ksUa4Rxkq5fa+0tp6nwobSgLjAIHpxje+s/DuGKQpON8iVKMa/x3HZF12h9dFWndZdF8KvVhFfKLmMyj0ulQh/8lsrUkdSlzIXsekVdSidrlUNZYdOV+mtS9xAsj04xmXIzA8YSZGBwpiKA4B8aM9eCiw7ftQ+xkDeu7Ooe50nua99GhALuoGsgYmUkthVdN3/3hMW0dbD84cpz2MEmbNfht5fm4XnklH24gy4F7h5d+iXrPbWO7TE3NQYGe1D4BCsZ4FBbs+utJkXd1VWWL4jryNBc/Wx2az4yX4D4cdCoFgVmPtMNT3Ry3lN13FgcephlwyWsAJH5wJwlvfKUSAWsg1xf38ma2XUUEgA2+TxsuCx1JkYhdUK/85QGYw3juu1rda/DRAb9PaViGY2XDoTAMsSI5xolGtYHFbOU1aqKPtLQxrG7gTgiqTZvQeVkJjQ+xj6sO6LrXFSrsJUteghcZTiik5f2lMP9VBu7n2FKFIFS3JQbB7sDp9ynkMImG+vobyIULbrZPMdPRb/F3myxV7TXAosk9HwLGttPDFfj4HJlypQ29tg2DDZHpgDEicBNca6fEvWugiYZkU8QlELoVIR1hNG9nhsmoWYyUoqSd1xMBxFquGbavXUbvS/16zmS1s19pGsjmoD6/A1OdDQtlAd4iYqJCDh5D0BFk3/gmtc04S2XjTIU407zqpU+qsiQSA+KfrscC0x7/FPoQIPUKBPruE7DJzIlR2ZBi/YRvBXglsW5X9gAgK3qPPoso9JyKAONltDc3JUchxyBdrNFJxF8mOyPEP+xMq+dGTMQdKJB6XjB5vTcMJn8CQzXkjIS/oP/Ft4Pt0V2ySco/YvU9Xu6neWe8OQUFjWpQ==","sign":"MEYCIQDi0jurKk/eps+oj/+ycbt4jsiOQTQXEaocAvqlL+owAwIhAJkFonBH8imkh99q4M34L0V3AB1OzVtOP60tZHjEmuXz"}

        System.out.println("再次验签 +++++++++++++++++++++++++++");

        byte[] decrypt2 = SM2Util.decrypt(privateKey, Base64.decodeBase64(encryptString));
        String decode = new String(decrypt2);
        System.out.println("解密结果" + decode);

        byte[] signBaseBytes = Base64.decodeBase64(sign);

        boolean b2 = SM2Util.sm2WithSm3VerifyANS1(publicKey, decrypt2, signBaseBytes);
        System.out.println("验签结果" + b2);

    }


    @Test
    void test31_4() {
        String publicKeyA = "046655632A5DFACF5B7244593E1D0E55FD86F89F61A9FD3B9394D7424F3CAFA2F1C786CE7F73AC5F491BFE99E46809F0BA09BB70F3AEDA7FA87A05006B53725780";
        String privateKeyA = "DE15EB85F7B47906D0ED1771F76DDB2EAB809D080E9AA4FBE8C6C8D6AC203DA3";

        String publicKeyB = "044D1AEF87AAE5AAA3870F5ED4BD939A0F5FD37A0D9C03BFE23E8C901855D53A0BC827AA0CC853F1B8703552553806C83765AB13FEC5E9E8D09772104E9AD2C5AE";
        String privateKeyB = "5E8186C7839FFFB3DD6DA46445C347812633BD5B6AB2E7D686809BB25762D32C";

        String requestData = "{\"downRatio\":\"200000\",\"carUscCode\":\"91420684MA49G2YF4Y\",\"legalTel\":\"18585856565\",\"fileName\":\"/zhaoshang_bank/AC112408190245101/43c4e9361993467a9f0f92ac9c6067a5.zip\",\"orderNo\":\"AC112408190245101\",\"accBankName\":\"招商银行股份有限公司郑州分行\",\"financeAmt\":\"450000\",\"accNo\":\"602380135910001\",\"coreUscCode\":\"91410000170001401D\",\"accName\":\"宇通客车股份有限公司\",\"carCompanyLegalName\":\"康大千\",\"orderAmt\":\"1\",\"carCompany\":\"湖北冠兴汽车服务有限公司\",\"totalPeriod\":\"36\",\"financTerm\":\"36\",\"rate\":\"3.45\",\"details\":[{\"carName\":\"宇通车辆\",\"vin\":\"LZYTBTE61N1001850\",\"carModel\":\"ZK6117HT61\"}]}";

        String respondData = "{\"code\":\"B0100\",\"message\":\"解密失败！\",\"data\":null}";

//        String data = "BJiusVClRiq/mhWrOJKxt88VYeS7jiEvKIR8K1JxmZfeQ848i/5Dc48S5rpkxdowMeW2zFuoavuNYhUi1qngRNU85FgR8zTKIHHFbjx29va2DRzdRrHzp5Va9N/kTyjXx9Rz8aX4B23B2Pc0Xx6owV4zk4Cq5Vluh0BZ558kqGL0d74ZNuI6qAig+YPaHZrr/YUx+l0Mes5cF6ggvOjVbfM8HjaNR70kscJnvALKjQdXiuMbCVEFwZWGQjvfEMpRJkz8LUGiI5bxYQxgRBG/+zoU9FdVWHXJv3kqUU1Ok+rUdqJkAnFBLrXD6sngCYhzmcMIJqqUNdARSM7ZL0lUtShIsm4k8me8FvkVNGjAOpEoWxB8/6UPy0GzEC+aqawb3k2Z06lDakTEQoIXVW/12M4CMy59onH8Znd6JI2LkHVSp0ZJTliadHr2sHS0wzT4eyBGoLVK9aAYOVBQtJ5+ohCB+lPZIGr483wLcnJUB2byMAQd/DS3b+CCyW3OnqTBDQxQ8dZ+TAWFjmeWLGfqsRUDpJAYnEdtoHDEu93iw6oQfc55VJijUH5bah74NRdHhoeQ8zs1fSFgnZjwz8NJdbgPJglztNxFPYqiL9ftJ0Yd65gQWnJX1Tj3vYqEXNMzVVTR5vievvsySmHBHEPshPkIJKOfedE5VtvmEnTHsLiFfFjOjyDrdy1iJfzZqHjBnw09W2TxOCHNxzNbite7S5dysjTzYs9Bj2QZcbALNJ9qXll+vVx+8dFNJx0efusA7uoeY6mw2vfF5b2sLIq5h0zxnmyJf76g910lReuazk9oFSCSH8pKV4rXhJhDrvTR/z/Q5N52t9R9HMbTqie+G/W/YigmDZjeuCOarDqr5KTge3tZYeTUx1R74gzn45FDmbIYz/ggAuLGYplCcBs6h/bK6UMGr2J3KOMcOWz7Rui3a6JgO4XdHZWcfJHe7Q==";
//
//        String signS = "MEUCIC74Sl7oVze6XUN/Cj87XclR1nqZpzT7vJVHAGA7WRWjAiEAtrU41U7PXvDpCHnEXrkvG4tRK1aPFI2FIwk+8drn8+0=";

        System.out.println("\n请求加密开始===========@@@@@@@@@@@@\n");

        String dataA = Base64.encodeBase64String(SM2Util.encrypt(publicKeyA, requestData.getBytes(StandardCharsets.UTF_8)));
        System.out.println("dataA " + dataA);
        String signA = Base64.encodeBase64String(SM2Util.sign(privateKeyB, requestData.getBytes(StandardCharsets.UTF_8)));
        System.out.println("signA " + signA);

        System.out.println("\n请求加密结束===========开始解密@@@@@@@@@@@@\n");

        String requestA = new String(SM2Util.decrypt(privateKeyA, Base64.decodeBase64(dataA)));
        System.out.println("解密后报文 " + requestA);
        boolean endA = SM2Util.sm2WithSm3VerifyANS1(publicKeyB, SM2Util.decrypt(privateKeyA, Base64.decodeBase64(dataA)), Base64.decodeBase64(signA));
        System.out.println("验签结果 " + endA);

        System.out.println("\n响应加密开始===========@@@@@@@@@@@@\n");

        String dataB = Base64.encodeBase64String(SM2Util.encrypt(publicKeyB, respondData.getBytes(StandardCharsets.UTF_8)));
        System.out.println("dataB " + dataB);
        String signB = Base64.encodeBase64String(SM2Util.sign(privateKeyA, respondData.getBytes(StandardCharsets.UTF_8)));
        System.out.println("signB " + signB);

        System.out.println("\n响应    &&&&&加密结束**************开始解密%%%%%%%%%%%%%\n");

        String requestB = new String(SM2Util.decrypt(privateKeyB, Base64.decodeBase64(dataB)));
        System.out.println("解密后报文 " + requestB);
        boolean endB = SM2Util.sm2WithSm3VerifyANS1(publicKeyA, SM2Util.decrypt(privateKeyB, Base64.decodeBase64(dataB)), Base64.decodeBase64(signB));
        System.out.println("验签结果  " + endB);

    }

    @Test
    void test31_5() {
        String publicKeyA = "04B6B3435D2DE6EF0819399440C3A047CEFE32F6F1A0C528B99C2208A68C950AC314D6C81E7C2C15DDEC731D511B0CF97931518B2A536A31BEEB03389886BF59D6";
        String privateKeyA = "0AC3B3E5AB1115315BA0C93262C1C4A6136E2C4178B655F4FCBCD22E9A62D984";

        String publicKeyB = "044D1AEF87AAE5AAA3870F5ED4BD939A0F5FD37A0D9C03BFE23E8C901855D53A0BC827AA0CC853F1B8703552553806C83765AB13FEC5E9E8D09772104E9AD2C5AE";
        String privateKeyB = "5E8186C7839FFFB3DD6DA46445C347812633BD5B6AB2E7D686809BB25762D32C";

        String requestData = "{\"downRatio\":\"200000\",\"carUscCode\":\"91420684MA49G2YF4Y\",\"legalTel\":\"18585856565\",\"fileName\":\"/zhaoshang_bank/AC112408190245101/43c4e9361993467a9f0f92ac9c6067a5.zip\",\"orderNo\":\"AC112408190245101\",\"accBankName\":\"招商银行股份有限公司郑州分行\",\"financeAmt\":\"450000\",\"accNo\":\"602380135910001\",\"coreUscCode\":\"91410000170001401D\",\"accName\":\"宇通客车股份有限公司\",\"carCompanyLegalName\":\"康大千\",\"orderAmt\":\"1\",\"carCompany\":\"湖北冠兴汽车服务有限公司\",\"totalPeriod\":\"36\",\"financTerm\":\"36\",\"rate\":\"3.45\",\"details\":[{\"carName\":\"宇通车辆\",\"vin\":\"LZYTBTE61N1001850\",\"carModel\":\"ZK6117HT61\"}]}";

        String respondData = "{\"code\":\"B0100\",\"message\":\"解密失败！\",\"data\":null}";

//        String data = "BJiusVClRiq/mhWrOJKxt88VYeS7jiEvKIR8K1JxmZfeQ848i/5Dc48S5rpkxdowMeW2zFuoavuNYhUi1qngRNU85FgR8zTKIHHFbjx29va2DRzdRrHzp5Va9N/kTyjXx9Rz8aX4B23B2Pc0Xx6owV4zk4Cq5Vluh0BZ558kqGL0d74ZNuI6qAig+YPaHZrr/YUx+l0Mes5cF6ggvOjVbfM8HjaNR70kscJnvALKjQdXiuMbCVEFwZWGQjvfEMpRJkz8LUGiI5bxYQxgRBG/+zoU9FdVWHXJv3kqUU1Ok+rUdqJkAnFBLrXD6sngCYhzmcMIJqqUNdARSM7ZL0lUtShIsm4k8me8FvkVNGjAOpEoWxB8/6UPy0GzEC+aqawb3k2Z06lDakTEQoIXVW/12M4CMy59onH8Znd6JI2LkHVSp0ZJTliadHr2sHS0wzT4eyBGoLVK9aAYOVBQtJ5+ohCB+lPZIGr483wLcnJUB2byMAQd/DS3b+CCyW3OnqTBDQxQ8dZ+TAWFjmeWLGfqsRUDpJAYnEdtoHDEu93iw6oQfc55VJijUH5bah74NRdHhoeQ8zs1fSFgnZjwz8NJdbgPJglztNxFPYqiL9ftJ0Yd65gQWnJX1Tj3vYqEXNMzVVTR5vievvsySmHBHEPshPkIJKOfedE5VtvmEnTHsLiFfFjOjyDrdy1iJfzZqHjBnw09W2TxOCHNxzNbite7S5dysjTzYs9Bj2QZcbALNJ9qXll+vVx+8dFNJx0efusA7uoeY6mw2vfF5b2sLIq5h0zxnmyJf76g910lReuazk9oFSCSH8pKV4rXhJhDrvTR/z/Q5N52t9R9HMbTqie+G/W/YigmDZjeuCOarDqr5KTge3tZYeTUx1R74gzn45FDmbIYz/ggAuLGYplCcBs6h/bK6UMGr2J3KOMcOWz7Rui3a6JgO4XdHZWcfJHe7Q==";
//
//        String signS = "MEUCIC74Sl7oVze6XUN/Cj87XclR1nqZpzT7vJVHAGA7WRWjAiEAtrU41U7PXvDpCHnEXrkvG4tRK1aPFI2FIwk+8drn8+0=";

        System.out.println("\n请求加密开始===========@@@@@@@@@@@@\n");

        String dataA = Base64.encodeBase64String(SM2Util.encrypt(publicKeyA, requestData.getBytes(StandardCharsets.UTF_8)));
        System.out.println("dataA " + dataA);
        String signA = Base64.encodeBase64String(SM2Util.sign(privateKeyB, requestData.getBytes(StandardCharsets.UTF_8)));
        System.out.println("signA " + signA);

        System.out.println("\n请求加密结束===========开始解密@@@@@@@@@@@@\n");

        String requestA = new String(SM2Util.decrypt(privateKeyA, Base64.decodeBase64(dataA)));
        System.out.println("解密后报文 " + requestA);
        boolean endA = SM2Util.sm2WithSm3VerifyANS1(publicKeyB, SM2Util.decrypt(privateKeyA, Base64.decodeBase64(dataA)), Base64.decodeBase64(signA));
        System.out.println("验签结果 " + endA);

        System.out.println("\n响应加密开始===========@@@@@@@@@@@@\n");

        String dataB = Base64.encodeBase64String(SM2Util.encrypt(publicKeyB, respondData.getBytes(StandardCharsets.UTF_8)));
        System.out.println("dataB " + dataB);
        String signB = Base64.encodeBase64String(SM2Util.sign(privateKeyA, respondData.getBytes(StandardCharsets.UTF_8)));
        System.out.println("signB " + signB);

        System.out.println("\n响应    &&&&&加密结束**************开始解密%%%%%%%%%%%%%\n");

        String requestB = new String(SM2Util.decrypt(privateKeyB, Base64.decodeBase64(dataB)));
        System.out.println("解密后报文 " + requestB);
        boolean endB = SM2Util.sm2WithSm3VerifyANS1(publicKeyA, SM2Util.decrypt(privateKeyB, Base64.decodeBase64(dataB)), Base64.decodeBase64(signB));
        System.out.println("验签结果  " + endB);

    }



    @Test
    void test32() {
        String url = "http://localhost:7004/orderInfo/cmbPushOrderRequest";

        // 要发送的JSON字符串
        String json = "{\"orderNo\":\"AC112408190245101\"}";

        // 创建HttpClient实例
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建HttpPost实例
            HttpPost httpPost = new HttpPost(url);

            // 设置请求头
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("busFlag", "anchi");
            httpPost.setHeader("busTag", "lichq");

            // 设置请求体
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);

            // 执行请求
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                // 获取响应体
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    // 打印响应内容
                    System.out.println(EntityUtils.toString(responseEntity));
                }

                // 确保响应体内容被完全消耗
                EntityUtils.consume(responseEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test33() {
        String str = "VVVV";
        ArrayList<String> contractList = new ArrayList<>();
        contractList.add("aaa");
        contractList.add("这是");

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(contractList);
        contractList.add("VVVV");
        contractList.add("这是什么");
        contractList.add("这是么aaa");
        contractList.add("这是么bbbb");
        System.out.println("&&&" + contractList);
        System.out.println("&&&" + contractList.toArray());
//        String[] a = new String[]{"3","4","8","9","5","6","7"};
        String[] a = new String[]{};
        System.out.println(a);
        String[] array = arrayList.toArray(a);
        System.out.println(array);
        System.out.println( StrUtil.containsAny(str, array) );
        List<String> collect = contractList.stream().filter(item -> StrUtil.containsAny(item, array)).collect(Collectors.toList());
        System.out.println(collect);

    }
    @Test
    void test34() {
        String str = "AH722408261345308";
        String substring = str.substring(0, str.length() - 2);
        System.out.println(substring);

        String str2 = "IN24082613453-9";
        String substring2 = str2.substring(0, str2.indexOf("-"));
        System.out.println(substring2);

    }

    @Test
    void test35() {
        String str = "☑";
        char aChar = '\uF0FE';

        System.out.println(aChar);

        int i = (int) aChar;
        String hexString = Integer.toHexString(aChar);

        System.out.println(Integer.toHexString(aChar));


        String str2 = "IN24082613453-9";
        String substring2 = str2.substring(0, str2.indexOf("-"));
        System.out.println(substring2);

    }

    @Test
    void test36() {
        DateTime parse = DateUtil.parse("2024-09-28 03:56:31", "yyyy-MM-dd HH:mm:ss");
        ZoneId zoneId = ZoneId.systemDefault();
        String stringDefaultTimeZone = parse.toStringDefaultTimeZone();
        System.out.println(stringDefaultTimeZone);
        System.out.println(parse.toInstant().toEpochMilli());



        String str2 = "IN24082613453-9";
        String substring2 = str2.substring(0, str2.indexOf("-"));
        System.out.println(substring2);

    }

    @Test
    void test37() {
        BigDecimal a = new BigDecimal("123.213");
        BigDecimal bigDecimal = a.setScale(2, BigDecimal.ROUND_HALF_UP);
        String replace = bigDecimal.toString().replace(".", "");
        System.out.println(a);
        System.out.println(replace);


    }

    @Test
    void test38() {
        BigDecimal bigDecimal = new BigDecimal("3549810.9675");
//        bigDecimal = new BigDecimal("0.0");
        List<String> amountDigits = getAmountDigits(bigDecimal);
        System.out.println(amountDigits);

    }

    public static List<String> getAmountDigits(BigDecimal amount) {
        // 将金额转换为以分为单位的整数
        long cents = amount.multiply(new BigDecimal(100)).longValue();
        List<String> digits = new ArrayList<>();
        // 计算各个数位的值
        digits.add(String.valueOf((int) (cents / 10000000000L)));
        digits.add(String.valueOf((int) (cents % 10000000000L / 1000000000L)));
        digits.add(String.valueOf((int) (cents % 1000000000L / 100000000L)));
        digits.add(String.valueOf((int) (cents % 100000000L / 10000000L)));
        digits.add(String.valueOf((int) (cents % 10000000L / 1000000L)));
        digits.add(String.valueOf((int) (cents % 1000000L / 100000L)));
        digits.add(String.valueOf((int) (cents % 100000L / 10000L)));
        digits.add(String.valueOf((int) (cents % 10000L / 1000L)));
        digits.add(String.valueOf((int) (cents % 1000L / 100L)));
        digits.add(String.valueOf((int) (cents % 100L / 10L)));
        digits.add(String.valueOf((int) (cents % 10L)));

        for (int i = 0; i < digits.size(); i++) {
            if ("0".equals(digits.get(i))){
                digits.set(i, " ");
                if (i == digits.size()-1){
                    digits.set(i, "0");
                    digits.set(i-1, "￥");
                }
            }else {
                digits.set(i-1, "￥");
                break;
            }
        }

        return digits;
    }

    @Test
    void test39() {

        // 创建一个初始的BigDecimal值，这里使用2.0作为初始值
        BigDecimal sqrt2 = new BigDecimal("2");

        // 设置计算精度和舍入模式
        // 精度为100位，你可以根据需要调整
        int precision = 100;
        MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);

        sqrt2 = sqrt2.sqrt(mc);

        // 打印结果
        System.out.println("根号2的高精度值（前50位）： " + sqrt2.toString());
    }




    public static double calculatePi(int times) {
        int numIterations = times;
        double a = 1;
        double b = 1 / Math.sqrt(2);
        double t = 1 / 4.0;
        double p = 1;

        for (int i = 0; i < numIterations; i++) {
            double a_next = (a + b) / 2;
            double b_next = Math.sqrt(a * b);
            double t_next = t - p * (a - a_next) * (a - a_next);
            double p_next = 2 * p;
            a = a_next;
            b = b_next;
            t = t_next;
            p = p_next;
        }
        double pi = (a + b) * (a + b) / (4 * t);
        return pi;
    }




    @Test
    void test40() {
        long start = System.currentTimeMillis();
        int numIterations = 2;
        double pi = calculatePi(numIterations);
        long end = System.currentTimeMillis();
        System.out.println("圆周率近似值: " + pi);
        System.out.println("圆周率似真值: " + "3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706798");
        System.out.println("计算用时:" + (end - start) + "ms");
    }

    public static BigDecimal calculatePiHighRite(int times) {
        int numIterations = times;
        int precision = 90000;
        RoundingMode halfUp = RoundingMode.HALF_UP;
        MathContext mathContext = new MathContext(precision, halfUp);


        BigDecimal a = new BigDecimal("1", mathContext);

        BigDecimal two = new BigDecimal("2", mathContext);

        BigDecimal four = new BigDecimal("4", mathContext);
        TextReader.logWriter("求根:" + "根号2");
        System.out.println("求根:" + "根号2");
        BigDecimal sqrt2 = new BigDecimal("2", mathContext).sqrt(mathContext);
        System.out.println("求根answer:" + "根号2");
        TextReader.logWriter("求根answer:" + "根号2");
        BigDecimal b = a.divide(sqrt2, mathContext);

        BigDecimal t = new BigDecimal("0.25", mathContext);

        BigDecimal p = new BigDecimal("1", mathContext);
        BigDecimal pi = new BigDecimal("1");

        for (int i = 0; i < numIterations; i++) {
            TextReader.logWriter("执行遍历:" + i);
            System.out.println("执行遍历:" + i);
            BigDecimal a_next = (a.add(b)).divide(two, mathContext);
            BigDecimal b_next = a.multiply(b).sqrt(mathContext);
            BigDecimal t_next = t.subtract(p.multiply(a.subtract(a_next).multiply(a.subtract(a_next))));
            BigDecimal p_next = two.multiply(p);

            a = a_next;
            b = b_next;
            t = t_next;
            p = p_next;
            if (i%5 == 0){
                TextReader.logWriter("第" + i + "次迭代结果 ");
                System.out.print("第" + i + "次迭代结果 ");
                pi = a.add(b).multiply(a.add(b)).divide(four.multiply(t), mathContext);
                TextReader.logWriter(pi.toString());
                System.out.println(pi);
            }
        }
        pi = a.add(b).multiply(a.add(b)).divide(four.multiply(t), mathContext);
        return pi;
    }

    @Test
    void test40_1() {
        long start = System.currentTimeMillis();
        int numIterations = 15;
        BigDecimal pi = calculatePiHighRite(numIterations);
        long end = System.currentTimeMillis();
        System.out.println("圆周率近似值: " + pi);
        TextReader.piWriter(pi, numIterations);
        System.out.println("圆周率似真值: " + "3.141592653589793238462643383279502884197169399375105820974944592307816406286208998628034825342117067982148086513282306647093844609550582231725359408128481117450284102701938521105559644622948954930381964428810975665933446128475648233786783165271201909145648566923460348610454326648213393607260249141273724587006606315588174881520920962829254091715364367892590360011330530548820466521384146951941511609433057270365759591953092186117381932611793105118548074462379962749567351885752724891227938183011949129833673362440656643086021394946395224737190702179860943702770539217176293176752384674818467669405132000568127145263560827785771342757789609173637178721468440901224953430146549585371050792279689258923542019956112129021960864034418159813629774771309960518707211349999998372978049951059731732816096318595024459455346908302642522308253344685035261931188171010003137838752886587533208381420617177669147303598253490428755468731159562863882353787593751957781857780532171226806613001927876611195909216420198938095257201065485863278865936153381827968230301952035301852968995773622599413891249721775283479131515574857242454150695950829533116861727855889075098381754637464939319255060400927701671139009848824012858361603563707660104710181942955596198946767837449448255379774726847104047534646208046684259069491293313677028989152104752162056966024058038150193511253382430035587640247496473263914199272604269922796782354781636009341721641219924586315030286182974555706749838505494588586926995690927210797509302955321165344987202755960236480665499119881834797753566369807426542527862551818417574672890977772793800081647060016145249192173217214772350141441973568548161361157352552133475741849468438523323907394143334547762416862518983569485562099219222184272550254256887671790494601653466804988627232791786085784383827967976681454100953883786360950680064225125205117392984896084128488626945604241965285022210661186306744278622039194945047123713786960956364371917287467764657573962413890865832645995813390478027590099465764078951269468398352595709825822620522489407726719478268482601476990902640136394437455305068203496252451749399651431429809190659250937221696461515709858387410597885959772975498930161753928468138268683868942774155991855925245953959431049972524680845987273644695848653836736222626099124608051243884390451244136549762780797715691435997700129616089441694868555848406353422072225828488648158456028506016842739452267467678895252138522549954666727823986456596116354886230577456498035593634568174324112515076069479451096596094025228879710893145669136867228748940560101503308617928680920874760917824938589009714909675985261365549781893129784821682998948722658804857564014270477555132379641451523746234364542858444795265867821051141354735739523113427166102135969536231442952484937187110145765403590279934403742007310578539062198387447808478489683321445713868751943506430218453191048481005370614680674919278191197939952061419663428754440643745123718192179998391015919561814675142691239748940907186494231961567945208095146550225231603881930142093762137855956638937787083039069792077346722182562599661501421503068038447734549202605414665925201497442850732518666002132434088190710486331734649651453905796268561005508106658796998163574736384052571459102897064140110971206280439039759515677157700420337869936007230558763176359421873125147120532928191826186125867321579198414848829164470609575270695722091756711672291098169091528017350671274858322287183520935396572512108357915136988209144421006751033467110314126711136990865851639831501970165151168517143765761835155650884909989859982387345528331635507647918535893226185489632132933089857064204675259070915481416549859461637180270981994309924488957571282890592323326097299712084433573265489382391193259746366730583604142813883032038249037589852437441702913276561809377344403070746921120191302033038019762110110044929321516084244485963766983895228684783123552658213144957685726243344189303968642624341077322697802807318915441101044682325271620105265227211166039666557309254711055785376346682065310989652691862056476931257058635662018558100729360659876486117910453348850346113657686753249441668039626579787718556084552965412665408530614344431858676975145661406800700237877659134401712749470420562230538994561314071127000407854733269939081454664645880797270826683063432858785698305235808933065757406795457163775254202114955761581400250126228594130216471550979259230990796547376125517656751357517829666454779174501129961489030463994713296210734043751895735961458901938971311179042978285647503203198691514028708085990480109412147221317947647772622414254854540332157185306142288137585043063321751829798662237172159160771669254748738986654949450114654062843366393790039769265672146385306736096571209180763832716641627488880078692560290228472104031721186082041900042296617119637792133757511495950156604963186294726547364252308177036751590673502350728354056704038674351362222477158915049530984448933309634087807693259939780541934144737744184263129860809988868741326047215695162396586457302163159819319516735381297416772947867242292465436680098067692823828068996400482435403701416314965897940924323789690706977942236250822168895738379862300159377647165122893578601588161755782973523344604281512627203734314653197777416031990665541876397929334419521541341899485444734567383162499341913181480927777103863877343177207545654532207770921201905166096280490926360197598828161332316663652861932668633606273567630354477628035045077723554710585954870279081435624014517180624643626794561275318134078330336254232783944975382437205835311477119926063813346776879695970309833913077109870408591337464144282277263465947047458784778720192771528073176790770715721344473060570073349243693113835049316312840425121925651798069411352801314701304781643788518529092854520116583934196562134914341595625865865570552690496520985803385072242648293972858478316305777756068887644624824685792603953527734803048029005876075825104747091643961362676044925627420420832085661190625454337213153595845068772460290161876679524061634252257719542916299193064553779914037340432875262888963995879475729174642635745525407909145135711136941091193932519107602082520261879853188770584297259167781314969900901921169717372784768472686084900337702424291651300500516832336435038951702989392233451722013812806965011784408745196012122859937162313017114448464090389064495444006198690754851602632750529834918740786680881833851022833450850486082503930213321971551843063545500766828294930413776552793975175461395398468339363830474611996653858153842056853386218672523340283087112328278921250771262946322956398989893582116745627010218356462201349671518819097303811980049734072396103685406643193950979019069963955245300545058068550195673022921913933918568034490398205955100226353536192041994745538593810234395544959778377902374216172711172364343543947822181852862408514006660443325888569867054315470696574745855033232334210730154594051655379068662733379958511562578432298827372319898757141595781119635833005940873068121602876496286744604774649159950549737425626901049037781986835938146574126804925648798556145372347867330390468838343634655379498641927056387293174872332083760112302991136793862708943879936201629515413371424892830722012690147546684765357616477379467520049075715552781965362132392640616013635815590742202020318727760527721900556148425551879253034351398442532234157623361064250639049750086562710953591946589751413103482276930624743536325691607815478181152843667957061108615331504452127473924544945423682886061340841486377670096120715124914043027253860764823634143346235189757664521641376796903149501910857598442391986291642193994907236234646844117394032659184044378051333894525742399508296591228508555821572503107125701266830240292952522011872676756220415420516184163484756516999811614101002996078386909291603028840026910414079288621507842451670908700069928212066041837180653556725253256753286129104248776182582976515795984703562226293486003415872298053498965022629174878820273420922224533985626476691490556284250391275771028402799806636582548892648802545661017296702664076559042909945681506526530537182941270336931378517860904070866711496558343434769338578171138645587367812301458768712660348913909562009939361031029161615288138437909904231747336394804575931493140529763475748119356709110137751721008031559024853090669203767192203322909433467685142214477379393751703443661991040337511173547191855046449026365512816228824462575916333039107225383742182140883508657391771509682887478265699599574490661758344137522397096834080053559849175417381883999446974867626551658276584835884531427756879002909517028352971634456212964043523117600665101241200659755851276178583829204197484423608007193045761893234922927965019875187212726750798125547095890455635792122103334669749923563025494780249011419521238281530911407907386025152274299581807247162591668545133312394804947079119153267343028244186041426363954800044800267049624820179289647669758318327131425170296923488962766844032326092752496035799646925650493681836090032380929345958897069536534940603402166544375589004563288225054525564056448246515187547119621844396582533754388569094113031509526179378002974120766514793942590298969594699556576121865619673378623625612521632086286922210327488921865436480229678070576561514463204692790682120738837781423356282360896320806822246801224826117718589638140918390367367222088832151375560037279839400415297002878307667094447456013455641725437090697939612257142989467154357846878861444581231459357198492252847160504922124247014121478057345510500801908699603302763478708108175450119307141223390866393833952942578690507643100638351983438934159613185434754649556978103829309716465143840700707360411237359984345225161050702705623526601276484830840761183013052793205427462865403603674532865105706587488225698157936789766974220575059683440869735020141020672358502007245225632651341055924019027421624843914035998953539459094407046912091409387001264560016237428802109276457931065792295524988727584610126483699989225695968815920560010165525637567856672279661988578279484885583439751874454551296563443480396642055798293680435220277098429423253302257634180703947699415979159453006975214829336655566156787364005366656416547321704390352132954352916941459904160875320186837937023488868947915107163785290234529244077365949563051007421087142613497459561513849871375704710178795731042296906667021449863746459528082436944578977233004876476524133907592043401963403911473202338071509522201068256342747164602433544005152126693249341967397704159568375355516673027390074972973635496453328886984406119649616277344951827369558822075735517665158985519098666539354948106887320685990754079234240230092590070173196036225475647894064754834664776041146323390565134330684495397907090302346046147096169688688501408347040546074295869913829668246818571031887906528703665083243197440477185567893482308943106828702722809736248093996270607472645539925399442808113736943388729406307926159599546262462970706259484556903471197299640908941805953439325123623550813494900436427852713831591256898929519642728757394691427253436694153236100453730488198551706594121735246258954873016760029886592578662856124966552353382942878542534048308330701653722856355915253478445981831341129001999205981352205117336585640782648494276441137639386692480311836445369858917544264739988228462184490087776977631279572267265556259628254276531830013407092233436577916012809317940171859859993384923549564005709955856113498025249906698423301735035804408116855265311709957089942732870925848789443646005041089226691783525870785951298344172953519537885534573742608590290817651557803905946408735061232261120093731080485485263572282576820341605048466277504500312620080079980492548534694146977516493270950493463938243222718851597405470214828971117779237612257887347718819682546298126868581705074027255026332904497627789442362167411918626943965067151577958675648239939176042601763387045499017614364120469218237076488783419689686118155815873606293860381017121585527266830082383404656475880405138080163363887421637140643549556186896411228214075330265510042410489678352858829024367090488711819090949453314421828766181031007354770549815968077200947469613436092861484941785017180779306810854690009445899527942439813921350558642219648349151263901280383200109773868066287792397180146134324457264009737425700735921003154150893679300816998053652027600727749674584002836240534603726341655425902760183484030681138185510597970566400750942608788573579603732451414678670368809880609716425849759513806930944940151542222194329130217391253835591503100333032511174915696917450271494331515588540392216409722910112903552181576282328318234254832611191280092825256190205263016391147724733148573910777587442538761174657867116941477642144111126358355387136101102326798775641024682403226483464176636980663785768134920453022408197278564719839630878154322116691224641591177673225326433568614618654522268126887268445968442416107854016768142080885028005414361314623082102594173756238994207571362751674573189189456283525704413354375857534269869947254703165661399199968262824727064133622217892390317608542894373393561889165125042440400895271983787386480584726895462438823437517885201439560057104811949884239060613695734231559079670346149143447886360410318235073650277859089757827273130504889398900992391350337325085598265586708924261242947367019390772713070686917092646254842324074855036608013604668951184009366860954632500214585293095000090715105823626729326453738210493872499669933942468551648326113414611068026744663733437534076429402668297386522093570162638464852851490362932019919968828517183953669134522244470804592396602817156551565666111359823112250628905854914509715755390024393153519090210711945730024388017661503527086260253788179751947806101371500448991721002220133501310601639154158957803711779277522597874289191791552241718958536168059474123419339842021874564925644346239253195313510331147639491199507285843065836193536932969928983791494193940608572486396883690326556436421664425760791471086998431573374964883529276932822076294728238153740996154559879825989109371712621828302584811238901196822142945766758071865380650648702613389282299497257453033283896381843944770779402284359883410035838542389735424395647555684095224844554139239410001620769363684677641301781965937997155746854194633489374843912974239143365936041003523437770658886778113949861647874714079326385873862473288964564359877466763847946650407411182565837887845485814896296127399841344272608606187245545236064315371011274680977870446409475828034876975894832824123929296058294861919667091895808983320121031843034012849511620353428014412761728583024355983003204202451207287253558119584014918096925339507577840006746552603144616705082768277222353419110263416315714740612385042584598841990761128725805911393568960143166828317632356732541707342081733223046298799280490851409479036887868789493054695570307261900950207643349335910602454508645362893545686295853131533718386826561786227363716975774183023986006591481616404944965011732131389574706208847480236537103115089842799275442685327797431139514357417221975979935968525228574526379628961269157235798662057340837576687388426640599099350500081337543245463596750484423528487470144354541957625847356421619813407346854111766883118654489377697956651727966232671481033864391375186594673002443450054499539974237232871249483470604406347160632583064982979551010954183623503030945309733583446283947630477564501500850757894954893139394489921612552559770143685894358587752637962559708167764380012543650237141278346792610199558522471722017772370041780841942394872540680155603599839054898572354674564239058585021671903139526294455439131663134530893906204678438778505423939052473136201294769187497519101147231528932677253391814660730008902776896311481090220972452075916729700785058071718638105496797310016787085069420709223290807038326345345203802786099055690013413718236837099194951648960075504934126787643674638490206396401976668559233565463913836318574569814719621084108096188460545603903845534372914144651347494078488442377217515433426030669883176833100113310869042193903108014378433415137092435301367763108491351615642269847507430329716746964066653152703532546711266752246055119958183196376370761799191920357958200759560530234626775794393630746305690108011494271410093913691381072581378135789400559950018354251184172136055727522103526803735726527922417373605751127887218190844900617801388971077082293100279766593583875890939568814856026322439372656247277603789081445883785501970284377936240782505270487581647032458129087839523245323789602984166922548964971560698119218658492677040395648127810217991321741630581055459880130048456299765112124153637451500563507012781592671424134210330156616535602473380784302865525722275304999883701534879300806260180962381516136690334111138653851091936739383522934588832255088706450753947395204396807906708680644509698654880168287434378612645381583428075306184548590379821799459968115441974253634439960290251001588827216474500682070419376158454712318346007262933955054823955713725684023226821301247679452264482091023564775272308208106351889915269288910845557112660396503439789627825001611015323516051965590421184494990778999200732947690586857787872098290135295661397888486050978608595701773129815531495168146717695976099421003618355913877781769845875810446628399880600616229848616935337386578773598336161338413385368421197893890018529569196780455448285848370117096721253533875862158231013310387766827211572694951817958975469399264219791552338576623167627547570354699414892904130186386119439196283887054367774322427680913236544948536676800000106526248547305586159899914017076983854831887501429389089950685453076511680333732226517566220752695179144225280816517166776672793035485154204023817460892328391703275425750867655117859395002793389592057668278967764453184040418554010435134838953120132637836928358082719378312654961745997056745071833206503455664403449045362756001125018433560736122276594927839370647842645676338818807565612168960504161139039063960162022153684941092605387688714837989559999112099164646441191856827700457424343402167227644558933012778158686952506949936461017568506016714535431581480105458860564550133203758645485840324029871709348091055621167154684847780394475697980426318099175642280987399876697323769573701580806822904599212366168902596273043067931653114940176473769387351409336183321614280214976339918983548487562529875242387307755955595546519639440182184099841248982623673771467226061633643296406335728107078875816404381485018841143188598827694490119321296827158884133869434682859006664080631407775772570563072940049294030242049841656547973670548558044586572022763784046682337985282710578431975354179501134727362577408021347682604502285157979579764746702284099956160156910890384582450267926594205550395879229818526480070683765041836562094555434613513415257006597488191634135955671964965403218727160264859304903978748958906612725079482827693895352175362185079629778514618843271922322381015874445052866523802253284389137527384589238442253547265309817157844783421582232702069028723233005386216347988509469547200479523112015043293226628272763217790884008786148022147537657810581970222630971749507212724847947816957296142365859578209083073323356034846531873029302665964501371837542889755797144992465403868179921389346924474198509733462679332107268687076806263991936196504409954216762784091466985692571507431574079380532392523947755744159184582156251819215523370960748332923492103451462643744980559610330799414534778457469999212859999939961228161521931488876938802228108300198601654941654261696858678837260958774567618250727599295089318052187292461086763995891614585505839727420980909781729323930106766386824040111304024700735085782872462713494636853181546969046696869392547251941399291465242385776255004748529547681479546700705034799958886769501612497228204030399546327883069597624936151010243655535223069061294938859901573466102371223547891129254769617600504797492806072126803922691102777226102544149221576504508120677173571202718024296810620377657883716690910941807448781404907551782038565390991047759414132154328440625030180275716965082096427348414695726397884256008453121406593580904127113592004197598513625479616063228873618136737324450607924411763997597461938358457491598809766744709300654634242346063423747466608043170126005205592849369594143408146852981505394717890045183575515412522359059068726487863575254191128887737176637486027660634960353679470269232297186832771739323619200777452212624751869833495151019864269887847171939664976907082521742336566272592844062043021411371992278526998469884770232382384005565551788908766136013047709843861168705231055314916251728373272867600724817298763756981633541507460883866364069347043720668865127568826614973078865701568501691864748854167915459650723428773069985371390430026653078398776385032381821553559732353068604301067576083890862704984188859513809103042359578249514398859011318583584066747237029714978508414585308578133915627076035639076394731145549583226694570249413983163433237897595568085683629725386791327505554252449194358912840504522695381217913191451350099384631177401797151228378546011603595540286440590249646693070776905548102885020808580087811577381719174177601733073855475800605601433774329901272867725304318251975791679296996504146070664571258883469797964293162296552016879730003564630457930884032748077181155533090988702550520768046303460865816539487695196004408482065967379473168086415645650530049881616490578831154345485052660069823093157776500378070466126470602145750579327096204782561524714591896522360839664562410519551052235723973951288181640597859142791481654263289200428160913693777372229998332708208296995573772737566761552711392258805520189887620114168005468736558063347160373429170390798639652296131280178267971728982293607028806908776866059325274637840539769184808204102194471971386925608416245112398062011318454124478205011079876071715568315407886543904121087303240201068534194723047666672174986986854707678120512473679247919315085644477537985379973223445612278584329684664751333657369238720146472367942787004250325558992688434959287612400755875694641370562514001179713316620715371543600687647731867558714878398908107429530941060596944315847753970094398839491443235366853920994687964506653398573888786614762944341401049888993160051207678103588611660202961193639682134960750111649832785635316145168457695687109002999769841263266502347716728657378579085746646077228341540311441529418804782543876177079043000156698677679576090996693607559496515273634981189641304331166277471233881740603731743970540670310967676574869535878967003192586625941051053358438465602339179674926784476370847497833365557900738419147319886271352595462518160434225372996286326749682405806029642114638643686422472488728343417044157348248183330164056695966886676956349141632842641497453334999948000266998758881593507357815195889900539512085351035726137364034367534714104836017546488300407846416745216737190483109676711344349481926268111073994825060739495073503169019731852119552635632584339099822498624067031076831844660729124874754031617969941139738776589986855417031884778867592902607004321266617919223520938227878880988633599116081923535557046463491132085918979613279131975649097600013996234445535014346426860464495862476909434704829329414041114654092398834443515913320107739441118407410768498106634724104823935827401944935665161088463125678529776973468430306146241803585293315973458303845541033701091676776374276210213701354854450926307190114731848574923318167207213727935567952844392548156091372812840633303937356242001604566455741458816605216660873874804724339121295587776390696903707882852775389405246075849623157436917113176134783882719416860662572103685132156647800147675231039357860689611125996028183930954870905907386135191459181951029732787557104972901148717189718004696169777001791391961379141716270701895846921434369676292745910994006008498356842520191559370370101104974733949387788598941743303178534870760322198297057975119144051099423588303454635349234982688362404332726741554030161950568065418093940998202060999414021689090070821330723089662119775530665918814119157783627292746156185710372172471009521423696483086410259288745799932237495519122195190342445230753513380685680735446499512720317448719540397610730806026990625807602029273145525207807991418429063884437349968145827337207266391767020118300464819000241308350884658415214899127610651374153943565721139032857491876909441370209051703148777346165287984823533829726013611098451484182380812054099612527458088109948697221612852489742555551607637167505489617301680961380381191436114399210638005083214098760459930932485102516829446726066613815174571255975495358023998314698220361338082849935670557552471290274539776214049318201465800802156653606776550878380430413431059180460680083459113664083488740800574127258670479225831912741573908091438313845642415094084913391809684025116399193685322555733896695374902662092326131885589158083245557194845387562878612885900410600607374650140262782402734696252821717494158233174923968353013617865367376064216677813773995100658952887742766263684183068019080460984980946976366733566228291513235278880615776827815958866918023894033307644191240341202231636857786035727694154177882643523813190502808701857504704631293335375728538660588890458311145077394293520199432197117164223500564404297989208159430716701985746927384865383343614579463417592257389858800169801475742054299580124295810545651083104629728293758416116253256251657249807849209989799062003593650993472158296517413579849104711166079158743698654122234834188772292944633517865385673196255985202607294767407261676714557364981210567771689348491766077170527718760119990814411305864557791052568430481144026193840232247093924980293355073184589035539713308844617410795916251171486487446861124760542867343670904667846867027409188101424971114965781772427934707021668829561087779440504843752844337510882826477197854000650970403302186255614733211777117441335028160884035178145254196432030957601869464908868154528562134698835544456024955666843660292219512483091060537720198021831010327041783866544718126039719068846237085751808003532704718565949947612424811099928867915896904956394762460842406593094862150769031498702067353384834955083636601784877106080980426924713241000946401437360326564518456679245666955100150229833079849607994988249706172367449361226222961790814311414660941234159359309585407913908720832273354957208075716517187659944985693795623875551617575438091780528029464200447215396280746360211329425591600257073562812638733106005891065245708024474937543184149401482119996276453106800663118382376163966318093144467129861552759820145141027560068929750246304017351489194576360789352855505317331416457050499644389093630843874484783961684051845273288403234520247056851646571647713932377551729479512613239822960239454857975458651745878771331813875295980941217422730035229650808917770506825924882232215493804837145478164721397682096332050830564792048208592047549985732038887639160199524091893894557676874973085695595801065952650303626615975066222508406742889826590751063756356996821151094966974458054728869363102036782325018232370845979011154847208761821247781326633041207621658731297081123075815982124863980721240786887811450165582513617890307086087019897588980745664395515741536319319198107057533663373803827215279884935039748001589051942087971130805123393322190346624991716915094854140187106035460379464337900589095772118080446574396280618671786101715674096766208029576657705129120990794430463289294730615951043090222143937184956063405618934251305726829146578329334052463502892917547087256484260034962961165413823007731332729830500160256724014185152041890701154288579920812198449315699905918201181973350012618772803681248199587707020753240636125931343859554254778196114293516356122349666152261473539967405158499860355295332924575238881013620234762466905581643896786309762736550472434864307121849437348530060638764456627218666170123812771562137974614986132874411771455244470899714452288566294244023018479120547849857452163469644897389206240194351831008828348024924908540307786387516591130287395878709810077271827187452901397283661484214287170553179654307650453432460053636147261818096997693348626407743519992868632383508875668359509726557481543194019557685043724800102041374983187225967738715495839971844490727914196584593008394263702087563539821696205532480321226749891140267852859967340524203109179789990571882194939132075343170798002373659098537552023891164346718558290685371189795262623449248339249634244971465684659124891855662958932990903523923333364743520370770101084388003290759834217018554228386161721041760301164591878053936744747205998502358289183369292233732399948043710841965947316265482574809948250999183300697656936715968936449334886474421350084070066088359723503953234017958255703601693699098867113210979889707051728075585519126993067309925070407024556850778679069476612629808225163313639952117098452809263037592242674257559989289278370474445218936320348941552104459726188380030067761793138139916205806270165102445886924764924689192461212531027573139084047000714356136231699237169484813255420091453041037135453296620639210547982439212517254013231490274058589206321758949434548906846399313757091034633271415316223280552297297953801880162859073572955416278867649827418616421878988574107164906919185116281528548679417363890665388576422915834250067361245384916067413734017357277995634104332688356950781493137800736235418007061918026732855119194267609122103598746924117283749312616339500123959924050845437569850795704622266461900010350049018303415354584283376437811198855631877779253720116671853954183598443830520376281944076159410682071697030228515225057312609304689842343315273213136121658280807521263154773060442377475350595228717440266638914881717308643611138906942027908814311944879941715404210341219084709408025402393294294549387864023051292711909751353600092197110541209668311151632870542302847007312065803262641711616595761327235156666253667271899853419989523688483099930275741991646384142707798870887422927705389122717248632202889842512528721782603050099451082478");
        System.out.println("计算用时:" + (end - start) + "ms");
        TextReader.logWriter("计算用时:" + (end - start) + "ms");

    }

    @Test
    void test41() {
        long start = System.currentTimeMillis();
        String expression = "1+(3-1)*(130.44-82.5)/(130.442-130.44*0.5)";
        double evaluate = NumberCalUtil.evaluate(expression);
        long end = System.currentTimeMillis();
        System.out.println("表达式 :" + expression + "   用时： " + (end - start) + "ms");
        System.out.println("计算结果 :" + evaluate);

    }

    @Test
    void test42() {
        int i = TextReader.fileCharContrast(300);
        System.out.println("精度比较计算结果 :" + i);

    }


    @Test
    void test44() {
        String money = "490.106";
        boolean number = NumberUtil.isNumber(money);
        System.out.println(number);
        Float value = Float.valueOf(money);
        value = value * 10000;
        System.out.println(String.format("%.2f", value));

    }


    @Test
    void test22() {
        String idStr = "1844620461197062146\n" +
                "1844620461197062145\n";
        String[] split = idStr.split("\n");
        String formatStr = "\'";
        String join = String.join(formatStr + ", " + formatStr, split);
        String endStr = formatStr + join + formatStr;
        System.out.println(endStr);


    }

    @Test
    void test23() {
        String idStr = "00134748,00134745,00134747,20016887,00135029,125";
        String[] split = idStr.split(",");
        String formatStr = "\'";
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
        }
        String join = String.join(formatStr + ", " + formatStr, split);
        String endStr = formatStr + join + formatStr;
        System.out.println(endStr);


    }

    @Test
    void test24() {
        LocalDate firstDayOfYear = LocalDate.now().withMonth(1).withDayOfMonth(1);

        // 如果你需要时区信息，可以转换为ZonedDateTime
        ZonedDateTime zdt = firstDayOfYear.atStartOfDay(ZoneId.systemDefault());

        // 然后转换为Date（如果需要的话）
        Date date = Date.from(zdt.toInstant());

        // 打印结果
        System.out.println("本年度第一天是：" + date);


    }



}