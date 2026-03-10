package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

//partida de xadrez
public class ChessMatch {

	private int turn;
	private Color currentPlayer;
	private Board board;
	
	private boolean check;
	
	
	private List<Piece> piecesOntheBoard = new ArrayList<>();
	private List<Piece> capturedPieces= new ArrayList<>();

	// Quando uma partida é criada, ele automaticamente cria um tabuleiro 8x8
	public ChessMatch() {
		board = new Board(8, 8);// dimensão do tabuleiro
		turn = 1;
		currentPlayer = Color.WHITE;
		check = false;
		
		initialSetup();
	}

	
	public int getTurn() {
		
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	
	public boolean getCheck() {
		return check;
	}
	
	// retorna uma matriz de peças de xadrez conrrespondente a partida.
	public ChessPiece[][] getPieces() {
		// Cria uma matriz vazia de peças de xadrez do mesmo tamanho do tabuleiro
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				// Pega cada peça do tabuleiro e converte para ChessPiece
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}

	
	public boolean [][] possibleMoves(ChessPosition sourcePosition){
		
		Position position	= sourcePosition.toPosition();
		validateSoucePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	
	//função responsavel pelas os moviemntos
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		// source = origem
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();

		validateSoucePosition(source);
		validateTargetPosition(source,target);

		Piece capturePiece = makeMove(source, target);

		if(testCheck(currentPlayer)) {
			undoMove(source, target, capturePiece);
			throw new ChessException("You can´t put yourself in check");
		}
		
		check = (testCheck(oppoenent(currentPlayer))) ? true : false;
		nextTurn();
		return (ChessPiece) capturePiece;

	}

	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturePiece = board.removePiece(target);
		board.placePiece(p, target);
		
		
		if(capturePiece != null) {
			piecesOntheBoard.remove(capturePiece);
			capturedPieces.add(capturePiece);
		}
		
		return capturePiece;
	}

	
	private void undoMove(Position source, Position target,Piece capturedPiece) {
		
		Piece p = board.removePiece(target);
		board.placePiece(p, source);
		
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOntheBoard.add(capturedPiece);
		}
		
	}
	
	
	private void validateSoucePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("there is no piece on source position");
		}
		
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessException("the chosen piece is not yours");
		}
		
		if (!board.piece(position).isThereAnyPOssivebleMove()) {
			throw new ChessException("there is no possible moves for the chosen piece");
		}
	}

	
	public void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("there chosen piece can´t move to target position ");
		}
	}
	
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE)? Color.BLACK : Color.WHITE;
	}
	
	private Color oppoenent(Color color) {
		
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king(Color color) {
		List<Piece> list = piecesOntheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());

		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece) p;
			}
		}

		throw new IllegalStateException("There is no " + color + " King on the board");
	}
	
	private boolean testCheck(Color color) {
		
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOntheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == oppoenent(color)).collect(Collectors.toList());

		for(Piece p :opponentPieces ) {
			boolean[][] mat = p.possibleMoves();
			if(mat[kingPosition.getRow()][kingPosition.getColoumn()]) {
				return true;
			}
		}
		return false;
	}
	
	

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOntheBoard.add(piece);
	}

	private void initialSetup() {

		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));

	}
}
