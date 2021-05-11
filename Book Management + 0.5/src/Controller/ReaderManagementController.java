package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Book;
import Model.Reader;
import Model.ReaderManagement;

public class ReaderManagementController {

    static FileWriter fileWriter;
    static BufferedWriter bufferedWriter;
    static PrintWriter printWriter;
    
     private static Scanner scanner;
    
    
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


    public void writeReaderManagementToFile(ReaderManagement RM, String fileName)
    {
        openFileToWrite(fileName);
        
        printWriter.println(RM.getReader().getReaderID() + "|" + RM.getBook().getBookID() + "|" + RM.getNumOfBorrow() + "|" + RM.getState());
        
        closeFileAfterWrite(fileName);
    }

    public static ArrayList<ReaderManagement> readRMsFromFile(String filename)
    {
       
        var books = BookController.readBooksFromFile("BOOK.DAT");
      
        var readers = ReaderController.readReadersFromFile("READER.DAT");
        
        openFileToRead(filename);

        ArrayList<ReaderManagement> rms = new ArrayList<>();
       
        while (scanner.hasNextLine())
        {   
            String data = scanner.nextLine();
            ReaderManagement rm = createRMsData(data, readers, books);
            rms.add(rm);    
        }
        
        closeFileAfterRead(filename);
        return rms;
    }

    public static ReaderManagement createRMsData(String data, ArrayList<Reader> readers, ArrayList<Book> books) 
    {
        
        String[] Tokens = data.split("\\|");
        
        ReaderManagement rm = new ReaderManagement(getBook(books, Integer.parseInt(Tokens[1]))
                                        , getReader(readers, Integer.parseInt(Tokens[0]))
                                        , Integer.parseInt(Tokens[2]), Tokens[3], 0);
        
        System.out.println(rm);
        return rm;
    }
    
    public static Book getBook(ArrayList<Book> books, int booksID) 
    {
        for (int i = 0 ; i< books.size() ; i++)
        {
            if (books.get(i).getBookID() == booksID) 
            {
                return books.get(i);
            }
        }
        
        return null;
    }

    public static Reader getReader(ArrayList<Reader> readers, int readerID) 
    {
        for (int i = 0;  i < readers.size(); i++)
        {
            if (readers.get(i).getReaderID() == readerID) 
            {
                return readers.get(i);
            };
        }
        
        return null;
    }

    public static void updateRMFile(ArrayList<ReaderManagement> list, String fileName)
    {
        File current = new File(fileName);
        if (current.exists()) current.delete();

        openFileToWrite(fileName);
        for (var rm : list)
        {
            printWriter.println(rm.getReader().getReaderID() + "|" + rm.getBook().getBookID() + "|" + rm.getNumOfBorrow() + "|" + rm.getState());
        }


        closeFileAfterWrite(fileName);
    }

}
