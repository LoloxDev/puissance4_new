import java.util.Arrays;

public class Main {

    /**
     * Taille des colonnes du plateau
     */
    final static int COL_SIZE=7;

    /***
     * Taille des lignes du plateau
     * */
    final static int ROW_SIZE=6;

    /**
     * Plateau de jeu
     */

    static int[][] board=new int[COL_SIZE][ROW_SIZE];

    public static void main(String[] args) {

        //board[3] = new int[]{0,1,1,1,1,1};
        System.out.println(Arrays.toString(placementJeton(3,1)));

    }

    public static Object[] placementJeton(int colonne, int nmDuJoueur){

        Object[] placeTrouve = { false , "" };

        for (int i=ROW_SIZE-1 ; !(boolean) placeTrouve[0] && i>=0 ; i--){
            if(board[colonne][i] == 0){
                board[colonne][i] = nmDuJoueur;
                placeTrouve[1] = i;
                placeTrouve[0] = true;
            }
        }
        return placeTrouve;
    }
}
