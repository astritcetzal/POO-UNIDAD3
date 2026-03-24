package persistencia;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

import persona.Jugador;
import persona.JugadorVIP;

public class JugadorVIPRepositorio implements Repositorio<JugadorVIP> {

    private static final String ARCHIVOVIP = "jugadores.csv";

    @Override
    public void guardar(List<JugadorVIP> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVOVIP))) {
            for (Jugador j : lista) {
                bw.write(j.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar jugadores: " + e.getMessage());
        }
    }

    @Override
    public List<JugadorVIP> cargar() {
        List<JugadorVIP> lista = new ArrayList<>();
        File archivo = new File(ARCHIVOVIP);

        if (archivo.exists()) {
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isEmpty()) {
                    continue;
                }
                try {
                    lista.add(JugadorVIP.fromCSV(linea));
                } catch (Exception e) {
                    System.out.println("Advertencia: línea corrupta ignorada -> " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar jugadores: " + e.getMessage());
        }

        return lista;
    }

}
