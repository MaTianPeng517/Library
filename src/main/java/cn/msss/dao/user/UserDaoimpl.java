package cn.msss.dao.user;

import cn.msss.entity.Users;
import cn.msss.util.BaseDao;
import cn.msss.util.PageUtil;

import java.io.Serializable;
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
}
