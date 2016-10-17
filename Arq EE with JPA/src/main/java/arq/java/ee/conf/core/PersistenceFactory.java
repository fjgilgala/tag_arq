package arq.java.ee.conf.core;

class PersistenceFactory {

	static Persistence _persistence;
	
	private PersistenceFactory(){
	}
	
	static Persistence setPersistence(Persistence persistence){
		if (PersistenceFactory._persistence == null)
			PersistenceFactory._persistence = persistence;
		return _persistence;
	}
}
