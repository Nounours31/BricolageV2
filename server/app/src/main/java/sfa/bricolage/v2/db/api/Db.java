package sfa.bricolage.v2.db.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import sfa.bricolage.v2.global.E4AError;
import sfa.bricolage.v2.global.E4AException;
import sfa.bricolage.v2.global.Envt;

public class Db {
	private static boolean _isDriverLoaded = false;
	private static Db _singleton = null;
	
	protected Db() {}
	
	protected static Db getInstance() {
		if (Db._singleton == null)
			Db._singleton = new Db();
		return Db._singleton;
	}
	
	private static synchronized E4AError loadDriver () {
        try {
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            _isDriverLoaded = true;
        } catch (Exception ex) {
            return E4AError.E_FAIL;
        }
        return E4AError.S_OK;
    }
	

	private Connection open () throws E4AException {
		Connection conn = null;
		try {
			if (!Db._isDriverLoaded) {
				E4AError rc = Db.loadDriver();
				if (!rc.SUCCEEDED()) 
					throw new E4AException("No Driver");
			}
			
			String options = String.format("user=%s&password=%s&connectTimeout=0&socketTimeout=0&character_set_server=ISO8859_2", 
					Envt.getUser(),
					Envt.getPassword());
			
			String ConnextionURL = String.format("jdbc:mysql://%s:%s/%s?%s",
					Envt.getHost(),
					Envt.getPort(),
					Envt.getDbName(),
					options);
			conn = DriverManager.getConnection(ConnextionURL);
		} catch (SQLException ex) {
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    
		    throw new E4AException("Open KO");
		}
		return conn;
	}

	protected  ArrayList<HashMap<String, Object>> select (Connection conn, String sqlSelect) throws E4AException {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<HashMap<String, Object>> retour = new ArrayList<HashMap<String, Object>>();

		try {
		    stmt = conn.createStatement();
		    if (stmt.execute(sqlSelect)) {
		        rs = stmt.getResultSet();
		    }
		    if (rs != null) {
		    	while (rs.next()) {
		    		HashMap<String, Object> hm = new HashMap<String, Object>();
		    		ResultSetMetaData meta = rs.getMetaData();

		    		int cols = meta.getColumnCount();

		            for (int i = 0; i < cols; i++) {
			            hm.put(meta.getColumnName(i +1), rs.getObject(i + 1));
		            }	
		            retour.add(hm);
		        }
		    }
		}
		catch (SQLException ex){
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore
		        rs = null;
		    }

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore
		        stmt = null;
		    }
		}
		return retour;
	}
	
	protected ArrayList<HashMap<String, Object>> selectAsRest (String sqlSelect) throws E4AException {
		Connection conn = this.open();
		return this.select (conn, sqlSelect);
	}

	protected HashMap<String, Integer> insert (Connection conn, String sqlInsert) throws E4AException {
		Statement stmt = null;
		ResultSet rs = null;
		HashMap<String, Integer> retour = new HashMap<String, Integer>();

		try {
		    stmt = conn.createStatement();
		    int nbRow = stmt.executeUpdate(sqlInsert, Statement.RETURN_GENERATED_KEYS);
		    retour.put(eDBInfo.NB_ROW.toString(), nbRow);
		    
		    int autoIncKeyFromApi = -1;
		    rs = stmt.getGeneratedKeys();

		    if (rs.next()) {
		        autoIncKeyFromApi = rs.getInt(1);
			    retour.put(eDBInfo.UID.toString(), autoIncKeyFromApi);
		    }
		}
		catch (SQLException ex){
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore
		        stmt = null;
		    }
		}
		return retour;
	}
	
	protected HashMap<String, Integer> insertAsRest (String sqlInsert) throws E4AException {
		Connection conn = this.open();
		return this.insert (conn, sqlInsert);
	}
	
	protected String toStringSelect(ArrayList<HashMap<String, Object>> x) {
		StringBuffer sb = new StringBuffer();
		sb.append("----------------------------------------------------------------"  + System.lineSeparator());
		sb.append("-- Resultat du select"  + System.lineSeparator());
		sb.append("----------------------------------------------------------------"  + System.lineSeparator());
		sb.append("Nb Reponse:" + x.size() + System.lineSeparator());
		int i = 1;
		for (HashMap<String, Object> uneLigne : x) {
			sb.append (String.format ("%03d/%03d  ", i, x.size()));
			for (String sKey : uneLigne.keySet()) {
				sb.append(String.format("[>%s<=>%s<]", sKey, uneLigne.get(sKey)));
			}
			sb.append(System.lineSeparator());
			i++;
		}
		sb.append("----------------------------------------------------------------"  + System.lineSeparator());
		return sb.toString();
	}
}
