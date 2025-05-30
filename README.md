## Introduccion 
**Definicion**
Los métodos numéricos son un conjunto de técnicas matemáticas que se utilizan para resolver 
problemas en los que se requiere obtener soluciones aproximadas o numéricas, 
especialmente cuando no es posible o práctico encontrar una solución 
exacta mediante métodos analíticos. 


Los métodos numéricos sirven para resolver una variedad de problemas en los que es 
necesario obtener soluciones aproximadas mediante cálculos numéricos. 


## Tema 2
- [Biseccion](https://github.com/housemarline00/MN-netbeans/blob/acbb1ba2631d08b2a81e281058b7d6ab28c46aa3/Tema%202)
# Método de bisección:
El método de bisección es un algoritmo utilizado para encontrar las raíces de una función continua en un intervalo dado. 
Este método es simple pero efectivo y se basa en el teorema del valor intermedio.El método de bisección garantiza la convergencia
a una raíz debido a la propiedad de que en cada iteración se reduce a la mitad la longitud del intervalo que contiene la raíz. Sin 
embargo, puede ser relativamente lento en comparación con otros métodos de búsqueda de raíces, especialmente para funciones con 
comportamientos complicados. 

# Algoritmo
Método_de_Bisección:
  Descripción: |
    El Método de Bisección es un algoritmo para encontrar la raíz de una función continua en un intervalo dado. 
    Se basa en el Teorema del Valor Intermedio, dividiendo repetidamente el intervalo a la mitad y seleccionando 
    el subintervalo que sigue cumpliendo con el teorema.
  
  Parámetros:
    - Función: "La función f(x) para la cual se quiere encontrar la raíz."
    - Intervalo: "El intervalo [a, b] donde se buscará la raíz, donde f(a) y f(b) tienen signos opuestos."
    - Tolerancia: "La precisión deseada para la raíz."
    - Iteraciones_máximas: "El número máximo de iteraciones permitidas para evitar bucles infinitos."

  Paso_a_Paso:
    - Inicializar a = extremo inferior del intervalo, b = extremo superior del intervalo.
    - Para cada iteración:
        - Calcular el punto medio c = (a + b) / 2.
        - Evaluar f(c).
        - Si f(c) es cercano a 0 (dentro de la tolerancia), c es la raíz y se termina el algoritmo.
        - Si f(c) y f(a) tienen signos opuestos, establecer b = c.
        - Si f(c) y f(b) tienen signos opuestos, establecer a = c.
        - Repetir hasta que se cumpla la condición de convergencia o se alcance el número máximo de iteraciones.

 


# IMPLEMENTACIÓN EN JAVA

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Scanner;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        // Crear un objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la función como una expresión válida de Java:");
        System.out.println("Por ejemplo, para x^2 - 4, ingrese 'x * x - 4'");
        String functionString = scanner.nextLine();

        // Definir la función a partir de la cadena ingresada por el usuario
        Function<Double, Double> function = buildFunction(functionString);

        System.out.println("Ingrese el límite inferior del intervalo:");
        double a = scanner.nextDouble();

        System.out.println("Ingrese el límite superior del intervalo:");
        double b = scanner.nextDouble();

        System.out.println("Ingrese la precisión deseada (epsilon):");
        double epsilon = scanner.nextDouble();

        // Calcular la raíz utilizando el método de bisección
        double root = bisection(function, a, b, epsilon);

        // Imprimir el resultado
        System.out.println("La raíz aproximada es: " + root);

        // Cerrar el scanner para evitar fugas de recursos
        scanner.close();
    }

    public static Function<Double, Double> buildFunction(String functionString) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        return x -> {
            try {
                engine.put("x", x);
                return Double.parseDouble(engine.eval(functionString).toString());
            } catch (ScriptException e) {
                throw new IllegalArgumentException("La función ingresada no es válida.");
            }
        };
    }

    public static double bisection(Function<Double, Double> function, double a, double b, double epsilon) {
        // Verificar si los límites del intervalo encierran una raíz
        if (function.apply(a) * function.apply(b) > 0) {
            throw new IllegalArgumentException("La función no cambia de signo en el intervalo dado.");
        }

        // Implementación del método de bisección
        while ((b - a) >= epsilon) {
            // Encontrar el punto medio del intervalo
            double c = (a + b) / 2;
            // Calcular el valor de la función en el punto medio
            double fc = function.apply(c);
            // Si el valor de la función en el punto medio es cercano a cero, hemos encontrado una buena aproximación
            if (Math.abs(fc) < epsilon) {
                return c; // Devolver la raíz aproximada
            }
            // Actualizar los límites del intervalo [a, b] según el cambio de signo
            if (fc * function.apply(a) < 0) {
                b = c; // Si hay cambio de signo entre a y c, actualizar b
            } else {
                a = c; // Si hay cambio de signo entre b y c, actualizar a
            }
        }
        // Devolver el punto medio del intervalo como la mejor aproximación encontrada
        return (a + b) / 2;
    }
}



