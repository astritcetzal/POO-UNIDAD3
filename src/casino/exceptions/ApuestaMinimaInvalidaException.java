package exceptions;
public class ApuestaMinimaInvalidaException extends Exception {
    private double apuestaMinima;
    
    public ApuestaMinimaInvalidaException(double apuestaMinima) {
        super("La apuesta mínima no puede ser menor que 100.00. Tú apuesta:"+apuestaMinima);
        this.apuestaMinima = apuestaMinima;
    }
}