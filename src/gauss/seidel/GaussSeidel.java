package gauss.seidel;

  public class GaussSeidel {
    public static void main(String[] args) {
        // Inicialización de variables
        double x1 = 0, x2 = 0, x3 = 0; // Aproximaciones iniciales
        double x1_new, x2_new, x3_new;
        double tolerancia = 0.0001; // Error permitido
        int maxIteraciones = 10;  // Límite de iteraciones
        int iteracion = 0;

        System.out.println("Método de Gauss-Seidel");

        do {
            // Cálculo de nuevas aproximaciones
            x1_new = (480 - 50 * x2 - 65 * x3) / 30;
            x2_new = (480 - 40 * x1_new - 40 * x3) / 50;
            x3_new = (480 - 50 * x1_new - 50 * x2_new) / 15;

            // Mostrar valores actuales
            System.out.printf("Iteración %d: x1 = %.5f, x2 = %.5f, x3 = %.5f%n",
                    iteracion + 1, x1_new, x2_new, x3_new);

            // Verificar si los resultados cumplen la tolerancia
            if (Math.abs(x1_new - x1) < tolerancia &&
                Math.abs(x2_new - x2) < tolerancia &&
                Math.abs(x3_new - x3) < tolerancia) {
                break;
            }

            // Actualizar valores para la siguiente iteración
            x1 = x1_new;
            x2 = x2_new;
            x3 = x3_new;

            iteracion++;
        } while (iteracion < maxIteraciones);

        System.out.println("\nSolución Final:");
        System.out.printf("x1 = %.5f, x2 = %.5f, x3 = %.5f%n", x1_new, x2_new, x3_new);
    }
}
