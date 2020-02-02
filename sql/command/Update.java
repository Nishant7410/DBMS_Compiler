package command;
import java.util.*;
import java.io.*;
import java.lang.*;
public class Update
{
     public void update(String comm[],String str)throws IOException
    {
         if(comm.length<4 && !comm[2].equals("set"))
         {
             System.out.println("Command not correct");
             return;
         }
         
         File f=new File("db/"+comm[1]+".txt");
        if(!f.exists())
        {
            System.out.println("Table not exist");
            return;
        }
        
         if(!(comm[3].charAt(0)>=97&&comm[3].charAt(0)<=122))
         {
             System.out.println("Command not correct");
             return;
         }
         
         if(!(str.charAt(str.length()-1)>=97&&str.charAt(str.length()-1)<=122))
         {
             System.out.println("Command not correct");
             return;
         }
        int index=comm[3].indexOf('=');
         
         if(index==-1 && comm.length>4 && !(comm[4].equals("="))&&!(comm[4].charAt(0)=='='))
         {
             System.out.println("Command not correct");
             return;
         }
         else
         {
             int c=0;
             for(int i=0;i<str.length();i++)
             {
                 if(str.charAt(i)=='=')
                     c++;
             }
             if(c>1)
             {
                 System.out.println("Command not correct");
                 return;
             }
             String col_Name=new String("");
             String data=new String("");
             if(index!=-1)
             {
                 int temp_index=str.indexOf('=');
                 if(str.charAt(temp_index+1)!=' ')
                 {
                     col_Name+=(comm[3].substring(0,index));
                     data+=(comm[3].substring(index+1,comm[3].length()));
                 }
                 else
                 {
                     col_Name+=(comm[3].substring(0,index));
                     data+=(comm[4]);
                 }
             }
             else if(comm.length>4 && comm[4].equals("="))
             {
                col_Name+=(comm[3]);
                data+=(comm[5]);
             }
             else if(comm.length>4 && comm[4].charAt(0)=='=')
             {
                 col_Name+=(comm[3]);
                 data+=(comm[4].substring(1,comm[4].length()));
             }
             if(col_Name.equals("")&&data.equals(""))
             {
                 System.out.println("Command not correct");
                 return;
             }
             else
             {
                 BufferedReader br=new BufferedReader(new FileReader("db/"+comm[1]+".txt"));
                 String Att_row=br.readLine();
                 if(Att_row==null)
                 {
                     System.out.println("File have no data");
                     return;
                 }
                 String Att_name[]=Att_row.split(" ");
                 int chk=0,pos=0;
                 for(int i=0;i<Att_name.length;i++)
                     if(Att_name[i].equals(col_Name))
                     {
                         chk=1;
                         pos=i;
                     }
                 if(chk==0)
                 {
                     System.out.println("Column name not correct");
                     return;
                 }
                 Vector<String> v=new Vector<String>();
                 String All_row=new String("");
                 while((All_row=br.readLine())!=null)
                 {
                     v.add(All_row);
                 }
                 br.close();
                 if(v.size()==0)
                 {
                     System.out.println("File have no data");
                     return;
                 }
                 BufferedWriter bw=new BufferedWriter(new FileWriter("db/"+comm[1]+".txt"));
                 bw.write(Att_row);
                 bw.flush();
                 bw.close();
                 BufferedWriter BW=new BufferedWriter(new FileWriter("db/"+comm[1]+".txt",true));
                 for(int i=0;i<v.size();i++)
                 {
                     String row_data[]=v.get(i).split(" ");
                     row_data[pos]=data;
                     String temp=new String("");
                     for(int j=0;j<row_data.length;j++)
                     {
                         temp+=row_data[j];
                         if(j!=row_data.length-1)
                             temp+=" ";
                     }
                     BW.write("\n");
                     BW.write(temp);
                     BW.flush();
                 }
                 BW.close();
                 System.out.println("data Updated");
                 return;
             }
         }
    }
}