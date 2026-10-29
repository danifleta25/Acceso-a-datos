package ud1;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GsonPrueba {

    public static void main(String[] args) throws IOException {

        String ruta = "C:\\Users\\Dani\\Desktop\\gson prueba.txt";
        Gson gson = new Gson();

        try (FileWriter fw = new FileWriter(ruta)) {

            GsonPrueba prod = new GsonPrueba(5, "LAPIZ", 0.62);
            String json = gson.toJson(prod); // Serialización a String
            gson.toJson(prod, fw); // Serialización a Stream (fichero)

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try (FileReader fr = new FileReader(ruta)) {

            // Deserialización desde fichero:
            GsonPrueba prod2 = gson.fromJson(fr, GsonPrueba.class);
            System.out.println(prod2.codigo);
            System.out.println(prod2.nombre);
            System.out.println(prod2.precio);
//


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        String strJson = "{\"id\":5,\"nombre\":\"LAPIZ\",\"precio\":0.62}";
        // Deserialización desde String:
           GsonPrueba prod3 = gson.fromJson(strJson, GsonPrueba.class);
        System.out.println(prod3.codigo);
        System.out.println(prod3.nombre);
        System.out.println(prod3.precio);    }


    private int codigo;
    private String nombre;
    private double precio;

    public GsonPrueba(int codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }
}
