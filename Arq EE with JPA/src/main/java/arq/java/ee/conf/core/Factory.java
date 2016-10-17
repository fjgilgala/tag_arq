package arq.java.ee.conf.core;

class Factory {
	
	private static Persistence _persistence;
	private static Business _business;
	
	public static Persistence persistence(){
		return _persistence;
	}
	
	public static Business business(){
		return _business;
	}
	
	static void _setImplePersistence(Persistence persistence){
		_persistence = PersistenceFactory.setPersistence(persistence);
	}
	
	static void _setImpleBusiness(Business business){
		_business = BusinessFactory.setBusiness(business);
	}	
}