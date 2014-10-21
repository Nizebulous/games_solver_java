package storage;

import java.util.Hashtable;

/**
 * Created by dhites on 10/10/14.
 */
public class NaiveMemStore implements SolutionStore {
    private Hashtable memStore;

    public NaiveMemStore(Enum label) {
        memStore = new Hashtable();
    }

    public void setValue(StoreKey key, Object value) {

    }

    public Object getValue(StoreKey key) {
        return null;
    }
}
