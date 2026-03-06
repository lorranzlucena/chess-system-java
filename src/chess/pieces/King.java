package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}


	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}
	
	
	@Override
	public boolean[][] possibleMoves() {
		boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
	
		//iniciando a posição
		Position p = new Position(0, 0);
		
		//above
		/**
		 * Lembrando que o xadrez começa de cima pra baixo
		 *  Linha 0  →  8  - - - - - - - -
			Linha 1  →  7  - - - - - - - -
			Linha 2  →  6  - - - - - - - -
			Linha 3  →  5  - - - - - - - -
			Linha 4  →  4  - - - - - - - -
			Linha 5  →  3  - - - - - - - -
			Linha 6  →  2  - - - - - - - -
			Linha 7  →  1  - - - - - - - -
						   a b c d e f g h
			quando se coloca que ele tem que pegar a linha -1 é pq a peça esta subindo.
			se ele ta na C3 então ele vai ta (L5,C2) pra subir tem que diminuir
			L4,c2
		 */
		p.setValues(position.getRow() -1, position.getColoumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColoumn()] = true;
		}
		
		
		
		//below
		p.setValues(position.getRow() +1, position.getColoumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColoumn()] = true;
		}
		
		//left
		p.setValues(position.getRow(), position.getColoumn()-1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColoumn()] = true;
		}
		
		//right
		
		p.setValues(position.getRow(), position.getColoumn()+1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColoumn()] = true;
		}
		
		//noroeste
		p.setValues(position.getRow() -1, position.getColoumn()-1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColoumn()] = true;
		}
		
		//nordeste
		p.setValues(position.getRow() - 1, position.getColoumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColoumn()] = true;
		}
		
		//sudoeste
		p.setValues(position.getRow() + 1, position.getColoumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColoumn()] = true;
		}
		
		
		
		//sudeste
		p.setValues(position.getRow() + 1, position.getColoumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColoumn()] = true;
		}
		
		return mat;
	}

	

	@Override
	public String toString() {
		return "K";
	}
	
	
}
