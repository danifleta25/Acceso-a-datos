package ud1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoObjectOutputStream implements Serializable{

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<ProductoObjectOutputStream> prods = new ArrayList<>();

        ProductoObjectOutputStream raton = new ProductoObjectOutputStream(1, "raton", 3.99);
        ProductoObjectOutputStream teclado = new ProductoObjectOutputStream(2, "teclado", 15.99);
        ProductoObjectOutputStream monitor = new ProductoObjectOutputStream(3, "monitor", 180);

        prods.add(raton);
        prods.add(teclado);
        prods.add(monitor);

        grabarFicheroObjeto(prods);

        leeFicheroObjeto();
    }




    private static final String fichero = "C:\\Users\\Dani\\Desktop\\fichero productos.txt";
    private int cod;
    private String descr;
    private double prUnit;

    public ProductoObjectOutputStream(int cod, String descr, double prUnit) {
        this.cod = cod;
        this.descr = descr;
        this.prUnit = prUnit;
    }

    @Override
    public String toString() {
        return "ProductoObjectOutputStream{" +
                "cod=" + cod +
                ", descr='" + descr + '\'' +
                ", prUnit=" + prUnit +
                '}';
    }

    public int getCod() {
        return cod;
    }

    public String getDescr() {
        return descr;
    }

    public double getPrUnit() {
        return prUnit;
    }


    static void grabarFicheroObjeto(List<ProductoObjectOutputStream> listaProductos) {
        // GrabaFichObj: escribe objetos de la clase Producto en un fichero, uno tras otro
        try (FileOutputStream os = new FileOutputStream(fichero);
             ObjectOutputStream oos = new ObjectOutputStream(os)) {
            for (ProductoObjectOutputStream unProd : listaProductos) {
                oos.writeObject(unProd);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static void leeFicheroObjeto() throws IOException, ClassNotFoundException {

        // LeeFichObj: lee los objetos, uno tras otro, hasta EOFException
        try (FileInputStream is = new FileInputStream(fichero);
             ObjectInputStream ois = new ObjectInputStream(is)) {
            for (int i = 1; is.available() > 0; i++) {
                ProductoObjectOutputStream unProd = (ProductoObjectOutputStream) ois.readObject();
                System.out.println(unProd);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
