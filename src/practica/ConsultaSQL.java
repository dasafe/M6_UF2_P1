package practica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConsultaSQL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner reader = new Scanner(System.in);

		int faccion_id[] = { 1, 2, 3 };
		String nombre_faccion[] = { "Caballeros", "Vikingos", "Samuráis" };
		String lore[] = { "Armadura y espadas", "Cascos y hachas", "Katanas" };

		int personaje_id[] = { 1, 2, 3, 4, 5, 6 };
		String nombre_personaje[] = { "Guardianes", "Conquistador", "Berserk", "Invasores", "Kensei", "Orochi" };
		int ataque[] = { 25, 32, 38, 23, 26, 27 };
		int defensa[] = { 30, 27, 22, 31, 32, 29 };
		int faccion_id_p[] = { 1, 1, 2, 2, 3, 3 };

		int opcion = 0;

		try {

			Connection conexion = DriverManager.getConnection("jdbc:sqlite:ForHonor.bd");
			Statement st = conexion.createStatement();

			while (opcion != 5) {

				System.out.println(
						"1. Generar BD\n2. Tabla Personajes\n3. Tabla Caballeros\n4. Samurai con más ataque\nOpcion:");
				opcion = reader.nextInt();
				switch (opcion) {
				case 1:
					st.execute(
							"CREATE TABLE faccion (faccion_id integer primary key, nombre_faccion text, lore text);");
					st.execute(
							"CREATE TABLE personajes (personaje_id integer primary key, nombre_personaje text, ataque integer, defensa integer, faccion_id integer, FOREIGN KEY(faccion_id) REFERENCES faccion(faccion_id));");

					for (int i = 0; i < faccion_id.length; i++) {
						st.executeUpdate("INSERT INTO faccion (faccion_id, nombre_faccion, lore) VALUES ('"
								+ faccion_id[i] + "','" + nombre_faccion[i] + "','" + lore[i] + "' );");
					}

					for (int i = 0; i < personaje_id.length; i++) {
						st.executeUpdate(
								"INSERT INTO personajes (personaje_id, nombre_personaje, ataque, defensa, faccion_id) VALUES ('"
										+ personaje_id[i] + "','" + nombre_personaje[i] + "','" + ataque[i] + "','"
										+ defensa[i] + "','" + faccion_id_p[i] + "' );");
					}
					break;
				case 2:
					mostrarPersonajes(st);
					break;
				case 3:
					mostrarCaballeros(st);
					break;
				case 4:
					mostrarSamuraisPorAtaque(st);
					break;
				case 5:
					System.out.println("Bye!");
					break;

				default:
					System.out.println("Opcion invalida");
					break;
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void mostrarPersonajes(Statement st) throws SQLException {
		// TODO Auto-generated method stub

		System.out.println("\n- Personajes -");
		ResultSet rs = st.executeQuery("Select * from personajes");
//		Select * from personajes
		while (rs.next()) {
			System.out.println("ID: " + rs.getObject("personaje_id") + ", Nombre: " + rs.getObject("nombre_personaje")
					+ ", Ataque: " + rs.getObject("ataque") + ", Defensa: " + rs.getObject("defensa") + ", Faccion: "
					+ rs.getObject("faccion_id"));
		}
		System.out.println();
	}

	private static void mostrarCaballeros(Statement st) throws SQLException {
		// TODO Auto-generated method stub

		System.out.println("\n- Caballeros -");
		ResultSet rs = st.executeQuery("Select * from personajes where faccion_id = 1");
//		Select * from personajes where faccion_id = 1
		while (rs.next()) {
			System.out.println("ID: " + rs.getObject("personaje_id") + ", Nombre: " + rs.getObject("nombre_personaje")
					+ ", Ataque: " + rs.getObject("ataque") + ", Defensa: " + rs.getObject("defensa") + ", Faccion: "
					+ rs.getObject("faccion_id"));
		}
		System.out.println();
	}

	private static void mostrarSamuraisPorAtaque(Statement st) throws SQLException {
		// TODO Auto-generated method stub

		System.out.println("\n- Samurais por ataque -");
		ResultSet rs = st.executeQuery(
				"Select * from personajes where faccion_id = 3 and ataque = (select max(ataque) from personajes where faccion_id = 3)");
//		Select * from personajes where faccion_id = 3 order by ataque
		while (rs.next()) {
			System.out.println("ID: " + rs.getObject("personaje_id") + ", Nombre: " + rs.getObject("nombre_personaje")
					+ ", Ataque: " + rs.getObject("ataque") + ", Defensa: " + rs.getObject("defensa") + ", Faccion: "
					+ rs.getObject("faccion_id"));
		}
		System.out.println();
	}

}
