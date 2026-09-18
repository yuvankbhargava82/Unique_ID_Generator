import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
public class IDGenerator // Implements the six ID generation methods 
{
    private static final SecureRandom RANDOM=new SecureRandom();
    private static final AtomicLong COUNTER=new AtomicLong(0);
    private static final AtomicLong HYBRID_COUNTER=new AtomicLong(0);
    private static final String CHARS="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    // snowflake-style settings
    private static final long EPOCH=1609459200000L;
    private static final int MAX_WORKER_ID=1023;
    private static final int MAX_SEQUENCE=4095;
    private static int workerId=1;
    private static long lastSnowflakeTime=-1L;
    private static long snowflakeSequence=0L;
    // timestamp ID settings
    private static long lastTimestamp=-1L;
    private static int timestampSequence=0;
    public static String generateUUID() 
    {
        return UUID.randomUUID().toString();
    }
    public static synchronized String generateTimestampID() {
        long now=System.currentTimeMillis();
        if(now==lastTimestamp) 
        {
            timestampSequence++;
            if (timestampSequence>9999) 
            {
                now = waitNextMillis(lastTimestamp);
                timestampSequence=0;
            }
        } 
        else 
        {
            timestampSequence=0;
        }

        lastTimestamp=now;
        return String.format("%d-%04d",now,timestampSequence);
    }
    public static long generateSequentialID() 
    {
        return COUNTER.incrementAndGet();
    }
    public static String generateHybridID() 
    {
        long timestamp=System.currentTimeMillis();
        int randomPart=RANDOM.nextInt(1_000_000);
        long sequence=HYBRID_COUNTER.incrementAndGet()%10_000;
        return String.format("%d-%06d-%04d",timestamp,randomPart,sequence);
    }
    public static String generateAlphanumericID(int length) 
    {
        StringBuilder id=new StringBuilder(length);
        for(int i=0;i<length;i++) 
        {
            id.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
        }
        return id.toString();
    }
    public static synchronized long generateSnowflakeID() 
    {
        long now=System.currentTimeMillis();
        if(now<lastSnowflakeTime) 
        {
            throw new IllegalStateException("System clock moved backwards");
        }
        if(now==lastSnowflakeTime) 
        {
            snowflakeSequence++;
            if(snowflakeSequence>MAX_SEQUENCE) 
            {
                now=waitNextMillis(lastSnowflakeTime);
                snowflakeSequence=0L;
            }
        } 
        else
        {
            snowflakeSequence=0L;
        }
        lastSnowflakeTime=now;
        long timestampPart=(now-EPOCH)<<22;
        long workerPart=((long)workerId)<<12;
        return timestampPart|workerPart|snowflakeSequence;
    }
    public static void setWorkerId(int id) 
    {
        if(id<0||id>MAX_WORKER_ID) 
        {
            throw new IllegalArgumentException("Worker ID must be between 0 and 1023");
        }
        workerId=id;
    }
    private static long waitNextMillis(long previousTime) 
    {
        long now=System.currentTimeMillis();
        while(now<=previousTime) 
        {
            Thread.yield();
            now=System.currentTimeMillis();
        }
        return now;
    }
}
