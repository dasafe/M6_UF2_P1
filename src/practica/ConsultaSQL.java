package practica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsultaSQL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String faccion_id[] = { "1", "2", "3" };
		String nombre_faccion[] = { "Caballeros", "Vikingos", "Samuráis" };
		String lore[] = { "Armadura y espadas", "Cascos y hachas", "Katanas" };

		String personaje_id[] = { "1", "2", "3", "4", "5", "6" };
		String nombre_personaje[] = { "Guardianes", "Conquistador", "Berserk", "Invasores", "Kensei", "Orochi" };
		String ataque[] = { "25", "32", "38", "23", "26", "27" };
		String defensa[] = { "30", "27", "22", "31", "32", "29" };
		String faccion_id_p[] = { "1", "1", "2", "2", "3", "3" };

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql:C:\\sqlite\\bd\\ForHonor.bd");
			Statement st = conexion.createStatement();

			st.execute(
					"CREATE TABLE IF NOT EXISTS faccion (faccion_id integer primary key, nombre_faccion Varchar (15),lore Varchar (200));");
			st.execute(
					"CREATE TABLE IF NOT EXISTS personaje (personaje_id integer primary key, nombre_personaje Varchar (15), ataque integer, defensa integer, faccion_id integer FOREIGN KEY REFERENCES faccion(faccion_id);");

			for (int i = 0; i < faccion_id.length; i++) {
				st.executeUpdate(
						"INSERT INTO faccion (faccion_id, nombre_faccion, lore) VALUES ('\"+faccion_id[i]+\"','\"+nombre_faccion[i]+\"','\"+lore[i]+\"' )");
			}
			for (int i = 0; i < personaje_id.length; i++) {
				st.executeUpdate(
						"INSERT INTO personaje (personaje_id, nombre_personaje, ataque, defensa, faccion_id) VALUES ('\"+personaje_id[i]+\"','\"+nombre_personaje[i]+\"','\"+ataque[i]+\"','\"+defensa[i]+\"','\"+faccion_id_p[i]+\"' )");
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mostrarPersonajes();
		mostrarCaballeros();
		mostrarSamuraisPorAtaque();

	}

	private static void mostrarPersonajes() {
		// TODO Auto-generated method stub
		System.out.println("- Personajes -");

//		Select * from personajes
		System.out.println(rs.getObject("personaje_id") + " " + rs.getObject("nombre_personaje") + " "
				+ +rs.getObject("ataque") + " " + rs.getObject("defensa") + " " + rs.getObject("faccion_id"));

		System.out.println();
	}

	private static void mostrarCaballeros() {
		// TODO Auto-generated method stub

		System.out.println("- Caballeros -");

//		Select * from personajes where faccion_id = 1
		System.out.println(rs.getObject("personaje_id") + " " + rs.getObject("nombre_personaje") + " "
				+ rs.getObject("ataque") + " " + rs.getObject("defensa") + " " + rs.getObject("faccion_id"));

		System.out.println();
	}

	private static void mostrarSamuraisPorAtaque() {
		// TODO Auto-generated method stub

		System.out.println("- Samurais por ataque -");

//		Select * from personajes where faccion_id = 3 order by ataque
		System.out.println(rs.getObject("personaje_id") + " " + rs.getObject("nombre_personaje") + " "
				+ rs.getObject("ataque") + " " + rs.getObject("defensa") + " " + rs.getObject("faccion_id"));

		System.out.println();
	}

}
