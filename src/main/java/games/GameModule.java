package games;

import storage.StoreKey;

import java.util.Vector;

/**
 * Created by dhites on 10/8/14.
 */
public interface GameModule extends StoreKey {

    public abstract Vector<Move> getMoves();
    public abstract GameModule doMove(Move move) throws InvalidMoveException;
    public abstract Value getValue();
    public abstract String toString();
    public abstract int hashCode();
    public abstract boolean equals(Object obj);

}


