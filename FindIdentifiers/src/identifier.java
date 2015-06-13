import java.util.ArrayList;


public class identifier {
	
	private String ident;											//String that identity is.
	private int recentLn;											//Line number this identifier was found on.
	private ArrayList<Integer> lineNums = new ArrayList<Integer>();	//Line numbers the identity can be found on.
	
	/**
	 * Class for identifier object.
	 * @param ident the identifier String.
	 * @param ln the recent line number the identifier was found on.
	 */
	public identifier(String ident, int ln) {
		this.ident = ident;
		lineNums.add(ln);
		recentLn = ln;
	}

	/**
	 * Gives the identifier name.
	 * @return String of identifiers name.
	 */
	public String getIdent() {
		return ident;
	}
	
	/**
	 * Gives the line number this identifier object was most recently found on.
	 * @return int of most recent line number it was found on.
	 */
	public int getRecentLn() {
		return recentLn;
	}
	
	/**
	 * Adds a line number this identifier can be found on to line number array.
	 * @param n line number identifier was found on.
	 */
	public void addLineNum(int n) {
		lineNums.add(n);
	}
	
	/**
	 * Prints a list of the line numbers the identifier can be found on.
	 */
	public void printArrayList() {
		for(int i = 0; i < lineNums.size(); i++) {
			System.out.print(lineNums.get(i) + ", ");
		}
	}
}
