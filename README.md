## Evolución del proyecto

Presente:
- Toda la documentación
- Hacer un proyecto de ejemplo con la libreria core

Futuro:
- Arreglar algunos errores que da la MV al realizar compilación dinámica (no encuentra la ruta de a no ser que se compile de nuevo 
  o se refresque el proyecto desde el IDE)
- Empaquetar la libreria core con un módulo para aplicaciones JPA
- Hacer un proyecto de ejemplo con la libreria core y la de JPA

## Uso de la biblioteca Core
- Necesario adjuntar el jar 
- Inicialización:
  ```
   // Inicia el framework pasandonle clases ya creadas por el usuario
   Service s = new Service();
   s.start(new BusinessImple(), new PersistenceImple());
     
     o bien generando las clases por defecto
     
   // Inicia el framework y genera los paquetes y clases demo
   Service.generarDemo(...);
   ```
   
- Acceso:
    ```
    BusinessImple b = (BusinessImple) s.get().business();
    b.(métodos de la implementación)
    ```
