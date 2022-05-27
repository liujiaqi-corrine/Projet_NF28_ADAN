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
    boolean idMdpOK;

    public static Connection getConn(){
        String ip = "10.0.2.2";//""192.168.0.102";//"172.25.7.107";//ipconfig IPV4
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
            Log.d("getConn", "ok load drive");
        } catch (ClassNotFoundException e) {
            Log.d("getConn", "err load drive");
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
        Thread a=new Thread(new Runnable() {
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
        });
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("verifierEmail4", "isEmailOk : "+ isEmailOk);
        return isEmailOk;
    }

    public boolean verifierMdp(String email, String mdp){
        Thread a=new Thread(new Runnable() {
            @Override
            public void run() {
                idMdpOK = true;
                Connection conn = null;
                String sql = "select * from user where email ='"+email+"'";
                Statement st;
                conn =(Connection) DBOpenHelper.getConn();
                Log.d("verifierMdp", "ok connect bdd");
                try {
                    // creat objet connect
                    java.sql.Statement statement = conn.createStatement();
                    Log.d("verifierMdp", "ok creat sql");
                    // execute sql
                    ResultSet rSet = statement.executeQuery(sql);
                    int cpt=0;
                    while (rSet.next()) {
                        cpt=cpt+1;
                        if(mdp.equals(rSet.getString("mdp")) == false){
                            idMdpOK = false;
                            Log.e("verifierMdp", "err mdp incorrect "+mdp);
                            Log.e("verifierMdp", "err mdp incorrect "+rSet.getString("mdp"));
                        }
                    }
                    if(cpt!=1){
                        idMdpOK = false;
                        Log.e("verifierMdp", "err nb compte"+cpt);
                    }
                } catch (SQLException e) {
                    Log.e("verifierMdp", "err sql");
                }
                //close bdd
                try {
                    conn.close();
                    Log.d("verifierMdp", "ok close bdd");
                } catch (SQLException e) {
                    Log.d("verifierMdp", "err close bdd");
                }
                Log.d("verifierMdp", "idMdpOK : "+ idMdpOK);
                return;
            }
        });
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("verifierMdp", "idMdpOK : "+ idMdpOK);
        return idMdpOK;
    }

    public boolean addUser(User usr){
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                isEmailOk=true;
                Connection conn = null;
                int u = 0;
                conn =(Connection) DBOpenHelper.getConn();
                String sql = "insert into user (nom, prenom, email, isArtiste, isEmployer, mdp) values(?,?,?,?,?,?)";
                PreparedStatement pst;
                try {
                    pst = (PreparedStatement) conn.prepareStatement(sql);
                    pst.setString(1,usr.getNom());
                    pst.setString(2,usr.getPrenom());
                    pst.setString(3,usr.getEmail());
                    pst.setInt(4,usr.getIsArtiste());
                    pst.setInt(5,usr.getIsEmployer());
                    pst.setString(6,usr.getMdp());
                    u = pst.executeUpdate();
                    pst.close();
                    Log.d("addUser", "ok sql");
                } catch (SQLException e) {
                    Log.e("addUser", "err sql");
                    isEmailOk=false;

                    sql="DELETE FROM user WHERE ID=(SELECT MAX(id) FROM user)";
                    try {
                        pst = (PreparedStatement) conn.prepareStatement(sql);
                        pst.close();
                        Log.d("addUser", "ok delete duplicate");
                    } catch (SQLException throwables) {
                        Log.e("addUser", "err delete duplicate");
                    }

                }
                try {
                    conn.close();
                    Log.d("addUser", "ok close bdd");
                } catch (SQLException e) {
                    Log.e("addUser", "err close bdd");
                }
            }
        });
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isEmailOk;
    }

    public void OffreAjouteCandidat(int idOffre, String contenuCandidat){
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                int u=0;
                Connection conn = null;
                conn =(Connection) DBOpenHelper.getConn();
                String sql = "UPDATE offre "+
                        "SET candidate = '" + contenuCandidat + "'"+
                        "WHERE id ='"+idOffre+"'";
                PreparedStatement pst;
                try {
                    pst = (PreparedStatement) conn.prepareStatement(sql);
                    u = pst.executeUpdate();
                    pst.close();
                    Log.d("OffreAjouteCandidat", "ok sql");
                } catch (SQLException e) {
                    Log.e("OffreAjouteCandidat", "err sql");
                }
                try {
                    conn.close();
                    Log.d("OffreAjouteCandidat", "ok close bdd");
                } catch (SQLException e) {
                    Log.e("OffreAjouteCandidat", "err close bdd");
                }
            }
        });
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("OffreAjouteCandidat", "fini");
    }

    public void ModiferUser(User usr){
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                int u=0;
                Connection conn = null;
                conn =(Connection) DBOpenHelper.getConn();
                String sql = "UPDATE user "+
                        "SET nom = '" + usr.getNom() + "',"+
                        "prenom = '" + usr.getPrenom() + "',"+
                        "isArtiste = '" + usr.getIsArtiste() + "',"+
                        "isEmployer = '" + usr.getIsEmployer() + "' "+
                        "WHERE id ='"+usr.getId()+"'";
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
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("ModiferUser", "fini");
    }

    public void ModiferArtiste(Artiste art){
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                int u=0;
                Connection conn = null;
                conn =(Connection) DBOpenHelper.getConn();
                String sql = "UPDATE artiste "+
                        "SET "+
                        "profession = '" + art.getProfession() + "',"+
                        "niveau = '" + art.getNiveau() + "',"+
                        "cv = '" + art.getCv() + "',"+
                        "oeuvre = '" + art.getOevre() + "'"+
                        "WHERE id ='"+art.getId()+"'";
                PreparedStatement pst;
                try {
                    pst = (PreparedStatement) conn.prepareStatement(sql);
                    u = pst.executeUpdate();
                    pst.close();
                    Log.d("ModiferArtiste", "ok sql");
                } catch (SQLException e) {
                    Log.e("ModiferArtiste", "err sql");
                }
                try {
                    conn.close();
                    Log.d("ModiferArtiste", "ok close bdd");
                } catch (SQLException e) {
                    Log.e("ModiferArtiste", "err close bdd");
                }
            }
        });
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("ModiferArtiste", "fini");
    }

    public void ModiferEmployer(Employer emp){
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                int u=0;
                Connection conn = null;
                conn =(Connection) DBOpenHelper.getConn();
                String sql = "UPDATE employer "+
                        "SET "+
                        "type = '" + emp.getType() + "',"+
                        "certificat = '" + emp.getCertificat() + "'"+
                        "WHERE id ='"+emp.getId()+"'";
                PreparedStatement pst;
                try {
                    pst = (PreparedStatement) conn.prepareStatement(sql);
                    u = pst.executeUpdate();
                    pst.close();
                    Log.d("ModiferEmployer", "ok sql");
                } catch (SQLException e) {
                    Log.e("ModiferEmployer", "err sql");
                }
                try {
                    conn.close();
                    Log.d("ModiferEmployer", "ok close bdd");
                } catch (SQLException e) {
                    Log.e("ModiferEmployer", "err close bdd");
                }
            }
        });
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("ModiferArtiste", "fini");
    }

    public int findIdUser(String email){
        id=0;
        Thread a=new Thread(new Runnable() {
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
                    int cpt=0;
                    while (rSet.next()){
                        cpt=cpt+1;
                        Log.d("findIdUser", "user");
                        Log.d("findIdUser", "id\t\t\temail\t");
                        Log.d("findIdUser", rSet.getString("id") + "\t");
                        id=Integer.parseInt(rSet.getString("id"));
                    }
                    cpt=cpt-1;
                    // print info
                    if(cpt==0){
                        Log.d("findIdUser", "email ok");
                    }
                    else{
                        Log.e("findIdUser", "email inexiste ou plusieur");
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
        });
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void addArtiste(Artiste art){
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                int u = 0;
                conn =(Connection) DBOpenHelper.getConn();
                String sql = "insert into artiste (id, nom, prenom, email, isArtiste, isEmployer, profession, niveau, cv, oeuvre) values(?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst;
                try {
                    pst = (PreparedStatement) conn.prepareStatement(sql);
                    pst.setInt(1,art.getId());
                    pst.setString(2,art.getNom());
                    pst.setString(3,art.getPrenom());
                    pst.setString(4,art.getEmail());
                    pst.setInt(5,art.getIsArtiste());
                    pst.setInt(6,art.getIsEmployer());
                    pst.setString(7,art.getProfession());
                    pst.setString(8,art.getNiveau());
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
                    Log.e("addArtiste","err sql");
                }
            }
        });
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("addArtiste", "fini");
    }

    public void addEmployer(Employer emp){
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                int u = 0;
                conn =(Connection) DBOpenHelper.getConn();
                String sql = "insert into employer (id, nom, prenom, email, isArtiste, isEmployer, type, certificat) values(?,?,?,?,?,?,?,?)";
                PreparedStatement pst;
                try {
                    pst = (PreparedStatement) conn.prepareStatement(sql);
                    pst.setInt(1,emp.getId());
                    pst.setString(2,emp.getNom());
                    pst.setString(3,emp.getPrenom());
                    pst.setString(4,emp.getEmail());
                    pst.setInt(5,emp.getIsArtiste());
                    pst.setInt(6,emp.getIsEmployer());
                    pst.setString(7,emp.getType());
                    Log.d("addEmployer",emp.getId()+" "+emp.getNom()+" "+emp.getEmail()+" "+emp.getType());
                    if(emp.getCertificat().isEmpty()==false){
                        pst.setString(8,emp.getCertificat());
                    }
                    else{
                        pst.setString(8,"");
                    }
                    u = pst.executeUpdate();
                    pst.close();
                    conn.close();
                } catch (SQLException e) {
                    Log.e("addEmployer","err sql");
                }
            }
        });
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("addEmployer", "fini");
    }

    public void addOffre(Offre offre){
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                int u = 0;
                conn =(Connection) DBOpenHelper.getConn();
                String sql = "insert into offre (titre, description, argent, nbCandidate, recurrence, durre, adresse, typeOffre, author, candidate) values(?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst;
                try {
                    pst = (PreparedStatement) conn.prepareStatement(sql);
                    pst.setString(1,offre.getTitre());
                    pst.setString(2,offre.getDescription());
                    pst.setInt(3,offre.getArgent());
                    pst.setInt(4,offre.getNbCandidate());
                    pst.setString(5,offre.getRecurrence());
                    pst.setString(6,offre.getDurre());
                    pst.setString(7,offre.getAdresse());
                    pst.setString(8,offre.getTypeOffre());
                    pst.setInt(9,offre.getAuthor());
                    pst.setString(10,offre.getCandidate());
                    u = pst.executeUpdate();
                    pst.close();
                    conn.close();
                } catch (SQLException e) {
                    Log.e("addOffre","err sql");
                }
            }
        });
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("addOffre", "fini");
    }

    public List<Offre> findOffres(){
        List<Offre> listOffre = new ArrayList<Offre>();
        Thread a=new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                String sql = "select * from offre";
                Statement st;
                conn =(Connection) DBOpenHelper.getConn();
                Log.d("findOffres", "ok connect bdd");
                try {
                    // creat objet connect
                    java.sql.Statement statement = conn.createStatement();
                    Log.d("findOffres", "ok creat sql");
                    // execute sql
                    ResultSet rSet = statement.executeQuery(sql);
                    while (rSet.next()){
                        Offre of = new Offre();
                        of.setId(rSet.getInt("id"));
                        of.setTitre(rSet.getString("titre"));
                        of.setDescription(rSet.getString("description"));
                        of.setArgent(rSet.getInt("argent"));
                        of.setRecurrence(rSet.getString("recurrence"));
                        of.setNbCandidate(rSet.getInt("nbCandidate"));
                        of.setDurre(rSet.getString("durre"));
                        of.setAdresse(rSet.getString("adresse"));
                        of.setTypeOffre(rSet.getString("typeOffre"));
                        of.setAuthor(rSet.getInt("author"));
                        of.setCandidate(rSet.getString("candidate"));
                        listOffre.add(of);
                    }
                } catch (SQLException e) {
                    Log.e("findOffres", "err sql");
                }
                //close bdd
                try {
                    conn.close();
                    Log.d("findOffres", "ok close bdd");
                } catch (SQLException e) {
                    Log.d("findOffres", "err close bdd");
                }
                return;
            }
        });
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return listOffre;
    }

    public List<Artiste> findArtistes(){
        List<Artiste> listArtiste = new ArrayList<Artiste>();
        Thread a=new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                String sql = "SELECT * FROM artiste ";
                Statement st;
                conn =(Connection) DBOpenHelper.getConn();
                Log.d("findIdUser", "ok connect bdd");
                try {
                    // creat objet connect
                    java.sql.Statement statement = conn.createStatement();
                    Log.d("findArtistes", "ok creat sql");
                    // execute sql
                    ResultSet rSet = statement.executeQuery(sql);
                    while (rSet.next()){
                        Artiste art = new Artiste();
                        art.setId(rSet.getInt("id"));
                        art.setOevre(rSet.getString("oeuvre"));
                        art.setCv(rSet.getString("cv"));
                        art.setEmail(rSet.getString("email"));
                        art.setNiveau(rSet.getString("niveau"));
                        art.setProfession(rSet.getString("profession"));
                        art.setPrenom(rSet.getString("prenom"));
                        art.setNom(rSet.getString("nom"));
                        art.setIsEmployer(rSet.getInt("isEmployer"));
                        art.setIsArtiste(rSet.getInt("isArtiste"));
                        art.setMdp(rSet.getString("mdp"));
                        listArtiste.add(art);
                    }
                } catch (SQLException e) {
                    Log.e("findArtistes", "err sql");
                }
                //close bdd
                try {
                    conn.close();
                    Log.d("findArtistes", "ok close bdd");
                } catch (SQLException e) {
                    Log.d("findArtistes", "err close bdd");
                }
                return;
            }
        });
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return listArtiste;
    }

    public List<Offre> findOffresAvecAuthors(int AuthorsID){
        List<Offre> listOffre = new ArrayList<Offre>();
        Thread a=new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                String sql = "select * from offre where author='"+AuthorsID+"'";
                Statement st;
                conn =(Connection) DBOpenHelper.getConn();
                Log.d("findOffres", "ok connect bdd");
                try {
                    // creat objet connect
                    java.sql.Statement statement = conn.createStatement();
                    Log.d("findOffres", "ok creat sql");
                    // execute sql
                    ResultSet rSet = statement.executeQuery(sql);
                    while (rSet.next()){
                        Offre of = new Offre();
                        of.setId(rSet.getInt("id"));
                        of.setTitre(rSet.getString("titre"));
                        of.setDescription(rSet.getString("description"));
                        of.setArgent(rSet.getInt("argent"));
                        of.setRecurrence(rSet.getString("recurrence"));
                        of.setNbCandidate(rSet.getInt("nbCandidate"));
                        of.setDurre(rSet.getString("durre"));
                        of.setAdresse(rSet.getString("adresse"));
                        of.setTypeOffre(rSet.getString("typeOffre"));
                        of.setAuthor(rSet.getInt("author"));
                        of.setCandidate(rSet.getString("candidate"));
                        listOffre.add(of);
                    }
                } catch (SQLException e) {
                    Log.e("findOffres", "err sql");
                }
                //close bdd
                try {
                    conn.close();
                    Log.d("findOffres", "ok close bdd");
                } catch (SQLException e) {
                    Log.d("findOffres", "err close bdd");
                }
                return;
            }
        });
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return listOffre;
    }

    public Artiste findUnArtistes(int id){
        Artiste art = new Artiste();
        Thread a=new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                String sql = "SELECT * FROM artiste WHERE id='"+id+"'";
                Statement st;
                conn =(Connection) DBOpenHelper.getConn();
                Log.d("findUnArtistes", "ok connect bdd");
                try {
                    // creat objet connect
                    java.sql.Statement statement = conn.createStatement();
                    Log.d("findUnArtistes", "ok creat sql");
                    // execute sql
                    ResultSet rSet = statement.executeQuery(sql);
                    while (rSet.next()){
                        art.setId(rSet.getInt("id"));
                        art.setOevre(rSet.getString("oeuvre"));
                        art.setCv(rSet.getString("cv"));
                        art.setEmail(rSet.getString("email"));
                        art.setNiveau(rSet.getString("niveau"));
                        art.setProfession(rSet.getString("profession"));
                        art.setPrenom(rSet.getString("prenom"));
                        art.setNom(rSet.getString("nom"));
                        art.setIsEmployer(rSet.getInt("isEmployer"));
                        art.setIsArtiste(rSet.getInt("isArtiste"));
                        art.setMdp(rSet.getString("mdp"));
                    }

                } catch (SQLException e) {
                    Log.e("findUnArtistes", "err sql");
                }
                //close bdd
                try {
                    conn.close();
                    Log.d("findUnArtistes", "ok close bdd");
                } catch (SQLException e) {
                    Log.d("findUnArtistes", "err close bdd");
                }
                return;
            }
        });
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return art;
    }

    public Offre findUnOffres(int id){
        Offre of=new Offre();
        Thread a=new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                String sql = "select * from offre where id='"+id+"'";
                Statement st;
                conn =(Connection) DBOpenHelper.getConn();
                Log.d("findUnOffres", "ok connect bdd");
                try {
                    // creat objet connect
                    java.sql.Statement statement = conn.createStatement();
                    Log.d("findUnOffres", "ok creat sql");
                    // execute sql
                    ResultSet rSet = statement.executeQuery(sql);
                    while (rSet.next()){
                        of.setId(rSet.getInt("id"));
                        of.setTitre(rSet.getString("titre"));
                        of.setDescription(rSet.getString("description"));
                        of.setArgent(rSet.getInt("argent"));
                        of.setRecurrence(rSet.getString("recurrence"));
                        of.setNbCandidate(rSet.getInt("nbCandidate"));
                        of.setDurre(rSet.getString("durre"));
                        of.setAdresse(rSet.getString("adresse"));
                        of.setTypeOffre(rSet.getString("typeOffre"));
                        of.setAuthor(rSet.getInt("author"));
                        of.setCandidate(rSet.getString("candidate"));
                    }
                } catch (SQLException e) {
                    Log.e("findUnOffres", "err sql");
                }
                //close bdd
                try {
                    conn.close();
                    Log.d("findUnOffres", "ok close bdd");
                } catch (SQLException e) {
                    Log.d("findUnOffres", "err close bdd");
                }
                return;
            }
        });
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return of;
    }

    public User findUnUser(int id){
        User usr = new User();
        Thread a=new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                String sql = "SELECT * FROM user WHERE id='"+id+"'";
                Statement st;
                conn =(Connection) DBOpenHelper.getConn();
                Log.d("findUnUser", "ok connect bdd");
                try {
                    // creat objet connect
                    java.sql.Statement statement = conn.createStatement();
                    Log.d("findUnUser", "ok creat sql");
                    // execute sql
                    ResultSet rSet = statement.executeQuery(sql);
                    while (rSet.next()){
                        usr.setId(rSet.getInt("id"));
                        usr.setEmail(rSet.getString("email"));
                        usr.setPrenom(rSet.getString("prenom"));
                        usr.setNom(rSet.getString("nom"));
                        usr.setIsEmployer(rSet.getInt("isEmployer"));
                        usr.setIsArtiste(rSet.getInt("isArtiste"));
                        usr.setMdp(rSet.getString("mdp"));
                    }

                } catch (SQLException e) {
                    Log.e("findUnUser", "err sql");
                }
                //close bdd
                try {
                    conn.close();
                    Log.d("findUnUser", "ok close bdd");
                } catch (SQLException e) {
                    Log.d("findUnUser", "err close bdd");
                }
                return;
            }
        });
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return usr;
    }

    public Employer findUnEmployer(int id){
        Employer emp = new Employer();
        Thread a=new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                String sql = "SELECT * FROM employer WHERE id='"+id+"'";
                Statement st;
                conn =(Connection) DBOpenHelper.getConn();
                Log.d("findUnEmployer", "ok connect bdd");
                try {
                    // creat objet connect
                    java.sql.Statement statement = conn.createStatement();
                    Log.d("findUnEmployer", "ok creat sql");
                    // execute sql
                    ResultSet rSet = statement.executeQuery(sql);
                    while (rSet.next()){
                        emp.setId(rSet.getInt("id"));
                        emp.setEmail(rSet.getString("email"));
                        emp.setPrenom(rSet.getString("prenom"));
                        emp.setNom(rSet.getString("nom"));
                        emp.setIsEmployer(rSet.getInt("isEmployer"));
                        emp.setIsArtiste(rSet.getInt("isArtiste"));
                        emp.setMdp(rSet.getString("mdp"));
                        emp.setType(rSet.getString("type"));
                        emp.setCertificat(rSet.getString("certificat"));
                    }

                } catch (SQLException e) {
                    Log.e("findUnEmployer", "err sql");
                }
                //close bdd
                try {
                    conn.close();
                    Log.d("findUnEmployer", "ok close bdd");
                } catch (SQLException e) {
                    Log.d("findUnEmployer", "err close bdd");
                }
                return;
            }
        });
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return emp;
    }
}
