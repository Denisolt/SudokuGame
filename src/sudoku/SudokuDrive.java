package sudoku;

import java.io.*;
import java.util.Scanner;
public class SudokuDrive
{
    public static void main(String [] args) throws IOException
    {
        Sudoku game = new Sudoku();
        Scanner kb = new Scanner(System.in);
        System.out.println("Welcome to Sudoku game by Deni!");
        System.out.print("Do you want to start new game or load your game?(new/load): ");
        String answer = kb.nextLine();
        int num;
        boolean start = true;
        do
        {
            if(answer.equalsIgnoreCase("new"))
            {
                game.start();
                start = true;
            }
            else if(answer.equalsIgnoreCase("load"))
            {
                game.load();
                start = true;
            }
            else if(!answer.equalsIgnoreCase("new")||!answer.equalsIgnoreCase("load"))
            {
                start = false;
                System.out.print("try again: ");
                answer = kb.nextLine();
            }
        }
        while(start == false);

        do
        {
            game.display();
            game.userInput();
            if(game.checking()==true)
            {
                System.out.println("You won");
                game.check = false;
            }
        }
        while(game.check==true);
        game.save();
    }
}