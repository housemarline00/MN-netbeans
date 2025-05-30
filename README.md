## Introduccion

Los métodos numéricos son un conjunto de técnicas matemáticas que se utilizan para resolver 
problemas en los que se requiere obtener soluciones aproximadas o numéricas, 
especialmente cuando no es posible o práctico encontrar una solución 
exacta mediante métodos analíticos. 


Los métodos numéricos sirven para resolver una variedad de problemas en los que es 
necesario obtener soluciones aproximadas mediante cálculos numéricos. 

## Tema 1 
# Tipos de Errores
En métodos numéricos, los errores son discrepancias entre el valor exacto de una solución y el valor aproximado obtenido por el método. Estos errores se clasifican principalmente en los siguientes tipos:

** Error de redondeo: Surge debido a la representación finita de números en computadoras. Los números reales se aproximan a números de punto flotante, lo que introduce pequeños errores en cada operación aritmética. Por ejemplo, al representar π o √2 en un sistema con precisión limitada.
** Error de truncamiento: Ocurre cuando se aproxima una función o un proceso matemático infinito por uno finito. Por ejemplo, al usar una serie de Taylor truncada para aproximar una función como sin(x), se descartan términos de orden superior, lo que genera un error.
** Error de discretización: Relacionado con el truncamiento, aparece al discretizar un problema continuo, como en la resolución de ecuaciones diferenciales mediante métodos como Euler o Runge-Kutta, donde se reemplazan derivadas por diferencias finitas.
** Error de propagación: Se produce cuando los errores iniciales (de redondeo o truncamiento) se acumulan o amplifican a lo largo de las iteraciones de un algoritmo. Puede ser significativo en métodos iterativos como el de Newton-Raphson.
** Error de datos o de entrada: Proviene de imprecisiones en los datos iniciales o parámetros del problema, como mediciones experimentales inexactas.
Error absoluto y relativo:

** Error absoluto: Diferencia entre el valor exacto 

** Error relativo: Error absoluto dividido por el valor exacto (si no es cero):

** Error de convergencia: En métodos iterativos, es la diferencia entre la solución aproximada en una iteración y la solución exacta, relacionada con la velocidad de convergencia del método.
Error de estabilidad: Surge en algoritmos sensibles a pequeñas perturbaciones en los datos o cálculos, lo que puede llevar a resultados muy diferentes. Por ejemplo, en sistemas mal condicionados.




