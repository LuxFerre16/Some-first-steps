package View;

import java.util.ArrayList;
import java.util.Scanner;

import Control.FileManipulation;
import Model.Book;

public class View 

{
    public static void main(String[] args)
    {
        int choice= 0;
        var booksFileName = "BOOK.DAT";
        var controller = new FileManipulation();
        var books = new ArrayList<Book>();
        var isIDChecked = false;



        Scanner scanner = new Scanner(System.in);
        do
        {
        System.out.println("-------------Menu ----------------");
        System.out.println("1. Insert new book title");
        System.out.println("2. Print list of books");
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
                
                if (!isIDChecked)
                    {
                        checkbookID(controller,booksFileName);
                        isIDChecked = true;
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
        
        }    
    
    
        }
        while (choice != 0);

    scanner.close();
    
    }

   
    private static void checkbookID(FileManipulation controller, String fileName) 
    {
        var listBooks = controller.readBooksFromFile(fileName);

        Book.setID(listBooks.get(listBooks.size()-1).getBookID() +1 );
    }


    private static void showBookInfo(ArrayList<Book> books) 
    {
        for (var b: books)
            System.out.println(b);
    }
}
