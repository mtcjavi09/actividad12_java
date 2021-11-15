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
import com.csvreader.CsvWriter; //Para generar y leer el archivo
import javax.swing.JOptionPane; //Para mandar mensajes fuera de la consola
import java.util.Date; //Para poder guardar datos de tipo date en el objeto

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
        
    }
    
    //save: guarda los cambios en el archivo
    public void save() throws Exception
    {
        String archivo = "contactos.csv";
        String [] titulos = {"Contacto", "Teléfono", "Fecha de creación"};
        try
        {
            boolean existe = new File(archivo).exists(); //Se verifica si existe el archivo
            //Si existe el archivo se borra
            if (existe)
            {
                File archivoUsuarios = new File(archivo);
                archivoUsuarios.delete();
            }
            
            
            CsvWriter writer = new CsvWriter(new FileWriter(archivo, true), ',');
            
            writer.writeRecord(titulos);
                      
            writer.endRecord();
            
            
        }
        catch(Exception e)
        {throw new Exception("Datos ingresados no guardados en el archivo CSV");}
    }
    
    //list: muestra los contactos de la agenda
    public String list() throws Exception
    {
        //Variables necesarias para el método
        String nombre; //Guarda el nombre del contacto
        String telefono; //Guarda el teléfono del contacto
        String fecha; //Guarda la fecha de creación del contacto
        int cantidadContactos; //Variable que maneja el ciclo do ... while
        int intentos = 3; //Especifica el número de equivocaciones permitidas
                
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones
        try
        {
            //Se pide al usuario cuántos contactos desea ingresar
            cantidadContactos = Integer.parseInt(JOptionPane.showInputDialog(
                    "Ingresa el número de contactos que deseas agregar"));
            
            //Se repiten las siguientes instrucciones hasta completar el número de contactos deseados
            for (int x = 0; x == cantidadContactos; x++)
            {
                //Se pide al usuario el nombre de contacto
                nombre = JOptionPane.showInputDialog("Ingresa el nombre de tu contacto:");
                
                do
                {
                    //Se pide al usuario el teléfono del contacto
                    telefono = JOptionPane.showInputDialog("Ingresa el número telefónico de"
                            + " tu contacto:");
                    //Si el teléfono no es de 10 dígitos, se resta un intento y vuelve a intentar
                    if (telefono.length() != 10)
                    {
                        intentos -= 1;
                        //Se muestra un mensaje de advertencia al usuario con los intentos restantes
                        JOptionPane.showMessageDialog(null, "El teléfono debe tener 10 dígitos, "
                                + "intenta nuevamente\n" + "Te quedan " + intentos + " intentos",
                                "Teléfono no válido", JOptionPane.ERROR_MESSAGE);
                    }
                }
                //Si no se acabó los intentos y llenó el campo, se finaliza el ciclo
                while(intentos != 0 && telefono.length() == 10); 
                
                //Si el usuario se acabó sus intentos disponibles, lanza una excepción
                if (intentos == 0)
                {throw new Exception("La cantidad de errores permitida ha sido superada.");}
                
                //Se guarda la fecha exacta de creación del contacto
                fecha = LocalDate.now().toString();
                
                //Se crea el objeto de tipo contact
                contact contact = new contact(nombre,telefono,fecha);
                
                //Se guarda el objeto en el HashMap
                contactos.put(telefono,contact);
                                
            }
            
            return "Contactos ingresados correctamente";
        }
        catch(Exception e)
        {throw new Exception("Datos ingresados no compatibles");}
    }
    
    //create: crea un nuevo contacto
    public void crate() throws Exception
    {
        
    }
    
    //delete: elimina un contacto
    public void delete() throws Exception
    {
        
    }
}
