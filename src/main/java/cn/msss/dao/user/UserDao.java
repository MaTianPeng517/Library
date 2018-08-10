package cn.msss.dao.user;

import cn.msss.dao.IBaseDao;
import cn.msss.entity.Users;

public interface UserDao extends IBaseDao<Users> {

    String validateName(String username);

     /*
        登录
     */
     Users login(String userName, String password);
}
