package com.qdu.pokerun.dao.impl;

import com.qdu.pokerun.dao.BaseDao;
import com.qdu.pokerun.util.DatabaseUtil;

import org.apache.commons.beanutils.BeanUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class BaseDaoImpl implements BaseDao {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public <T> T executeQueryDaoOBJ(String sql, Class<T> clazz, Object... params) {
        List<T> TList = executeQueryDaoList(sql, clazz, params);
        return (TList!=null&&TList.size()>0) ? TList.get(0) : null;
    }

    @Override
    public <T> List<T> executeQueryDaoList(String sql, Class<T> clazz, Object... params) {
        try {

            List<T> list = new ArrayList<T>();
            T t = null;

            con = DatabaseUtil.getConnection();
            ps = con.prepareStatement(sql);
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while (rs.next()) {
                t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = rsmd.getColumnName(i + 1);
                    Object value = rs.getObject(columnName);
                    //String realColName=RegExUtil.matchString("(?:.*\\\\.)?(.+)", columnName);
                    BeanUtils.copyProperty(t, columnName, value);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
    }

    @Override
    public String executeQueryDaoAttri(String sql, Object... params) {
        try {
            con = DatabaseUtil.getConnection();
            ps = con.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(null, ps, con);
        }
        return null;
    }

    @Override
    public int executeUpdateDao(String sql, Object... params) {
        int rows = 0;

        try {
            con = DatabaseUtil.getConnection();
            ps = con.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            rows = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(null, ps, con);
        }
        return rows;
    }
}
