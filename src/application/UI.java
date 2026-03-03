package application;

import chess.ChessPiece;


/**
 * Essa classe é responsável por exibir o tabuleiro no terminal!
 */
public class UI {

	public static void printBoard(ChessPiece[][] pieces) {
/*   │       │      │
/    │       │      └── não retorna nada
/    │       └── pertence à classe, não ao objeto
/    └── qualquer classe pode chamar
*/
		for(int i=0; i<pieces.length; i++) {
			System.out.print((8-i) + " ");
			for(int j=0; j<pieces.length; j++) {
				//2. Percorre cada coluna e chama o método que imprime a peça
				printPiece(pieces[i][j]);
			}
			//3. Após cada linha pula para a próxima
				System.out.println();
		}
			System.out.println("  a b c d e f g h");
	}
	
	private static void printPiece(ChessPiece piece) {
		if(piece == null) {
			System.out.print("-");
		}else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}
	
}
