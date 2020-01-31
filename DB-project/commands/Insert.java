package commands;
import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Paths;
public class Insert{
  String keywords[]={"as","create","insert","delete","print","select","drop","groupby","in","into","quit","from","having","where","asc","desc"};
  List<String> keyword=Arrays.asList(keywords);
  String syntax[]={"insert","into","values"};
  public boolean validate(String token[])
  {
    if(token.length<5)
    {
      System.out.println("Missing variables");
      return false;
    }
    else if(!(token[1].equals(syntax[1])))
    {
      System.out.println("Missing variables");
      return false;
    }
    else {
      if(checktable(token[2]))
    {
      if(token[3].equals(syntax[2]))
      {
        	if(token[4].charAt(0)=='(' && token[token.length-1].charAt(token[token.length-1].length()-1)==')')
          {
            return true;
          }
          else
          {
            System.out.println("Brackets error");
            return false;
          }
      }
      else
      {
        System.out.println("Syntex error");
        return false;
      }
    }
    else
    return false;
  }
  }
  public boolean insertvalues(String token[])throws Exception
  {
    String command=" ";
    for(int i=4;i<token.length;i++)
    {
      command+=token[i];
      if(token[i].charAt(token[i].length()-1)!=',')
      command+=" ";
    }
    command=command.trim().replaceAll("\\s{2,}"," ");
    command=command.replace("(","");
    command=command.replace(")","");
    command=command.trim();
    int v=0;
    String cols[]=command.split(",");
    String path="tables/"+token[2]+".txt";
    List<String> alllines =Files.readAllLines(Paths.get(path));
    for(String lines:alllines)
    {
      String filetoken[]=lines.split(",");
      if(cols.length==filetoken.length)
      v=1;
      else if(cols.length>filetoken.length)
      {
        System.out.println("limit exceed");
        return false;
      }
      else
      {
        System.out.println("less values");
        return false;
      }
      break;
    }
    if(v==1)
    {
      FileWriter writer=new FileWriter(path,true);
      for(int i=0;i<cols.length;i++){
        writer.write(cols[i]);
        if(i!=cols.length-1)
        writer.write(",");
      }
      writer.write("\r\n");
      writer.close();
    System.out.print("Inserted");
    return true;
    }
    else
    return false;


  }
  public boolean checktable(String tablename)
  {
    String path="tables/"+tablename+".txt";
    try{
      FileInputStream file=new FileInputStream(path);
    }
    catch(FileNotFoundException e)
    {
      System.out.println("Invalid Tablename");
      return false;
    }
    return true;
  }
}
