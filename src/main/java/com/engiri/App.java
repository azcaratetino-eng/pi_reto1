package com.engiri;

public class App {
    public static void main(String[] args) {
        Periodico periodico = new Periodico();

        System.out.println("=== PRUEBA 1: CREAR NOTICIAS ===");
        Noticia n1 = new Noticia(20210930, 1005, "Lanzamiento de nuevo satélite.");
        Noticia n2 = new Noticia(20210930, 1230, "Aumento de las temperaturas este fin de semana.");
        Noticia n3 = new Noticia(20211015, 915, "Inauguración de la biblioteca municipal.");
        Noticia n4 = new Noticia(20211101, 1845, "Resultados del torneo regional de ajedrez.");

        // Simulación de lecturas
        n1.incLecturas();
        n1.incLecturas();
        n1.incLecturas(); // 3 lecturas

        n2.incLecturas(); // 1 lectura

        n3.incLecturas();
        n3.incLecturas();
        n3.incLecturas(); // 3 lecturas (Empatada con n1)

        System.out.println("Noticias creadas correctamente.\n");

        System.out.println("=== PRUEBA 2: INSERTAR ===");
        periodico.insertar(n1);
        periodico.insertar(n2);
        periodico.insertar(n3);
        periodico.insertar(n4);
        periodico.mostrar();

        System.out.println("=== PRUEBA 3: PRIMERA NOTICIA ===");
        Noticia primera = periodico.primeraNoticia(30, 9, 2021);
        if (primera != null) {
            System.out.println("Primera noticia encontrada para 30/09/2021:\n" + primera);
        } else {
            System.out.println("No se encontró ninguna noticia para esa fecha.");
        }

        System.out.println("=== PRUEBA 4: MÁS POPULARES ===");
        periodico.masPopulares();

        System.out.println("=== PRUEBA 5: BORRAR ANTERIORES ===");
        System.out.println("Borrando noticias anteriores al 01/10/2021...");
        periodico.borrarAnteriores(1, 10, 2021);

        System.out.println("\nEstado del periódico tras el borrado:");
        periodico.mostrar();
    }
}