package domain;

public class Departamento {
    private int idDepartamento;
    private int numeroDepartamento;
    private String habitaciones;

    public Departamento() {
    }

    public Departamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Departamento(int idDepartamento, int numeroDepartamento, String habitaciones) {
        this.idDepartamento = idDepartamento;
        this.numeroDepartamento = numeroDepartamento;
        this.habitaciones = habitaciones;
    }

    public Departamento(int numeroDepartamento, String habitaciones) {
        this.numeroDepartamento = numeroDepartamento;
        this.habitaciones = habitaciones;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getNumeroDepartamento() {
        return numeroDepartamento;
    }

    public void setNumeroDepartamento(int numeroDepartamento) {
        this.numeroDepartamento = numeroDepartamento;
    }

    public String getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(String habitaciones) {
        this.habitaciones = habitaciones;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Departamento{");
        sb.append("idDepartamento=").append(idDepartamento);
        sb.append(", numeroDepartamento=").append(numeroDepartamento);
        sb.append(", habitaciones=").append(habitaciones);
        sb.append('}');
        return sb.toString();
    }
    
    
}
