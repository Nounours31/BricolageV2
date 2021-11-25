package sfa.bricolage.v2.db.api;

import java.util.ArrayList;
import java.util.HashMap;

import sfa.bricolage.v2.db.object.User;
import sfa.bricolage.v2.global.E4AException;
import sfa.bricolage.v2.global.E4ALogger;

public class DBUser extends Db {

	private static final String Matricule = "matricule";
	private static final String PreNom = "prenom";
	private static final String Nom = "nom";
	
	private static E4ALogger _logger = E4ALogger.getLogger("DBUser");

	private DBUser() {
		super();
	}

	public static User getUserFromId(int id) {
		String sql = String.format("select * from user where (uid=%d)", id);
		Db db = DBUser.getInstance();
		User u = null;
		try {
			ArrayList<HashMap<String, Object>> lUsers = db.selectAsRest(sql);
			_logger.debug( db.toString(lUsers));
			for (HashMap<String, Object> user : lUsers) {
				u = new User();
				u.setId(id);
				u.setMatricule((Integer)user.get(DBUser.Matricule));
				u.setNom((String) user.get(DBUser.Nom));
				u.setPrenom((String) user.get(DBUser.PreNom));
			}
		} catch (E4AException e) {
			e.printStackTrace();
		}
		return u;	
	}
	
	


}
