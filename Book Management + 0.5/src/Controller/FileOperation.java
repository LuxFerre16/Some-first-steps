package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileOperation {
    
    static FileWriter fileWriter;
    static BufferedWriter bufferedWriter;
    static PrintWriter printWriter;
    static Scanner scanner;
    
    
    public static void openFileToWrite(String filename)
    {
        try 
        {
            fileWriter = new FileWriter(filename, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);

        } catch (Exception e) 
            {
            e.printStackTrace();
            }
        

    }
    
    public static void closeFileAfterWrite(String fileName)
    {
        try 
        {
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();    
        } catch (Exception e) 
            {
            e.printStackTrace();
            }
    }
    
    public static void openFileToRead(String fileName)
    {
        try 
        {
            File file = new File(fileName);
            if (!file.exists())
            {
                file.createNewFile();
            }
            scanner = new Scanner(Paths.get(fileName),"UTF-8");
        } catch (Exception e) 
            {
            e.printStackTrace();
            }
    }

    public static void closeFileAfterRead(String fileName)
    {
        try 
        {
            scanner.close();
        } 
        catch (Exception e) 
            {
            e.printStackTrace();
            }
    }


    
}
