/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlockChain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Jimmy
 */
public class Bloque implements Serializable{
    private int ID;
    private int nonce;
    private long marcaDeTiempo;
    private String hash;
    private String hashPrevio;
    private ArrayList<Transaccion> listadoTransacciones;
    
    public Bloque(int ID, String hashPrevio){
        this.ID = ID;
        this.hashPrevio = hashPrevio;
        this.marcaDeTiempo = new Date().getTime();        
        this.listadoTransacciones = new ArrayList<>();
        this.nonce = -1;
        this.hash = null;
    }
    
    public Bloque(){
        this.marcaDeTiempo = new Date().getTime();
        this.listadoTransacciones = new ArrayList<>();
        this.ID = -1;
        this.nonce = -1;
        this.hash = null;        
    }
    
    public boolean registro(int nonce, String hash){
        if((this.ID > -1) && (this.nonce < 0) && (this.hash == null)){
            this.nonce = nonce;
            this.hash = hash;
            
            return true;
        }
        
        return false;
    }
    
    public void setTransaccion(String emisor, String receptor, Paciente paciente){
        this.listadoTransacciones.add(
                new Transaccion(
                        this.listadoTransacciones.size(), 
                        emisor, 
                        receptor, 
                        paciente
                )
        );
    }
    
    public void setTransaccion(Transaccion transaccion){
        this.listadoTransacciones.add(
                new Transaccion(
                        this.listadoTransacciones.size(),
                        transaccion.getEmisor(),
                        transaccion.getReceptor(),
                        transaccion.getPaciente()
                )
        );
    }
    
    public Transaccion getTransaccion(int ID){
        return this.listadoTransacciones.get(ID);
    }
    
    public int cantidadTransacciones(){
        return this.listadoTransacciones.size();
    }
    
    public int getID(){
        return this.ID;
    }
    
    public int getNonce(){
        return this.nonce;       
    }
    
    public long getMarceDeTiempo(){
        return this.marcaDeTiempo;
    }
    
    public String getHash(){
        return this.hash;
    }
    
    public String getHashPrevio(){
        return this.hashPrevio;
    }
    
    @Override
    public String toString(){
        String cadena = Integer.toString(this.ID) + Long.toString(this.marcaDeTiempo) + this.hashPrevio;
        
        for(int i = 0; i < this.listadoTransacciones.size(); i++){
            cadena = cadena + this.listadoTransacciones.get(i).toString();
        }
        
        return cadena;
    }
}
