package es.ucm.fdi.tp.practica5.utils;

public class Utils {
	
	/*Es una gilipollez, si, pero me quedo m�s tranquilo*/
	public static boolean InfiniteDistanceExceeded(int oldRow, int oldColumn, int row, int column) {
		return Math.max(Math.abs(oldRow - row), Math.abs(oldColumn - column)) > 2;
	}
}
