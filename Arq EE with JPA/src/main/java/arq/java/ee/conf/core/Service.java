package arq.java.ee.conf.core;

public class Service {

	public static Business services = Factory.business();
	public static Persistence persistence = Factory.persistence();

	public static void startBusiness(Business business) {
		if (services == null)
			Factory._setImpleBusiness(business);
	}

	public static void startPersistence(Persistence persistence) {
		if (Service.persistence == null)
			Factory._setImplePersistence(persistence); 
	}

	public static void startBusiness(String nameBusiness)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (services == null)
			Factory._setImpleBusiness((Business) Class.forName(nameBusiness).newInstance());
	}

	public static void startPersistence(String namePersistence)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (persistence == null)
			Factory._setImplePersistence((Persistence) Class.forName(namePersistence).newInstance());
	}
}