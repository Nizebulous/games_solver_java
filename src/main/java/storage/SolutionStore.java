package storage;

/**
 * Created by dhites on 10/10/14.
 */
public interface SolutionStore {

    public abstract void setValue(StoreKey key, Object value);

    public abstract Object getValue(StoreKey key);

}

