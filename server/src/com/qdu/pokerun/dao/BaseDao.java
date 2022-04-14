package com.qdu.pokerun.dao;

import java.util.List;

public interface BaseDao {

    /**
     * 传入sql语句和参数并执行查询
     * 查询结果以单个对象形式返回
     *
     * @param sql 传入的sql语句
     * @param clazz 要返回对象所属的类
     * @param params 若干个参数
     * @return 查询出的单个对象
     */
    public <T> T executeQueryDaoOBJ(String sql, Class<T> clazz, Object... params);

    /**
     * 传入sql语句和参数并使用反射以及BeanUtils相关jar进行一个泛型类的查询
     * 查询结果以对象列表形式返回
     *
     * @param sql 传入的sql语句
     * @param clazz 要返回对象所属的类
     * @param params 若干个参数
     * @return 查询出的对象组成的列表
     */
    public <T> List<T> executeQueryDaoList(String sql, Class<T> clazz, Object... params);

    /**
     * 传入sql语句和参数并执行查询
     * 查询结果为单个属性，以字符串的形式返回
     *
     * @param sql 传入的sql语句
     * @param params 若干个参数
     * @return 查询出的属性，以字符串格式返回
     */
    public String executeQueryDaoAttri(String sql, Object... params);

    /**
     * 传入sql语句和参数并执行增删改操作
     *
     * @param sql 传入的sql语句
     * @param params 若干个参数
     * @return 成功更改的数据行数
     */
    public int executeUpdateDao(String sql, Object... params);
}