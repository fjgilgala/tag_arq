package arq.java.ee.presentation;

import arq.java.ee.business.BusinessImplWithJPA;
import arq.java.ee.conf.core.Service;
import arq.java.ee.persistence.PersistenceImplWithJPA;

public class App {
	
	
	public static void main(String[] args) {	
		
		// forma 1 de acceso
		Service.startBusiness(new BusinessImplWithJPA());
		Service.startPersistence(new PersistenceImplWithJPA());
		
		// accesos a las capas
		BusinessImplWithJPA fact = (BusinessImplWithJPA) Service.services;
		fact.testComunicaPresentacion();
	}

}
