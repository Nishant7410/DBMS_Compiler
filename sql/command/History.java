package command;
import java.util.*;
import java.io.*;
import java.lang.*;
public class History
{
    public History(String str)throws IOException
    {
          File f=new File("db/"+str+".txt");
          if(!f.exists())
        {
            System.out.println("No History");
            return;
        } 
          BufferedReader bf=new BufferedReader(new FileReader("db/"+str+".txt"));
          String data;
          while((data=bf.readLine())!=null)
          {
              System.out.println(data);
          }
         bf.close();
        return;
    }
}