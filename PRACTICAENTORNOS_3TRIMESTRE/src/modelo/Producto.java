package modelo;
/**
 * Clase Producto que guardara la informacion de los productos 
 * @author danie
 *
 */
public class Producto {
	private String nombreProducto;
	private float precio;
	private int cantidad;
	/**
	 * Constructor por defecto
	 */
	public Producto() {
		this.nombreProducto="";
		this.precio=0;
		this.cantidad=0;
	}
	/**
	 * Contructor por parametros
	 * @param nombreProducto parametro que guardara el nombre del producto
	 * @param precio parametro que guardara el precio del producto
	 * @param cantidad parametro que guardara la cantidad que hay de un producto
	 */
	public Producto(String nombreProducto, float precio, int cantidad) {
		super();
		this.nombreProducto = nombreProducto;
		this.precio = precio;
		this.cantidad = cantidad;
	}
	/**
	 * Devuelve el nombre de un producto
	 * @return nombreProducto que es una cadena de caracteres
	 */
	public String getNombreProducto() {
		return nombreProducto;
	}
	/**
	 * Establece el nombre de un producto
	 * @param nombreProducto parametro que guardara el nombre del producto
	 */
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	/**
	 * Devuelve el precio de un producto
	 * @return precio que es un float
	 */
	public float getPrecio() {
		return precio;
	}
	/**
	 * Establece el precio de un producto
	 * @param precio  parametro que guardara el precio del producto
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	/**
	 * Devuelve la cantidad que hay de un producto
	 * @return cantidad que es un entero
	 */
	public int getCantidad() {
		return cantidad;
	}
	/**
	 * Establece la cantidad que hay de un producto
	 * @param cantidad parametro que guardara la cantidad que hay de un producto
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
}
