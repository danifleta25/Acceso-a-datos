package ud1.ActividadPropuesta13;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Empleado {
    public static void main(String[] args) throws IOException {
        //String ruta = "C:\\Users\\Dani\\Desktop\\lista empleados.txt";
        List<Empleado> listaEmp = new ArrayList<>();

        Empleado jose = new Empleado(1, "53256987T", "Jose", 1450.50, true);
        Empleado maria = new Empleado(2, "9846984654G", "Maria", 2450.50, false);
        Empleado pepe = new Empleado(3, "98465135E", "Pepe", 1850.50, false);

        listaEmp.add(jose);
        listaEmp.add(maria);
        listaEmp.add(pepe);

        escribirCSV(listaEmp);
    }

    private Integer numEmp;
    private String DNI;
    private String nombre;
    private Double salario;
    private Boolean parcial;


    public Empleado(Integer numEmp, String DNI, String nombre, Double salario, Boolean parcial) {
        this.numEmp = numEmp;
        this.DNI = DNI;
        this.nombre = nombre;
        this.salario = salario;
        this.parcial = parcial;
    }

    public Integer getNumEmp() {
        return numEmp;
    }

    public String getDNI() {
        return DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getSalario() {
        return salario;
    }

    public Boolean getParcial() {
        return parcial;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "numEmp=" + numEmp +
                ", DNI='" + DNI + '\'' +
                ", nombre='" + nombre + '\'' +
                ", salario=" + salario +
                ", parcial=" + parcial +
                '}';
    }

    static void escribirCSV(List<Empleado> listaEmpleados) {
        String separador = ";";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Dani\\Desktop\\lista empleados.txt"))){
            if (!listaEmpleados.isEmpty()) {
                for (Empleado emp : listaEmpleados) {
                    bw.write((emp.getNumEmp() != null) ? emp.getNumEmp().toString() : ""); bw.write(separador);

                    bw.write((emp.getDNI() != null) ? emp.getDNI() : ""); bw.write(separador);

                    bw.write((emp.getNombre() != null) ? emp.getNombre() : ""); bw.write(separador);

                    bw.write((emp.getSalario() != null) ? emp.getSalario().toString() : ""); bw.write(separador);

                    if (emp.getParcial() == null){
                        bw.write("");
                    } else {
                        bw.write((emp.getParcial() == true) ? "1" : "0");
                    }
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }







}
