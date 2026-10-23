package ud1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermissions;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Caso práctico 1.1: informe de auditoría de un directorio.
 * Muestra, por cada elemento: nombre, tamaño en bytes, permisos (rwx)
 * y fecha de última modificación. Marca los permisos inseguros y las
 * modificaciones recientes.

 * Uso: java AuditoriaFicheros [ruta]   (por defecto, el directorio actual)
 */
public class AuditoriaFicheros {

    private static final long DIAS_RECIENTE = 7;
    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());

    public static void main(String[] args) {
        String ruta = args.length > 0 ? args[0] : ".";
        File fich = new File(ruta);

        if (!fich.exists()) {
            System.out.printf("No existe (%s).%n", ruta);
            return;
        }

        if (fich.isFile()) {
            System.out.printf("%s es un fichero.%n", ruta);
            cabecera();
            mostrar(fich);
        } else if (fich.isDirectory()) {
            System.out.printf("%s es un directorio. Informe de contenidos:%n", ruta);
            File[] contenido = fich.listFiles();
            if (contenido == null) {          // sin permiso de lectura o error de E/S
                System.out.println("No se puede leer el contenido del directorio.");
                return;
            }
            Arrays.sort(contenido);           // orden alfabético estable
            cabecera();
            for (File f : contenido) {
                mostrar(f);
            }
        }
    }

    private static void cabecera() {
        System.out.printf("%-30s %12s  %-10s %-19s  %s%n",
                "NOMBRE", "BYTES", "PERMISOS", "MODIFICADO", "AVISOS");
        System.out.println("-".repeat(90));
    }

    private static void mostrar(File f) {
        String nombre = f.getName() + (f.isDirectory() ? "/" : "");
        String tamano = f.isDirectory() ? "-" : String.valueOf(f.length());
        String rwx = permisos(f);
        String fecha = FORMATO.format(Instant.ofEpochMilli(f.lastModified()));

        StringBuilder avisos = new StringBuilder();
        if (esInseguro(rwx)) avisos.append("[INSEGURO] ");
        if (esReciente(f))   avisos.append("[RECIENTE]");

        System.out.printf("%-30s %12s  %-10s %-19s  %s%n",
                nombre, tamano, rwx, fecha, avisos);
    }

    /** Permisos en formato rwxr-xr-x (con 'd' delante si es directorio). */
    private static String permisos(File f) {
        String tipo = f.isDirectory() ? "d" : "-";
        try {
            // Sistemas POSIX (Linux, macOS): permisos de propietario, grupo y otros
            return tipo + PosixFilePermissions.toString(Files.getPosixFilePermissions(f.toPath()));
        } catch (UnsupportedOperationException | IOException e) {
            // Windows: solo se puede saber lo que puede hacer el usuario actual
            return tipo + (f.canRead() ? "r" : "-") + (f.canWrite() ? "w" : "-")
                        + (f.canExecute() ? "x" : "-") + "??????";
        }
    }

    /** Inseguro = cualquier usuario ("otros") puede escribir. */
    private static boolean esInseguro(String rwx) {
        return rwx.length() == 10 && rwx.charAt(8) == 'w';
    }

    private static boolean esReciente(File f) {
        long limite = System.currentTimeMillis() - DIAS_RECIENTE * 24L * 60 * 60 * 1000;
        return f.lastModified() >= limite;
    }
}
