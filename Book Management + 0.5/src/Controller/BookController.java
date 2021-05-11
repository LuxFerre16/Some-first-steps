package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Book;

public class BookController {
    
    static Scanner scanner = new Scanner(System.in);

    public void writeBookToFile(Book book, String fileName)
    {
        FileOperation.openFileToWrite(fileName);
        
        FileOperation.printWriter.println(book.getBookID() + "|" + book.getBookName() + "|" + book.getAuthor() + "|" + book.getSpecializations()
                                                                         + "|" + book.getPublicYear() + "|" + book.getQuantity());
        FileOperation.closeFileAfterWrite(fileName);
    
    }
    
    public ArrayList<Book> readBooksFromFile(String fileName)
    {
        FileOperation.openFileToRead(fileName);

        ArrayList<Book> books = new ArrayList<>();

        while (scanner.hasNextLine())
        {
            String data = scanner.nextLine();
            Book book = createBookFromData(data);
            books.add(book);
                
        }
        
        FileOperation.closeFileAfterRead(fileName);

        return books;
    }

    public static Book createBookFromData(String data) 
    {
        String[] Tokens = data.split("\\|");
    
        Book book = new Book(Integer.parseInt(Tokens[0]),Tokens[1],Tokens[2],Tokens[3],Integer.parseInt(Tokens[4]), Integer.parseInt(Tokens[5]));
        
        return book;
    }
    
}
