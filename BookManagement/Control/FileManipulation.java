import java.io.FileWriter;

public class FileManipulation 

{
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private Scanner scanner;

    void openFiletoWrite(String filename)
    {
        try 
        {
            fileWriter = new FileWriter(filename, append = true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);

        } catch (Exception e) 
            {
            e.printStackTrace();
            }
        

    }

    void writeBooktoFile(Book book, String filname)
    {
        printWriter.println(book.getbookID() + "|" + book.getBookName() + "|" + book.getAuthor + "|" + book.getSpecializations
                                                                         + "|" + book.getPublicYear + "|" + book.getQuantity);
    
    
    }

    void writeReadertoFile(Reader reader, String filname)
    {
        printWriter.println(reader.getReaderID() + "|" + reader.getFullName() + "|" + reader.getAddress() + "|" + reader.getPhoneNumber());
    
    
    }

    void writeBookReaderManagementtoFile(BookReaderManagement brm, String filname)
    {
        printWriter.println(brm.getReaderID() + "|" + brm.getFullName()
         + "|" + brm.getBookName() + "|" + brm.getBookID() + "|" + brm.getNumOfBorrow() + "|" + brm.getState());
    
    
    }

                                                                        
    void closeFileAfterWrite(String filename)
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

}
