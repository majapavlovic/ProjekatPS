/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form;

/**
 *
 * @author Milos
 */
public class FrmMain extends javax.swing.JFrame {

    /**
     * Creates new form FrmMain
     */
    public FrmMain() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jmenuMain = new javax.swing.JMenuBar();
        jmenuProduct = new javax.swing.JMenu();
        jmiProductNew = new javax.swing.JMenuItem();
        jmiProductView = new javax.swing.JMenuItem();
        jmiNewProductList = new javax.swing.JMenuItem();
        jmenuAbout = new javax.swing.JMenu();
        jmiProductAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main Form");

        jmenuProduct.setText("Product");
        jmenuProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmenuProductActionPerformed(evt);
            }
        });

        jmiProductNew.setText("New");
        jmiProductNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiProductNewActionPerformed(evt);
            }
        });
        jmenuProduct.add(jmiProductNew);

        jmiProductView.setText("View");
        jmiProductView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiProductViewActionPerformed(evt);
            }
        });
        jmenuProduct.add(jmiProductView);

        jmiNewProductList.setText("New product list");
        jmiNewProductList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNewProductListActionPerformed(evt);
            }
        });
        jmenuProduct.add(jmiNewProductList);

        jmenuMain.add(jmenuProduct);

        jmenuAbout.setText("About");

        jmiProductAbout.setText("Software");
        jmenuAbout.add(jmiProductAbout);

        jmenuMain.add(jmenuAbout);

        setJMenuBar(jmenuMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiProductNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiProductNewActionPerformed
        //new FrmNewProduct(this, false).setVisible(true);
        new FrmNewProduct(this, true).setVisible(true);
    }//GEN-LAST:event_jmiProductNewActionPerformed

    private void jmenuProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmenuProductActionPerformed

    }//GEN-LAST:event_jmenuProductActionPerformed

    private void jmiProductViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiProductViewActionPerformed
        new FrmViewProduct(this, true).setVisible(true);
    }//GEN-LAST:event_jmiProductViewActionPerformed

    private void jmiNewProductListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNewProductListActionPerformed
        new FrmNewProductsTM(this, true).setVisible(true);
    }//GEN-LAST:event_jmiNewProductListActionPerformed

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jmenuAbout;
    private javax.swing.JMenuBar jmenuMain;
    private javax.swing.JMenu jmenuProduct;
    private javax.swing.JMenuItem jmiNewProductList;
    private javax.swing.JMenuItem jmiProductAbout;
    private javax.swing.JMenuItem jmiProductNew;
    private javax.swing.JMenuItem jmiProductView;
    // End of variables declaration//GEN-END:variables
}
