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
    
    public int getCantidadBloques(){
        return this.blockChain.size();
    }
    
    public Bloque getBloque(int indice){
        return this.blockChain.get(indice);
    }
    
    public Bloque getUltimoBloque(){
        return this.blockChain.get(this.blockChain.size() - 1);
    }
    
    //Diferente de
    public boolean crearBloqueGenesis(Paciente paciente, String cliente){
        if(this.blockChain.size() < 1){
            Bloque bloqueTemporal = new Bloque(0, "0000000000000000000000000000000000000000000000000000000000000000"); //En teoría son 64 ceros
            
            if(paciente != null){
                bloqueTemporal.setTransaccion("0000GeNeSiS", cliente, paciente);
            }
            
            this.blockChain.add(bloqueTemporal);
            this.minarBloque();
            return true;
        }
        
        return false;
    }
    
    //Diferente de
    public boolean crearBloqueGenesis(){
        if(this.blockChain.size() < 1){
            Bloque bloqueTemporal = new Bloque(0, "0000000000000000000000000000000000000000000000000000000000000000"); //En teoría son 64 ceros
            this.blockChain.add(bloqueTemporal);
            this.minarBloque();
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
    
    //El método getBalance en nuestro proyecto no aplica
    
    /*public double getBalance(String pSender){
        double positiveAmount = 0;
        double negativeAmount = 0;
        
        for(int i = 0; i < this.getCantidadBloques(); i++){
            for(int j = 0; j < this.getBloque(i).cantidadTransacciones(); j++){
                if(this.getBloque(i).getTransaccion(j).getReceptor().equals(pSender)){
                    possitiveAmount += this.getBloque(i).getTransaccion(j).getAumount();                    
                }
                else if(this.getBloque(i).getTransaccion(j).getEmisor().equals(pSender)){
                    negativeAmount += this.getBloque(i).getTransaccion(j).getAmount();
                }
            }
        }
        
        return positiveAmount - negativeAmount;
    }*/
    
    public boolean getPruebaDeTrabajo_overBlock(Bloque bloque){
        String cadena = bloque.toString();
        int nonce = bloque.getNonce();
        String hash = "";
        
        hash = this.generarHash(cadena + Integer.toString(nonce));
        
        if(hash.equals(bloque.getHash())){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean añadirBloqueVerificado(Bloque bloque){
        if(!this.bloqueExistente(bloque)){
            if(this.getPruebaDeTrabajo_overBlock(bloque)){
                this.blockChain.add(bloque);                
                return true;
            }
        }
        
        return false;
    }
    
    public void minarBloque(){
        String cadena = this.blockChain.get(this.blockChain.size() - 1).toString();
        int nonce = 0;
        String hash = "";
        
        while(true){
            hash = this.generarHash(cadena + Integer.toString(nonce));
            
            if(hash.subSequence(0, this.complejidad).equals(this.pruebaDeTrabajo)){
                this.blockChain.get(this.blockChain.size() - 1).registro(nonce, hash);
                break;
            }
            
            nonce++;
        }
    }
    
    private String generarHash(String cadena){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] hash = digest.digest(cadena.getBytes("UTF-8"));
            StringBuffer stringHexadecimal = new StringBuffer();
            
            for(int i = 0; i < hash.length; i++){
                String hexadecimal = Integer.toHexString(0xff & hash[i]);
                
                if(hexadecimal.length() == 1){
                    stringHexadecimal.append('0');                    
                }
                
                stringHexadecimal.append(hexadecimal);
            }
            
            return stringHexadecimal.toString();
        } 
        catch (Exception ex) {
            return null;
        }
    }
    
    public String reporteDeTransaccion(int numeroDeBloque){
        String cadena = "";
        Bloque bloque = this.blockChain.get(numeroDeBloque);
        
        for(int i = 0; i < bloque.cantidadTransacciones(); i++){
            cadena += "\t Transacción #" + Integer.toString(bloque.getTransaccion(i).getID()) + 
                      ": " + bloque.getTransaccion(i).getPaciente().getNombre() + " " + bloque.getTransaccion(i).getPaciente().getPadecimiento() + ".\t(" + 
                      bloque.getTransaccion(i).getEmisor() + " ---> " + 
                      bloque.getTransaccion(i).getReceptor() + ")\n";
        }
        
        return cadena;
    }
    
    @Override
    public String toString(){
        String blockChain = "";
        
        for(Bloque bloque : this.blockChain){
            blockChain += bloque.toString() + "\n";
        }
        
        return blockChain;
    }
}
