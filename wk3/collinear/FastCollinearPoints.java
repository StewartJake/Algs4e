public class FastCollinearPoints
{
    private LineSegment[] lineSegs;
    private int count;

    public FastCollinearPoints(Point[] points)
    {
        lineSegs = new LineSegment[points.length];
        LineSegment slopes = new LineSegment[points.length];
        maxIter = points.length;
        count = 0;

        for (int p = 0; p < maxIter - 1; p++)
        {
            for (int q = p + 1; q < maxIter; q++)
                slopes[p] = points[p].slopeTo(points[q]);
            if (p == maxIter - 1)
            {

                Arrays.sort(slopes, slopeOrder()); 
                int slopesCounter = 0;
                Double slope = slopes[0];
                for (int q = 1; q < slopes.length; q++)
                {
                    if (slopes[q] == slopes)
                        slopeCounter++;
                    else
                    {
                        if (slopeCounter >= 3)

    }
  
   
    public int numberOfSegments()
   {    return count;   }


    public LineSegment[] segment()
    {   return lineSegs; }
}           
