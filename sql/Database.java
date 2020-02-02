import java.util.*;
import java.io.*;
import java.lang.*;
import command.*;
public class Database
{
    
    public static void history(String str)throws IOException
    {
        PrintWriter pr=new PrintWriter(new FileWriter("db/history.txt",true));
        pr.write("\n");    
        pr.write(str);
        pr.flush();
        pr.close();
    }
   
    public static void main(String ar[])throws IOException
    {
        System.out.println("Only for create,insert,select,drop,load,print,history");
        Scanner sc=new Scanner(System.in);
        ArrayList<ArrayList<String>>alist=new ArrayList<ArrayList<String>>();
        while(true)
        {
        String str=sc.nextLine();
        String temp=new String(str);    
        if(str.equals("EXIT")||str.equals("QUIT"))
            System.exit(0);
        str=str.trim().replaceAll("\\s{1,}"," ");
        str=str.toLowerCase();
        String comm[]=str.split(" ");
        if((comm[0].equals("delete")&&comm.length==2)||(comm[0].equals("history")&&comm.length==1)||(comm[0].equals("load"))||(comm[0].equals("print")))
        {
            switch(comm[0])
            { 
              case "delete":
                    Delete dl=new Delete(comm);
                    break;
              case "history":
                    History history=new History(comm[0]);
                    break;
              case "load":
                    history(temp);
                    Load ld=new Load();
                    ld.load(comm,alist);
                    break;
               case "print":
                    history(temp);
                    Print pr=new Print();
                    pr.print(comm,alist);
                    break;       
            }
        }
       else if(comm.length<3)
       {
           if(comm[0]=="history")
               System.out.println("Only type history");
           else
               System.out.println("Command not correct");
       }
        else
        {
        switch(comm[0])
        {
            case "create":
                history(temp);
                Create cr=new Create();
                cr.create(comm,str);
                break;
            case "select":
                history(temp);
                Select sl=new Select();
                sl.select(comm,str);
                break;
            case "insert":
                history(temp);
                Insert ins=new Insert();
                ins.insert(comm,str);
                break;
            case "update":
                history(temp);
                Update up=new Update();
                up.update(comm,str);
                break;
            case "drop":
                history(temp);
                Drop dp=new Drop();
                dp.drop(comm,str);
                break;
            default:
                System.out.println("command not recognised");
        }
        }
        }
    }
}