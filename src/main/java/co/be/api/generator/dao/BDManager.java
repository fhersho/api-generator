package co.be.api.generator.dao;

import co.be.api.generator.dao.datasource.GenericDataSource;
import co.be.api.generator.dao.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para el acceso y manipulacion de base de datos.
 * 
 * @author fernando padilla, fhersho@gmail.com
 * @date 31/08/2016
 */
public class BDManager {

    private static final Logger LOG = Logger.getLogger(BDManager.class.getName());

    /**
     *
     * @param sql
     * @param params
     * @return
     * @throws DAOException
     */
    public String query(String sql, List<Object> params) throws DAOException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = GenericDataSource.getConnection();
            
            pstmt = conn.prepareStatement(sql);
            
            if(params != null){
                for (int i = 0; i < params.size(); i++) {
                    pstmt.setObject(i, params.get(i));
                }
            }

            rs = pstmt.executeQuery();
            
            if (rs.next()) {

            }

        } catch (SQLException e) {
            LOG.log(Level.SEVERE,"", e);
            throw new DAOException(e.getMessage());
        } finally {
            GenericDataSource.close(conn, pstmt, rs);
        }
        return "";
    }
}
