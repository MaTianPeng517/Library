package cn.msss.dao.user;

import cn.msss.entity.Users;
import cn.msss.util.BaseDao;
import cn.msss.util.PageUtil;
import cn.msss.util.ResultSetUtil;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoimpl extends BaseDao implements UserDao {


    //注册
    @Override
    public int add(Users users) {
        String sql="INSERT INTO `users`(`user_name`,`user_password`) VALUES(?,?)";

        Object [] obje={users.getUser_name(),users.getUser_password()};
        return executeUpdate(sql,obje);
    }

    @Override
    public int delete() {
        return 0;
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

    @Override
    public int findRownum() {
        return 0;
    }

    @Override
    public List<Users> findAllByPage(PageUtil util, Object... params) {
        return null;
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
