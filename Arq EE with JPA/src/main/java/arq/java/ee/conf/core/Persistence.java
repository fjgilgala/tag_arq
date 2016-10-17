package arq.java.ee.conf.core;

public abstract class Persistence {
	
	protected Persistence(){
		Factory._setImplePersistence(this);
	}
	
	public void testComunicaBusiness() {
		System.out.println("OK: comunicacion business->persistencia");
	}
	
}
