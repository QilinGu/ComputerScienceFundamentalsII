//Pearson Radu
//250785461
//CS1027B

/**
 * LinkedBinaryTree implements the BinaryTreeADT interface
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/19/08
 */

import java.util.Iterator;

public class LinkedBinaryTree<T> implements BinaryTreeADT<T>
{
	protected int count;
	protected BinaryTreeNode<T> root; 

	/**
	 * Creates an empty binary tree.
	 */
	public LinkedBinaryTree() 
	{
		count = 0;
		root = null;
	}

	/**
	 * Creates a binary tree with the specified element as its root.
	 *
	 * @param element  the element that will become the root of the new binary
	 *                 tree
	 */
	public LinkedBinaryTree (T element) 
	{
		count = 1;
		root = new BinaryTreeNode<T> (element);
	}

	/**
	 * Creates a binary tree with the specified element as its root.
	 *
	 * @param element  the element that will become the root of the new binary
	 *                 tree
	 */
	/**
	 * Constructor creates tree from element as root and two subtrees
	 * as left and right subtrees of root.
	 * @param element		element at root	
	 * @param leftSubtree	left subtree
	 * @param rightSubtree	right subtree
	 */

	public LinkedBinaryTree (T element, LinkedBinaryTree<T> leftSubtree,
			LinkedBinaryTree<T> rightSubtree) 
	{
		root = new BinaryTreeNode<T> (element);
		count = 1;
		if (leftSubtree != null)
		{
			count = count + leftSubtree.size();
			root.left = leftSubtree.root;
		}
		else
			root.left = null;

		if (rightSubtree !=null)
		{
			count = count + rightSubtree.size(); 
			root.right = rightSubtree.root;
		}
		else
			root.right = null;

	}

	/**
	 * Returns a reference to the element at the root
	 *
	 * @return                           a reference to the specified target
	 * @throws EmptyCollectionException  if the tree is empty
	 */
	public T getRoot() throws EmptyCollectionException
	{
		return root.element;
	}

	/**
	 * Returns true if this binary tree is empty and false otherwise.
	 *
	 * @return  true if this binary tree is empty
	 */
	public boolean isEmpty() 
	{
		return count==0; 
	}

	/**
	 * Returns the integer size of this tree.
	 *
	 * @return  the integer size of this tree
	 */
	public int size() 
	{
		return count;
	}

	/**
	 * Returns true if this tree contains an element that matches the
	 * specified target element and false otherwise.
	 *
	 * @param targetElement              the element being sought in this tree
	 * @return                           true if the element in is this tree
	 * @throws ElementNotFoundException  if an element not found exception occurs
	 */
	public boolean contains (T targetElement) 
	{
		return targetElement.equals(find(targetElement));
	}

	/**
	 * Returns a reference to the specified target element if it is
	 * found in this binary tree.  Throws a NoSuchElementException if
	 * the specified target element is not found in the binary tree.
	 *
	 * @param targetElement              the element being sought in this tree
	 * @return                           a reference to the specified target
	 * @throws ElementNotFoundException  if an element not found exception occurs
	 */
	public T find(T targetElement) throws ElementNotFoundException
	{
		BinaryTreeNode<T> current = findAgain( targetElement, root );

		if( current == null )
			throw new ElementNotFoundException("binary tree");

		return (current.element);
	}

	/**
	 * Returns a reference to the specified target element if it is
	 * found in this binary tree.
	 *
	 * @param targetElement  the element being sought in this tree
	 * @param next           the element to begin searching from
	 */
	private BinaryTreeNode<T> findAgain(T targetElement, 
			BinaryTreeNode<T> next)
			{
		if (next == null)
			return null;

		if (next.element.equals(targetElement))
			return next;

		BinaryTreeNode<T> temp = findAgain(targetElement, next.left);

		if (temp == null)
			temp = findAgain(targetElement, next.right);

		return temp;
			}

	/**
	 * Returns a string representation of this binary tree.
	 *
	 * @return  a string representation of this binary tree
	 */
	public String toString() 
	{
		StringBuilder toReturn = new StringBuilder(""); // String toReturn = ""

		Iterator<T> it = this.iteratorInOrder();
		while (it.hasNext()){
			toReturn.append(it.next());  /// += it.next()
		}
		return toReturn.toString(); // return toReturn
	}

	/**
	 * Performs an inorder traversal on this binary tree by calling an
	 * overloaded, recursive inorder method that starts with
	 * the root.
	 *
	 * @return  an in order iterator over this binary tree
	 */
	public Iterator<T> iteratorInOrder()
	{
		ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
		inorder (root, tempList);

		return tempList.iterator();
	}

	/**
	 * Performs a recursive inorder traversal.
	 *
	 * @param node      the node to be used as the root for this traversal
	 * @param tempList  the temporary list for use in this traversal
	 */
	protected void inorder (BinaryTreeNode<T> node, 
			ArrayUnorderedList<T> tempList) 
	{
		if (node != null)
		{
			inorder (node.left, tempList);
			tempList.addToRear(node.element);
			inorder (node.right, tempList);
		}
	}

	/**
	 * Performs an preorder traversal on this binary tree by calling 
	 * an overloaded, recursive preorder method that starts with
	 * the root.
	 *
	 * @return  an pre order iterator over this tree
	 */
	public Iterator<T> iteratorPreOrder() 
	{
		ArrayUnorderedList <T> tempList = new ArrayUnorderedList <T>();
		preorder (root, tempList);
		
		return tempList.iterator();
	}

