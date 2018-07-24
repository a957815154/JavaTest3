package com.hand;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

/**
 * Created by chengcong on 2018/7/22.
 */
public class test1 {

    public static void getCitybyCountryid(int id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT city_id,city FROM `city` WHERE country_id=?";
        System.out.println("Country ID: " + id);
        getCountrybyId(id);
        List<Object[]> query = queryRunner.query(sql, id, new ArrayListHandler());

        for (Object[] b : query
                ) {
            for (int i = 0; i < b.length; i++) {
                if (i == 0) {
                    System.out.print(b[i] + " | ");
                } else {
                    System.out.print(b[i]);
                }
            }
            System.out.println();
        }
    }

    public static void getCountrybyId(int id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT country from country WHERE country_id=?";
        Object[] query = queryRunner.query(sql, id, new ArrayHandler());
        System.out.println("Country " + query[0].toString() + " 的城市");
    }

    public static void getFilmbyCustomerid(int id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT film.film_id,title,rental_date from film,inventory,rental WHERE customer_id = ? and inventory.film_id = film.film_id and inventory.inventory_id = rental.inventory_id order by rental_date DESC";
        System.out.println("Customer ID: " + id);
        getCustomerbyId(id);
        List<Object[]> query = queryRunner.query(sql, id, new ArrayListHandler());
        for (Object[] b : query) {
            for (int i = 0; i < b.length; i++) {
                if (i != 2) {
                    System.out.print(b[i] + " | ");
                } else {
                    System.out.print(b[i]);
                }
            }
            System.out.println();
        }
    }

    public static void getCustomerbyId(int id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT first_name,last_name from customer WHERE customer_id=?";
        Object[] query = queryRunner.query(sql, id, new ArrayHandler());
        System.out.println(query[0].toString() + " " + query[1].toString() + "租用的Film");
    }

    public static void main(String[] args) throws Exception {
        //testCreateTable();
        // testAddStudent();
        // testList();
        System.out.println("answer 1");
        String ip = System.getenv("IP");
        String port=System.getenv("PORT");
        String dbname=System.getenv("DBNAME");
        String username = System.getenv("USERNAME");
        String password=System.getenv("PASSWORD");
        System.out.println(ip + port+ dbname+username+password+" ");
        System.out.println(System.getenv("COUNTRYID"));
        getCitybyCountryid(Integer.parseInt(System.getenv("COUNTRYID")));
        System.out.println("answer 2");
        getFilmbyCustomerid(Integer.parseInt(System.getenv("CUSTOMERID")));
    }
}
