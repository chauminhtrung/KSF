/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Utils;

import Model.NhanVien;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author Admin
 */
public class Header extends javax.swing.JPanel {

    private EventHeader event;
    int sotb = 0;
  

    /**
     * Creates new form Header
     */
    public Header() {
        initComponents();
        sotb = btnThongbao.getBadges();
 
    }

    public void initMenu(EventHeader event) {
        this.event = event;

    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Hinh = new Utils.Paneltron();
        btnThongbao = new Utils.ButtonTron();
        lblTen = new javax.swing.JLabel();
        lblVaitro = new javax.swing.JLabel();
        panel1 = new Utils.Panel();
        btnHome = new Utils.ButtonTron();
        panel3 = new Utils.Panel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/IconHotel2.png"))); // NOI18N

        Hinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/trung.jpg"))); // NOI18N

        btnThongbao.setBackground(new java.awt.Color(255, 102, 153));
        btnThongbao.setForeground(new java.awt.Color(255, 102, 102));
        btnThongbao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/IconNoti.png"))); // NOI18N
        btnThongbao.setBadges(1);
        btnThongbao.setEffectColor(new java.awt.Color(255, 255, 255));
        btnThongbao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongbaoActionPerformed(evt);
            }
        });

        lblTen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTen.setForeground(new java.awt.Color(255, 255, 255));
        lblTen.setText("Minh Trung");

        lblVaitro.setForeground(new java.awt.Color(204, 204, 204));
        lblVaitro.setText("Admin");

        panel1.setBackground(new java.awt.Color(44, 46, 47));
        panel1.setPreferredSize(new java.awt.Dimension(4, 0));

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnHome.setBackground(new java.awt.Color(102, 102, 255));
        btnHome.setForeground(new java.awt.Color(255, 102, 102));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/home.png"))); // NOI18N
        btnHome.setEffectColor(new java.awt.Color(255, 255, 255));
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        panel3.setBackground(new java.awt.Color(44, 46, 47));
        panel3.setPreferredSize(new java.awt.Dimension(4, 0));

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/IconNamehotel.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 916, Short.MAX_VALUE)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongbao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Hinh, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTen)
                    .addComponent(lblVaitro))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThongbao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lblTen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblVaitro))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Hinh, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        int index = 0;
        event.selected(index);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnThongbaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongbaoActionPerformed
        int index = 1;
        sotb--;
        btnThongbao.setBadges(sotb);
        event.selected(index);
    }//GEN-LAST:event_btnThongbaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public Utils.Paneltron Hinh;
    private Utils.ButtonTron btnHome;
    private Utils.ButtonTron btnThongbao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JLabel lblTen;
    public javax.swing.JLabel lblVaitro;
    private Utils.Panel panel1;
    private Utils.Panel panel3;
    // End of variables declaration//GEN-END:variables
}
