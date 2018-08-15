/*
Nombre del programa: Matriz
Descripcion: -
Fecha de creacion: 19/6
Autor: Sebastian Soto
Fecha de modificacion: 19/6
Modificado por: -
 */
package buscaminas;

public class RutinasMinas {

    public static int[][] arregloPositions;
    //    Mapa 3x3 para juego facil (lo que el usuario ve)
    public static int[][] facilJuego = {
        {11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11},};

    //    Donde esta el terreno de juego
    public static int[][] facilCompleto = new int[5][5];

    //    Mapa 8x8 para juego medio (lo que el usuario ve)
    public static int[][] medioJuego = {
        {11, 11, 11, 11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11, 11, 11, 11},};
    public static int[][] medioCompleto = new int[8][8];

    /*
    0 => - (No minas)      |1 => 1     |2 => 2     |3 => 3     |4 => 4     
    |5 => 5     |6 => 6     |7 => 7     |8 => 8        |9 => X (Mina)       |10 => M (Marca) |11 => # (Terreno cubierto)*/
    public static char[] SYMBOLS = {'-', '1', '2', '3', '4', '5', '6', '7', '8', 'X', 'M', '#', '~'};

    /*
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

    public static int currentPositionValue(boolean position, int[][] pmapa, int row, int col) {
        int counter = 0;
        if (position) {
            if (pmapa[row][col] == 9) {
                counter++;
            }
        }
        return counter;
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
        boolean rightExist = checkExistence(length, row, rightCol);

        currentPosition = currentPositionValue(leftExist, pmapa, row, leftCol) + currentPositionValue(rightExist, pmapa, row, rightCol)
                + currentPositionValue(botRightExist, pmapa, botRow, rightCol) + currentPositionValue(botLeftExist, pmapa, botRow, leftCol) + currentPositionValue(botExist, pmapa, botRow, col)
                + currentPositionValue(topRightExist, pmapa, topRow, rightCol) + currentPositionValue(topLeftExist, pmapa, topRow, leftCol) + currentPositionValue(topExist, pmapa, topRow, col);

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

    /*
    ESTO ES PARA DESCUBRIR UN ESPACIO
    ESTO ES PARA DESCUBRIR UN ESPACIO
     */
    public static boolean descubrirEspacio(int[][] juego, int[][] completo, int row, int col) {
        boolean win = true;
        if (completo[row][col] == 9) {
            win = false;
        } else if (completo[row][col] == 0) {
            clearSpace(juego, completo, row, col);
            clearInGame(juego, completo);
        } else {
            juego[row][col] = completo[row][col];
        }
        return win;
    }

    public static int[][] clearInGame(int[][] juego, int[][] completo) {

        for (int i = 0; i < completo.length; i++) {
            for (int j = 0; j < completo[i].length; j++) {
                if (completo[i][j] == 12) {
                    
                    arrayClear(juego, completo, i, j);
                }
            }
        }
        return juego;
    }

    public static void arrayClear(int[][] juego, int[][] completo, int row, int col) {
        int length = juego.length;

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
        boolean rightExist = checkExistence(length, row, rightCol);

        clearArea(juego, completo, topExist, topRow, col);
        clearArea(juego, completo, topLeftExist, topRow, leftCol);
        clearArea(juego, completo, leftExist, row, leftCol);
        clearArea(juego, completo, botLeftExist, botRow, leftCol);
        clearArea(juego, completo, botExist, botRow, col);
        clearArea(juego, completo, botRightExist, botRow, rightCol);
        clearArea(juego, completo, rightExist, row, rightCol);
        clearArea(juego, completo, topRightExist, topRow, rightCol);
        
        

    }

    public static void clearArea(int[][] juego, int[][] completo, boolean exists, int row, int col) {
        if (exists) {
            juego[row][col] = completo[row][col];
        }
    }

    public static String clearSpace(int[][] juego, int[][] completo, int row, int col) {

        String coordenadas = "";

        coordenadas = getCoordinates(completo, row, col, coordenadas);

        coordenadas = reuseString(completo, coordenadas);

        return coordenadas;
    }

//    Reusa el String, pero le quita la primer pos
    public static String reuseString(int[][] completo, String coordenadas) {

        while (coordenadas.length() > 1) {
            StringBuffer check = new StringBuffer(coordenadas);
            int x = Integer.parseInt(Character.toString(check.charAt(0)));
            int y = Integer.parseInt(Character.toString(check.charAt(1)));
            check.replace(0, 2, "");
            coordenadas = check.toString();
            coordenadas = getCoordinates(completo, x, y, coordenadas);

        }
        return coordenadas;
    }

//    Toma todas las coordenadas de una position
    private static String getCoordinates(int[][] completo, int row, int col, String coordenadas) {
        int length = completo.length;
        completo[row][col] = 12;
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
        boolean rightExist = checkExistence(length, row, rightCol);

        coordenadas = isClear(topExist, topRow, col, completo, coordenadas);
        coordenadas = isClear(topLeftExist, topRow, leftCol, completo, coordenadas);
        coordenadas = isClear(leftExist, row, leftCol, completo, coordenadas);
        coordenadas = isClear(botLeftExist, botRow, leftCol, completo, coordenadas);
        coordenadas = isClear(botExist, botRow, col, completo, coordenadas);
        coordenadas = isClear(botRightExist, botRow, rightCol, completo, coordenadas);
        coordenadas = isClear(rightExist, row, rightCol, completo, coordenadas);
        coordenadas = isClear(topRightExist, topRow, rightCol, completo, coordenadas);

        return coordenadas;
    }

//    Agrega la position al string en caso de no pertenecer al mismo.
    public static String isClear(boolean exists, int row, int col, int[][] completo, String position) {

        if (exists) {
            if (completo[row][col] == 0) {
                if (position.length() == 0) {
                    position += row + "" + col;
                } else {
                    boolean contains = false;
                    for (int i = 0; i <= position.length() - 2; i += 2) {
                        if (position.substring(i, i + 2).contains(row + "" + col)) {
                            contains = true;
                        }
                    }
                    if (!contains) {
                        position += row + "" + col;
                    }
                }
            }
        }

        return position;
    }

    public static void marcarEspacio(int[][] juego, int row, int col) {
        juego[row][col] = 10;
    }
}
