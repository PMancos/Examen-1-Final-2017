
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author loren
 */
public class aplicacion implements Comparator<reconocimiento>{
static Scanner teclado=new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<reconocimiento> lista=null;
        File fichero=new File("RevMedicas.dat");
        try{
        if(fichero.exists())
            {
                FileInputStream fis=new FileInputStream(fichero);
                ObjectInputStream ois=new ObjectInputStream(fis);
                lista=(ArrayList<reconocimiento>)ois.readObject();
                ois.close();
                fis.close();
            }else
               lista=new ArrayList<>();
        
        int opcion=0;
        
        boolean modificado=false;
        
            do {
                opcion = menu();
                switch (opcion) {
                    case 1:
                        revisarDatos(lista);
                        modificado = true;
                        break;
                    case 2:
                        Collections.sort(lista);
                        mostrarDatos(lista);
                        break;
                    case 3:
                        Collections.sort(lista, new aplicacion());
                        mostrarDatos(lista);
                        break;
                    case 4:
                        if (modificado) {
                            //Si se ha modificado guardamos los cambios
                            FileOutputStream fos = new FileOutputStream(fichero);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            System.out.println("Guardado correctamente");
                            oos.writeObject(lista);
                            oos.close();
                        } else {
                            System.out.println("No ha habido cambios, no se ha guardado");
                        }
                        System.out.println("Fin del programa");
                        break;

                }
            } while (opcion !=4);
          
        } catch (ClassNotFoundException ex) {
        Logger.getLogger(aplicacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
        Logger.getLogger(aplicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
}
      public static int pedirNumeroEntero() {
        int n = 0;
        try {
            n = teclado.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error no has introducido un entero");
            teclado.nextLine();
        }
        return n;
    }

    public static int menu() {

        System.out.println("\n1. Registrar datos de la revision"
                + "\n2. Listar por nombre alfabeticamente"
                + "\n3. listar por edad ascendentemente"
                + "\n4. Salir\n");

        int opcion;
        do {
            System.out.print("Introduce una opcion (1/4): ");
            opcion = pedirNumeroEntero();
        } while (opcion < 1 || opcion > 4);
        teclado.nextLine();
        return opcion;
    }
    public static void revisarDatos(ArrayList<reconocimiento>lista){
        String dni, nombre;
        int edad;
        try{
        double colesterol, glucosa, presS, presD;
        do{
        System.out.print("Introduce tu nombre: ");
            nombre=teclado.nextLine();
        }while(nombre.isEmpty());
        do{
        System.out.print("Introduce tu dni: ");
            dni=teclado.nextLine();
        }while(dni.length()!=9);
        do{
        System.out.print("Introduce tu edad: ");
            edad=teclado.nextInt();
        }while(edad<0);
        do{
        System.out.print("Introduce tu colesterol: ");
            colesterol=teclado.nextDouble();
        }while(colesterol<0);
        do{
        System.out.print("Introduce tu glucosa: ");
            glucosa=teclado.nextDouble();
        }while(glucosa<0);
        do{
        System.out.print("Introduce tu presion sistolica: ");
            presS=teclado.nextDouble();
        }while(presS<0);
        do{
        System.out.print("Introduce tu pression diastolica: ");
            presD=teclado.nextDouble();
        }while(presD<0);
        
        
        
        lista.add(new reconocimiento(dni, nombre, edad, colesterol, glucosa, presS, presD));
        System.out.println("Paciente agregado !!!");
        
        }catch(InputMismatchException ex){
            System.out.println("\nHas introducido un tipo de dato erroneo\n\tVuelve a introducir los datos correctamente");
            teclado.nextLine();
        }
    }
    public static void mostrarDatos(ArrayList<reconocimiento>lista){
        System.out.println("");
        System.out.println("Nombre\tDNI\t\tEdad\tColesterol\tGlucosa\t\tPresion Sistolica\tPresion Diastolica\tFecha Revision");
        System.out.println("");           
        for (int i=0;i<lista.size();i++){
            System.out.println(lista.get(i).toString());
        }
    }

    @Override
    public int compare(reconocimiento o1, reconocimiento o2) {
        return o2.getEdad()-o1.getEdad();
    }
}