package cn.msss.dao;

import cn.msss.entity.Users;
import cn.msss.util.PageUtil;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {

    /*
        所有接口的父接口
        所有接口的公共方法
     */

    //注册
    int add(Users users);
    //删除
    int delete();
    //修改
    int update();
    //
    T findByCondition(Serializable id);
    //查询
    List<T> findAll();
    //
    int findRownum();
    //分页
    List<T>  findAllByPage(PageUtil util, Object ...params);
}
