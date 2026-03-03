package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

//partida de xadrez
public class ChessMatch {

	private Board board;

	//Quando uma partida é criada, ele automaticamente cria um tabuleiro 8x8 
	public ChessMatch() {
		board = new Board(8, 8);//dimensão do tabuleiro
		initialSetup();
	}
	
	
	//retorna uma matriz de peças de xadrez conrrespondente a partida.
	public ChessPiece [][] getPieces(){
		//Cria uma matriz vazia de peças de xadrez do mesmo tamanho do tabuleiro
		ChessPiece [][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for(int i=0; i<board.getRows(); i++) {
			for(int j=0; j<board.getColumns();j++) {
				//Pega cada peça do tabuleiro e converte para ChessPiece
				mat[i][j] = (ChessPiece)board.piece(i,j);
			}
		}
		return mat;
	}
	
	
	private void initialSetup() {
		
		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
		board.placePiece(new King(board, Color.BLACK), new Position(1, 4));
		board.placePiece(new King(board, Color.WHITE), new Position(7, 4));

	
	}
}
