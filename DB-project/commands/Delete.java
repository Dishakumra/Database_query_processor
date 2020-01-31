package commands;
import java.util.*;
import java.io.*;
public class Delete{
    String keyword[]={"as","create","insert","delete","print","select","drop","groupby","in","into","quit","from","having","where","asc","desc"};
    public boolean validate(String token[])
    {
      String syntax[]={"drop","table"};
      if(token.length<3)
      {
        System.out.println("Missing keyword");
        return false;
      }
      if(token.length>3)
      {
      System.out.println("ERROR");
      return false;
    }
      if(!token[1].equals(syntax[1]))
      {
        System.out.println("Missing keyword");
        return false;
      }
      else
      {
        return checktable(token[2]);
      }
    }
    public boolean checktable(String tablename)
    {
      String path="tables/"+tablename+".txt";
      File file=new File(path);
      if(file.delete())
      {
        System.out.println("table is dropped");
      return true;
    }
      else
      {
        System.out.println("Table not found");
        return false;
      }
    }
}
