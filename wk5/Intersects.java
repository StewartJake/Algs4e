Node x = root;
while (x != null)
{
    if      (x.interval.intersects(lo, hi)) return x.interval;
    else if (x.left == null)                x = x.right;
    else if (x.left.max < lo)               x = x.right;
    else                                    x = x.left;
}
return null;
