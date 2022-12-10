package domain;

public class Vivienda {
    private int idVivienda;
    private int idDepartamento;
    private int idInquilino;

    public Vivienda() {
    }

    public Vivienda(int idVivienda) {
        this.idVivienda = idVivienda;
    }

    public Vivienda(int idVivienda, int idDepartamento, int idInquilino) {
        this.idVivienda = idVivienda;
        this.idDepartamento = idDepartamento;
        this.idInquilino = idInquilino;
    }

    public Vivienda(int idDepartamento, int idInquilino) {
        this.idDepartamento = idDepartamento;
        this.idInquilino = idInquilino;
    }

    public int getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(int idVivienda) {
        this.idVivienda = idVivienda;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(int idInquilino) {
        this.idInquilino = idInquilino;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vivienda{");
        sb.append("idVivienda=").append(idVivienda);
        sb.append(", idDepartamento=").append(idDepartamento);
        sb.append(", idInquilino=").append(idInquilino);
        sb.append('}');
        return sb.toString();
    }
    
    
}
