package ie.gmit.rad;

import javax.activation.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

public class DAO {
	private DataSource mysqlDS;

	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/jdbc/garage";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
}
