package games;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by dhites on 10/22/14.
 */
public class TicTacToeMoveTest {

    @Test
    public void testEquals() throws Exception {
        TicTacToeMove move_one = new TicTacToeMove(1, 1);
        TicTacToeMove move_two = new TicTacToeMove(1, 1);
        Assert.assertEquals(move_one, move_two);
    }
}
