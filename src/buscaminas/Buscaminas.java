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
//        Esto genera el mapa con las posiciones que e1lija el usuario
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

    static void juego(int[][] juego, int[][] completo) throws IOException {
        boolean win = false;
        do {
            int opc;
            mostrarMenuJuego();
            opc = leerOpcionJuego();
            win = ejecutarAccionJuego(opc, juego, completo);
            if(win){
                if(RutinasMinas.checkWin(juego, completo)){
                    imprimirGane();
                    break;
                }
            }
        } while (win);
    }

    static void mostrarMenuInicio() {
        System.out.println("/-----------------/");
        System.out.println("| 1.  Facil (5x5) |");
        System.out.println("| 2.  Medio (8x8) |");
        System.out.println("/-----------------/");
    }

    static void mostrarMenuJuego() {
        System.out.println("/-----------------/");
        System.out.println("| 1.  Descubrir   |");
        System.out.println("| 2.  Marcar      |");
        System.out.println("| 3.  Desmarcar   |");
        System.out.println("| 4.  Rendirse    |");
        System.out.println("/-----------------/");
    }

    static void textoPerder() {
        System.out.println("/-----------------/\t/-----------------/\t/-----------------/");
        System.out.println("| Mejor suerte la |\t| Mejor suerte la |\t| Mejor suerte la |");
        System.out.println("|     proxima!    |\t|     proxima!    |\t|     proxima!    |");
        System.out.println("/-----------------/\t/-----------------/\t/-----------------/");
    }
    
    static void imprimirGane(){
        System.out.println("/-----------------/\t/-----------------/\t/-----------------/");
        System.out.println("|   FELICIDADES!  |\t|   FELICIDADES!  |\t|   FELICIDADES!  |");
        System.out.println("|     GANASTE!    |\t|     GANASTE!    |\t|     GANASTE!    |");
        System.out.println("/-----------------/\t/-----------------/\t/-----------------/");
    }

    static int leerOpcionJuego() throws IOException {

        int opcion;

        System.out.print("Seleccione lo que desea hacer con el número correspondiente:\t");
        opcion = Integer.parseInt(leer.readLine());
        System.out.println();

        return opcion;
    }

    static boolean ejecutarAccionJuego(int popcion, int[][] juego, int[][] completo) throws IOException {
//      En esto debo crear el terreno de juego (seleccionas el modo de juego, generar las bombas, acomodar los numeros)
        int row = -1;
        int col = -1;
        boolean win = true;
        switch (popcion) {
            case 1:
                row = leerRow();
                col = leerCol();
                
                if(row < 0 || row > juego.length-1 || col < 0 || col > juego.length-1){
                    
                    System.out.println("Esa coordenada no existe.");
                    break;
                }
                espacioVisto(row, col, juego, completo);
                win = RutinasMinas.descubrirEspacio(juego, completo, row, col);
                if (win == true) {
                    imprimirMapa(juego);
                } else {
                    imprimirMapa(completo);
                    textoPerder();
                }
                break;
            case 2:
                row = leerRow();
                col = leerCol();
                if(row < 0 || row > juego.length-1 || col < 0 || col > juego.length-1){
                    
                    System.out.println("Esa coordenada no existe.");
                    break;
                }
                boolean res = RutinasMinas.marcarEspacio(juego, row, col);
                if(!res){
                    imprimirMapa(juego);
                }else{
                    System.out.println("Ya no se pueden marcar más espacios");
                }
                
                break;

            case 3:
                row = leerRow();
                col = leerCol();
                if(row < 0 || row > juego.length-1 || col < 0 || col > juego.length-1){
                    
                    System.out.println("Esa coordenada no existe.");
                    break;
                }
                RutinasMinas.desmarcarEspacio(juego, completo, row, col);
                imprimirMapa(juego);
                break;

            case 4:
                imprimirMapa(completo);
                textoPerder();
                win = false;
                break;

            default: //Cualquier otro valor dado por el usuario se considera invalido

                System.out.println("Opcion invalida");
                System.out.println();
                break;
        }
        return win;
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
                RutinasMinas.setMines(RutinasMinas.facilCompleto);
                RutinasMinas.generaMapa(RutinasMinas.facilCompleto);
                RutinasMinas.setCantMarcas(RutinasMinas.facilCompleto);
                imprimirMapa(RutinasMinas.facilJuego);
                juego(RutinasMinas.facilJuego, RutinasMinas.facilCompleto);
                break;
            case 2:
                RutinasMinas.setMines(RutinasMinas.medioCompleto);
                RutinasMinas.generaMapa(RutinasMinas.medioCompleto);
                RutinasMinas.setCantMarcas(RutinasMinas.medioCompleto);
                imprimirMapa(RutinasMinas.medioJuego);
                juego(RutinasMinas.medioJuego, RutinasMinas.medioCompleto);
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
    public static void espacioVisto(int row, int col, int[][] juego, int[][] completo) {
        if (juego[row][col] == completo[row][col]) {
            System.out.println("________________________________________");
            System.out.println();
            System.out.println("Este espacio ya fue revelado previamente");
            System.out.println("________________________________________");
            System.out.println();
        }
    }

    public static void imprimirMapa(int[][] pmapa) {
//        En este espacio se imprime la cantidad de marcas que han sido utilizadas

        int cantMarcas = RutinasMinas.getCantMarcas();
        System.out.println("|------------------------|");
        System.out.println("|            " + cantMarcas + "           |");
        System.out.println("|    Marcas restantes    |");
        System.out.println("|------------------------|");

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

    public static int leerRow() throws IOException {
        System.out.print("Fila del espacio que desea usar:\t");
        int row = Integer.parseInt(leer.readLine());
        return row - 1;
    }

    public static int leerCol() throws IOException {
        System.out.print("Columna del espacio que desea usar:\t");
        int col = Integer.parseInt(leer.readLine());
        return col - 1;
    }
}
