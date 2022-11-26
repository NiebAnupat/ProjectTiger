package com.tiger.Class;
import java.sql.*;

public class DB_Connector {


    // <editor-fold defaultstate="collapsed" desc="Database Connection">
    // ----------------สร้าง Object ที่ใช้กับ Database---------------------
    private Connection conn = null;
    private Statement st = null;
    private ResultSet rs = null;
    // ---------------------------------------------------------------
    // ----------------URL สำหรับเชื่อมฐานข้อมูลบน Server-------------------
    private String url = "jdbc:mysql://localhost:3306/project_tiger";
    // ---------------------------------------------------------------
    //----------------------User ที่ใช้ Database-------------------------
    private String username = "root";
    private String password = "";
    // ---------------------------------------------------------------
    // </editor-fold>


    // -------Method for connect Database--------
    public void connectDB() throws SQLException {
        try{
            conn = DriverManager.getConnection(url,username,password);
//            System.out.println("Connected to Database");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            myAlert alert = new myAlert();
            alert.showErrorAlert("ไม่สามารถเชื่อมต่อฐานข้อมูลได้");
        }
    }
    // ----------------------------------------------

    // -------Method for get connection Database--------
    public Connection getConnection() throws SQLException {
        connectDB();
        return conn;
    }


    // ------Method for disconnect database--------
    public void disconnect() throws SQLException {
        try {
            conn.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    // -----------------------------------------------

    // -----------Method for execute Sql-----------------
    public boolean execute (String query) throws SQLException {
        boolean rs;
        try{
            connectDB();
            st = conn.createStatement();
            st.execute(query);
            rs = true;
            disconnect();
//            System.out.println("Execute Success");
        }catch (SQLException ex){
            rs = false;
            System.out.println(ex.getMessage());
        }
        return rs;
    }
    // ------------------------------------------------------------

    // ----------Method get ResultSet from execute-------------
    public ResultSet getResultSet (String query) throws SQLException {
        try{
            connectDB();
            st = conn.createStatement();
            rs = st.executeQuery(query);
//            System.out.println("Get ResultSet Success");
        }catch (SQLException ex){
            rs = null;
            System.out.println(ex.getMessage());
        }
        return rs;
    }
    // ---------------------------------------------------------------------


}