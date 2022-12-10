package domain;

public class Servicio {
    private int idServicio;
    private int idEmpleado;
    private int idDepartamentos ;
    private String servicio;
    private String status;

    public Servicio() {
    }

    public Servicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public Servicio(int idEmpleado, int idDepartamentos, String servicio, String status) {
        this.idEmpleado = idEmpleado;
        this.idDepartamentos = idDepartamentos;
        this.servicio = servicio;
        this.status = status;
    }

    public Servicio(int idServicio, int idEmpleado, int idDepartamentos, String servicio, String status) {
        this.idServicio = idServicio;
        this.idEmpleado = idEmpleado;
        this.idDepartamentos = idDepartamentos;
        this.servicio = servicio;
        this.status = status;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdDepartamentos() {
        return idDepartamentos;
    }

    public void setIdDepartamentos(int idDepartamentos) {
        this.idDepartamentos = idDepartamentos;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Servicio{");
        sb.append("idServicio=").append(idServicio);
        sb.append(", idEmpleado=").append(idEmpleado);
        sb.append(", idDepartamentos=").append(idDepartamentos);
        sb.append(", servicio=").append(servicio);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
    
}
