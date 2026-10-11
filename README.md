# Reto 1 - Periódico Digital (Parte 2: Análisis y Comparación con IA)

Este documento contiene la memoria completa correspondiente a la Parte 2 del Reto 1 (Periódico Digital). Se presenta el análisis detallado del proceso de desarrollo, los prompts utilizados, los errores corregidos, la comparación técnica entre versiones y las reflexiones personales sobre el uso de la Inteligencia Artificial como asistente de programación.

---

## 1. Herramienta de IA utilizada

* **Herramienta principal:** ChatGPT (GPT-4o) / Claude 3.5 Sonnet.
* **Entorno de trabajo:** Interfaz web e integración auxiliar en el entorno de desarrollo IntelliJ IDEA mediante plugin.
* **Uso principal:** Generación inicial del proyecto Maven, estructuración de las clases `Noticia`, `Periodico` y `App`, detección de bugs lógicos y refactorización del código.

---

## 2. Prompts principales

A continuación se seleccionan las interacciones más relevantes del proceso de desarrollo:

### Prompt 1: Generación de la solución inicial
> **Prompt:**
> "Analiza el siguiente enunciado de un reto en Java. Necesito implementar un proyecto Maven llamado `pi_reto1` con las siguientes clases:
> 1. `Noticia`: atributos privados (`fecha` aaaammdd, `hora` hhmm, `texto`, `lecturas`), constructor con fecha, hora y texto, getters, `incLecturas()`, `igualFecha(d, m, a)` y método `toString()` con el formato exacto especificado en el enunciado.
> 2. `Periodico`: `ArrayList<Noticia>` limitado a 1000 elementos, atributo `numNoticias`, constructor y métodos `insertar(Noticia n)`, `primeraNoticia(d, m, a)`, `masPopulares()`, `borrarAnteriores(d, m, a)` y `mostrar()`.
> 3. `App`: un programa de prueba en `com.engiri` que ejecute las 5 pruebas principales. Proporciona el código completo y bien estructurado."

### Prompt 2: Corrección del bug en la ordenación y primera noticia
> **Prompt:**
> "En el método `primeraNoticia(int d, int m, int a)` de la clase `Periodico`, la IA implementó una búsqueda que no respetaba el sentido ascendente en el `ArrayList` o fallaba al reconstruir la fecha a partir del entero `aaaammdd`. Revisa el método para que compare la fecha numéricamente ($a \times 10000 + m \times 100 + d$) y devuelva la primera coincidencia que encuentre comenzando desde el índice 0, o `null` si no existe."

### Prompt 3: Refactorización y análisis comparativo entre soluciones
> **Prompt:**
> "Compara las dos versiones generadas del código: una versión tradicional con bucles imperativos `for` e `if` estándar (Solución A) y una versión modernizada con Streams de Java 8+ (Solución B). Analiza la estructura, claridad, complejidad, tratamiento de errores y mantenibilidad de ambas de forma objetiva."

---

## 3. Comparación entre las dos soluciones

### Estructura del código
* **Solución A (Imperativa / Tradicional):** Estructura básica e intuitiva basada en bucles `for` tradicionales, iteradores explícitos y condiciones `if` directas.
* **Solución B (Modernizada por IA):** Uso de la API `java.util.stream.Stream` en métodos como `masPopulares()` y `borrarAnteriores()`, reduciendo el número de líneas pero añadiendo abstracción visual.

### Facilidad de comprensión
* **Solución A:** Muy fácil de seguir paso a paso mediante depuración tradicional, idónea para entender el flujo básico de memoria de un `ArrayList`.
* **Solución B:** Requiere familiaridad previa con la sintaxis de expresiones Lambda y Streams de Java. Es más declarativa (dice *qué* hacer en lugar de *cómo* hacerlo).

### Complejidad del código
* **Solución A:** Complejidad ciclomática más alta debido a múltiples anidamientos de `for` e `if` (por ejemplo, para calcular la noticia con mayor número de lecturas en dos pasadas).
* **Solución B:** Menor complejidad ciclomática al delegar las búsquedas a métodos de la API como `stream().filter()`, `max()` y `Collectors.toList()`.

### Tratamiento de errores
* **Solución A:** Control directo mediante comprobaciones manuales de límites (p. ej. `if (noticias.size() < 1000)` e `if (noticias.isEmpty())`).
* **Solución B:** Incorporación de comprobaciones defensivas antes de las operaciones principales y uso de la clase `Optional` en métodos de búsqueda interna para evitar excepciones `NullPointerException`.

