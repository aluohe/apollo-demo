package com.example.fxmail;

import freemarker.template.*;

import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author aluohe
 * @className MyMail
 * @projectName demo
 * @date 2020/3/18 19:40
 * @description 我的邮件处理
 * @modified_by
 * @version: 1.0.0
 */

public class MyMail {

    private Configuration configure = null;

    public MyMail() {
        configure = new Configuration(new Version("2.3.30"));
        configure.setDefaultEncoding("utf-8");
    }

    /**
     * 根据Doc模板生成word文件
     *
     * @param dataMap  Map 需要填入模板的数据
     * @param fileName 文件名称
     * @param savePath 保存路径
     */
    public void createDoc(Map<String, Object> dataMap, String downloadType, String savePath) {
        try {
            //加载需要装填的模板
            Template template = null;
            //加载模板文件
            configure.setClassForTemplateLoading(this.getClass(), "/com/favccxx/secret/templates");
            //设置对象包装器
            configure.setObjectWrapper(new DefaultObjectWrapper(new Version("2.3.30")));
            //设置异常处理器
            configure.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
            //定义Template对象,注意模板类型名字与downloadType要一致
            template = configure.getTemplate(downloadType + ".xml");
            //输出文档
            File outFile = new File(savePath);
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
            template.process(dataMap, out);
            outFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据下载类型获取需要传递的Map参数
     *
     * @param oid          对象Id
     * @param downloadType 下载类型
     */
    private Map<String, Object> getDataMap(String oid, String downloadType) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        if ("Parameter1".equals(downloadType)) {
            // ...
//        ...
            dataMap = setObjToMap("Object1");
        } else {
//        ...
//        ...
            dataMap = setObjToMap("Object2");
        }
        return dataMap;
    }

    private static Map<String, Object> dataMap = new HashMap<String, Object>();

    /**
     * 将对象转换成Map
     *
     * @param obj 对象类
     * @return
     */
    public static Map<String, Object> setObjToMap(Object obj) {
        Class c;
        try {
            c = Class.forName(obj.getClass().getName());
            Method[] methods = c.getMethods();
            for (int i = 0, l = methods.length; i < l; i++) {
                String method = methods[i].getName();
                System.out.println("The method is:" + method);
                if (method.startsWith("get")) {
                    Object value = methods[i].invoke(obj);
                    if (value != null) {
                        if (value.getClass().getClassLoader() != null) {  //处理自定义的对象类型
                            setObjToMap(value);
                        }
                        String key = method.substring(3);
                        key = key.substring(0, 1).toLowerCase() + key.substring(1);
                        if ("java.util.Date".equals(value.getClass().getName())) {
//                            value = DateUtil.dateToString((Date)value);
                        }
                        dataMap.put(key, value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataMap;
    }

}