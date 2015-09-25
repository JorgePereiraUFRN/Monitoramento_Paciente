package br.ufrn.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class acessoBD {
    Connection con;
   
   
    public Connection conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/monitoramentopaciente", "root", "qwe123");
        } catch (SQLException ex) {
            Logger.getLogger(acessoBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(acessoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;

    }
    
    public void desconectar(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(acessoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
