package storage;

import games.Value;

import java.util.Hashtable;

/**
 * Created by dhites on 10/10/14.
 */
public class NaiveMemStore implements SolutionStore {
    private static Hashtable<String, Hashtable<StoreKey, Value>> memStore = new Hashtable<String, Hashtable<StoreKey, Value>>();
    private Hashtable<StoreKey, Value> myMemStore = null;
    private String label = null;

    public NaiveMemStore(String label) {
        this.label = label;
        myMemStore = memStore.get(label);
        if (myMemStore == null) {
            myMemStore = new Hashtable<StoreKey, Value>();
        }
    }

    public void setValue(StoreKey key, Value value) {
        myMemStore.put(key, value);
    }

    public Value getValue(StoreKey key) {
        Value value = myMemStore.get(key);
        if (value == null) {
            value = Value.UNKNOWN;
        }
        return value;
    }

    public void store() {
        memStore.put(label, myMemStore);
    }
}
