package commands;
import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Create{
   Pattern pattern=Pattern.compile("[a-zA-Z_]*");
   String keywords[]={"as","create","insert","delete","print","select","drop","groupby","in","into","quit","from","having","where","asc","desc"};
   String syntax[]={"create","table"};
   List<String> keyword=Arrays.asList(keywords);
  public boolean validate(String token[])
  {
  

   if(token.length<5)
   {
     System.out.println("Missing Something");
     return false;
   }
   else if(!token[1].equals("table"))
   {
     System.out.println("Missing Something1");
     return false;
   }
   else if(!checktablename(token[2]))
   {
     System.out.println("Table name can not contain special character");
     return false;
   }
   else if(!(token[3].charAt(0)=='('))
   {
     System.out.println("Missing Something2");
     return false;
   }
   else
   {
     if(token[token.length-1].charAt(token[token.length-1].length()-1)==')')
     {
       if(token.length==5)
       {
         System.out.println("Enter atleast one column");
         return false;
       }

       return true;

     }
     else
     {
       System.out.println("Missing paranthesis");
       return false;
     }
   }
  }
  public boolean checktablename(String tablename)
  {
    Matcher mach=pattern.matcher(tablename);
    if(! mach.matches()){
      System.out.println("table name contains special character");
      return false;
    }
    String path="tables/"+tablename+".txt";
		try{
		FileInputStream fin=new FileInputStream(path);
		}
		catch(FileNotFoundException e){
		if(keyword.contains(tablename)){
			System.out.println("Tablename is a keyword!!!");
			return false;
		}
		else
			return true;
		}
		System.out.println("Table already exists");
		return false;
  }
  public boolean createtable(String token[])throws Exception
  {
    String path="tables/"+token[2]+".txt";
    String cols=token[3];
    for(int i=4;i<token.length;i++)
    cols+=token[i];
    cols=cols.trim().replaceAll("\\s{2,}"," ");
    cols=cols.replace("(","");
    cols=cols.replace(")","");
    cols=cols.trim();
    String colms[]=cols.split(",");

    List<String> columnss=Arrays.asList(colms);
    Set<String> set= new HashSet<String>(columnss);
    if(set.size()<columnss.size()){
      System.out.println("Column names must be distinct");
      return false;
    }
    for(int i=0;i<colms.length;i++){
      Matcher matcher = pattern.matcher(colms[i]);
      if(keyword.contains(colms[i]) || colms[i].equals(token[2]) || !matcher.matches()){
          System.out.println("Column name "+colms[i]+" invalid");
            return false;
      }
    }
    File file=new File(path);
    if(file.createNewFile())
    {
      FileWriter writer=new FileWriter(path,true);
      for(int i=0;i<colms.length;i++)
      {
        writer.write(colms[i]);
        if(i!=colms.length-1)
        writer.write(",");

      }
      writer.write("\r\n");
      writer.close();
    return true;
    }
    else
    return false;



  }

}
