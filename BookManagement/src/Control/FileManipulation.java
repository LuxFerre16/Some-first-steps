package Control;

import java.io.BufferedWriter;
import java.io.File;
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
    
    private static FileWriter fileWriter;
    private static BufferedWriter bufferedWriter;
    private static PrintWriter printWriter;
    private static Scanner scanner;

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
        String[] datas = data.split("\\|");

        Reader reader = new Reader(Integer.parseInt(datas[0]),datas[1], datas[2], datas[3]);

        return reader;
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
        String[] datas = data.split("\\|");
    
        Book book = new Book(Integer.parseInt(datas[0]),datas[1],datas[2],datas[3],Integer.parseInt(datas[4]), Integer.parseInt(datas[5]));
        
        return book;
    }

    public static ArrayList<BookReaderManagement> readBRMsFromFile(String filename)
    {
       
        var books = readBooksFromFile("BOOK.DAT");
      
        var readers = readReadersFromFile("READER.DAT");
        openFileToRead(filename);

        ArrayList<BookReaderManagement> brms = new ArrayList<>();

        while (scanner.hasNextLine())
        {
            String data = scanner.nextLine();
            BookReaderManagement brm = createBRMsData(data, readers, books);
            brms.add(brm);

            
        }
        
        
        closeFileAfterRead(filename);

        return brms;
    }

    public static BookReaderManagement createBRMsData(String data, ArrayList<Reader> readers, ArrayList<Book> books) 
    {
        String[] datas = data.split("\\|");
    
        BookReaderManagement brm = new BookReaderManagement(getBook(books, Integer.parseInt(datas[1]))
                                        , getReader(readers, Integer.parseInt(datas[0]))
                                        , Integer.parseInt(datas[2]), datas[3], 0);
        
        return brm;
    }
    public static void updateBRMFile(ArrayList<BookReaderManagement> list, String fileName)
    {
        File current = new File(fileName);
        if (current.exists()) current.delete();

        openFileToWrite(fileName);
        for (var brm : list)
        {
            printWriter.println(brm.getReader().getReaderID() + "|" + brm.getNumOfBorrow() + "|" + brm.getState());
        }


        closeFileAfterWrite(fileName);
    }

    private static Reader getReader(ArrayList<Reader> readers, int readerID) 
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


    private static Book getBook(ArrayList<Book> books, int booksID) 
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

}


