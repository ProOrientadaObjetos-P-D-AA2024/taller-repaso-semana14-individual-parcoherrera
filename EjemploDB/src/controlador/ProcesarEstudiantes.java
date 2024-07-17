package controlador;

import java.sql.*;
import java.util.ArrayList;
import modelo.ConexionDB;
import modelo.Estudiante;

public class ProcesarEstudiantes {
    
    private ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
    private ConexionDB conexionDB = new ConexionDB();
    
    public void insertarEstudiante(Estudiante estudiante){
        calcularPromedios();
        calculoEstados();
        try{
            conexionDB.establecerConexion();
            String sql = "INSERT INTO Estudiante (nombreEst, nota1, nota2, promedio, estado) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conexionDB.obtenerConexion().prepareStatement(sql);
            pstmt.setString(1, estudiante.getNombreEst());
            pstmt.setDouble(2, estudiante.getNota1());
            pstmt.setDouble(3, estudiante.getNota2());
            pstmt.setDouble(4, estudiante.getPromedio());
            pstmt.setString(5, estudiante.getEstado());
            pstmt.executeUpdate();
            conexionDB.obtenerConexion().close();
        }catch(SQLException e){
            System.out.println("Exception: insertarEstudiante");
            System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<Estudiante> listarEstudiantes(){
        listaEstudiantes.clear();
        try{
            conexionDB.establecerConexion();
            Statement statement = conexionDB.obtenerConexion().createStatement();
            String data = "SELECT * FROM Estudiante;";
            ResultSet rs = statement.executeQuery(data);
            while (rs.next()) {
                Estudiante estudiante = new Estudiante(rs.getInt("id"), 
                                                       rs.getString("nombreEst"), 
                                                       rs.getDouble("nota1"), 
                                                       rs.getDouble("nota2"),
                                                       rs.getDouble("promedio"),
                                                       rs.getString("estado"));
                listaEstudiantes.add(estudiante);
            }
            conexionDB.obtenerConexion().close();
        }catch(SQLException e){
            System.out.println("Exception: listarEstudiantes");
            System.out.println(e.getMessage());
        }
        return listaEstudiantes;
    }

    public void actualizarEstudiante(Estudiante estudiante){
        calcularPromedios();
        calculoEstados();
        try{
            conexionDB.establecerConexion();
            String sql = "UPDATE Estudiante SET nombreEst = ?, nota1 = ?, nota2 = ?, promedio = ?, estado = ? WHERE id = ?";
            PreparedStatement pstmt = conexionDB.obtenerConexion().prepareStatement(sql);
            pstmt.setString(1, estudiante.getNombreEst());
            pstmt.setDouble(2, estudiante.getNota1());
            pstmt.setDouble(3, estudiante.getNota2());
            pstmt.setDouble(4, estudiante.getPromedio());
            pstmt.setString(5, estudiante.getEstado());
            pstmt.setInt(6, estudiante.getId());
            pstmt.executeUpdate();
            conexionDB.obtenerConexion().close();
        }catch(SQLException e){
            System.out.println("Exception: actualizarEstudiante");
            System.out.println(e.getMessage());
        }
    }

    public void eliminarEstudiante(int id){
        try{
            conexionDB.establecerConexion();
            String sql = "DELETE FROM Estudiante WHERE id = ?";
            PreparedStatement pstmt = conexionDB.obtenerConexion().prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            conexionDB.obtenerConexion().close();
        }catch(SQLException e){
            System.out.println("Exception: eliminarEstudiante");
            System.out.println(e.getMessage());
        }
    }

    public void calcularPromedios() {
        for (Estudiante estudiante : listaEstudiantes) {
            double promedio = (estudiante.getNota1() + estudiante.getNota2()) / 2;
            estudiante.setPromedio(promedio);
        }
    }
    
    public void calculoEstados() {
        for (Estudiante estudiante : listaEstudiantes) {
            if (estudiante.getPromedio() >= 7) {
                estudiante.setEstado("Aprobado");
            } else {
                estudiante.setEstado("Reprobado");
            }
        }
    }
}
