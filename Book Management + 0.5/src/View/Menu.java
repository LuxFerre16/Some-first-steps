package View;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.BookController;
import Controller.DataUtility;
import Controller.ReaderController;
import Controller.ReaderManagementController;
import Model.Book;
import Model.Reader;
import Model.ReaderManagement;

public class Menu {

    public static void menuCall()
    {
        int choice= 0;
        
        var booksFileName = "BOOK.DAT";
        var readersFileName = "READER.DAT";
        var RMFileName = "RM.DAT";
        
        var bController = new BookController();
        var rController = new ReaderController();
        var books = new ArrayList<Book>();
        var readers = new ArrayList<Reader>();
        ArrayList<ReaderManagement> rms = new ArrayList<ReaderManagement>();
        
        var isBookIDChecked = false;
        var isReaderIDChecked = false;

        var utility = new DataUtility();

        Scanner scanner = new Scanner(System.in);
        
        do
        {
        System.out.println("-------------Menu ----------------");
        System.out.println("1. Insert new book title");
        System.out.println("2. Print list of books");
        System.out.println("3. Insert new Reader");
        System.out.println("4. Print list of Readers");
        System.out.println("5. Establish book management information");
        System.out.println("6. Sort");
        System.out.println("7. Find reader in RM list.");
        System.out.println("0. Exit");
        System.out.println("Your choice: ? ? ?");

            choice = scanner.nextInt();
            scanner.nextLine();
    
        switch (choice)
            {
                case 0: 
                    {
                    System.out.println("___________________________");
                    System.out.println("Thanks for using");
                    break;
                    }
        
                case 1: 
                {
                
                    
                   if (!isBookIDChecked)
                        {
                        checkbookID(bController,booksFileName);
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
                    bController.writeBookToFile(book, booksFileName);
                
                
                    break;
                }
            
                case 2:
                {
                    System.out.println("_____________Books information__________________");
                
                    books = BookController.readBooksFromFile(booksFileName);
                    showBookInfo(books);
                    
                    break;
                }
            
                case 3:
                {   
                    if (!isReaderIDChecked)
                    {
                        checkReaderID(rController,readersFileName);
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
                
                    }while (!phoneNumber.matches("\\d{10}"));

                    Reader reader = new Reader(0, fullName, address, phoneNumber);
                    ReaderController.writeReaderToFile(reader, readersFileName);

                    break;
                }

                case 4:
                {
                    readers = ReaderController.readReadersFromFile(readersFileName);
                    showReaderInfo(readers);
                    break;
                }


                case 5:
                {   
                    readers = ReaderController.readReadersFromFile(readersFileName);
                    books = BookController.readBooksFromFile(booksFileName);
                    rms = ReaderManagementController.readRMsFromFile(RMFileName);

                    int readerID, booksID;
                    boolean reachLimit = false;
                    
                    showReaderInfo(readers);
                    System.out.println("==========================");
                
                    do
                    {
                        System.out.println("Input reader ID: , 0 to skip.");
                        readerID = scanner.nextInt();
                        if (readerID == 0 ) break;

                        reachLimit = checkBorrowed(rms, readerID);

                        if (reachLimit) break;
                        else 
                        {
                            System.out.println("This reader has reached borrow limit!");
                        }
                    
                    }while(true);

                    boolean bookLimit = false;
                    do
                    {
                        showBookInfo(books);
                        System.out.println("==========================");
                        System.out.println("Input bookID, 0 to skip.");
                        booksID = scanner.nextInt();
                    
                        if (booksID == 0) {break;}
                        bookLimit = checkBookLimit(rms, readerID, booksID);
                        if (bookLimit) System.out.println("Reached limit of this book title!"); else break; 
                    }while(true);

                    int total = getTotal(rms, readerID, booksID);
                    do
                    {
                    
                        System.out.println("Input number of books to borrow, max 3. (Borrowed " + total + ")");
                        int x = scanner.nextInt();
                        if (x+total >=1 && x + total <= 3)
                        {
                            total += x;
                            break;
                        }  else System.out.println("Re-input please, Invalid info inputted!");
                
                    }while(true);
                    scanner.nextLine();

                    System.out.println("Input book condition when borrow.");
                    String status = "";
                    status = scanner.nextLine();


                    Book currentBook = getBook(books, booksID);
                    Reader currentReader = getReader(readers, readerID);

                    ReaderManagement b = new ReaderManagement(currentBook, currentReader, total, status,0);

                    rms = utility.updateRMInfo(rms, b);
                    ReaderManagementController.updateRMFile(rms, RMFileName);
                    showRMInfo(rms);
                    
                    break;
                }
                case 6: 
                {
                    rms = ReaderManagementController.readRMsFromFile("RM.DAT");
                    
                    System.out.println("===================================================");
                    System.out.println("=========Sorting method: ==========");
                    rms = utility.updateTotalBorrow(rms);
                    do 
                    {
                    
                    System.out.println("1. Sort by Reader's name.");
                    System.out.println("2. Sort by total borrow.");
                    System.out.println("0 to return to main menu");
                    int z;
                    z = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (z==0) {break;}
                    switch(z)
                        {
                            case 1: 
                                {
                                    rms = utility.sortByReaderName(rms);
                                    showRMInfo(rms);
                                    break;
                                }
                            case 2:
                                {
                                    rms = utility.sortByNumOfBorrow(rms);
                                    showRMInfo(rms);
                                    break;
                                }
                        }
                    } while (true);
                    break;
                }
                case 7:
                {
                    rms = ReaderManagementController.readRMsFromFile(RMFileName);
                    System.out.println("Input words in desire Name: ");
                    String key = scanner.nextLine();

                    var result = utility.searchByReaderName(rms, key);
                    if (result.size() == 0)
                    {
                    System.out.println("None Reader with similar name found!");
                    } else 
                    {
                        showRMInfo(result);;
                    }
                    break;
                }

            }

        }while (choice != 0);
        scanner.close();

    }

   
    private static void showRMInfo(ArrayList<ReaderManagement> rms) 
    {
        for (var b : rms)
        {
            System.out.println(b);
        }
    }


    private static Reader getReader(ArrayList<Reader> readers, int readerID) 
    {
        for (int i = 0;  i < readers.size(); i++)
        {
            if (readers.get(i).getReaderID() == readerID) 
            {
                return readers.get(i);
            }
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


    private static int getTotal(ArrayList<ReaderManagement> rms, int readerID, int booksID) 
    {
        for (var r: rms)
        {
            if (r.getReader().getReaderID() == readerID 
                && r.getBook().getBookID() == booksID) {return r.getNumOfBorrow();}
        }
        
        
        return 0;
    }


    private static boolean checkBookLimit(ArrayList<ReaderManagement> rms, int readerID, int bookID) 
    {
        for (var r: rms)
        {
            if (r.getReader().getReaderID() == readerID 
                && r.getBook().getBookID() == bookID && r.getNumOfBorrow() ==3 ) {return true;}
        }
        
        
        
        return false;
    }


    private static void showReaderInfo(ArrayList<Reader> readers) 
    {
        System.out.println("_________Readers Information_____________");
        for(var r:readers)
        {
            System.out.println(r);
        }
    }


    private static void checkReaderID(ReaderController controller, String fileName) 
    {
        var readersList = ReaderController.readReadersFromFile(fileName);
        
        if (readersList.size() == 0)
        {
            //do nothing
        } else 
        Reader.setID(readersList.get(readersList.size() -1).getReaderID() +1);
    }


    private static void checkbookID(BookController controller, String fileName) 
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

    private static boolean checkBorrowed(ArrayList<ReaderManagement> rms, int readerID)
    {
        int count = 0;
        for (var r: rms)
        {
            if (r.getReader().getReaderID() == readerID)
            {
                count += r.getNumOfBorrow();
            }
        }
        
        if (count == 15) return false;
        return true;

    }
}
    

