import java.util.ArrayList;
import java.util.List;
public class Casino {
    private String nombre;
    private List<Jugador> jugadores; //agrgacion
    private List<Empleado>empleados; //agregacion
    private List<JuegoMesa> juegos; //composicion
    private double cajaTotal;

    public Casino(String nombre){
        this.nombre = nombre;
        this.jugadores= new ArrayList<>();
        this.empleado = new ArrayList<>();
        this.JuegoMesa =new ArrayList<>();

    }
    //En main List<JuegoMesa> juegos = List.of ();

    public String getNombre(){
        return nombre;
    }

    public double getCajaTotal(){
        return cajaTotal;
    }

    


 
}
