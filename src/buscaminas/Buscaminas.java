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
        int[][] prueba = {{11, 11, 11}, {11, 11, 11}, {11, 9, 11}};
        imprimirMapa(prueba);
        buscaMina(prueba, 1,0);
    }

    public static int buscaMina(int[][] pmapa, int row, int col) {
        int currentPosition = 0;
//      Verifica que el row y el col esten en el rango del arreglo
        int length = pmapa.length;

        int topRow = row - 1;
        int botRow = row + 1;
        int leftCol = col - 1;
        int rightCol = col + 1;
        
        boolean topExist = checkExistence(length, topRow, col);
        boolean topLeftExist = checkExistence(length, topRow, leftCol);
        boolean topRightExist = checkExistence(length, topRow, rightCol);
        boolean botExist = checkExistence(length, botRow, col);
        boolean botLeftExist = checkExistence(length, botRow, leftCol);
        boolean botRightExist = checkExistence(length, botRow, rightCol);
        boolean leftExist = checkExistence(length, row, leftCol);
        boolean rightExist = checkExistence(length, botRow, rightCol);
        
        
        if (topExist) {
            int top = pmapa[topRow][col];
            if (top == 9) {
                currentPosition++;
            }
            System.out.println("true top");
        }
        if (topLeftExist) {
            int topLeft = pmapa[topRow][leftCol];
            if (topLeft == 9) {
                currentPosition++;
            }
            System.out.println("true top left");
        }
        if (leftExist) {
            int left = pmapa[row][leftCol];
            if (left == 9) {
                currentPosition++;
            }
            System.out.println("true left");
        }
        if (botLeftExist) {
            int botLeft = pmapa[botRow][leftCol];
            if (botLeft == 9) {
                currentPosition++;
            }
            System.out.println("true bot left");
        }
        if (botExist) {
            int bot = pmapa[botRow][col];
            if (bot == 9) {
                currentPosition++;
            }
            System.out.println("true bot");
        }
        if (botRightExist) {
            int botRight = pmapa[botRow][rightCol];
            if (botRight == 9) {
                currentPosition++;
            }
            System.out.println("true bot right");
        }
        if (rightExist) {
            int right = pmapa[row][rightCol];
            if (right == 9) {
                currentPosition++;
            }
            System.out.println("true right");
        }
        if (topRightExist) {
            int topRight = pmapa[topRow][rightCol];
            if (topRight == 9) {
                currentPosition++;
            }
            System.out.println("true top right");
        }
        
        
        System.out.println(currentPosition);
        return currentPosition;
    }

    public static boolean checkExistence(int length, int row, int col) {
        boolean rowExist = true;
        boolean colExist = true;
        boolean positionExists = false;
        if (row < 0 || row > length - 1) {
            rowExist = false;
        }
        if (col < 0 || col > length - 1) {
            colExist = false;
        }
        if (rowExist && colExist) {
            positionExists = true;
        }
        return positionExists;
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

        switch (popcion) {
            case 1:
                imprimirMapa(RutinasMinas.facilTerreno);
                RutinasMinas.setMines(RutinasMinas.facilTerreno);
                imprimirMapa(RutinasMinas.facilTerreno);
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
                if (celdaActual < 0 || celdaActual > symbolos.length - 1) {
                    System.out.println("404");
                } else {
                    System.out.print(symbolos[celdaActual] + "\t");
                }
            }
            System.out.println();
        }
    }

}
