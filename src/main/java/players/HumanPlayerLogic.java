package players;

import games.Move;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 * Created by dhites on 10/8/14.
 */
public class HumanPlayerLogic implements PlayerLogic {
    public Move selectMove(Vector<Move> moves) {
        int index;
        do {
            System.out.println("Possible moves:");
            for (int i = 0; i < moves.size(); i++) {
                System.out.println((i + 1) + ". " + moves.elementAt(i));
            }
            System.out.print("Select a move (");
            if (moves.size() > 0) {
                System.out.print("1 - " + (moves.size()) + ", ");
            }
            System.out.print("'q' to quit):>> ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String entry = null;
            try {
                entry = br.readLine();
            } catch (IOException ioe) {
                System.out.println("Input error!");
                System.exit(1);
            }
            if (entry.equals("q")) {
                System.exit(0);
            }
            try {
                index = Integer.parseInt(entry) - 1;
            } catch (NumberFormatException e) {
                index = -1;
            }
            if (index < 0 || index >= moves.size()) {
                System.out.println("\nINVALID INPUT!\n");
            }
        } while (index < 0 || index >= moves.size());
        return moves.elementAt(index);
    }
}
