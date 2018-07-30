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
        setUp();
    }

    /*
    Estos procedimientos son para generar el mapa  \\
    Estas procedimientos son para generar el mapa  VVV
    Estos procedimientos son para generar el mapa   V
     */
    static void setUp() throws IOException {
        int opc;
        mostrarMenuInicio();
        opc = leerOpcionSetUp();
        ejecutarAccionSetUp(opc);
    }

    static void mostrarMenuInicio() {
        System.out.println("/-----------------/");
        System.out.println("| 1.  Facil (5x5) |");
        System.out.println("| 2.  Medio (8x8) |");
        System.out.println("/-----------------/");
    }

    static int leerOpcionSetUp() throws IOException {

        int opcion;

        System.out.print("Seleccione su modo de juego con el número correspondiente:\t");
        opcion = Integer.parseInt(leer.readLine());
        System.out.println();

        return opcion;
    }

    static void ejecutarAccionSetUp(int popcion) throws IOException {
//      En esto debo crear el terreno de juego (seleccionas el modo de juego, generar las bombas, acomodar los numeros)
        switch (popcion) {
            case 1:
                imprimirMapa(RutinasMinas.facilTerreno);
                RutinasMinas.setMines(RutinasMinas.facilCompleto);
                RutinasMinas.generaMapa(RutinasMinas.facilCompleto);
                imprimirMapa(RutinasMinas.facilCompleto);
                break;
            case 2:
                imprimirMapa(RutinasMinas.medioTerreno);
                RutinasMinas.setMines(RutinasMinas.medioCompleto);
                RutinasMinas.generaMapa(RutinasMinas.medioCompleto);
                imprimirMapa(RutinasMinas.medioCompleto);
                break;
            default: //Cualquier otro valor dado por el usuario se considera invalido

                System.out.println("Opcion invalida");
                System.out.println();
                break;
        }
    }

    /*
    Estos procedimientos son para generar el mapa   A
    Estas procedimientos son para generar el mapa  AAA
    Estos procedimientos son para generar el mapa  ||
     */
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
                int celdaActual = pmapa[row][col];

                System.out.print(symbolos[celdaActual] + "\t");

            }
            System.out.println("\n");
        }
        //      Linea divisoria
        for (int line = 0; line < pmapa[0].length; line++) {
            System.out.print("=========");
        }
        System.out.println();
    }

}
