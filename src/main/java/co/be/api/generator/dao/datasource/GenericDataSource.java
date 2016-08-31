package co.be.api.generator.dao.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author jax
 * @date 10/02/2016
 */
public class GenericDataSource {

    private static DataSource dataSource;
    private static final Logger LOG = Logger.getLogger(GenericDataSource.class.getName());

    static {
        try {

            dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/colbitex");

        } catch (NamingException ne) {
            LOG.log(Level.SEVERE, ne.getMessage(), ne);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            LOG.warning(e.getMessage());
        }

        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            LOG.warning(e.getMessage());
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            LOG.warning(e.getMessage());
        }
    }

    public static void close(Connection conn, PreparedStatement pstmt) {
        close(conn, pstmt, null);
    }
}
