
import java.util.Scanner;

public class Main {

    /**
     * On initialise les couleurs
     */
    public static final String ANSI_RESET = "\u001B[32m";
    public static final String ANSI_GREEN = "\u001B[42m";
    public static final String ANSI_BLACK = "\u001B[30m";

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
                    System.out.printf("|%1s|", "_");
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
        affichageGrill();
        int tour = whoStart;
        do{
            if(tour%2 == 0){
                System.out.println("C'est le tour du " + joueurs[0]);
                whoStart = 1;
            }else{
                System.out.println("C'est le tour du " + joueurs[1]);
                whoStart = 2;
            }
            tour++;
            System.out.println("Ou voulez vous placer votre jeu ?");
            int colonne = 0;
            do {
                colonne = clavier.nextInt();
            }while(colonne<0&&colonne>6);
            placementJeton(colonne, whoStart);

        }while(tour != 42);
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
                affichageGrill();
                verifVictoire(colonne, (int) placeTrouve[1], nmDuJoueur);
            }
        }
        if(!(boolean) placeTrouve[0]){
            System.out.println("Choisissez une autre colonne, celle ci est pleine !");
            Scanner clavier = new Scanner(System.in);
            int newColonne = clavier.nextInt();
            placementJeton(newColonne, nmDuJoueur); // On rapelle la fonction et on utilise une nouvelle saisie pour éviter la récurisivité
        }
    }

    public static void verifVictoire(int posX, int posY, int nmDuJoueur){



        int winX = 1;
        int winY = 1;
        int winDiagX = 1;
        int winDiagY = 1;
        int pointeurX = posX;
        int pointeurY = posY;

        // Ici on calcule l'axe Y via méthode 1 : boucle for
        for(int i=posY+1; i <= 5 ; i++){
            if(board[posX][i] == nmDuJoueur){
                winY++;
            } else {
                winY = 1;
            }
        }

        // Ici on caclcule l'axe X vers la droite via méthode 2 : boucle while
        boolean boucle = true;
        while (pointeurX < 6 && boucle){

            if(board[(pointeurX+1)][posY] == nmDuJoueur){
                winX++;
            } else {
                   boucle = false;
            }
            pointeurX++;
        }

        // Puis vers la gauche
        pointeurX = posX;
        while (pointeurX > 0){

            if(board[(pointeurX-1)][posY] == nmDuJoueur){
                winX++;
            } else {
                break; // On peux utiliser break pour éviter la variable boucle.
            }
            pointeurX--;
        }

        // Ici on calcule les axes de diagonales

        // Droite montante
        pointeurX = posX;
        while(pointeurX < 6 && pointeurY > 0){
            if(board[(pointeurX+1)][(pointeurY-1)] == nmDuJoueur){
                winDiagX++;
            } else {
                break;
            }
            pointeurX++;
            pointeurY--;
        }

        // Droite Déscendante
        pointeurY = posY;
        pointeurX = posX;
        while(pointeurX > 0 && pointeurY < 5){
            if(board[(pointeurX-1)][(pointeurY+1)] == nmDuJoueur){
                winDiagX++;
            } else {
                break;
            }
            pointeurX--;
            pointeurY++;
        }

        // Gauche montante
        pointeurY = posY;
        pointeurX = posX;
        while(pointeurX > 0 && pointeurY > 0){
            if(board[(pointeurX-1)][(pointeurY-1)] == nmDuJoueur){
                winDiagY++;
            } else {
                break;
            }
            pointeurX--;
            pointeurY--;
        }

        // Gauche Déscendante
        int pointeur2Y = posY;
        int pointeur2X = posX;
        while(pointeur2X < 6 && pointeur2Y < 5){
            if(board[(pointeur2X+1)][(pointeur2Y+1)] == nmDuJoueur){
                winDiagY++;
            } else {
                break;
            }
            pointeur2X++;
            pointeur2Y++;
        }

        System.out.println("Score de winX : " + winX);
        System.out.println("Score de winY : " + winY);
        System.out.println("Score de winDiagX : " + winDiagX);
        System.out.println("Score de winDiagY : " + winDiagY);


        youWin(winX, winY, winDiagX, winDiagY, nmDuJoueur);
    }

    // Fonction qui vérifie la victoire, affiche le gagnant et coupe le script.
    public static void youWin(int winX, int winY, int winDiagX, int winDiagY, int nmDuJoueur){
        if (winX >= 4 || winY >= 4 || winDiagX >= 4 || winDiagY >= 4){
            System.out.println(ANSI_GREEN + ANSI_BLACK + "Félicitations au joueur " + nmDuJoueur + " qui remporte la partie !" + ANSI_RESET);
            System.exit(0);
        }
    }
}
