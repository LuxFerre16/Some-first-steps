package Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Book;
import Model.Reader;
import Model.ReaderManagement;

public class ReaderManagementController {

    private static Scanner scanner;

    public void writeReaderManagementToFile(ReaderManagement RM, String fileName)
    {
        FileOperation.openFileToWrite(fileName);
        
        FileOperation.printWriter.println(RM.getReader().getReaderID() + "|" + RM.getBook().getBookID() + "|" + RM.getNumOfBorrow() + "|" + RM.getState());
        
        FileOperation.closeFileAfterWrite(fileName);
    }

    public static ArrayList<ReaderManagement> readRMsFromFile(String filename)
    {
       
        var books = BookController.readBooksFromFile("BOOK.DAT");
      
        var readers = ReaderController.readReadersFromFile("READER.DAT");
        FileOperation.openFileToRead(filename);

        ArrayList<ReaderManagement> rms = new ArrayList<>();

        while (scanner.hasNextLine())
        {
            String data = scanner.nextLine();
            ReaderManagement rm = createRMsData(data, readers, books);
            rms.add(rm);    
        }
        
        FileOperation.closeFileAfterRead(filename);

        return rms;
    }

    public static ReaderManagement createRMsData(String data, ArrayList<Reader> readers, ArrayList<Book> books) 
    {
        String[] Tokens = data.split("\\|");
    
        ReaderManagement rm = new ReaderManagement(getBook(books, Integer.parseInt(Tokens[1]))
                                        , getReader(readers, Integer.parseInt(Tokens[0]))
                                        , Integer.parseInt(Tokens[2]), Tokens[3], 0);
        
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

        FileOperation.openFileToWrite(fileName);
        for (var rm : list)
        {
            FileOperation.printWriter.println(rm.getReader().getReaderID() + "|" + rm.getBook().getBookID() + "|" + rm.getNumOfBorrow() + "|" + rm.getState());
        }


        FileOperation.closeFileAfterWrite(fileName);
    }

}
