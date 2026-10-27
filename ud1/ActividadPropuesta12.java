package ud1;

import java.io.*;

public class ActividadPropuesta12 {
    public static void main(String[] args) throws IOException {

        /*
        El siguiente programa crea una copia de un fichero, leyendo y escribiendo byte a byte:

        FileInputStream fis = new FileInputStream(nomFichOrig);
        FileOutputStream fos = new FileOutputStream(nomFichDest);
        int b;
        while ((b = fis.read()) != -1) {
         fos.write(b);
        }

        Cambia este ejemplo para que, en vez de leer y escribir byte a byte, lea y escriba por bloques (se recomienda
        utilizar un bucle do...while).
         */

//        File nomFichOrig = new File("C:\\Users\\Dani\\Desktop\\Nuevo Documento de texto.txt");
//
//        try (FileInputStream fis = new FileInputStream(nomFichOrig);
//             FileOutputStream fos = new FileOutputStream("C:\\Users\\Dani\\Desktop\\Nuevo Documento de texto2.txt")) {
//
//            int b;
//            while ((b = fis.read()) != -1) {
//                fos.write(b);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        File nomFichOrig = new File("C:\\Users\\Dani\\Desktop\\Nuevo Documento de texto.txt");

        try (FileInputStream fis = new FileInputStream(nomFichOrig);
             FileOutputStream fos = new FileOutputStream("C:\\Users\\Dani\\Desktop\\Nuevo Documento de texto2.txt")) {

            int bytesLeidos;
            byte[] buffer = new byte[16];

            do {
                bytesLeidos = fis.read(buffer);
                if (bytesLeidos > 0) {
                    fos.write(buffer, 0, bytesLeidos);
                }
            } while(bytesLeidos != -1);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



// Segunda Actividad
//        File nombreFile = new File("C:\\Users\\Dani\\Desktop\\Nuevo Documento de texto 3.txt");
//
//        try(FileInputStream fis = new FileInputStream(nombreFile)) {
//            int bitesLeidos;
//            byte[] buffer = new byte[16];
//
//            do {
//                bitesLeidos = fis.read(buffer);
//                if (bitesLeidos > 0) {
//                    for (int i = 0; i < bitesLeidos; i++) {
//                        System.out.printf("%02x ", buffer[i]);
//                    }
//                    System.out.println();
//                }
//            } while (bitesLeidos != -1);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//








    }
}
