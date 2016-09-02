package co.be.api.generator.common.util;

import java.util.PropertyResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jax
 * @date 10/02/2016
 */
public class Properties {
    
    private static final Logger LOG = Logger.getLogger(Properties.class.getName());
    private static final String NAME_PROPERTIES = "api-generator";

    private Properties() {
    }

    /**
     * Lee una propiedad
     *
     * @param name
     * @return
     */
    public static String getProperty(String name) {

        PropertyResourceBundle b = (PropertyResourceBundle) PropertyResourceBundle.getBundle(NAME_PROPERTIES);
        try {
            return b.getString(name);
        } catch (Exception e) {
            LOG.log(Level.WARNING, "getProperty", e);
        }
        return null;

    }
}