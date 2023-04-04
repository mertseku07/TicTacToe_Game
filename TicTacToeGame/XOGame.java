//XOGame.java
import javax.swing.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class XOGame extends Canvas implements MouseListener
{
    private String message="";
    private String scoreboard1 ="";
    private String scoreboard2="";
    private int scoreX;
    private int scoreO;
    //StartX=X-coordination of board up-left corner
//startY=Y-coordination of board up-left corner
//squareSize=size of each square
    private int StartX=200,startY=50,squareSize=60,n=10;
    private int [][]board=new int[n][n];
    private int playerTurn=1; //playerTurn=1---> Player X, playerTurn=2---->Player Y
    private boolean gameOver;
    private int numberOfPieces=0;
    public XOGame()
    {
        addMouseListener(this);
    }
    public void paint( Graphics g )
    {
//draw n*n square board
        for(int i=0;i<=n;i++) {
            g.drawLine(StartX, startY+i*squareSize, StartX+n*squareSize, startY+i*squareSize);
            g.drawLine(StartX+i*squareSize, startY, StartX+i*squareSize, startY+n*squareSize);
        }
//Display X and O on the Screen
        g.setFont(new Font ("Arial",Font.ITALIC, 32));
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                if(board[i][j]==1) {
                    g.setColor(Color.red);
                    g.drawString("X",StartX+j*squareSize+squareSize/3 ,startY+i*squareSize+squareSize/2);
                }
                else if(board[i][j]==2) {
                    g.setColor(Color.blue);
                    g.drawString("O",StartX+j*squareSize+squareSize/3 ,startY+i*squareSize+squareSize/2);
                }
        g.setFont(new Font ("Arial",Font.BOLD, 24));
        g.setColor(Color.BLACK);
        g.drawString(message,10,40);
        g.drawString(scoreboard1,10,70);
        g.drawString(scoreboard2,10,100);
    }
    public void mouseClicked( MouseEvent evt )
    {
        if(!gameOver) {
            if( evt.getX() >StartX && evt.getX()<StartX+n*squareSize
                    && evt.getY()>startY && evt.getY()<startY+n*squareSize) {
                int row=(evt.getY()-startY)/squareSize;
                int col=(evt.getX()-StartX)/squareSize;
                if(board[row][col]==0) {
                    numberOfPieces++;
                    board[row][col]=playerTurn;
                    scoreX=0;
                    scoreO=0;
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            if(j<=7){
                                if ((board[i][j] == 1 && board[i][j+1] == 1 && board[i][j+2] == 1)) {
                                    scoreX++;
                                } else if ((board[i][j] == 2 && board[i][j+1] == 2 && board[i][j + 2] == 2)) {
                                    scoreO++;
                                }
                            }
                            if(i<=7){
                                if ((board[i][j] == 1 && board[i+1][j] == 1 && board[i+2][j] == 1)) {
                                    scoreX++;
                                } else if ((board[i][j] == 2 && board[i+1][j] == 2 && board[i+2][j] == 2)) {
                                    scoreO++;
                                }
                            }
                            if(j<=7 && i<=7){
                                if ((board[i][j] == 1 && board[i+1][j+1] == 1 && board[i+2][j+2] == 1)) {
                                    scoreX++;
                                } else if ((board[i][j] == 2 && board[i+1][j+1] == 2 && board[i][j+2] == 2)) {
                                    scoreO++;
                                }
                            }
                            if(i<=7 && j>=2){
                                if ((board[i][j] == 1 && board[i+1][j-1] == 1 && board[i+2][j-2] == 1)) {
                                    scoreX++;
                                }
                                else if ((board[i][j] == 2 && board[i+1][j-1] == 2 && board[i+2][j-2] == 2)) {
                                    scoreO++;
                                }
                            }
                        }
                    }
                }
                if(numberOfPieces==100) {
                    if(scoreX > scoreO)
                        message = "X Wins!";
                    else if (scoreO > scoreX)
                        message = "O Wins!";
                    else
                        message = "Tie!";
                    gameOver=true;
                }
                else {
                    if(playerTurn==1) {
                        message="Next Turn=O";
                        playerTurn=2;
                    }
                    else{
                        message="Next Turn=X";
                        playerTurn=1;
                    }
                }
                scoreboard1= "Score of X: " + scoreX;
                scoreboard2= "Score of O: " + scoreO;
            }
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
// TODO Auto-generated method stub
    }
    @Override
    public void mouseExited(MouseEvent arg0) {
// TODO Auto-generated method stub
    }
    @Override
    public void mousePressed(MouseEvent arg0) {
// TODO Auto-generated method stub
    }
    @Override
    public void mouseReleased(MouseEvent arg0) {
// TODO Auto-generated method stub
    }
}
//---------------------------------------------
class main{
    public static void main(String[] args) {
        JFrame win = new JFrame("X-O Game");
        win.setSize(1024,768);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.add(new XOGame());
        win.setVisible(true);
    }
}
