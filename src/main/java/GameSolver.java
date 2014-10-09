import games.*;
import players.HumanPlayerLogic;
import players.PlayerLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

class GameSolver {
    private enum Game {
        TicTacToe
    }
    private enum Action {
        solve,
        play
    }
    private enum Player {
        HumanPlayer
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Java Game Solver!");
        GameModule gameModule = promptGameModule();
        gameMenu(gameModule);
	}

    private static GameModule promptGameModule() {
        System.out.println("Here are the available games:");
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
        return getGameModule(game);
    }

    private static GameModule getGameModule(Game game) {
        switch(game) {
            case TicTacToe:
                return new TicTacToeModule();
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

    private static void gameMenu(GameModule gameModule) {
        System.out.println("What would you like to do?\nOptions:");
        for (Action a : Action.values()) {
            System.out.println(a.toString());
        }
        System.out.print("Action :>> ");
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
                play(gameModule);
                break;
        }
    }

    private static void solve() {

    }

    private static void play(GameModule gameModule) {
        PlayerLogic[] players = {
                new HumanPlayerLogic(),
                new HumanPlayerLogic()
        };
        int whose_turn = 0;
        Value value = Value.UNKNOWN;
        do {
            System.out.print(gameModule);
            Vector<Move> moves = gameModule.getMoves();
            Move move = players[whose_turn].selectMove(moves);
            try {
                gameModule.doMove(move);
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