package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);

	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// verificando acima da peça
		p.setValues(position.getRow() - 1, position.getColoumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColoumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		if (getBoard().positionExists(p) && isThereOppenentPiece(p)) {
			mat[p.getRow()][p.getColoumn()] = true;
		}

		// LEFT
		p.setValues(position.getRow(), position.getColoumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColoumn()] = true;
			p.setColoumn(p.getColoumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOppenentPiece(p)) {
			mat[p.getRow()][p.getColoumn()] = true;
		}

		// RIGHT
		p.setValues(position.getRow(), position.getColoumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColoumn()] = true;
			p.setColoumn(p.getColoumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOppenentPiece(p)) {
			mat[p.getRow()][p.getColoumn()] = true;
		}

		// BELOW= BAIXO
		p.setValues(position.getRow() + 1, position.getColoumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColoumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		if (getBoard().positionExists(p) && isThereOppenentPiece(p)) {
			mat[p.getRow()][p.getColoumn()] = true;
		}
		return mat;
	}

}
