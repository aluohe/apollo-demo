package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.example.demo.compent.AuroraProperties;
import com.example.demo.compent.MailCompent;
import com.example.demo.entity.*;
import com.example.demo.manager.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    SysDayDataService sysDayDataService;
    @Autowired
    SysHourDataService sysHourDataService;

    @Autowired
    MailCompent mailCompent;
    @Autowired
    AuroraProperties auroraProperties;
    @Autowired
    AreasService areasService;

    @Autowired
    SysVehicleGroupService sysVehicleGroupService;

    @Test
    public void test(){


      /*  IPage<SysVehicleGroup> page = sysVehicleGroupService.page(new Page<>(0, 10),null);

        for (SysVehicleGroup record : page.getRecords()) {
            System.out.println(record);
        }
*/

        int count = sysVehicleGroupService.count();
        int page = getPage(count, 1000);
        System.out.println(count+"\t"+page);
        Page<SysVehicleGroup> Ipage = new Page<>(1,1000);
        IPage<SysVehicleGroup> groupIPage = sysVehicleGroupService.page(Ipage,null);
        for (SysVehicleGroup record : groupIPage.getRecords()) {
            System.out.println(record);
        }

    }





    private int getPage(int count, Integer limit) {
        int yushu = count % limit;
        int pages = count / limit;
        if (pages == 0 && yushu > 0) {
            pages = 1;
        } else if (pages > 0 && yushu > 0) {
            pages = pages + 1;
        }
        return pages;
    }







    @Test
    public void testArea(){

        Areas one = areasService.getOne(null);
        System.out.println(one);
    }


    @Test
    public void testPor() {
        System.out.println(auroraProperties.getAPP_KEY());
        System.out.println(auroraProperties.getMASTER_SECRET());
    }

    @Test
    public void mail() {
        mailCompent.sendV2();
    }

    @Test
    void contextLoads() {


        for (int i = 0; i < 24; i++) {

            Calendar instance = Calendar.getInstance();

            instance.set(Calendar.HOUR, i);

//            System.out.println(dataFormat(instance.getTime(), "yyyyMMddHH"));
            Random random = new Random();
            sysHourDataService.save(new SysHourData()
                    .setAmount(new BigDecimal(random.nextInt(100)))
                    .setCompanyId(1)
                    .setUserId(1)
                    .setUserCount(random.nextInt(1000))
                    .setLoadingCount(random.nextInt(1000))
                    .setLoadingNumber(new BigDecimal(random.nextInt(1000)))
                    .setServiceFee(new BigDecimal(random.nextInt(1000)))
                    .setUnloadingNumber(new BigDecimal(random.nextInt(1000)))
                    .setUnloadingCount(random.nextInt(1000))
                    .setTimes(Integer.valueOf(dataFormat(instance.getTime(), "yyyyMMddHH")))
            );


        }


    }

    @Test
    public void testDay() {


        QueryWrapper<SysHourData> w = new QueryWrapper<>();
        w.likeRight("times", "20200304");

        List<SysHourData> list = sysHourDataService.list(w);
        if (list != null && list.size() > 0) {
            for (SysHourData sysHourData : list) {
                sysDayDataService.addDayData(sysHourData);
            }
        }


    }


    private String dataFormat(Date data, String format) {

        SimpleDateFormat format1 = new SimpleDateFormat(format);

        return format1.format(data);


    }


    @Test
    public void testPri() throws IOException {

        handleCode(baseUrl, "[class=provincetr]");


        boolean b = provinceService.saveBatch(list);
        System.out.println(b);


    }

    @Test
    public void testCity() throws IOException {
        handleCode(baseUrl, "[class=provincetr]");

        boolean b = citiesService.saveBatch(cities);

        System.out.println(b);

    }


    private String baseUrl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2019/index.html";

    static List<Province> list = new ArrayList<>();
    static List<Cities> cities = new ArrayList<>();
