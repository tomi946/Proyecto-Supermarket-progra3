
package com.mycompany.proyectoprogra.igu;

import com.mycompany.proyectoprogra.controllers.DetalleordenesJpaController;
import com.mycompany.proyectoprogra.entitys.Categorias;
import com.mycompany.proyectoprogra.entitys.Clientes;
import com.mycompany.proyectoprogra.entitys.Controladora;
import com.mycompany.proyectoprogra.entitys.Detalleordenes;
import static java.awt.SystemColor.control;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ventanaDetalleOrdenes extends javax.swing.JFrame {
    
    Controladora control = new Controladora();
    private long clienteSeleccionadoId = -1;
    private JFrame ventanaPrincipal;
    
    public ventanaDetalleOrdenes(JFrame ventanaPrincipal) {
        initComponents();
        this.ventanaPrincipal = ventanaPrincipal;
        cargarTablaDetalleOrdenes();
        cargarCategoriasEnCombo();
    }
    
    public ventanaDetalleOrdenes() {
        initComponents();
        cargarTablaDetalleOrdenes();
    }
    
    private void cargarCategoriasEnCombo() {
        List<Categorias> categorias = control.traerCategorias();
        categoriaSelect.removeAllItems();
        for (Categorias cat : categorias) {
            categoriaSelect.addItem(cat.toString()); 
        }
    }
    
    public void cargarTablaDetalleOrdenes() {
        String[] columnas = {
            "ID Orden", 
            "ID Cliente",
            "ID Producto",
            "Fecha",
            "Nombre Producto", 
            "Cantidad", 
            "Precio Unitario", 
            "Categoría", 
            "Total Venta", 
            "Ganancia", 
            "ID Envío",
            "Estado Envío",
            "Ciudad",
            "Código Postal",
            "Modo de Envío"
        };

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        List<Detalleordenes> detalles = control.traerDetalleOrdenes();

        for (Detalleordenes detalle : detalles) {
            Object[] fila = new Object[]{
                detalle.getDetalleordenesPK().getIdOrden(),
                detalle.getOrdenes().getIdCliente().getIdCliente(),
                detalle.getDetalleordenesPK().getIdProducto(),    
                detalle.getOrdenes().getFechaOrden(),
                detalle.getProductos().getNombreProducto(), 
                detalle.getCantidad(),
                detalle.getProductos().getPrecioUnitario(),
                detalle.getProductos().getIdCategoria(),
                String.format("%.2f", detalle.getTotalVenta()),
                String.format("%.2f", detalle.getGanancia()),
                detalle.getOrdenes().getIdEnvio().getIdEnvio(),
                detalle.getOrdenes().getIdEnvio().getEstado(),
                detalle.getOrdenes().getIdEnvio().getCiudad(),
                detalle.getOrdenes().getIdEnvio().getCodigoPostal(),
                detalle.getOrdenes().getIdEnvio().getModoEnvio()
            };
            modelo.addRow(fila);
        }

        tableDetalle.setModel(modelo);
    }




    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        volverBTN = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDetalle = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        fechaSelect = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        categoriaSelect = new javax.swing.JComboBox<>();
        exportarBTN = new javax.swing.JButton();
        limpiarFiltroBTN = new javax.swing.JButton();
        filtrarBTN1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        volverBTN.setText("Atras");
        volverBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBTNActionPerformed(evt);
            }
        });

        tableDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDetalleMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDetalle);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Fecha");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Categoria");

        categoriaSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        exportarBTN.setBackground(new java.awt.Color(153, 255, 153));
        exportarBTN.setForeground(new java.awt.Color(51, 51, 51));
        exportarBTN.setText("Exportar ordenes");
        exportarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportarBTNActionPerformed(evt);
            }
        });

        limpiarFiltroBTN.setBackground(new java.awt.Color(255, 255, 204));
        limpiarFiltroBTN.setForeground(new java.awt.Color(102, 102, 102));
        limpiarFiltroBTN.setText("Limpiar filtro");
        limpiarFiltroBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarFiltroBTNActionPerformed(evt);
            }
        });

        filtrarBTN1.setBackground(new java.awt.Color(153, 204, 255));
        filtrarBTN1.setForeground(new java.awt.Color(255, 255, 255));
        filtrarBTN1.setText("Filtrar ordenes");
        filtrarBTN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtrarBTN1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(volverBTN)
                        .addGap(76, 76, 76)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(categoriaSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(limpiarFiltroBTN)
                        .addGap(18, 18, 18)
                        .addComponent(filtrarBTN1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exportarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(limpiarFiltroBTN)
                                .addComponent(jLabel2)
                                .addComponent(filtrarBTN1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(exportarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jLabel1))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(volverBTN))
                        .addComponent(categoriaSelect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fechaSelect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    
    
    private void tableDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDetalleMouseClicked
       int filaSeleccionada = tableDetalle.getSelectedRow();
       if (filaSeleccionada >= 0) {
            // Leer valores de la fila seleccionada
         Object idOrden = tableDetalle.getValueAt(filaSeleccionada, 0);
         Object idCliente = tableDetalle.getValueAt(filaSeleccionada, 1);
         Object idProducto = tableDetalle.getValueAt(filaSeleccionada, 2);
         Object fechaOrden = tableDetalle.getValueAt(filaSeleccionada, 3); 
         Object nombreProducto = tableDetalle.getValueAt(filaSeleccionada, 4);
         Object cantidad = tableDetalle.getValueAt(filaSeleccionada, 5);
         Object precioUnitario = tableDetalle.getValueAt(filaSeleccionada, 6);
         Object categoria = tableDetalle.getValueAt(filaSeleccionada, 7);
         Object totalVenta = tableDetalle.getValueAt(filaSeleccionada, 8);
         Object ganancia = tableDetalle.getValueAt(filaSeleccionada, 9);
         Object idEnvio = tableDetalle.getValueAt(filaSeleccionada, 10);
         Object estadoEnvio = tableDetalle.getValueAt(filaSeleccionada, 11);
         Object ciudad = tableDetalle.getValueAt(filaSeleccionada, 12);
         Object codigoPostal = tableDetalle.getValueAt(filaSeleccionada, 13);
         Object modoEnvio = tableDetalle.getValueAt(filaSeleccionada, 14);

        // Mostrar todos los datos en un mensaje
       String info = String.format(
            "🧾 Detalle de Orden\n\n" +
            "ID Orden: %s\n" +
            "ID Cliente: %s\n" +
            "ID Producto: %s\n" +
            "Fecha de Orden: %s\n" + // 👈 Agregado aquí
            "Nombre Producto: %s\n" +
            "Cantidad: %s\n" +
            "Precio Unitario: %s\n" +
            "Categoría: %s\n" +
            "Total Venta: %s\n" +
            "Ganancia: %s\n" +
            "ID Envío: %s\n" +
            "Estado de Envío: %s\n" +
            "Ciudad: %s\n" +
            "Código Postal: %s\n" +
            "Modo de Envío: %s",
            idOrden, idCliente, idProducto, fechaOrden,
            nombreProducto, cantidad, precioUnitario, categoria,
            totalVenta, ganancia, idEnvio, estadoEnvio, ciudad, codigoPostal, modoEnvio
       );

       JOptionPane.showMessageDialog(this, info, "Información completa", JOptionPane.INFORMATION_MESSAGE);
      }
    }//GEN-LAST:event_tableDetalleMouseClicked
    
    private void volverBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBTNActionPerformed
        ventanaPrincipal.setVisible(true); 
        dispose(); 
    }//GEN-LAST:event_volverBTNActionPerformed

    private void exportarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportarBTNActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar como");
        fileChooser.setSelectedFile(new java.io.File("detalle_ordenes.xlsx"));

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            try (Workbook workbook = new XSSFWorkbook()) {
                org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Detalle Ordenes");

                DefaultTableModel model = (DefaultTableModel) tableDetalle.getModel();

                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < model.getColumnCount(); i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(model.getColumnName(i));
                }

                for (int i = 0; i < model.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Object value = model.getValueAt(i, j);
                        Cell cell = row.createCell(j);
                        cell.setCellValue(value != null ? value.toString() : "");
                    }
                }

                for (int i = 0; i < model.getColumnCount(); i++) {
                    sheet.autoSizeColumn(i);
                }

                try (FileOutputStream out = new FileOutputStream(fileToSave)) {
                    workbook.write(out);
                }

                JOptionPane.showMessageDialog(this, "Tabla exportada con éxito a:\n" + fileToSave.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al exportar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_exportarBTNActionPerformed

    private void limpiarFiltroBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarFiltroBTNActionPerformed
        fechaSelect.setDate(null);
        if (categoriaSelect.getItemCount() > 0) {
            categoriaSelect.setSelectedIndex(0);
        }
        cargarTablaDetalleOrdenes();
    }//GEN-LAST:event_limpiarFiltroBTNActionPerformed

    private void filtrarBTN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtrarBTN1ActionPerformed
        java.util.Date fechaSeleccionada = fechaSelect.getDate();
        String categoriaSeleccionada = (String) categoriaSelect.getSelectedItem();

        DefaultTableModel modelo = (DefaultTableModel) tableDetalle.getModel();
        modelo.setRowCount(0); 

        List<Detalleordenes> detalles = control.traerDetalleOrdenes();

        for (Detalleordenes detalle : detalles) {
            java.util.Date fechaOrden = detalle.getOrdenes().getFechaOrden();
            String categoria = detalle.getProductos().getIdCategoria().toString(); 

            boolean coincideFecha = true;
            boolean coincideCategoria = true;

            if (fechaSeleccionada != null) {
                
                coincideFecha = esMismaFecha(fechaOrden, fechaSeleccionada);
            }

            if (categoriaSeleccionada != null && !categoriaSeleccionada.isEmpty()) {
                coincideCategoria = categoriaSeleccionada.equalsIgnoreCase(categoria);
            }

            if (coincideFecha && coincideCategoria) {
                Object[] fila = new Object[]{
                    detalle.getDetalleordenesPK().getIdOrden(),
                    detalle.getOrdenes().getIdCliente().getIdCliente(),
                    detalle.getDetalleordenesPK().getIdProducto(),    
                    detalle.getOrdenes().getFechaOrden(),
                    detalle.getProductos().getNombreProducto(), 
                    detalle.getCantidad(),
                    detalle.getProductos().getPrecioUnitario(),
                    detalle.getProductos().getIdCategoria().toString(),
                    String.format("%.2f", detalle.getTotalVenta()),
                    String.format("%.2f", detalle.getGanancia()),
                    detalle.getOrdenes().getIdEnvio().getIdEnvio(),
                    detalle.getOrdenes().getIdEnvio().getEstado(),
                    detalle.getOrdenes().getIdEnvio().getCiudad(),
                    detalle.getOrdenes().getIdEnvio().getCodigoPostal(),
                    detalle.getOrdenes().getIdEnvio().getModoEnvio()
                };
                modelo.addRow(fila);
            }
        }

        if (modelo.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No se encontraron órdenes con los filtros seleccionados.");
        }
    }//GEN-LAST:event_filtrarBTN1ActionPerformed
    
    private boolean esMismaFecha(java.util.Date d1, java.util.Date d2) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(d1).equals(sdf.format(d2));
    }
  
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> categoriaSelect;
    private javax.swing.JButton exportarBTN;
    private com.toedter.calendar.JDateChooser fechaSelect;
    private javax.swing.JButton filtrarBTN1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limpiarFiltroBTN;
    private javax.swing.JTable tableDetalle;
    private javax.swing.JButton volverBTN;
    // End of variables declaration//GEN-END:variables
}