- [Regla falsa](https://github.com/housemarline00/MN-netbeans/blob/1e33a171a82eea7398c15235ea6d043a5123bc55/Regla%20Falsa)
  
## Regla Falsa:
El método de la regla falsa, también conocido como regula falso o falsa posición, es un método iterativo para
resolver numéricamente ecuaciones no lineales. Este método combina el método de bisección y el método de la secante. 

# Algoritmo


# IMPLEMENTACIÓN EN JAVA
import java.util.Scanner;
import java.util.function.Function;

public class Main { 
    
    public static double reglaFalsa(Function<Double, Double> funcion, double a, double b, double epsilon) {
        double c = 0;
        double fa = funcion.apply(a);
        double fb = funcion.apply(b);
        
        if (fa * fb >= 0) {
            throw new IllegalArgumentException("La función no cambia de signo en el intervalo dado.");
        }
        
        while (Math.abs(b - a) > epsilon) {
            c = (a * fb - b * fa) / (fb - fa);
            double fc = funcion.apply(c);
            
            if (fa * fc < 0) {
                b = c;
                fb = fc;
            } else {
                a = c;
                fa = fc;
            }
        }
        
        return c;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Ingrese la ecuación en términos de 'x', por ejemplo: 'x^3 - 2*x - 5'");
        String ecuacion = scanner.nextLine();
        Function<Double, Double> funcion = x -> evaluarFuncion(ecuacion, x);
        
        System.out.println("Ingrese el extremo izquierdo del intervalo:");
        double a = scanner.nextDouble();
        
        System.out.println("Ingrese el extremo derecho del intervalo:");
        double b = scanner.nextDouble();
        
        System.out.println("Ingrese la tolerancia de error:");
        double epsilon = scanner.nextDouble();
        
        double resultado = reglaFalsa(funcion, a, b, epsilon);
        System.out.println("La raíz aproximada es: " + resultado);
        
        scanner.close();
    }
    
    public static double evaluarFuncion(String ecuacion, double x) {
        // Esta función evalúa la ecuación ingresada en 'x'
        // Aquí puedes implementar tu propio parser para evaluar la ecuación,
        // pero por simplicidad, vamos a usar la biblioteca ScriptEngine de Java
        javax.script.ScriptEngineManager manager = new javax.script.ScriptEngineManager();
        javax.script.ScriptEngine engine = manager.getEngineByName("js");
        try {
            return (double) engine.eval(ecuacion.replace("x", String.valueOf(x)));
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        }
    }
}
--------------------------------------------
- [Newton](https://github.com/housemarline00/MN-netbeans/blob/1174e1dd911dc9e63402ade51bbc019407a88564/M%C3%A9todo%20de%20Newton)
- ## Método de Newton:
El Método de Newton, también conocido como Método de Newton-Raphson, es un algoritmo iterativo para encontrar raíces de una función no lineal. 
El método es especialmente útil cuando se necesita una aproximación numérica rápida y precisa de las raíces de una función.

# Algoritmo

-----------------------------------------------------------------------------------------------

# IMPLEMENTACIÓN EN JAVA-----
import java.util.Scanner;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la función f(x):");
        String funcionStr = scanner.nextLine();
        Function<Double, Double> funcion = x -> evaluarFuncion(funcionStr, x);

        System.out.println("Ingrese la estimación inicial para la raíz:");
        double estimacionInicial = scanner.nextDouble();

        System.out.println("Ingrese la tolerancia:");
        double tolerancia = scanner.nextDouble();

        double x = estimacionInicial;
        int iteracion = 0;

        // Definir el número máximo de iteraciones permitidas
        int maxIteraciones = 1000;

        while (true) {
            // Calcular el valor de la función y su derivada en x
            double fx = funcion.apply(x);
            double fpx = calcularDerivada(funcion, x);

            // Calcular la nueva estimación
            double nuevaEstimacion = x - (fx / fpx);

            // Verificar convergencia
            if (Math.abs(nuevaEstimacion - x) < tolerancia || iteracion >= maxIteraciones) {
                System.out.println("La raíz aproximada es: " + nuevaEstimacion);
                break;
            }

            // Actualizar x para la próxima iteración
            x = nuevaEstimacion;
            iteracion++;
        }

        scanner.close();
    }

    // Método para evaluar la función en un punto dado
    private static double evaluarFuncion(String funcionStr, double x) {
        return evaluarExpresion(funcionStr, x);
    }

    // Método para calcular la derivada numérica de la función en un punto dado
    private static double calcularDerivada(Function<Double, Double> funcion, double x) {
        double h = 0.0001; // Tamaño del paso para la aproximación numérica de la derivada
        return (funcion.apply(x + h) - funcion.apply(x)) / h;
    }

    // Método para evaluar una expresión matemática en un punto dado
    private static double evaluarExpresion(String expresionStr, double x) {
        // Convertir la expresión en una cadena con el valor de x sustituido
        String evaluada = expresionStr.replaceAll("x", Double.toString(x));

        // Evaluar la expresión y devolver el resultado
        return evaluar(evaluada);
    }

    // Método para evaluar una expresión matemática
    private static double evaluar(String expresionStr) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < expresionStr.length()) ? expresionStr.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expresionStr.length()) throw new RuntimeException("Carácter inesperado: " + (char)ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // suma
                    else if (eat('-')) x -= parseTerm(); // resta
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplicación
                    else if (eat('/')) x /= parseFactor(); // división
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unario más
                if (eat('-')) return -parseFactor(); // unario menos

                double x;
                int startPos = this.pos;
                if (eat('(')) { // paréntesis
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // números
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expresionStr.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // funciones y variables
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = expresionStr.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else throw new RuntimeException("Función desconocida: " + func);
                } else {
                    throw new RuntimeException("Carácter inesperado: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponente

                return x;
            }
        }.parse();
    }
}
-------------------------

