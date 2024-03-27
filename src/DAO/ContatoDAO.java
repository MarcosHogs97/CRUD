package DAO;

import Factory.ConnectionFactory;
import Model.Contato;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    /*
     * CRUD:
     * C:CREATE
     * R:READ
     * U:UPDATE
     * D:DELETE
     * */

    // METODO PARA SALVAR (CREATE)
    public void save(Contato contato) {
        String sql = "INSERT INTO contato(NOME, IDADE, DATACADASTRO) VALUES (?,?,?);";
        // ? é uma marcação para substituição automática dos parâmetros na execução da string.
        Connection conn = null;
        PreparedStatement pstmt = null;
        // Declaração de uma variável que será utilizada para preparar e executar consultas SQL parametrizadas.

        //Tenta conectar ao banco
        try {
            // Estabelece uma conexão com o banco de dados MySQL
            conn = ConnectionFactory.creatConnectionToMysql();
            // Prepara uma consulta parametrizada para inserção de dados
            pstmt = conn.prepareStatement(sql);
            // Define o valor do primeiro parâmetro como o nome do contato
            pstmt.setString(1, contato.getNOME());
            // Define o valor do segundo parâmetro como a idade do contato
            pstmt.setInt(2, contato.getIDADE());
            // Define o valor do terceiro parâmetro como a data de cadastro do contato
            pstmt.setDate(3, new Date(contato.getDATACADASTRO().getTime()));
            // Executa a inserção dos dados no banco de dados
            pstmt.executeUpdate();
        } catch (Exception e) {
            // Em caso de exceção, imprime o rastreamento da pilha de erros
            e.printStackTrace();
        } finally {
            try {
                // Fecha o PreparedStatement para liberar recursos
                if (pstmt != null) {
                    pstmt.close();
                }
                // Fecha a conexão com o banco de dados para liberar recursos
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                // Em caso de exceção ao fechar os recursos, imprime o rastreamento da pilha de erros
                e2.printStackTrace();
            }
        }
    }

    //método UPDATE (atualizar)
    public void update(Contato contato) {
        String sql = "UPDATE contato SET NOME = ?, IDADE = ?, DATACADASTRO = ? WHERE ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionFactory.creatConnectionToMysql();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, contato.getNOME());
            pstmt.setInt(2, contato.getIDADE());
            pstmt.setDate(3, new Date(contato.getDATACADASTRO().getTime()));
            pstmt.setInt(4, contato.getID());

            // Executa a query de atualização
            pstmt.executeUpdate(); // Esta linha executa a atualização no banco de dados

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    //método Listar contatos
    public List<Contato> getContato(){
        // Query SQL para selecionar todos os contatos da tabela
        String sql = "SELECT * FROM contato;";
        // Declaração de objetos de conexão e preparação de consulta
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        // Declaração de uma variável que será utilizada para preparar e executar consultas SQL parametrizadas.

        // Adiciona o contato na lista de contatos
        List<Contato> contatos = new ArrayList<>();

        try{
            // Estabelece uma conexão com o banco de dados MySQL
            conn = ConnectionFactory.creatConnectionToMysql();
            // Prepara uma consulta parametrizada para inserção de dados
            pstmt = conn.prepareStatement(sql);
            // Executa a inserção dos dados no banco de dados
            rset = pstmt.executeQuery();
            while (rset.next()){
                // Cria um novo contato
                Contato contato = new Contato();
                // Define o ID do contato
                contato.setID(rset.getInt("ID"));
                // Define o nome do contato
                contato.setNOME(rset.getString("NOME"));
                // Define a idade do contato
                contato.setIDADE(rset.getInt("IDADE"));
                // Define a data de cadastro do contato
                contato.setDATACADASTRO(rset.getDate("DATACADASTRO"));

                contatos.add(contato);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                // Fecha o PreparedStatement para liberar recursos
                if (pstmt != null) {
                    pstmt.close();
                }
                // Fecha a conexão com o banco de dados para liberar recursos
                if (conn != null) {
                    conn.close();
                }
                // Fecha a rset com o banco de dados para liberar recursos
                if (rset != null) {
                    rset.close();
                }
            } catch (Exception e2) {
                // Em caso de exceção ao fechar os recursos, imprime o rastreamento da pilha de erros
                e2.printStackTrace();
            }
        }
        return contatos;
    }

    //Delete contato
    public void deleteById(int ID){
        // Query SQL para selecionar todos os contatos da tabela
        String sql = "DELETE FROM contato WHERE ID = ?;";
        // Declaração de objetos de conexão e preparação de consulta
        Connection conn = null;
        PreparedStatement pstm = null;
        // Declaração de uma variável que será utilizada para preparar e executar consultas SQL parametrizadas.

        try{
            // Estabelece uma conexão com o banco de dados MySQL
            conn = ConnectionFactory.creatConnectionToMysql();
            // Prepara uma consulta parametrizada para inserção de dados
            pstm = conn.prepareStatement(sql);
            // Executa a inserção dos dados no banco de dados
            pstm.setInt(1, ID);
            pstm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                // Fecha o PreparedStatement para liberar recursos
                if (pstm != null) {
                    pstm.close();
                }
                // Fecha a conexão com o banco de dados para liberar recursos
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                // Em caso de exceção ao fechar os recursos, imprime o rastreamento da pilha de erros
                e2.printStackTrace();
            }
        }

    }
}
