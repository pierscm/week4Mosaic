/**
 * Mosaic Application creates a 10 x 10 mosaic of randomly colored circles and
 * squares with randomly colored complementary colors.
 *
 * The unique feature is shape / text resizing.
 * As the window is resized, both the text and the shape will stay proportional
 * until there is no space to show any shape color
 * at which point, only letters will appear.
 *
 * An additional unique feature is the "Switcharoo!" button, which allows you to
 * switch circles and squares while keeping their existing colors and letters.
 * This required learning about how to implement more than one response from an
 * action listener!
 *
 * @author Cassie Pierson
 */



import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.util.Random;
import java.util.ArrayList;

class Tile extends JPanel{
    private int red, green, blue;
    private String randomLetter;
    private int column, row;

    private Color letterColor;
    private int circOrSquare = GetNumberBetween(0,1);

    public static final int GRIDSIZE = 10;


    Tile(){
        super();
        SetRandomValues();
    }

    public void setRandomShape(){
        circOrSquare = GetNumberBetween(0,1);
    }

    public int getRandomShape(){
        return circOrSquare;
    }


    // setOppositeShape method checks the
    // current value and changes to the
    // opposite.
    public void setOppositeShape(){
        if (circOrSquare == 0){
            circOrSquare = 1;
        }
        else{
            circOrSquare = 0;
        }
    }

    public void setColumn(int colNumIn){
        column = colNumIn;
    }

    public void setRow(int rowNumIn){
        row = rowNumIn;
    }

    public String getColumn(){
        return String.valueOf(column);
    }

    public String getRow(){
        return String.valueOf(row);
    }

    public String getRandomLetter() {
        return randomLetter;
    }


    public final void SetRandomValues(){

        red = GetNumberBetween(0,255);
        blue = GetNumberBetween(0, 255);
        green = GetNumberBetween(0, 255);


        switch(GetNumberBetween(0,25)){
            case 0:
                randomLetter = "a";
                break;
            case 1:
                randomLetter = "b";
                break;
            case 2:
                randomLetter = "c";
                break;
            case 3:
                randomLetter = "d";
                break;
            case 4:
                randomLetter = "e";
                break;
            case 5:
                randomLetter = "f";
                break;
            case 6:
                randomLetter = "g";
                break;
            case 7:
                randomLetter = "h";
                break;
            case 8:
                randomLetter = "i";
                break;
            case 9:
                randomLetter = "j";
                break;
            case 10:
                randomLetter = "k";
                break;
            case 11:
                randomLetter = "l";
                break;
            case 12:
                randomLetter = "m";
                break;
            case 13:
                randomLetter = "n";
                break;
            case 14:
                randomLetter = "o";
                break;
            case 15:
                randomLetter = "p";
                break;
            case 16:
                randomLetter = "q";
                break;
            case 17:
                randomLetter = "r";
                break;
            case 18:
                randomLetter = "s";
                break;
            case 19:
                randomLetter = "t";
                break;
            case 20:
                randomLetter = "u";
                break;
            case 21:
                randomLetter = "v";
                break;
            case 22:
                randomLetter = "w";
                break;
            case 23:
                randomLetter = "x";
                break;
            case 24:
                randomLetter = "y";
                break;
            case 25:
                randomLetter = "z";
                break;
            default:
                randomLetter = "um...";
        }

    }

    // Helper function provided in class
    private static int GetNumberBetween(int min, int max){
        Random myRandom = new Random();
        return min + myRandom.nextInt(max - min + 1);
    }

    // Formal toString function using class getters and setters as well as
    // default color getters and setters.
    public String toString(){
        String info =  String.format("Column is: %s Row is: %s Letter is: %s Shape Color is: (%d,%d,%d) Letter Color is (%s, %s, %s)" , getColumn(), getRow(), getRandomLetter(), red, green, blue, letterColor.getRed(), letterColor.getBlue(), letterColor.getGreen());
        return info;
    }



