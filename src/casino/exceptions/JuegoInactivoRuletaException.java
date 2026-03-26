package exceptions;

public class JuegoInactivoRuletaException extends Exception {
    private String nombreRuleta;

    public JuegoInactivoRuletaException(String nombreRuleta) {
        super("La Ruleta: " + nombreRuleta + " debe estar activa o no tienes un jugador asignado");
        this.nombreRuleta = nombreRuleta;
    }

    public String getNombreRuleta() {
        return nombreRuleta;
    }
}