import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PgnReader {
    private static String[][] board;

    public static String tagValue(String tagName, String game) {
        String tagVal  = "NOT GIVEN";
        int index = game.indexOf(tagName);

        if (index>-1){
            int index2 = game.indexOf("\"", index);
            tagVal = game.substring(index2+1, game.indexOf("\"", index2+1));
        }

        return (tagVal);
    }

    public static String finalPosition(String game) {
        // java PgnReader giuoco-piano.pgn
        initializeBoard();
        String moveText = game.substring(game.lastIndexOf(']')+3).replaceAll("[\r\n]+", " ");;
        String finalPos = "";
        int turn = 1;
        boolean gameFinished = false;

        do{
            int indexW = moveText.indexOf(Integer.toString(turn)+".")+3;
                if (turn>9) indexW+=1;
            int indexB = moveText.substring(indexW).indexOf(' ')+indexW+1;
            int indexEnd = moveText.substring(indexB).indexOf(' ')+indexB;

            updatePieces("y", moveText.substring(indexW, indexB).trim()); //sends white move to function
            if ((!(moveText.substring(indexW, indexB).trim().contains("#") || moveText.substring(indexW, indexB).trim().contains("++"))) && (indexB<indexEnd)){
                updatePieces("x", moveText.substring(indexB, indexEnd).trim()); //sends black..
                if (moveText.substring(indexB, indexEnd).trim().contains("#") || moveText.substring(indexB, indexEnd).trim().contains("++"))
                    gameFinished=true;
            } else
                gameFinished=true;
            if (!moveText.substring(indexEnd).contains(Integer.toString(turn+1)))
                 gameFinished=true;
            else turn++;
        } while (!gameFinished);

        int spaces = 0;
        for (int i = 0; i < 8; i++){
            if(i>0)
                finalPos+="/";
            for (int j = 0; j < 8; j++){
                if(board[i][j]==null && j < 8)
                    spaces++;
                else {
                    if(spaces!=0)
                        finalPos+=Integer.toString(spaces);
                    if (board[i][j].substring(0,1).equals("x"))
                        finalPos+=board[i][j].substring(1).toLowerCase();
                    else finalPos+=board[i][j].substring(1);
                    spaces=0;
                }
            }
            if(spaces!=0)
                finalPos+=Integer.toString(spaces);
            spaces=0;
        }

        return(finalPos);
    }

    private static void updatePieces(String color, String move){
        //file+rank-->board[rank][file]
        char[] moveArray = move.toCharArray();
        boolean isCapture = (moveArray[1]=='x');

        if (moveArray[0]=='O'){
            int r = 7;
            if(color.equals("x"))
                r = 0;
            if (moveArray.length>3){
                board[r][0] = null;
                board[r][4] = null;
                board[r][2] = color+"K";
                board[r][3] = color+"R";
            } else {
                board[r][7] = null;
                board[r][4] = null;
                board[r][6] = color+"K";
                board[r][5] = color+"R";
            }
        } else if (Character.isLowerCase(moveArray[0])){
            if(isCapture){
                if (board[8-Character.getNumericValue(moveArray[3])][getFile(moveArray[2])]==null){
                    if (color.equals("x"))
                        board[8-Character.getNumericValue(moveArray[3])-1][getFile(moveArray[2])] = null;
                    else
                        board[8-Character.getNumericValue(moveArray[3])+1][getFile(moveArray[2])] = null;
                }
                removePiece(color+"P", 8-Character.getNumericValue(moveArray[3]), getFile(moveArray[0]));
                board[8-Character.getNumericValue(moveArray[3])][getFile(moveArray[2])] = color+"P";
            } else {
                removePiece(color+"P", 8-Character.getNumericValue(moveArray[1]), getFile(moveArray[0]));
                board[8-Character.getNumericValue(moveArray[1])][getFile(moveArray[0])] = color+"P";
            }
            if ((8-Character.getNumericValue(moveArray[1])==0)||(8-Character.getNumericValue(moveArray[1])==7))
                board[8-Character.getNumericValue(moveArray[1])][getFile(moveArray[0])] = color+"P";
        } else if (Character.isUpperCase(moveArray[0])){
            if((moveArray.length>3) && (Character.isLetter(moveArray[1]) && Character.isLetter(moveArray[2])) && (moveArray[1]!='x')){
                removePiece(color+moveArray[0], -1, getFile(moveArray[1]));
                board[8-Character.getNumericValue(moveArray[3])][getFile(moveArray[2])] = color+moveArray[0];
            } else if((moveArray.length>3) && (Character.isDigit(moveArray[1]) && Character.isLetter(moveArray[2]))){
                removePiece(color+moveArray[0], 8-Character.getNumericValue(moveArray[1]), -1);
                board[8-Character.getNumericValue(moveArray[3])][getFile(moveArray[2])] = color+moveArray[0];
            } else if((moveArray.length>4) && (Character.isLetter(moveArray[1]) && Character.isDigit(moveArray[2])) && (moveArray[1]!='x')){
                removePiece(color+moveArray[0], 0-(8-Character.getNumericValue(moveArray[2])), 0-getFile(moveArray[1]));
                board[8-Character.getNumericValue(moveArray[3])][getFile(moveArray[2])] = color+moveArray[0];
            } else if(isCapture){
                removePiece(color+moveArray[0], 8-Character.getNumericValue(moveArray[3]), getFile(moveArray[2]));
                board[8-Character.getNumericValue(moveArray[3])][getFile(moveArray[2])] = color+moveArray[0];
            } else {
                removePiece(color+moveArray[0], 8-Character.getNumericValue(moveArray[2]), getFile(moveArray[1]));
                board[8-Character.getNumericValue(moveArray[2])][getFile(moveArray[1])] = color+moveArray[0];
            }
        }
    }

    private static void initializeBoard(){
        board = new String[8][8];
        for (int i = 0; i<8; i++){
            for (int j = 0; j<8; j++){
                if (i==0||i==7){
                    String[] pieceOrder = {"R","N","B","Q","K","B","N","R"};
                    if (i==0)
                        board[i][j] = "x"+pieceOrder[j];
                    else
                        board[i][j] = "y"+pieceOrder[j];
                } else if (i==1||i==6){
                    if (i==1)
                        board[i][j] = "xP";
                    else
                        board[i][j] = "yP";
                }
            }
        }
    }

    private static int getFile(char file){
        char[] files = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        for (int i = 0; i < files.length; i++){
            if (files[i]==file)
                return i;
        }
        return 1000;
    }

    public static void removePiece(String piece, int rank, int file){
        //board[rank][file]
        if (rank<0 && file<0){
            board[Math.abs(rank)][Math.abs(file)]=null;
        } else if (rank<0){
            for (int i = 0; i < 8; i++){
                if (board[i][file]!=null && board[i][file].equals(piece))
                    board[i][file]=null;
            }
        } else if (file<0){
            for (int i = 0; i < 8; i++){
                if (board[rank][i]!=null && board[rank][i].equals(piece))
                    board[rank][i]=null;
            }
        } else if (piece.contains("P")){
            for (int i = 0; i < 8; i++){
                if ((board[i][file]!=null && board[i][file].equals(piece))&&(Math.abs(i-rank)<=2)){
                    if ((piece.contains("x") && i<rank) || (piece.contains("y") && i>rank))
                        board[i][file]=null;
                }
            }
        } else if (piece.contains("R")){
            int rCount = 0;
            int[] rJ = {-1, -1};
            int[] rK = {-1,-1};
            for (int j = 0; j < 8; j++){
                for (int k = 0; k < 8; k++){
                    if((board[j][k]!=null && board[j][k].equals(piece))&&(j==rank || k==file)){
                        rJ[rCount] = j;
                        rK[rCount] = k;
                        rCount++;
                    }
                }
            }
            if(rCount<2){
                board[rJ[0]][rK[0]] = null;
            } else if(rCount==2){
                boolean isMovable;
                for(int count=0; count<2; count++){
                    isMovable = true;
                    if(rJ[count]==rank){
                        if(rK[count]>file){
                            for (int y = file; y<rK[count]; y++){
                                if(board[rank][y]!=null) isMovable = false;
                            }
                        } else {
                            for (int y = rK[count]+1; y<file; y++){
                                if(board[rank][y]!=null) isMovable = false;
                            }
                        }
                    } else {
                        if(rJ[count]>file){
                            for (int y = rank; y<rJ[count]; y++){
                                if(board[y][file]!=null) isMovable = false;
                            }
                        } else {
                            for (int y = rJ[count]+1; y<rank; y++){
                                if(board[y][file]!=null) isMovable = false;
                            }
                        }
                    }
                    if(isMovable){
                        board[rJ[count]][rK[count]] = null;
                    }
                }
            } else System.out.print("ROOK ERROR!!!");
        } else if (piece.contains("N")){
            for (int l = 0; l < 8; l++){
                for (int o = 0; o < 8; o++){
                    if((board[l][o]!=null && board[l][o].equals(piece))&&((Math.abs(l-rank)==2 && Math.abs(o-file)==1) || (Math.abs(l-rank)==1 && Math.abs(o-file)==2)))
                        board[l][o]=null;
                }
            }
        } else if (piece.contains("B")){
            for (int m = 0; m < 8; m++){
                for (int n = 0; n < 8; n++){
                    if((board[m][n]!=null && board[m][n].equals(piece))&&(Math.abs(m-rank)==Math.abs(n-file)))
                        board[m][n]=null;
                }
            }
        } else if (piece.contains("K")){
            for (int p = 0; p < 8; p++){
                for (int q = 0; q < 8; q++){
                    if(board[p][q]!=null && board[p][q].equals(piece))
                        board[p][q]=null;
                }
            }
        } else if (piece.contains("Q")){
            for (int r = 0; r < 8; r++){
                for (int s = 0; s < 8; s++){
                    if(board[r][s]!=null && board[r][s].equals(piece))
                        board[r][s]=null;
                }
            }
        }
    }

    public static String fileContent(String path) {
        Path file = Paths.get(path);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                // Add the \n that's removed by readline()
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            System.exit(1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String game = fileContent(args[0]);
        System.out.format("Event: %s%n", tagValue("Event", game));
        System.out.format("Site: %s%n", tagValue("Site", game));
        System.out.format("Date: %s%n", tagValue("Date", game));
        System.out.format("Round: %s%n", tagValue("Round", game));
        System.out.format("White: %s%n", tagValue("White", game));
        System.out.format("Black: %s%n", tagValue("Black", game));
        System.out.format("Result: %s%n", tagValue("Result", game));
        System.out.println("Final Position:");
        System.out.println(finalPosition(game));
    }
}
