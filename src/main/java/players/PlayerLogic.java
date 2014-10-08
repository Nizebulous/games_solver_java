package players;

import games.Move;

import java.util.Vector;

/**
 * Created by dhites on 10/8/14.
 */
public interface PlayerLogic {
    public abstract Move selectMove(Vector<Move> moves);
}
