import java.util.Scanner;
import Control.FileManipulation;
public class View 

{
    public static void main(String[] args)
    {
        int choice= 0;
        var booksFileName = "BOOK.DAT";
        var controller = new FileManipulation();
        //var books = new Book null;



        Scanner scanner = new Scanner(System.in);
        do
        {
        system.out.println("-------------Menu ----------------");
        system.out.println("1. Insert new book title");
        system.out.println("2. Print list of books");
        system.out.println("0. Exit");
        system.out.println("Your choice: ? ? ?");

        choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice)
        {
            case 0: {
                    system.out.println("Thanks for using");
                    break;
                    }
        
            case 1: 
            {
                string bookname, author, specializations;
                int yearofpubliation, quantity,specV;
                
                system.out.println("Input book title: ");
                bookname = scanner.nextLine();

                system.out.println("Input author: ");
                author = scanner.nextLine();

                String[] specs = {"Science", "Art", "Economic", "IT"};
                do
                {
                system.out.println("Input specialization: ");
                system.out.println("1. Science\n 2.Art\n 3.Economic\n 4.IT");
                specV = scanner.next_int();
                }
                while (specV < 1 || specV > 4);
                spec = specs[specV-1];
                
                system.out.println("Input year of publication: ");
                yearofpubliation = scanner.nextInt();
                
                system.out.println("Input quantity: ");
                quantity = scanner.nextInt();
                
                Book book = new Book(bookID = 0,bookname,author,specialization,yearofpubliation,quantity);
                controller.writeBooktoFile(book, booksFileName);
                
                
                break;
            }
            
            
        
        }    
    
    
        }
        while (choice != 0);


    }
}
