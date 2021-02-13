/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada_labo2_fft;

/**
 *
 * @author sidmox
 */
import ada_labo2_fft.Complex;
public class FFT {

    public FFT() {
    }
        
    public Complex[] fft(Complex[] x) {
        int n = x.length;

        if (n == 1) return new Complex[] { x[0] };

        if (n % 2 != 0) {
            throw new IllegalArgumentException("n is not a power of 2");
        }
        
        Complex[] even = new Complex[n/2];
        for (int k = 0; k < n/2; k++) {
            even[k] = x[2*k];
        }
        Complex[] evenFFT = fft(even);

        Complex[] odd  = even;
        for (int k = 0; k < n/2; k++) {
            odd[k] = x[2*k + 1];
        }
        Complex[] oddFFT = fft(odd);

        Complex[] y = new Complex[n];
        for (int k = 0; k < n/2; k++) {
            double kth = -2 * k * Math.PI / n;
            Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
            y[k]       = evenFFT[k].plus (wk.times(oddFFT[k]));
            y[k + n/2] = evenFFT[k].minus(wk.times(oddFFT[k]));
        }
        return y;
    }


    public Complex[] ifft(Complex[] x) {
        int n = x.length;
        Complex[] y = new Complex[n];

        for (int i = 0; i < n; i++) {
            y[i] = x[i].conjugate();
        }

        y = fft(y);

        for (int i = 0; i < n; i++) {
            y[i] = y[i].conjugate();
        }

        for (int i = 0; i < n; i++) {
            y[i] = y[i].scale(1.0 / n);
        }

        return y;
    }

    public Complex[] cconvolve(Complex[] x, Complex[] y) {

        if (x.length != y.length) {
            throw new IllegalArgumentException("Dimensions don't agree");
        }

        int n = x.length;

        Complex[] a = fft(x);
        Complex[] b = fft(y);

        Complex[] c = new Complex[n];
        for (int i = 0; i < n; i++) {
            c[i] = a[i].times(b[i]);
        }

        return ifft(c);
    }


    public Complex[] convolve(Complex[] x, Complex[] y) {
        Complex ZERO = new Complex(0, 0);

        Complex[] a = new Complex[2*x.length];
        for (int i = 0;        i <   x.length; i++) a[i] = x[i];
        for (int i = x.length; i < 2*x.length; i++) a[i] = ZERO;

        Complex[] b = new Complex[2*y.length];
        for (int i = 0;        i <   y.length; i++) b[i] = y[i];
        for (int i = y.length; i < 2*y.length; i++) b[i] = ZERO;

        return cconvolve(a, b);
    }

    public Complex[] dft(Complex[] x) {
        int n = x.length;
        Complex ZERO = new Complex(0, 0);
        Complex[] y = new Complex[n];
        for (int k = 0; k < n; k++) {
            y[k] = ZERO;
            for (int j = 0; j < n; j++) {
                int power = (k * j) % n;
                double kth = -2 * power *  Math.PI / n;
                Complex wkj = new Complex(Math.cos(kth), Math.sin(kth));
                y[k] = y[k].plus(x[j].times(wkj));
            }
        }
        return y;
    }

            
    public void Imprimir(Complex[] x, String title) {
        System.out.println(title);
        System.out.println("--------------");
        for (int i = 0; i < x.length; i++) {
            System.out.println(x[i]);
        }
        System.out.println();
    }
}
