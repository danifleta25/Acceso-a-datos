package ud1;

public class ActividadPropuesta11 {
    public static void main(String[] args) {

        /*
        Partimos del siguiente fragmento, en el que cada división se controla con su propio bloque try...catch:
        Modifica el programa de ejemplo para que las tres operaciones de división se ejecuten dentro de un único
        bloque try...catch. Reflexiona sobre el resultado: ¿qué diferencias encuentras respecto a tener un bloque de
        gestión de excepciones independiente para cada división?


        int a = 5, b = 2;
        try {
            System.out.printf("%d/%d=%d\n", a, b, a / b);
        } catch (ArithmeticException e) {
            System.out.printf("Error al dividir %d/%d\n", a, b);
        }
        */

// ... (bloques try/catch análogos para b=0 y b=3)
// Salida:
// 5/2=2
// Error al dividir: 5/0
// 5/3=1
        int a = 5, b = 2;
        try {
            System.out.printf("%d / %d = %d\n", a, b, a / b);
            b = 0;
            System.out.printf("%d / %d = %d\n", a, b, a / b);
            b = 3;
            System.out.printf("%d / %d = %d\n", a, b, a / b);
        } catch (ArithmeticException e) {
            System.out.printf("Error al dividir %d / %d\n", a, b);
        }
    }
}
