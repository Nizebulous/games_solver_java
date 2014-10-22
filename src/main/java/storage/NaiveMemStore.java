package storage;

import games.Value;

import java.util.Hashtable;

/**
 * Created by dhites on 10/10/14.
 */
public class NaiveMemStore implements SolutionStore {
    private Hashtable<StoreKey, Value> memStore;

    public NaiveMemStore(Enum label) {
        memStore = new Hashtable<StoreKey, Value>();
    }

    public void setValue(StoreKey key, Value value) {
        memStore.put(key, value);
    }

    public Value getValue(StoreKey key) {
        Value value = memStore.get(key);
        if (value == null) {
            value = Value.UNKNOWN;
        }
        return value;
    }
}