//    static List<Cities> cities = new ArrayList<>();
//    static List<Cities> cities = new ArrayList<>();
//    static List<Cities> cities = new ArrayList<>();
//    static List<Province> list = new ArrayList<>();

    @Autowired
    ProvinceService provinceService;
    @Autowired
    CitiesService citiesService;


    public void main(String[] args) throws Exception {

//        handleCode(baseUrl, "[class=provincetr]");

        for (Province name : list) {
            System.out.println(name);
        }

    }

    private static Map<Integer, String> codeMap = new HashMap<>();

    static {
        codeMap.put(0, "[class=citytr]");
        codeMap.put(1, "[class=countytr]");
        codeMap.put(2, "[class=towntr]");
        codeMap.put(3, "[class=villagetr]");

    }


    private void handleCode(String url, String css) throws IOException {
        Connection connect = Jsoup.connect(url);

        Elements select = connect.get().select(css);


        //省级
        for (Element element : select) {
            Elements a = element.getElementsByTag("a");

            for (Element elementPri : a) {
                int count = 0;
                String href = elementPri.attr("href");
                String text = elementPri.text();
                System.out.println(text + "\t" + href);

//                list.add(new BaseName().setCode(String.format("%s0000000000", href.replaceAll("(\\d+).html", "$1"))).setName(text));

                //市级
                String hrefUrl = baseUrl.replaceAll("(http://.+/)(.+\\.html)", "$1" + href);

                // citytr  countytr  towntr  villagetr
//                handleSubCodeV(hrefUrl, codeMap.get(0), href.replaceAll("(\\d+).html", "$1"));
                handleSubCode(hrefUrl, codeMap.get(count++), count, href.replaceAll("(\\d+).html", "$1"));
            }

        }
    }

    private void handleSubCodeV(String hrefUrl, String css, String count) throws IOException {
        Connection cityConnect = Jsoup.connect(hrefUrl);
        System.out.println(hrefUrl + "   " + css);
        //获取行数据
        Elements elements = cityConnect.get().select(css);

//        String ghUrl = hrefUrl;
//        int counts = count;
        for (int i = 0; i < elements.size(); i++) {
//            count = counts;
            Elements a = elements.get(i).getElementsByTag("a");

            if (a != null && a.size() > 0) {

                String code = a.get(0).text();
                String city = a.get(1).text();
                System.out.println(code + "\t" + city);
                cities.add(new Cities().setCode(code).setName(city).setProvinceCode(count));
            }
        }
    }

    private void handleSubCode(String hrefUrl, String css, int count, String codes) throws IOException {
        Connection cityConnect = Jsoup.connect(hrefUrl);
        System.out.println(hrefUrl + "   " + css);
        //获取行数据
        Elements elements = cityConnect.get().select(css);

        if (count == 4) {
            for (Element element : elements) {
                Elements td = element.getElementsByTag("td");
                System.out.println(td.get(0).text() + "   " + td.get(2).text());
//                list.add(new BaseName().setCode(td.get(0).text()).setName(td.get(2).text()));
            }
            System.out.println();
            return;
        }

        String ghUrl = hrefUrl;
        int counts = count;
        for (int i = 0; i < elements.size(); i++) {
            count = counts;
            Elements a = elements.get(i).getElementsByTag("a");

            if (a != null && a.size() > 0) {

                String code = a.get(0).text();
                String city = a.get(1).text();
                System.out.println(code + "\t" + city);
//                list.add(new BaseName().setName(code).setName(city));
                if (counts == 1) {
                    cities.add(new Cities().setCode(code).setName(city).setProvinceCode(codes));
                }
                if (counts == 2) {
                    cities.add(new Cities().setCode(code).setName(city).setProvinceCode(codes));
                }
                if (counts == 3) {
                    cities.add(new Cities().setCode(code).setName(city).setProvinceCode(codes));
                }


                String href = a.get(0).attr("href");

                hrefUrl = ghUrl.replaceAll("(http://.+/)(.+\\.html)", "$1" + href);

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handleSubCode(hrefUrl, codeMap.get(count++), count, href.replaceAll("(\\d+).html", "$1"));

            }
        }
    }


    @Test
    public void testCon(){

        Config config = ConfigService.getAppConfig();
        System.out.println(config.getProperty("sofa.alipay.glmapper.name", "kll"));
    }

}
