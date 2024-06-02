/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlockChain;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Jimmy
 */
public class Transaccion implements Serializable{
    private int ID;
    private long marcaDeTiempo;
    private String emisor;
    private String receptor;
    private Paciente paciente;
    
    public Transaccion(int ID, String emisor, String receptor, Paciente paciente){
        this.ID = ID;
        this.marcaDeTiempo = new Date().getTime();
        this.emisor = emisor;
        this.receptor = receptor;
        this.paciente = paciente;
    }
    
    @Override
    public String toString(){
        return Integer.toString(this.ID) + Long.toString(this.marcaDeTiempo) + this.emisor + this.receptor + this.paciente.getNombre() + Integer.toString(this.paciente.getEdad()) + Double.toString(this.paciente.getPeso()) + this.paciente.getFechaNacimiento() + this.paciente.getPadecimiento();
    }
    
    public int getID(){
        return this.ID;
    }
    
    public long getMarcaDeTiempo(){
        return this.marcaDeTiempo;
    }
    
    public String getEmisor(){
        return this.emisor;
    }
    
    public String getReceptor(){
        return this.receptor;
    }
    
    public Paciente getPaciente(){
        return this.paciente;
    }
}