	/**
	 * Performs a recursive preorder traversal.
	 *
	 * @param node  the node to be used as the root for this traversal
	 * @param tempList  the temporary list for use in this traversal
	 */
	protected void preorder (BinaryTreeNode<T> node, 
			ArrayUnorderedList<T> tempList) 
	{
		if (node != null)
		{
			tempList.addToRear(node.element);
			preorder (node.left, tempList);
			preorder (node.right, tempList);			
		}
	}

	/**
	 * Performs an postorder traversal on this binary tree by calling
	 * an overloaded, recursive postorder method that starts
	 * with the root.
	 *
	 * @return  a post order iterator over this tree
	 */
	public Iterator<T> iteratorPostOrder() 
	{
		ArrayUnorderedList <T> tempList = new ArrayUnorderedList <T>();
		postorder (root, tempList);
		
		return tempList.iterator();
	}

	/**
	 * Performs a recursive postorder traversal.
	 *
	 * @param node      the node to be used as the root for this traversal
	 * @param tempList  the temporary list for use in this traversal
	 */
	protected void postorder (BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) 
	{
		if (node != null)
		{
			postorder (node.left, tempList);
			postorder (node.right, tempList);
			tempList.addToRear(node.element);
		}
	}

	/**
	 * Performs a levelorder traversal on this binary tree, using a
	 * templist.
	 *
	 * @return  a levelorder iterator over this binary tree
	 */
	
	 public Iterator<T> iteratorLevelOrder() 
	   {
		   ArrayUnorderedList <T> nodes = new ArrayUnorderedList <T>();
		   ArrayUnorderedList <T> tempList = new ArrayUnorderedList <T>();
		   BinaryTreeNode<T> current;
		   
		   nodes.addToRear(root.element);
		   
		   while (!nodes.isEmpty())
		   {
			   current = (BinaryTreeNode<T>)nodes.removeFirst();
			   
			   if (current != null)
			   {
				   tempList.addToRear(current.getElement());
				   nodes.addToRear(current.left.element);
				   nodes.addToRear(current.right.element);
			   }
			   else
				   tempList.addToRear(null);
		   }
		   
		   return tempList.iterator();
	   }


	/*************************    Assignment 4    ****************************************/

	/**
	 * Finds the target elements and returns a path from the targetElement to the root. 
	 * It calls a recursive method to do this.
	 * 
	 * @param targetElement
	 * @return Iterator which will iterate over elements on the path from the targetElement 
	 * to the root of the tree (includes both).
	 */
	public Iterator<T> pathToRoot(T targetElement) throws ElementNotFoundException									//method finds any path to the root from a node
	{
		ArrayUnorderedList <T> pathToRoot = new ArrayUnorderedList <T>();											//an unordered list to contain the path
		pathToRootAgain (targetElement, root, pathToRoot);															//instance of the recursive method that finds the node
		
		if (pathToRoot.isEmpty())																					//make sure there actually is a path if not throw exception
			throw new ElementNotFoundException("Binary Tree");
		
		return pathToRoot.iterator();																				//return iterator from pathToRoot that contains the path
	}

	/**
	 * Will add to the pathToRoot ArrayUnorderedList<T> visitor if the node 
	 * is on the path from the targetElement to the root of the tree
	 * 
	 * @param targetElement
	 * @param node
	 * @param pathToRoot
	 */
	protected void pathToRootAgain(T targetElement, BinaryTreeNode<T> node, ArrayUnorderedList<T> pathToRoot)		//recursive method that finds the node, starting from the root
	{
		if (node != null)																							//make sure there is a node there
		{
			if(node.element.equals(targetElement))																	//if the node is the target add it to the list
					pathToRoot.addToRear(node.element);
			else
			{
				pathToRootAgain (targetElement, node.left, pathToRoot);												//search the left side of the tree
				
				if (pathToRoot.isEmpty())																			//if the left side of the tree is empty search the right 
					pathToRootAgain (targetElement, node.right, pathToRoot);
				
				if (!(pathToRoot.isEmpty()))																		//while the path is not empty add the node to the rear
					pathToRoot.addToRear(node.element);
			}
		}
	}

	/**
	 * Finds the lowest (ie. deepest) or maximal-level node common to both the path from 
	 * targetOne to the root and the path from targetTwo to the root.
	 * @param targetOne The first element to find
	 * @param targetTwo The second element to find
	 * @return the element found in the lowest common ancestor node for targetOne and targetTwo
	 * @throws ElementNotFoundException
	 */
	
	public T lowestCommonAncestor( T targetOne, T targetTwo) throws ElementNotFoundException
	{
		Iterator <T> one = pathToRoot (targetOne);																	//iterator for target one
		Iterator <T> two = pathToRoot (targetTwo);																	//iterator for target two
		
		ArrayUnorderedList <T> pathOne = new ArrayUnorderedList <T>();												//unordered list to contain the single path
		T temp = null;																								//temporary variable location
		
		while (one.hasNext())																						//while one has elements add them to the rear of the path and get the next element
			pathOne.addToRear(one.next());
				
		while (two.hasNext())																						//while two has elements store the element in temp
		{
			temp = two.next();
			
			if (pathOne.contains(temp))																				//if the path contains the temporary element
				return temp;																						//return temp
		}
			
		if (temp == null)																							//if temporary is null return the base case
			return root.element;
		else
			return temp;																							//otherwise return temp
	}
}
