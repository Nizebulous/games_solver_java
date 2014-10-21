package games;

import java.util.Vector;

/**
 * Created by dhites on 10/8/14.
 */
public interface GameModule {
    public abstract Vector<Move> getMoves();
    public abstract void doMove(Move move) throws InvalidMoveException;
    public abstract Value getValue();
    public abstract String toString();

}


