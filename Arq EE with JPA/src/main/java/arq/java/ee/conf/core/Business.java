package arq.java.ee.conf.core;

public abstract class Business {
			
	
	protected Business() {
		Factory._setImpleBusiness(this);
	}
	
	public void testComunicaPresentacion(){
		System.out.println("OK: comunicacion persistencia->business");
	}
	
}
