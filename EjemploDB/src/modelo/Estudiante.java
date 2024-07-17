package modelo;

public class Estudiante {
    
    private int id; // Nuevo campo id
    private String nombreEst;
    private double nota1;
    private double nota2;
    private double promedio;
    private String estado;

    public Estudiante(int id, String nombreEst, double nota1, double nota2, double promedio, String estado) {
        this.id = id;
        this.nombreEst = nombreEst;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.promedio = promedio;
        this.estado = estado;
    }

    public Estudiante(String nombreEst, double nota1, double nota2) {
        this.nombreEst = nombreEst;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.promedio = 0.0;
        this.estado = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEst() {
        return nombreEst;
    }

    public void setNombreEst(String nombreEst) {
        this.nombreEst = nombreEst;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        String data = String.format("ID: %d, Nombre: %s, Nota1: %.2f, Nota2: %.2f, Promedio: %.2f, Estado: %s\n", 
                                    getId(), getNombreEst(), getNota1(), getNota2(), getPromedio(), getEstado());
        return data;
    }
}
