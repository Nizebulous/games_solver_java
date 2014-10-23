package players;

import games.Move;
import games.TicTacToeModule;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.Vector;

public class ComputerPlayerLogicTest extends TestCase {

    public void testSelectMove() throws Exception {
        ComputerPlayerLogic player = new ComputerPlayerLogic(TicTacToeModule.class);
        TicTacToeModule gameModule = new TicTacToeModule();
        Vector<Move> moves = gameModule.getMoves();
        Move move = player.selectMove(gameModule, moves);
        Assert.assertEquals(move, moves.elementAt(0));
    }

}