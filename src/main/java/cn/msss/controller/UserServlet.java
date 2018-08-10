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
    ResuUtil util=new ResuUtil();
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
    public String login(HttpServletRequest request, HttpServletResponse response){
        /*
           1.获取用户输入的用户名
           2.获取用户输入的密码
         */
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //得从数据库中获取一个用户名  如果没有用户名不需要再执行后续代码
        String passwordInDB=userService.validateName(password); //验证用户名是否存在
        if(passwordInDB!=null){
            try {
                if(Md5Encrypt.validPassword(password,passwordInDB)){
                    Users users=userService.login(username,passwordInDB);
                    //保存对象
                    request.getSession().setAttribute("loginUser",users);
                    return "addBook";
                }else{
                    System.out.println("密码错误");
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("用户名不存在");
        }
        return "login";
    }

    /**
     * 验证AJAX用户是否存在
     * @param request
     * @param response
     * @return
     */
    public ResuUtil validateName(HttpServletRequest request, HttpServletResponse response){
        //1.获取用户名称
        String username = request.getParameter("username");
        System.out.println("ajax--->"+username);
        //2.调用Service层代码
        String passwordInDB= userService.validateName(username);
        if(passwordInDB==null){
            util.getSuccessfulmethod();
        }else{
            util.getErrormethod("昵称已注册");
        }
        return util;
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
