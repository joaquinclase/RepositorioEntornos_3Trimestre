package vista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import modelo.Producto;
/**
 * Clase que contiene el menu y los metodos de la aplicacion
 * @author danie
 *
 */
public class Formulario {
	/**
	 * Metodo que contiene el menu con el que interactuara el usuario
	 */
	public void menu() {
		Scanner t = new Scanner(System.in);
		int opcion;
		
			System.out.print("**** Bienvenido al supermercado ****"
							 + "\n1. Soy cliente"
							 + "\n2. Soy empleado"
							 + "\n0. Salir"
							 + "\nPorfavor identifiquese: ");
			opcion =  t.nextInt();
			
			switch (opcion) {
			case 1:
				areaCliente();
				break;
			case 2:
				areaEmpleado();
				break;
			case 0:
				System.out.println("Hasta la próxima, gracias por comprar !");
				break;
			default:
				break;
			}
	}
	/**
	 * Metodo que diferencia entre empleado y cliente al iniciar la aplicacion,si es empleado contiene la posilidad de introducir modificar o borrar un producto de la base de datos
	 * @return f que es el objeto formulario que guardara la informacion
	 */
	public Formulario areaEmpleado() {
		Formulario f = new Formulario();
		Scanner t = new Scanner(System.in);
		
		System.out.println("Por favor introduzca sus datos de acceso: ");
		System.out.print("Usuario: ");
		String usuario = t.nextLine();
		System.out.print("Contraseña: ");
		String password = t.nextLine();
		
		try {
			Producto p = new Producto();
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/supermercadoentornos", usuario , password);
			System.out.println("\nAcceso concedido, bienvenido trabajador: " + usuario);
			
			int opcion;
			
			System.out.print("1. Introducir producto"
					     + "\n2. Modificar producto"
						 + "\n3. Borrar producto"
					     + "\n0. Salir"
						 + "\n\nSeleccione una opción: ");
			opcion = t.nextInt();
			t.nextLine();
			switch (opcion) {
			case 1:
				
				/*try {
					System.out.print("Introduzca el nombre del producto: ");
					p.setNombreProducto(t.nextLine());
					System.out.print("Introduzca el precio del producto: ");
					p.setPrecio(t.nextFloat());
					System.out.print("Introduzca el stock del producto: ");
					p.setCantidad(t.nextInt());
					
					Statement consulta = conexion.createStatement();	
					consulta.executeUpdate("insert into almacen (Nombre_Producto, Precio, Cantidad) values ('"
							+ p.getNombreProducto()
							+ "', "
							+ p.getPrecio()
							+ ", "
							+ p.getCantidad()
							+ ")");
					
					conexion.close();
					System.out.println("Producto introducido correctamente");
					
				}catch (SQLException e) {
					System.out.println("No se ha introducido el producto");
				}*/
				agregarProducto(usuario, password);
			break;
			case 2:
				
				/*try {
					
					Statement consulta = conexion.createStatement();
					ResultSet registro = consulta.executeQuery("select Nombre_Producto from almacen");
					
					while(registro.next()){
						System.out.println(registro.getString("Nombre_Producto"));
					}
					
					System.out.println("Introduzca el nombre del producto a modificar: ");
					String nombrep = t.nextLine();
					
					System.out.println("Introduzca el nuevo precio del producto: ");
					float preciop = t.nextFloat();
					
					System.out.println("Introduzca el nuevo stock del producto:  ");
					int stock = t.nextInt();
					t.nextLine();
					
					consulta.executeUpdate("update almacen set Precio = " + preciop + ", Cantidad = " + stock + " where Nombre_Producto like '" + nombrep + "%'");
					
					conexion.close();
					System.out.println("Producto modificado correctamente");
				}catch (SQLException e) {
					e.printStackTrace();
					System.out.println("No se ha modificado el producto");
				}*/
				modificarProducto(usuario, password);
			break;
			
			case 3:

				/*try {
					
					Statement consulta = conexion.createStatement();
					
					System.out.println("Introduzca el nombre del producto que quieras eliminar: ");
					String nombrep = t.nextLine();
					
					consulta.executeUpdate("delete from almacen where Nombre_Producto like '" + nombrep + "%'");

					conexion.close();					
					System.out.println("Producto eliminado correctamente");
				}catch (SQLException e) {
					e.printStackTrace();
					System.out.println("No se ha eliminado el producto");
				}*/
				borrarProducto(usuario, password);
				
			break;
			
			case 0:
				System.out.println("Hasta la proxima");
			break;
			
			default:
				break;
			}

		} catch (SQLException e) {
			System.out.println("Datos de acceso incorrectos, vuelva a introducirlos");
			areaEmpleado();
		}
		
		return f;
	}
	
