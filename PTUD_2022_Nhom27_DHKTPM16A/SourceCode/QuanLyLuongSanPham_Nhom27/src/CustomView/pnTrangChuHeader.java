/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package CustomView;

/**
 *
 * @author December
 */
public class pnTrangChuHeader extends javax.swing.JPanel {

    /**
     * Creates new form pnTrangChuHeader
     */
    public pnTrangChuHeader() {
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

        panelBorder1 = new CustomView.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelBorder1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TrangChu/human.png"))); // NOI18N
        panelBorder1.add(jLabel1);
        jLabel1.setBounds(10, 40, 290, 50);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        jLabel2.setText("100 thành viên");
        panelBorder1.add(jLabel2);
        jLabel2.setBounds(50, 130, 240, 50);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        jLabel3.setText("Nhân viên:");
        panelBorder1.add(jLabel3);
        jLabel3.setBounds(50, 80, 240, 50);

        add(panelBorder1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 289, 198));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private CustomView.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}