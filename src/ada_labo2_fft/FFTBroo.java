/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada_labo2_fft;

import ada_labo2_fft.Complex;
import java.util.ArrayList;
/**
 *
 * @author sidmox
 */
public class FFTBroo {
    
    public FFTBroo() {
    }
    
    public Complex wMaker(double Angulo){
        Complex w=new Complex(Math.cos(Angulo), Math.sin(Angulo));
        return w;        
    }
    
}
