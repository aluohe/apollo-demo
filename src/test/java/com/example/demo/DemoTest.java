package com.example.demo;

import cn.com.antcloud.api.AntFinTechApiClient;
import cn.com.antcloud.api.AntFinTechProfile;
import cn.com.antcloud.api.shuziwuliu.v1_0_0.request.*;
import cn.com.antcloud.api.shuziwuliu.v1_0_0.response.*;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author aluohe
 * @className DemoTest
 * @projectName demo
 * @date 2020/3/4 11:36
 * @description
 * @modified_by
 * @version:
 */
public class DemoTest {

    private static final String APP_KEY = "LTAI4FkgcvKyxJDbWT9poUHp";
    private static final String APP_SECRET = "5bW2kjRgypgzfFCh5SiB2x9ppcIt0y";

    @Test
    void contextLoads() {


        for (int i = 0; i < 24; i++) {

            Calendar instance = Calendar.getInstance();

            instance.set(Calendar.HOUR, i);

            System.out.println(dataFormat(instance.getTime(), "yyyyMMddHH"));


        }


    }

    @Test
    public void testCon(){

        Config config = ConfigService.getAppConfig();
        System.out.println(config.getProperty("sofa.alipay.glmapper.name", "kll"));
    }

    private String dataFormat(Date data, String format) {

        SimpleDateFormat format1 = new SimpleDateFormat(format);

        return format1.format(data);


    }

