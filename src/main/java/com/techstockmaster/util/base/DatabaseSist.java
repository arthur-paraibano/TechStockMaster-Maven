package com.techstockmaster.util.base;

import com.techstockmaster.util.CriptografiaUtils;
import com.techstockmaster.util.DbException;
import com.techstockmaster.util.Message;

import java.sql.*;
import java.util.Properties;

/*
* Classe responsável por estabelecer a conexão com o banco de dados.
* Utiliza o padrão Singleton para garantir que apenas uma instância da conexão seja criada.
* */
public class DatabaseSist {
    private static Connection connection = null;
    private static final CriptografiaUtils criptoUtils = new CriptografiaUtils();

    /*
    * Metodo para carregar as propriedades descriptografadas do arquivo dbpessoal.properties.encrypted. Se ocorrer um erro, uma exceção DbException é lançada.
    * */
    private static Properties loadProperties() {
        Properties props = criptoUtils.obterPropriedadesDescriptografadas("dbpessoal.properties.encrypted");
        if (props != null) {
            return props;
        } else {
            Message.fatal(null, "Erro ao obter propriedades descriptografadas.");
            throw new DbException("Erro ao obter propriedades descriptografadas.");
        }
    }

    // check database connection
    public static Connection getConnection() {
        // padrão singleton
        try {
            Properties props = loadProperties();
            if (props != null) {
                String URL = props.getProperty("dburl");
                if (URL != null) {
                    if (connection == null || connection.isClosed()) {
                        connection = DriverManager.getConnection(URL, props);
                    }
                    return connection;
                } else {
                    System.err.println("A propriedade 'dburl' é nula.");
                }
            } else {
                System.err.println("As propriedades criptografadas são nulas.");
            }
        } catch (SQLException ex) {
            Message.fatal(null, "Conexão com o Banco de dados\n" + ex.getMessage());
        }
        return null;
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
                System.out.println("Conexão finalizada");
            }
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);
        try {
            if (stmt != null) {
                stmt.close();
                System.out.println("Conexão finalizada");
            }
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);

        try {
            if (rs != null) {
                rs.close();
                System.out.println("Conexão finalizada");
            }
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }
    }

    public static void testeConnection() {
        try {
            getConnection();
            System.out.println("Conexão estabelecida!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
