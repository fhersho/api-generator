package co.be.api.generator.datasource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

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
			
			if ("true".equals(Properties.getProperty("colbitex.db.standalone"))){
				ResourceBundle b = ResourceBundle.getBundle("colbitex");
				BasicDataSource bds = new BasicDataSource();
				bds.setDriverClassName(b.getString("colbitex.jdbc.driverClassName"));
				bds.setUrl(b.getString("colbitex.jdbc.url"));
				bds.setUsername(b.getString("colbitex.jdbc.user"));
				bds.setPassword(b.getString("colbitex.jdbc.password"));
				dataSource = bds;
				
			}
			else{
				dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/colbitex");
			}
			
			
		} catch (NamingException ne) {
			LOG.log(Level.SEVERE, ne.getMessage(), ne);
		}
	}

	public static Connection getConnection() throws SQLException {
			return dataSource.getConnection();
	}

	public static QueryRunner getQueryRunner() {
			return new QueryRunner(dataSource);
	}
	

    


}