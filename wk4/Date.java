public final class Date implements Comparable<Date>
{
    private final int month;
    private final int day;
    private final int year;


    public boolean equals(Object y)
    {
        if (y == this)  return true;

        if (y == null) return false;

        if (y.getClass() != this.getClass())
            return false;

        Date that = (Date) y;
        if (this.day    !=  that.day    )   return false;
        if (this.month  !=  that.month  )   return false;
        if (this.year   !=  that.year   )   return false;
        return true;
    }
}
