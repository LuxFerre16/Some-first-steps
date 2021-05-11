package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Reader;

public class ReaderController {

    static FileWriter fileWriter;
    static BufferedWriter bufferedWriter;
    static PrintWriter printWriter;
    static Scanner scanner;
    
    
    private static void openFileToWrite(String filename)
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
    
    private static void closeFileAfterWrite(String fileName)
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
    
    private static void openFileToRead(String fileName)
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

    private static void closeFileAfterRead(String fileName)
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

    public static void writeReaderToFile(Reader reader, String fileName)
    {
        openFileToWrite(fileName);
        
        printWriter.println(reader.getReaderID() + "|" + reader.getFullName() + "|" + reader.getAddress() + "|" + reader.getPhoneNumber());
    
        closeFileAfterWrite(fileName);
    }

    public static ArrayList<Reader> readReadersFromFile(String fileName)
    {
        openFileToRead(fileName);
        
        ArrayList<Reader> readers = new ArrayList<> ();
            while (scanner.hasNextLine())
            {
                String data = scanner.nextLine();
                Reader reader = createReaderFromData(data);
                readers.add(reader);
            }


        closeFileAfterRead(fileName);
        return readers;
    }

    public static Reader createReaderFromData(String data)
    {
        String[] Tokens = data.split("\\|");

        Reader reader = new Reader(Integer.parseInt(Tokens[0]),Tokens[1], Tokens[2], Tokens[3]);

        return reader;
    }
    
}
