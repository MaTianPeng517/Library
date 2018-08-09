package cn.msss.service;

import cn.msss.service.user.UserServiceimpl;

/*
    时机
        1.需要实例化很多的对象
        2.这些对象有共同的父类和接口
 */
public class ServiceFactory {

    //私有化静态变量
    private static ServiceFactory serviceFactory;
    //私有构造
    private  ServiceFactory(){}
    //静态代码块
    static {
        if(serviceFactory==null){
            synchronized (ServiceFactory.class){
                if(serviceFactory==null){
                    serviceFactory=new ServiceFactory();
                }
            }
        }
    }

    //工厂
    public static IBaseService gteServiceimpl(String serviceName){
        IBaseService service=null;
        switch (serviceName){
            case "userService":
                service=new UserServiceimpl();
                break;
            default:
                break;
        }
        return service;
    }

}
