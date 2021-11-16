/*
    PROGRAMA 11. | Agenda telefónica | Clase adresBook
    AUTORA: Maria Tchijov Cruz
    Fecha: 13 de Noviembre de 2021.
    Programa para guardar contactos en una agenda telefónica.
 */

//Se importan las librerías necesarias para el correcto funcionamiento de la clase
import java.util.HashMap; //Para crear el Hash Map que guardará el contacto
import java.time.LocalDate; //Para poder guardar la fecha de creación del contacto
import java.io.*; //Para manejar las excepciones
import java.time.LocalTime; //Para poder guardar la hora de creación del contacto
import com.csvreader.*; //Para generar y leer el archivo
import javax.swing.JOptionPane; //Para mandar mensajes fuera de la consola

public class addressBook 
{
    //Atributos de la clase adressBook
    private HashMap<String, contact> contactos = new HashMap <String, contact> ();
    
    //Métodos get y set para el atributo privado
    public HashMap<String, contact> getContactos() {return contactos;}
    public void setContactos(HashMap<String, contact> contactos) {this.contactos = contactos;}
    
    //Métodos propios de la clase
    
    //load: carga los contactos del archivo
    public void load() throws Exception
    {
        //Variables necesarias para el método
        //Constante del nombre del archivo donde fueron guardados los contactos
        String ARCHIVO = "contactos.csv";
                
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones
        try
        {
            //Se crea el lector del archivo .csv
            CsvReader reader = new CsvReader(ARCHIVO);
            
            //Se crea un nuevo HashMap para controlar el ciclo while
            HashMap<String, contact> archivo = new HashMap <>();
            
            //Se crea un ciclo while para guardar en el HashMap los registros encontrados
            while(reader.readRecord())
            {
                //Se guardan los datos en las variables para el HashMap
                String name = reader.get(0);
                String phone = reader.get(1);
                String date = reader.get(2);
                String hour = reader.get(3);

                //Se crea el objeto con los datos necesarios
                contact contacto = new contact(name,phone,date,hour);

                //Se guarda el registro en el HashMap
                archivo.put(phone, contacto);
            }
            
            //Se cierra el lector
            reader.close();

            //Se le notifica al usuario que se mostrarán todos los contactos
            System.out.println("Los contactos guardados en el archivo son:");
            //Se agrega una línea para mejor lectura
            System.out.println();
            //Se usa un ciclo for para recorrer el HashMap
            for(Object x : archivo.values())
            {
                 //Se convierte el objeto x al objeto y
                 contact y = (contact) x;
                 //Me muestran los datos en consola
                 System.out.print("Nombre: " + y.getName() + "; ");
                 System.out.println("Telefono: " + y.getPhone());
                 System.out.print("Fecha de creación: "+ y.getDate() + "; ");
                 System.out.println("Hora de creación: " + y.getHour());
                 //Se agrega una línea para mejor lectura
                 System.out.println();
            }
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {throw new Exception("Error en la lectura del archivo .csv");}
    }
    
    //save: guarda los cambios en el archivo
    public void save() throws Exception
    {
        //Variables necesarias para el método
        //Constante del nombre del archivo donde se guardarán los contactos
        String ARCHIVO = "contactos.csv"; 
        
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones
        try
        {
            //Se verifica si existe el archivo
            boolean existe = new File(ARCHIVO).exists(); 
            
            //Si existe el archivo, éste será borrado
            if (existe)
            {
                File archivoUsuarios = new File(ARCHIVO);
                archivoUsuarios.delete();
            }
            
            //Se crea el escritor que guardará los datos
            CsvWriter writer = new CsvWriter(new FileWriter(ARCHIVO, true), ',');
            
            //Se escriben las columnas para reconocer los datos guardados
            writer.write("Contacto");
            writer.write("Teléfono");
            writer.write("Fecha de creación");
            writer.write("Hora de creación");
            //Deja de escribir en la misma fila
            writer.endRecord();
            
            //Se crea un ciclo para recorrer el HashMap
            for (Object x : contactos.values())
            {
                //Se convierte el objeto x al objeto y
                contact y = (contact) x;
                //Se llaman a los métodos get para conocer y escribir cada dato
                //Está en el mismo orden que las columnas
                //Se escribe el contacto
                writer.write(y.getName());
                //Se escribe el teléfono
                writer.write(y.getPhone());
                //Se escribe la fecha
                writer.write(y.getDate());
                //Se escribe la hora
                writer.write(y.getHour());
                //Deja de escribie en la misma fila
                writer.endRecord();
            }
            
            //Una vez guardados todos los valores en el archivo, se cierra el escritor
            writer.close();
            
            //Se regresa un valor true para indicar el fin exitoso del método
            System.out.println("Los contactos han sido guardados correctamente en el archivo .csv"); 
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {throw new Exception("Datos ingresados no guardados en el archivo .csv");}
    }
    
    //list: muestra los contactos de la agenda (usando el HashMap)
    public void list() throws Exception
    { 
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones
        try
        {
           //Se le notifica al usuario que se mostrarán todos los contactos
           System.out.println("Los contactos guardados en el HashMap son:");
           //Se agrega una línea para mejor lectura
           System.out.println();
           //Se usa un ciclo for para recorrer el HashMap
           for(Object x : contactos.values())
           {
                //Se convierte el objeto x al objeto y
                contact y = (contact) x;
                //Me muestran los datos en consola
                System.out.print("Nombre: " + y.getName() + "; ");
                System.out.println("Telefono: " + y.getPhone());
                System.out.print("Fecha de creación: "+ y.getDate() + "; ");
                System.out.println("Hora de creación: " + y.getHour());
                //Se agrega una línea para mejor lectura
                System.out.println();
           }
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {throw new Exception("Datos ingresados no guardados en el archivo .csv");}
        //Para evitar que se termine el programa, se agrega la cláusula finally
        finally
        {System.out.println("No se pudieron mostrar los contactos correctamente.");}
    }
    
    //create: crea un nuevo contacto
    public void create(int cantidadContactos) throws Exception
    {
        //Variables necesarias para el método
        String nombre; //Guarda el nombre del contacto
        String telefono; //Guarda el teléfono del contacto
        String fecha; //Guarda la fecha de creación del contacto
        String hora; //Guarda la hora de creación del contacto
        int intentos = 3; //Especifica el número de equivocaciones permitidas

        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones
        try
        {   
            //Se repiten las siguientes instrucciones hasta completar el número de contactos deseados
            for (int x = 0; x < cantidadContactos; x++)
            {
                //Se pide al usuario el nombre de contacto, y se guarda en mayúsculas para homogenizar el formato
                nombre = JOptionPane.showInputDialog("Ingresa el nombre de tu contacto:").toUpperCase();
                
                //Se crea el ciclo para pedir el número al usuario y asegurar que cumpla con 10 dígitos
                do
                {
                    //Se pide al usuario el teléfono del contacto
                    telefono = JOptionPane.showInputDialog("Ingresa el número telefónico de"
                            + " tu contacto:");
                    //Si el teléfono no es de 10 dígitos, se resta un intento y vuelve a intentar
                    if (telefono.length() != 10)
                    {
                        //Primero se resta el intento
                        intentos -= 1;
                        //Después se muestra un mensaje de advertencia al usuario con los intentos restantes
                        JOptionPane.showMessageDialog(null, "El teléfono debe tener 10 dígitos, "
                                + "intenta nuevamente\n" + "Te quedan " + intentos + " intentos",
                                "Teléfono no válido", JOptionPane.ERROR_MESSAGE);
                    }
                                        
                }
                //Si llenó el teléfono de forma correcta y no se acabó los intentos, se termina el ciclo
                while(intentos != 0 && telefono.length() != 10); 
                
                //Si el usuario se acabó sus intentos disponibles, lanza una excepción
                if (intentos == 0)
                {throw new Exception("La cantidad de errores permitida ha sido superada.");}

                //Se guarda la fecha exacta de creación del contacto
                //Al final se convierte a string para su guardado en el objeto
                fecha = LocalDate.now().toString();
                
                //Se guarda la hora exacta de creación del contacto
                //Al final se convierte a string para su guardado en el objeto
                hora = LocalTime.now().toString();
                
                //Se crea el objeto de tipo contact con los datos ingresados
                contact contact = new contact(nombre, telefono, fecha, hora);
                
                //Se guarda el objeto en el HashMap, repitiendo el teléfono como la llave
                contactos.put(telefono, contact);
            }

            //Se retorna el mensaje de creación exitosa de los contactos
            System.out.println("Se agregaron " + contactos.size() + " contactos correctamente");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {throw new Exception("Los tipos de datos ingresados no son válidos");}
        //Para evitar que se termine el programa, se agrega la cláusula finally
        finally
        {System.out.println("No se pudieron agregar los contactos.");}
    }
    
    //delete: elimina un contacto
    public void delete() throws Exception
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones
        try
        {
            //Se pide el número de teléfono del contacto que quiere eliminar el usuario
            String telefono = JOptionPane.showInputDialog("Ingresa el teléfono del contacto"
                    + " que deseas eliminar");
            contact elimina = contactos.get(telefono);
            //Se pide confirmación del usuario para eliminar el contacto
            int confirma = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar el siguiente contacto?\n"
                    + elimina.getName() + "; " + elimina.getPhone());
            //Se decide la acción a realizar a partir de la confirmación a partir de la decisión del usuario
            //Se usa un switch ... case para dicha acción
            switch(confirma)
            {
                case 0: //Se elimina el contacto
                {
                    //Se elimina el contacto deseado del HashMap contactos
                    contactos.remove(telefono);
                    //Se confirma al usuario que el contacto ha sido eliminado
                    System.out.println("El contacto ha sido eliminado exitosamente.");
                }
                
                default: //No se elimina el contacto
                {
                    System.out.println("El usuario ha decidido cancelar la eliminación del contacto.");
                }
            }
            
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {throw new Exception("Contacto inexistente");}
        //Para evitar que se termine el programa, se agrega la cláusula finally
        finally
        {System.out.println("El contacto no pudo ser eliminado.");}
    }
    
    //modify: modifica la información de algún contacto
    public void modify() throws Exception
    {
        //Variables necesarias para el correcto funcionamiento del método
        int intentos = 3; //Especifica el número de equivocaciones permitidas
        String newTelefono; //Especifica el nuevo número telefónico que tendrá el contacto modificado
        
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones
        try
        {
            //Se pide el número de teléfono del contacto que quiere modificar el usuario
            String telefono = JOptionPane.showInputDialog("Ingresa el teléfono del contacto"
                    + " que deseas modificar");
            //Se pide al usuario el nuevo nombre de contacto, y se guarda en mayúsculas para homogenizar el formato
            String nombre = JOptionPane.showInputDialog("Ingresa el nuevo nombre de tu contacto:").toUpperCase();

            //Se crea el ciclo para pedir el nuevo número al usuario y asegurar que cumpla con 10 dígitos
            do
            {
                //Se pide al usuario el teléfono del contacto
                newTelefono = JOptionPane.showInputDialog("Ingresa el nuevo número telefónico de"
                        + " tu contacto:");
                //Si el teléfono no es de 10 dígitos, se resta un intento y vuelve a intentar
                if (newTelefono.length() != 10)
                {
                    //Primero se resta el intento
                    intentos -= 1;
                    //Después se muestra un mensaje de advertencia al usuario con los intentos restantes
                    JOptionPane.showMessageDialog(null, "El teléfono debe tener 10 dígitos, "
                            + "intenta nuevamente\n" + "Te quedan " + intentos + " intentos",
                            "Teléfono no válido", JOptionPane.ERROR_MESSAGE);
                }

            }
            //Si llenó el teléfono de forma correcta y no se acabó los intentos, se termina el ciclo
            while(intentos != 0 && newTelefono.length() != 10); 

            //Si el usuario se acabó sus intentos disponibles, lanza una excepción
            if (intentos == 0)
            {throw new Exception("La cantidad de errores permitida ha sido superada.");}

            //Se guarda la fecha exacta de modificación del contacto
            //Al final se convierte a string para su guardado en el objeto
            String fecha = LocalDate.now().toString();

            //Se guarda la hora exacta de modificación del contacto
            //Al final se convierte a string para su guardado en el objeto
            String hora = LocalTime.now().toString();
            
            //Se crea el objeto de tipo contact con los nuevos datos ingresados
            contact contact = new contact(nombre, telefono, fecha, hora);

            //Se realizan diferentes acciones dependiendo del número telefónico
            //Si el teléfono es igual al que tenía en el registro
            if (telefono.equals(newTelefono)) 
            {
                //Sólo se reemplazarán los demás valores, sin necesidad de cambiar la clave
                contactos.replace(telefono, contact);
            }
            //Si el nuevo teléfono es diferente al que se tenía como clave
            else
            {
                //Se llama al método de eliminación del registro anterior del HashMap
                //La razón de esto es por si cambia el teléfono, se pueda cambiar la clave
                //  y así se pueda buscar el registro con el nuevo teléfono
                contactos.remove(telefono);
                //Se crea un nuevo registro con todos los datos actualizados
                contactos.put(newTelefono, contact);
            }
            
            //Se muestra al usuario que la modificación fue exitosa
            System.out.println("El contacto ha sido modificado correctamente");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {throw new Exception("Contacto inexistente");}
        //Para evitar que se termine el programa, se agrega la cláusula finally
        finally
        {System.out.println("El contacto no pudo ser modificado.");}
    }
}
