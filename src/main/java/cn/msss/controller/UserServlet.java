package cn.msss.controller;

import cn.msss.entity.Users;
import cn.msss.service.ServiceFactory;
import cn.msss.service.user.UserService;
import cn.msss.service.user.UserServiceimpl;
import cn.msss.util.Md5Encrypt;
import cn.msss.util.ResuUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/login")
public class UserServlet extends BaseServlet{

    //不实例化service层对象 ，让工厂区实例化
    private UserService userService;

    //当访问时，先执行这个方法
    @Override
    public void init() throws ServletException {
        userService=(UserService) ServiceFactory.gteServiceimpl("userService");
    }

    @Override
    public Class getServletClass() {

        return UserServlet.class;
    }

    //登录
    public ResuUtil login(HttpServletRequest request, HttpServletResponse response){
        /*
           1.获取用户输入的用户名
           2.获取用户输入的密码
         */
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        ResuUtil pu=new ResuUtil();
        //从数据库获取一个密码，并使用MD5验证，如没有这个用户就不执行之后的代码
        if("admin".equals(username)){
            pu.getSuccessfulmethod(username);
        }else{
            pu.getErrormethod("用户名不存在！！！！");
        }
        return pu;
    }

    //注册
    public String register(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Users users=new Users();
        users.setUser_name(username);
        try {
            users.setUser_password(Md5Encrypt.getEncryptedPwd(password));
            System.out.println(users.getUser_password());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int add = userService.add(users);
        System.out.println(add+"add");
        if(add>0){
            return "addBook";
        }else{
            return "register";
        }
    }
}
