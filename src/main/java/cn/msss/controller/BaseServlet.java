package cn.msss.controller;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/*
    BaseServlet 是 子Servlet 类的父类
    作用：

    这个类写了什么？
    1.所有的请求都会进入此类
    2.获取请求，根据请求分发到各个子Servlet
 */






public abstract class BaseServlet extends HttpServlet{

    /*
        创建一个抽象方法，用于子类继承并实现
     */
    public abstract Class getServletClass();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户的请求
        String methodName = request.getParameter("methodName");
        System.out.println("获取用户+++"+methodName);
        /*
            1.根据用户传递的参数，确定执行那个servlet中的方法
            2.用户需要指定的方法
            3.执行方法的返回值
         */
        //用户指定的方法
        Method method=null;
        //执行方法的返回值
        Object obje=null;
        /*
            1.判断获取用户的请求，有一项条件为空的话
            2.执行返回登录页面
         */
        if (methodName==null||"".equals(methodName)){
            // 执行返回登录页面
            obje=returnto(request,response);
        }else{    //如不是空，说明有这一项方法
            /*
                1.找到方法
             */
            try {
                method=getServletClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
                System.out.println("method======="+method);
                try {
                    //执行方法
                    obje = method.invoke(this, request, response);
                    System.out.println("obje》》》》》》"+obje);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            //这里根据不通的返回值，来执行不同的操作
            /*
                 1.request  :请求
                 2.response ：响应
                 3.obje ：方法的返回值
             */
            toview(request,response,obje);

        }
    }
    //来执行不同的操作
    private void toview(HttpServletRequest request, HttpServletResponse response, Object obje) {
        //判断方法的的返回值
        if(obje==null){
            System.out.println("抱歉啦，没有获取到执行方法的返回值");
        }else{
            /*
                以上是没有获取到执行方法的返回值，
                以下是获取到了执行方法的返回值，
                都知道返回值有两种 1.字符串，2.JSON
                不返回-----》AJAX
             */
            //一,字符串的
            if (obje instanceof String ){
                String viewName=obje.toString()+".jsp";
                try {
                    //转发到执行方法的返回值得页面
                    request.getRequestDispatcher(viewName).forward(request,response);
                    System.out.println("字符串--------"+viewName);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{   //不是说过么，返回的不是JSON就是字符串，上面是字符串那返回的不是字符串就是 JSON 咯！！！
                //二 JSON
                String objejson = JSON.toJSONString(obje);
                System.out.println("JSON*********"+objejson);
                try {
                    PrintWriter writer=response.getWriter();
                    writer.write(objejson);
                    writer.flush(); //清除缓存
                    writer.close(); //关闭
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object returnto(HttpServletRequest request, HttpServletResponse response) {

        return "login"; //登录页面
    }
}
