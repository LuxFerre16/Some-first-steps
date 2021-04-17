package Control;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Book;
import Model.BookReaderManagement;
import Model.Reader;


public class FileManipulation 

{
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private Scanner scanner;

    public void openFileToWrite(String filename)
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

    
    public void writeBookToFile(Book book, String fileName)
    {
        openFileToWrite(fileName);
        
        printWriter.println(book.getBookID() + "|" + book.getBookName() + "|" + book.getAuthor() + "|" + book.getSpecializations()
                                                                         + "|" + book.getPublicYear() + "|" + book.getQuantity());
        closeFileAfterWrite(fileName);
    
    }

    public void writeReaderToFile(Reader reader, String fileName)
    {
        openFileToWrite(fileName);
        
        printWriter.println(reader.getReaderID() + "|" + reader.getFullName() + "|" + reader.getAddress() + "|" + reader.getPhoneNumber());
    
        closeFileAfterWrite(fileName);
    }

    public void writeBookReaderManagementToFile(BookReaderManagement brm, String fileName)
    {
        openFileToWrite(fileName);
        
        printWriter.println(brm.getReader().getReaderID() + "|" + brm.getNumOfBorrow() + "|" + brm.getState());
        
        closeFileAfterWrite(fileName);
    }

                                                                        
    public void closeFileAfterWrite(String fileName)
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

    public void openFileToRead(String fileName)
    {
        try 
        {
            scanner = new Scanner(Paths.get(fileName),"UTF-8");
        } catch (Exception e) 
            {
            e.printStackTrace();
            }
    }

    public void closeFileAfterRead(String fileName)
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

    public ArrayList<Reader> readReadersFromFile(String fileName)
    {
        openFileToRead(fileName);
        closeFileAfterRead(fileName);
        ArrayList<Reader> readers = new ArrayList<> ();
            while (scanner.hasNextLine())
            {
                String data = scanner.nextLine();
                Reader reader = createReaderFromData(data);
                readers.add(reader);
            }


        return readers;
    }

    public Reader createReaderFromData(String data)
    {
        String[] datas = data.split("\\|");

        Reader reader = new Reader(Integer.parseInt(datas[0]),datas[1], datas[2], Integer.parseInt(datas[3]));

        return reader;
    }

    public ArrayList<Book> readBooksFromFile(String fileName)
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

    public Book createBookFromData(String data) 
    {
        String[] datas = data.split("\\|");
    
        Book book = new Book(Integer.parseInt(datas[0]),datas[1],datas[2],datas[3],Integer.parseInt(datas[4]), Integer.parseInt(datas[5]));
        
        return book;
    }

    public ArrayList<BookReaderManagement> readBRMsFromFile(String filename)
    {
        openFileToRead(filename);

        ArrayList<BookReaderManagement> brms = new ArrayList<>();

        while (scanner.hasNextLine())
        {
            String data = scanner.nextLine();
            BookReaderManagement brm = createBRMsData(data);
            brms.add(brm);

            
        }
        
        
        closeFileAfterRead(filename);

        return brms;
    }

    public BookReaderManagement createBRMsData(String data) 
    {
        String[] datas = data.split("\\|");
    
        BookReaderManagement brm = new BookReaderManagement( new Book(Integer.parseInt(datas[1]), data, data, data, 0, 0), new Reader(Integer.parseInt(datas[0]), data, data, 0),Integer.parseInt(datas[2]), datas[3], 0);
        
        return brm;
    }


}