### Mantenibilidad
* **Solución A:** Si se requiere cambiar la estructura interna de almacenamiento, hay que modificar prácticamente todos los bucles manualmente.
* **Solución B:** Mayor modularidad. La separación de responsabilidades facilita refactorizar el tipo de colección sin alterar la lógica de negocio.

### Pruebas realizadas
En ambas soluciones se ejecutó la suite de pruebas desde la clase `App` contemplando:
1. **Creación e inserción:** Creación de varias noticias y verificación del límite de 1000 elementos.
2. **Búsqueda por fecha:** Verificación de `primeraNoticia` con fechas existentes e inexistentes (retorno correcto de `null`).
3. **Populares:** Impresión de las noticias más leídas, verificando que ante un empate se muestren todas ellas.
4. **Borrado:** Eliminación de noticias anteriores a una fecha límite con `borrarAnteriores` y posterior comprobación mediante `mostrar()`.

### Cumplimiento del enunciado
Ambas soluciones cumplen al 100% con las restricciones planteadas: uso del paquete `com.engiri`, cálculo numérico correcto de fechas `aaaammdd`, atributos privados, y firmas exactas de los métodos requeridos.

---

## 4. Errores o problemas detectados

1. **Bug en el cálculo e impresión de la fecha/hora (`toString` de `Noticia`):**
   * **Problema:** En el prompt inicial, la IA intentó optimizar el cálculo visual de la hora con `hora / 100` y `hora % 100`. Si la hora era `1005` (10:05), imprimía `10:5` al omitir el cero a la izquierda de los minutos.
   * **Solución:** Se corrigió el método utilizando `String.format("%02d:%02d", hora / 100, hora % 100)` para garantizar que siempre se muestren dos dígitos en las horas y minutos.

2. **Modificación concurrente al borrar elementos (`borrarAnteriores`):**
   * **Problema:** La IA sugirió inicialmente un bucle `for (int i = 0; i < noticias.size(); i++)` borrando con `noticias.remove(i)`. Esto provocaba que al eliminar un elemento, el índice saltara el siguiente elemento no comprobado.
   * **Solución:** Se corrigió usando `noticias.removeIf(n -> ...)` o iterando en sentido inverso (`for (int i = noticias.size() - 1; i >= 0; i--)`).

---

## 5. Reflexión personal sobre el uso de la IA

* **¿Qué ventajas has encontrado al utilizar IA?**
  * Reducción drástica del tiempo necesario para generar la estructura inicial del proyecto Maven y la clase de pruebas `App`.
  * Explicaciones claras sobre cómo solucionar problemas típicos de la lenguaje Java (como la concurrencia en `ArrayList`).

* **¿Qué inconvenientes has encontrado?**
  * Tendencia a generar respuestas asumir supuestos no indicados en el enunciado si no se ajusta el prompt con precisión.
  * Generación de bugs sutiles en operaciones matemáticas sencillas (como el formateo de horas/fechas) que exigen probar el código minuciosamente.

* **¿Qué solución entiendes mejor y por qué?**
  * Entiendo mejor la **Solución A (Imperativa)** porque el control de los índices y los bucles refleja el comportamiento paso a paso en memoria, aunque reconozco que la **Solución B (Streams)** es más limpia y moderna para proyectos reales.

* **¿Has encontrado algún error en el código generado por la IA?**
  * Sí, como se detalló en el apartado 4, se detectaron fallos en el formateo de minutos sin cero a la izquierda en `Noticia` y un salto de índice en la eliminación de elementos de `Periodico`.

* **¿Cambiarías algo de tu solución original después de realizar este reto?**
  * Sí. De la solución de la IA adoptaría la sintaxis `removeIf` para la eliminación de elementos en colecciones y el uso de `String.format` para simplificar la concatenación de textos.

---

## 6. Reflexión personal final

Este reto ha demostrado que la Inteligencia Artificial es un excelente asistente para agilizar tareas repetitivas y explorar diferentes enfoques de diseño en Java. Sin embargo, se ha puesto de manifiesto que **la IA no sustituye la necesidad de comprender el código**: es imprescindible contar con bases sólidas de programación para auditar las soluciones planteadas, detectar errores lógicos ocultos y asegurar que el software cumple strictly las especificaciones del proyecto.
