public OrigJavaHash
{
    public int hashCode()
    {
        int hash = 0;
        int skip = Math.max(1, length() / 8);
        for (int i = 0; i < length(); i += skip)
            hash = s[i] + (37 * hash);
        return hash;
    }
}

// really bad collisions in real world application
