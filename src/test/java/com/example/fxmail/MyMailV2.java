package com.example.fxmail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author aluohe
 * @className MyMailV2
 * @projectName demo
 * @date 2020/3/18 19:58
 * @description
 * @modified_by
 * @version:
 */
public class MyMailV2 {
    @Test
    public void test() {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        try {
            Calendar cal = Calendar.getInstance();
            //编号
            dataMap.put("name", "aluohe");
            //日期
            dataMap.put("data", new SimpleDateFormat("yyyy/MM/dd").format(cal.getTime()));
            //附件张数
            dataMap.put("year", cal.get(Calendar.YEAR) + "");
            //受款人
            dataMap.put("month", cal.get(cal.MONTH) + 1 + "");
            //付款用途
            dataMap.put("day", cal.get(Calendar.DAY_OF_MONTH) + "");
            //自测结果
            dataMap.put("reslut_content", "成功");
            //送测内容功能描述
            dataMap.put("test_content", "油卡");
            //影响范围
            dataMap.put("change_range", "油卡记录");
            //Configuration 用于读取ftl文件
            Configuration configuration = new Configuration(new Version("2.3.30"));
            configuration.setDefaultEncoding("utf-8");

            /**
             * 以下是两种指定ftl文件所在目录路径的方式，注意这两种方式都是
             * 指定ftl文件所在目录的路径，而不是ftl文件的路径
             */
            //指定路径的第一种方式（根据某个类的相对路径指定）
//                configuration.setClassForTemplateLoading(this.getClass(), "../file_template/");

            //指定路径的第二种方式，我的路径是C：/a.ftl
            configuration.setDirectoryForTemplateLoading(new File("D:\\report\\demo\\src\\test\\java\\com\\example\\fxmail\\file_template"));

            //输出文档路径及名称
            File outFile = new File("D:/报销信息导出.doc");

            //以utf-8的编码读取ftl文件
            Template template = configuration.getTemplate("送测单-v1.ftl", "utf-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"), 10240);
            template.process(dataMap, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testdir(){

        System.out.println(System.getProperty("user.dir"));
    }
}