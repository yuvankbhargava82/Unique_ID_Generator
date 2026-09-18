import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class ResultAnalyzer  // Calculates simple statistics for generated ID
{
    public int countUnique(List<String> ids)
    {
        return new HashSet<String>(ids).size();
    }
    public int countDuplicates(List<String> ids) 
    {
        return ids.size()-countUnique(ids);
    }
    public boolean allUnique(List<String> ids)
    {
        return countUnique(ids)==ids.size();
    }
    public double averageLength(List<String> ids) 
    {
        if(ids.isEmpty()) 
        {
            return 0.0;
        }
        long total = 0;
        for(String id : ids)
        {
            total+=id.length();
        }
        return (double)total/ids.size();
    }
}
