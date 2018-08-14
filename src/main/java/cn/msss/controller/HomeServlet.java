package cn.msss.controller;


import cn.msss.entity.Users;
import cn.msss.service.ServiceFactory;
import cn.msss.service.user.UserService;
import cn.msss.service.user.UserServiceimpl;
import cn.msss.util.PageUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends BaseServlet {

    UserService userService=null;
    @Override
    public Class getServletClass() {

        return HomeServlet.class;
    }


    //当访问时，先执行这个方法
    @Override
    public void init() throws ServletException {
        userService= (UserService) ServiceFactory.gteServiceimpl("userService");
    }


    //分页的方法
    public PageUtil findAllByPage(HttpServletRequest request, HttpServletResponse response){
        //获取当前页面 pageindex
        int pageIndex = Integer.valueOf(request.getParameter("pageIndex"));
        //创建PageUtil对象
        PageUtil pageUtil=new PageUtil();
        //把获取的数据封装到pageUtil
        pageUtil.setPageIndex(pageIndex);
        pageUtil.setTotalCount(userService.findRownum());
        //调用service代码，获取数据
        List<Users> allByPage = userService.findAllByPage(pageUtil);
        pageUtil.setList(allByPage);
        //打印
        System.out.println("pageUtil-------------"+pageUtil);
        //返回pageUtil
        return pageUtil;
    }

    //删除
    public String deleID(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        System.out.println("进入删除id");
        int delete = userService.delete(id);
        if (delete>0){
            return "addBook";
            }
        return "login";
    }

    /**
     * 上传文件的操作
     */
    public String fileupload(HttpServletRequest request, HttpServletResponse response){
        //创建实体类的对象
        Users users=new Users();
        //创建DiskFileItemFactory :
        DiskFileItemFactory diskFileItemFactory=new DiskFileItemFactory();
        //创建ServletFileUpload
        ServletFileUpload servletFileUpload=new ServletFileUpload(diskFileItemFactory);
        //1.首先判断是否为文件请求
        boolean content = servletFileUpload.isMultipartContent(request);
        if (content){  //文件上传请求
            try {
                List<FileItem> fileItems = servletFileUpload.parseRequest(request);
                //使用迭代器遍历
                Iterator<FileItem> iterator = fileItems.iterator();
                //循环遍历
                while (iterator.hasNext()){
                    /**
                     * 这里分为两种文件，普通元素，文件元素
                     */
                    //获取每一个，普通元素，文件元素
                    FileItem next = iterator.next();
                    if(next.isFormField()){ //判断普通元素，文件元素
                        String fieldName = next.getFieldName();
                        System.out.println("fieldName------"+fieldName);
                        if ("fileName".equals(fieldName)) {
                            try {
                                //转换编码
                                users.setUser_name(next.getString("UTF-8"));
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }else{//文件元素
                                                //上传到指定的路径下
                        String realPath = request.getSession().getServletContext().getRealPath("E:\\原老师视频\\新建文件夹\\LIBRARY");
                        //创建File对象
                        File file=new File(realPath);
                        //判断如没有这个文件夹的话，创建一个新的
                        if(file.exists()){
                            file.mkdirs();//多级文件夹
                        }
                        //获取上传的文件名称
                        String name = next.getName();
                        //判断用户是否选中了文件
                        if(name!=null&&name.equals("")){
                            File fielname=new File(name);
                            File fielupload=new File(realPath, fielname.getName());
                            try {
                                //真。上传
                                next.write(fielupload);
                                users.setUser_file(realPath+"\\"+fielname.getName());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    users.setUser_name("呵呵");
                    users.setUser_password("hehe");
                    int add = userService.add(users);
                    if (add>0){
                        System.out.println("上传成功");
                    }else {
                        System.out.println("上传失败");
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("抱歉这不是文件请求");
        }
        return "";
    }

}
