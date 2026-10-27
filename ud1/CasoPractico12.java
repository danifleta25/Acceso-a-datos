package ud1;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CasoPractico12 {
    public static void main(String[] args) throws IOException {

        /*
        Estás en soporte de TI y te piden una utilidad para localizar rápidamente incidencias en logs. Desarrolla un programa
        que busque una palabra clave (por ejemplo, “ERROR”) en un fichero de texto, y que muestre para cada aparición la línea y la columna,
        leyendo el fichero línea a línea.
         */


        File nomFich = new File("C:\\Users\\Dani\\Desktop\\Nuevo Documento de texto.txt");

        try(BufferedReader br = new BufferedReader(new FileReader(nomFich, StandardCharsets.UTF_8))) {
            String linea = "";
            int contadorLinea = 1;
            int erroresLeidos = 0;
            while ((linea = br.readLine()) != null) {
                if (linea.toLowerCase().contains("error")) { // Si la linea contiene la palabra error

                    int indice = linea.toLowerCase().indexOf("error"); // almacena el indice de comienzo de la palabra
                    while (indice != -1) {
                        erroresLeidos++;
                        System.out.println("Linea " + contadorLinea + " en el índice " + (indice + 1) + " --> " + linea);
                        indice = linea.toLowerCase().indexOf("error", indice + 5); // almacena el indice de comienzo de la palabra
                    }
                }
                contadorLinea++;
            }
            System.out.println("------------------------------------------------");
            System.out.println("Lineas leidas: " + contadorLinea + " | Errores leídos: " + erroresLeidos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
