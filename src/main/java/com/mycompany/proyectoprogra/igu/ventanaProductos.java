
package com.mycompany.proyectoprogra.igu;

import com.mycompany.proyectoprogra.entitys.Categorias;
import com.mycompany.proyectoprogra.entitys.Clientes;
import com.mycompany.proyectoprogra.entitys.Controladora;
import com.mycompany.proyectoprogra.entitys.Productos;
import static java.awt.SystemColor.control;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ventanaProductos extends javax.swing.JFrame {
    
    Controladora control = new Controladora();
    private long productoSeleccionadoId = -1;
    private JFrame ventanaPrincipal;
    
    public ventanaProductos(JFrame ventanaPrincipal) {
        initComponents();
        this.ventanaPrincipal = ventanaPrincipal;
        cargarCategoriasEnCombo();
        cargarTabla();
    }
    
    
    private void cargarCategoriasEnCombo() {
    List<Categorias> categorias = control.traerCategorias();
    categoriaCombo.removeAllItems();
    for (Categorias cat : categorias) {
        categoriaCombo.addItem(cat.toString()); 
    }
}
    
    
    public void cargarTabla() {
        
        String[] columnas = {"ID", "Nombre", "Categoría", "Precio Unitario"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        List<Productos> listaProductos = control.traerProductos();
        for (Productos p : listaProductos) {
            Object[] fila = {
                p.getIdProducto(),
                p.getNombreProducto(),
                p.getIdCategoria().getCategoria() + " - " + p.getIdCategoria().getSubcategoria(),
                p.getPrecioUnitario() 
            };
            modelo.addRow(fila);
        }

        tableProductos.setModel(modelo);
    }
    
   private void limpiarCampos() {
        NombreTXT.setText("");
        precioTXT.setText(""); 
        categoriaCombo.setSelectedIndex(-1); 
        productoSeleccionadoId = -1;
    }
    
    private void guardarProducto() {
        String nombre = NombreTXT.getText();
        String precioTexto = precioTXT.getText(); 
        String categoriaStringSeleccionada = (String) categoriaCombo.getSelectedItem();
        Categorias categoriaSeleccionada = null;
        float precioUnitario = 0.0f; 
       
        if (nombre.isBlank()) {
            JOptionPane.showMessageDialog(this, "El nombre del producto es obligatorio.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar y parsear el precio
        try {
            precioUnitario = Float.parseFloat(precioTexto);
            if (precioUnitario <= 0) {
                JOptionPane.showMessageDialog(this, "El precio unitario debe ser un número positivo.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio unitario debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Buscar la categoría por su String
        if (categoriaStringSeleccionada != null) {
            List<Categorias> todasLasCategorias = control.traerCategorias();
            for (Categorias cat : todasLasCategorias) {
                if (cat.toString().equals(categoriaStringSeleccionada)) {
                    categoriaSeleccionada = cat;
                    break;
                }
            }
        }

        // Validar que se haya seleccionado una categoría
        if (categoriaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una categoría válida.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            control.agregarProducto(nombre, precioUnitario, categoriaSeleccionada);
            JOptionPane.showMessageDialog(this, "Producto guardado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTabla();
            limpiarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); 
        }
    }


private void modificarProducto() {
        String nuevoNombre = NombreTXT.getText();
        String nuevoPrecioTexto = precioTXT.getText(); // Capturar el nuevo precio
        String categoriaStringSeleccionada = (String) categoriaCombo.getSelectedItem();
        Categorias nuevaCategoria = null;
        float nuevoPrecioUnitario = 0.0f;

        // Validar que haya un producto seleccionado
        if (productoSeleccionadoId == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Validar nombre
        if (nuevoNombre.isBlank()) {
            JOptionPane.showMessageDialog(this, "El nombre del producto es obligatorio.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar y parsear el nuevo precio
        try {
            nuevoPrecioUnitario = Float.parseFloat(nuevoPrecioTexto);
            if (nuevoPrecioUnitario <= 0) {
                JOptionPane.showMessageDialog(this, "El precio unitario debe ser un número positivo.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio unitario debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Buscar la nueva categoría
        if (categoriaStringSeleccionada != null) {
            List<Categorias> todasLasCategorias = control.traerCategorias();
            for (Categorias cat : todasLasCategorias) {
                if (cat.toString().equals(categoriaStringSeleccionada)) {
                    nuevaCategoria = cat;
                    break;
                }
            }
        }

        // Validar que se haya seleccionado una categoría
        if (nuevaCategoria == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una categoría válida.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            control.modificarProducto(productoSeleccionadoId, nuevoNombre, nuevoPrecioUnitario, nuevaCategoria);
            JOptionPane.showMessageDialog(this, "Producto modificado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTabla();
            limpiarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }




    
    
    private void eliminarProducto() {
        if (productoSeleccionadoId != -1) {
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este producto?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                try {
                    control.eliminarProducto(productoSeleccionadoId);
                    JOptionPane.showMessageDialog(this, "Producto eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarTabla();
                    limpiarCampos();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        GuardarBTN = new javax.swing.JButton();
        EliminarBTN = new javax.swing.JButton();
        NombreTXT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        categoriaCombo = new javax.swing.JComboBox<>();
        precioTXT = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        volverBTN = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProductos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("NOMBRE");

        GuardarBTN.setBackground(new java.awt.Color(153, 255, 153));
        GuardarBTN.setForeground(new java.awt.Color(0, 0, 0));
        GuardarBTN.setText("Guardar producto");
        GuardarBTN.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
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
        EliminarBTN.setForeground(new java.awt.Color(0, 0, 0));
        EliminarBTN.setText("Eliminar");
        EliminarBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EliminarBTNMouseClicked(evt);
            }
        });
        EliminarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarBTNActionPerformed(evt);
            }
        });

        jLabel4.setText("Categoria");

        categoriaCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("PRECIO");

        volverBTN.setBackground(new java.awt.Color(255, 255, 255));
        volverBTN.setForeground(new java.awt.Color(0, 0, 0));
        volverBTN.setText("Atras");
        volverBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(NombreTXT))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(21, 21, 21)
                                .addComponent(precioTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(categoriaCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(14, 14, 14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(volverBTN)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(EliminarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(GuardarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(volverBTN)
                .addGap(75, 75, 75)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(NombreTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(precioTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(categoriaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(130, 130, 130)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GuardarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EliminarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableProductos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    private void GuardarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarBTNActionPerformed

        guardarProducto();
    }//GEN-LAST:event_GuardarBTNActionPerformed

    private void tableProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductosMouseClicked
        int filaSeleccionada = tableProductos.getSelectedRow();
    if (filaSeleccionada >= 0) {
        productoSeleccionadoId = (Long) tableProductos.getValueAt(filaSeleccionada, 0);
        String nombre = (String) tableProductos.getValueAt(filaSeleccionada, 1);
        String categoriaNombreEnTabla = (String) tableProductos.getValueAt(filaSeleccionada, 2); 

        NombreTXT.setText(nombre);

        // Seleccion de item del comboBOx
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) categoriaCombo.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            if (model.getElementAt(i).equals(categoriaNombreEnTabla)) {
                categoriaCombo.setSelectedIndex(i);
                break;
            }
        }
    }
    }//GEN-LAST:event_tableProductosMouseClicked

    //NO TOCAR ESTO
    private void GuardarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarBTNMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_GuardarBTNMouseClicked

    private void EliminarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarBTNMouseClicked
      
        
    }//GEN-LAST:event_EliminarBTNMouseClicked

    private void EliminarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarBTNActionPerformed
        eliminarProducto();
    }//GEN-LAST:event_EliminarBTNActionPerformed

    private void volverBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBTNActionPerformed
        ventanaPrincipal.setVisible(true);
        this.dispose(); 
    }//GEN-LAST:event_volverBTNActionPerformed

  
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EliminarBTN;
    private javax.swing.JButton GuardarBTN;
    private javax.swing.JTextField NombreTXT;
    private javax.swing.JComboBox<String> categoriaCombo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField precioTXT;
    private javax.swing.JTable tableProductos;
    private javax.swing.JButton volverBTN;
    // End of variables declaration//GEN-END:variables
}
