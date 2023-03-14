
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
            winX = 1;
            winY = 1;
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
}
