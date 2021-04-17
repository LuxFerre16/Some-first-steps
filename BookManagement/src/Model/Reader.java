package Model;

public class Reader 
{
    
    private static int id = 1000000;
    private int readerID;
    private int phoneNumber;
    private String fullName;
    private String address;
   

    //constructor
    public Reader(int readerID, String fullName, String address, int phoneNumber)
    {
        if (readerID == 0) 
        {
            this.readerID = id++;
        }
        else this.readerID = readerID;
        
        this.readerID = readerID;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.address = address;
    }

    //GetSet
    public int getReaderID() {
        return readerID;
    }


    public void setReaderID(int readerID) {
        this.readerID = readerID;
    }


    public int getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getFullName() {
        return fullName;
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }

   
    

}
