package games;

import java.util.Vector;

public class TicTacToeModule implements GameModule {
    private final char player_one = 'X';
    private final char player_two = 'O';
    private final char empty = ' ';
    private final char[] players = {
            player_one,
            player_two
    };
    private final char[][] board = {
            {empty, empty, empty},
            {empty, empty, empty},
            {empty, empty, empty}
    };

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

    public void doMove(Move move) throws InvalidMoveException {
        TicTacToeMove myMove = (TicTacToeMove)move;
        if (myMove.x >= board.length || myMove.y >= board[myMove.x].length || board[myMove.x][myMove.y] != empty) {
            throw new InvalidMoveException();
        }
        board[myMove.x][myMove.y] = players[getPlayerIndex()];
    }

    public Value getValue() {
        // We want the piece of the player whose turn it ISN'T
        // So we get the current player, then get the other player
        // and get their piece
        char matching_piece = players[(getPlayerIndex() + 1) % 2];
        boolean empty_seen = false;
        // rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == matching_piece
                    && board[i][1] == matching_piece
                    && board[i][2] == matching_piece) {
                return Value.LOSS;
            }
            if (board[i][0] == empty
                    || board[i][1] == empty
                    || board[i][2] == empty) {
                empty_seen = true;
            }
        }
        // columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == matching_piece
                    && board[1][i] == matching_piece
                    && board[2][i] == matching_piece) {
                return Value.LOSS;
            }
        }
        if (board[0][0] == matching_piece
                && board[1][1] == matching_piece
                && board[2][2] == matching_piece) {
            return Value.LOSS;
        }
        if (board[0][2] == matching_piece
                && board[1][1] == matching_piece
                && board[2][0] == matching_piece) {
            return Value.LOSS;
        }
        if (empty_seen) {
            return Value.UNKNOWN;
        } else {
            return Value.TIE;
        }
    }

    public String toString() {
        String board_rep = "\n===================\n\nTIC-TAC-TOE\n\n";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board_rep += board[i][j];
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
        for (char[] row : board) {
            for (char space : row) {
                if (space == player_one) {
                    player_one_pieces++;
                } else if (space == player_two) {
                    player_two_pieces++;
                }
            }
        }
        if (player_one_pieces <= player_two_pieces) {
            return 0;
        } else {
            return 1;
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

    public boolean equals(Object move) {
        return (move instanceof TicTacToeMove && x == ((TicTacToeMove) move).x && y == ((TicTacToeMove) move).y);
    }

    public String toString() {
        return "(" + (x+1) + "," + (y+1) + ")";
    }
}