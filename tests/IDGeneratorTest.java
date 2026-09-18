import java.util.List;
public class IDGeneratorTest // Simple validation tests
{
    public static void main(String args[]) 
    {
        GenerationService service=new GenerationService();
        ResultAnalyzer analyzer=new ResultAnalyzer();
        testUUID(service,analyzer);
        testSequential(service,analyzer);
        testTimestamp(service,analyzer);
        testAlphanumeric(service,analyzer);
        testSnowflake(service,analyzer);
        testInvalidInput();
        System.out.println("All validation tests passed");
    }
    private static void testUUID(GenerationService service,ResultAnalyzer analyzer)
    {
        List<String> ids=service.generate(new IDRequest(IDMethod.UUID, 20, 0, 1));
        check(ids.size()==20,"UUID count test failed");
        check(analyzer.allUnique(ids),"UUID uniqueness test failed");
        for(String id:ids) 
        {
            check(id.length() == 36,"UUID length test failed");
        }
    }
    private static void testSequential(GenerationService service,ResultAnalyzer analyzer) 
    {
        List<String> ids=service.generate(new IDRequest(IDMethod.SEQUENTIAL, 10, 0, 1));
        check(analyzer.allUnique(ids),"Sequential uniqueness test failed");
    }
    private static void testTimestamp(GenerationService service,ResultAnalyzer analyzer) 
    {
        List<String> ids = service.generate(new IDRequest(IDMethod.TIMESTAMP, 50, 0, 1));
        check(analyzer.allUnique(ids),"Timestamp uniqueness test failed.");
    }
    private static void testAlphanumeric(GenerationService service,ResultAnalyzer analyzer) 
    {
        List<String> ids=service.generate(new IDRequest(IDMethod.ALPHANUMERIC, 20, 8, 1));
        check(analyzer.allUnique(ids),"Alphanumeric uniqueness test failed.");
        for(String id:ids) 
        {
            check(id.length()==8,"Alphanumeric length test failed.");
            check(id.matches("[A-Z0-9]+"),"Alphanumeric format test failed.");
        }
    }
    private static void testSnowflake(GenerationService service,ResultAnalyzer analyzer) 
    {
        List<String> ids=service.generate(new IDRequest(IDMethod.SNOWFLAKE, 50, 0, 7));
        check(analyzer.allUnique(ids),"Snowflake uniqueness test failed");
    }
    private static void testInvalidInput() 
    {
        boolean failedCorrectly = false;
        try{
                new GenerationService().generate(new IDRequest(IDMethod.UUID, 0, 0, 1));
        } 
        catch (IllegalArgumentException e) 
        {
            failedCorrectly = true;
        }
        check(failedCorrectly,"Invalid input test failed.");
    }
    private static void check(boolean condition,String message) 
    {
        if(!condition) 
        {
            throw new AssertionError(message);
        }
    }
}