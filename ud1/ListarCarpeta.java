package ud1;


import java.io.File;

public class ListarCarpeta {
    public static void main(String[] args) {
        File carpeta = new File(".");

        //Verificamos que la carpeta exista y sea un directorio
        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles();

            if (archivos != null){
                for (File f: archivos){
                    String tipo = f.isDirectory() ? "DIR " : "FILE";
                    System.out.printf("%s %-15s %d bytes%n", tipo, f.getName(), f.length());
                }
            }
        }
    }
}