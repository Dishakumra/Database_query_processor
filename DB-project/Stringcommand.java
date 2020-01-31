import java.util.*;
import commands.*;
class Stringcommand{
  public static void main(String args[])throws Exception
  {
    Scanner sc=new Scanner(System.in);
    while(true)
    {
    String compcommand=sc.nextLine();
    String command[]={"create","insert","delete","print","select","drop"};
    String keyword[]={"as","create","insert","delete","print","select","drop","groupby","in","into","quit","from","having","where","asc","desc"};
    compcommand=compcommand.replace("("," ( ");
    compcommand=compcommand.replace(")"," ) ");
    compcommand=compcommand.replace(";","");
    compcommand=compcommand.replace(","," , ");
    compcommand=compcommand.trim().replaceAll("\\s{2,}", " ");
    String token[]=compcommand.split(" ");
    // for(int i=0;i<token.length;i++)
    // {
    //   System.out.println(token[i]);
    // }
    List<String> commands=Arrays.asList(command);
    List<String> keywords=Arrays.asList(keyword);
    if(commands.contains(token[0]))
    {
      if(token[0].equals("create"))
      {
        Create obj=new Create();
        if(obj.validate(token))
        {
          if(obj.createtable(token))
            System.out.println("Table Created");
        }
      }
      else if(token[0].equals("insert"))
      {
        Insert obj=new Insert();
        if(obj.validate(token))
        if(obj.insertvalues(token))
        System.out.print("in table");
      }
      else if(token[0].equals("drop"))
      {
        Delete obj=new Delete();
        if(obj.validate(token)){

        }
      }
      else if(token[0].equals("select"))
      {
        Select obj=new Select();
        if(obj.validate(token)){

        }
      }
      else{

      }
    }
    else if(token[0].equals("q"))
    {
      break;
    }
    else
    {
        System.out.println("comand not found");
    }

  }
}
}
