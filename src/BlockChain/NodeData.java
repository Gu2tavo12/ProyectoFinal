/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlockChain;
import java.io.Serializable;

/**
 *
 * @author Jimmy
 */
public class NodeData implements Serializable{
    private static final long serialVersionUID = 1L;
    String nombreDelNodo;
    String direccionIP;
    int numeroDeSocket;
    
    public NodeData(){
        
    }
    
    public NodeData(String nombreDelNodo, String direccionIP, int numeroDeSocket){
        this.nombreDelNodo = nombreDelNodo;
        this.direccionIP = direccionIP;
        this.numeroDeSocket = numeroDeSocket;
    }
    
    public String getNombreDelNodo(){
        return this.nombreDelNodo;
    }
    
    public String getDireccionIP(){
        return this.direccionIP;
    }
    
    public int getNumeroDeSocket(){
        return this.numeroDeSocket;
    }
}
