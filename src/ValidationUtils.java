public class ValidationUtils 
{
    private ValidationUtils() 
    {
    }
    public static void validate(IDRequest request) 
    {
        if(request == null) 
        {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if(request.getCount()<1||request.getCount()>100000) 
        {
            throw new IllegalArgumentException("Count must be between 1 and 100000");
        }
        if(request.getMethod()==IDMethod.ALPHANUMERIC) 
        {
            if (request.getLength()<1||request.getLength()>64) 
            {
                throw new IllegalArgumentException("Length must be between 1 and 64");
            }
        }
        if(request.getMethod()==IDMethod.SNOWFLAKE) 
        {
            if(request.getWorkerId()<0||request.getWorkerId()>1023) 
            {
                throw new IllegalArgumentException("Worker ID must be between 0 and 1023");
            }
        }
    }
}
