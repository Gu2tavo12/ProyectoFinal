/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlockChain;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

/**
 *
 * @author Jimmy
 */
public class Cifrado {
    private SecretKeySpec llave;
    private Cipher cifrado;    
    private Cipher descifrado; 
    
    public Cifrado(String llave)
    {
        try{
            MessageDigest hash = MessageDigest.getInstance("SHA-1");
            byte[] aBytes = hash.digest(llave.getBytes("UTF-8"));
            byte[] aBytes32 = Arrays.copyOf(aBytes, 32);
            this.llave = new SecretKeySpec(aBytes32, "AES");
            
            this.cifrado = Cipher.getInstance("AES/ECB/PKCS5Padding");
            this.cifrado.init(Cipher.ENCRYPT_MODE, this.llave);
            
            this.descifrado = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            this.descifrado.init(Cipher.DECRYPT_MODE, this.llave);
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
        }
    }
       
    public String encriptar(String cadena) throws Exception {
        byte[] aBytes = cadena.getBytes("UTF-8");
        byte[] aBytesEnc = this.cifrado.doFinal(aBytes);
        
        return Base64.getEncoder().encodeToString(aBytesEnc);
    }

    public String desencriptar(String cadena) throws Exception {
        byte[] aBytes = Base64.getDecoder().decode(cadena);
        byte[] aBytesDec = this.descifrado.doFinal(aBytes);
        String datos = new String(aBytesDec);
        
        return datos;
    }

    public Cipher getCifrado() {
        return cifrado;
    }
    
    public Cipher getDescifrado() {
        return descifrado;
    }  
}
