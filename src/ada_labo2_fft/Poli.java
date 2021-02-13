/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada_labo2_fft;

import ada_labo2_fft.Complex;
import java.util.ArrayList;
import ada_labo2_fft.FFT;
/**
 *
 * @author sidmox
 */
public class Poli {

    public Poli() {
    }
    
    public ArrayList<Integer> PoliMaker(String l){
        ArrayList<Integer> PoliNew=new ArrayList();
        String[] Lista=l.split(",");
        int n=Lista.length;
        for(int i=0 ; i<n ;i++){
            int num=Integer.parseInt(Lista[i]);
            PoliNew.add(num);
        }
        return PoliNew;
    }
    
    public Complex[] PoliComplexMaker(String l){
        String[] Lista=l.split(",");
        int n=Lista.length;
        Complex[] PoliNew=new Complex[n];
        for(int i=0 ; i<n ;i++){
            int num=Integer.parseInt(Lista[i]);
            PoliNew[i]=new Complex(num,0);
        }
        return PoliNew;
    }
    
    public Complex[] PoliComplexFFT(Complex[]Poli1){
        int n=Poli1.length;
        int m=n-1;
        Complex[] PoliU=new Complex[2*n];
        for(int i=0;i<2*n;i++){
            if(i<n){
                PoliU[i]=Poli1[m];
                m--;
            }
            else
                PoliU[i]=new Complex(0,0);
        }
        return PoliU;
    }
    
    public Complex[] PoliComplexUnion(Complex[]Poli1,Complex[]Poli2){
        int n=Poli1.length;
        int m=Poli2.length;
        Complex[] PoliU=new Complex[n+m];
        for(int i=0;i<n;i++){
            PoliU[i]=Poli1[i];
        }
        for(int i=0;i<m;i++){
            PoliU[i+n]=Poli1[i];
        }
        return PoliU;
    }
    
    public Complex[] PoliComplexVacio(int n){
        Complex[] PoliU=new Complex[n];
        for(int i=0;i<n;i++){
            PoliU[i]=new Complex(0,0);
        }
        return PoliU;
    }
    public Complex[] PoliComplexInvert(Complex[] Poli){
        int n=Poli.length;
        int k=0;
        Complex[] PoliU=new Complex[n];
        for(int i=n-1;i>=0;i--){
            PoliU[i]=Poli[k];
            k++;
        }
        return PoliU;
    }
    public Complex[] MultiPoli(Complex[] A, Complex[] B){
        FFT f=new FFT();
        Complex[] y1=f.fft(A);
        Complex[] y2=f.fft(B);
        Complex[] Resul=new Complex[y1.length];
        for(int i=0;i<y1.length;i++){
            Resul[i]= y1[i].times(y2[i]);
        }
        Complex[] fin=f.ifft(Resul);
        
        return PoliComplexInvert(fin);
    }
    public String mostrar(Complex[]Poli){
        String l="";
        int n=Poli.length;
        int k=n-1;
        for(int i=0;i<n;i++){
            if(i==n-1)
                l=l+" + "+Poli[i].toString();
            else{
                if(k==n-1){
                    l=l+Poli[i].toString()+"*X^"+k;
                    k--;        
                }else{
                    l=l+" + "+Poli[i].toString()+"*X^"+k;
                    k--;
                }
            }
        }
        return l;
    }
    public String mostrarComa(Complex[] arr){
        String n="Valores \n";
        for(int i=0; i<arr.length ;i++){
            if(i==arr.length-1){
                n+=arr[i].re()+" \n";
            }else{
                if(i%10!=0 || i==0){
                    n+=arr[i].re()+", ";                
                }else{
                    n+=arr[i].re()+", \n";
                }
            }
        }
        return n;
    }
    
    public String mostrar(ArrayList<Integer>Poli){
        String l="";
        int n=Poli.size();
        int k=n-1;
        for(int i=0;i<n;i++){
            if(i==n-1)
                l=l+" + "+Poli.get(i).toString();
            else{
                if(k==n-1){
                    l=l+Poli.get(i).toString()+"*X^"+k;
                    k--;        
                }else{
                    l=l+" + "+Poli.get(i).toString()+"*X^"+k;
                    k--;
                }
            }
        }
        return l;
    }
}
