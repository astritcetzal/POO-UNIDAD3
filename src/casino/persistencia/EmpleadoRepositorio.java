package persistencia;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import persona.Empleado;

public class EmpleadoRepositorio implements Repositorio<Empleado> {
    private static final String nombreArchivo= "empleado.csv";

    @Override
    public void guardar(List<Empleado> empleados){
        try(PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo))){
            pw.println(",,,,,");
            for (Empleado empl: empleados){
                pw.println(empl.toCSV());
            }
        }
    }

    @Override
    public List<Empleado> cargar() throws IOException{
        List<Empleado> empleados = new ArrayList<>();
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()){
            return empleados;
        }
        try(BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))){
            String linea;
            boolean primeraLinea = true;
            while ((linea= br.readLine()) != null){
                if (primeraLinea){
                    primeraLinea = false;
                    continue;
                }
                if (!linea.trim().isEmpty()){
                    try {
                        empleados.add(Empleado.fromCSV(linea));
                    } catch(IllegalArgumentException e){
                        System.out.println("Linea ignorada: " + e.getMessage());
                    }
                }
            }
        }
        return empleados;
    }
}
