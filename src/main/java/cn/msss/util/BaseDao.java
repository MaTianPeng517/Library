package cn.msss.util;

import java.sql.*;

public class BaseDao {

    /*
        主要三个功能
        1.增删改查
        2.连接数据库
        3.释放资源
     */

    //JDBC需要的API
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    //1.连接数据库
    public  boolean  getConnection(){
        try {
            //加载驱动
            Class.forName(ConfigManager.getvalue("jdbc.driver"));
            try {
                //建立连接
                con= DriverManager.getConnection(ConfigManager.getvalue("jdbc.url"),ConfigManager.getvalue("jdbc.username"),ConfigManager.getvalue("jdbc.password"));
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    //2.增删改
    public int executeUpdate(String sql,Object...obje){
        int num=0;
        if (getConnection()){
            try {
                ps  = con.prepareStatement(sql);
                if(obje!=null){
                    for (int i=0;i<obje.length;i++){
                        ps.setObject(i+1,obje[i]);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                num=ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                //释放资源
                close();
            }
        }
        return num;
    }
    //3.查
    public ResultSet executeQuery(String sql,Object...obje){
        if (getConnection()){
            try {
                ps  = con.prepareStatement(sql);
                if(obje!=null){
                    for (int i=0;i<obje.length;i++){
                        ps.setObject(i+1,obje[i]);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                rs=ps.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                //释放资源
                close();
            }
        }
        return rs;
    }
    //4.释放资源
    public void close(){
            try {
                if (rs!=null){
                rs.close();
                }
                if (ps!=null){
                    ps.close();
                }
                if (con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
