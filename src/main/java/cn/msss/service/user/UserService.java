package cn.msss.service.user;

import cn.msss.entity.Users;
import cn.msss.service.IBaseService;

public interface UserService extends IBaseService<Users> {

    //ajax验证用户名称
    String validateName(String username);
    //登录
    Users login(String username, String passwordInDB);
}
