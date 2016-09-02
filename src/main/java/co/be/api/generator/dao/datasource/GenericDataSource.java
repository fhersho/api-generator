package co.be.api.generator.dao.datasource;

import co.be.api.generator.common.util.Properties;
import co.be.api.generator.dao.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author jax
 * @date 10/02/2016
 */
public class GenericDataSource {

    private static DataSource dataSource;
    private static final Logger LOG = Logger.getLogger(GenericDataSource.class.getName());
    private static final boolean TEST = true;
    
    static {
        try {
            if(TEST){
                BasicDataSource bds = new BasicDataSource();
                bds.setDriverClassName(Properties.getProperty("jdbc.driverClassName"));
                bds.setUrl(Properties.getProperty("jdbc.url"));
                bds.setUsername(Properties.getProperty("jdbc.user"));
                bds.setPassword(Properties.getProperty("jdbc.password"));
                dataSource = bds;
            }else{
                dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/colbitex");
            }
            

        } catch (NamingException ne) {
            LOG.log(Level.SEVERE, ne.getMessage(), ne);
        }
    }

    public static Connection getConnection() throws DAOException {
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            throw new DAOException(ex);
        }
    }
}
