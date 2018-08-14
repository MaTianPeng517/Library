package cn.msss.entity;

public class Users {
  //``user_id` `user_password` `user_name`
    private  Integer user_id;
    private  String user_name;
    private  String user_password;
    private  String user_file;

    @Override
    public String toString() {
        return "Users{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_file='" + user_file + '\'' +
                '}';
    }

    public Users() {
    }

    public Users(Integer user_id, String user_name, String user_password, String user_file) {

        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_file = user_file;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_file() {
        return user_file;
    }

    public void setUser_file(String user_file) {
        this.user_file = user_file;
    }

}
