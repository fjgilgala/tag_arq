## Evolución del proyecto

Presente:
- Toda la documentación
- Generación de paquetes para proyectos Maven

Futuro:
- Arreglar algunos errores que da la MV al realizar compilación dinámica (no encuentra la ruta de a no ser que se compile de nuevo 
  o se refresque el proyecto desde el IDE)

## Uso del framework
- Necesario adjuntar el jar y los demás jar referenciados
- Inicialización:
  ```
  // La clase principal de la aplicación del usuario debe extender de la clase Service, y en ella iniciar el framework
  public class App extends Service {
     public static void main(String[] args) throws BusinessException {
     
        // Opcionalmente se puede iniciar una base de datos embebida, p.e:
        String databasename = "internal";
			  generateDerbyDBServerJDBC(databasename);

        // Inicia el framework pasandonle clases ya creadas por el usuario
        start(new BusinessImple(), new PersistenceImple());
     
        // o bien generando las clases por defecto
     
        // Inicia el framework y genera los paquetes y clases demo
        generarDemo(...);
     }
  }
   ```
   
- Acceso:

    ```
    BusinessImple b = (BusinessImple) get().business();
    b.(métodos de la implementación)
    
    // también se puede acceder a todos los servicios de Services a través de la clase principal de la aplicación
    App.(lo que sea)
    ```
