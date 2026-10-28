package ud1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoCSV {

    public static void main(String[] args) throws IOException {
        List<ProductoCSV> lProd = new ArrayList<>();

        ProductoCSV raton = new ProductoCSV(1, "Raton", null);
        ProductoCSV teclado = new ProductoCSV(2, "Teclado", 25.5);

        lProd.add(raton);
        lProd.add(teclado);

        escribirCSV(lProd);



        String ruta = "C:\\Users\\Dani\\Desktop\\prueba csv.txt";

        System.out.println(leerCSV(ruta));
    }

    private Integer cod;
    private String descr;
    private Double prUnit;

    public ProductoCSV(Integer cod, String descr, Double prUnit) throws IOException {
        this.cod = cod;
        this.descr = descr;
        this.prUnit = prUnit;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescr() {
        return descr;
    }

    public Double getPrUnit() {
        return prUnit;
    }


    @Override
    public String toString() {
        return "ProductoCSV{" +
                "cod=" + cod +
                ", descr='" + descr + '\'' +
                ", prUnit=" + prUnit +
                '}';
    }

    static void escribirCSV(List<ProductoCSV> lProd) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Dani\\Desktop\\prueba csv.txt"))) {
            String separador = ";";

            for (ProductoCSV prod : lProd) {
                bw.write((prod.getCod() != null) ? prod.getCod().toString() : "");
                bw.write(separador);

                bw.write((prod.getDescr() != null) ? prod.getDescr() : "");
                bw.write(separador);

                bw.write((prod.getPrUnit() != null) ? prod.getPrUnit().toString() : "");
                bw.newLine();
            }
        }
    }

    static List<ProductoCSV> leerCSV(String ruta){
        //ruta = "C:\\Users\\Dani\\Desktop\\prueba csv.txt";
        List<ProductoCSV> productosLeidos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))){
            String linea = "";
            String separador = ";";
            while ((linea = br.readLine()) != null){
                String[] campos = linea.split(separador, -1);
                if (campos.length < 3) {
                    System.out.println("Error, se esperaban 3 campos");
                    continue;
                }
                Integer cod = (!campos[0].isEmpty()) ? Integer.parseInt(campos[0]) : null;
                String desc = (!campos[1].isEmpty()) ? campos[1] : "";
                Double prUnit = (!campos[2].isEmpty()) ? Double.parseDouble(campos[2]): null;

                ProductoCSV producto = new ProductoCSV(cod, desc, prUnit);
                productosLeidos.add(producto);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error " + e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e){
            System.out.println("Error " + e.getMessage());
        }
        return productosLeidos;
    }


}


