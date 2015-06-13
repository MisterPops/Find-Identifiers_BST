
public class BinarySearchTree{
	
	private static class BinaryNode {
		
		identifier element;	//The data in the node.
		BinaryNode left;	//Left child.
		BinaryNode right;	//Right child.
		
		BinaryNode(identifier theElement, BinaryNode lt, BinaryNode rt) {
			element = theElement;
			left = lt;
			right = rt;	
		}
	}
	
	private BinaryNode root;	//Holds initial tree root.
	
	/**
	 * Default constructor creates an empty tree.
	 */
	public BinarySearchTree() {
		root = null;
	}
	
	/**
	 * Makes the root null, rendering the bst empty.
	 */
	public void makeEmpty() {
		root = null;
	}
	
	/**
	 * Checks if the bst is empty.
	 * @return true if root is null.
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
	 * Checks if toCheck is in the bst.
	 * @param toCheck the node to be checked.
	 * @return true if toCheck is in the bst, false otherwise.
	 */
	public boolean contains(identifier toCheck) {
		return contains(toCheck, root);
	}
	
	/**
	 * To find the smallest node.
	 * @return the smallest node.
	 * @throws Exception throws exception if the tree is empty.
	 */
	public identifier findMin() throws Exception {
		if(isEmpty()) throw new Exception();
		return findMin(root).element;
	}
	
	/**
	 * To find the largest node.
	 * @return the largest node.
	 * @throws Exception throws exception if the tree is empty.
	 */
	public identifier findMax() throws Exception {
		if(isEmpty()) throw new Exception();
		return findMax(root).element;
	}
	
	/**
	 * To insert identifier into bst.
	 * @param toInsert the identifier to be inserted.
	 */
	public void insert(identifier toInsert) {
		root = insert(toInsert,root);
	}
	
	/**
	 * To remove given identifier from the bst.
	 * @param toRemove
	 */
	public void remove(identifier toRemove) {
		root = remove(toRemove, root);
	}
	
	/**
	 * Print the tree contents in sorted order.
	 */
	public void printTree() {
		if(isEmpty())
			System.out.println("Empty Tree");
		else printTree(root);
	}
	
	/*Private Methods*/
	
	/**
	 * Internal method to find an item in a subtree.
	 * @param toCheck the item to search for.
	 * @param node the node that roots the subtree.
	 * @return true if the item is found; false otherwise.
	 */
	private boolean contains(identifier toCheck, BinaryNode node) {
		if(node == null)
			return false;
		
		int compareResult = toCheck.getIdent().toLowerCase().compareTo(node.element.getIdent().toLowerCase());
		
		if(compareResult < 0)
			return contains(toCheck, node.left);
		else if(compareResult > 0)
			return contains(toCheck, node.right);
		else
			return true;	//Match!
	}
	
	/**
	 * INternal method to find the smallest item in a subtree.
	 * @param node the node that roots the subtree.
	 * @return node containing the smallest item.
	 */
	private BinaryNode findMin(BinaryNode node) {
		if(node == null)
			return null;
		else if(node.left == null)
			return node;
		return findMin(node.left);
	}
	
	/**
	 * Internal method to find the largest item in a subtree.
	 * @param node the node that roots the subtree.
	 * @return node containing the largest item.
	 */
	private BinaryNode findMax(BinaryNode node) {
		if(node != null)
			while(node.right != null)
				node = node.right;
		
		return node;
	}
	
	/**
	 * Internal method to insert into a subtree.
	 * @param toInsert the item to insert.
	 * @param node the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private BinaryNode insert(identifier toInsert, BinaryNode node) {
		if(node == null)
			return new BinaryNode(toInsert, null, null);
			
		int compareResult = toInsert.getIdent().toLowerCase().compareTo(node.element.getIdent().toLowerCase());
		
		if(compareResult < 0)
			node.left = insert(toInsert, node.left);
		else if(compareResult > 0)
			node.right = insert(toInsert, node.right);
		else
			node.element.addLineNum(toInsert.getRecentLn());
		return node;
	}
	
	/**
	 * Internal method to remove from a subtree.
	 * @param toRemove the item to remove.
	 * @param node the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private BinaryNode remove(identifier toRemove, BinaryNode node) {
		if(node == null)
			return node;	//Item not found, do nothing
		
		int compareResult = toRemove.getIdent().toLowerCase().compareTo(node.element.getIdent().toLowerCase());
		
		if(compareResult < 0)
			node.left = remove(toRemove, node.left);
		else if(compareResult > 0)
			node.right = remove(toRemove, node.right);
		else if(node.left != null && node.right != null) {	//Two children.
			node.element = findMin(node.right).element;
			node.right = remove(node.element, node.right);
		}
		else
			node = (node.left != null) ? node.left : node.right;
		
		return node;
	}
	
	/**
	 * Internal method to print a subtree in sorted order
	 * @param node the node that roots the subtree
	 */
	private void printTree(BinaryNode node) {
		if(node != null) {
			printTree(node.left);
			System.out.print(node.element.getIdent() + "; Appears on lines: ");
			node.element.printArrayList();
			System.out.println();
			printTree(node.right);
		}
	}
	
}