### Tema 2
- [Métodos de Solución de Ecuaciones](https://github.com/housemarline00/MN-netbeans/blob/acbb1ba2631d08b2a81e281058b7d6ab28c46aa3/Tema%202)
- Los métodos de solución de ecuaciones son técnicas o procedimientos utilizados para encontrar las soluciones 
de una ecuación dada. Una ecuación es una igualdad matemática que involucra una o más incógnitas, y su solución 
consiste en encontrar los valores de las incógnitas que hacen que la ecuación sea verdadera.

Existen varios métodos para resolver ecuaciones, y la elección del método adecuado depende de la naturaleza de la 
ecuación y de las herramientas matemáticas disponibles.La elección del método adecuado depende de varios factores, 
como la complejidad de la ecuación, la disponibilidad de herramientas matemáticas y la precisión requerida en la 
solución.

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
Regla_Falsa:
  descripcion: >
    Algoritmo de Regla Falsa para encontrar la raíz de una función.
  pasos:
    - Paso_1: 
        descripcion: Inicializar valores
        acciones:
          - Asignar valores iniciales a a y b (intervalo inicial)
          - Evaluar la función en los extremos del intervalo para obtener f(a) y f(b)
    - Paso_2:
        descripcion: Iteración
        acciones:
          - Calcular el valor de c utilizando la fórmula de interpolación lineal
          - Evaluar la función en c para obtener f(c)
          - Verificar el cambio de signo entre f(a) y f(c), o f(c) y f(b)
          - Actualizar el intervalo [a, b] según el cambio de signo
    - Paso_3:
        descripcion: Condición de convergencia
        acciones:
          - Repetir el Paso_2 hasta que se satisfaga una condición de convergencia (por ejemplo, |b - a| < epsilon)
    - Paso_4:
        descripcion: Retorno del resultado
        acciones:
          - Devolver el valor aproximado de la raíz encontrada (c)


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


- [Newton](https://github.com/housemarline00/MN-netbeans/blob/1174e1dd911dc9e63402ade51bbc019407a88564/M%C3%A9todo%20de%20Newton)
- ## Método de Newton:
El Método de Newton, también conocido como Método de Newton-Raphson, es un algoritmo iterativo para encontrar raíces de una función no lineal. 
El método es especialmente útil cuando se necesita una aproximación numérica rápida y precisa de las raíces de una función.

# Algoritmo
metodo_de_newton:
  descripcion: >
    Algoritmo del Método de Newton para encontrar raíces de una función no lineal.
  parametros:
    - funcion: "f(x)"
      descripcion: "La función para la cual se desea encontrar la raíz."
    - derivada: "f'(x)"
      descripcion: "La derivada de la función."
    - estimacion_inicial: "x0"
      descripcion: "Estimación inicial para la raíz."
    - tolerancia: "tol"
      descripcion: "Tolerancia para el criterio de convergencia."
    - max_iteraciones: "max_iter"
      descripcion: "Número máximo de iteraciones permitidas."
  pasos:
    - paso: "1"
      descripcion: "Inicialización"
      acciones:
        - seleccionar_estimacion_inicial: "x0"
    - paso: "2"
      descripcion: "Iteración"
      acciones:
        - calcular_nueva_estimacion: "xn+1 = xn - f(xn) / f'(xn)"
    - paso: "3"
      descripcion: "Convergencia"
      acciones:
        - verificar_criterio_de_convergencia: "|f(xn+1)| < tol o |xn+1 - xn| < tol"
        - continuar_iterando_o_finalizar: "Dependiendo del criterio de convergencia y max_iter"



# IMPLEMENTACIÓN EN JAVA
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


- [Secante](https://github.com/housemarline00/MN-netbeans/blob/f83091b33214d39277bf5ce17d90a2a2fb254c51/M%C3%A9todo%20de%20Secante)
- ## Método de Secante:
El método de la secante es un algoritmo de investigación de raíces que utiliza una serie de raíces de líneas secantes para 
aproximar mejor la raíz de una función. Es una variación del método de Newton-Raphson.

# Algoritmo
metodo_secante:
  descripcion: |
    El Método de la Secante es un método numérico utilizado para encontrar las raíces de una función no lineal.
  pasos:
    - Paso_1: |
        Inicializar valores iniciales x0 y x1, y definir la tolerancia ε.
    - Paso_2: |
        Calcular el valor de la función en x0 y x1: f(x0) y f(x1), respectivamente.
    - Paso_3: |
        Calcular la pendiente de la secante: m = (f(x1) - f(x0)) / (x1 - x0).
    - Paso_4: |
        Calcular el próximo punto de intersección de la secante con el eje x:
        x2 = x1 - f(x1) * (x1 - x0) / (f(x1) - f(x0)).
    - Paso_5: |
        Si |x2 - x1| < ε o |f(x2)| < ε, detener el algoritmo y devolver x2 como la aproximación de la raíz.
        De lo contrario, continuar con el siguiente paso.
    - Paso_6: |
        Actualizar x0 y x1:
        x0 = x1
        x1 = x2
    - Paso_7: |
        Ir al Paso 2 y repetir el proceso hasta alcanzar la precisión deseada o un número máximo de iteraciones.


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

# Método de Jacobi:
Los métodos iterativos de Jacobi y Gauss-Seidel son los procesos de aproximaciones sucesivas para resolver sistemas de ecuaciones lineales 
compatibles determinados.Ambos requieren de la verificación de un criterio de convergencia comúnmente conocido como diagonal pesada.

# Algoritmo//Método de Jacobi
metodo_jacobi:
  descripcion: "Algoritmo para encontrar soluciones aproximadas de un sistema de ecuaciones lineales"
  entrada:
    A: 
      tipo: matriz
      descripcion: "Matriz de coeficientes del sistema de ecuaciones"
    b:
      tipo: vector
      descripcion: "Vector de términos constantes"
    x0:
      tipo: vector
      descripcion: "Aproximación inicial de la solución"
    tol:
      tipo: float
      descripcion: "Tolerancia para el criterio de convergencia"
    max_iter:
      tipo: int
      descripcion: "Número máximo de iteraciones"
  salida:
    x:
      tipo: vector
      descripcion: "Solución aproximada del sistema de ecuaciones"
    iteraciones:
      tipo: int
      descripcion: "Número de iteraciones realizadas"
  algoritmo: |
    función jacobi(A, b, x0, tol, max_iter):
      n = tamaño de A[0]  # Número de ecuaciones/sistema de ecuaciones

      x = copiar(x0)  # Inicializar el vector solución
      x_nuevo = crear vector de longitud n
      iteraciones = 0

      mientras iteraciones < max_iter:
        para i de 0 a n-1:
          suma = 0
          para j de 0 a n-1:
            si j ≠ i:
              suma += A[i][j] * x[j]
          x_nuevo[i] = (b[i] - suma) / A[i][i]

        si norma_infinita(x_nuevo - x) < tol:
          devolver x_nuevo, iteraciones

        x = copiar(x_nuevo)
        iteraciones += 1

      devolver x, iteraciones


# IMPLEMENTACIÓN EN JAVA
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedir dimensiones de la matriz
        System.out.println("Ingrese el tamaño de la matriz (n x n):");
        int n = scanner.nextInt();

        // Pedir matriz de coeficientes
        System.out.println("Ingrese la matriz de coeficientes A (una fila a la vez):");
        double[][] A = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = scanner.nextDouble();
            }
        }

        // Pedir vector de términos constantes
        System.out.println("Ingrese el vector de términos constantes b:");
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextDouble();
        }

        // Calcular solución utilizando el método de Jacobi
        double[] solucion = jacobi(A, b);

        // Mostrar resultado
        System.out.println("La solución aproximada del sistema de ecuaciones es:");
        for (int i = 0; i < n; i++) {
            System.out.println("x[" + i + "] = " + solucion[i]);
        }
    }

    public static double[] jacobi(double[][] A, double[] b) {
        int n = A.length;
        double[] x0 = new double[n];
        double[] xNuevo = new double[n];

        // Inicializar x0 con ceros
        for (int i = 0; i < n; i++) {
            x0[i] = 0.0;
        }

        // Realizar un número fijo de iteraciones (en este caso, 50)
        for (int k = 0; k < 50; k++) {
            // Calcular nuevo valor de x
            for (int i = 0; i < n; i++) {
                double suma = 0.0;
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        suma += A[i][j] * x0[j];
                    }
                }
                xNuevo[i] = (b[i] - suma) / A[i][i];
            }

            // Actualizar x0
            for (int i = 0; i < n; i++) {
                x0[i] = xNuevo[i];
            }
        }

        return x0;
    }
}


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


