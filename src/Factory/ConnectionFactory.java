package Factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    //Criar os parametros para poder acessar o banco de dados
    //Nome do usuario no MySQL
    private static final String USERNAME = "root";
    //Senha do usuario no MySQL
    private static final String PASSWORD = "Sen@iDev23";
    //Nome do banco de dados
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";
    //Criar uma conexão com o banco de dados
    public static Connection creatConnectionToMysql() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        return (Connection) connection;
    }
    //Fechar a conexão com o banco de dados
    public static void closeConnection(Connection connection) throws Exception {
        connection.close();
    }
    public static void main(String[] args) throws Exception {
        Connection con = (Connection) creatConnectionToMysql();

        if (con != null) {
            System.out.println("Conectado com sucesso!");
            con.close();
        }
    }
}