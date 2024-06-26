/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;
import BlockChain.BlockChain;
import BlockChain.Bloque;
import BlockChain.Cifrado;
import BlockChain.NodeData;
import BlockChain.Paciente;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Jimmy
 */
public class frmServidor extends javax.swing.JFrame implements Runnable {
    private Thread tListener;
    private NodeData nodoActual;
    private ServerSocket serverSocket;
    private BlockChain blockChain;
    private Cifrado cifrado;
    
    /**
     * Creates new form frmServidor
     */
    public frmServidor() {
        initComponents();
    }
    
    public frmServidor(NodeData nodoActual){
        initComponents();
        
        this.nodoActual = nodoActual;        
        this.cifrado = new Cifrado("¡¡Soltala Erika soltala!!");        
        
        this.lblUbicacion.setText("Ubicación del servidor: " + this.nodoActual.getNombreDelNodo());
        this.lblDirecciónIP.setText("Dirección IP: " + this.nodoActual.getDireccionIP());
        this.lblNumeroSocket.setText("Número de puerto: " + Integer.toString(this.nodoActual.getNumeroDeSocket()));
                
        this.txtMensajes.setEditable(false);
        
        this.iniciarServidor();
        this.setResizable(false);        
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Pane1 = new javax.swing.JScrollPane();
        txtMensajes = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        btnResumen = new javax.swing.JButton();
        lblNumeroSocket = new javax.swing.JLabel();
        lblDirecciónIP = new javax.swing.JLabel();
        lblUbicacion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        txtMensajes.setBackground(new java.awt.Color(255, 255, 255));
        txtMensajes.setColumns(20);
        txtMensajes.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtMensajes.setForeground(new java.awt.Color(0, 0, 0));
        txtMensajes.setRows(5);
        txtMensajes.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        Pane1.setViewportView(txtMensajes);

        jLabel1.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Acciones y mensajes de BlockChain:");

        btnResumen.setBackground(new java.awt.Color(204, 0, 0));
        btnResumen.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        btnResumen.setForeground(new java.awt.Color(255, 255, 255));
        btnResumen.setText("Resumen");
        btnResumen.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnResumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResumenActionPerformed(evt);
            }
        });

        lblNumeroSocket.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        lblNumeroSocket.setForeground(new java.awt.Color(0, 0, 0));
        lblNumeroSocket.setText("Número de Socket:");

        lblDirecciónIP.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        lblDirecciónIP.setForeground(new java.awt.Color(0, 0, 0));
        lblDirecciónIP.setText("Dirección IP:");

        lblUbicacion.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        lblUbicacion.setForeground(new java.awt.Color(0, 0, 0));
        lblUbicacion.setText("Ubicación del servidor:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(lblNumeroSocket))
                        .addGap(391, 403, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Pane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblUbicacion)
                                    .addComponent(lblDirecciónIP))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUbicacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDirecciónIP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNumeroSocket)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Pane1, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnResumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResumenActionPerformed
        // TODO add your handling code here:
        String cadena = "";
        
        for(int i = 0; i < this.blockChain.getCantidadBloques(); i++){
            cadena += "Bloque: " + this.blockChain.getBloque(i).getID() + ". \n" +
                      this.blockChain.reporteDeTransaccion(this.blockChain.getBloque(i).getID()) + 
                      "--------------------------------------\n";
        }
        
        this.txtMensajes.setText(cadena);
    }//GEN-LAST:event_btnResumenActionPerformed

    private void iniciarServidor(){
        this.blockChain = new BlockChain(3, "0");        
        this.blockChain.crearBloqueGenesis();
        
        try {
            InetAddress direccionIP = InetAddress.getByName(this.nodoActual.getDireccionIP());
            InetSocketAddress redServidor = new InetSocketAddress(direccionIP, this.nodoActual.getNumeroDeSocket());
            this.serverSocket = new ServerSocket();
            this.serverSocket.bind(redServidor);
            this.tListener = new Thread(this);
            this.tListener.start();
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean bloqueBroadcast(Bloque bloque){
        try {            
            Socket socket = new Socket(
                    "127.0.0.1",
                    7000
            );
                
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(bloque);
            socket.close();
            
            return true;
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmServidor().setVisible(true);
            }
        });
    }

    @Override
    public void run() {
        while(true){
            try {
                Socket socket = this.serverSocket.accept();
                
                InputStream inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Bloque bloque = (Bloque) objectInputStream.readObject();
                socket.close();
                
                if(bloque.getID() < 0){
                    String emisor = this.cifrado.desencriptar(bloque.getTransaccion(0).getEmisor());
                    String receptor = this.cifrado.desencriptar(bloque.getTransaccion(0).getReceptor());
                    Paciente paciente = bloque.getTransaccion(0).getPaciente();
                    
                    Paciente pacienteTemporal = new Paciente(
                            this.cifrado.desencriptar(paciente.getNombre()),
                            paciente.getEdad(),
                            paciente.getPeso(),
                            paciente.getFechaNacimiento(),
                            this.cifrado.desencriptar(paciente.getPadecimiento())
                    );
                    
                    Bloque bloqueTemporal = new Bloque();
                    bloqueTemporal.setTransaccion(emisor, receptor, pacienteTemporal);
                    
                    this.blockChain.crearBloque();
                    this.blockChain.getUltimoBloque().setTransaccion(bloqueTemporal.getTransaccion(0));
                    this.blockChain.minarBloque();
                    this.bloqueBroadcast(this.blockChain.getUltimoBloque());
                }
                else{
                    this.blockChain.añadirBloqueVerificado(bloque);
                }
            } 
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
            }
        }            
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane Pane1;
    private javax.swing.JButton btnResumen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDirecciónIP;
    private javax.swing.JLabel lblNumeroSocket;
    private javax.swing.JLabel lblUbicacion;
    private javax.swing.JTextArea txtMensajes;
    // End of variables declaration//GEN-END:variables
}