# IMPLEMENTACIÓN EN JAVA
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

# Metodo Simpson
--Método de Simpson 1/3:
En el método de Simpson 1/3, la integral definida se aproxima mediante una parábola que pasa a través de tres puntos 
equidistantes. Estos puntos se obtienen dividiendo el intervalo de integración en subintervalos de igual longitud y aplicando
una fórmula que utiliza los valores de la función en los extremos y en el punto medio de cada subintervalo.
--Método de Simpson 3/8:
En el método de Simpson 3/8, la integral definida se aproxima mediante una interpolación polinómica de tercer grado que pasa a
través de cuatro puntos igualmente espaciados. Se usa cuando el número de subintervalos es múltiplo de tres (para garantizar que 
se puedan aplicar las cuatro evaluaciones necesarias).


# Algoritmos
# metodo_simpson_1_3:
  descripcion: Método de Simpson 1/3 para aproximar una integral definida.
  entrada:
    - a: Límite inferior de integración
    - b: Límite superior de integración
    - n: Número de subintervalos (debe ser un múltiplo de 2)
    - f: Función a integrar
  salida:
    - valor_aproximado: Valor aproximado de la integral definida
  algoritmo: |
    h = (b - a) / n
    suma = f(a) + f(b)
    suma_impares = 0
    suma_pares = 0

    for i in range(1, n):
      xi = a + i * h
      if i % 2 == 0:
        suma_pares += f(xi)
      else:
        suma_impares += f(xi)

    valor_aproximado = (h / 3) * (suma + 4 * suma_impares + 2 * suma_pares)
    return valor_aproximado

---metodo_simpson_3_8:
  descripcion: Método de Simpson 3/8 para aproximar una integral definida.
  entrada:
    - a: Límite inferior de integración
    - b: Límite superior de integración
    - n: Número de subintervalos (debe ser un múltiplo de 3)
    - f: Función a integrar
  salida:
    - valor_aproximado: Valor aproximado de la integral definida
  algoritmo: |
    h = (b - a) / n
    suma = f(a) + f(b)
    suma_1_3 = 0
    suma_2_3 = 0

    for i in range(1, n):
      xi = a + i * h
      if i % 3 == 0:
        suma += 2 * f(xi)
      elif i % 3 == 1:
        suma_1_3 += f(xi)
      else:
        suma_2_3 += f(xi)

    valor_aproximado = (3 * h / 8) * (suma + 3 * suma_1_3 + 3 * suma_2_3 + f(b))
    return valor_aproximado


# IMPLEMENTACIÓN EN JAVA
# Simpson 1/8
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Scanner;

public class Simpson18 {
    // Evalúa la función dada como una cadena de texto en el punto x
    public static double f(String expr, double x) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
        return (Double) engine.eval(expr.replace("sin", "Math.sin")
                                          .replace("cos", "Math.cos")
                                          .replace("tan", "Math.tan")
                                          .replace("x", String.valueOf(x)));
    }

    // Aproxima la integral de la función dada usando la regla de Simpson 1/8
    public static double simpsons18(String expr, double a, double b, int n) {
        double h = (b - a) / n;
        double sum = 0;

        try {
            sum = f(expr, a) + f(expr, b);

            for (int i = 1; i < n; i++) {
                if (i % 4 == 0) {
                    sum += 2 * f(expr, a + i * h);
                } else {
                    sum += 4 * f(expr, a + i * h);
                }
            }
        } catch (ScriptException e) {
            System.err.println("Error evaluando la función: " + e.getMessage());
        }

        return (h / 3) * sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la función a integrar (por ejemplo, 'Math.sin(x)' para seno): ");
        String expr = scanner.nextLine();

        System.out.print("Ingrese el límite inferior de integración: ");
        double a = scanner.nextDouble();

        System.out.print("Ingrese el límite superior de integración: ");
        double b = scanner.nextDouble();

        System.out.print("Ingrese el número de intervalos: ");
        int n = scanner.nextInt();

        double result = simpsons18(expr, a, b, n);
        System.out.printf("Valor aproximado de la integral: %.4f%n", result);
    }
}

----------Salida en consola 1/8----------
Ingrese la función a integrar (por ejemplo, 'Math.sin(x)' para seno): sin(x)
Ingrese el límite inferior de integración: 0
Ingrese el límite superior de integración: 3.141592653589793
Ingrese el número de intervalos: 100
Valor aproximado de la integral: 2.3336
----------------------------------------


# Simpson  3/8
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Scanner;

