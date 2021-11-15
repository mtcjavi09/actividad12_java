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
            contactos.save();
        }
        catch(Exception e)
        {}
    }
}
