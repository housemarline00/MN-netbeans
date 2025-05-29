## Introduccion 
**Definicion**
Los métodos numéricos son un conjunto de técnicas matemáticas que se utilizan para resolver 
problemas en los que se requiere obtener soluciones aproximadas o numéricas, 
especialmente cuando no es posible o práctico encontrar una solución 
exacta mediante métodos analíticos. 


Los métodos numéricos sirven para resolver una variedad de problemas en los que es 
necesario obtener soluciones aproximadas mediante cálculos numéricos. 
Algunas de las razones por las que se utilizan estos métodos incluyen:

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

 
-----------------------------------------------------------------------------------------------------------------------------------

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
------------------------------------------

- [Regla falsa](https://github.com/housemarline00/MN-netbeans/blob/1e33a171a82eea7398c15235ea6d043a5123bc55/Regla%20Falsa)
## Regla Falsa:
El método de la regla falsa, también conocido como regula falsi o falsa posición, es un método iterativo para
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
<br>

## Tema 4

- [Metodo de Trapecio](https://github.com/housemarline00/MN-netbeans/blob/8eabc78fb6f522821dcafc47c1849b639c7ed1c3/Tema%204)
<br>


## Tema 5

- [Interpolación Lineal](https://github.com/housemarline00/MN-netbeans/blob/8eabc78fb6f522821dcafc47c1849b639c7ed1c3/Tema%205)

<br>


## Tema 6

- [Método de Euler](https://github.com/housemarline00/MN-netbeans/blob/8eabc78fb6f522821dcafc47c1849b639c7ed1c3/Tema%206)
<br>