    @Test
    public void testOwner() {

        try {
            // 初始化客户端
            AntFinTechProfile profile = AntFinTechProfile.getProfile(
                    "https://prodapigw.cloud.alipay.com",
                    APP_KEY,
                    APP_SECRET
            );
            AntFinTechApiClient client = new AntFinTechApiClient(profile);

            // 构建请求
            CreateConsignorDisRequest request = new CreateConsignorDisRequest();
            request.setCheckAll(false);
            request.setEpCertName("宁夏晨宁中运输有限公司");
            request.setEpCertNo("91640300MA770M8P1C");
            request.setExtensionInfo("123 ");
            request.setLegalPersonCertName("苏克中");
            request.setLegalPersonCertNo("642127198003061830");
            request.setPlatformDid("did:mychain:83a51fd622d93a89593f5835675920766ba90eff1a425646ccc1cfefc87b9b1f");
            request.setProductInstanceId("digital-logistic-sit");
            request.setRegionName("CN-HANGZHOU-FINANCE");

            // 发送请求，并且获取响应结果
            CreateConsignorDisResponse response = client.execute(request);
            Result result = new Result(response);
            System.out.println(result);

            //Result(code=OK, msg=success,
            // did=did:mychain:6b864d11c7884426c622aae31ba8ca47bdd8b5efc77e547b62f0307430e8b363,
            // msgID=749cdd357fd74649bd2f33e84a11de68)

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testDriver() {
        try {
            // 初始化客户端
            AntFinTechProfile profile = AntFinTechProfile.getProfile(
                    "https://prodapigw.cloud.alipay.com",
                    APP_KEY,
                    APP_SECRET
            );
            AntFinTechApiClient client = new AntFinTechApiClient(profile);

            // 构建请求
            CreateDriverDisRequest request = new CreateDriverDisRequest();
            request.setCertNo("142601197106177618");
            request.setExtensionInfo("123");
            request.setMobile("13734104110");
            request.setName("段爱国");
            request.setPlatformDid("did:mychain:83a51fd622d93a89593f5835675920766ba90eff1a425646ccc1cfefc87b9b1f");
            request.setProductInstanceId("digital-logistic-sit");
            request.setRegionName("CN-HANGZHOU-FINANCE");

            // 发送请求，并且获取响应结果
            CreateDriverDisResponse response = client.execute(request);
            System.out.println(new Result(response));
            //Result(code=OK, msg=success,
            // did=did:mychain:90f4b62abf28df6885d3072d0761e28fd625e808e0024a9da701b8f725b3e2a8,
            // msgID=f84f01320074497586470bcf24694c11)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGoods() {

        try {
            // 初始化客户端
            AntFinTechProfile profile = AntFinTechProfile.getProfile(
                    "https://prodapigw.cloud.alipay.com",
                    APP_KEY,
                    APP_SECRET
            );
            AntFinTechApiClient client = new AntFinTechApiClient(profile);

            // 构建请求
            CreateCargoOrderRequest request = new CreateCargoOrderRequest();
            request.setAllFreight("1098.87");
            request.setCargoBusinessCode("123");
            request.setCargoCode("123");
            request.setCargoInsuranceMoney("123");
            request.setCargoName("香蕉 ");
            request.setCargoOrder("123123");
            request.setCargoType("水果 ");
            request.setCargoUnit("123");
            request.setCargoVolume("123");
            request.setPlatformDid("did:mychain:83a51fd622d93a89593f5835675920766ba90eff1a425646ccc1cfefc87b9b1f");
            request.setDeliveryPlace("上海 ");
            request.setLoadingPlace("河北 ");
            request.setConsignorDid("did:mychain:6b864d11c7884426c622aae31ba8ca47bdd8b5efc77e547b62f0307430e8b363");
            request.setStartTime(1566221266000L);
            request.setUserPhone("13133333333");
            request.setWeight("17.8KG");
            request.setProductInstanceId("digital-logistic-sit");
            request.setRegionName("CN-HANGZHOU-FINANCE");

            // 发送请求，并且获取响应结果
            CreateCargoOrderResponse response = client.execute(request);

            System.out.println(new Result(response));
            //Result(code=OK, msg=success, did=null,
            // msgID=cd6fdfd15aad40a3b7d09b072d0f4478,
            // texCode=bdb7b12f387926bf273e0de89b2dc958f316ca2156fdee5820635d30305172a5)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testOrders() {
        try {
            // 初始化客户端
            AntFinTechProfile profile = AntFinTechProfile.getProfile(
                    "https://prodapigw.cloud.alipay.com",
                    APP_KEY,
                    APP_SECRET
            );
            AntFinTechApiClient client = new AntFinTechApiClient(profile);

            // 构建请求
            CreateWaybillOrderRequest request = new CreateWaybillOrderRequest();
            request.setAccountId("did:mychain:90f4b62abf28df6885d3072d0761e28fd625e808e0024a9da701b8f725b3e2a8");
            request.setAllFreight("1000.28 ");
            request.setBackFee("10.12 ");
            request.setCargoBusinessCode("123");
            request.setCargoCode("123");
            request.setCargoInsuranceMoney("123");
            request.setCargoOrder("123123");
            request.setCargoUnit("123");
            request.setCargoVolume("123");
            request.setCargoWeight("123");
            request.setCartBadgeColor("黄色 ");
            request.setCartBadgeNo("沪B123123 ");
            request.setConsignorFreightAmount("123");
            request.setCreatedTime(1566221266000L);
            request.setDrawee("企业A ");
            request.setDraweeTaxNo("123123");
            request.setDriverDid("did:mychain:90f4b62abf28df6885d3072d0761e28fd625e808e0024a9da701b8f725b3e2a8");
            request.setDriverName("小王");
            request.setEndCityCode("0527 ");
            request.setEndCityName("宿迁市 ");
            request.setEndCountyCode("0527 ");
            request.setEndCountyName("宿豫区 ");
            request.setEndDivisionCode("320101000000");
            request.setEndProvinceCode("0527");
            request.setEndProvinceName("江苏省 ");
            request.setFreightIncr("100");
            request.setGoodsAmount(1L);
            request.setGoodsAmountType("箱");
            request.setGoodsName("水果");
            request.setIdCard("3101101010101010101");
            request.setLossFee("11.12 ");
            request.setMobileNo("13717746456");
            request.setPartnerId("123123");
            request.setPlatformDid("did:mychain:83a51fd622d93a89593f5835675920766ba90eff1a425646ccc1cfefc87b9b1f");
            request.setPrepayments("100.28 ");
            request.setPrepaymentsBuyEtc("100");
            request.setPrepaymentsBuyGas("120.12 ");
            request.setPrepaymentsBuyOil("19.8 ");
            request.setPrepaymentsEtccard("100");
            request.setPrepaymentsOilcard("20.12");
            request.setPresentAmountOil("19.87 ");
            request.setStartCityCode("025");
            request.setStartCityName("南京");
            request.setStartCountyCode("025");
            request.setStartCountyName("雨花台区 ");
            request.setStartDivisionCode("320101000000");
            request.setStartProvinceCode("025");
            request.setStartProvinceName("江苏省 ");
            request.setStartTime(1566221266000L);
            request.setTaxWaybillId("123123");
            request.setUnitPrice("10.18 ");
            request.setProductInstanceId("digital-logistic-sit");
            request.setRegionName("CN-HANGZHOU-FINANCE");

            // 发送请求，并且获取响应结果
            CreateWaybillOrderResponse response = client.execute(request);

            System.out.println(new Result(response));
            //Result(code=OK, msg=success, did=null,
            // msgID=761a424a34d24f58ad8148bec4a7514a,
            // texCode=d444055882bf3209ef223ea1d6b41d540b8a33dfbfbd8fb861ee974407e66647)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testOrderEnd() {
        try {
            // 初始化客户端
            AntFinTechProfile profile = AntFinTechProfile.getProfile(
                    "https://prodapigw.cloud.alipay.com",
                    APP_KEY,
                    APP_SECRET
            );
            AntFinTechApiClient client = new AntFinTechApiClient(profile);

            // 构建请求
            SaveWaybillOrderRequest request = new SaveWaybillOrderRequest();
            request.setAccountId("did:mychain:90f4b62abf28df6885d3072d0761e28fd625e808e0024a9da701b8f725b3e2a8");
            request.setAllFreight("1000.123 ");
            request.setBackFee("213.321");
            request.setCargoBusinessCode("123");
            request.setCargoCode("123");
            request.setCargoInsuranceMoney("123");
            request.setCargoOrder("123123");
            request.setCargoUnit("123");
            request.setCargoVolume("123");
            request.setCargoWeight("123");
            request.setCartBadgeColor("黄色");
            request.setCartBadgeNo("沪B123123 ");
            request.setConsignorFreightAmount("123");
            request.setCreatedTime(1566221266000L);
            request.setDrawee("企业B ");
            request.setDraweeTaxNo("123123");
            request.setDriverDid("did:mychain:90f4b62abf28df6885d3072d0761e28fd625e808e0024a9da701b8f725b3e2a8");
            request.setDriverName("小李 ");
            request.setEndCityCode("0527");
            request.setEndCityName("宿迁市 ");
            request.setEndCountyCode("0527");
            request.setEndCountyName("宿城区 ");
            request.setEndDivisionCode("320101000000");
            request.setEndProvinceCode("0527");
            request.setEndProvinceName("江苏省 ");
            request.setEndTime(1566221266000L);
            request.setFreightIncr("100");
            request.setGoodsAmount("1");
            request.setGoodsAmountType("箱");
            request.setGoodsName("水果 ");
            request.setIdCard("310123123123123");
            request.setLossFee("421.21 ");
            request.setMobileNo("13789898989");
            request.setPartnerId("123");
            request.setPayCheck(false);
            request.setPlatformDid("did:mychain:83a51fd622d93a89593f5835675920766ba90eff1a425646ccc1cfefc87b9b1f");
            request.setPrepayments("100.12");
            request.setPrepaymentsBuyEtc("1000");
            request.setPrepaymentsBuyGas("100");
            request.setPrepaymentsBuyOil("100");
            request.setPrepaymentsEtccard("100");
            request.setPrepaymentsOilcard("100");
            request.setPresentAmountOil("100");
            request.setStartCityCode("025");
            request.setStartCityName("南京市 ");
            request.setStartCountyCode("025");
            request.setStartCountyName("雨花台区 ");
            request.setStartDivisionCode("320101000000");
            request.setStartProvinceCode("025");
            request.setStartProvinceName("江苏省 ");
            request.setStartTime(1566221266000L);
            request.setTaxWaybillId("123123");
            request.setUnitPrice("123.32");
            request.setProductInstanceId("digital-logistic-sit");
            request.setRegionName("CN-HANGZHOU-FINANCE");

            // 发送请求，并且获取响应结果
            SaveWaybillOrderResponse response = client.execute(request);

            System.out.println(new Result(response));

            //Result(code=OK, msg=success, did=null,
            // msgID=aceccbf42fff46ff9bf96067766f0fc2,
            // texCode=3b7f04f45e2d48425b2ebe5055c0ac06dbc85210c7ba9757eea1a8e3ecf6a463)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void orderClose() {
        try {
            // 初始化客户端
            AntFinTechProfile profile = AntFinTechProfile.getProfile(
                    "https://prodapigw.cloud.alipay.com",
                    APP_KEY,
                    APP_SECRET
            );
            AntFinTechApiClient client = new AntFinTechApiClient(profile);

            // 构建请求
            CloseWaybillOrderRequest request = new CloseWaybillOrderRequest();
            request.setPlatformDid("did:mychain:83a51fd622d93a89593f5835675920766ba90eff1a425646ccc1cfefc87b9b1f");
            request.setTaxWaybillId("123123");
            request.setProductInstanceId("digital-logistic-sit");
            request.setRegionName("CN-HANGZHOU-FINANCE");

            // 发送请求，并且获取响应结果
            CloseWaybillOrderResponse response = client.execute(request);

            System.out.println(new Result(response));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOrderend() {
        try {
            // 初始化客户端
            AntFinTechProfile profile = AntFinTechProfile.getProfile(
                    "https://prodapigw.cloud.alipay.com",
                    APP_KEY,
                    APP_SECRET
            );
            AntFinTechApiClient client = new AntFinTechApiClient(profile);

            // 构建请求
            CreateWaybillPayRequest request = new CreateWaybillPayRequest();
            request.setArriveTime(1566221266000L);
            request.setBankSn("123123123");
            request.setPayAmount("1000.32 ");
            request.setPayBankCardNo("6226123123123123");
            request.setPayBankName("网商银行 ");
            request.setPayCheck(true);
            request.setPayDid("did:mychain:b6db5d89ab1dac07a8c2a6201db3fff529f1ce7d78f58888fd791aec84dc8a8e ");
            request.setPayName("企业A ");
            request.setPayTime(1566221266000L);
            request.setPayTypeNew("1 ");
            request.setPayWay("支付宝 ");
            request.setPlatformDid("did:mychain:1b8b30228ec99cb5de99b3365538f3c505b274e0bef98fb9cee7aabd8f5f47eb ");
            request.setPosInfoId("123123");
            request.setRealPayBank("网商银行 ");
            request.setRealPayBankCardNo("6226123123123123");
            request.setRealPayName("企业A ");
            request.setRecvBankCardNo("6226123123123123");
            request.setRecvBankName("网商银行 ");
            request.setRecvDid("did:mychain:b5db2d89ab1dac07a8c2a6201db3fff529f1ce7d78f58888fd791aec84dc8a8e ");
            request.setRecvName("小李 ");
            request.setWaybillId("123123");
            request.setProductInstanceId("digital-logistic-sit");
            request.setRegionName("CN-HANGZHOU-FINANCE");

            // 发送请求，并且获取响应结果
            CreateWaybillPayResponse response = client.execute(request);

            System.out.println(new Result(response));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

@Data
@Accessors(chain = true)
class Result {

    private String code;

    private String msg;

    private String did;

    private String msgID;

    private String texCode;

    public Result(CreateConsignorDisResponse response) {
        this.code = response.getResultCode();
        this.msg = response.getResultMsg();
        this.did = response.getDid();
        this.msgID = response.getReqMsgId();
    }

    public Result(CreateDriverDisResponse response) {
        this.code = response.getResultCode();
        this.msg = response.getResultMsg();
        this.did = response.getDid();
        this.msgID = response.getReqMsgId();
    }

    public Result(CreateCargoOrderResponse response) {
        this.code = response.getResultCode();
        this.msg = response.getResultMsg();
        this.texCode = response.getTxCode();
        this.msgID = response.getReqMsgId();
    }

    public Result(CreateWaybillOrderResponse response) {
        this.code = response.getResultCode();
        this.msg = response.getResultMsg();
        this.texCode = response.getTxCode();
        this.msgID = response.getReqMsgId();
    }

    public Result(CreateWaybillPayResponse response) {
        this.code = response.getResultCode();
        this.msg = response.getResultMsg();
        this.texCode = response.getTxCode();
        this.msgID = response.getReqMsgId();
    }

    public Result(SaveWaybillOrderResponse response) {
        this.code = response.getResultCode();
        this.msg = response.getResultMsg();
        this.texCode = response.getTxCode();
        this.msgID = response.getReqMsgId();
    }

    public Result(CloseWaybillOrderResponse response) {
        this.code = response.getResultCode();
        this.msg = response.getResultMsg();
        this.texCode = response.getTxCode();
        this.msgID = response.getReqMsgId();
    }


}