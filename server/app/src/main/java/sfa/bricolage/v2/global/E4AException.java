package sfa.bricolage.v2.global;

public class E4AException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public E4AException() {
		super();
	}

	public E4AException(E4AException err) {
		super(err.toString());
	}

	public E4AException(String s) {
		super (s);
	}
}
