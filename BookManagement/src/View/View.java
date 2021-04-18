package View;

import java.util.ArrayList;
import java.util.Scanner;

import Control.FileManipulation;
import Model.Book;
import Model.Reader;

public class View 

{
    public static void main(String[] args)
    {
        int choice= 0;
        
        var booksFileName = "BOOK.DAT";
        var readersFileName = "READER.DAT";
        
        var controller = new FileManipulation();
        var books = new ArrayList<Book>();
        var readers = new ArrayList<Reader>();
        
        var isBookIDChecked = false;
        var isReaderIDChecked = false;


        Scanner scanner = new Scanner(System.in);
        
        do
        {
        System.out.println("-------------Menu ----------------");
        System.out.println("1. Insert new book title");
        System.out.println("2. Print list of books");
        System.out.println("3. Insert new Reader");
        System.out.println("4. Print list of Readers");
        System.out.println("0. Exit");
        System.out.println("Your choice: ? ? ?");

        choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice)
        {
            case 0: {
                    System.out.println("___________________________");
                    System.out.println("Thanks for using");
                    break;
                    }
        
            case 1: 
            {
                
                if (!isBookIDChecked)
                    {
                        checkbookID(controller,booksFileName);
                        isBookIDChecked = true;
                    }
                
                
                String bookName, author, specializations;
                int yearOfPublication, quantity,specV;
                
                System.out.println("Input book title: ");
                bookName = scanner.nextLine();

                System.out.println("Input author: ");
                author = scanner.nextLine();

                String[] specs = {"Science", "Art", "Economic", "IT"};
                do
                {
                System.out.println("Input specialization: ");
                System.out.println("1. Science\n 2.Art\n 3.Economic\n 4.IT");
                specV = scanner.nextInt();
                }
                while (specV < 1 || specV > 4);
                specializations = specs[specV-1];
                
                System.out.println("Input year of publication: ");
                yearOfPublication = scanner.nextInt();
                
                System.out.println("Input quantity: ");
                quantity = scanner.nextInt();
                
                Book book = new Book(0,bookName, author, specializations, yearOfPublication, quantity);
                controller.writeBookToFile(book, booksFileName);
                
                
                break;
            }
            
            case 2:
            {
                System.out.println("_____________Books information__________________");
                
                books = controller.readBooksFromFile(booksFileName);
                showBookInfo(books);

                
                break;
            }
            
            case 3:
            {   
                if (!isReaderIDChecked)
                {
                    checkReaderID(controller,readersFileName);
                    isReaderIDChecked = true;
                }
                
                String fullName, address, phoneNumber;
                
                System.out.println("Input Reader's full name: ");
                fullName = scanner.nextLine();

                System.out.println("Input Reader's address: ");
                address = scanner.nextLine();

                do 
                {
                
                System.out.println("Input valid Reader's phone number: ");
                phoneNumber = scanner.nextLine();
                
                }
                while (!phoneNumber.matches("\\d{10}"));

                Reader reader = new Reader(0, fullName, address, phoneNumber);
                controller.writeReaderToFile(reader, readersFileName);

                break;
            }

            case 4:
            {
                readers = controller.readReadersFromFile(readersFileName);
                showReaderInfo(readers);
                break;
            }


        }    
    
    
        }
        while (choice != 0);

    
        scanner.close();
    }

   
    private static void showReaderInfo(ArrayList<Reader> readers) 
    {
        System.out.println("_________Readers Information_____________");
        for(var r:readers)
        {
            System.out.println(r);
        }
    }


    private static void checkReaderID(FileManipulation controller, String fileName) 
    {
        var readersList = controller.readReadersFromFile(fileName);
        
        if (readersList.size() == 0)
        {
            //do nothing
        } else 
        Reader.setID(readersList.get(readersList.size() -1).getReaderID() +1);
    }


    private static void checkbookID(FileManipulation controller, String fileName) 
    {
        var booksList = controller.readBooksFromFile(fileName);

        if (booksList.size() == 0)
        {
            //do nothing            
        } else 
        Book.setID(booksList.get(booksList.size()-1).getBookID() +1 );
    }


    private static void showBookInfo(ArrayList<Book> books) 
    {
        for (var b: books)
            System.out.println(b);
    }



    
}
