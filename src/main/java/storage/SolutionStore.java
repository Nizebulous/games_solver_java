package storage;

import games.Value;

/**
 * Created by dhites on 10/10/14.
 */
public interface SolutionStore {

    public abstract void setValue(StoreKey key, Value value);

    public abstract Value getValue(StoreKey key);

}

