package cn.msss.service.user;

import cn.msss.dao.user.UserDao;
import cn.msss.dao.user.UserDaoimpl;
import cn.msss.entity.Users;
import cn.msss.util.PageUtil;

import java.io.Serializable;
import java.util.List;

public class UserServiceimpl implements UserService {
    UserDao userDao=new UserDaoimpl();
    @Override
    public int add(Users users) {

        return userDao.add(users);
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