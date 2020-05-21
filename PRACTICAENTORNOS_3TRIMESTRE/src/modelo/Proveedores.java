package modelo;
/**
 * Clase que guardara la informacion de los proveedores
 * @author danie
 *
 */
public class Proveedores {
	private String nombreEmpresa;
	/**
	 * Constructor por defecto
	 */
	public Proveedores() {
		this.nombreEmpresa="";
	}
	/**
	 * Constructor por parametros
	 * @param nombreEmpresa parametro que guardara el nombre de la empresa
	 */
	public Proveedores(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	/**
	 * Devuelve el nombre de la empresa
	 * @return nombreEmpresa que es una cadena de caracteres
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	/**
	 * Establece el nombre de una empresa
	 * @param nombreEmpresa parametro que guardara el nombre de la empresa
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	
	
}
