package com.hand;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by chengcong on 2018/7/22.
 */
public class C3P0Utils {

        private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

        //使用静态代码块给ComboPooledDataSource设置4大数据量连接信息

    static{

        try {

           dataSource.setDriverClass("com.mysql.jdbc.Driver");
            String ip = System.getenv("IP");
            String port=System.getenv("PORT");
            String dbname=System.getenv("DBNAME");
            String username = System.getenv("USERNAME");
            String password=System.getenv("PASSWORD");
            dataSource.setJdbcUrl("jdbc:mysql://"+ip+":"+port+"/"+dbname);

           dataSource.setUser(username);

           dataSource.setPassword(password);

        } catch (Exception e) {

           throw new RuntimeException("设置连接信息失败!");

        }

    }

        // 创建一个返回ComboPooledDataSource的方法

        public static DataSource getDataSource(){

        return dataSource;

        }

        // 创建一个静态方法返回Connection对象

        public static Connection getConnection() {

        try {

        return dataSource.getConnection();

        } catch (SQLException e) {

        throw new RuntimeException(e + "获取数据库连接失败");

        }

        }



        // 定义一个释放资源的方法

        public static void close(ResultSet rs, Statement stat, Connection conn) {

        if (rs != null) {

        try {

        rs.close();

        } catch (SQLException e) {

        e.printStackTrace();

        }

        }

        if (stat != null) {

        try {

        stat.close();

        } catch (SQLException e) {

        e.printStackTrace();

        }

        }

        if (conn != null) {

        try {

        rs.close();

        } catch (SQLException e) {

        e.printStackTrace();

        }

        }

        }

        }