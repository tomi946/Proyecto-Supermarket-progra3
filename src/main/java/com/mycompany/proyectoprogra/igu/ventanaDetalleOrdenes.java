
package com.mycompany.proyectoprogra.igu;

import com.mycompany.proyectoprogra.controllers.DetalleordenesJpaController;
import com.mycompany.proyectoprogra.entitys.Clientes;
import com.mycompany.proyectoprogra.entitys.Controladora;
import com.mycompany.proyectoprogra.entitys.Detalleordenes;
import static java.awt.SystemColor.control;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ventanaDetalleOrdenes extends javax.swing.JFrame {
    
    Controladora control = new Controladora();
    private long clienteSeleccionadoId = -1;
    private JFrame ventanaPrincipal;
    
    public ventanaDetalleOrdenes(JFrame ventanaPrincipal) {
        initComponents();
        this.ventanaPrincipal = ventanaPrincipal;
        cargarTablaDetalleOrdenes();
    }
    
    public ventanaDetalleOrdenes() {
        initComponents();
        cargarTablaDetalleOrdenes();
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(volverBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volverBTN))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

       JOptionPane.showMessageDialog(this, info, "📦 Información completa", JOptionPane.INFORMATION_MESSAGE);
      }
    }//GEN-LAST:event_tableDetalleMouseClicked
    
    private void volverBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBTNActionPerformed
        ventanaPrincipal.setVisible(true); 
        dispose(); 
    }//GEN-LAST:event_volverBTNActionPerformed

  
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableDetalle;
    private javax.swing.JButton volverBTN;
    // End of variables declaration//GEN-END:variables
}
