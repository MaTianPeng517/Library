package cn.msss.dao.user;

import cn.msss.entity.Users;
import cn.msss.util.BaseDao;
import cn.msss.util.PageUtil;
import cn.msss.util.ResultSetUtil;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoimpl extends BaseDao implements UserDao {


    //注册
    @Override
    public int add(Users users) {
        String sql="INSERT INTO `users`(`user_name`,`user_password`,`user_file`) VALUES(?,?,?)";
        Object [] obje={users.getUser_name(),users.getUser_password(),users.getUser_file()};
        return executeUpdate(sql,obje);
    }

    @Override
    public int delete(Serializable id) {
        String sql="DELETE FROM `users` WHERE `user_id`=?";
        int i = executeUpdate(sql, id);
        return i;
    }

    @Override
    public int update() {
        return 0;
    }

    @Override
    public Users findByCondition(Serializable id) {
        return null;
    }

    @Override
    public List<Users> findAll() {
        return null;
    }

    //查询总记录数
    @Override
    public int findRownum() {
        String sql="SELECT COUNT(`user_id`) as cout FROM `users`";
        rs=executeQuery(sql);
        int cout=0;
        if (cout>0){
            try {
                rs.getInt("cout");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cout;
    }

    /**
     *
     * （当前页-1）*pageSize
     *   limit  ?,3
     */

    @Override
    public List<Users> findAllByPage(PageUtil util, Object... params) {
        String sql="SELECT `user_id`,`user_name`,user_password FROM `users` LIMIT ?,?";
        Object [] obje={(util.getPageIndex()-1)*util.getPageSize(),util.getPageSize()};
        rs=executeQuery(sql,obje);
        List<Users> list=ResultSetUtil.eachList(rs,Users.class);
        return list;
    }

    //ajax验证用户名称是否存在/密码是否正确
    @Override
    public String validateName(String username) {
        String sql="SELECT `user_password` FROM `users` WHERE `user_name`=?";
        ResultSet rs = executeQuery(sql, username);
        String password=null;
        try {
            if (rs.next()){
                password=rs.getString("user_password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }

    @Override
    public Users login(String userName, String password) {
        String sql="SELECT `user_id`,`user_name`,`user_password` FROM `users`  WHERE `user_name`=? AND `user_password`=?";
        Object [] obje={userName,password};
         rs = executeQuery(sql, obje);
        Users users=ResultSetUtil.eachOne(rs,Users.class);
        return users;
    }
}