- [Secante](https://github.com/housemarline00/MN-netbeans/blob/f83091b33214d39277bf5ce17d90a2a2fb254c51/M%C3%A9todo%20de%20Secante)
- ## Método de Secante:
El método de la secante es un algoritmo de investigación de raíces que utiliza una serie de raíces de líneas secantes para 
aproximar mejor la raíz de una función. Es una variación del método de Newton-Raphson.

# Algoritmo
----------------------------------------------------------------------------------------------------------------------

# IMPLEMENTACIÓN EN JAVA
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Scanner;

public class Ejercicio1 {
    public static double findRoot(String functionExpression, double x0, double x1, double tolerance) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        
        String function = "function f(x) { return " + functionExpression + "; }";
        engine.eval(function);
        
        double x2 = 0;
        double f0 = (double) engine.eval("f(" + x0 + ")");
        double f1 = (double) engine.eval("f(" + x1 + ")");
        int iterations = 0;
        
        do {
            x2 = x1 - ((f1 * (x1 - x0)) / (f1 - f0));
            f0 = f1;
            f1 = (double) engine.eval("f(" + x2 + ")");
            x0 = x1;
            x1 = x2;
            iterations++;
        } while (Math.abs(f1) > tolerance);
        
        return x2;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Pedir al usuario que ingrese la función
        System.out.println("Ingrese la función en términos de 'x', por ejemplo, 'Math.cos(x) - x':");
        String functionExpression = scanner.nextLine();
        
        // Pedir al usuario que ingrese los puntos iniciales
        System.out.println("Ingrese el punto inicial x0:");
        double x0 = scanner.nextDouble();
        System.out.println("Ingrese el punto inicial x1:");
        double x1 = scanner.nextDouble();
        
        // Pedir al usuario que ingrese la tolerancia
        System.out.println("Ingrese la tolerancia:");
        double tolerance = scanner.nextDouble();
        
        try {
            // Encontrar la raíz
            double root = findRoot(functionExpression, x0, x1, tolerance);
            
            // Imprimir el resultado
            System.out.println("Raíz encontrada: " + root);
        } catch (ScriptException e) {
            System.out.println("La función ingresada no es válida.");
        }
    }
}

<br>


## Tema 3

