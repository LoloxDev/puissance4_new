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

    static int[][] board=new int[COL_SIZE][ROW_SIZE]; // Ici on déclare nos tableaux pour le jeu.

    static void affichageGrill(){ // Ici, on affiche la grille
        for(int i = 0; i < ROW_SIZE; i++) //
        {
            for(int j = 0; j < COL_SIZE; j++) //
            {
                System.out.printf("[%1d]", board[j][i]);
            }
            System.out.println();
        }
    }

    static String[] initDuJeu() { // Ici, on déclare et on initialise nos joueurs
        Scanner clavier = new Scanner(System.in);
        System.out.println("Veuillez saisir le nom du joueur 1");
        String joueur1 = clavier.next();
        System.out.println("Veuillez saisir le nom du joueur 2");
        String joueur2 = clavier.next();
        String[] joueurs = {joueur1,joueur2};

        return joueurs;
    }

    static boolean tourDeJeu(String[] joueurs){

        Scanner clavier = new Scanner(System.in);
        int whoStart = (Math.random() <= 0.5) ? 0 : 1;
        int whoPlay = whoStart;
        System.out.println("C'est " + joueurs[whoStart]+ " qui commence");  //<-- ++ opti
        int tour = whoStart;
        do{
            if(tour%2 == 0){
                System.out.println("C'est le tour du " + joueurs[0]);
                whoPlay = 1;
            }else{
                System.out.println("C'est le tour du " + joueurs[1]);
                whoPlay = 2;
            }
            affichageGrill();
            tour++;
            System.out.println("Ou voulez vous placer votre jeu ?");
            int colonne = clavier.nextInt();
            placementJeton(colonne, whoPlay);

        }while(tour != 15);
        return true;
    }

    public static void main(String[] args) {
        //initialisation du jeu
        String[] joueur = initDuJeu();
        tourDeJeu(joueur);

        //board[3] = new int[]{0,0,0,0,1,1};   // <-- Sert à tester la fonction placementJeton
        //System.out.println(Arrays.toString(placementJeton(3,1)));

    }

    public static Object[] placementJeton(int colonne, int nmDuJoueur){

        Object[] placeTrouve = { false , "" };     // <--- Sers à retourner l'index de la case joué, et si une place à été trouvée.

        for (int i=ROW_SIZE-1 ; !(boolean) placeTrouve[0] && i>=0 ; i--){ // On parcours la colonne, pour voir s'il y a une place, si oui, on prends la première, sinon on refuse le placement.
            if(board[colonne][i] == 0){
                board[colonne][i] = nmDuJoueur;
                placeTrouve[1] = i;
                placeTrouve[0] = true;
            }
        }
        if(!(boolean) placeTrouve[0]){
            System.out.println("Choisissez une autre colonne, celle ci est pleine !");
            // Rappeler la fonction, attention à lé récursivité
        }
        verifVictoire(colonne, (int) placeTrouve[1], nmDuJoueur);
        return placeTrouve;

    }

    public static void verifVictoire(int posX, int posY, int nmDuJoueur){

        int winX = 1;
        int winY = 1;

        // Ici on calcule le nombre de points allignés à l'horizontal
        for(int i=1; i<3; i++){

            if(board[posY][posX-i] == nmDuJoueur){
                winX++;
            }

            if(board[posY][posY-i] == nmDuJoueur){
                winX++;
            }

            youWin(winX, winY, nmDuJoueur);
        }

        // Ici on calcule le nombre de points allignés à la vertical
        for(int i=1; i<3; i++){

            if(board[posY][posX+i] == nmDuJoueur){
                winX++;
            }

            if(board[posY][posY+i] == nmDuJoueur){
                winX++;
            }

            youWin(winX, winY, nmDuJoueur);
        }

        // Ici on reset les compteurs
        if (!youWin(winX, winY, nmDuJoueur)){
            //winX == 1;
            //winY == 1;
        }

    }

    public static boolean youWin(int winX, int winY, int nmDuJoueur){
        boolean youWin;
        if (winX >= 4 || winY >= 4){
            youWin = true;
            System.exit(0);
            System.out.println("Félicitations au joueur " + nmDuJoueur + " qui remporte la partie !");
        } else {
            youWin = false;
        }

        return youWin;
    }



    //






}
