package cn.msss.controller;

import cn.msss.util.ResuUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class UserServlet extends BaseServlet{


    @Override
    public Class getServletClass() {

        return UserServlet.class;
    }


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
}
