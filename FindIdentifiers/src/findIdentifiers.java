import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class findIdentifiers {

	/**
	 * Check if the given string is a restricted word in Java.
	 * @param string the string to be tested.
	 * @return true if the string is a restricted word in Java, false otherwise.
	 */
	private static boolean isReserved(String string) {
		String wordCheck = string.toLowerCase();

		switch(wordCheck) {
		case("abstract"): case("assert"): case("boolean"): case("break"): case("byte"): case("case"):
		case("catch"): case("char"): case("class"): case("const"): case("continue"): case("default"):
		case("do"): case("double"): case("else"): case("enum"): case("extends"): case("final"):
		case("finally"): case("float"): case("for"): case("goto"): case("if"): case("implements"):
		case("import"): case("instanceof"): case("int"): case("interface"): case("long"): case("native"): 
		case("new"): case("package"): case("private"): case("protected"): case("public"): case("return"):
		case("short"): case("static"): case("strictfp"): case("super"): case("switch"): case("synchronized"):
		case("this"): case("throw"): case("throws"): case("transient"): case("try"): case("void"):
		case("volatile"): case("while"): case("false"): case("null"): case("true"):
			return true;
		default:
			return false;
		}
	}

	public static void main(String[] args) throws FileNotFoundException  {
		int lineNum = 1;									//Current line number on file.
		Scanner scan;										//File to be looked over.
		boolean string = false;								//Flag for string.
		boolean comment = false;							//Flag for multi line comments.
		BinarySearchTree idents = new BinarySearchTree();	//Holds identities found.

		//Load text file to be checked.
		//If no file is entered use default file ./findIdentifiers.java
		if(args.length == 0) {
			scan = new Scanner(new File("./findIdentifiers.java"));
		} else {
			scan = new Scanner(new File(args[0]));
		}

		//Check each line of the given file.
		while(scan.hasNextLine()) {
			String stringLine = scan.nextLine();
			String[] split = stringLine.split("[^A-Za-z0-9*\"\'_//\\\\]+");

			for(String identifier:split){
				//Remove empty strings.
				if(identifier.equals(""))
					continue;

				//Check for strings.
				if(identifier.startsWith("\"") && identifier.endsWith("\"") && identifier.length() > 1)
					continue;
				else {
					if(string && identifier.endsWith("\"")) {
						string = !string;
						continue;
					}

					if(!string && identifier.startsWith("\"")) {
						string = !string;
						continue;
					}
				}
				
				//Check for chars.
				if(identifier.startsWith("\'") && identifier.endsWith("\'") && identifier.length() > 1)
					continue;
				else {
					if(string && identifier.endsWith("\'")) {
						string = !string;
						continue;
					}

					if(!string && identifier.startsWith("\'")) {
						string = !string;
						continue;
					}
				}

				//Check for comments.
				if(comment && identifier.endsWith("*/")) {
					comment = !comment;
					continue;
				}

				if(!comment && identifier.startsWith("/*")) {
					comment = !comment;
					continue;
				}

				//Break to next line if comment and continue if string or starts with a number(invalid).
				if(identifier.startsWith("//"))
					break;
				else if(string || comment || identifier.substring(0, 1).matches("[0-9]")) {
					continue;
				}

				//Check if remaining Strings are not reserved by Java.
				if(!isReserved(identifier)) {
					idents.insert(new identifier(identifier, lineNum));
				}
			}

			lineNum++;
		}

		//Print identities and line numbers in order.
		idents.printTree();
		scan.close();
	}
}