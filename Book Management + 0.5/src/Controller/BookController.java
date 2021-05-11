package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Book;

public class BookController {
    

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



    public void writeBookToFile(Book book, String fileName)
    {
        openFileToWrite(fileName);
        
        printWriter.println(book.getBookID() + "|" + book.getBookName() + "|" + book.getAuthor() + "|" + book.getSpecializations()
                                                                         + "|" + book.getPublicYear() + "|" + book.getQuantity());
        closeFileAfterWrite(fileName);
    
    }
    
    public static ArrayList<Book> readBooksFromFile(String fileName)
    {
        openFileToRead(fileName);

        ArrayList<Book> books = new ArrayList<>();

        while (scanner.hasNextLine())
        {
            String data = scanner.nextLine();
            Book book = createBookFromData(data);
            books.add(book);
                
        }
        
        closeFileAfterRead(fileName);

        return books;
    }

    public static Book createBookFromData(String data) 
    {
        String[] Tokens = data.split("\\|");
    
        Book book = new Book(Integer.parseInt(Tokens[0]),Tokens[1],Tokens[2],Tokens[3],Integer.parseInt(Tokens[4]), Integer.parseInt(Tokens[5]));
        
        return book;
    }
    
}
