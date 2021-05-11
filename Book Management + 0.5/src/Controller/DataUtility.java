package Controller;

import java.io.File;
import java.util.ArrayList;

import Model.ReaderManagement;

public class DataUtility {

    public ArrayList<ReaderManagement> updateRMInfo(ArrayList<ReaderManagement> list , ReaderManagement rm){
    boolean isUpdated = false;

    for (int i = 0; i < list.size(); i++)
    {
        ReaderManagement b = list.get(i);
        if ( b.getBook().getBookID() == rm.getBook().getBookID()
        && b.getReader().getReaderID() == rm.getReader().getReaderID() )
        {
            list.set(i, rm);
        }
    }
    
    if(!isUpdated)
    {
        list.add(rm);
    }
    return list;    
    }

    public ArrayList<ReaderManagement> updateTotalBorrow(ArrayList<ReaderManagement> list){
        
        for(int i = 0; i< list.size(); i++)
            {
                    ReaderManagement b = list.get(i);
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

    public ArrayList<ReaderManagement> sortByReaderName(ArrayList<ReaderManagement> list){
        
        for(int i = 0; i< list.size(); i++)
            {
                    
                    
                for (int j = list.size() - 1; j < i; j--)
                    {
                        ReaderManagement bj = list.get(j);
                        ReaderManagement bbj = list.get(j-1);

                        String[] name1 = bj.getReader().getFullName().split("\\s");
                        String[] name2 = bj.getReader().getFullName().split("\\s");

                        if(name1[name1.length -1].compareTo(name2[name2.length -1]) < 0 );
                        {
                            list.set(j, bbj);
                            list.set(j-1, bj);
                        } 
                    }
            
            }
        return list;
    }
    
    public ArrayList<ReaderManagement> sortByNumOfBorrow(ArrayList<ReaderManagement> list){
        
        for(int i = 0; i< list.size(); i++)
            {
                    
                    
                for (int j = list.size() - 1; j < i; j--)
                    {
                        ReaderManagement bj = list.get(j);
                        ReaderManagement bbj = list.get(j-1);

                        if(bj.getTotalBorrow() > bbj.getTotalBorrow())
                        {
                            list.set(j, bbj);
                            list.set(j-1, bj);
                        } 
                    }
            
            }
        return list;
    }

    public ArrayList<ReaderManagement> searchByReaderName(ArrayList<ReaderManagement> list, String key)
    {
        
        ArrayList<ReaderManagement> result = new ArrayList<>();
        String pattern = ".*" + key.toLowerCase() + ".*";


        for(int i = 0; i< list.size(); i++)
            {
               ReaderManagement b = list.get(i);
               if(b.getReader().getFullName().toLowerCase().matches(pattern))
               {
                   result.add(b);
               }     
                    
                
            
            }
        return result;
    }

}
