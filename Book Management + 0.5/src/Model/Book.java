package Model;

public class Book {
    private static int id = 100000;
    private int bookID;
    private String bookName;
    private String author;
    private String specializations;
    private int publicYear;
    private int quantity;

    //Constructors
    public Book(int bookID, String bookName, String author, String specializations, int publicYear, int quantity) {
        if (bookID == 0)
        {
            this.bookID = id++;
        }
        else this.bookID = bookID;
        
        
        this.bookName = bookName;
        this.author = author;
        this.specializations = specializations;
        this.publicYear = publicYear;
        this.quantity = quantity;
    }

    //GetSet
    public int getBookID() {
        return bookID;
    }


    public  void setBookID(int bookID) {
        this.bookID = bookID;
    }


    public String getBookName() {
        return bookName;
    }


    public void setBookName(String bookName) {
        this.bookName = bookName;
    }


    public String getAuthor() {
        return author;
    }


    public void setAuthor(String author) {
        this.author = author;
    }


    public String getSpecializations() {
        return specializations;
    }


    public void setSpecializations(String specializations) {
        this.specializations = specializations;
    }


    public int getPublicYear() {
        return publicYear;
    }


    public void setPublicYear(int publicYear) {
        this.publicYear = publicYear;
    }


    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static void setID(int i) 
    {
    id = i;
    }

    @Override
    public String toString() {
        return "Book [ BookID: " + bookID + ", Book title: " + bookName + "Author: " + author + ", Public Year:" + publicYear
                + ", Quantity: " + quantity + ", Specializations:" + specializations + "]";
    }

    
    
}
