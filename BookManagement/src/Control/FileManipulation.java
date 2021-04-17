import model.Book;
import model.BookReaderManagement;
import model.Reader;


import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import jdk.javadoc.internal.doclets.formats.html.SplitIndexWriter;


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
            fileWriter = new FileWriter(filename, append = true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);

        } catch (Exception e) 
            {
            e.printStackTrace();
            }
        

    }

    
    public void writeBookToFile(Book book, String filname)
    {
        printWriter.println(book.getbookID() + "|" + book.getBookName() + "|" + book.getAuthor + "|" + book.getSpecializations
                                                                         + "|" + book.getPublicYear + "|" + book.getQuantity);
    
    
    }

    public void writeReaderToFile(Reader reader, String filname)
    {
        printWriter.println(reader.getReaderID() + "|" + reader.getFullName() + "|" + reader.getAddress() + "|" + reader.getPhoneNumber());
    
    
    }

    public void writeBookReaderManagementToFile(BookReaderManagement brm, String filname)
    {
        printWriter.println(brm.getReaderID() + "|" + brm.getFullName()
         + "|" + brm.getBookName() + "|" + brm.getBookID() + "|" + brm.getNumOfBorrow() + "|" + brm.getState());
    
    
    }

                                                                        
    public void closeFileAfterWrite(String filename)
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

    public void openFileToRead(String filename)
    {
        try 
        {
            scanner = new Scanner(Paths.get(filename), charsetName = "UTF-8");
        } catch (Exception e) 
            {
            e.printStackTrace();
            }
    }

    public void closeFileAfterRead(String filename)
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

    public ArrayList<Reader> readReadersFromFile(String filename)
    {
        openFileToRead(filename);
        closeFileAfterRead(filename);
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

        Reader reader = new Reader(Integer.parseInt(datas[0]),datas[1], datas[2], datas[3]);

        return reader;
    }

    public ArrayList<Book> readBooksFromFile(String filename)
    {
        openFileToRead(filename);

        ArrayList<Book> books = new ArrayList<>();

        while (scanner.hasNextLine())
        {
            String data = scanner.nextLine();
            Book book = createBookFromData(data);
            books.add(book);

            
        }
        
        
        closeFileAfterRead(filename);

        return books;
    }

    public Book createBookFromData(String data) 
    {
        String[] datas = data.split( regex =  "\\|");
    
        Book book = new Book(Integer.parseInt(datas[0]),datas[1],datas[2],datas[3],Integer.parseInt(datas[4]), datas[5]);
        
        return book;
    }

    public ArrayList<BookReaderManagement> readBRMsFromFile(String filename)
    {
        openFileToRead(filename);

        ArrayList<BookReaderManagement> brms = new ArrayList<>();

        while (scanner.hasNextLine())
        {
            String data = scanner.nextLine();
            BookReaderManagement brm = createBRmsFromData(data);
            brms.add(brm);

            
        }
        
        
        closeFileAfterRead(filename);

        return brms;
    }

    public BookReaderManagement createBRMsData(String data) 
    {
        String[] datas = data.split( regex =  "\\|");
    
        BookReaderManagement brm = new BookReaderManagement( new Book(Integer.parseInt(datas[1])), new Reader(Integer.parseInt(datas[0])),
         Integer.parseInt(datas[2], datas[3], totalBorrow = 0 ));
        
        return brm;
    }


}


