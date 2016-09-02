/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.be.api.generator.business;

import co.be.api.generator.business.exception.BusinessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fernando Padilla
 */
public class ApiManagerTest {
    
    @Test
    public void testQuery() {
        try {
            System.out.println("query");
            int idQuery = 0;
            ApiManager instance = new ApiManager();
            String result = instance.query(idQuery,"[{value:\"0\"}]");
            assertNotNull(result);
        } catch (BusinessException ex) {
            Logger.getLogger(ApiManagerTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
    }
    
}
