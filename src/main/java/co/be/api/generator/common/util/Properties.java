package co.be.api.generator.common.util;

import java.text.MessageFormat;
import java.util.Locale;
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
	public static final ThreadLocal<String> userLang = new ThreadLocal<>();
	private static final String NAME_PROPERTIES = "colbitex";
	private static final String ERRORS_PROPERTIES = "errores";

	private Properties(){}
	/**
	 * Lee una propiedad de mensajes
	 * 
	 * @param name
	 * @return
	 */
	public static String getProperty(String name) {
		String lang = userLang.get();
		if (lang == null)
			lang = "es";
		Locale locale = new Locale(lang.toLowerCase());
		PropertyResourceBundle b = (PropertyResourceBundle) PropertyResourceBundle.getBundle(NAME_PROPERTIES, locale);
		try {
			return b.getString(name);
		} catch (Exception e) {
			LOG.log(Level.WARNING, "getProperty", e);
			return lang + ":MESSAGE_CODE:" + name;
		}
	}

	/**
	 * lee una propiedad y combina los parametros recibidos en el mensaje
	 * 
	 * @param name
	 * @param parameters
	 * @return
	 */
	public static String getPropertyMessage(String name, Object... parameters) {
		return MessageFormat.format(getProperty(name), parameters);
	}

	/**
	 * lee una propiedad y combina los parametros recibidos en el mensaje de
	 * error
	 * 
	 * @param name
	 * @param parameters
	 * @return
	 */
	public static String getErrorPropertyMessage(String name, Object... parameters) {
		return MessageFormat.format(getErrorProperty(name), parameters);
	}

	/**
	 * lee una propiedad y combina los parametros recibidos en el mensaje,
	 * especificando antes el lenguaje
	 * 
	 * @param name
	 * @param parameters
	 * @return
	 */
	public static String getPropertyMessageLang(String name, String lang, Object... parameters) {
		userLang.set(lang);
		return MessageFormat.format(getProperty(name), parameters);
	}

	/**
	 * lee una propiedad y combina los parametros recibidos en el mensaje,
	 * especificando antes el lenguaje
	 * 
	 * @param name
	 * @param parameters
	 * @return
	 */
	public static String getErrorPropertyLang(String name, String lang, Object... parameters) {
		userLang.set(lang);
		return MessageFormat.format(getErrorProperty(name), parameters);
	}

	/**
	 * lee una propiedad de errores
	 * 
	 * @param name
	 * @return
	 */
	public static String getErrorProperty(String name) {
		String lang = userLang.get();
		if (lang == null)
			lang = "es";
		try {
			return ((PropertyResourceBundle) PropertyResourceBundle.getBundle(ERRORS_PROPERTIES,
					new Locale(lang.toLowerCase()))).getString(name);
		} catch (Exception e) {
			LOG.log(Level.WARNING, "getErrorProperty", e);
			return lang + ":ERROR_CODE:" + name;
		}
	}
	
	/**
	 * Lee un mensaje de error en el lenguaje especificado
	 * @param name
	 * @param lang
	 * @return
	 */
	public static String getErrorPropertyLang(String name,String lang) {
		userLang.set(lang);
		return  getErrorProperty(name);
	}
	
	/**
	 * Lee un mensaje de propiedad en el lenguaje especificado
	 * @param name
	 * @param lang
	 * @return
	 */
	public static String getPropertyLang(String name,String lang) {
		userLang.set(lang);
		return  getProperty(name);
	}
	
	
	
}