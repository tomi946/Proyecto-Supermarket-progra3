
package com.mycompany.proyectoprogra.igu;

import com.mycompany.proyectoprogra.entitys.Clientes;
import com.mycompany.proyectoprogra.entitys.Controladora;
import com.mycompany.proyectoprogra.entitys.Detalleordenes;
import com.mycompany.proyectoprogra.entitys.Productos;
import static java.awt.SystemColor.control;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


public class ventanaOrdenes extends javax.swing.JFrame {
    
    private List<Detalleordenes> detallesTemp = new ArrayList<>();
    Controladora control = new Controladora();
    private long productoSeleccionadoId = -1;
   
    private JFrame ventanaPrincipal;

    public ventanaOrdenes(JFrame ventanaPrincipal) {
        initComponents();
        this.ventanaPrincipal = ventanaPrincipal;

        inicializarTablaDetallesOrden();

        tableProductos.setModel(new DefaultTableModel(new Object[]{"Cargando..."}, 0));

        cargarModosEnvioConThread();
        
        cargarClientesEnCombo();
        
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Producto", "Cantidad"}, 0);
        jTable1.setModel(modelo);
    }
    
    public void cargarProductos() {
        try {
            List<Productos> listaProductos = control.traerProductos(); 

            SwingUtilities.invokeLater(() -> {
                DefaultTableModel modelo = new DefaultTableModel(
                    new Object[]{"ID", "Nombre", "Categoría", "Precio Unitario"}, 0
                );

                for (Productos p : listaProductos) {
                    Object[] fila = {
                        p.getIdProducto(),
                        p.getNombreProducto(),
                        p.getIdCategoria() != null
                            ? p.getIdCategoria().getCategoria() + " - " + p.getIdCategoria().getSubcategoria()
                            : "Sin Categoría",
                        p.getPrecioUnitario()
                    };
                    modelo.addRow(fila);
                }

                tableProductos.setModel(modelo);
            });

        } catch (Exception e) {
            SwingUtilities.invokeLater(() ->
                JOptionPane.showMessageDialog(this, "Error al cargar productos: " + e.getMessage())
            );
            e.printStackTrace();
        }
    }
    
    private void cargarModosEnvioConThread() {
        Thread hiloEnvios = new Thread(() -> {
            try {
                // Simulamos obtenerlo (puede venir de BD si querés)
                String[] modosEnvio = {
                    "Estándar",
                    "Express",
                    "Retiro en Sucursal",
                    "Internacional"
                };

                // Actualización segura de la UI
                SwingUtilities.invokeLater(() -> {
                    jComboBox1.setModel(new DefaultComboBoxModel<>(modosEnvio));
                });

            } catch (Exception e) {
                SwingUtilities.invokeLater(() ->
                    JOptionPane.showMessageDialog(this, "Error al cargar modos de envío: " + e.getMessage())
                );
                e.printStackTrace();
            }
        });

        hiloEnvios.start();
    }
    
    //  Métodos de Carga de Datos en la UI 
    private void cargarTablaProductos() {
        String[] columnas = {"ID", "Nombre", "Categoría", "Precio Unitario"}; // Agrega Precio Unitario
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        List<Productos> listaProductos = control.traerProductos();
        for (Productos p : listaProductos) {
            Object[] fila = {
                p.getIdProducto(),
                p.getNombreProducto(),
                p.getIdCategoria() != null ? p.getIdCategoria().getCategoria() + " - " + p.getIdCategoria().getSubcategoria() : "Sin Categoría",
                p.getPrecioUnitario() 
            };
            modelo.addRow(fila);
        }
        tableProductos.setModel(modelo);
    }
    
    private void cargarClientesEnCombo() {
        List<Clientes> listaClientes = control.traerClientes();
        DefaultComboBoxModel<Clientes> modelo = new DefaultComboBoxModel<>();

        for (Clientes c : listaClientes) {
            modelo.addElement(c); 
        }

        clientesCombo.setModel(modelo);
    }

    
    private void actualizarTotalOrden() {
        float total = 0.0f;
        for (Detalleordenes d : detallesTemp) {
            total += d.getTotalVenta();
        }
        total_orden.setText(String.format("Total: $ %.2f", total));
    }
   

    private void cargarModosEnvio() {
        String[] modosEnvio = {"Estándar", "Express", "Retiro en Sucursal", "Internacional"};
        jComboBox1.setModel(new DefaultComboBoxModel<>(modosEnvio));
    }
    
    // Nueva tabla para los detalles de la orden actual (productos agregados)
    private void inicializarTablaDetallesOrden() {
        String[] columnasDetalles = {"ID Producto", "Producto", "Cantidad", "Precio Unitario", "Total Venta", "Descuento", "Ganancia"};
        DefaultTableModel modeloDetalles = new DefaultTableModel(columnasDetalles, 0);
      //  jTableDetallesOrden.setModel(modeloDetalles); 
    }

