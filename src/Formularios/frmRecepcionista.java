/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;
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
public class frmRecepcionista extends javax.swing.JFrame implements Runnable{
    private NodeData nodeData;
    private ArrayList<NodeData> listadoServidores;
    private Paciente pacienteActual;
    private ServerSocket socketCliente;
    private Thread tListener;
    private Cifrado cifrado;
    
    /**
     * Creates new form frmRecepcionista
     */
    public frmRecepcionista() {
        initComponents();
        
        this.cifrado = new Cifrado("¡¡Soltala Erika soltala!!");
        
        this.setLocationRelativeTo(null);
        this.setResizable(false);        
    }
    
    public void configurar(NodeData nodoCliente, ArrayList<NodeData> listadoNodos){
        this.nodeData = nodoCliente;
        this.lblDireccionIPYSocket.setText("IP: " + this.nodeData.getDireccionIP() + ". Socket: " + this.nodeData.getNumeroDeSocket());
        this.lblUsuario.setText(this.nodeData.getNombreDelNodo());
        this.regitrarServidores(listadoNodos);
        this.iniciarCliente();        
    }
    
    public void regitrarServidores(ArrayList<NodeData> listadoServidores){
        this.listadoServidores = listadoServidores;
        this.cbServidor.removeAllItems();
        
        for(int i = 0; i < this.listadoServidores.size(); i++){
            this.cbServidor.addItem(this.listadoServidores.get(i).getNombreDelNodo());
        }
    } 
    
    public boolean enviarTransaccion(){
        String nombreDelNodo = this.nodeData.getNombreDelNodo();
        String receptor = this.txtDoctor.getText().trim().toUpperCase();
        Paciente paciente = new Paciente(
                this.txtNombrePaciente.getText(),
                Integer.parseInt(this.txtEdad.getText()),
                Double.parseDouble(this.txtPeso.getText()),
                this.txtFechaDeNacimiento.getText(),
                this.txtPadecimiento.getText()
        );
        
        int numeroDeServidor = this.cbServidor.getSelectedIndex();
        
        try {
            nombreDelNodo = this.cifrado.encriptar(nombreDelNodo);
            receptor = this.cifrado.encriptar(receptor);
            
            Bloque bloque = new Bloque();
            bloque.setTransaccion(nombreDelNodo, receptor, paciente);
            
            Socket socket = new Socket(
                    this.listadoServidores.get(numeroDeServidor).getDireccionIP(),
                    this.listadoServidores.get(numeroDeServidor).getNumeroDeSocket()
            );
            
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    socket.getOutputStream()
            );
            
            objectOutputStream.writeObject(bloque);
            socket.close();
            
            this.limpiarTextFields();
            return true;
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        txtNombrePaciente = new javax.swing.JTextField();
        txtPadecimiento = new javax.swing.JTextField();
        txtFechaDeNacimiento = new javax.swing.JFormattedTextField();
        txtPeso = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtDoctor = new javax.swing.JTextField();
        lblUsuario = new javax.swing.JLabel();
        lblDireccionIPYSocket = new javax.swing.JLabel();
        cbServidor = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nombre:");

        jLabel2.setText("Edad:");

        jLabel3.setText("Peso:");

        jLabel4.setText("Fecha de nacimiento:");

        jLabel5.setText("Padecimiento:");

        txtFechaDeNacimiento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        jLabel6.setText("Doctor asignado:");

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblUsuario.setText("Usuario:");

        lblDireccionIPYSocket.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblDireccionIPYSocket.setText("Dirección IP / Socket:");

        jLabel7.setText("Seleccionar Servidor:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsuario)
                    .addComponent(lblDireccionIPYSocket)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombrePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addGap(12, 12, 12)
                                    .addComponent(txtDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtFechaDeNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPadecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(cbServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDireccionIPYSocket)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtFechaDeNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtPadecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtNombrePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        // TODO add your handling code here:
        this.enviarTransaccion();
        this.limpiarTextFields();
    }//GEN-LAST:event_btnEnviarActionPerformed

    public void limpiarTextFields(){
        this.txtNombrePaciente.setText("");
        this.txtFechaDeNacimiento.setText("");
        this.txtEdad.setText("");
        this.txtPadecimiento.setText("");
        this.txtPeso.setText("");
        this.txtDoctor.setText("");
        this.cbServidor.setSelectedItem(-1);
    }
    
    private void iniciarCliente(){
        try {
            InetAddress inetAddress = InetAddress.getByName(this.nodeData.getDireccionIP());
            InetSocketAddress redSocket = new InetSocketAddress(inetAddress, this.nodeData.getNumeroDeSocket());
            this.socketCliente = new ServerSocket(this.nodeData.getNumeroDeSocket(), 50, inetAddress);
            this.tListener = new Thread(this);
            this.tListener.start();
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
        }
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
            java.util.logging.Logger.getLogger(frmRecepcionista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmRecepcionista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmRecepcionista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmRecepcionista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmRecepcionista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JComboBox<String> cbServidor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblDireccionIPYSocket;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField txtDoctor;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JFormattedTextField txtFechaDeNacimiento;
    private javax.swing.JTextField txtNombrePaciente;
    private javax.swing.JTextField txtPadecimiento;
    private javax.swing.JTextField txtPeso;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        while(true){
            try {
                Socket socket = this.socketCliente.accept();
            
                InputStream inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                this.pacienteActual = (Paciente) objectInputStream.readObject();
                
                socket.close();
            } 
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
