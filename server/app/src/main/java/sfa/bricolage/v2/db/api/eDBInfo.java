package sfa.bricolage.v2.db.api;

public enum eDBInfo {
	NB_ROW ("NB_ROW"),
	UID ("UID");
	
	private String _nom;
	private eDBInfo(String s) {
		_nom = s;
	}
	
	@Override
	public String toString() {
		return _nom;
	}
}
