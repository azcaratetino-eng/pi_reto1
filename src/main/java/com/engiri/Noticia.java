package com.engiri;

public class Noticia {
    private int fecha;     // Formato aaaammdd
    private int hora;      // Formato hhmm
    private String texto;  // Texto de la noticia
    private int lecturas;  // Número de veces leída

    public Noticia(int fecha, int hora, String texto) {
        this.fecha = fecha;
        this.hora = hora;
        this.texto = texto;
        this.lecturas = 0;
    }

    public int getFecha() {
        return fecha;
    }

    public int getLecturas() {
        return lecturas;
    }

    public void incLecturas() {
        this.lecturas++;
    }

    public boolean igualFecha(int d, int m, int a) {
        int fechaFormateada = a * 10000 + m * 100 + d;
        return this.fecha == fechaFormateada;
    }

    @Override
    public String toString() {
        String s = "";
        s += String.format("%02d/%02d/%04d-%02d:%02d\n",
                fecha % 100, (fecha / 100) % 100, fecha / 10000, hora / 100, hora % 100);
        s += texto + "\n";
        s += "Leída " + lecturas + " veces\n";
        return s;
    }
}