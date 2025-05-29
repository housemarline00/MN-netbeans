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
- ## Métodos de solución de ecuaciones:
Los métodos de solución de ecuaciones son técnicas o procedimientos utilizados para encontrar las soluciones 
de una ecuación dada. Una ecuación es una igualdad matemática que involucra una o más incógnitas, y su solución 
consiste en encontrar los valores de las incógnitas que hacen que la ecuación sea verdadera.

Existen varios métodos para resolver ecuaciones, y la elección del método adecuado depende de la naturaleza de la 
ecuación y de las herramientas matemáticas disponibles.La elección del método adecuado depende de varios factores, 
como la complejidad de la ecuación, la disponibilidad de herramientas matemáticas y la precisión requerida en la 
solución.



# Regla Falsa:
El método de la regla falsa, también conocido como regula falsi o falsa posición, es un método iterativo para
resolver numéricamente ecuaciones no lineales. Este método combina el método de bisección y el método de la secante. 

-----Algoritmo------
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
----------------------------------------------------------------------------------------------------------------------------

---IMPLEMENTACIÓN EN JAVA-----
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
-------------------------------

- [Regla falsa](https://github.com/housemarline00/MN-netbeans/blob/1e33a171a82eea7398c15235ea6d043a5123bc55/Regla%20Falsa)

- [Newton](https://github.com/housemarline00/MN-netbeans/blob/1174e1dd911dc9e63402ade51bbc019407a88564/M%C3%A9todo%20de%20Newton)

- [Secante](https://github.com/housemarline00/MN-netbeans/blob/f83091b33214d39277bf5ce17d90a2a2fb254c51/M%C3%A9todo%20de%20Secante)

<br>


## Tema 3

- [Método de Gauss-Seidel](https://github.com/housemarline00/MN-netbeans/blob/8eabc78fb6f522821dcafc47c1849b639c7ed1c3/Tema%203)
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


