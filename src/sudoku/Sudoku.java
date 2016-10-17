package sudoku;

import java.util.Scanner;
import java.util.*;
import java.io.*;
public class Sudoku
{
    public int[][] game;
    public int Row;
    public int Col;
    public int input;
    public int[][] answer;
    public int[][] start;
    public String load;
    public boolean check;
    public Sudoku()
    {
        game = new int[9][9];
        answer = new int[9][9];
        start = new int[9][9];
    }

    ////????? inclide the Random numbers to get the random template
    public void userInput()
    {
        check = true;
        do
        {
            System.out.println("If you want to exit and save the game press - 999: ");
            Scanner keyboard = new Scanner(System.in);
            System.out.print("Please choose the row: ");
            Row = keyboard.nextInt(); 
            if (Row == 999)
            {
                check = false;
            }
            else
            {
                System.out.print("Now choose the column: ");
                Col = keyboard.nextInt();
                if(Col==999)
                {
                    check = false;
                    break;
                }
                System.out.print("Your input: ");
                input = keyboard.nextInt();
                if(input==999)
                {
                    check = false;
                    break;
                }
            }

            if((Row<1||Row>9||Col<1||Col>9||input<1||input>9))
            {   
                System.out.println("One of the outputs is less than 1 or over 9");
                System.out.println("Try again ");
            }
            else if(start[Row-1][Col-1]!=0)
            {
                System.out.println("You can not change this number");
                System.out.println("Try again ");
            }
            else
                game[Row-1][Col-1] = input;
        }
        while((Row<0&&Row>9||Col<0&&Col>9||input<0&&input>9)&&(start[Row-1][Col-1]!=0));
    }

    public boolean checking()//checks if you won
    {
        int num = 0;
        for(int row = 0; row < 9; row++)
        {
            for(int col = 0; col < 9; col++)
            {
                if(game[row][col]==answer[row][col])
                    num++;
            }
        }
        if(num==81)
            return true;
        else
            return false;
    }

    public void display()
    {
        System.out.println(" -----------------------");
        for(int row=0;row<9;row++)
        {
            System.out.print("| ");
            for(int col=0;col<9;col++)
            {
                System.out.print(""+game[row][col]+" ");
                if((col+1)/1==3||(col+1)/1==6||(col+1)/1==9)
                {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if((row+1)/1==3||(row+1)/1==6||(row+1)/1==9)
            {
                System.out.println(" -----------------------");
            }
        }
    }

    public void load() throws IOException
    {
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter the name of the game: ");
        String load = kb.nextLine() + ".txt";
        File file = new File(load);
        Scanner inFile = new Scanner(file);
        for(int row=0;row<game.length;row++)
        {
            for(int col=0;col<game[row].length;col++)
            {
                game[row][col] = inFile.nextInt();
            }
        }

        for(int row=0;row<game.length;row++)
        {
            for(int col=0;col<game[row].length;col++)
            {
                answer[row][col] = inFile.nextInt();
            }
        }

        for(int row=0;row<game.length;row++)
        {
            for(int col=0;col<game[row].length;col++)
            {
                start[row][col] = inFile.nextInt();
            }
        }
        inFile.close();
    }

    public void save() throws IOException
    {
        Scanner kb = new Scanner(System.in);
        System.out.print("How do you want your game to be saved: ");
        String save = kb.nextLine() + ".txt";
        PrintWriter filesWrited = new PrintWriter(save);
        for(int row=0;row<game.length;row++)
        {
            for(int col=0;col<game[row].length;col++)
            {
                filesWrited.print(game[row][col]+ " \t");
            }
            filesWrited.println();
        }        
        for(int row=0;row<game.length;row++)
        {
            for(int col=0;col<game[row].length;col++)
            {
                filesWrited.print(answer[row][col]+ " \t");
            }
            filesWrited.println();
        }
        for(int row=0;row<game.length;row++)
        {
            for(int col=0;col<game[row].length;col++)
            {
                filesWrited.print(start[row][col]+ " \t");
            }
            filesWrited.println();
        }
        filesWrited.close();
    }

    public void start() throws IOException//asks what level and loads the template and loads the answers
    {
        boolean checkconditions = false;
        do
        {
            String load1="";
            Scanner kb = new Scanner(System.in);
            System.out.print("Enter the difficulty level:(easy/medium/hard) ");
            String difficulty = kb.nextLine();
            if(difficulty.equalsIgnoreCase("easy"))
            {
                load1 = "easy.txt";
                checkconditions = true;
            }
            else if(difficulty.equalsIgnoreCase("medium"))
            {
                load1 = "medium.txt";
                checkconditions = true;
            }
            else if(difficulty.equalsIgnoreCase("hard"))
            {
                load1 = "hard.txt";
                checkconditions = true;
            }
            else
                checkconditions = false;
            if(checkconditions == true)
            {
                File file = new File(load1);
                Scanner inFile = new Scanner(file);
                for(int row=0;inFile.hasNextInt() && row<game.length;row++)//fills the array start with numbers from file
                {
                    for(int col=0;col<game[row].length;col++)
                    {
                        start[row][col] = inFile.nextInt();
                    }
                }

                for(int row=0; inFile.hasNextInt() && row<answer.length;row++)//fills the array answer with numbers from file
                {
                    for(int col=0;col<answer[row].length;col++)
                    {
                        answer[row][col] = inFile.nextInt();
                    }
                }

                for(int row=0;row<game.length;row++)//copies the answer to start array
                {
                    for(int col=0;col<game[row].length;col++)
                    {
                        game[row][col] = start[row][col];
                    }
                }
                inFile.close();
            }
        }
        while(checkconditions==false);
    }
}