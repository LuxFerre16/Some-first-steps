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

    public ArrayList<BookReaderManagement> updateTotalBorrow(ArrayList<BookReaderManagement> list)
    {
        
        for(int i = 0; i< list.size(); i++)
            {
                    BookReaderManagement b = list.get(i);
                    int count = 0;
                for (int j = 0; j < list.size(); j++)
                    {
                        if(list.get(j).getReader().getReaderID() == b.getReader().getReaderID()) {count += list.get(j).getNumOfBorrow();}
                    }
            b.setTotalBorrow(count);
            list.set(i, b);
            }
        return list;
    }

    public ArrayList<BookReaderManagement> sortByReaderName(ArrayList<BookReaderManagement> list)
    {
        
        for(int i = 0; i< list.size(); i++)
            {
                    
                    
                for (int j = list.size() - 1; j < i; j--)
                    {
                        BookReaderManagement bj = list.get(j);
                        BookReaderManagement bbj = list.get(j-1);

                        String[] name1 = bj.getReader().getFullName().split("\\s");
                        String[] name2 = bj.getReader().getFullName().split("\\s");

                        if(name1[name1.length() -1].compareTo(name2[name2.length() -1]) < 0 );
                        {
                            list.set(j, bbj);
                            list.set(j-1, bj);
                        } 
                    }
            
            }
        return list;
    }

    public ArrayList<BookReaderManagement> sortByNumOfBorrow(ArrayList<BookReaderManagement> list)
    {
        
        for(int i = 0; i< list.size(); i++)
            {
                    
                    
                for (int j = list.size() - 1; j < i; j--)
                    {
                        BookReaderManagement bj = list.get(j);
                        BookReaderManagement bbj = list.get(j-1);

                        if(bj.getTotalBorrow() > bbj.getTotalBorrow())
                        {
                            list.set(j, bbj);
                            list.set(j-1, bj);
                        } 
                    }
            
            }
        return list;
    }

    public ArrayList<BookReaderManagement> searchByReaderName(ArrayList<BookReaderManagement> list, String key)
    {
        
        ArrayList<BookReaderManagement> result = new ArrayList<>();
        String pattern = ".*" + key.toLowerCase() + ".*";


        for(int i = 0; i< list.size(); i++)
            {
               BookReaderManagement b = list.get(i);
               if(b.getReader().getFullName().toLowerCase().matches(pattern))
               {
                   result.add(b);
               }     
                    
                
            
            }
        return result;
    }



}
