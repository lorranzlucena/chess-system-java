package boardgame;

public class Board {

	
	private int rows;
	private int columns;
	private Piece [][] pieces;//matriz de peças

	
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows] [columns];
	
	}


	public int getRows() {
		return rows;
	}


	public void setRows(int rows) {
		this.rows = rows;
	}


	public int getColumns() {
		return columns;
	}


	public void setColumns(int columns) {
		this.columns = columns;
	}

	 //Recebe linha e coluna como números inteiros e retorna a peça que está nessa posição.
	public Piece piece(int row, int column) {
		return pieces[row][column];
		
	}
	
	//Recebe um objeto do tipo Position e retorna a peça nessa posição.
	public Piece piece (Position position) {
		return pieces[position.getRow()][position.getColoumn()];
	}
	
	
}
