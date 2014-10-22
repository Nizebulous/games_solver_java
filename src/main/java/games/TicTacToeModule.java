package games;

import java.util.Vector;

public class TicTacToeModule implements GameModule {
    private final int player_one = 0;
    private final int player_two = 1;
    private final int empty = 2;
    private final int[] players = {
            player_one,
            player_two
    };
    private final char[] pieces = {'X', 'O', ' '};
    private final int[][] board = {
            {empty, empty, empty},
            {empty, empty, empty},
            {empty, empty, empty}
    };

    public static Game getLabel() {
        return Game.TicTacToe;
    }

    public TicTacToeModule() {

    }

    public TicTacToeModule(TicTacToeModule gameModule) {
        for (int i = 0; i < gameModule.board.length; i++) {
            System.arraycopy(gameModule.board[i], 0, board[i], 0, board[i].length);
        }
    }

    public TicTacToeModule(TicTacToeModule gameModule, TicTacToeMove move) throws InvalidMoveException {
        this(gameModule);
        if (move.x >= board.length || move.y >= board[move.x].length || board[move.x][move.y] != empty) {
            throw new InvalidMoveException();
        }
        board[move.x][move.y] = players[getPlayerIndex()];

    }

    @Override
    public Vector<Move> getMoves() {
        Vector<Move> moves = new Vector<Move>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == empty) {
                    moves.add(new TicTacToeMove(i, j));
                }
            }
        }
        return moves;
    }

    @Override
    public GameModule doMove(Move move) throws InvalidMoveException {
        return new TicTacToeModule(this, (TicTacToeMove) move);
    }

    @Override
    public Value getValue() {
        // We want the piece of the player whose turn it ISN'T
        // So we get the current player, then get the other player
        // and get their piece
        int matching_piece = players[(getPlayerIndex() + 1) % players.length];
        boolean empty_seen = false;
        // rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == matching_piece && board[i][1] == matching_piece && board[i][2] == matching_piece) {
                return Value.LOSS;
            }
            if (board[i][0] == empty || board[i][1] == empty || board[i][2] == empty) {
                empty_seen = true;
            }
        }
        // columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == matching_piece && board[1][i] == matching_piece && board[2][i] == matching_piece) {
                return Value.LOSS;
            }
        }
        if (board[0][0] == matching_piece && board[1][1] == matching_piece && board[2][2] == matching_piece) {
            return Value.LOSS;
        }
        if (board[0][2] == matching_piece && board[1][1] == matching_piece && board [2][0] == matching_piece) {
            return Value.LOSS;
        }
        if (empty_seen) {
            return Value.UNKNOWN;
        } else {
            return Value.TIE;
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int[] row : board) {
            for (int slot : row) {
                hash <<= 2;
                hash += slot;
            }
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof TicTacToeModule && obj.hashCode() == this.hashCode());
    }

    @Override
    public String toString() {
        String board_rep = "\n===================\n\nTIC-TAC-TOE\n\n";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board_rep += pieces[board[i][j]];
                if (j < board[i].length - 1) {
                    board_rep += '|';
                }
            }
            board_rep += "\n";
            if (i < board.length - 1) {
                board_rep += "-----\n";
            }
        }
        board_rep += "\nPlayer " + (getPlayerIndex() + 1) + "'s turn.\n\n===================\n\n";
        return board_rep;
    }

    private int getPlayerIndex() {
        int player_one_pieces = 0;
        int player_two_pieces = 0;
        for (int[] row : board) {
            for (int space : row) {
                if (space == player_one) {
                    player_one_pieces++;
                } else if (space == player_two) {
                    player_two_pieces++;
                }
            }
        }
        if (player_one_pieces <= player_two_pieces) {
            return player_one;
        } else {
            return player_two;
        }
    }
}

class TicTacToeMove extends Move {
    public int x = -1;
    public int y = -1;

    public TicTacToeMove(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object move) {
        return (move instanceof TicTacToeMove && x == ((TicTacToeMove) move).x && y == ((TicTacToeMove) move).y);
    }

    @Override
    public String toString() {
        return "(" + (x+1) + "," + (y+1) + ")";
    }
}