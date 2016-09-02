package co.be.api.generator.business;

import co.be.api.generator.business.exception.BusinessException;
import co.be.api.generator.dao.DBDAO;
import co.be.api.generator.dao.datasource.GenericDataSource;
import co.be.api.generator.dao.exception.DAOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando Padilla
 * @date 31/08/2016
 */
public class ApiManager {
    
    private static final Logger LOG = Logger.getLogger(ApiManager.class.getName());
    
    /**
     *
     * @param idQuery
     * @return 
     * @throws co.be.api.generator.business.exception.BusinessException 
     */
    public String query(int idQuery, String params) throws BusinessException{
        String query = null;
        try {
            Connection connection = GenericDataSource.getConnection();
            DBDAO db = new DBDAO();
            query = db.query(connection, "select id, name, mail, status_id as aja, phone, "
                    + "second_factor, activation_key, address, identity_id, "
                    + "identity_type, country_code from user_account where id = ?", params);
        } catch (DAOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            Logger.getLogger(ApiManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return query;
    }
}
