package dam2.add.p12.lib;

import java.util.Comparator;

import dam2.add.p12.dao.Usuario;
 
/**
 *
 * @author david     prueba funcionamiento copia
 */

public class Comparador implements Comparator<Usuario>{
     
     @Override
     public int compare(Usuario e1, Usuario e2){
    	
    	int aciertos1 = Integer.parseInt(e1.getAciertos());
    	
    	int aciertos2 = Integer.parseInt(e2.getAciertos());
    	 
        if(aciertos1 > aciertos2 ){
            return -1;
        }else if(aciertos1 == aciertos2){
            return 0;
        }else{
            return 1;
        }
    }
     
     
}