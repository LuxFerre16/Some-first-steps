package Model;

public class ReaderManagement {
    private Book book;
    private Reader reader;
    private int numOfBorrow;
    private String state;
    private int totalBorrow;

    //Constructor
    public ReaderManagement(Book book, Reader reader, int numOfBorrow, String state, int totalBorrow) 
    {
        this.book = book;
        this.reader = reader;
        this.numOfBorrow = numOfBorrow;
        this.state = state;
        this.totalBorrow = totalBorrow;
    }

    //GetSet
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public int getNumOfBorrow() {
        return numOfBorrow;
    }

    public void setNumOfBorrow(int numOfBorrow) {
        this.numOfBorrow = numOfBorrow;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTotalBorrow() {
        return totalBorrow;
    }

    public void setTotalBorrow(int totalBorrow) {
        this.totalBorrow = totalBorrow;
    }

    public int getFullName() {
        return 0;
    }
    
    @Override
    public String toString() {
        return "ReaderManagement [ReaderID: " + reader.getReaderID() + ", Reader's name: " + reader.getFullName()
            + ", BookID: " + book.getBookID() + ", Book title: " + book.getBookName() + ", Num Of borrow: " + numOfBorrow + ", Status: "
            + state + ", total borrow:" + totalBorrow + "]";
    }
}
