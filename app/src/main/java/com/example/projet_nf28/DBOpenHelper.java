package com.example.projet_nf28;

import android.util.Log;
import android.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DBOpenHelper {
    int id=0;
    boolean isEmailOk;

    public static Connection getConn(){
        String ip = "172.25.7.107";//ipconfig IPV4
        int port = 3306;
        String dbName = "nf28";
        String url = "jdbc:mysql://" + ip + ":" + port + "/" + dbName + "?useUnicode=true&characterEncoding=UTF-8";
        String user = "newuser";
        String password = "123456";

        Connection conn = null;
        //conn =(Connection) DBOpenHelper.getConn();
        //String sql = "select nom from artiste where id='"+i+"'";
        Statement st;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Log.d("ok", "ok load drive");
        } catch (ClassNotFoundException e) {
            Log.d("not ok", "err load drive");
        }
        try {
            conn = DriverManager.getConnection(url, user, password);
            Log.d("getConn", "ok connect bdd");
        } catch (SQLException e) {
            Log.e("getConn", "err connect bdd");
        }
        /*//close bdd
        try {
            conn.close();
            Log.d("MainActivity", "ok close bdd");
        } catch (SQLException e) {
            Log.d("MainActivity", "err close bdd");
        }*/
        return conn;
    }

    public boolean verifierEmail(String email){
        new Thread(new Runnable() {
            @Override
            public void run() {
                isEmailOk = true;
                Connection conn = null;
                String sql = "select * from user where email ='"+email+"'";
                Statement st;
                conn =(Connection) DBOpenHelper.getConn();
                Log.d("verifierEmail", "ok connect bdd");
                try {
                    // creat objet connect
                    java.sql.Statement statement = conn.createStatement();
                    Log.d("verifierEmail", "ok creat sql");
                    // execute sql
                    ResultSet rSet = statement.executeQuery(sql);
                    // print info
                    Log.d("verifierEmail", "user");
                    while (rSet.next()) {
                        Log.d("verifierEmail1", "isEmailOk : "+ isEmailOk);
                        isEmailOk=false;
                        Log.d("verifierEmail", "email existe : "+ rSet.getString("id"));
                        Log.d("verifierEmail2", "isEmailOk : "+ isEmailOk);
                    }
                } catch (SQLException e) {
                    Log.e("verifierEmail", "err sql");
                }
                //close bdd
                try {
                    conn.close();
                    Log.d("verifierEmail", "ok close bdd");
                } catch (SQLException e) {
                    Log.d("verifierEmail", "err close bdd");
                }
                Log.d("verifierEmail3", "isEmailOk : "+ isEmailOk);
                return;
            }
        }).start();
        Log.d("verifierEmail4", "isEmailOk : "+ isEmailOk);
        return isEmailOk;
    }

    public void addUser(User usr){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                int u = 0;
                conn =(Connection) DBOpenHelper.getConn();
                String sql = "insert into user (id, nom, prenom, email, isArtiste, isEmployer) values(?,?,?,?,?,?)";
                PreparedStatement pst;
                try {
                    pst = (PreparedStatement) conn.prepareStatement(sql);
                    pst.setInt(0,usr.getId());
                    pst.setString(1,usr.getNom());
                    pst.setString(2,usr.getPrenom());
                    pst.setString(3,usr.getEmail());
                    pst.setInt(4,usr.getIsArtiste());
                    pst.setInt(5,usr.getIsEmployer());
                    u = pst.executeUpdate();
                    pst.close();
                    conn.close();
                    Log.d("addUser", "ok sql");
                } catch (SQLException e) {
                    Log.e("addUser", "err sql");
                }
            }
        }).start();
    }

    public int findIdUser(String email){
        id=0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                String sql = "select * from user where email ='"+email+"'";
                Statement st;
                conn =(Connection) DBOpenHelper.getConn();
                Log.d("findIdUser", "ok connect bdd");
                try {
                    // creat objet connect
                    java.sql.Statement statement = conn.createStatement();
                    Log.d("findIdUser", "ok creat sql");
                    // execute sql
                    ResultSet rSet = statement.executeQuery(sql);
                    // print info
                    Log.d("findIdUser", "user");
                    Log.d("findIdUser", "id\t\t\temail\t");
                    Log.d("findIdUser", rSet.getString("id") + "\t" + rSet.getString("nom")+"\t");
                    if(rSet.getString("id").isEmpty()==false){
                        id=Integer.parseInt(rSet.getString("id"));
                        Log.d("findIdUser", "email ok");
                    }
                    else{
                        Log.e("findIdUser", "email inexiste");
                    }
                } catch (SQLException e) {
                    Log.e("findIdUser", "err sql");
                }
                //close bdd
                try {
                    conn.close();
                    Log.d("findIdUser", "ok close bdd");
                } catch (SQLException e) {
                    Log.d("findIdUser", "err close bdd");
                }
                return;
            }
        }).start();
        return id;
    }

    public void addArtiste(Artiste art){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                int u = 0;
                conn =(Connection) DBOpenHelper.getConn();
                String sql = "insert into artiste (id, nom, prenom, email, isArtiste, isEmployer, profession, niveau, cv, oeuvre) values(?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst;
                try {
                    pst = (PreparedStatement) conn.prepareStatement(sql);
                    pst.setInt(0,art.getId());
                    pst.setString(1,art.getNom());
                    pst.setString(2,art.getPrenom());
                    pst.setString(3,art.getEmail());
                    pst.setInt(4,art.getIsArtiste());
                    pst.setInt(5,art.getIsEmployer());
                    pst.setString(6,art.getProfession());
                    pst.setString(7,art.getNiveau());
                    pst.setString(8,art.getPrenom());
                    if(art.getCv().isEmpty()==false){
                        pst.setString(9,art.getCv());
                    }
                    else{
                        pst.setString(9,"");
                    }
                    if(art.getOevre().isEmpty()==false){
                        pst.setString(10,art.getOevre());
                    }
                    else{
                        pst.setString(10,"");
                    }
                    u = pst.executeUpdate();
                    pst.close();
                    conn.close();
                } catch (SQLException e) {
                    Log.d("addArtiste","err sql");
                }
            }
        }).start();
    }
}
