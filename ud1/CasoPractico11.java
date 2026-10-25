package ud1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
/*
* Partimos del siguiente programa, que muestra por defecto un listado de los ficheros y directorios que
contiene el directorio desde el que se ejecuta. Si se le pasa como argumento la ruta de un directorio o de un
fichero, muestra información acerca de él y, si se trata de un directorio, muestra los ficheros y directorios
que contiene:
* */

public class CasoPractico11 {
    public static void main(String[] args) {
        String ruta = "C:\\Users\\Dani\\Desktop\\DAM2\\Programacion Multimedia";
        File fich = new File(ruta);


        if (!fich.exists()) {
            System.out.printf("No existe (%s).", ruta);
            return;
        }
        if (fich.isFile()) {
            System.out.printf("%s es un fichero.\n", ruta);
        } else if (fich.isDirectory()) {
            System.out.printf("%s es un directorio. Contenidos:\n", ruta);
            System.out.printf("%-35s %15s %-7s %-19s %-10s","NOMBRE", "BYTES", "PERMISOS", "MODIFICADO", "AVISOS");
            System.out.println();
            File[] listaFicheros = fich.listFiles();
            if (listaFicheros == null){
                System.out.println("No se puede leer el contenido del directorio");
                return;
            }
            Arrays.sort(listaFicheros);
            for (File f : listaFicheros) {
                String avisos = "";
                String nombre = f.getName();
                String tamaño = "";
                if (f.isDirectory()) {
                    nombre += "/";
                    tamaño += "-";
                } else {
                    tamaño = String.valueOf(f.length());
                }
                if (inseguro(permisos(f))) avisos += "INSEGURO ";
                if (modificado(f)) avisos += "MOD. RECIENTE";

                System.out.printf("%-35s %15s %-7s %-19s %-10s", nombre, tamaño, permisos(f), fechaModificacion(f), avisos);
                System.out.println();
            }
        }


    }

    public static String permisos(File fich){
        String permiso = "";
            if (fich.canRead()) {
                permiso += "r";
            } else {
                permiso += "-";
            }
            if (fich.canWrite()) {
                permiso += "w";
            } else {
                permiso += "-";
            }
            if (fich.canExecute()) {
                permiso += "x";
            } else {
                permiso += "-";
            }
        return permiso;
        }


    public static String fechaModificacion(File fich){
        long modificado = fich.lastModified();
        Date fecha = new Date(modificado);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formato.format(fecha);
    }

    public static boolean modificado(File fich){
        boolean mod = false;
        long milisSemana = 7L * 24 * 60 * 60 * 1000;
        long ahora = System.currentTimeMillis();
        long diferencia = ahora - fich.lastModified();
        if (diferencia >= 0 && diferencia < milisSemana) mod = true;

        return mod;
    }

    public static boolean inseguro(String rwx){
        boolean inseguro = false;
        if((rwx.length() == 3) && (rwx.charAt(1) == 'w'))
            inseguro = true;
        return inseguro;
    }



    }

