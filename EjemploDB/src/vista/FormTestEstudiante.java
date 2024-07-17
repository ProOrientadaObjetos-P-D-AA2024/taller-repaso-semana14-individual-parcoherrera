package vista;

import controlador.ProcesarEstudiantes;
import modelo.Estudiante;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormTestEstudiante extends javax.swing.JFrame {

    private ProcesarEstudiantes procesar = new ProcesarEstudiantes();
    private DefaultTableModel modeloTabla;

    public FormTestEstudiante() {
        iniComponents();
        inicializarTabla();
        actualizarTabla();
    }

    private void inicializarTabla() {
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Nota 1", "Nota 2", "Promedio", "Estado"}, 0);
        tableEstudiantes.setModel(modeloTabla);
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0); // Limpiar la tabla
        for (Estudiante estudiante : procesar.listarEstudiantes()) {
            modeloTabla.addRow(new Object[]{
                estudiante.getId(),
                estudiante.getNombreEst(),
                estudiante.getNota1(),
                estudiante.getNota2(),
                estudiante.getPromedio(),
                estudiante.getEstado()
            });
        }
    }

    private void crearEstudiante() {
        String nombreEst = JOptionPane.showInputDialog("Ingrese nombre del estudiante:");
        double nota1 = Double.parseDouble(JOptionPane.showInputDialog("Ingrese nota 1:"));
        double nota2 = Double.parseDouble(JOptionPane.showInputDialog("Ingrese nota 2:"));

        // Crear el estudiante
        Estudiante nuevoEstudiante = new Estudiante(nombreEst, nota1, nota2);
        
        // Calcular el promedio y el estado
        nuevoEstudiante.setPromedio((nota1 + nota2) / 2);
        if (nuevoEstudiante.getPromedio() >= 7) {
            nuevoEstudiante.setEstado("Aprobado");
        } else {
            nuevoEstudiante.setEstado("Reprobado");
        }

        // Insertar el estudiante en la base de datos
        procesar.insertarEstudiante(nuevoEstudiante);
        
        // Actualizar la tabla
        actualizarTabla();
    }

    private void modificarEstudiante() {
        int filaSeleccionada = tableEstudiantes.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
            String nombreEst = JOptionPane.showInputDialog("Ingrese nuevo nombre del estudiante:", modeloTabla.getValueAt(filaSeleccionada, 1));
            double nota1 = Double.parseDouble(JOptionPane.showInputDialog("Ingrese nueva nota 1:", modeloTabla.getValueAt(filaSeleccionada, 2)));
            double nota2 = Double.parseDouble(JOptionPane.showInputDialog("Ingrese nueva nota 2:", modeloTabla.getValueAt(filaSeleccionada, 3)));

            Estudiante estudianteActualizar = new Estudiante(id, nombreEst, nota1, nota2, 0, "");
            
            // Calcular el promedio y el estado
            estudianteActualizar.setPromedio((nota1 + nota2) / 2);
            if (estudianteActualizar.getPromedio() >= 7) {
                estudianteActualizar.setEstado("Aprobado");
            } else {
                estudianteActualizar.setEstado("Reprobado");
            }

            procesar.actualizarEstudiante(estudianteActualizar);
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante para modificar.");
        }
    }

    private void borrarEstudiante() {
        int filaSeleccionada = tableEstudiantes.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
            procesar.eliminarEstudiante(id);
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante para borrar.");
        }
    }
    
    @SuppressWarnings("unchecked")
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCrear = new javax.swing.JButton();
        btnLeer = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEstudiantes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCrear.setText("Crear Estudiante");

        btnLeer.setText("Leer DataBase");

        btnModificar.setText("Modificar Estudiante");

        btnBorrar.setText("Borrar Estudiante");

        tableEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableEstudiantes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCrear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLeer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBorrar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrear)
                    .addComponent(btnLeer)
                    .addComponent(btnModificar)
                    .addComponent(btnBorrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iniComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEstudiantes = new javax.swing.JTable();
        btnCrear = new javax.swing.JButton();
        btnLeer = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Nombre", "Nota 1", "Nota 2", "Promedio", "Estado"}
        ));
        jScrollPane1.setViewportView(tableEstudiantes);

        btnCrear.setText("Crear Estudiante");
        btnCrear.addActionListener(evt -> crearEstudiante());

        btnLeer.setText("Leer Base de Datos");
        btnLeer.addActionListener(evt -> actualizarTabla());

        btnModificar.setText("Modificar Estudiante");
        btnModificar.addActionListener(evt -> modificarEstudiante());

        btnBorrar.setText("Borrar Estudiante");
        btnBorrar.addActionListener(evt -> borrarEstudiante());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCrear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLeer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBorrar)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrear)
                    .addComponent(btnLeer)
                    .addComponent(btnModificar)
                    .addComponent(btnBorrar))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FormTestEstudiante().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnLeer;
    private javax.swing.JButton btnModificar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableEstudiantes;
    // End of variables declaration//GEN-END:variables
}
