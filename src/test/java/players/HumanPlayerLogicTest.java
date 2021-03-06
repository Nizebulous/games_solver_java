package players;

import games.Move;
import games.TicTacToeModule;
import junit.framework.TestCase;
import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.util.Vector;

public class HumanPlayerLogicTest extends TestCase {

    public void testSelectMove() throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        HumanPlayerLogic player = new HumanPlayerLogic();
        TicTacToeModule gameModule = new TicTacToeModule();
        Vector<Move> moves = gameModule.getMoves();
        Move move = player.selectMove(gameModule, moves);
        Assert.assertEquals(move, moves.elementAt(0));
    }

}