package src.casino.interfaces;
import src.casino.persona.Jugador;
public interface Jugable {

    public void iniciar(Jugador j);

    public void jugar();

    public void terminar();
    
    public String getNombre();
    
    

    
}