public class Simpson38 {
    // Evalúa la función dada como una cadena de texto en el punto x
    public static double f(String expr, double x) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
        return (Double) engine.eval(expr.replace("sin", "Math.sin")
                                          .replace("cos", "Math.cos")
                                          .replace("tan", "Math.tan")
                                          .replace("x", String.valueOf(x)));
    }

    // Aproxima la integral de la función dada usando la regla de Simpson 3/8
    public static double simpsons38(String expr, double a, double b, int n) {
        try {
            double h = (b - a) / n;
            double sum = f(expr, a) + f(expr, b);

            for (int i = 1; i < n; i++) {
                if (i % 3 == 0) {
                    sum += 2 * f(expr, a + i * h);
                } else {
                    sum += 3 * f(expr, a + i * h);
                }
            }

            return (3 * h / 8) * sum;
        } catch (ScriptException e) {
            System.err.println("Error evaluando la función: " + e.getMessage());
            return Double.NaN; // Otra acción apropiada en caso de error
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la función a integrar (por ejemplo, 'Math.log(x)' para logaritmo natural): ");
        String expr = scanner.nextLine();

        System.out.print("Ingrese el límite inferior de integración: ");
        double a = scanner.nextDouble();

        System.out.print("Ingrese el límite superior de integración: ");
        double b = scanner.nextDouble();

        System.out.print("Ingrese el número de intervalos: ");
        int n = scanner.nextInt();

        double result = simpsons38(expr, a, b, n);
        if (!Double.isNaN(result)) {
            System.out.printf("Valor aproximado de la integral: %.4f%n", result);
        }
    }
}


-----------Salida en pantalla 3/8---------------
Ingrese la función a integrar (por ejemplo, 'Math.log(x)' para logaritmo natural): sin(x)
Ingrese el límite inferior de integración: 0
Ingrese el límite superior de integración: 3.141592653589793
Ingrese el número de intervalos: 100
Valor aproximado de la integral: 1.9999


# Método de la Cuadratura Gaussiana
El método de la cuadratura gaussiana es un método de análisis numérico que aproxima la integral definida de una función. Este método selecciona los 
puntos de evaluación de manera óptima, no de forma igualmente espaciada, para obtener el resultado exacto al integrar polinomios de grado 2n-1 o menos. 


# Algoritmo Método de la Cuadratura Gaussiana
nombre: Metodo de la Cuadratura Gaussiana
descripcion: 
  El Método de la Cuadratura Gaussiana es un método numérico para la aproximación de integrales definidas. 
  Se basa en la idea de aproximar la integral de una función mediante una suma ponderada de los valores de la función en puntos específicos llamados nodos.
  Estos nodos y los correspondientes pesos se eligen de tal manera que la aproximación resultante sea lo más precisa posible.

paso_1:
  descripcion: Definir la función a integrar y el intervalo de integración.
  codigo: 
    Definir la función f(x).
    Definir el intervalo de integración [a, b].

paso_2:
  descripcion: Escoger el número de nodos y calcular sus posiciones y pesos.
  codigo: 
    Determinar el número de nodos n.
    Calcular las posiciones de los nodos x_i y los pesos w_i para la Cuadratura Gaussiana de n puntos en el intervalo [-1, 1].

paso_3:
  descripcion: Calcular la aproximación de la integral.
  codigo: 
    Calcular la aproximación de la integral usando la fórmula de Cuadratura Gaussiana:
      integral ≈ sumatoria desde i=1 hasta n de (w_i * f((b-a)/2 * x_i + (b+a)/2)).


# Implementacion en JAVA
import java.util.Scanner;

public class GaussianQuadrature {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedir la función al usuario
        System.out.print("Ingrese la función a integrar (utilice 'x' como variable independiente, y funciones trigonométricas como 'sin', 'cos', 'tan'): ");
        String funcionStr = scanner.nextLine();

        // Pedir los límites de integración
        System.out.print("Ingrese el límite inferior de integración (a): ");
        double a = scanner.nextDouble();
        System.out.print("Ingrese el límite superior de integración (b): ");
        double b = scanner.nextDouble();

        // Pedir el número de puntos para la aproximación
        System.out.print("Ingrese el número de puntos para la aproximación: ");
        int n = scanner.nextInt();

        // Calcular la integral utilizando el método de la Cuadratura de Gauss
        double integral = calcularIntegral(funcionStr, a, b, n);

        System.out.println("El resultado de la integral es: " + integral);

