/*
Nombre del programa: Matriz
Descripcion: -
Fecha de creacion: 19/6
Autor: Sebastian Soto
Fecha de modificacion: 19/6
Modificado por: -
 */
package buscaminas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Buscaminas {

    static BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
//        int [][] prueba = new int[5][5];
//        imprimirMapa(prueba);
//        RutinasMinas.crearMinas(prueba);
//        imprimirMapa(prueba);
        setUp();
    }

    static void setUp() throws IOException {
        int opc;
        mostrarMenuInicio();
        opc = leerOpcion();
        ejecutarAccion(opc);
    }

    static void mostrarMenuInicio() {
        System.out.println("/-----------------/");
        System.out.println("| 1.  Facil (5x5) |");
        System.out.println("| 2.  Medio (8x8) |");
        System.out.println("/-----------------/");
    }

    static int leerOpcion() throws IOException {

        int opcion;

        System.out.print("Seleccione su modo de juego con el número correspondiente:\t");
        opcion = Integer.parseInt(leer.readLine());
        System.out.println();

        return opcion;
    }

    static void ejecutarAccion(int popcion) throws IOException {

        switch (popcion) {
            case 1:
                imprimirMapa(RutinasMinas.facilTerreno);
                RutinasMinas.setMines(RutinasMinas.facilCompleto);
                imprimirMapa(RutinasMinas.facilCompleto);
                break;
            case 2:
                imprimirMapa(RutinasMinas.medioTerreno);
                RutinasMinas.setMines(RutinasMinas.medioCompleto);
                imprimirMapa(RutinasMinas.medioCompleto);
                break;
            default: //Cualquier otro valor dado por el usuario se considera invalido

                System.out.println("Opcion invalida");
                System.out.println();
                break;
        }
//        EWTsadafs
    }

    public static void imprimirMapa(int[][] pmapa) {
        //Estos simbolos nunca cambian, se quedan siempre iguales
        char[] symbolos = RutinasMinas.SYMBOLS;
//      Imprime las coordenadas superiores
        for (int i = 0; i < pmapa[0].length + 1; i++) {
            if (i == 0) {
                System.out.print("Buscar\t");
            } else {
                System.out.print("[" + i + "]\t");
            }
        }
        System.out.println();
//      Linea divisoria
        for (int line = 0; line < pmapa[0].length; line++) {
            System.out.print("=========");
        }
        System.out.println();

//      Imprime el mapa y las coordenadas laterales
        for (int row = 0; row < pmapa.length; row++) {
            for (int col = 0; col < pmapa[row].length; col++) {

                if (col == 0) {
                    System.out.print("[" + (row + 1) + "]\t");
                }
                System.out.print(symbolos[pmapa[row][col]] + "\t");
            }
            System.out.println();
        }
    }

}
