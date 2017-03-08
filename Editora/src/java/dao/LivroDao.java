/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import conexao.ConectaBancoLivro;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Livro;

/**
 *
 * @author Marcelo
 */
public class LivroDao {
    
    private ConectaBancoLivro conectaBancoLivro;
    private Connection conection;
    private Livro livro;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;

    // Metodo que insere um novo livro no banco de dados.
    public void insertLivro(Livro livro) {
        // Cria um novo objeto de ConectaBancoLivro.
        conectaBancoLivro = new ConectaBancoLivro();
        try {
            // Cria uma conexão com o banco de dados.
            conection = conectaBancoLivro.conexaoBancoLivro();
            // Cria um comando de SQL Script para ser passado ao banco de dados.
            String sql = "INSERT INTO Livro (Titulo,"
                    + " Editora,"
                    + " Autor,"
                    + " Edicao,"
                    + " AnoLancamento,"
                    + " NumeroPaginas)"
                    + " VALUES (?,?,?,?,?,?)";

            // Cria um PrepareStatement para receber um sql String.
            PreparedStatement ps = conection.prepareStatement(sql);
            // Seta valores que o ps ira receber.
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getEditora());
            ps.setString(3, livro.getAutor());
            ps.setString(4, livro.getEdicao());
            ps.setInt(5, livro.getAnoLancamento());
            ps.setInt(6, livro.getNumeroPaginas());

            // Manda gravar no Banco de dados.
            ps.execute();

            // Fecha a conexão com o banco de dados.
            conection.close();
            
        } catch (Exception ex) {
            Logger.getLogger(LivroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Metodo que lista dados de Livro do banco de dados.
    public List<Livro> listaLivro() {
        List<Livro> listaLisvro = new ArrayList<>();

        // Cria um novo objeto de ConectaBancoLivro.
        conectaBancoLivro = new ConectaBancoLivro();
        // Cria scrip SQL.
        String sql = "SELECT CodigoLivro, Titulo, Editora, Autor, Edicao, AnoLancamento, NumeroPaginas FROM Livro";
        try {
            // Cria uma conexão com o banco de dados.
            conection = conectaBancoLivro.conexaoBancoLivro();
            st = conection.createStatement();
            rs = st.executeQuery(sql);

            // Começa um loop para pegar e adicionar os dados em um objjeto livro.
            while (rs.next()) {
                int id_livro = rs.getInt("CodigoLivro");
                String titulo = rs.getString("Titulo");
                String editora = rs.getString("Editora");
                String autor = rs.getString("Autor");
                String edicao = rs.getString("Edicao");
                int anoLancamento = rs.getInt("AnoLancamento");
                int numeroPaginas = rs.getInt("NumeroPaginas");

                // Cria um objeto Livro.
                livro = new Livro();
                livro.setId_livro(id_livro);
                livro.setTitulo(titulo);
                livro.setEditora(editora);
                livro.setAutor(autor);
                livro.setEdicao(edicao);
                livro.setAnoLancamento(anoLancamento);
                livro.setNumeroPaginas(numeroPaginas);

                // Adiciona o objeto criado a lista de livros.
                listaLisvro.add(livro);
            }
        } catch (Exception e) {
        }
        
        try {
            // Fecha a conexão com o banco.
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(LivroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaLisvro;
    }

    // Metodo para buscar um dado por Id.
    public Livro listaLivroId(int idLivro) throws Exception {
        // Cria um novo objeto Livro.
        Livro livro = new Livro();
        // Cria um objeto do tipo conectaBancoLivro
        conectaBancoLivro = new ConectaBancoLivro();
        PreparedStatement ps = null;
        try {
            // Cria um novo objeto de ConectaBancoLivro.
            conection = conectaBancoLivro.conexaoBancoLivro();
            // Cria Script SQL de select por ID.
            String sql = "SELECT CodigoLivro,"
                    + "Titulo,"
                    + " Editora,"
                    + " Autor, "
                    + "Edicao,"
                    + " AnoLancamento,"
                    + " NumeroPaginas "
                    + "FROM Livro"
                    + " WHERE CodigoLivro = ? ";
            ps = conection.prepareStatement(sql);
            ps.setInt(1, idLivro);
            // Adiciona a conexão ao ResultSet.
            rs = ps.executeQuery();
            
            while (rs.next()) {
                // Seta no objeto livro os valores retornados do baco de dados.
                livro.setId_livro(rs.getInt("CodigoLivro"));
                livro.setTitulo(rs.getString("Titulo"));
                livro.setEditora(rs.getString("Editora"));
                livro.setAutor(rs.getString("Autor"));
                livro.setEdicao(rs.getString("Edicao"));
                livro.setAnoLancamento(rs.getInt("AnoLancamento"));
                livro.setNumeroPaginas(rs.getInt("NumeroPaginas"));
            }
            
        } catch (Exception e) {
        }
        try {
            // Fecha a conexão com o banco.
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(LivroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return livro;
    }

    // Metodo para editar uma nota.
    public void editaLivro(Livro livro, int idLivro) throws Exception {
        // Cria um objeto do tipo conectaBancoLivro
        conectaBancoLivro = new ConectaBancoLivro();
        
        try {
            // Cria um novo objeto de ConectaBancoLivro.
            conection = conectaBancoLivro.conexaoBancoLivro();
            // Cria Script SQL de select por ID.
            String sql = "UPDATE Livro SET Titulo = ?,"
                    + " Editora = ?,"
                    + " Autor = ?, "
                    + " Edicao = ?, "
                    + " AnoLancamento = ?, "
                    + " NumeroPaginas = ?"
                    + " WHERE CodigoLivro = ?";

            // Cria um objeto PreparedStatement.
            ps = conection.prepareStatement(sql);
            // Seta novos valores de livro.
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getEditora());
            ps.setString(3, livro.getAutor());
            ps.setString(4, livro.getEdicao());
            ps.setInt(5, livro.getAnoLancamento());
            ps.setInt(6, livro.getNumeroPaginas());
            ps.setInt(7, idLivro);
            ps.executeQuery();
            // Fecha a conexão.
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metodo para deletar livro do banco de dados.
    public void deleteLivro(int idLivro) throws Exception {
        // Cria um objeto do tipo conectaBancoLivro
        conectaBancoLivro = new ConectaBancoLivro();
        try {
            // Cria um novo objeto de ConectaBancoLivro.
            conection = conectaBancoLivro.conexaoBancoLivro();
            // Cria Script SQL de select por ID.
            String sql = "DELETE FROM Livro WHERE CodigoLivro = ?";
            
            ps = conection.prepareStatement(sql);
            ps.setInt(1, idLivro);
            ps.executeQuery();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
