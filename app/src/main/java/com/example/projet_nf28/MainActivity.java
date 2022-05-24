package com.example.projet_nf28;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity{

    private static final int TEST_USER_SELECT = 1;
    int i =0;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String name;
            switch (msg.what){
                case TEST_USER_SELECT:
                    Artiste test = (Artiste) msg.obj;
                    name = test.getNom();
                    System.out.println("***********");
                    System.out.println("***********");
                    System.out.println("name:"+name);
                    Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void connectBDD(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
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
                    Log.d("MainActivity", "ok connect bdd");
                    //查询学生表
                    String sql = "SELECT * FROM artiste ";
                    try {
                        // 创建用来执行sql语句的对象
                        java.sql.Statement statement = conn.createStatement();
                        Log.d("MainActivity", "ok create sql");
                        // 执行sql查询语句并获取查询信息
                        ResultSet rSet = statement.executeQuery(sql);
                        // 迭代打印出查询信息
                        Log.d("MainActivity", "artiste");
                        Log.d("MainActivity", "id\t\t\tnom\t");
                        while (rSet.next()) {
                            Log.d("MainActivity", rSet.getString("id") + "\t" + rSet.getString("nom")+"\t");
                            //Toast.makeText(getApplicationContext(), rSet.getString("name"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (SQLException e) {
                        Log.e("MainActivity", "err sql");
                    }
                    //关闭数据库
                    try {
                        conn.close();
                        Log.d("MainActivity", "ok close bdd");
                    } catch (SQLException e) {
                        Log.d("MainActivity", "err close bdd");
                    }
                    return;
                } catch (SQLException e) {
                    Log.d("MainActivity", "err connect bdd");
                }
            }
        }).start();
    }

    public void connectBDD2(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                //conn =(Connection) DBOpenHelper.getConn();
                //String sql = "select nom from artiste where id='"+i+"'";
                Statement st;
                conn =(Connection) DBOpenHelper.getConn();
                Log.d("MainActivity", "ok connect bdd");
                //requery
                String sql = "SELECT * FROM artiste ";
                try {
                    // creat objet connect
                    java.sql.Statement statement = conn.createStatement();
                    Log.d("MainActivity", "ok creat sql");
                    // execute sql
                    ResultSet rSet = statement.executeQuery(sql);
                    // print info
                    Log.d("MainActivity", "artiste");
                    Log.d("MainActivity", "id\t\t\tnom\t");
                    while (rSet.next()) {
                        Log.d("MainActivity", rSet.getString("id") + "\t" + rSet.getString("nom")+"\t");
                        //Toast.makeText(getApplicationContext(), rSet.getString("name"), Toast.LENGTH_SHORT).show();
                    }
                } catch (SQLException e) {
                    Log.e("MainActivity", "err sql");
                }
                //关闭数据库
                try {
                    conn.close();
                    Log.d("MainActivity", "ok close bdd");
                } catch (SQLException e) {
                    Log.d("MainActivity", "err close bdd");
                }
                return;
            }
        }).start();
    }

    public void deleteData(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                conn =(Connection) DBOpenHelper.getConn();
                String sql = "delete from user where id='"+5+"'";
                int u = 0;
                PreparedStatement pst;
                try {
                    pst = (PreparedStatement) conn.prepareStatement(sql);
                    u = pst.executeUpdate();
                    pst.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void addData(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                int u = 0;
                conn =(Connection) DBOpenHelper.getConn();
                //String sql = "insert into user (nom, prenom) values(?,?)";
                String sql = "insert into user (nom, prenom, email, isArtiste, isEmployer) values(?,?,?,?,?)";
                PreparedStatement pst;
                try {
                    pst = (PreparedStatement) conn.prepareStatement(sql);
                    pst.setString(1,"");
                    pst.setString(2,"Test2");
                    pst.setString(3,"Test3@Test");
                    pst.setInt(4,0);
                    pst.setInt(5,0);
                    u = pst.executeUpdate();
                    pst.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void commence(View view) {
        Intent intent = new Intent(this, ChoixInscriptionConnnexion.class);
        startActivity(intent);
    }

    public void Modifer(View view){
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                int u = 0;
                Connection conn = null;
                conn =(Connection) DBOpenHelper.getConn();
                String sql = "UPDATE user "+
                        "SET nom = '" + "A" + "',"+
                        "prenom = '" + "Aa" + "',"+
                        "isArtiste = '" + 1 + "',"+
                        "isEmployer = '" + 0 + "' "+
                        "WHERE id ='"+27+"'";
                PreparedStatement pst;
                try {
                    pst = (PreparedStatement) conn.prepareStatement(sql);
                    u = pst.executeUpdate();
                    pst.close();
                    Log.d("ModiferUser", "ok sql");
                } catch (SQLException e) {
                    Log.e("ModiferUser", "err sql");
                }
                try {
                    conn.close();
                    Log.d("ModiferUser", "ok close bdd");
                } catch (SQLException e) {
                    Log.e("ModiferUser", "err close bdd");
                }
            }
        });
        a.start();
    }

    public void addArtiste(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                int u = 0;
                conn =(Connection) DBOpenHelper.getConn();
                //String sql = "insert into artiste (id, nom, prenom, email, isArtiste, isEmployer, profession, niveau, cv, oeuvre) values(?,?,?,?,?,?,?,?,?,?)";
                String sql = "insert into artiste (id, nom, prenom, email, isArtiste, isEmployer, profession, niveau, cv, oeuvre) values(?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst;
                try {
                    pst = (PreparedStatement) conn.prepareStatement(sql);
                    pst.setInt(1,17);
                    pst.setString(2,"AAA");
                    pst.setString(3,"AAAA");
                    pst.setString(4,"AAAAA@AAAAA");
                    pst.setInt(5,1);
                    pst.setInt(6,0);
                    pst.setString(7,"AAAAAAA");
                    pst.setString(8,"Debutant");
                    pst.setString(9,"");
                    pst.setString(10,"");
                    u = pst.executeUpdate();
                    pst.close();
                    Log.d("addArtiste","ok sql");
                } catch (SQLException e) {
                    Log.e("addArtiste","err sql");
                }

                try {
                    conn.close();
                    Log.d("addArtiste","ok close bdd");
                } catch (SQLException throwables) {
                    Log.e("addArtiste","err close bdd");
                }
            }
        }).start();
    }
}