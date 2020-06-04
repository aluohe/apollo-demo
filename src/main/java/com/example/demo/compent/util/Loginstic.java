package com.example.demo.compent.util;

import cn.com.antcloud.api.AntFinTechApiClient;
import cn.com.antcloud.api.AntFinTechProfile;
import cn.com.antcloud.api.shuziwuliu.v1_0_0.request.CreatePlatformDidRequest;
import cn.com.antcloud.api.shuziwuliu.v1_0_0.response.CreatePlatformDidResponse;

/**
 * @author aluohe
 * @className Loginstic
 * @projectName demo
 * @date 2020/4/1 10:18
 * @description
 * @modified_by
 * @version:
 */
public class Loginstic {

    private static final String APP_KEY = "LTAI4FkgcvKyxJDbWT9poUHp";
    private static final String APP_SECRET = "5bW2kjRgypgzfFCh5SiB2x9ppcIt0y";


    public static void main(String[] args) {
        try {
            // 初始化客户端
            AntFinTechProfile profile = AntFinTechProfile.getProfile(
                    "https://prodapigw.cloud.alipay.com",
                    APP_KEY,
                    APP_SECRET
            );
            AntFinTechApiClient client = new AntFinTechApiClient(profile);

            // 构建请求
            CreatePlatformDidRequest request = new CreatePlatformDidRequest();
            //企业名称
            request.setEpCertName("绥德县卡漠物流有限公司");

            //todo 参考环境配置章节，测试环境的实例 ID 为 digital-logistic-sit，
            // 线上环境的实例 ID 为 digital-logistic-prod
            request.setProductInstanceId("digital-logistic-sit");
            //默认配置 不做处理
            request.setRegionName("CN-HANGZHOU-FINANCE");

            //企业信用号码
            request.setEpCertNo("91610826MA7094NJ1J");
            //扩展字段
            request.setExtensionInfo("123");
            //企业法人姓名
            request.setLegalPersonCertName("钱宝霖");
            //企业法人身份证号码
            request.setLegalPersonCertNo("110101190103074692");

            // 发送请求，并且获取响应结果
            CreatePlatformDidResponse response = client.execute(request);

            System.out.println(response.getDid());
            System.out.println(response.getResultCode());
            System.out.println(response.getResultMsg());
            System.out.println(response.getReqMsgId());
            System.out.println(response.getSecrets());

            //did:mychain:83a51fd622d93a89593f5835675920766ba90eff1a425646ccc1cfefc87b9b1f
            //OK
            //success
            //810bec7508ad42bab782fcc572305d89

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}