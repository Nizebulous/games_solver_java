package storage;

/**
 * Created by dhites on 10/10/14.
 */
public interface StoreKey {

    public abstract long getHash();

    public abstract int hashCode();

    public abstract boolean equals(Object obj);
}
