package players;

import games.*;
import solvers.SimpleTree;
import solvers.Solver;
import storage.NaiveMemStore;
import storage.SolutionStore;

import java.lang.reflect.Method;
import java.util.Vector;

/**
 * Created by dhites on 10/21/14.
 */
public class ComputerPlayerLogic implements PlayerLogic {

    private SolutionStore gameSolution = null;
    private Solver solver = null;

    public ComputerPlayerLogic(Class<? extends GameModule> gameModuleClass) {
        gameSolution = new NaiveMemStore(gameModuleClass.toString());
        solver = new SimpleTree(gameModuleClass, gameSolution);
    }

    @Override
    public Move selectMove(GameModule gameModule, Vector<Move> moves) {
        Vector<Move> wins = new Vector<Move>();
        Vector<Move> losses = new Vector<Move>();
        Vector<Move> ties = new Vector<Move>();
        GameModule nextPosition = null;

        Value value = gameSolution.getValue(gameModule);
        if (value == Value.UNKNOWN) {
            System.out.print("Solving...");
            solver.solve();
            System.out.println("Solved!");
        }

        for (Move move : moves) {
            try {
                nextPosition = gameModule.doMove(move);
            } catch (InvalidMoveException exception) {
                System.out.println("Something terrible has occurred!");
            }
            value = gameSolution.getValue(nextPosition);
            switch (value) {
                case LOSS:
                    wins.addElement(move);
                    break;
                case TIE:
                    ties.addElement(move);
                    break;
                case WIN:
                    losses.addElement(move);
                    break;
            }
        }
        if (wins.size() > 0) {
            return wins.elementAt(0);
        } else if (ties.size() > 0) {
            return ties.elementAt(0);
        } else if (losses.size() > 0) {
            return losses.elementAt(0);
        } else {
            return null;
        }
    }

}
