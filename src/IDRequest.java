public class IDRequest {
    private final IDMethod method;
    private final int count;
    private final int length;
    private final int workerId;
    public IDRequest(IDMethod method, int count, int length, int workerId) 
    {
        this.method=method;
        this.count=count;
        this.length=length;
        this.workerId=workerId;
    }
    public IDMethod getMethod() 
    {
        return method;
    }
    public int getCount() 
    {
        return count;
    }
    public int getLength() 
    {
        return length;
    }
    public int getWorkerId() 
    {
        return workerId;
    }
}
