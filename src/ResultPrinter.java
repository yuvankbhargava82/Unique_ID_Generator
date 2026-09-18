import java.util.List;
public class ResultPrinter 
{
    private final ResultAnalyzer analyzer=new ResultAnalyzer();
    public void print(IDRequest request,List<String> ids,long timeNanos) 
    {
        System.out.println();
        System.out.println("==============================================");
        System.out.println("ENHANCED UNIQUE ID GENERATOR");
        System.out.println("==============================================");
        System.out.println("Method    : "+request.getMethod());
        System.out.println("Total IDs : "+ids.size());
        for(int i=0;i<ids.size();i++) 
        {
            System.out.printf("%5d.%s%n",i+1,ids.get(i));
        }
        System.out.println("----------------------------------------------");
        System.out.println("Unique IDs : "+analyzer.countUnique(ids));
        System.out.println("Duplicates : "+analyzer.countDuplicates(ids));
        System.out.printf("Avg. length : %.2f characters%n",analyzer.averageLength(ids));
        System.out.printf("Time taken  : %.3f ms%n",timeNanos / 1_000_000.0);
        System.out.println("All unique?: "+(analyzer.allUnique(ids)?"YES":"NO"));
        System.out.println("----------------------------------------------");
    }
    public void printComparisonHeader() 
    {
        System.out.println();
        System.out.println("ID Generation Comparison");
        System.out.println("------------------------------------------------");
        System.out.printf("%-14s|%-6s|%-8s|%s%n","Method","Count","Unique","Time");
        System.out.println("------------------------------------------------");
    }
    public void printComparisonRow(String method,int count,int unique,long timeNanos) 
    {
        System.out.printf("%-14s|%-6d|%-8d|%.3fms%n",method,count,unique,timeNanos/1_000_000.0);
    }
    public void printHelp() 
    {
        System.out.println();
        System.out.println("Enhanced Unique ID Generator");
        System.out.println("Methods:");
        System.out.println("  uuid(1) <count>");
        System.out.println("  timestamp(2) <count>");
        System.out.println("  sequential(3) <count>");
        System.out.println("  hybrid(4) <count>");
        System.out.println("  alphanumeric(5) <count> <length>");
        System.out.println("  snowflake(6) <count> <workerId>");
        System.out.println(" To compare all the methods:");
        System.out.println("  compare <count>");
    }
}
