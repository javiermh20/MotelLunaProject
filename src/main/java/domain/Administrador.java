package domain;

public class Administrador {
    private int idAdministrador;
    private String nombre;
    private String usuario;
    private String pass;

    public Administrador() {
    }

    public Administrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public Administrador(String nombre, String usuario, String pass) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.pass = pass;
    }

    public Administrador(int idAdministrador, String nombre, String usuario, String pass) {
        this.idAdministrador = idAdministrador;
        this.nombre = nombre;
        this.usuario = usuario;
        this.pass = pass;
    }

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Administrador{");
        sb.append("idAdministrador=").append(idAdministrador);
        sb.append(", nombre=").append(nombre);
        sb.append(", usuario=").append(usuario);
        sb.append(", pass=").append(pass);
        sb.append('}');
        return sb.toString();
    }

    
}
