/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyectoprogra.igu;

import com.mycompany.proyectoprogra.entitys.Clientes;
import com.mycompany.proyectoprogra.entitys.Controladora;
import static java.awt.SystemColor.control;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ventanaCliente extends javax.swing.JFrame {
    
    Controladora control = new Controladora();
    private long clienteSeleccionadoId = -1;
    private JFrame ventanaPrincipal;
    
    public ventanaCliente(JFrame ventanaPrincipal) { 
        System.out.println("Empieza la carga de los componentes");
        initComponents();
        System.out.println("Termina la carga de los componentes");
        this.ventanaPrincipal = ventanaPrincipal;
    }
    
    public ventanaCliente() {
        initComponents();
    }
    
    public void cargarTabla() {
        String[] columnas = {"ID", "Nombre"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        List<Clientes> listaClientes = control.traerClientes();
        for (Clientes c : listaClientes) {
            Object[] fila = {c.getIdCliente(), c.getNombreCliente()};
            modelo.addRow(fila);
        }

        tableClientes.setModel(modelo);
    }
    
    private void limpiarCampos() {
        NombreTXT.setText("");
        clienteSeleccionadoId = -1;
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        GuardarBTN = new javax.swing.JButton();
        EliminarBTN = new javax.swing.JButton();
        NombreTXT = new javax.swing.JTextField();
        volverBTN = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableClientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Nombre del cliente");

        GuardarBTN.setBackground(new java.awt.Color(153, 255, 153));
        GuardarBTN.setForeground(new java.awt.Color(102, 102, 102));
        GuardarBTN.setText("Guardar");
        GuardarBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GuardarBTNMouseClicked(evt);
            }
        });
        GuardarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarBTNActionPerformed(evt);
            }
        });

        EliminarBTN.setBackground(new java.awt.Color(255, 204, 204));
        EliminarBTN.setForeground(new java.awt.Color(102, 0, 0));
        EliminarBTN.setText("Eliminar");
        EliminarBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EliminarBTNMouseClicked(evt);
            }
        });

        NombreTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreTXTActionPerformed(evt);
            }
        });

        volverBTN.setBackground(new java.awt.Color(255, 255, 255));
        volverBTN.setForeground(new java.awt.Color(102, 102, 102));
        volverBTN.setText("Atras");
        volverBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBTNActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setForeground(new java.awt.Color(0, 0, 102));
        jButton1.setText("Modificar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(190, 190, 190))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(GuardarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NombreTXT)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(volverBTN)
                                    .addComponent(EliminarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(volverBTN)
                .addGap(57, 57, 57)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NombreTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(GuardarBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addComponent(EliminarBTN))
        );

        tableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableClientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 24, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarCliente() {
        String nombre = NombreTXT.getText();
        if (!nombre.isBlank()) {
            try {
                control.agregarCliente(nombre);
                JOptionPane.showMessageDialog(this, "Cliente guardado exitosamente.");
                cargarTabla();
                limpiarCampos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.");
        }
    }
    
     private void eliminarCliente() {
        if (clienteSeleccionadoId != -1) {
            control.eliminarCliente(clienteSeleccionadoId);
            JOptionPane.showMessageDialog(this, "Cliente eliminado.");
            cargarTabla();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente de la tabla.");
        }
    }
    
    private void modificarCliente() {
    if (clienteSeleccionadoId != -1) {
        String nuevoNombre = NombreTXT.getText();

        if (!nuevoNombre.isBlank()) { 
            try { 
                control.modificarCliente(clienteSeleccionadoId, nuevoNombre); 
                JOptionPane.showMessageDialog(this, "Cliente modificado correctamente.", "Modificación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                cargarTabla(); 
                limpiarCampos(); 
            } catch (Exception e) { 
                
                JOptionPane.showMessageDialog(this, "Error al modificar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                
                e.printStackTrace(); 
            }
        } else {
            JOptionPane.showMessageDialog(this, "El nuevo nombre no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente de la tabla para modificarlo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
}
    
    private void tableClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClientesMouseClicked
       int filaSeleccionada = tableClientes.getSelectedRow();
        if (filaSeleccionada >= 0) {
            clienteSeleccionadoId = (Long) tableClientes.getValueAt(filaSeleccionada, 0);
            String nombre = (String) tableClientes.getValueAt(filaSeleccionada, 1);
            NombreTXT.setText(nombre);
        }
    }//GEN-LAST:event_tableClientesMouseClicked

    private void EliminarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarBTNMouseClicked
        eliminarCliente();
        
    }//GEN-LAST:event_EliminarBTNMouseClicked

    private void volverBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBTNActionPerformed
        ventanaPrincipal.setVisible(true); 
        dispose(); 
    }//GEN-LAST:event_volverBTNActionPerformed

    private void NombreTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreTXTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreTXTActionPerformed

    private void GuardarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarBTNActionPerformed
        guardarCliente();
    }//GEN-LAST:event_GuardarBTNActionPerformed

    private void GuardarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarBTNMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_GuardarBTNMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        modificarCliente(); 
    }//GEN-LAST:event_jButton1ActionPerformed

  
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EliminarBTN;
    private javax.swing.JButton GuardarBTN;
    private javax.swing.JTextField NombreTXT;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableClientes;
    private javax.swing.JButton volverBTN;
    // End of variables declaration//GEN-END:variables
}
