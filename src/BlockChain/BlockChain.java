/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlockChain;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jimmy
 */
public class BlockChain {
    private ArrayList<Bloque> blockChain;
    private int complejidad;
    private String pruebaDeTrabajo;
    
    public BlockChain(int complejidad, String caracterDePrueba){
        this.blockChain = new ArrayList<>();
        this.complejidad = complejidad;
        this.pruebaDeTrabajo = "";
        
        for(int i = 0; i < this.complejidad; i++){
            this.pruebaDeTrabajo += caracterDePrueba;
        }
    }
    
    public List<Bloque> getBlockChain(){
        return this.blockChain;
    }
    
    public boolean bloqueExistente(Bloque bloque){
        for(int i = 0; i < this.blockChain.size(); i++){
            if(this.blockChain.get(i).getID() == bloque.getID()){
                return true;
            }
        }
        
        return false;
    }
    
    public Bloque getBloque(int indice){
        return this.blockChain.get(indice);
    }
    
    public Bloque getUltimoBloque(){
        return this.blockChain.get(this.blockChain.size() - 1);
    }
    
    public boolean crearBloqueGenesis(Paciente paciente, String cliente){
        if(this.blockChain.size() < 1){
            Bloque bloqueTemporal = new Bloque(0, "0000000000000000000000000000000000000000000000000000000000000000"); //En teoría son 64 ceros
            
            if(paciente != null){
                bloqueTemporal.setTransaccion("0000GeNeSiS", cliente, paciente);
            }
            this.blockChain.add(bloqueTemporal);
            //this.minarBloque();
            return true;
        }
        
        return false;
    }
    
    public boolean crearBloqueGenesis(){
        if(this.blockChain.size() < 1){
            Bloque bloqueTemporal = new Bloque(0, "0000000000000000000000000000000000000000000000000000000000000000"); //En teoría son 64 ceros
            this.blockChain.add(bloqueTemporal);
            //this.minarBloque();
            return true;
        }
        
        return false;
    }
    
    public void crearBloque(){
        String hashPrevio = this.blockChain.get(this.blockChain.size() - 1).getHash();
        this.blockChain.add(
                new Bloque(
                        this.blockChain.size(), hashPrevio
                )        
        );
    }
    
    //El siguiente método de crear es getBalance
}
