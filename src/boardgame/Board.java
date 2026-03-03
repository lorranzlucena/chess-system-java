package boardgame;

public class Board {

	
	private int rows;
	private int columns;
	private Piece [][] pieces;//matriz de peças

	
	public Board(int rows, int columns) {
		if(rows < 1 && columns < 1) {
			throw new BoardException("Error creating board : there must be at last 1 row and 1 column");
			
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows] [columns];
	
	}


	public int getRows() {
		return rows;
	}


	public int getColumns() {
		return columns;
	}


	 //Recebe linha e coluna como números inteiros e retorna a peça que está nessa posição.
	public Piece piece(int row, int column) {
		if(!positionExists(row,column)) {
			throw new BoardException("position not on the board");
		}
		return pieces[row][column];
		
	}
	
	//Recebe um objeto do tipo Position e retorna a peça nessa posição.
	public Piece piece (Position position) {
		if(!positionExists(position)) {
			throw new BoardException("position not on the board");
		}
		
		
		return pieces[position.getRow()][position.getColoumn()];
	}
	
	
	public void placePiece(Piece piece, Position position) {
		if(thereIsAPiece(position)) {
			throw new BoardException("thre is already a piece on position "+position);
		}
		
		pieces[position.getRow()][position.getColoumn()] = piece;
		piece.position = position;
	}
	
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >=0 && column < columns;
	}
	
	public boolean  positionExists(Position position){
		return positionExists(position.getRow(), position.getColoumn());

	}

	public boolean thereIsAPiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("position not on the board");
		}
		return piece(position) != null;
	}
	
}

