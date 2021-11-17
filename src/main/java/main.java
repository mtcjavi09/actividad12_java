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
            //Se declaran las variables que se usarán en el método
            //nombreUsuario: guardará el nombre del usuario para tener una mayor personalización
            String nombreUsuario;
            //intentos: le dará al usuario tres intentos para no dejar en blanco su nombre
            int intentos = 3;
            //directorio: objeto que ayudará a acceder a los métodos de la clase addressBook
            addressBook directorio = new addressBook();
                
            //Se crea un ciclo para que el usuario ingrese su nombre de forma correcta
            do
            {
                //Se pide el nombre del usuario
                nombreUsuario = JOptionPane.showInputDialog("Bienvenid@ al directorio de contactos\n"
                    + "Por favor, escribe tu nombre:");
                //Si deja en blanco su nombre, se resta un intento y vuelve a intentar
                if (nombreUsuario.equals(""))
                {
                    intentos -= 1;
                    //Se muestra un mensaje de advertencia al usuario con los intentos restantes
                    JOptionPane.showMessageDialog(null, "No dejes el nombre en blanco, "
                            + "intenta nuevamente\n" + "Te quedan " + intentos + " intentos",
                            "Campo no completado", JOptionPane.ERROR_MESSAGE);
                }   
            }
            //Si no se acabó los intentos y llenó el campo, se finaliza el ciclo
            while(intentos != 0 && nombreUsuario.equals("")); 
            
            //Si el usuario se acabó sus intentos disponibles, lanza una excepción
            if (intentos == 0)
            {throw new Exception("Nombre no completado");}
            
            //Una vez que se agregó el nombre, se llama al método menu para mostrar las funciones disponibles
            menu(nombreUsuario);
            
            //Se guardan los resultados en el archivo .csv
            directorio.save();
            
            //Se agrega una línea para mejor visibilidad
            System.out.println();
            
            //Se muestran los contactos guardados al usuario
            directorio.load();
            
            
            //Despedida al usuario por correcta ejecución del programa
            System.out.println("El programa se ha terminado con éxito. Nos vemos pronto"
                    + ", " + nombreUsuario + " :)\n");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {
            System.out.println("Se terminó el programa por la excepción: " + e);
            System.out.println("Hasta pronto :)\n");
        }
    }
    
    //Método menu: ayudará al usuario a elegir la opción que desea
    public static void menu(String nombreUsuario) throws Exception
    {
        try
        {
            //Variables necesarias para el método
            //opciones: arreglo que guardará las opciones que se encuentran disponibles en el programa
            String [] opciones = {"Listar los contactos guardados", "Crear nuevos contactos",
               "Eliminar un contacto existente", "Modificar un usuario existente", "Salir del menú"};
            //opcionElegida: le pide al usuario la acción que desea realizar con los contactos
            String opcionElegida;
            //salir: le pide al usuario saber si desea salir o no del menú
            int salir = 0;
            //intentos: son el máximo de veces que se puede equivocar el usuario
            int intentos = 3;
            
            //Se crea un ciclo do ... while para permitir al usuario realizar las funciones varias veces
            do
            {
                opcionElegida = (String) JOptionPane.showInputDialog(null, "¡Hola! " + nombreUsuario + 
                        " por favor, selecciona la opción que deseas", "Menú principal", JOptionPane.DEFAULT_OPTION, 
                        null, opciones, opciones[0]);
                //directorio: objeto que ayudará a acceder a los métodos de la clase addressBook
                addressBook directorio = new addressBook();


                //Se le notifica al usuario la opción que eligió
                System.out.println("Elegiste: " + opcionElegida);

                //Se usa un switch ... case para elegir el método apropiado de acuerdo con la elección del usuario
                switch(opcionElegida)
                {
                    case "Listar los contactos guardados": //Se enlistan todos los contactos del HashMap
                    {
                        //Se llama al método list
                        directorio.list();
                        //Se termina el switch
                        break;
                    }
                    case "Crear nuevos contactos": //Se crean contactos y se guardan en el HashMap
                    {
                        //Se pide al usuario cuántos contactos desea ingresar
                        int cantidadContactos = Integer.parseInt(JOptionPane.showInputDialog(
                                "Ingresa el número de contactos que deseas agregar: "));
                        //Se llama al método create
                        directorio.create(cantidadContactos);
                        //Se termina el switch
                        break;
                    }
                    case "Eliminar un contacto existente": //Elimina un contacto existente
                    {
                        //Se llama al método delete
                        directorio.delete();
                        //Se termina el switch
                        break;
                    }
                    case "Modificar un usuario existente": //Modifica los datos de un contacto existente
                    {
                        //Se llama al método modify
                        directorio.modify();
                        //Se termina el switch
                        break;
                    }
                }
                
                if(opcionElegida.equals("Salir del menú"))
                {
                    //Se pregunta al usuario si desea salir del menú
                    salir = Integer.parseInt(JOptionPane.showInputDialog("¿Estás seguro de que deseas salir?\n"
                            + "[1] SI\n[2] NO\n"));
                    
                    if (salir < 1 && salir > 2)
                    {
                        //Primero se resta el intento
                        intentos -= 1;
                        //Después se muestra un mensaje de advertencia al usuario con los intentos restantes
                        JOptionPane.showMessageDialog(null, "Debes brindar una respuesta válida, "
                                + "intenta nuevamente\n" + "Te quedan " + intentos + " intentos",
                                "Confirmación no recibida", JOptionPane.ERROR_MESSAGE);
                    }
                }
                
                //Se agrega una línea para mejor visibilidad
                System.out.println();
                
            }
            //Si eligió la opción de salir y no se acabó sus intentos, se ciera el ciclo
            while(intentos != 0 && salir != 1);
            
            //Si el usuario se acabó sus intentos disponibles, lanza una excepción
            if (intentos == 0)
            {throw new Exception("La cantidad de errores permitida ha sido superada.");}
            
        }
        catch(Exception e)
        {throw new Exception("Error en la ejecución del menú");}
    }
}