	/**
	 * Metodo que al ser cliente te permite elegir lo que quieres comprar de la base de datos y la cantidad
	 * @return f que es el objeto formulario que guardara la informacion
	 */
	public Formulario areaCliente() {
		Formulario f = new Formulario();
		Scanner t = new Scanner(System.in);
		
		Connection conexion;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/supermercadoentornos" , "cliente" , "cliente");
			
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select Nombre_Producto, Precio, Cantidad from almacen");
			
			System.out.println("En este momento tenemos los siguiente productos en stock: ");
			
			while(registro.next()){
				System.out.println(registro.getString("Nombre_Producto") + " " + registro.getFloat("Precio") + "€ " + registro.getInt("Cantidad") + " unidades");
			}
			
			System.out.print("Que producto desea comprar: ");
			String nombrep = t.nextLine();
			System.out.print("Cuantas unidades quieres: ");
			int unidades = t.nextInt();
			t.nextLine();

			consulta.executeUpdate("update almacen set Cantidad = Cantidad -" + unidades );
			
			conexion.close();
			System.out.println("Compra realizada correctamente gracias por usar nuestro servicios");
		} catch (SQLException e) {
			
			System.out.println("No se ha podido realizar la compra");
		}

		
		
		return f;
	}
	/**
	 * Metodo que agrega un producto a la base de datos
	 * @param usuario parametro que guarda el nombre de usuario de la BBDD
	 * @param password parametro que guarda la contraseña de la BBDD
	 * @return f que es un objeto formulario
	 */
	public Formulario agregarProducto(String usuario, String password) {
		Formulario f  =  new Formulario();
		Scanner t =  new Scanner(System.in);
		
		try {
			Producto p = new Producto();
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/supermercadoentornos", usuario , password);	
			
					System.out.print("Introduzca el nombre del producto: ");
					p.setNombreProducto(t.nextLine());
					System.out.print("Introduzca el precio del producto: ");
					p.setPrecio(t.nextFloat());
					System.out.print("Introduzca el stock del producto: ");
					p.setCantidad(t.nextInt());
					
					Statement consulta = conexion.createStatement();	
					consulta.executeUpdate("insert into almacen (Nombre_Producto, Precio, Cantidad) values ('"
							+ p.getNombreProducto()
							+ "', "
							+ p.getPrecio()
							+ ", "
							+ p.getCantidad()
							+ ")");
					
					conexion.close();
					System.out.println("Producto introducido correctamente");
					
				}catch (SQLException e) {
					System.out.println("No se ha introducido el producto");
				}
		
		
		
		return f;
	}
	/**
	 * Metodo que modifica un producto de la base de datos
	 * @param usuario parametro que guarda el nombre de usuario de la BBDD
	 * @param password parametro que guarda la contraseña de la BBDD
	 * @return f que es un objeto formulario
	 */
	public Formulario modificarProducto(String usuario, String password) {
		Formulario f = new Formulario();
		Scanner t = new Scanner(System.in);
		try {
			
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/supermercadoentornos", usuario , password);	
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select Nombre_Producto from almacen");
			
			while(registro.next()){
				System.out.println(registro.getString("Nombre_Producto"));
			}
			
			System.out.print("Introduzca el nombre del producto a modificar: ");
			String nombrep = t.nextLine();
			
			System.out.print("Introduzca el nuevo precio del producto: ");
			float preciop = t.nextFloat();
			
			System.out.print("Introduzca el nuevo stock del producto:  ");
			int stock = t.nextInt();
			t.nextLine();
			
			consulta.executeUpdate("update almacen set Precio = " + preciop + ", Cantidad = " + stock + " where Nombre_Producto like '" + nombrep + "%'");
			
			conexion.close();
			System.out.println("Producto modificado correctamente");
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("No se ha modificado el producto");
		}
		
		
		return f;
	}
	/**
	 * Metodo que borra un producto de la base de datos
	 * @param usuario parametro que guarda el nombre de usuario de la BBDD
	 * @param password parametro que guarda la contraseña de la BBDD
	 * @return f que es un objeto formulario
	 */
	public Formulario borrarProducto(String usuario, String password) {
		Formulario f = new Formulario();
		Scanner t = new Scanner(System.in);
		
		try {

			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/supermercadoentornos", usuario , password);	
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select Nombre_Producto from almacen");
			
			System.out.println("Estos son los productos que tenemos actualmente: ");
			while(registro.next()){
				System.out.println(registro.getString("Nombre_Producto"));
			}
			
			System.out.print("\nIntroduzca el nombre del producto que quieras eliminar: ");
			String nombrep = t.nextLine();
			
			consulta.executeUpdate("delete from almacen where Nombre_Producto like '" + nombrep + "%'");

			conexion.close();					
			System.out.println("Producto eliminado correctamente");
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("No se ha eliminado el producto");
		}
		
		
		
		
		return f;
	}
}























