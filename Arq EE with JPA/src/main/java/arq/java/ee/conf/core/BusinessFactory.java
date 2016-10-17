package arq.java.ee.conf.core;

class BusinessFactory {

	static Business _business;
	private BusinessFactory(){}

	static Business setBusiness(Business business) {
		if (_business == null)
			BusinessFactory._business = business;
		return _business;
	}

}
