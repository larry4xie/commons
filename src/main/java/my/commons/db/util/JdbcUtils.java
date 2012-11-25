package my.commons.db.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * jdbc 工具类
 * </p>
 * 
 * @author xiegang
 * @since 2012-11-12
 *
 */
public class JdbcUtils {
	private static final Logger LOG = LoggerFactory.getLogger(JdbcUtils.class);


	/**
	 * Close the given JDBC Connection and ignore any thrown exception.
	 * This is useful for typical finally blocks in manual JDBC code.
	 * @param con the JDBC Connection to close (may be <code>null</code>)
	 */
	public static void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				LOG.debug("Could not close JDBC Connection", ex);
			} catch (Throwable ex) {
				// We don't trust the JDBC driver: It might throw RuntimeException or Error.
				LOG.debug("Unexpected exception on closing JDBC Connection", ex);
			}
		}
	}

	/**
	 * Close the given JDBC Statement and ignore any thrown exception.
	 * This is useful for typical finally blocks in manual JDBC code.
	 * @param stmt the JDBC Statement to close (may be <code>null</code>)
	 */
	public static void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				LOG.trace("Could not close JDBC Statement", ex);
			} catch (Throwable ex) {
				// We don't trust the JDBC driver: It might throw RuntimeException or Error.
				LOG.trace("Unexpected exception on closing JDBC Statement", ex);
			}
		}
	}

	/**
	 * Close the given JDBC ResultSet and ignore any thrown exception.
	 * This is useful for typical finally blocks in manual JDBC code.
	 * @param rs the JDBC ResultSet to close (may be <code>null</code>)
	 */
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				LOG.trace("Could not close JDBC ResultSet", ex);
			} catch (Throwable ex) {
				// We don't trust the JDBC driver: It might throw RuntimeException or Error.
				LOG.trace("Unexpected exception on closing JDBC ResultSet", ex);
			}
		}
	}



	/**
	 * Return whether the given JDBC driver supports JDBC 2.0 batch updates.
	 * <p>Typically invoked right before execution of a given set of statements:
	 * to decide whether the set of SQL statements should be executed through
	 * the JDBC 2.0 batch mechanism or simply in a traditional one-by-one fashion.
	 * <p>Logs a warning if the "supportsBatchUpdates" methods throws an exception
	 * and simply returns <code>false</code> in that case.
	 * @param con the Connection to check
	 * @return whether JDBC 2.0 batch updates are supported
	 * @see java.sql.DatabaseMetaData#supportsBatchUpdates()
	 */
	public static boolean supportsBatchUpdates(Connection con) {
		try {
			DatabaseMetaData dbmd = con.getMetaData();
			if (dbmd != null) {
				if (dbmd.supportsBatchUpdates()) {
					LOG.debug("JDBC driver supports batch updates");
					return true;
				} else {
					LOG.debug("JDBC driver does not support batch updates");
				}
			}
		} catch (SQLException ex) {
			LOG.debug("JDBC driver 'supportsBatchUpdates' method threw exception", ex);
		} catch (AbstractMethodError err) {
			LOG.debug("JDBC driver does not support JDBC 2.0 'supportsBatchUpdates' method", err);
		}
		return false;
	}
}
