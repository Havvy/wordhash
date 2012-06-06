package assignment8;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class MultiMap<K, V extends Comparable<V>>
{
    private final Map<K, Set<V>> impl = new HashMap<K, Set<V>>();

    public boolean add(K key, V value)
    {
        Set<V> vs = impl.get(key);
        if (vs == null)
        {
            vs = new TreeSet<V>();
            vs.add(value);
            impl.put(key, vs);
            return true;
        }
        return vs.add(value);
    }

    public boolean remove(K key, V value)
    {
        Set<V> vs = impl.get(key);
        if (vs == null)
            return false;
        boolean rv = vs.remove(value);
        if (rv && vs.isEmpty())
            impl.remove(key);
        return rv;
    }

    public boolean remove_all(K key)
    {
        return impl.remove(key) != null;
    }

    public Iterable<V> get(K key)
    {
        Set<V> vs = impl.get(key);
        if (vs != null)
            return Collections.unmodifiableSet(vs);
        else
            return Collections.emptySet();
    }
}