    private void actualizarTablaDetallesOrden() {
        String[] columnasDetalles = {"ID Producto", "Producto", "Cantidad", "Precio Unitario", "Total Venta", "Descuento", "Ganancia"};
        DefaultTableModel modeloDetalles = new DefaultTableModel(columnasDetalles, 0);

        for (Detalleordenes detalle : detallesTemp) {
            Object[] fila = {
                detalle.getProductos().getIdProducto(),
                detalle.getProductos().getNombreProducto(),
                detalle.getCantidad(),
                detalle.getProductos().getPrecioUnitario(), // Muestra el precio unitario del producto
                detalle.getTotalVenta(),
                detalle.getDescuento(),
                detalle.getGanancia()
            };
            modeloDetalles.addRow(fila);
        }
       // jTableDetallesOrden.setModel(modeloDetalles);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jPanel1 = new javax.swing.JPanel();
        CPostalTXT = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        CiudadField = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        CiudadTXT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        EstadoTXT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        volverBTN = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProductos = new javax.swing.JTable();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        clientesCombo = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        nombreTXT = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cantidadTXT = new javax.swing.JTextField();
        agregarBTN = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        EliminarBTN = new javax.swing.JButton();
        GuardarBTN = new javax.swing.JButton();
        total_orden = new javax.swing.JLabel();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        CPostalTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CPostalTXTActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        CiudadField.setText("Ciudad");

        jLabel5.setText("C. postal");

        jLabel6.setText("Modo de envio");

        jLabel4.setText("Estado");

        jLabel7.setText("Fecha de orden");

        jLabel1.setText("Detalle de envio");

        jLabel9.setText("ID cliente");

        volverBTN.setBackground(new java.awt.Color(255, 255, 255));
        volverBTN.setForeground(new java.awt.Color(102, 102, 102));
        volverBTN.setText("Atras");
        volverBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBTNActionPerformed(evt);
            }
        });

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

        clientesCombo.setModel(new javax.swing.DefaultComboBoxModel<>());
        clientesCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientesComboActionPerformed(evt);
            }
        });

        jLabel8.setText("Producto seleccionado");

        nombreTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreTXTActionPerformed(evt);
            }
        });

        jLabel2.setText("Cantidad");

        cantidadTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadTXTActionPerformed(evt);
            }
        });

        agregarBTN.setText("Agregar producto a la orden");
        agregarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarBTNActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jLabel3.setText("Total orden: ");

        EliminarBTN.setBackground(new java.awt.Color(255, 204, 204));
        EliminarBTN.setForeground(new java.awt.Color(0, 0, 0));
        EliminarBTN.setText("Eliminar");
        EliminarBTN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        EliminarBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EliminarBTNMouseClicked(evt);
            }
        });

        GuardarBTN.setBackground(new java.awt.Color(153, 255, 153));
        GuardarBTN.setForeground(new java.awt.Color(0, 0, 0));
        GuardarBTN.setText("Guardar orden");
        GuardarBTN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(volverBTN)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(CiudadField))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CiudadTXT)
                                    .addComponent(EstadoTXT)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CPostalTXT))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(clientesCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreTXT)
                    .addComponent(cantidadTXT)
                    .addComponent(agregarBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(EliminarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GuardarBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(total_orden, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 71, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(volverBTN)
                        .addGap(69, 69, 69)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(84, 84, 84)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(EstadoTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CiudadField)
                                    .addComponent(CiudadTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(25, 25, 25)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5)
                                            .addComponent(CPostalTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(clientesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantidadTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(agregarBTN)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(EliminarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(GuardarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(total_orden, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


   
    private void GuardarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarBTNActionPerformed
        // Validar campos de entrada
        if (jDateChooser1.getDate() == null ||
            clientesCombo.getSelectedItem() == null ||
            EstadoTXT.getText().isEmpty() ||
            CiudadTXT.getText().isEmpty() ||
            CPostalTXT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos de la orden y envío son obligatorios, incluida la fecha.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }


        try {

            Date fechaOrden = jDateChooser1.getDate();
            if (fechaOrden == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha válida.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Clientes clienteSeleccionado = (Clientes) clientesCombo.getSelectedItem();
            if (clienteSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente del combo.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }
            long idCliente = clienteSeleccionado.getIdCliente();

            // Datos del Envío
            String estadoEnvio = EstadoTXT.getText();
            String ciudadEnvio = CiudadTXT.getText();
            String modoEnvio = (String) jComboBox1.getSelectedItem();
            int codigoPostalEnvio = Integer.parseInt(CPostalTXT.getText());

            if (detallesTemp.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe agregar al menos un producto a la orden.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean guardado = control.guardarOrdenCompleta(fechaOrden, idCliente, modoEnvio, ciudadEnvio, estadoEnvio, codigoPostalEnvio, detallesTemp);

            if (guardado) {
                JOptionPane.showMessageDialog(this, "Orden guardada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCamposOrden();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar la orden. Verifique ID Cliente, datos de envío o la disponibilidad de productos.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Código Postal deben ser números válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado al guardar la orden: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_GuardarBTNActionPerformed

    private void GuardarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarBTNMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_GuardarBTNMouseClicked

    private void EliminarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarBTNMouseClicked
       
   JOptionPane.showMessageDialog(this, "La función de eliminar órdenes completas no está implementada.", "Información", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_EliminarBTNMouseClicked

    private void cantidadTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadTXTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadTXTActionPerformed

    private void agregarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBTNActionPerformed
     int fila = tableProductos.getSelectedRow();
        if (fila >= 0) {
            try {
                long idProducto = (Long) tableProductos.getValueAt(fila, 0);
                int cantidad = Integer.parseInt(cantidadTXT.getText());

                if (cantidad <= 0) {
                    JOptionPane.showMessageDialog(this, "La cantidad debe ser un número positivo.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Productos producto = control.traerProducto(idProducto);
                if (producto != null) {
                    Detalleordenes detalle = new Detalleordenes();
                    detalle.setProductos(producto);
                    detalle.setCantidad(cantidad);

                    double precioUnitarioProducto = producto.getPrecioUnitario();
                    float totalVenta = (float) (precioUnitarioProducto * cantidad);
                    float descuento = 0.0f;
                    float ganancia = totalVenta * 0.20f;

                    detalle.setTotalVenta(totalVenta);
                    detalle.setDescuento(descuento);
                    detalle.setGanancia(ganancia);

                    boolean productoYaAgregado = false;
                    for (Detalleordenes d : detallesTemp) {
                        if (d.getProductos().getIdProducto() == idProducto) {
                            d.setCantidad(d.getCantidad() + cantidad);
                            d.setTotalVenta(d.getTotalVenta() + totalVenta);
                            d.setDescuento(d.getDescuento() + descuento);
                            d.setGanancia(d.getGanancia() + ganancia);
                            productoYaAgregado = true;
                            break;
                        }
                    }

                    if (!productoYaAgregado) {
                        detallesTemp.add(detalle);
                    }

                    // 🧩 Agregar a jTable1: nombre del producto y cantidad
                    DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                    boolean filaActualizada = false;

                    for (int i = 0; i < modelo.getRowCount(); i++) {
                        String nombreExistente = (String) modelo.getValueAt(i, 0);
                        if (nombreExistente.equals(producto.getNombreProducto())) {
                            int cantidadExistente = (int) modelo.getValueAt(i, 1);
                            modelo.setValueAt(cantidadExistente + cantidad, i, 1);
                            filaActualizada = true;
                            break;
                        }
                    }

                    if (!filaActualizada) {
                        modelo.addRow(new Object[]{producto.getNombreProducto(), cantidad});
                    }

                    actualizarTotalOrden(); // 🧮 Calcula y actualiza total_orden

                    JOptionPane.showMessageDialog(this, "Producto agregado a la orden (temporalmente).");
                    cantidadTXT.setText("");
                    nombreTXT.setText("");
                    productoSeleccionadoId = -1;
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Producto no encontrado en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Cantidad inválida. Por favor, ingrese un número entero.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al agregar producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto de la tabla para agregar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_agregarBTNActionPerformed

    private void tableProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductosMouseClicked
        int filaSeleccionada = tableProductos.getSelectedRow();
        if (filaSeleccionada >= 0) {
            productoSeleccionadoId = (Long) tableProductos.getValueAt(filaSeleccionada, 0);
            String nombre = (String) tableProductos.getValueAt(filaSeleccionada, 1);
            nombreTXT.setText(nombre);
        }  
    }//GEN-LAST:event_tableProductosMouseClicked

    private void nombreTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreTXTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreTXTActionPerformed

    private void CPostalTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CPostalTXTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CPostalTXTActionPerformed

    private void volverBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBTNActionPerformed
        ventanaPrincipal.setVisible(true); // Vuelve a mostrar la principal
        this.dispose(); // Cierra la ventana actual
    }//GEN-LAST:event_volverBTNActionPerformed

    private void clientesComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientesComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clientesComboActionPerformed

  
    // --- Métodos de Utilidad ---
    private void limpiarCamposOrden() {
        EstadoTXT.setText("");
        CiudadTXT.setText("");
        jComboBox1.setSelectedIndex(0); // Reinicia el combobox
        CPostalTXT.setText("");
        cantidadTXT.setText("");
        nombreTXT.setText("");
        detallesTemp.clear(); // Limpia la lista de detalles temporales
        actualizarTablaDetallesOrden(); // Refresca la tabla de detalles para mostrarla vacía
    }
    
    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CPostalTXT;
    private javax.swing.JLabel CiudadField;
    private javax.swing.JTextField CiudadTXT;
    private javax.swing.JButton EliminarBTN;
    private javax.swing.JTextField EstadoTXT;
    private javax.swing.JButton GuardarBTN;
    private javax.swing.JButton agregarBTN;
    private javax.swing.JTextField cantidadTXT;
    private javax.swing.JComboBox clientesCombo;
    private javax.swing.Box.Filler filler1;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nombreTXT;
    private javax.swing.JTable tableProductos;
    private javax.swing.JLabel total_orden;
    private javax.swing.JButton volverBTN;
    // End of variables declaration//GEN-END:variables
}