        scanner.close();
    }

    // Calcular la integral de la función en el intervalo [a, b] utilizando el método de la Cuadratura de Gauss
    private static double calcularIntegral(String funcionStr, double a, double b, int n) {
        double sum = 0.0;
        double[] x = new double[n];
        double[] w = new double[n];

        // Calcular los nodos y pesos para el número de puntos especificado
        calcularNodosYPesos(n, x, w);

        // Calcular la integral utilizando los nodos y pesos
        for (int i = 0; i < n; i++) {
            double fx = evaluarFuncion(funcionStr, (a + b) / 2.0 + (b - a) / 2.0 * x[i]);
            sum += w[i] * fx;
        }

        return sum * (b - a) / 2.0;
    }

    // Evaluar la función ingresada por el usuario en un punto dado
    private static double evaluarFuncion(String funcionStr, double x) {
        // Implementación de un parser simple para evaluar funciones matemáticas
        return evaluateFunction(funcionStr, x);
    }

    private static double evaluateFunction(String funcionStr, double x) {
        // Reemplazar la variable 'x' en la cadena de la función con el valor específico
        String replacedFunc = funcionStr.replace("sin", "Math.sin")
                                         .replace("cos", "Math.cos")
                                         .replace("tan", "Math.tan")
                                         .replace("x", Double.toString(x));
        try {
            // Evaluar la expresión matemática utilizando la función eval de la clase Math
            return (double) new Object() { }.getClass().getMethod("eval", String.class).invoke(null, replacedFunc);
        } catch (Exception e) {
            // En caso de error, regresar NaN (no se pudo evaluar la función correctamente)
            return Double.NaN;
        }
    }

    // Calcular los nodos y pesos para el método de la Cuadratura de Gauss
    private static void calcularNodosYPesos(int n, double[] x, double[] w) {
        if (n == 1) {
            x[0] = 0.0;
            w[0] = 2.0;
        } else {
            double[] P = new double[n];
            double[] Pp = new double[n];
            double[] d = new double[n];

            // Calcular los polinomios de Legendre
            for (int i = 0;i < n; i++) {
                P[i] = legendrePolynomial(i, x[i]);
            }

            // Calcular los polinomios de Legendre derivados
            for (int i = 1; i < n; i++) {
                Pp[i] = derivateLegendrePolynomial(i, x[i]);
            }

            // Calcular los polinomios de Legendre normalizados
            for (int i = 0; i < n; i++) {
                d[i] = derivateLegendrePolynomial(i + 1, x[i]);
                P[i] /= d[i];
                Pp[i] /= d[i];
            }

            // Calcular los nodos
            for (int i = 0; i < n; i++) {
                double p = P[i];
                double p1 = P[i + 1];
                double p2 = P[i + 2];
                double p1p = Pp[i + 1];
                double p2p = Pp[i + 2];
                double denom = (p1p * p2 - p1 * p2p) / (p - p2);
                x[i] = (p * p2p - p2 * p1p) / (p1p - p2p);
                x[i] = x[i] - x[i] * x[i] * denom;
            }

            // Calcular los pesos
            for (int i = 0; i < n; i++) {
                double p = P[i];
                double p1 = P[i + 1];
                double p2 = P[i + 2];
                double p1p = Pp[i + 1];
                double p2p = Pp[i + 2];
                double denom = (p1p * p2 - p1 * p2p) / (p - p2);
                w[i] = 2.0 * (1.0 - x[i] * x[i]) / (p * p1p * denom);
            }
        }
    }

    // Calcular el polinomio de Legendre de grado n
    private static double legendrePolynomial(int n, double x) {
        double result = 1.0;
        double x2 = x * x;

        for (int i = 1; i <= n; i++) {
            result *= x2 - 1.0;
            result /= i;
            result *= 2.0 * i - 1.0;
        }

        return result;
    }

    // Calcular la derivada del polinomio de Legendre de grado n
    private static double derivateLegendrePolynomial(int n, double x) {
        double result = n * (x * legendrePolynomial(n, x) - legendrePolynomial(n - 1, x)) / (x * x - 1.0);
        return result;
    }
}


-----------Salida en pantalla-----------------
Ingrese la función a integrar (utilice 'x' como variable independiente, y funciones trigonométricas como 'sin', 'cos', 'tan'): sin(x)
Ingrese el límite inferior de integración (a): 0
Ingrese el límite superior de integración (b): 3.141592653589793
Ingrese el número de puntos para la aproximación: 100
El resultado de la integral es: 2.0
------------------------------------------



<br>


## Tema 5

- [Interpolación Lineal](https://github.com/housemarline00/MN-netbeans/blob/8eabc78fb6f522821dcafc47c1849b639c7ed1c3/Tema%205)
- ## Métodos de solución de problemas de interpolación
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

# interpolación lineal
La interpolación lineal es un método numérico y gráfico que permite encontrar datos desconocidos entre otros datos ya conocidos. Consiste en trazar una 
línea recta que pasa por dos puntos conocidos y calcular los valores intermedios según esta recta.

# ALGORITMO
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


# IMPLEMENTACIÓN EN JAVA 
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

# -Interpolación Cuadrática
La interpolación cuadrática es un método de interpolación que se utiliza cuando se tienen tres puntos de datos. Este método consiste en ajustar los tres
puntos en un polinomio de segundo grado, lo que permite lograr una mejor estimación al introducir una curvatura en la línea que une los puntos.

# Algoritmo
InterpolacionCuadratica:
  entrada:
    - x1: "Valor de x1"
    - x2: "Valor de x2"
    - x3: "Valor de x3"
    - fx1: "Valor de f(x1)"
    - fx2: "Valor de f(x2)"
    - fx3: "Valor de f(x3)"
    - x: "Valor de x para interpolar"
  salida:
    - resultado: "Resultado de la interpolación cuadrática en x"
  algoritmo: |
    Función interpolarCuadratica(x1, x2, x3, fx1, fx2, fx3, x):
      L1 = ((x - x2) * (x - x3)) / ((x1 - x2) * (x1 - x3))
      L2 = ((x - x1) * (x - x3)) / ((x2 - x1) * (x2 - x3))
      L3 = ((x - x1) * (x - x2)) / ((x3 - x1) * (x3 - x2))
      resultado = (fx1 * L1) + (fx2 * L2) + (fx3 * L3)
      retornar resultado
------------------------------------------------------------

# IMPLEMENTACIÓN EN JAVA
package intercua1;
import java.util.Scanner;

public class InterCua1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el valor de x1:");
        double x1 = scanner.nextDouble();
        System.out.println("Ingrese el valor de x2:");
        double x2 = scanner.nextDouble();
        System.out.println("Ingrese el valor de x3:");
        double x3 = scanner.nextDouble();

        System.out.println("Ingrese el valor de f(x1):");
        double fx1 = scanner.nextDouble();
        System.out.println("Ingrese el valor de f(x2):");
        double fx2 = scanner.nextDouble();
        System.out.println("Ingrese el valor de f(x3):");
        double fx3 = scanner.nextDouble();

        System.out.println("Ingrese el valor de x para interpolar:");
        double x = scanner.nextDouble();

        double resultado = interpolarCuadratica(x1, x2, x3, fx1, fx2, fx3, x);
        System.out.println("El resultado de la interpolación cuadrática en x=" + x + " es: " + resultado);
    }

    public static double interpolarCuadratica(double x1, double x2, double x3, double fx1, double fx2, double fx3, double x) {
        double L1 = ((x - x2) * (x - x3)) / ((x1 - x2) * (x1 - x3));
        double L2 = ((x - x1) * (x - x3)) / ((x2 - x1) * (x2 - x3));
        double L3 = ((x - x1) * (x - x2)) / ((x3 - x1) * (x3 - x2));

        double resultado = (fx1 * L1) + (fx2 * L2) + (fx3 * L3);
        return resultado;
    }
}

