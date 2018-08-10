package cn.msss.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetUtil {

    //给指定的单个对象赋值
    public static <T> T eachOne(ResultSet rs,Class<T> clazz){
        T object=null;
        try {
            if (rs.next()){
                try {
                    object=clazz.newInstance();//通过反射的方式获取实例化对象
                    Field [] fields=clazz.getDeclaredFields();//通过反射的方式获取类中所有信息
                    for (Field f:fields) {
                        f.setAccessible(true);//打开访问私有属性的开关
                        f.set(object,rs.getObject(f.getName()));
                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    //给集合对象赋值
    public static <T> List<T> eachList(ResultSet rs,Class<T> clazz)  {
        List<T> list=new ArrayList<T>();
        T object=null;
        try {
            while (rs.next()){
                try {
                    object=clazz.newInstance();//通过反射的方式获取实例化对象
                    Field [] fields=clazz.getDeclaredFields();//通过反射的方式获取类中所有信息
                    for (Field f:fields) {
                        f.setAccessible(true);//打开访问私有属性的开关
                        f.set(object,rs.getObject(f.getName()));
                    }
                    list.add(object);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  list;
    }

}
