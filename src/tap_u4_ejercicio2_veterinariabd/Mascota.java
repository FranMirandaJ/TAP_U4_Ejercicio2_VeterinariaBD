
package tap_u4_ejercicio2_veterinariabd;

public class Mascota {
    
    int id;
    String nombre;
    String propietario;
    String atencion;
    float costo;
    String raza;

    public Mascota(int id, String nombre, String propietario, String atencion, float costo, String raza) {
        this.id = id;
        this.nombre = nombre;
        this.propietario = propietario;
        this.atencion = atencion;
        this.costo = costo;
        this.raza = raza;
    }
    
    public String[] toRenglon(){
        String[] renglon = new String[6];
        
        renglon[0] = ""+id;
        renglon[1] = ""+nombre;
        renglon[2] = ""+propietario;
        renglon[3] = ""+atencion;
        renglon[4] = ""+costo;
        renglon[5] = ""+raza;
        
        return renglon;
    }
    
}
