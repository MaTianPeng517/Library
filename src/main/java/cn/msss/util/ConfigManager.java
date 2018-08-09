package cn.msss.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    /*
        作用
            单例类
            数据库连接：四要素，统一共享一个即可
     */

    //1.创建一个单例的静态变量
    private static ConfigManager configManager;

    //2.创建properties对象 专门解析jdbc.properties 这种文件
    private static Properties properties;

    //3.静态代码块，读取jdbc.properties 文件 ，无须实例化对象即可使用。
    static {
        //获取路径
        String path="jdbc.properties";
        //实例化对象
        properties=new Properties();
        //读取path
        InputStream stream = ConfigManager.class.getClassLoader().getResourceAsStream(path);
        try {
            //加载jdbc.properties 文件
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //4.创建一个对外访问的方法
    public static synchronized ConfigManager getinstance(){

        return  configManager;
    }

    //5.properties对象已有值 properties进入内存，通过KEY拿到VALUE
    public static String getvalue(String key){

        return properties.getProperty(key);
    }
}
