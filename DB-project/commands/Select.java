package commands;
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
public class Select{
  String keyword[]={"as","create","insert","delete","print","select","drop","groupby","in","into","quit","from","having","where","asc","desc"};
  public boolean validate(String token[])throws Exception
  {
    if(token.length<4)
    {
      System.out.println("Missing keyword");
      return false;
    }
    int flag=-1;
    for(int i=0;i<token.length;i++)
    {
      if(token[i].equals("from"))
      {
        flag=i;
        break;
      }
    }
    if(flag==-1||flag==1||flag==token.length-1)
    {
      System.out.println("Syntax ERROR");
      return false;
    }
    if(checktable(token[flag+1]))
    {

     if(!token[1].equals("*"))
    {
       if(checkcolumn(token,flag))
       {
       selecttable(token,flag);
       return true;
     }
     return false;
    }
    else
    {
      if(token[2].equals("from"))
      {
        printtable(token[flag+1]);
        return true;
      }
      else{
        System.out.println("Missing something");
        return false;
      }
    }
  }
  else
  {
    System.out.println("Table doesn't exist");
    return false;
  }
  }
  public boolean checktable(String tablename)
  {
     String path="tables/"+tablename+".txt";
     try
     {
       FileInputStream file=new FileInputStream(path);
     }
     catch(FileNotFoundException e)
     {
       System.out.println("Table not exist");
       return false;
     }
     return true;
  }
  public void printtable(String tablename)throws Exception
  {
    String path="tables/"+tablename+".txt";
    List<String> allLines = Files.readAllLines(Paths.get(path));
    System.out.println("|--------------------------------------------------------------------------|");
      for (String line : allLines) {
        System.out.print("|\t");
        System.out.println(line.replaceAll(",","\t | \t")+"\t\t");
        System.out.println("|--------------------------------------------------------------------------|");
  }
}
public void selecttable(String token[],int flag)throws Exception
{
  String tablename=token[flag+1];
  String path="tables/"+tablename+".txt";
  String columns=" ";
  for(int i=1;i<flag;i++){

    if(token[i].equals(","))
      columns+=" ";
      else
      columns+=token[i];
  }
  columns=columns.trim().replaceAll("\\s{2,}", " ");

  columns=columns.trim();
  String cols[]=columns.split(" ");

  List<String> allLines = Files.readAllLines(Paths.get(path));
  int selected[]=new int[cols.length];
  for(String line:allLines)
  {
    String filetokens[]=line.split(",");
    List<String> ftoken=Arrays.asList(filetokens);
    int h=0;
    for(int i=0;i<cols.length;i++)
    {
      selected[h]=ftoken.indexOf(cols[i]);
      h++;
    }
    break;
  }
  for(String line:allLines){
    String data[]=line.split(",");
    System.out.print("|\t");
    for(int i=0;i<cols.length;i++){
      System.out.print(data[selected[i]]);
      if(i!=cols.length-1)
      System.out.print("\t|\t");
    }
    System.out.println(" \t |");
  }

}
public boolean checkcolumn(String token[],int flag)throws Exception
{
  String tablename=token[flag+1];
  String path="tables/"+tablename+".txt";
  String columns=" ";
  for(int i=1;i<flag;i++){

    if(token[i].equals(","))
      columns+=" ";
      else
      columns+=token[i];
  }
  columns=columns.trim().replaceAll("\\s{2,}", " ");

  columns=columns.trim();
  String cols[]=columns.split(" ");
   // for(int i=0;i<cols.length;i++)
   // {
   //   System.out.println(cols[i]);
   // }
  List<String> allLines = Files.readAllLines(Paths.get(path));

  for(String list:allLines)
  {
    String filetoken[]=list.split(",");
    List<String> filetokens=Arrays.asList(filetoken);
    if(cols.length>filetoken.length)
    {
      System.out.println("Column doesn't exist1");
      return false;
    }
    int f=0;
    for(int i=0;i<cols.length;i++)
    {
      if(!filetokens.contains(cols[i]))
      {
        f=1;
        break;
      }
    }
    if(f==1)
    {
      System.out.println("Column doesn't exist l");
      return false;
    }
    else
    {
      return true;
    }

  }
  return false;

}
}
