package ud1.CasoPractico13Y14;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;

public class AccesoFicherosAleatorios {

    public static void main(String[] args) {
        List<Map.Entry<String, Integer>> campos = new ArrayList<>();
        campos.add(new SimpleEntry("DNI", 9));
        campos.add(new SimpleEntry("NOMBRE", 32));
        campos.add(new SimpleEntry("CP", 5));

        String ruta = "C:\\Users\\Dani\\Desktop\\acceso aleatorio datos.txt";

        try{
            AccesoFicherosAleatorios faa = new AccesoFicherosAleatorios(ruta, campos);


            // ---------- CASO PRÁCTICO 1.3 ----------
            System.out.println("=== CASO PRÁCTICO 1.3 ===");
            Map<String, String> reg = new HashMap<>();
            reg.put("DNI", "56789012B");
            reg.put("NOMBRE", "SAMPER");
            reg.put("CP", "29730");
            faa.insertar(reg); // posición 0
            reg.clear();

            reg.put("DNI", "89012345E");
            reg.put("NOMBRE", "ROJAS");
            reg.put("CP", "46001");
            faa.insertar(reg); // posición 1

            mostrarEstado(faa, ruta, "Tras insertar 2 registros");

            // Insertamos en la posición 5, aunque solo hay 2 registros (0 y 1)
            reg.clear();
            reg.put("DNI", "24589681S");
            reg.put("NOMBRE", "BUENO");
            reg.put("CP", "28044");
            faa.insertar(reg, 5);
            mostrarEstado(faa, ruta, "Tras insertar en la posición 5");
            // Volvemos a abrir el fichero: numReg se recalcula a partir del tamaño
            faa = new AccesoFicherosAleatorios(ruta, campos);
            mostrarEstado(faa, ruta, "Tras volver a abrir el fichero");

            // ---------- CASO PRÁCTICO 1.4 ----------
            System.out.println("\n=== CASO PRÁCTICO 1.4 ===");
            for (long i = 0; i < faa.getNumReg(); i++) {
                System.out.printf("Registro %d -> DNI: [%s] NOMBRE: [%s] CP: [%s]%n", i,
                        faa.obtenerValor(i, "DNI"),
                        faa.obtenerValor(i, "NOMBRE"),
                        faa.obtenerValor(i, "CP"));
            }
            System.out.println("NOMBRE del registro 5: " + faa.obtenerValor(5, "NOMBRE"));
            System.out.println("CP del registro 1: " + faa.obtenerValor(1, "cp"));
            // Casos de error
            try {
                faa.obtenerValor(1, "TELEFONO");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
            try {
                faa.obtenerValor(10, "DNI");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        }
        }







    private long numReg;
    private String ruta = "C:\\Users\\Dani\\Desktop\\acceso aleatorio datos.txt";

    private int longReg = 0;

    private List<Map.Entry<String, Integer>> campos;

    public AccesoFicherosAleatorios(String ruta, List<Map.Entry<String, Integer>> campos) {
        this.ruta = ruta;
        this.campos = campos;
        for (Map.Entry<String, Integer> campo : this.campos){
            longReg += campo.getValue();
        }
        File f = new File(ruta);
        numReg = f.length() / longReg;
    }

    public long getNumReg() {
        return numReg;
    }

    public void insertar(Map<String, String> reg) throws IOException {
        insertar(reg, this.numReg);
        this.numReg++;
    }

    public void insertar(Map<String, String> reg, long pos) throws IOException {
        try (RandomAccessFile faa = new RandomAccessFile(ruta, "rws")) {

            faa.seek(pos * this.longReg);
            for (Map.Entry<String, Integer> campo : this.campos) {
                String nomCampo = campo.getKey();
                Integer longCampo = campo.getValue();
                String valorCampo = reg.get(nomCampo);

                if (valorCampo == null) valorCampo = "";

                String valorCampoForm = String.format("%1$-" + longCampo + "s", valorCampo);
                faa.write(valorCampoForm.getBytes("UTF-8"), 0, longCampo);
            }
        }
    }

    /** Muestra el tamaño del fichero y el número de registros (caso 1.3). */
    static void mostrarEstado(AccesoFicherosAleatorios faa, String nomFich, String titulo) {
        File fich = new File(nomFich);
        System.out.printf("%s: tamaño = %d bytes (%d registros de 46 bytes) | numReg = %d%n",
                titulo, fich.length(), fich.length() / 46, faa.getNumReg());
    }

//    public String obtenerValorCampo(long pos, String nomCampo) throws IOException {
//        long desplazamiento = 0;
//        int longCampo = 0;
//        boolean existe = false;
//
//        for (Map.Entry<String, Integer> campo : campos) {
//            if (campo.getKey().equalsIgnoreCase(nomCampo)) {
//                longCampo = campo.getValue();
//                existe = true;
//                break;
//            }
//            desplazamiento += campo.getValue();
//        }
//
//        if (!existe) {
//            throw new IllegalArgumentException("El campo no existe: " + nomCampo);
//        }
//
//        if (pos < 0 || pos >= numReg) {
//            throw new IllegalArgumentException("La posición no existe: " + pos);
//        }
//
//        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {
//            raf.seek(pos * longReg + desplazamiento);
//
//            byte[] datos = new byte[longCampo];
//            raf.readFully(datos);
//
//            return new String(datos, "UTF-8").trim();
//        }
//    }


    public String obtenerValor(long pos, String nomCampo) {
        boolean existe = false;
        long desplazamiento = 0;
        int longCampo = 0;
        String lecturaDato = "";
        for (Map.Entry<String, Integer> campo: campos){
            if (campo.getKey().equalsIgnoreCase(nomCampo)){
                existe = true;
                longCampo = campo.getValue();
                break;
            } else {
                desplazamiento += campo.getValue();
            }
        }
        if (!existe){
            throw new IllegalArgumentException("El campo no existe: " + nomCampo);
        }

        long posicion = (pos * longReg) + desplazamiento;

        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")){
            raf.seek(posicion);
            
            byte[] dato = new byte[longCampo];
            raf.readFully(dato);
            
            lecturaDato = new String(dato, "UTF-8").trim();


        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        return lecturaDato;
    }


}