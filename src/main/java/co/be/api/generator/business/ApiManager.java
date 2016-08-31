package co.be.api.generator.business;

import co.be.api.generator.dao.BDManager;
import co.be.api.generator.dao.exception.DAOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando Padilla
 * @date 31/08/2016
 */
public class ApiManager {
    /**
     *
     * @param idQuery
     * @return 
     */
    public String query(int idQuery){
        try {
            BDManager bDManager = new BDManager();
            bDManager.query("select id, name, mail, status_id, phone, "
                    + "second_factor, activation_key, address, identity_id, "
                    + "identity_type, country_code from user_account", null);
        } catch (DAOException ex) {
            Logger.getLogger(ApiManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
