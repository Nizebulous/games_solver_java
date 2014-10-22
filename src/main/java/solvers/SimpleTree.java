package solvers;

import games.GameModule;
import games.InvalidMoveException;
import games.Move;
import games.Value;
import storage.SolutionStore;

import java.util.Vector;

/**
 * Created by dhites on 10/10/14.
 */
public class SimpleTree implements Solver {
    Class gameModuleClass;
    SolutionStore solution;

    public SimpleTree(Class gameModuleClass, SolutionStore solution) {
        this.gameModuleClass = gameModuleClass;
        this.solution = solution;
    }

    @Override
    public void solve() {
        GameModule gameModule = null;
        try {
            gameModule = (GameModule) gameModuleClass.newInstance();
        } catch (Exception exception) {
            System.out.println("Can't create class" + gameModuleClass.toString());
            System.exit(1);
        }
        solveHelper(gameModule);
    }

    private Value solveHelper(GameModule gameModule) {
        Vector<Move> moves = gameModule.getMoves();
        GameModule nextPosition = null;
        int ties = 0;
        int losses = 0;
        Value value = solution.getValue(gameModule);
        if (value != Value.UNKNOWN) {
            return value;
        }
        value = gameModule.getValue();
        if (value != Value.UNKNOWN) {
            solution.setValue(gameModule, value);
            return value;
        }
        for (Move move : moves) {
            try {
                nextPosition = gameModule.doMove(move);
            } catch (InvalidMoveException exception) {
                System.out.println("Ruh-roh, something bad happened in doMove");
            }
            value = solveHelper(nextPosition);
            switch (value) {
                case TIE:
                    ties++;
                    break;
                case LOSS:
                    losses++;
                    break;
            }
        }
        if (losses > 0) {
            value = Value.WIN;
        } else if (ties > 0) {
            value = Value.TIE;
        } else {
            value = Value.LOSS;
        }
        solution.setValue(gameModule, value);
        return value;
    }
}
