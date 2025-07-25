
package com.mycompany.proyectoprogra.igu;

import jakarta.persistence.EntityManager;


public class Principal extends javax.swing.JFrame {

   
    public Principal() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        clientesBTN = new javax.swing.JButton();
        ordenesBTN = new javax.swing.JButton();
        productosBTN = new javax.swing.JButton();
        detalleBTN = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        clientesBTN.setBackground(new java.awt.Color(66, 66, 66));
        clientesBTN.setForeground(new java.awt.Color(255, 255, 255));
        clientesBTN.setText("CLIENTES");
        clientesBTN.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        clientesBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientesBTNActionPerformed(evt);
            }
        });
        jPanel1.add(clientesBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 280, 100));

        ordenesBTN.setBackground(new java.awt.Color(66, 66, 66));
        ordenesBTN.setForeground(new java.awt.Color(255, 255, 255));
        ordenesBTN.setText("ORDENES");
        ordenesBTN.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ordenesBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordenesBTNActionPerformed(evt);
            }
        });
        jPanel1.add(ordenesBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, 280, 100));

        productosBTN.setBackground(new java.awt.Color(66, 66, 66));
        productosBTN.setForeground(new java.awt.Color(255, 255, 255));
        productosBTN.setText("PRODUCTOS");
        productosBTN.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        productosBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productosBTNActionPerformed(evt);
            }
        });
        jPanel1.add(productosBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 280, 100));

        detalleBTN.setBackground(new java.awt.Color(66, 66, 66));
        detalleBTN.setForeground(new java.awt.Color(255, 255, 255));
        detalleBTN.setText("DETALLE ORDENES");
        detalleBTN.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        detalleBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detalleBTNActionPerformed(evt);
            }
        });
        jPanel1.add(detalleBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, 280, 100));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 80)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText(" Sistema de gestión");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 800, 150));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ChatGPT Image 25 jun 2025, 03_13_09.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, 870));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clientesBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientesBTNActionPerformed

        ventanaCliente ventana = new ventanaCliente(this);
        this.setVisible(false);

        new Thread(() -> {
            try {
                
                Thread.sleep(300);
                ventana.cargarTabla();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
    }//GEN-LAST:event_clientesBTNActionPerformed

    private void ordenesBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordenesBTNActionPerformed
        
        ventanaOrdenes ventanaOrdenes = new ventanaOrdenes(this);
        this.setVisible(false);
        new Thread(() -> {
            try {
                Thread.sleep(300);
                ventanaOrdenes.cargarTabla();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        
        ventanaOrdenes.setVisible(true);
        ventanaOrdenes.setLocationRelativeTo(null);
        
    }//GEN-LAST:event_ordenesBTNActionPerformed

    private void productosBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productosBTNActionPerformed
        ventanaProductos ventanaProductos = new ventanaProductos(this);
        this.setVisible(false);
        new Thread(() -> {
            try {
                Thread.sleep(300);
                ventanaProductos.cargarTabla();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        ventanaProductos.setVisible(true);
        ventanaProductos.setLocationRelativeTo(null);
        
    }//GEN-LAST:event_productosBTNActionPerformed

    private void detalleBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detalleBTNActionPerformed
        ventanaDetalleOrdenes ventanaDetalle = new ventanaDetalleOrdenes(this);
        this.setVisible(false);

        new Thread(() -> {
            try {
                Thread.sleep(300);
                ventanaDetalle.cargarTabla();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        ventanaDetalle.setVisible(true);
        ventanaDetalle.setLocationRelativeTo(null);
    }//GEN-LAST:event_detalleBTNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clientesBTN;
    private javax.swing.JButton detalleBTN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton ordenesBTN;
    private javax.swing.JButton productosBTN;
    // End of variables declaration//GEN-END:variables
}