    public void paintComponent(Graphics g){
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // If value of circOrSquare is 1, the shape will be a square.
        if (circOrSquare == 1) {
            g.setColor(new Color(red, green, blue));
            g.fillRect(panelWidth % 6, panelHeight % 6, panelWidth - 8, panelHeight - 8);

            letterColor = new Color(GetContColor(red), GetContColor(blue), GetContColor(green));
            g.setColor(letterColor);
        }

        // If value of circOrSquare is 0, the shape will be a circle.
        else{
            g.setColor(new Color(red, green, blue));
            g.fillOval(panelWidth % 6, panelHeight % 6, panelWidth - 8, panelHeight - 8);

            letterColor = new Color(GetContColor(red), GetContColor(blue), GetContColor(green));
            g.setColor(letterColor);
        }


        //Sets font style, size and placement.
        // Resizable font, woo!
        final int fontSize = ((panelHeight + panelWidth)/3);
        g.setFont(new Font("helvetica", Font.BOLD, fontSize));
        int stringX = (panelWidth/2) - (fontSize/4);
        int stringY = (panelHeight/2) + (fontSize/3);
        g.drawString(randomLetter,stringX,stringY);
    }

    private static int GetContColor(int colorIn){
        return((colorIn+128) %256);
    }
}

class TileFrame extends JFrame implements ActionListener{

    private ArrayList<Tile> tileList;

    public TileFrame(){
        setBounds(400,400, 800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        JButton randomize = new JButton("Randomize");
        buttonPanel.add(randomize);
        randomize.addActionListener(this);

        JPanel mosaicPanel = new JPanel();
        contentPane.add(mosaicPanel, BorderLayout.CENTER);
        mosaicPanel.setLayout(new GridLayout(10, 10));



        tileList = new ArrayList<Tile>();

        for(int i = 0; i < 100; i++){
            Tile tile = new Tile();
            tileList.add(tile);
            mosaicPanel.add(tile);
        }

    }

    // Second constructor allows custom bound setting.
    public TileFrame(int Xin, int YIn, int widthIn, int heightIn) {
        setBounds(Xin, YIn, widthIn, heightIn);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel RandomizePanel = new JPanel();
        contentPane.add(RandomizePanel, BorderLayout.SOUTH);

        JButton randomize = new JButton("Randomize");
        RandomizePanel.add(randomize);
        randomize.addActionListener(this);

        // Switcharoo button implementation
        JButton switchARoo = new JButton("Switcharoo!");
        RandomizePanel.add(switchARoo);
        switchARoo.addActionListener(this);

        JPanel TilePanel = new JPanel();
        contentPane.add(TilePanel, BorderLayout.CENTER);
        TilePanel.setLayout(new GridLayout(10, 10));


        tileList = new ArrayList<Tile>();

        // Create 10 x 10 Tiles
        for (int i = 0; i < 100; i++) {
            Tile tile = new Tile();
            tileList.add(tile);
            TilePanel.add(tile);

               tile.setColumn(1 + (i % Tile.GRIDSIZE));
               tile.setRow(1 + (i / Tile.GRIDSIZE));
        }

    }


    public void actionPerformed(ActionEvent e) {

        String toDo = e.getActionCommand();

        // Check to see whether button text is Randomize or Switcharoo
        // and take appropriate action.

        if (toDo == "Randomize") {
            System.out.println("Start Paint***");

            for (Tile tile : tileList) {
                tile.setRandomShape();
                tile.SetRandomValues();
                System.out.println(tile.toString());
            }
            repaint();
        }

        if (toDo == "Switcharoo!"){
            System.out.println("Start Paint***");

            for (Tile tile : tileList) {
                tile.setOppositeShape();
                System.out.println(tile.toString());
            }
            repaint();
        }
    }



}

public class Mosaic {


    public static void main(String[] args) {
	System.out.println("Hurrah!");

	TileFrame myTileFrame = new TileFrame(500, 500, 700, 800);
	myTileFrame.setVisible(true);



    }
}
