package games;

import org.junit.Assert;
import org.junit.Test;

import java.util.Vector;

public class TicTacToeModuleTest {

    @Test
    public void testDoMoveGetMoves() throws Exception {
        TicTacToeModule ttt_module = new TicTacToeModule();
        Vector<Move> moves = ttt_module.getMoves();
        Assert.assertEquals(moves.size(), 9);
        TicTacToeMove first_move = (TicTacToeMove) moves.firstElement();
        ttt_module = (TicTacToeModule) ttt_module.doMove(first_move);
        moves = ttt_module.getMoves();
        for (Move move : moves) {
            Assert.assertNotEquals(first_move, move);
        }
        Assert.assertEquals(moves.size(), 8);
        for (Move move : moves) {
            ttt_module = (TicTacToeModule) ttt_module.doMove(move);
        }
        moves = ttt_module.getMoves();
        Assert.assertEquals(moves.size(), 0);
    }

    @Test(expected = InvalidMoveException.class)
    public void testOutOfBoundsMove() throws Exception {
        TicTacToeModule ttt_module = new TicTacToeModule();
        ttt_module.doMove(new TicTacToeMove(4, 4));
    }

    @Test(expected = InvalidMoveException.class)
    public void testAlreadyFilledMove() throws Exception {
        TicTacToeModule ttt_module = new TicTacToeModule();
        ttt_module = (TicTacToeModule) ttt_module.doMove(new TicTacToeMove(0, 0));
        ttt_module.doMove(new TicTacToeMove(0, 0));
    }

    @Test
    public void testGetValue() throws Exception {
        TicTacToeModule ttt_module = new TicTacToeModule();
        Value value = ttt_module.getValue();
        Assert.assertEquals(value, Value.UNKNOWN);
        Vector<Move> moves = ttt_module.getMoves();
        for (Move move : moves) {
            ttt_module = (TicTacToeModule) ttt_module.doMove(move);
        }
        value = ttt_module.getValue();
        Assert.assertEquals(value, Value.LOSS);
    }

    @Test
    public void testEqual() throws Exception {
        TicTacToeModule ttt_one = new TicTacToeModule();
        TicTacToeModule ttt_two = new TicTacToeModule();
        Assert.assertEquals(ttt_one, ttt_two);
    }

}

