/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlockChain;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Jimmy
 */
public class Paciente implements Serializable{
    private static final long serialVersionUID = 1L;
    private String nombre;
    private int edad;
    private double peso;
    private Date fechaNacimiento;
    private String padecimiento;
    private SimpleDateFormat formatoFecha;
    
    public Paciente(String nombre, int edad, double peso, String fechaNacimiento, String padecimiento){
        try {
            this.formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            
            this.nombre = nombre;
            this.edad = edad;
            this.peso = peso;
            this.fechaNacimiento = this.formatoFecha.parse(fechaNacimiento);
            this.padecimiento = padecimiento;
        } 
        catch (Exception ex) {
            JOptionPane.showConfirmDialog(null, ex.getMessage(), "¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public int getEdad(){
        return this.edad;
    }
    
    public double getPeso(){
        return this.peso;
    }
    
    public String getFechaNacimiento(){
        this.formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        
        return this.formatoFecha.format(this.fechaNacimiento);
    }
    
    public String getPadecimiento(){
        return this.padecimiento;                
    }
}
