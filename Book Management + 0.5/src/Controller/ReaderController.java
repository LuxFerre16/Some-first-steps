package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Reader;

public class ReaderController {

    private static Scanner scanner;

    public static void writeReaderToFile(Reader reader, String fileName)
    {
        FileOperation.openFileToWrite(fileName);
        
        FileOperation.printWriter.println(reader.getReaderID() + "|" + reader.getFullName() + "|" + reader.getAddress() + "|" + reader.getPhoneNumber());
    
        FileOperation.closeFileAfterWrite(fileName);
    }

    public static ArrayList<Reader> readReadersFromFile(String fileName)
    {
        FileOperation.openFileToRead(fileName);
        
        ArrayList<Reader> readers = new ArrayList<> ();
            while (scanner.hasNextLine())
            {
                String data = scanner.nextLine();
                Reader reader = createReaderFromData(data);
                readers.add(reader);
            }


        FileOperation.closeFileAfterRead(fileName);
        return readers;
    }

    public static Reader createReaderFromData(String data)
    {
        String[] Tokens = data.split("\\|");

        Reader reader = new Reader(Integer.parseInt(Tokens[0]),Tokens[1], Tokens[2], Tokens[3]);

        return reader;
    }
    
}
