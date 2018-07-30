/*
Nombre del programa: Matriz
Descripcion: -
Fecha de creacion: 19/6
Autor: Sebastian Soto
Fecha de modificacion: 19/6
Modificado por: -
 */
package buscaminas;

import java.util.Arrays;

public class RutinasMinas {

    //    Mapa 3x3 para juego facil (lo que el usuario ve)
    public static int[][] facilTerreno = {
        {11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11},};

    //    Donde esta el terreno de juego
    public static int[][] facilCompleto = new int[5][5];

    //    Mapa 8x8 para juego medio (lo que el usuario ve)
    public static int[][] medioTerreno = {
        {11,11,11,11,11,11,11,11},
        {11,11,11,11,11,11,11,11},
        {11,11,11,11,11,11,11,11},
        {11,11,11,11,11,11,11,11},
        {11,11,11,11,11,11,11,11},
        {11,11,11,11,11,11,11,11},
        {11,11,11,11,11,11,11,11},
        {11,11,11,11,11,11,11,11},
    };
    public static int[][] medioCompleto = new int[8][8];

    /*
    0 => - (No minas)      |1 => 1     |2 => 2     |3 => 3     |4 => 4     
    |5 => 5     |6 => 6     |7 => 7     |8 => 8        |9 => X (Mina)       |10 => M (Marca) |11 => # (Terreno cubierto)*/
    public static char[] SYMBOLS = {'-', '1', '2', '3', '4', '5', '6', '7', '8', 'X', 'M', '#'};

    /*
    TODO ESTO ES PARA CREAR LAS BOMBAS Y PONERLAS EN EL MAPA
    TODO ESTO ES PARA CREAR LAS BOMBAS Y PONERLAS EN EL MAPA
    TODO ESTO ES PARA CREAR LAS BOMBAS Y PONERLAS EN EL MAPA
     */
    public static void setMines(int[][] pmapa) {
        int[][] coordenadas = crearCoordenadas(pmapa);
        for (int i = 0; i < pmapa.length; i++) {
            int row = coordenadas[i][0];
            int col = coordenadas[i][1];

            pmapa[row][col] = 9;
        }
    }

    public static int[][] crearCoordenadas(int[][] pmapa) {
        int dim = pmapa.length;

        int[][] positions = new int[dim][2];

//      String que contiene las posiciones.
        String checkPositions = generatePositions(dim);

//      Estos for transforman el string de posiciones en las coordenadas en un arreglo [dim][2]
        int current = 0;
        for (int i = 0; i < positions.length; i++) {
            for (int j = 0; j < positions[i].length; j++) {

//              Esto guarda en dicha posicion la coordenada correspondiente
                positions[i][j] = Integer.parseInt(Character.toString(checkPositions.charAt(current)));
                current += 1;

            }
        }
        return positions;
    }

//  Retorna un String de posiciones diferentes (van en parejas substring(0,2))
    public static String generatePositions(int dim) {
        String positions = "";
//      Hace que checkPositions tenga las coordenadas diferentes en formato string
        while (positions.length() < dim * 2) {
//          Creacion del x - y a validar.
            int randomRow = randomRango(dim);
            int randomCol = randomRango(dim);
//          String simple con las coordenadas
            String currentPair = Integer.toUnsignedString(randomRow) + Integer.toUnsignedString(randomCol);
//          Esto solo agrega las posiciones si no han sido establecidas previamente
            if (!positions.contains(currentPair)) {
                positions += currentPair;
            }
        }
        return positions;
    }

//  Genera un numero aleatorio entero desde 0 al maximo
    public static int randomRango(int max) {
        return (int) (Math.random() * max);
    }

    /*
    ESTO ES PARA GENERAR LOS NUMEROS QUE RODEAN LAS MINAS
    ESTO ES PARA GENERAR LOS NUMEROS QUE RODEAN LAS MINAS
    ESTO ES PARA GENERAR LOS NUMEROS QUE RODEAN LAS MINAS
     */
    public static void generaMapa(int[][] pmapa) {
        for (int i = 0; i < pmapa.length; i++) {
            for (int j = 0; j < pmapa[i].length; j++) {
                if (!(pmapa[i][j] == 9)) {
                    buscaMina(pmapa, i, j);
                }
            }
        }
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
        }
        if (topLeftExist) {
            int topLeft = pmapa[topRow][leftCol];
            if (topLeft == 9) {
                currentPosition++;
            }
        }
        if (leftExist) {
            int left = pmapa[row][leftCol];
            if (left == 9) {
                currentPosition++;
            }
        }
        if (botLeftExist) {
            int botLeft = pmapa[botRow][leftCol];
            if (botLeft == 9) {
                currentPosition++;
            }
        }
        if (botExist) {
            int bot = pmapa[botRow][col];
            if (bot == 9) {
                currentPosition++;
            }
        }
        if (botRightExist) {
            int botRight = pmapa[botRow][rightCol];
            if (botRight == 9) {
                currentPosition++;
            }
        }
        if (rightExist) {
            int right = pmapa[row][rightCol];
            if (right == 9) {
                currentPosition++;
            }
        }
        if (topRightExist) {
            int topRight = pmapa[topRow][rightCol];
            if (topRight == 9) {
                currentPosition++;
            }
        }
        pmapa[row][col] = currentPosition;
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
}
