
import java.util.Scanner;

public class Main {

    /**
     * Taille des colonnes du plateau
     */
    final static int X_POS=7;

    /***
     * Taille des lignes du plateau
     * */
    final static int Y_POS=6;

    /**
     * Plateau de jeu
     */

    static int[][] board=new int[X_POS][Y_POS]; // Ici on déclare nos tableaux pour le jeu.

    static void affichageGrill(){ // Ici, on affiche la grille
        for(int i = 0; i < Y_POS; i++) //
        {
            for(int j = 0; j < X_POS; j++) //
            {
                if(board[j][i] == 0){
                    System.out.printf("|%1s|", "-");
                }else if(board[j][i] == 1){
                    System.out.printf("|%1s|", "\u001B[33m"+"O"+"\u001B[0m");
                }else{
                    System.out.printf("|%1s|","\u001B[31m"+"X"+"\u001B[0m");
                }
            }
            System.out.println();
        }
    }


    static String[] initDuJeu() { // Ici, on déclare et on initialise nos joueurs
        Scanner clavier = new Scanner(System.in);
        System.out.println("Veuillez saisir le nom du joueur 1");
        String[] joueurs = {clavier.next(),""};
        System.out.println("Veuillez saisir le nom du joueur 2");
        joueurs[1] = clavier.next();

        return joueurs;
    }

    static void tourDeJeu(String[] joueurs){

        Scanner clavier = new Scanner(System.in);
        int whoStart = (Math.random() <= 0.5) ? 0 : 1;
        System.out.println("C'est " + joueurs[whoStart]+ " qui commence");  //<-- ++ opti
        int tour = whoStart;
        do{
            if(tour%2 == 0){
                System.out.println("C'est le tour du " + joueurs[0]);
                whoStart = 1;
            }else{
                System.out.println("C'est le tour du " + joueurs[1]);
                whoStart = 2;
            }
            affichageGrill();
            System.out.println("Ou voulez vous placer votre jeu ?");
            int colonne = clavier.nextInt();
            if (colonne > 6 || colonne < 0){
                System.out.println("veuillez saisir un chiffre entre 0 et 6");
            }else{
                placementJeton(colonne, whoStart);
                tour++;
            }






        }while(tour != 15);
    }

    public static void main(String[] args) {
        //initialisation du jeu
        String[] joueur = initDuJeu();
        tourDeJeu(joueur);

    }

    public static void placementJeton(int colonne, int nmDuJoueur){

        Object[] placeTrouve = { false , "" };     // <--- Sers à retourner l'index de la case joué, et si une place à été trouvée.

        for (int i=Y_POS-1 ; !(boolean) placeTrouve[0] && i>=0 ; i--){ // On parcours la colonne, pour voir s'il y a une place, si oui, on prends la première, sinon on refuse le placement.
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
    }

    public static void verifVictoire(int posX, int posY, int nmDuJoueur){


        // Ici on calcule l'axe Y via méthode 1 : boucle for
        int winX = 1;
        int winY = 1;

        for(int i=posY+1; i <= 5 ; i++){
            if(board[posX][i] == nmDuJoueur){
                winY++;
            } else {
                winY = 1;
            }
        }

        // Ici on caclcule l'axe X vers la droite via méthode 2 : boucle while
        boolean boucle = true;

        while (posX < 6 && boucle){

            if(board[(posX+1)][posY] == nmDuJoueur){
                winX++;
            } else {
                   boucle = false;
            }
            posX++;
        }

        // Puis vers la gauche
        boucle = true;

        while (posX > 0 && boucle){

            if(board[(posX-1)][posY] == nmDuJoueur){
                winX++;
            } else {
                boucle = false;
            }
            posX--;
        }

        youWin(winX, winY, nmDuJoueur);

    }

    public static void youWin(int winX, int winY, int nmDuJoueur){
        if (winX >= 4 || winY >= 4){
            System.out.println("Félicitations au joueur " + nmDuJoueur + " qui remporte la partie !");
            //System.exit(0);
        }
    }
}
