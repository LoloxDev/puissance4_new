import java.util.Arrays;
import java.util.Scanner;

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
        //initialisation du jeu
        String[] joueur = initDuJeu();
        tourDeJeu(joueur);
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



    //
    static String[] initDuJeu() {
        Scanner clavier = new Scanner(System.in);
        System.out.println("Veuillez saisir le nom du joueur 1");
        String joueur1 = clavier.next();
        System.out.println("Veuillez saisir le nom du joueur 2");
        String joueur2 = clavier.next();
        String[] joueurs = {joueur1,joueur2};

        int whoStart = (Math.random() <= 0.5) ? 1 : 2;
        System.out.println("C'est " + joueurs[whoStart]+ " qui commence");  //<-- ++ opti
        return joueurs;

        //switch(whoStart){
        //case 1:
        //    System.out.println("C'est " + joueurs[0]+ " qui commence");
        //    break;
        //                                                                    //<-- + opti
        //case 2:
        //    System.out.println("C'est " + joueurs[1] + "qui commence");
        //    break;
        //}
    }

    static boolean tourDeJeu(String[] joueurs){
        int tour = 0;
        do{
            if(tour%2 == 0){
                System.out.println("C'est le tour du " + joueurs[0]);
                affichageGrill();
                tour++;
            }else{
                System.out.println("C'est le tour du " + joueurs[1]);
                affichageGrill();
                tour++;
            }
        }while(tour != 15);
        return true;
    }
    static void affichageGrill(){
        int [][]grid = new int[6][7];
        for(int i = 0; i < 6; i++)
        {
            for(int j = 0; j < 7; j++)
            {
                System.out.printf("%2d ", grid[i][j]);
            }
            System.out.println();
        }
    }


}
