package Control;

import java.util.ArrayList;

import Model.BookReaderManagement;

public class DataUtility
{


    public ArrayList<BookReaderManagement> updateBRMInfo(ArrayList<BookReaderManagement> list , BookReaderManagement brm)
    {
    boolean isUpdated = false;

    for (int i = 0; i < list.size(); i++)
    {
        BookReaderManagement b = list.get(i);
        if ( b.getBook().getBookID() == brm.getBook().getBookID()
        && b.getReader().getReaderID() == brm.getReader().getReaderID() )
        {
            list.set(i, brm);
        }
    }
    
    if(!isUpdated)
    {
        list.add(brm);
    }
    return list;    
    }
}