-----------------Salida en pantalla--------------------
Ingrese el valor de x1:
1
Ingrese el valor de x2:
4
Ingrese el valor de x3:
6
Ingrese el valor de f(x1):
0
Ingrese el valor de f(x2):
1.386294
Ingrese el valor de f(x3):
1.791759
Ingrese el valor de x para interpolar:
2
El resultado de la interpolación cuadrática en x=2.0 es: 0.5658441999999999


# Interpolación Newton
La interpolación de Newton es un método de interpolación polinómica que se utiliza para encontrar valores intermedios en datos ajustando funciones 
adecuadas por tramos en puntos de datos adyacentes. Este método es útil para situaciones que requieran un número bajo de puntos para interpolar, ya que a
medida que crece el número de puntos, también lo hace el grado del polinomio.

# Algoritmo
Interpolación de Newton:
  Descripción: >
    Un algoritmo para realizar la interpolación de Newton
    utilizando diferencias divididas.
  Entrada:
    - Nombre: n
      Descripción: Número de puntos de datos
      Tipo: Entero
    - Nombre: x
      Descripción: Valores de x
      Tipo: Lista de Flotantes
    - Nombre: y
      Descripción: Valores de y
      Tipo: Lista de Flotantes
    - Nombre: valorX
      Descripción: Valor de x para interpolar
      Tipo: Flotante
  Salida:
    - Nombre: resultado
      Descripción: Resultado de la interpolación en valorX
      Tipo: Flotante
  Pasos:
    1. Crear una tabla de diferencias divididas inicializada con ceros.
    2. Calcular las diferencias divididas para cada orden.
    3. Calcular el resultado interpolado utilizando las diferencias divididas.
  Pasos Detallados:
    1. Inicializar una tabla de diferencias divididas de tamaño n x n con ceros.
    2. Para cada columna j de 0 a n-1:
       a. Establecer la primera fila de la columna j igual a los valores de y.
       b. Para cada fila i de 0 a n-j-1:
            Calcular la diferencia dividida correspondiente utilizando la fórmula:
            tablaDiferenciasDivididas[i][j] = (tablaDiferenciasDivididas[i + 1][j - 1] - tablaDiferenciasDivididas[i][j - 1]) / (x[i + j] - x[i]).
    3. Calcular el resultado interpolado:
       resultado = y[0]
       Para cada i de 1 a n-1:
           producto = 1.0
           Para cada j de 0 a i-1:
               producto *= (valorX - x[j])
           resultado += tablaDiferenciasDivididas[0][i] * producto
  Pseudocódigo: |
    tablaDiferenciasDivididas = matriz de tamaño n x n inicializada con ceros
    para cada columna j de 0 a n-1:
        establecer la primera fila de la columna j igual a los valores de y
        para cada fila i de 0 a n-j-1:
            tablaDiferenciasDivididas[i][j] = (tablaDiferenciasDivididas[i + 1][j - 1] - tablaDiferenciasDivididas[i][j - 1]) / (x[i + j] - x[i])
    resultado = y[0]
    para cada i de 1 a n-1:
        producto = 1.0
        para cada j de 0 a i-1:
            producto *= (valorX - x[j])
        resultado += tablaDiferenciasDivididas[0][i] * producto
-------------------------------------------------------------------------------------------------

# IMPLEMENTACION EN JAVA
package internewton1;

import java.util.Scanner;

public class InterNewton1 {

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        
        // Pedir al usuario el número de puntos de datos
        System.out.print("Ingrese el número de puntos de datos: ");
        int n = scanner.nextInt();
        
        // Arreglos para almacenar los valores de x e y
        double[] x = new double[n];
        double[] y = new double[n];
        
