package vista;

import controlador.ProcesarEstudiantes;
import modelo.Estudiante;
import java.sql.SQLException;
import java.util.Scanner;

public class TestEstudiante {

    public static void main(String[] args) throws SQLException {
        
        ProcesarEstudiantes procesar = new ProcesarEstudiantes();
        Scanner scanner = new Scanner(System.in);
        int option;
        
        do {
            System.out.println("---------------------MENU---------------------");
            System.out.println("[1] Añadir un nuevo estudiante.");
            System.out.println("[2] Imprimir en pantalla lista de estudiantes.");
            System.out.println("[3] Actualizar un estudiante.");
            System.out.println("[4] Eliminar un estudiante.");
            System.out.println("[0] Salir.");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (option) {
                case 1:
                    System.out.print("Ingrese nombre del estudiante: ");
                    String nombreEst = scanner.nextLine();
                    
                    System.out.print("Ingrese nota 1: ");
                    double nota1 = scanner.nextDouble();
                    
                    System.out.print("Ingrese nota 2: ");
                    double nota2 = scanner.nextDouble();
                    
                    Estudiante nuevoEstudiante = new Estudiante(nombreEst, nota1, nota2);
                    procesar.listarEstudiantes().add(nuevoEstudiante);
                    
                    procesar.calcularPromedios();
                    procesar.calculoEstados();
                    
                    procesar.insertarEstudiante(nuevoEstudiante);
                    
                    System.out.println("Estudiante añadido.\n");
                    break;
                
                case 2:
                    System.out.println("Lista de estudiantes:");
                    for (Estudiante estudiante : procesar.listarEstudiantes()) {
                        System.out.printf("%s", estudiante);
                    }
                    System.out.println();
                    break;

                case 3:
                    System.out.print("Ingrese el ID del estudiante a actualizar: ");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    
                    Estudiante estudianteActualizar = null;
                    for (Estudiante estudiante : procesar.listarEstudiantes()) {
                        if (estudiante.getId() == idActualizar) {
                            estudianteActualizar = estudiante;
                            break;
                        }
                    }
                    
                    if (estudianteActualizar != null) {
                        System.out.print("Ingrese nuevo nombre del estudiante: ");
                        nombreEst = scanner.nextLine();
                        
                        System.out.print("Ingrese nueva nota 1: ");
                        nota1 = scanner.nextDouble();
                        
                        System.out.print("Ingrese nueva nota 2: ");
                        nota2 = scanner.nextDouble();
                        
                        estudianteActualizar.setNombreEst(nombreEst);
                        estudianteActualizar.setNota1(nota1);
                        estudianteActualizar.setNota2(nota2);
                        
                        procesar.calcularPromedios();
                        procesar.calculoEstados();
                        
                        procesar.actualizarEstudiante(estudianteActualizar);
                        
                        System.out.println("Estudiante actualizado exitosamente.\n");
                    } else {
                        System.out.println("Estudiante no encontrado.\n");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el ID del estudiante a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    
                    procesar.eliminarEstudiante(idEliminar);
                    System.out.println("Estudiante eliminado exitosamente.\n");
                    break;
                
            }
        } while (option != 0);
    }
}
