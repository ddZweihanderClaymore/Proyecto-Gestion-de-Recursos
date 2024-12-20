package javaapplication1; // Paquete que contiene la clase principal de la aplicación

import java.sql.Connection; // Importa la clase Connection para manejar conexiones a bases de datos
import java.sql.DriverManager; // Importa DriverManager para establecer conexiones a bases de datos
import java.sql.SQLException; // Importa SQLException para manejar errores relacionados con SQL
import javafx.application.Application; // Importa la clase Application para crear aplicaciones JavaFX
import javafx.fxml.FXMLLoader; // Importa FXMLLoader para cargar archivos FXML
import javafx.scene.Parent; // Importa Parent para representar el nodo raíz de la escena
import javafx.scene.Scene; // Importa Scene para definir la escena de la aplicación
import javafx.stage.Stage; // Importa Stage para representar la ventana principal

public class JavaApplication1 extends Application {

    private static Connection con; // Variable estática que almacena la conexión a la base de datos

    @Override
    public void start(Stage stage) {
        try {
            conectarBaseDatos(); // Llama al método para establecer conexión con la base de datos
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml")); // Carga el archivo FXML que define la interfaz gráfica
            Parent root = loader.load(); // Crea el nodo raíz a partir del archivo FXML

            // Configurar escena
            Scene scene = new Scene(root); // Crea una nueva escena con el nodo raíz cargado
            stage.setTitle("Pestaña Login"); // Establece el título de la ventana principal
            stage.setScene(scene); // Asigna la escena a la ventana principal
            stage.show(); // Muestra la ventana al usuario
        } catch (Exception e) {
            System.err.println("Error al iniciar la aplicación: " + e.getMessage()); // Manejo de errores en caso de fallos al iniciar
            e.printStackTrace(); // Imprime el stack trace del error en consola
        }
    }

    private void conectarBaseDatos() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Carga el driver de MySQL para establecer conexión
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase?useTimezone=true&serverTimezone=UTC", "root", "Zweihander128"); 
            System.out.println("Conexión exitosa a la base de datos 'mydatabase'"); // Mensaje de éxito al conectar a la base de datos
        } catch (SQLException e) {
            System.err.println("Error en la conexión a MySQL: " + e.getMessage()); // Manejo de errores en caso de fallos en la conexión SQL
            e.printStackTrace(); // Imprime el stack trace del error en consola
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se pudo cargar el driver de MySQL"); // Manejo de errores si no se encuentra el driver MySQL
            e.printStackTrace(); // Imprime el stack trace del error en consola
        }
    }

    public static Connection getConnection() {
        return con; // Método estático que devuelve la conexión a la base de datos
    }

    @Override
    public void stop() {
        try {
            if (con != null && !con.isClosed()) { // Verifica si hay una conexión abierta antes de cerrarla
                con.close(); // Cierra la conexión a la base de datos
                System.out.println("Conexión cerrada."); // Mensaje indicando que se ha cerrado la conexión exitosamente
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage()); // Manejo de errores al cerrar la conexión SQL
            e.printStackTrace(); // Imprime el stack trace del error en consola
        }
    }

    public static void main(String[] args) {
        launch(args); // Método principal que inicia la aplicación JavaFX
    }
}