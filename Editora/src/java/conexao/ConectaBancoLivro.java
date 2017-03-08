/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo
 */
public class ConectaBancoLivro {

    public static final String DRIVE = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Editora";
    public static final String USUARIO = "sa";
    public static final String SENHA = "Magnata25";
    public Connection connection;

    // Cria a conexão com o banco de dados.
    public Connection conexaoBancoLivro() throws Exception {
        try {
            Class.forName(DRIVE).newInstance();
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ConectaBancoLivro.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Retorna uma conexão.
        return connection;
    }
}
