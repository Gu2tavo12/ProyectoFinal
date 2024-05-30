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
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Jimmy
 */
public class frmServidor extends javax.swing.JFrame implements Runnable {
    private Thread tListener;
    private NodeData nodoActual;
    private ArrayList<NodeData> listadoClientes;
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
        
        this.listadoClientes = new ArrayList<>();
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

        lblDirecciónIP = new javax.swing.JLabel();
        lblNumeroSocket = new javax.swing.JLabel();
        lblUbicacion = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnResumen = new javax.swing.JButton();
        Pane1 = new javax.swing.JScrollPane();
        txtMensajes = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblDirecciónIP.setText("Dirección IP:");

        lblNumeroSocket.setText("Número de Socket:");

        lblUbicacion.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblUbicacion.setText("Ubicación del servidor:");

        jLabel1.setText("Acciones y mensajes de BlockChain:");

        btnResumen.setText("Resumen");
        btnResumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResumenActionPerformed(evt);
            }
        });

        txtMensajes.setColumns(20);
        txtMensajes.setRows(5);
        Pane1.setViewportView(txtMensajes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Pane1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUbicacion)
                            .addComponent(jLabel1)
                            .addComponent(btnResumen)
                            .addComponent(lblDirecciónIP)
                            .addComponent(lblNumeroSocket))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUbicacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDirecciónIP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNumeroSocket)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnResumen)
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Pane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
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
    
    
    public void setBlockChainCopia(BlockChain blockChainCopia){
        this.blockChain = blockChainCopia;
    }
    
    public BlockChain getBlockChainCopia(){
        return this.blockChain;
    }
    
    public int getTamañoBlockChain(){
        return this.blockChain.getCantidadBloques();
    }
    
    public void iniciarCuentaDeUsuario(NodeData nodoCliente, Paciente paciente){
        this.blockChain.crearBloque();
        this.blockChain.getUltimoBloque().setTransaccion("000BLOQUE000INICIAL", nodoCliente.getNombreDelNodo(), paciente);
        this.blockChain.minarBloque();
    }
    
    public void registrarClientes(ArrayList<NodeData> listadoClientes){
        this.listadoClientes = listadoClientes;
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
                    
                    Bloque bloqueTemporal = new Bloque();
                    bloqueTemporal.setTransaccion(emisor, receptor, paciente);
                    
                    this.blockChain.crearBloque();
                    this.blockChain.getUltimoBloque().setTransaccion(bloqueTemporal.getTransaccion(0));
                    this.blockChain.minarBloque();
                    this.bloqueBroadcast(this.blockChain.getUltimoBloque());
                    this.reportarNuevoPaciente(receptor, paciente);
                }
                else{
                    this.blockChain.añadirBloqueVerificado(bloque);
                }
            } 
            catch (Exception ex) {
                
            }
        }            
    }
    
    public void reportarNuevoPaciente(String receptor, Paciente paciente){
        for(int i = 0; i < this.listadoClientes.size(); i++){
            if(this.listadoClientes.get(i).getNombreDelNodo().equals(receptor)){
                try {
                    Socket socket = new Socket(
                            this.listadoClientes.get(i).getDireccionIP(),
                            this.listadoClientes.get(i).getNumeroDeSocket()
                    );
                    
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(paciente);
                    socket.close();
                } 
                catch (Exception ex) {
                    
                }
            }
        }
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane Pane1;
    private javax.swing.JButton btnResumen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDirecciónIP;
    private javax.swing.JLabel lblNumeroSocket;
    private javax.swing.JLabel lblUbicacion;
    private javax.swing.JTextArea txtMensajes;
    // End of variables declaration//GEN-END:variables
}
