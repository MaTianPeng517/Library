package cn.msss.util;
/*
    这是一个所有返回值的工具类
    1.正确  与  错误
    2.错误信息
    3.正确的时候，有可能有数据
 */
public class ResuUtil {

    private Integer status; //一种状态  1成功     0失败
    private String message; //错误信息
    private Object data; //返回的数据

    //成功方法
    public ResuUtil getSuccessfulmethod(Object obje){
        this.data=obje;
        this.status=1;
        return this;
    }

    public ResuUtil getSuccessfulmethod(){
        this.status=1;
        return this;
    }

    //错误方法
    public ResuUtil getErrormethod(String message){
       this.message=message;
       this.status=0;
        return this;
    }

    public ResuUtil(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResuUtil() {

    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
