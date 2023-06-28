package dam2.add.p12.dao;

public class Usuario {

	private String aciertos;
	private String nombre;
	private String apellidos;

	public Usuario() {
		super();
	}

	public Usuario(String aciertos, String nombre, String apellidos) {
		super();
		this.aciertos = aciertos;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public String getAciertos() {
		return aciertos;
	}

	public void setAciertos(String aciertos) {
		this.aciertos = aciertos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

}
