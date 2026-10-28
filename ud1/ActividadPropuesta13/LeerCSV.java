package ud1.ActividadPropuesta13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeerCSV {
    public static void main(String[] args) {
        String ruta = "C:\\Users\\Dani\\Desktop\\lista empleados.txt";

        lecturaCSV(ruta);
    }

    static void lecturaCSV(String ruta) {
        List<Empleado> listEmp = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea = "";
            String separador = ";";

            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(separador, -1);

                if (campos.length != 5) {
                    System.out.println("Número de campos inesperado en la linea");
                    continue;
                } else {
                    Integer numEmp = (!campos[0].isEmpty()) ? Integer.parseInt(campos[0]) : null;
                    String DNI;

                    if (campos[1].isEmpty()) {
                        System.out.println("El campo DNI no puede estar vacío");
                        continue;
                    } else {
                        DNI = campos[1];
                    }
                    String nombre;
                    if (campos[2].isEmpty()) {
                        System.out.println("El campo nombre no puede estar vacío");
                        continue;
                    } else {
                        nombre = campos[2];
                    }

                    Double salario = (!campos[3].isEmpty()) ? Double.parseDouble(campos[3]) : null;
                    Boolean parcial;
                    if (campos[4].equals("1")) {
                        parcial = true;
                    } else if (campos[4].equals("0")) {
                        parcial = false;
                    } else {
                        parcial = null;
                    }

                    Empleado emp = new Empleado(numEmp, DNI, nombre, salario, parcial);
                    System.out.println(emp);
                    listEmp.add(emp);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
