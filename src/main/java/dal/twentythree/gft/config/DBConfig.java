package dal.twentythree.gft.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Configuration;

import dal.twentythree.gft.exception.CopyCatMeDBConfigException;
import dal.twentythree.gft.util.Constants;

@Configuration
public class DBConfig implements Constants{

	private static String url;
	private static String userName;
	private static String password;
	private static DBConfig dbConfigInstance = null;

	public Connection getConnectionInstance() throws CopyCatMeDBConfigException{
		try {
		
			if (url == null || url.trim().isEmpty()) {
				url = DatabaseURL;
			}
			if (userName == null || userName.trim().isEmpty()) {
				userName = DatabaseUserName;
			}
			if (password == null || password.trim().isEmpty()) {
				password = DatabasePassword;
			}
			return DriverManager.getConnection(url, userName, password);
		}
		catch(SQLException e) {
			throw new CopyCatMeDBConfigException();
		}

	}

	public static DBConfig getDBConfigInstance() {
		if (null == dbConfigInstance) {
			dbConfigInstance = new DBConfig();
		}
		return dbConfigInstance;
	}

}
