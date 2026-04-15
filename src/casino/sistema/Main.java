package sistema;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import exceptions.ApuestaInvalidaRuletaException;
import exceptions.JuegoInactivoRuletaException;
import exceptions.SaldoInsuficienteException;
import exceptions.ApuestaMinimaInvalidaException;
import exceptions.CedulaEmpleadoDuplicadoException;
import exceptions.IDJugadorDuplicadoException;
import exceptions.JuegoInactivoException;
import exceptions.ApuestaMaximaInvalidaException;
import juegos.BlackJack;
import juegos.Ruleta;
import persona.Empleado;
import persona.Jugador;
import persona.JugadorVIP;
import repositorio.JugadorRepository;
import repositorio.PersonaRepository;
import servicio.EmpleadoService;
import servicio.JugadorService;
import persistencia.EmpleadoArchivo;
import persistencia.JugadorArchivo;

public class Main { 
    public static void main(String[] args) throws IOException, JuegoInactivoException {
        Scanner sc = new Scanner(System.in);
        Casino casino = new Casino("La Cima");

        try{
            EmpleadoService empleadoService = new EmpleadoService(new EmpleadoArchivo("empleados.csv"));
            JugadorService jugadorService = new JugadorService(new JugadorArchivo("jugadores.cvs"));

            int opcion;
            do{
                System.out.println("\n~~ SISTEMA CASINO: " + casino.getNombre().toUpperCase() + " ~~\n");
                System.out.println("1.- Registrar Jugador");
                System.out.println("2.- Registrar Empleado");
                System.out.println("3.- Buscar jugador");
                System.out.println("4.- Ver jugadores VIP"); // filtrado
                System.out.println("5.- Eliminar empleado"); //Iterator
                System.out.println("6.- Salir y guarfar");
                System.out.print("\nSeleccione una opción: ");
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("Nombre: "); String nomJ = sc.nextLine();
                        System.out.println("Apellido: "); String apeJ = sc.nextLine();
                        System.out.println("Cedula (ej. CED-001): "); String cedJ = sc.nextLine();
                        System.out.println("ID jugador (ej. JUG-001): "); String id = sc.nextLine();
                        
                        jugadorService.agregarJugador(nomJ, apeJ, cedJ, 30, id, 1000);
                        System.out.println("El jugador se registró correctamente!");
                        break;

                    case 2:
                        System.out.println("Nombre: "); String nomE = sc.nextLine();
                        System.out.println("Apellido: "); String apeE = sc.nextLine();
                        System.out.println("Cédula (ej. EMP-001): "); String cedE = sc.nextLine();
                        System.out.println("Cargo: "); String cargo = sc.nextLine();
                        
                        empleadoService.agregarEmpleado(nomE, apeE, cedE, 25, cargo);
                        System.out.println("El empleado se registró en el sistema");
                        break; 

                    case 3:
                        System.out.println("ID que desea buscar"); String jugbuscar = sc.nextLine();
                        Jugador encontrado =  jugadorService.buscarJugador(jugbuscar);
                        System.out.println("El jugador fue encotrado: " + encontrado.getNombre());
                        break;

                    case 4:
                    

                    case 5:

                    case 6:

                    default:
    
                }

            } while (opcion != 6);
        } catch (IOException e){
            System.out.println("Error de archivo: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Error del sistema: " + e.getMessage());
        }
    }

}