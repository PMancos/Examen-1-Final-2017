
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author loren
 */
public class reconocimiento implements Serializable, Comparable<reconocimiento> {
    private String dni;
    private String nombre;
    private int edad;
    private GregorianCalendar fechaRevision;
    private double colesterol;
    private double glucosa;
    private double presSis;
    private double presDias;

    public reconocimiento(String dni, String nombre, int edad, double colesterol, double glucosa, double presSis, double presDias) {
        this.dni = dni;
        this.nombre = nombre;
        this.edad = edad;
        this.colesterol = colesterol;
        this.glucosa = glucosa;
        this.presSis = presSis;
        this.presDias = presDias;
        fechaRevision=new GregorianCalendar();
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if(dni.length()==9)
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(!nombre.isEmpty())
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if(edad>=0)
        this.edad = edad;
    }

    public double getColesterol() {
        return colesterol;
    }

    public void setColesterol(double colesterol) {
        if(colesterol>=0)
        this.colesterol = colesterol;
    }

    public double getGlucosa() {
        return glucosa;
    }

    public void setGlucosa(double glucosa) {
        if(glucosa>=0)
        this.glucosa = glucosa;
    }

    public double getPresSis() {
        return presSis;
    }

    public void setPresSis(double presSis) {
        if(presSis>=0)
        this.presSis = presSis;
    }

    public double getPresDias() {
        return presDias;
    }

    public void setPresDias(double presDias) {
        if(presDias>=0)
        this.presDias = presDias;
    }
    
    public String fechaSiguienteRevision(){
        int año,mes,dia;
        int añoSig = 0, mesSig = 0, diaSig = 0;
        
        dia=fechaRevision.get(Calendar.DAY_OF_MONTH);
        mes=fechaRevision.get(Calendar.MONTH);
        año=fechaRevision.get(Calendar.YEAR);
        
        if(colesterol>200 || glucosa > 100 || presSis >14 || presDias >9){
            diaSig=dia;
            mesSig=(mes+2);
            añoSig=año;
        }else if(colesterol<200 && glucosa <100 && (presSis >=10 &&presSis <=14) && (presDias >=6 && presDias<=9)){
            if(edad<50){
                diaSig=dia;
                mesSig=mes;
                añoSig=(año+2);
            }
            if(edad>50){
                diaSig=dia;
                mesSig=mes;
                añoSig=(año+1);
        }
       
    }
        return diaSig+"/"+mesSig+"/"+añoSig;
}

    @Override
    public String toString() {
        return nombre + "\t" + dni + "\t" + edad + "\t" + colesterol + "\t\t" + glucosa + "\t\t" + presSis + "\t\t\t" + presDias + "\t\t\t" + fechaSiguienteRevision();
    }

    @Override
    public int compareTo(reconocimiento o) {
        return this.nombre.compareToIgnoreCase(o.getNombre());
    }
    
    
    
    
}
