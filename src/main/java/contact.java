/*
    PROGRAMA 11. | Agenda telefónica | Clase phone
    AUTORA: Maria Tchijov Cruz
    Fecha: 13 de Noviembre de 2021.
    Programa para guardar contactos en una agenda telefónica.
 */

public class contact 
{
    //Atributos de la clase
    private String name; //Guarda el nombre del contacto
    private String phone; //Guarda el teléfono del contacto
    private String date; //Guarda la fecha de creación del contacto
    private String hour; //Guarda la hora de creación del contacto
    
    //Métodos get y set para los atributos privados
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}
    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}
    public String getHour() {return hour;}
    public void setHour(String hour) {this.hour = hour;}
    
    
    //Constructor para creación del objeto usando Hash Map
    public contact(String name, String phone, String date, String hour) 
    {
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.hour = hour;
    }  
}
