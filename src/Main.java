import java.util.List;
public class Main // Main Program which operates everything
{ public static void main(String args[]) 
    {  
        ResultPrinter printer=new ResultPrinter();
        if(args.length==0||isHelp(args[0])) 
        {
            printer.printHelp();
            return;
        }
        try{
            if(args[0].equalsIgnoreCase("compare")) 
            {
                runComparison(args,printer);
                return;
            }
            IDRequest request=buildRequest(args);
            GenerationService service=new GenerationService();
            long start=System.nanoTime();
            List<String> ids=service.generate(request);
            long end=System.nanoTime();
            printer.print(request,ids,end-start);
        } 
        catch(NumberFormatException e)
        {
            System.out.println("Error: count, length and worker ID "+"must be numbers.");
            System.out.println();
            printer.printHelp();
        }
        catch(IllegalArgumentException e) 
        {
            System.out.println("Error:"+e.getMessage());
            System.out.println();
            printer.printHelp();
        }
        catch(Exception e) 
        {
            System.out.println("Unexpected error: "+e.getMessage());
        }
    }
    private static IDRequest buildRequest(String args[]) 
    {
        if(args.length<2) 
        {
            throw new IllegalArgumentException("Please provide a method and count");
        }
        IDMethod method=IDMethod.fromString(args[0]);
        int count=Integer.parseInt(args[1]);
        int length=0;
        int workerId=1;
        if(method==IDMethod.ALPHANUMERIC) 
        {
            if (args.length<3) 
            {
                throw new IllegalArgumentException("Alphanumeric requires a length");
            }
            length=Integer.parseInt(args[2]);
        }
        if(method == IDMethod.SNOWFLAKE&&args.length >= 3) 
        {
            workerId = Integer.parseInt(args[2]);
        }
        return new IDRequest(method,count,length,workerId);
    }
    private static void runComparison(String args[],ResultPrinter printer) 
    {
        if(args.length<2) 
        {
            throw new IllegalArgumentException("Compare requires a count");
        }
        int count=Integer.parseInt(args[1]);
        if(count<1||count>100000) 
        {
            throw new IllegalArgumentException("Count must be between 1 and 100000");
        }
        GenerationService service=new GenerationService();
        ResultAnalyzer analyzer=new ResultAnalyzer();
        IDMethod[] methods={IDMethod.UUID,IDMethod.TIMESTAMP,IDMethod.SEQUENTIAL,IDMethod.HYBRID,IDMethod.ALPHANUMERIC,IDMethod.SNOWFLAKE};
        printer.printComparisonHeader();
        for(IDMethod method:methods) 
        {
            int length=method==IDMethod.ALPHANUMERIC?8:0;
            IDRequest request=new IDRequest(method,count,length,1);
            long start=System.nanoTime();
            List<String> ids=service.generate(request);
            long end=System.nanoTime();
            printer.printComparisonRow(method.toString(),count,analyzer.countUnique(ids),end-start);
        }
    }
    private static boolean isHelp(String value) 
    {
        return value.equalsIgnoreCase("help")||value.equalsIgnoreCase("-h")||value.equalsIgnoreCase("--help");
    }
}
