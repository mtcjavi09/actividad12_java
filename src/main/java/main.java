/*
    PROGRAMA 11. | Agenda telefónica | Clase main
    AUTORA: Maria Tchijov Cruz
    Fecha: 13 de Noviembre de 2021.
    Programa para guardar contactos en una agenda telefónica.
 */

//Se importan las librerías necesarias para el correcto funcionamiento de la clase
import java.io.*; //Para permitir el manejo de excepciones
import javax.swing.*; //Para enviar mensajes al usuario fuera de la consola


public class main 
{
    //Método main
    public static void main (String [] args) throws Exception
    {
        try
        {
            addressBook contactos = new addressBook();
            //Se pide al usuario cuántos contactos desea ingresar
            int cantidadContactos = Integer.parseInt(JOptionPane.showInputDialog(
                    "Ingresa el número de contactos que deseas agregar: "));
            String mensaje = contactos.create(cantidadContactos);
            contactos.save();
            
            System.out.println(mensaje + "\n");
        }
        catch(Exception e)
        {System.out.println("Se terminó el programa por la excepción: " + e + "");}
    }
}
