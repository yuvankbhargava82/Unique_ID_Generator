import java.util.ArrayList;
import java.util.List;
public class GenerationService // Connects the user's request with the ID generator
{
    public List<String> generate(IDRequest request) 
    {
        ValidationUtils.validate(request);
        if(request.getMethod() == IDMethod.SNOWFLAKE) 
        {
            IDGenerator.setWorkerId(request.getWorkerId());
        }
        List<String> ids=new ArrayList<String>(request.getCount());
        for(int i = 0;i<request.getCount();i++) 
        {
            ids.add(generateOne(request));
        }
        return ids;
    }
    private String generateOne(IDRequest request) 
    {
        switch(request.getMethod()) 
        {
            case UUID:
                return IDGenerator.generateUUID();
            case TIMESTAMP:
                return IDGenerator.generateTimestampID();
            case SEQUENTIAL:
                return String.valueOf(IDGenerator.generateSequentialID());
            case HYBRID:
                return IDGenerator.generateHybridID();
            case ALPHANUMERIC:
                return IDGenerator.generateAlphanumericID(request.getLength());
            case SNOWFLAKE:
                return String.valueOf(IDGenerator.generateSnowflakeID());
            default:
                throw new IllegalArgumentException("Unsupported generation method.");
        }
    }
}