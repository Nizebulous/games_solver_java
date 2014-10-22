import games.*;
import players.ComputerPlayerLogic;
import players.HumanPlayerLogic;
import players.PlayerLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

class GameSolver {
    private enum Action {
        solve,
        play
    }
    private enum Player {
        HumanPlayer
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Java Game Solver!\n");
        gameMenu(promptGameModule());
	}

    private static Class<? extends GameModule> promptGameModule() {
        System.out.println("Here are the available games:\n");
        for (Game g : Game.values()) {
            System.out.println(g.toString());
        }
        System.out.print("\nSelect a game :>> ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Game game = null;
        try {
            game = Game.valueOf(br.readLine());
        } catch (IOException ioe) {
            System.out.println("Input error!");
            System.exit(1);
        }
        return getGameModuleClass(game);
    }

    private static Class<? extends GameModule> getGameModuleClass(Game game) {
        switch(game) {
            case TicTacToe:
                return TicTacToeModule.class;
            default:
                return null;
        }
    }

    private static PlayerLogic getPlayer(Player player) {
        switch (player) {
            case HumanPlayer:
                return new HumanPlayerLogic();
            default:
                return null;
        }
    }

    private static void gameMenu(Class<? extends GameModule> gameModuleClass) {
        System.out.println("What would you like to do?\n\nOptions:");
        for (Action a : Action.values()) {
            System.out.println(a.toString());
        }
        System.out.print("\nAction :>> ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Action action = null;
        try {
            action = Action.valueOf(br.readLine());
        } catch (IOException ioe) {
            System.out.println("Input error!");
            System.exit(1);
        }
        switch(action) {
            case solve:
                solve();
                break;
            case play:
                //solve();
                play(gameModuleClass);
                break;
        }
    }

    private static void solve() {

    }

    private static void play(Class<? extends GameModule> gameModuleClass) {
        PlayerLogic[] players = {
                new HumanPlayerLogic(),
                new ComputerPlayerLogic(gameModuleClass)
        };

        GameModule gameModule = null;
        try {
            gameModule = gameModuleClass.newInstance();
        } catch (Exception exception) {
            System.out.println("Issue creating game module");
            System.exit(1);
        }
        int whose_turn = 0;
        Value value = Value.UNKNOWN;
        do {
            System.out.print(gameModule);
            Vector<Move> moves = gameModule.getMoves();
            Move move = players[whose_turn].selectMove(gameModule, moves);
            try {
                gameModule = gameModule.doMove(move);
            } catch (InvalidMoveException inv_move) {
                System.out.println("Invalid Move Attempted!");
                continue;
            }
            whose_turn = (whose_turn + 1) % 2;
            value = gameModule.getValue();
        } while (value == Value.UNKNOWN);
        System.out.print(gameModule);
        System.out.println("Game Over!");
        switch (value) {
            case TIE:
                System.out.println("Cat's Game!");
                break;
            case LOSS:
                System.out.println("Player " + (((whose_turn + 1) % 2) + 1) + " wins!");
        }
    }

}