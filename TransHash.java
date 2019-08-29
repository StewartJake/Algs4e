// an example of a hash for Transaction object
public int hashCode()
{
    int hash = 17;  // non-zero constant
    hash = 31 * hash + who.hashCode();  // for refernce types, use hashCode()
    hash = 31 * hash + when.hashCode();
    hash = 31 * hash + ((Double) amount).hashCode();    // primitive types | hashCode of wrapper type
    return hash;
 
