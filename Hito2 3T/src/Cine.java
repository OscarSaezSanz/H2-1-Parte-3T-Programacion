import java.sql.*;
import java.util.*;

public class Cine {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/cine";
        String usuario = "root";
        String contraseña = "curso";
        
        Scanner sc = new Scanner(System.in);
        int opcion;

        try {
        	// Se intenta establecer conexión con la base de datos
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("¡Conexión exitosa!");

            do {
                System.out.println("\n-_-_-_-_-MENÚ-_-_-_-_-\n");
                System.out.println("1 - Ver películas");
                System.out.println("2 - Salir");
                System.out.print("Elige una opción: ");
                opcion = sc.nextInt();

                if (opcion == 1) {
                    System.out.println("\nListado de Películas:\n");
                    
                 // Consulta SQL que obtiene los datos de películas y los directores asociados
                    String consulta = """
                        SELECT p.codigo_pelicula, p.titulo, p.anio_estreno, p.duracion_min,
                               p.genero, d.nombre AS director
                        FROM peliculas p
                        JOIN directores d ON p.director_id = d.id_director
                    """;
                    
                 // Ejecutar la consulta
                    Statement stmt = conexion.createStatement();
                    ResultSet rs = stmt.executeQuery(consulta);

                    while (rs.next()) {
                        System.out.println("Código:     " + rs.getString("codigo_pelicula"));
                        System.out.println("Título:     " + rs.getString("titulo"));
                        System.out.println("Año:        " + rs.getInt("anio_estreno"));
                        System.out.println("Duración:   " + rs.getInt("duracion_min") + " min");
                        System.out.println("Género:     " + rs.getString("genero"));
                        System.out.println("Director:   " + rs.getString("director"));
                        System.out.println("\n──────────────────────────────────────\n");
                    }

                    rs.close();
                    stmt.close();

                } else if (opcion != 2) {
                    System.out.println("Opción no válida.");
                }

            } while (opcion != 2);
            // Cerrar la conexión y el scanner
            conexion.close();
            sc.close();
            System.out.println("Programa finalizado.");
            
        } catch (SQLException e) {
        	// Manejo de errores si falla la conexión o consulta
            System.out.println("Error de conexión o consulta: " + e.getMessage());
        }
    }
}


            

        
