package juegos;

import persona.Jugador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exceptions.ApuestaMaximaInvalidaException;
import exceptions.ApuestaMinimaInvalidaException;
import exceptions.JuegoInactivoException;
import exceptions.SaldoInsuficienteException;

public class BlackJack extends JuegoMesa {
    private int puntosJugador;
    private int puntosCasa;
    private List<Integer> mazo;

    public BlackJack(String nombre, Jugador jugadorActual, double apuestaMinima, double apuestaMaxima, boolean activo) throws ApuestaMaximaInvalidaException, ApuestaMinimaInvalidaException {
        super(nombre, jugadorActual, apuestaMinima, apuestaMaxima, activo);
        this.mazo = new ArrayList<>();
        prepararMazo();
    }

    private void prepararMazo() {
        mazo.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 10; j++) {
                mazo.add(j);
            }
        }
        Collections.shuffle(mazo);
    }

    private int sacarCarta() {
        if (mazo.isEmpty())
            prepararMazo();
        return mazo.remove(0);
    }

    @Override
    public void iniciar(Jugador jugador) throws SaldoInsuficienteException {
        if (jugador == null) return; 
        
        if (jugador.getSaldo() < getApuestaMinima()) {
            throw new SaldoInsuficienteException(jugador.getSaldo(), getApuestaMinima());
        }

        setJugadorActual(jugador);
        this.puntosJugador = 0;
        this.puntosCasa = 0;
        setActivo(true);
        prepararMazo();
        
        System.out.println("══════════════════════════════════════");
        System.out.println("  Bienvenido al BlackJack, " + jugador.getNombre() + "!");
        System.out.println("  Apuesta mínima : $" + getApuestaMinima());
        System.out.println("  Apuesta máxima : $" + getApuestamaxima());
        System.out.println("  Saldo actual   : $" + jugador.getSaldo());
        System.out.println("══════════════════════════════════════");
    }

    @Override
    public void jugar() {
        try {
            if (!isActivo()) throw new JuegoInactivoException(getNombre());

            double montoSimulado = getApuestaMinima(); 
            getJugadorActual().apostar(montoSimulado);

            repartirCartas();
            
            // Lógica automática del jugador (pide hasta 17)
            while (this.puntosJugador < 17) {
                this.puntosJugador += sacarCarta();
            }

            // Lógica automática de la casa
            if (this.puntosJugador <= 21) {
                while (this.puntosCasa < 17) {
                    this.puntosCasa += sacarCarta();
                }
            }
            
            // Manejo de pagos
            int resultado = calcularPuntos();
            if (resultado == 1) {
                getJugadorActual().recibirPago(montoSimulado * 2);
            } else if (resultado == 0) {
                getJugadorActual().recibirPago(montoSimulado); // Empate devuelve apuesta
            }

            terminar();
            
        } catch (JuegoInactivoException e) {
            System.out.println("Error de estado: " + e.getMessage());
        }
    }
    
    @Override
    public void terminar() {
        if (getJugadorActual() != null) {
            System.out.println("══════════════════════════════════════");
            int resultado = calcularPuntos();
            if (resultado == 1) {
                System.out.println("  RESULTADO       : ¡GANASTE! 🎉");
            } else if (resultado == 2) {
                System.out.println("  RESULTADO       : GANA LA CASA");
            } else {
                System.out.println("  RESULTADO       : EMPATE");
            }
            System.out.println("  Puntos Jugador  : " + puntosJugador);
            System.out.println("  Puntos Casa     : " + puntosCasa);
            System.out.println("  Saldo final     : $" + getJugadorActual().getSaldo());
            System.out.println("══════════════════════════════════════");
        }
        setActivo(false);
        setJugadorActual(null);
    }

    public void repartirCartas() {
        this.puntosJugador = sacarCarta() + sacarCarta();
        this.puntosCasa = sacarCarta() + sacarCarta();
    }

    public int calcularPuntos() {
        if (puntosJugador > 21) return 2;
        if (puntosCasa > 21 || puntosJugador > puntosCasa) return 1;
        if (puntosJugador < puntosCasa) return 2;
        return 0;
    }

    public int getPuntosJugador() { return puntosJugador; }
    public int getPuntosCasa() { return puntosCasa; }
}