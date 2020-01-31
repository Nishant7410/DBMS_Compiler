package command;
import java.util.*;
import java.io.*;
import java.lang.*;
public class Delete
{
    public Delete(String str[])throws IOException
    {
          File f=new File("db/history.txt");
        if(!str[1].equals("history"))
        {
            System.out.println("Command not correct");
            return;
        }
          if(!f.delete())
        {
            System.out.println("History already deleted");
            return;
        } 
        else
        {
           System.out.println("History delete");
           return;
        }
    }
}