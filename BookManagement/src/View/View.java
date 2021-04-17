import java.util.Scanner;

public class View 

{
    public static void main(String[] args)
    {
        int choice= 0;
        var booksFileName = "BOOK.DAT";
        var controller = new FileManipulation();
        



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
                    System.out.println("Thanks for using");
                    break;
                    }
        
            case 1: 
            {
                String bookname, author, specializations;
                int yearofpubliation, quantity,specV, bookID;
                
                System.out.println("Input book title: ");
                bookname = scanner.nextLine();

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
                yearofpubliation = scanner.nextInt();
                
                System.out.println("Input quantity: ");
                quantity = scanner.nextInt();
                
                Book book = new Book(bookID = 0,bookname, author, specializations, yearofpubliation, quantity);
                controller.writeBooktoFile(book, booksFileName);
                
                
                break;
            }
            
            
        
        }    
    
    
        }
        while (choice != 0);


    }
}
