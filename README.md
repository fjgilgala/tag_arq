# tag_arq


De momento se esta realizando la parte core (Arq Core) realizando entre otras cosas:
- Toda la documentación
- Arreglar algunos errores de implementación
- Crear la máxima independencia entre la inicialización del framework y las llamadas realizadas por el usuario.

Futuro:
- Arreglar algunos errores que da la MV al realizar compilación dinámica (no encuentra la ruta de a no ser que se compile de nuevo 
  o se refresque el proyecto desde el IDE)
- Empaquetar la parte core como un libreria y añadirla a un proyecto de ejemplo
- Empaquetar la libreria core con un módulo para aplicaciones JPA
- Hacer un proyecto de ejemplo con la libreria core y la de JPA



Uso de la biblioteca Core
- Necesario adjuntar el jar 
- Inicialización:
    // Inicia el framework
		Service s = new Service();
		s.start(new BusinessImple(), new PersistenceImple()); // siendo BusinessImpl y PersistenceImpl las implementaciones del usuario que        heredan de las clases Business y Persistence respectivamente.
    Ó
    // Inicia el framework y genera los paquetes y clases demo
    Service.main()
- Acceso:
    BusinessImple b = (BusinessImple) s.get().business();
    b.(métodos de la implementación)
    ...
    lo mismo con persistence    
