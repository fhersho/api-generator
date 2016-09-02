package co.be.api.generator.dao;

import co.be.api.generator.common.model.Param;
import co.be.api.generator.dao.exception.DAOException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

/**
 * Clase para el acceso y manipulacion de base de datos.
 * 
 * @author fernando padilla, fhersho@gmail.com
 * @date 31/08/2016
 */
public class DBDAO {

    private static final Logger LOG = Logger.getLogger(DBDAO.class.getName());

    /**
     *
     * @param connection 
     * @param sql
     * @param params
     * @return
     * @throws DAOException
     */
    public String query(Connection connection, String sql, String params) throws DAOException {
        List<Map<String, Object>> listOfMaps = null;
        try {
            Object[] parametros = getParametros(params);
            
            QueryRunner queryRunner = new QueryRunner();
            
            listOfMaps = queryRunner.query(connection, sql, new MapListHandler(), parametros);
        } catch (SQLException se) {
            LOG.log(Level.SEVERE, se.getMessage(), se);
            throw new DAOException(se.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return new Gson().toJson(listOfMaps);
    }

    private Object[] getParametros(String params) {
        Gson gson = new Gson();
        List<Param> items = gson.fromJson (params, new TypeToken<List<Param>>(){}.getType());
        Object[] paramsRequest = new Object[items.size()];
        for (int i = 0; i < items.size(); i++) {
            paramsRequest[i] = new Integer(items.get(i).getValue());
        }
        return paramsRequest;
    }
}