        // Pedir al usuario los valores de x e y
        System.out.println("Ingrese los valores de x e y:");
        for (int i = 0; i < n; i++) {
            System.out.print("x[" + i + "]: ");
            x[i] = scanner.nextDouble();
            System.out.print("y[" + i + "]: ");
            y[i] = scanner.nextDouble();
        }
        
        // Pedir al usuario el valor de x para interpolar
        System.out.print("Ingrese el valor de x para interpolar: ");
        double valorX = scanner.nextDouble();
        
        // Calcular el resultado de la interpolación de Newton
        double resultado = interpolacionNewton(x, y, valorX);
        
        System.out.println("El resultado de la interpolación en x=" + valorX + " es y=" + resultado);
        
        scanner.close();
    }
    
    // Método para realizar la interpolación de Newton
    public static double interpolacionNewton(double[] x, double[] y, double valorX) {
        int n = x.length;
        double resultado = y[0];
        double[][] tablaDiferenciasDivididas = new double[n][n];
        
        // Calcular las diferencias divididas
        for (int i = 0; i < n; i++) {
            tablaDiferenciasDivididas[i][0] = y[i];
        }
        
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                tablaDiferenciasDivididas[i][j] = (tablaDiferenciasDivididas[i + 1][j - 1] - tablaDiferenciasDivididas[i][j - 1]) / (x[i + j] - x[i]);
            }
        }
        
        // Calcular el resultado interpolado
        for (int i = 1; i < n; i++) {
            double producto = 1.0;
            for (int j = 0; j < i; j++) {
                producto *= (valorX - x[j]);
            }
            resultado += tablaDiferenciasDivididas[0][i] * producto;
        }
        
        return resultado;
    }
    
}
-------------------------------------------------------------------------

------------------------Salida en pantalla---------------------------
Ingrese el número de puntos de datos: 3
Ingrese los valores de x e y:
x[0]: -2
y[0]: 1
x[1]: -1
y[1]: 0
x[2]: 0
y[2]: 1
Ingrese el valor de x para interpolar: 2
El resultado de la interpolación en x=2.0 es y=9.0
------------------------------------------------------------

# -Interpolación Lagrange
La interpolación de Lagrange es un método numérico que aproxima funciones utilizando un polinomio que pasa por puntos conocidos
de la función que se pretende aproximar. Es un método de interpolación polinómica que busca encontrar un polinomio que pase exactamente por los puntos dados

# Algoritmo
Interpolación de Lagrange:

  Descripción: |
    El método de interpolación de Lagrange es un método para encontrar un polinomio que pase a través de un conjunto de puntos dados.
    Se basa en la idea de construir un polinomio de grado n-1 (donde n es el número de puntos conocidos) que pasa exactamente por todos los puntos dados.

  Pasos:
    - Paso 1: Obtener los puntos conocidos (x, y).
    - Paso 2: Para cada punto conocido i:
      - Paso 2.1: Calcular el término del polinomio de Lagrange correspondiente a ese punto.
        - Paso 2.1.1: Inicializar el término como el valor de y en el punto i.
        - Paso 2.1.2: Para cada otro punto conocido j:
          - Paso 2.1.2.1: Si j ≠ i, multiplicar el término por (xInterpolar - x[j]) / (x[i] - x[j]).
      - Paso 2.2: Sumar el término al resultado final.
    - Paso 3: Devolver el resultado final, que es la interpolación de Lagrange para el valor xInterpolar.

  Ejemplo:
    Puntos conocidos:
      - (1, 2)
      - (2, 3)
      - (3, 4)
    Valor para interpolar: 2.5

  Resultado: |
    El resultado de la interpolación de Lagrange para x = 2.5 es el valor calculado utilizando el polinomio de Lagrange construido a partir de los puntos conocidos.
-------------------------------------------------------------------------------------------------------------

# IMPLEMENTACIÓN EN JAVA
import java.util.Scanner;

public class LagrangeInterpolation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedir al usuario el número de puntos conocidos
        System.out.print("Introduce el número de puntos conocidos: ");
        int numPoints = scanner.nextInt();

        // Arrays para almacenar las coordenadas x e y de los puntos conocidos
        double[] x = new double[numPoints];
        double[] y = new double[numPoints];

        // Pedir al usuario las coordenadas x e y de los puntos conocidos
        System.out.println("Introduce las coordenadas x e y de los puntos conocidos:");
        for (int i = 0; i < numPoints; i++) {
            System.out.print("x[" + i + "]: ");
            x[i] = scanner.nextDouble();
            System.out.print("y[" + i + "]: ");
            y[i] = scanner.nextDouble();
        }

        // Pedir al usuario el valor de x para el cual se desea interpolar y
        System.out.print("Introduce el valor de x para el cual deseas interpolar: ");
        double xInterpolate = scanner.nextDouble();

        // Calcular el resultado de la interpolación de Lagrange
        double result = lagrangeInterpolation(x, y, xInterpolate);

        // Mostrar el resultado
        System.out.println("El resultado de la interpolación de Lagrange para x = " + xInterpolate + " es: " + result);

        scanner.close();
    }

    // Método para calcular la interpolación de Lagrange
    public static double lagrangeInterpolation(double[] x, double[] y, double xInterpolate) {
        double result = 0;

        for (int i = 0; i < x.length; i++) {
            double term = y[i];
            for (int j = 0; j < x.length; j++) {
                if (j != i) {
                    term = term * (xInterpolate - x[j]) / (x[i] - x[j]);
                }
            }
            result += term;
        }

        return result;
    }
}
-------------------------------------------------------------------------------------------------

