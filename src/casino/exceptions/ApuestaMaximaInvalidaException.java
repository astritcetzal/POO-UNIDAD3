package exceptions;

public class ApuestaMaximaInvalidaException extends Exception {
    private double apuestaMaxima;


    public ApuestaMaximaInvalidaException(double apuestaMaxima) {
        super("La apuesta máxima no puede ser mayor que 35,000. Tú apuesta es de :" + apuestaMaxima);
        this.apuestaMaxima = apuestaMaxima;
    }

        public double getApuestaMaxima() { return apuestaMaxima;}

    
}