- [Método de Gauss-Seidel](https://github.com/housemarline00/MN-netbeans/blob/8eabc78fb6f522821dcafc47c1849b639c7ed1c3/Tema%203)
  ## Sistema de ecuaciones lineales
Un sistema de ecuaciones lineales es un conjunto de ecuaciones de primer grado que relacionan dos o más incógnitas. 
Las incógnitas aparecen en varias de las ecuaciones, pero no necesariamente en todas.

La importancia de los sistemas de ecuaciones lineales radica en que proporcionan un marco fundamental para resolver una amplia 
variedad de problemas prácticos y teóricos en diversos campos, incluyendo matemáticas, ciencias aplicadas, ingeniería y tecnología. 
Estos sistemas son esenciales para modelar fenómenos del mundo real, optimizar procesos, diseñar sistemas complejos y resolver 
problemas de manera eficiente. Además, los sistemas de ecuaciones lineales son la base para el estudio de conceptos algebraicos 
avanzados y proporcionan una herramienta fundamental en el desarrollo de tecnologías innovadoras. Su comprensión es crucial 
para abordar problemas complejos en numerosos campos de estudio y aplicación.

# Método de Gauss-Seidel:
En el método de Gauss-Seidel se propone ir sustituyendo los nuevos valores de la aproximación siguiente conforme 
se vayan obteniendo sin esperar a tener un vector completo. De esta forma se acelera la convergencia.

# Algoritmo //Método de Gauss-Seidel
Algoritmo_de_Gauss_Seidel:
  Descripción: >
    Método iterativo para resolver sistemas de ecuaciones lineales aproximadamente.
    Se basa en la actualización sucesiva de las incógnitas en cada iteración.
  Pasos:
    - Paso_1: >
        Inicializar un vector x^(0) con valores iniciales arbitrarios o calculados.
    - Paso_2: >
        Para cada i-ésima ecuación del sistema, calcular x_i^(k+1) usando la fórmula:
        x_i^(k+1) = (1/a_{ii}) * (b_i - ∑_{j=1, j ≠ i}^{n} a_{ij} * x_j^(k))
    - Paso_3: >
        Repetir el Paso 2 hasta que la diferencia entre dos iteraciones sucesivas sea menor que una cierta tolerancia
        predefinida o hasta alcanzar un número máximo de iteraciones.
  Convergencia: >
    La convergencia está garantizada si la matriz A es diagonalmente dominante o simétrica definida positiva.
    La velocidad de convergencia puede variar dependiendo de la naturaleza del sistema.
-------------------------------------------------------------------------------------------------------------

# IMPLEMENTACIÓN EN JAVA
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedir el tamaño de la matriz
        System.out.print("Ingrese el tamaño de la matriz cuadrada (n): ");
        int n = scanner.nextInt();

        // Inicializar la matriz A y el vector b
        double[][] A = new double[n][n];
        double[] b = new double[n];

        // Pedir los elementos de la matriz A
        System.out.println("Ingrese los elementos de la matriz A:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("A[%d][%d]: ", i, j);
                A[i][j] = scanner.nextDouble();
            }
        }

        // Pedir los elementos del vector b
        System.out.println("Ingrese los elementos del vector b:");
        for (int i = 0; i < n; i++) {
            System.out.printf("b[%d]: ", i);
            b[i] = scanner.nextDouble();
        }

        // Resolver el sistema de ecuaciones utilizando Gauss-Seidel
        double[] x = gaussSeidel(A, b);

        // Mostrar el resultado
        System.out.println("El resultado es:");
        for (int i = 0; i < n; i++) {
            System.out.printf("x[%d] = %.4f\n", i, x[i]);
        }

        scanner.close();
    }

    public static double[] gaussSeidel(double[][] A, double[] b) {
        int n = b.length;
        double[] x = new double[n];
        double[] xPrev = new double[n];

        // Inicializar x con valores arbitrarios o cero
        for (int i = 0; i < n; i++) {
            x[i] = 0; // Se pueden utilizar otros valores iniciales también
        }

        // Iterar hasta que la diferencia entre las soluciones sea muy pequeña
        while (true) {
            // Copiar los valores de la iteración anterior
            System.arraycopy(x, 0, xPrev, 0, n);

            // Calcular la nueva aproximación para cada variable
            for (int i = 0; i < n; i++) {
                double sum = 0;
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum += A[i][j] * x[j];
                    }
                }
                x[i] = (b[i] - sum) / A[i][i];
            }

            // Verificar si la diferencia entre las soluciones es muy pequeña
            double maxDiff = 0;
            for (int i = 0; i < n; i++) {
                double diff = Math.abs(x[i] - xPrev[i]);
                if (diff > maxDiff) {
                    maxDiff = diff;
                }
            }
            if (maxDiff < 0.0001) { // Cambiar este valor según la precisión deseada
                break;
            }
        }

        return x;
    }
}
-----------------------------------------
- [Eliminación Gaussiana](https://github.com/housemarline00/MN-netbeans/blob/40f365f85dba7d4d89b7e3da1f8bdeaf7d0c4bb5/Eliminaci%C3%B3n%20Gaussiana)
- ## Eliminación Gaussiana:
La Eliminación Gaussiana, también conocida como el método de eliminación de Gauss, es un algoritmo utilizado 
para resolver sistemas de ecuaciones lineales. El objetivo de este método es transformar un sistema de ecuaciones
lineales en otro equivalente pero más simple, hasta llegar a un sistema triangular que puede ser fácilmente resuelto 
mediante sustitución hacia atrás.

# Algoritmo//Eliminación Gaussiana
Entrada: Una matriz A de coeficientes de tamaño n x n y un vector b de tamaño n (sistema de ecuaciones lineales Ax = b)
Salida: La solución del sistema de ecuaciones lineales

1. Para k desde 1 hasta n-1 hacer:
   a. Para i desde k+1 hasta n hacer:
      i. Calcular el factor multiplicativo m = A[i][k] / A[k][k]
      ii. Para j desde k+1 hasta n hacer:
          1. A[i][j] = A[i][j] - m * A[k][j]
      iii. b[i] = b[i] - m * b[k]
      
2. Resolución hacia atrás:
   a. Para i desde n hasta 1, decrementando:
      i. Inicializar sum = 0
      ii. Para j desde i+1 hasta n hacer:
          1. sum = sum + A[i][j] * x[j]
      iii. x[i] = (b[i] - sum) / A[i][i]

3. Devolver el vector solución x
--------------------------------------------------------------------------------------------------------------------------

# IMPLEMENTACIÓN EN JAVA
import java.util.Scanner;
public class GaussianElimination {
    
    public static double[] solve(double[][] A, double[] b) {
        int n = A.length;
        // Eliminación hacia adelante
        for (int k = 0; k < n-1; k++) {
            for (int i = k+1; i < n; i++) {
                double factor = A[i][k] / A[k][k];
                for (int j = k+1; j < n; j++) {
                    A[i][j] -= factor * A[k][j];
                }
                b[i] -= factor * b[k];
            }
        }
        // Sustitución hacia atrás
        double[] x = new double[n];
        for (int i = n-1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i+1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        return x;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese el tamaño de la matriz cuadrada: ");
        int n = scanner.nextInt();
        
        double[][] A = new double[n][n];
        double[] b = new double[n];
        
        System.out.println("Ingrese los elementos de la matriz A:");
        for (int i = 0; i < n; i++) {
            System.out.println("Fila " + (i+1) + ":");
            for (int j = 0; j < n; j++) {
                System.out.print("A[" + i + "][" + j + "] = ");
                A[i][j] = scanner.nextDouble();
            }
        }
        
        System.out.println("Ingrese los elementos del vector b:");
        for (int i = 0; i < n; i++) {
            System.out.print("b[" + i + "] = ");
            b[i] = scanner.nextDouble();
        }
        
        double[] x = solve(A, b);
        
        System.out.println("Solución del sistema de ecuaciones:");
        for (int i = 0; i < x.length; i++) {
            System.out.println("x[" + i + "] = " + x[i]);
        }
        scanner.close();
    }
}
-------------------------------------------------
[Gauss-Jordan](https://github.com/housemarline00/MN-netbeans/blob/e600275c0830994addbd0fb0715503d291e55c0e/Gauss-Jordan)
## Gauss-Jordan:
El método de eliminación Gauss-Jordan consiste en representar el sistema de ecuaciones por medio de una matriz y obtener a partir de ella 
lo que se define como la matriz escalonada equivalente, a través de la cual se determina el tipo de solución de la ecuación.

# Algoritmo //Método de Gauss-Jordan
Entrada: Una matriz aumentada A (m x n)
Salida: La matriz A en su forma escalonada reducida por filas

Para k = 1 hasta m hacer:
    Para i = 1 hasta m hacer:
        Si i ≠ k entonces:
            Multiplica la fila k por -A[i,k]/A[k,k] y súmala a la fila i de A
            
    Divide la fila k por A[k,k]

    Para j = 1 hasta n hacer:
        Si j ≠ k entonces:
            Multiplica la columna k por -A[k,j]/A[k,k] y súmala a la columna j de A
-------------------------------------------------------------------------------------------------------

# IMPLEMENTACIÓN EN JAVA
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese el tamaño de la matriz cuadrada: ");
        int size = scanner.nextInt();
        
        double[][] matrix = new double[size][size + 1]; // Matriz aumentada
        
        System.out.println("Ingrese los elementos de la matriz aumentada fila por fila:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size + 1; j++) {
                System.out.print("Elemento [" + (i + 1) + "," + (j + 1) + "]: ");
                matrix[i][j] = scanner.nextDouble();
            }
        }
        
        gaussJordan(matrix);
        
        System.out.println("La matriz en su forma escalonada reducida por filas es:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size + 1; j++) {
                if (Math.abs(matrix[i][j] - (int)matrix[i][j]) < 1e-6) {
                    System.out.print((int)matrix[i][j] + "\t");
                } else {
                    System.out.print(matrix[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }
    
    public static void gaussJordan(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        for (int k = 0; k < rows; k++) {
            // Pivoteo parcial
            int maxRow = k;
            for (int i = k + 1; i < rows; i++) {
                if (Math.abs(matrix[i][k]) > Math.abs(matrix[maxRow][k])) {
                    maxRow = i;
                }
            }
            double[] temp = matrix[k];
            matrix[k] = matrix[maxRow];
            matrix[maxRow] = temp;
            
            // Reducción
            for (int i = 0; i < rows; i++) {
                if (i != k) {
                    double factor = matrix[i][k] / matrix[k][k];
                    for (int j = k; j < cols; j++) {
                        matrix[i][j] -= factor * matrix[k][j];
                    }
                }
            }
            
            // Normalización
            double divisor = matrix[k][k];
            for (int j = k; j < cols; j++) {
                matrix[k][j] /= divisor;
            }
        }
    }
}
-----------------------------------------------------------

<br>

## Tema 4

- [Metodo de Trapecio](https://github.com/housemarline00/MN-netbeans/blob/8eabc78fb6f522821dcafc47c1849b639c7ed1c3/Tema%204)
- ## Método de trapecio---------------------
El método del trapecio es un método numérico para calcular aproximaciones numéricas de integrales definidas. La técnica consiste en dividir el intervalo total 
en intervalos pequeños y aproximar la curva Y = f(X) en los diversos intervalos pequeños mediante alguna curva más simple cuya integral puede calcularse utilizando
solamente las ordenadas de los puntos extremos de los intervalos. 
--------------------------------------------------------------------

# Algoritmo---------------------------
Método_del_Trapecio:
  Descripción: 
    El Método del Trapecio es una técnica de integración numérica que aproxima el valor de una integral definida mediante la suma de áreas de trapecios formados
debajo de la curva de la función.
  Paso_a_Paso:
    - Paso_1: 
        Definir la función f(x) que se desea integrar en el intervalo [a, b].
    - Paso_2: 
        Especificar el límite inferior 'a' y el límite superior 'b' del intervalo de integración.
    - Paso_3: 
        Especificar el número de subintervalos 'n' en los que se dividirá el intervalo [a, b]. Cuanto mayor sea 'n', más precisa será la aproximación.
    - Paso_4: 
        Calcular el ancho de cada subintervalo h = (b - a) / n.
    - Paso_5: 
        Calcular los valores de la función en los extremos de los subintervalos: f(a), f(a + h), f(a + 2h), ..., f(b).
    - Paso_6: 
        Sumar todas las áreas de los trapecios formados por los subintervalos utilizando la fórmula:
        Área = h * (f(a) + 2 * f(a + h) + 2 * f(a + 2h) + ... + f(b)) / 2
    - Paso_7: 
        El resultado de la integral aproximada es la suma de todas las áreas calculadas en el paso anterior.
------------------------------------------------------------------------------------------------------------------

# IMPLEMENTACIÓN EN JAVA-------------------
package metodotrapecio;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Scanner;
import java.util.function.Function;

public class MetodoTrapecio {

    public static double metodoTrapecio(Function<Double, Double> f, double a, double b, int n) {
        double h = (b - a) / n;
        double suma = 0.5 * (f.apply(a) + f.apply(b)); // Sumamos los extremos

        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            suma += f.apply(x);
        }
 
        return h * suma;
    }

    public static double richardsonExtrapolation(Function<Double, Double> f, double a, double b, int n) {
        double h = (b - a) / n;
        double hBy2 = h / 2;
        double suma1 = 0.5 * (f.apply(a) + f.apply(b));
        double suma2 = 0.0;

        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            suma2 += f.apply(x + hBy2);
        }

        double resultN = h * (suma1 + suma2);
        double resultNBy4 = (4.0 * metodoTrapecio(f, a, b, n * 2) - metodoTrapecio(f, a, b, n)) / 3.0;
        return resultNBy4;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la función f(x):");
        String funcionStr = scanner.nextLine();

        Function<Double, Double> funcion = x -> {
            // Evaluamos la función para un valor específico de x
            return evaluarFuncion(funcionStr, x);
        };

        System.out.println("Ingrese el límite inferior 'a' de la integral:");
        double a = scanner.nextDouble();

        System.out.println("Ingrese el límite superior 'b' de la integral:");
        double b = scanner.nextDouble();

        System.out.println("Ingrese el número inicial de subintervalos 'n':");
        int n = scanner.nextInt();

        double resultado = richardsonExtrapolation(funcion, a, b, n);
        System.out.println("El resultado de la integral aproximada es: " + resultado);

        scanner.close();
    }

    // Función para evaluar una expresión matemática dada una cadena y un valor de x
    private static double evaluarFuncion(String funcionStr, double x) {
        // Remplazamos x en la función y evaluamos el resultado
        return evaluarExpresion(funcionStr.replace("x", Double.toString(x)));
    }

    // Función para evaluar una expresión matemática dada una cadena
    private static double evaluarExpresion(String expresion) {
        try {
            // Creamos un motor de script
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");

            // Evaluamos la expresión
            Object result = engine.eval(expresion);
            return Double.parseDouble(result.toString());
        } catch (ScriptException e) {
            // Manejamos cualquier error de evaluación de la expresión
            System.out.println("Error al evaluar la función: " + e.getMessage());
            return 0.0;
        }
    }
}
---------------------------------------------------------------------------------------------------------

----------Salida en pantalla ------------------------
Ingrese la función f(x):
4*x/(8*x*x)
Ingrese el límite inferior 'a' de la integral:
3
Ingrese el límite superior 'b' de la integral:
5
Ingrese el número inicial de subintervalos 'n':
100
El resultado de la integral aproximada es: 0.25541281188478643
---------------------------------------------
<br>


## Tema 5

- [Interpolación Lineal](https://github.com/housemarline00/MN-netbeans/blob/8eabc78fb6f522821dcafc47c1849b639c7ed1c3/Tema%205)
- ## Métodos de solución de problemas de interpolación-------------------
Hay varios métodos para resolver problemas de interpolación, que es el proceso de estimar valores desconocidos entre datos conocidos. 
Aquí hay algunos de los métodos más comunes:
Interpolación lineal: Es el método más simple y consiste en conectar los puntos conocidos con líneas rectas. Si tienes dos puntos conocidos, 
puedes encontrar el valor intermedio de manera lineal. Este método es rápido y fácil de implementar, pero puede no ser muy preciso si los datos no están bien distribuidos.
Interpolación polinómica: Este método implica encontrar un polinomio que pase exactamente por todos los puntos dados. Algunos de los métodos más populares son el método de
Lagrange y el método de interpolación de Newton. Estos métodos suelen ser más precisos que la interpolación lineal, especialmente si se usan polinomios de grado más alto.
Interpolación de spline: Los splines son curvas suaves que pasan por los puntos de datos. Se construyen mediante la unión de varias funciones polinómicas más pequeñas a 
lo largo de intervalos específicos, de modo que la curva resultante sea suave y tenga buenas propiedades de interpolación. Los splines cúbicos son los más comunes y se
utilizan en una variedad de aplicaciones. Interpolación de Fourier: Este método se utiliza específicamente para datos periódicos o datos que pueden ser considerados como 
periódicos. Utiliza una serie de funciones sinusoidales para aproximar la función subyacente. Es especialmente útil en el procesamiento de señales y en problemas 
relacionados con las transformadas de Fourier.Interpolación de vecinos más cercanos: Este método consiste en asignar el valor de un punto desconocido como el mismo 
valor que el punto conocido más cercano. Es simple y útil cuando se trabaja con datos discretos y no se necesita una función continua. Sin embargo, puede no ser 
adecuado para conjuntos de datos con ruido o variaciones abruptas.Interpolación de Kriging: Este método se utiliza en la interpolación espacial, especialmente en
la estimación de valores desconocidos en puntos dentro de una región geográfica. Utiliza un modelo estadístico para predecir valores desconocidos basados en la 
información proporcionada por los puntos conocidos y su distribución espacial.La elección del método de interpolación depende del tipo de datos, la precisión
requerida y la complejidad computacional aceptable. Es importante comprender las características de los datos y las limitaciones de cada método antes de seleccionar 
uno para resolver un problema de interpolación específico.

# interpolación lineal-------------------------------
La interpolación lineal es un método numérico y gráfico que permite encontrar datos desconocidos entre otros datos ya conocidos. Consiste en trazar una 
línea recta que pasa por dos puntos conocidos y calcular los valores intermedios según esta recta.

---------------ALGORITMO------------------------
# Algoritmo Interpolación Lineal:

  Inicio:
    - Solicitar al usuario los puntos conocidos (x0, y0) y (x1, y1)
    - Solicitar al usuario el valor de x para interpolar (x)
    - Calcular la interpolación lineal:
      - Calcular la pendiente (m) entre los puntos conocidos:
        m = (y1 - y0) / (x1 - x0)
      - Calcular el valor de y interpolado utilizando la fórmula:
        y = y0 + m * (x - x0)
    - Mostrar el valor interpolado de y para x
--------------------------------------------------

----------IMPLEMENTACIÓN EN JAVA -------------------
import java.util.Scanner;

public class InterpolacionLineal {
    
    // Método para calcular la interpolación lineal
    public static double interpolacionLineal(double x0, double y0, double x1, double y1, double x) {
        return y0 + ((y1 - y0) / (x1 - x0)) * (x - x0);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Solicitar los puntos conocidos
        System.out.println("Ingresa el primer punto conocido (x0, y0):");
        System.out.print("x0: ");
        double x0 = scanner.nextDouble();
        System.out.print("y0: ");
        double y0 = scanner.nextDouble();
        
        System.out.println("Ingresa el segundo punto conocido (x1, y1):");
        System.out.print("x1: ");
        double x1 = scanner.nextDouble();
        System.out.print("y1: ");
        double y1 = scanner.nextDouble();
        
        // Solicitar el valor de x para interpolar
        System.out.print("Ingresa el valor de x para interpolar: ");
        double x = scanner.nextDouble();
        
        // Calcular la interpolación lineal
        double y = interpolacionLineal(x0, y0, x1, y1, x);
        
        // Mostrar el resultado
        System.out.println("El valor interpolado de y para x=" + x + " es: " + y);
        
        scanner.close();
    }
}
-----------------------------------------------------------------------------

------------Salida en pantalla--------------
Ingresa el primer punto conocido (x0, y0):
x0: 1
y0: 0
Ingresa el segundo punto conocido (x1, y1):
x1: 6
y1: 1.7918
Ingresa el valor de x para interpolar: 2
El valor interpolado de y para x=2.0 es: 0.35836
--------------------------------------------------

<br>


## Tema 6

- [Método de Euler](https://github.com/housemarline00/MN-netbeans/blob/8eabc78fb6f522821dcafc47c1849b639c7ed1c3/Tema%206)
- ## Una ecuación diferencial es una ecuación matemática que relaciona una función con sus derivadas. Las ecuaciones 
diferenciales son fundamentales en diversas áreas de la ciencia y la ingeniería porque describen cómo cambian las
cantidades con el tiempo o con respecto a otras variables.

Solución de Ecuaciones Diferenciales
La solución de una ecuación diferencial puede ser explícita o implícita, y a menudo requiere métodos analíticos o numéricos.
Métodos Analíticos:
-Ecuaciones separables
-Factores integrantes: Usados para ecuaciones lineales de primer orden.
-Transformada de Laplace: Para resolver ecuaciones lineales con condiciones iniciales.
-Series de potencias: Para ecuaciones donde la solución puede expresarse como una serie.

Métodos Numéricos:
Método de Euler: Aproximación simple y directa.
Métodos de Runge-Kutta: Más precisos y usados ampliamente en aplicaciones prácticas.

# ----------Solución de ecuaciones diferenciales Método de un paso-------------
Los métodos numéricos de un paso son técnicas utilizadas para aproximar la solución de ecuaciones diferenciales
ordinarias (EDO). Estos métodos son especialmente útiles cuando la solución analítica de una EDO es difícil o imposible
de obtener.
 Aquí, se presentarán algunos de los métodos de un paso más comunes:
-Método de Euler:
El método de Euler es el más sencillo y básico de los métodos de un paso. Se basa en la idea de aproximar la curva
de solución mediante segmentos de recta.
-Método de Euler Mejorado (o método del punto medio):
También conocido como método de Euler modificado o método de Heun, mejora la precisión utilizando una estimación del valor 
medio de la pendiente en el intervalo.
-Método de Runge-Kutta de 2º orden (RK2):
Este método utiliza una combinación de pendientes para mejorar la precisión en la aproximación.
-Método de Runge-Kutta de 4º orden (RK4):
Este es uno de los métodos más populares debido a su buena precisión y estabilidad.
----------------------------------------------------------------------------------------

-----------------------Algoritmo----------------------------
metodo_euler:
  descripcion: "Método de Euler para la resolución numérica de ecuaciones diferenciales ordinarias (EDO)."
  pasos:
    - paso: 1
      descripcion: "Definir la función f(t, y) que representa la EDO dy/dt = f(t, y)."
      ejemplo: "f(t, y) = -2 * y"
    - paso: 2
      descripcion: "Inicializar los valores de t y y con las condiciones iniciales."
      ejemplo:
        t0: 0
        y0: 1
    - paso: 3
      descripcion: "Establecer el tamaño del paso h y el número máximo de pasos n_steps."
      ejemplo:
        h: 0.1
        t_max: 1
        n_steps: "n_steps = int(t_max / h)"
    - paso: 4
      descripcion: "Inicializar los arreglos para almacenar los valores de t y y."
      ejemplo:
        t_array: "t = np.linspace(t0, t_max, n_steps + 1)"
        y_array: "y = np.zeros(n_steps + 1)"
        y_initial: "y[0] = y0"
    - paso: 5
      descripcion: "Iterar desde 0 hasta n_steps, actualizando los valores de y utilizando el método de Euler."
--------------------------------------------------------------------------------------------------------------------

-------------------------IMPLEMENTACIÓN EN JAVA---------------------------------
package metodoeuler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MetodoEuler {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Método de Euler para resolver ecuaciones diferenciales");
        System.out.print("Introduce la función f(x, y) que define la derivada y' = f(x, y): ");
        String funcion = scanner.nextLine();

        System.out.print("Introduce el valor inicial de x (x0): ");
        double x0 = scanner.nextDouble();

        System.out.print("Introduce el valor inicial de y (y0): ");
        double y0 = scanner.nextDouble();

        System.out.print("Introduce el tamaño del paso (h): ");
        double h = scanner.nextDouble();

        System.out.print("Introduce el número de pasos (n): ");
        int n = scanner.nextInt();

        List<double[]> resultados = metodoEuler(funcion, x0, y0, h, n);

        System.out.println("\nResultados:");
        for (double[] par : resultados) {
            System.out.printf("x: %.4f, y: %.4f%n", par[0], par[1]);
        }

        scanner.close();
    }

    public static List<double[]> metodoEuler(String funcion, double x0, double y0, double h, int n) {
        List<double[]> resultados = new ArrayList<>();
        double x = x0;
        double y = y0;
        resultados.add(new double[]{x, y});

        for (int i = 0; i < n; i++) {
            y += h * evaluarFuncion(funcion, x, y);
            x += h;
            resultados.add(new double[]{x, y});
        }

        return resultados;
    }

    public static double evaluarFuncion(String funcion, double x, double y) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        funcion = funcion.replace("x", Double.toString(x)).replace("y", Double.toString(y));
        try {
            return (double) engine.eval(funcion);
        } catch (ScriptException e) {
            System.out.println("Error al evaluar la función: " + e.getMessage());
            return 0;
        }
    }
}
----------------------------------------------------------------------------------------------------

-----------------------Salida de pantalla--------------------------
Método de Euler para resolver ecuaciones diferenciales
Introduce la función f(x, y) que define la derivada y' = f(x, y): 2x-2
Introduce el valor inicial de x (x0): 5
Introduce el valor inicial de y (y0): 3
Introduce el tamaño del paso (h): 2
Introduce el número de pasos (n): 1

Resultados:
x: 5.0000, y: 3.0000
x: 7.0000, y: 49.0000
--------------------------------------
<br>