-----------------Salida en pantalla--------------------------------
Introduce el número de puntos conocidos: 2
Introduce las coordenadas x e y de los puntos conocidos:
x[0]: 7
y[0]: 3940
x[1]: 13
y[1]: 4755
Introduce el valor de x para el cual deseas interpolar: 10
El resultado de la interpolación de Lagrange para x = 10.0 es: 4347.5
------------------------------------------

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


# Método de pasos múltiples
El método de pasos múltiples es una técnica utilizada para resolver ecuaciones diferenciales ordinarias (EDOs). A 
diferencia de los métodos de un solo paso, como el método de Euler o el método de Runge-Kutta, los métodos de pasos múltiples 
utilizan varios puntos previos en la malla de solución para calcular la solución en el siguiente paso.
Los métodos de pasos múltiples se dividen en dos categorías principales:
Métodos explícitos: donde la solución en el siguiente paso se calcula directamente a partir de las soluciones previas.
Métodos implícitos: donde la solución en el siguiente paso implica resolver una ecuación que incluye la solución desconocida.
Un ejemplo clásico de métodos de pasos múltiples son los métodos de Adams-Bashforth (explícitos) y Adams-Moulton (implícitos).
---------------------------------------------------------------------------------------------

# Algoritmo Adams-Bashforth (Explícito)
algoritmo:
  nombre: Adams-Bashforth
  orden: 4
  tipo: explícito
  descripción: >
    Método de pasos múltiples explícito de Adams-Bashforth de 4to orden para la solución
    de ecuaciones diferenciales ordinarias.

parámetros:
  condiciones_iniciales:
    t0: 0.0          # Tiempo inicial
    y0: 1.0          # Valor inicial de la solución
  tamaño_paso: 0.1   # Tamaño del paso (h)
  pasos: 50          # Número de pasos a realizar

ecuación:
  función: f         # Nombre de la función que define la EDO
  definición: "t - y" # Definición de la EDO en términos de t y y
------------------------------------------------------------------------

# IMPLEMENTACION EN JAVA
import java.util.Scanner;

public class AdamsBashforth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar valores al usuario
        System.out.print("Ingrese t0, y0, h y n: ");
        double t0 = scanner.nextDouble();
        double y0 = scanner.nextDouble();
        double h = scanner.nextDouble();
        int n = scanner.nextInt();

        // Inicializar arrays para almacenar los valores de tiempo y solución
        double[] t = new double[n + 1];
        double[] y = new double[n + 1];

        // Calcular los primeros valores de t y y usando RK4
        for (int i = 0; i < 4; i++) {
            t[i] = t0 + i * h;
            y[i] = rk4(t[i], y0, h);
        }

        // Aplicar el método de Adams-Bashforth para los pasos restantes
        for (int i = 3; i < n; i++) {
            t[i + 1] = t[i] + h;
            y[i + 1] = adamsBashforth(t[i], y[i], h);
        }

        // Imprimir los resultados
        System.out.println("Resultados:");
        for (int i = 0; i <= n; i++) {
            System.out.printf("t = %.4f, y = %.4f%n", t[i], y[i]);
        }

        scanner.close();
    }

    // Definir la EDO
    public static double f(double t, double y) {
        return t - y;
    }

    // Implementar el método RK4
    public static double rk4(double t, double y, double h) {
        double k1 = h * f(t, y);
        double k2 = h * f(t + h / 2, y + k1 / 2);
        double k3 = h * f(t + h / 2, y + k2 / 2);
        double k4 = h * f(t + h, y + k3);
        return y + (k1 + 2 * k2 + 2 * k3 + k4) / 6;
    }

    // Implementar el método de Adams-Bashforth
    public static double adamsBashforth(double t, double y, double h) {
        // Calcular utilizando la fórmula de Adams-Bashforth
        return y + h / 24 * (55 * f(t, y) - 59 * f(t - h, y) + 37 * f(t - 2 * h, y) - 9 * f(t - 3 * h, y));
    }
}
---------------------------------------------------------------------------------------------------

-------------------Salida en pantalla-----------------------------
Ingrese t0, y0, h y n: 4 
3
1
22
Resultados:
t = 4.0000, y = 4.0000
t = 5.0000, y = 4.6250
t = 6.0000, y = 5.2500
t = 7.0000, y = 5.8750
t = 8.0000, y = 7.5000
t = 9.0000, y = 8.5000
t = 10.0000, y = 9.5000
t = 11.0000, y = 10.5000
t = 12.0000, y = 11.5000
t = 13.0000, y = 12.5000
t = 14.0000, y = 13.5000
t = 15.0000, y = 14.5000
t = 16.0000, y = 15.5000
t = 17.0000, y = 16.5000
t = 18.0000, y = 17.5000
t = 19.0000, y = 18.5000
t = 20.0000, y = 19.5000
t = 21.0000, y = 20.5000
t = 22.0000, y = 21.5000
t = 23.0000, y = 22.5000
t = 24.0000, y = 23.5000
t = 25.0000, y = 24.5000
t = 26.0000, y = 25.5000
------------------------------------------------

<br>


