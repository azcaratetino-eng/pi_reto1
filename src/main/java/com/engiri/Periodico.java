package com.engiri;

import java.util.ArrayList;

public class Periodico {
    private static final int MAX_NOTICIAS = 1000;
    private ArrayList<Noticia> noticias;
    private int numNoticias;

    public Periodico() {
        this.noticias = new ArrayList<>();
        this.numNoticias = 0;
    }

    public void insertar(Noticia n) {
        if (numNoticias < MAX_NOTICIAS && n != null) {
            noticias.add(n);
            numNoticias++;
        }
    }

    public Noticia primeraNoticia(int d, int m, int a) {
        for (Noticia n : noticias) {
            if (n.igualFecha(d, m, a)) {
                return n;
            }
        }
        return null;
    }

    public void masPopulares() {
        if (noticias.isEmpty()) {
            System.out.println("No hay noticias en el periódico.");
            return;
        }

        int maxLecturas = -1;
        for (Noticia n : noticias) {
            if (n.getLecturas() > maxLecturas) {
                maxLecturas = n.getLecturas();
            }
        }

        System.out.println("--- NOTICIAS MÁS POPULARES (" + maxLecturas + " lecturas) ---");
        for (Noticia n : noticias) {
            if (n.getLecturas() == maxLecturas) {
                System.out.print(n.toString());
            }
        }
    }

    public void borrarAnteriores(int d, int m, int a) {
        int fechaLimite = a * 10000 + m * 100 + d;

        for (int i = 0; i < noticias.size(); i++) {
            if (noticias.get(i).getFecha() < fechaLimite) {
                noticias.remove(i);
                i--;
                numNoticias--;
            }
        }
    }

    public void mostrar() {
        if (noticias.isEmpty()) {
            System.out.println("El periódico no tiene noticias registradas.");
            return;
        }

        System.out.println("=== LISTADO COMPLETO DE NOTICIAS ===");
        for (Noticia n : noticias) {
            System.out.println(n.toString());
        }
    }
}