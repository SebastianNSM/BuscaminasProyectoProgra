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

    //    Mapa 3x3 para juego facil
    public static int[][] facilTerreno = {
        {11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11},};
    public static int[][] facilCompleto = new int[5][5];

    //    Mapa 8x8 para juego medio
    public static int[][] medioTerreno = {
        {11, 11, 11, 11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11, 11, 11, 11},
        {11, 11, 11, 11, 11, 11, 11, 11}
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
    public static void setMines (int [][] pmapa){
        int [][] coordenadas = crearCoordenadas(pmapa);
        for(int i=0;i<pmapa.length;i++){
            int row = coordenadas[i][0];
            int col = coordenadas[i][1];
            
            pmapa[row][col] = 9;
        }
    }
    
    public static int [][] crearCoordenadas(int[][] pmapa) {
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
        System.out.println("Positions: "+Arrays.deepToString(positions));
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
    TODO ESTO ES PARA CREAR LAS BOMBAS Y PONERLAS EN EL MAPA
    TODO ESTO ES PARA CREAR LAS BOMBAS Y PONERLAS EN EL MAPA
    TODO ESTO ES PARA CREAR LAS BOMBAS Y PONERLAS EN EL MAPA
    */
}
