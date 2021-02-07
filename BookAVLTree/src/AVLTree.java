import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

// Java program for insertion in AVL Tree
class Node {
	String key; 
	Book book;
	int height;
	Node left, right;

	Node(Book d) {
		key = d.getISBN();
		book = d;
		height = 0;
		left = null;
		right = null;
	}
}

class AVLTree {

	Node root;

	// A utility function to get the height of the tree
	int height(Node N) {
		if (N == null)
			return -1;
		return N.height;
	}

	// A utility function to get maximum of two integers
	int max(int a, int b) {
		return (a > b) ? a : b;
	}

	// A utility function to right rotate subtree rooted with y
	// See the diagram given above.
	Node rightRotate(Node y) {
		Node x = y.left;
		Node T2 = x.right;

		// Perform rotation
		x.right = y;
		y.left = T2;

		// Update heights
		y.height = max(height(y.left), height(y.right)) + 1;
		x.height = max(height(x.left), height(x.right)) + 1;

		// Return new root
		return x;
	}

	// A utility function to left rotate subtree rooted with x
	// See the diagram given above.
	Node leftRotate(Node x) {
		Node y = x.right;
		x.right = y.left;
		y.left = x;

		// Update heights
		x.height = max(height(x.left), height(x.right)) + 1;
		y.height = max(height(y.left), height(y.right)) + 1;

		// Return new root
		return y;
	}

	// Get Balance factor of node N
	int getBalance(Node N) {
		if (N == null)
			return 0;

		return height(N.left) - height(N.right);
	}

	Node insert(Node node, Node insert) {

		/* 1. Perform the normal BST insertion */
		if (node == null) {
			node = new Node(book);
			return node;
		}

		else if (insert.key.compareTo(node.key) < 0) {
			node.left = insert(node.left, insert);
		}
		else if (insert.key.compareTo(node.key) > 0) {
			node.right = insert(node.right, insert);
		}
		else if (insert.key.compareTo(node.key) == 0)// Duplicate keys not allowed
			return node;

		
		/* 2. Update height of this ancestor node */
		node.height = 1 + max(height(node.left),
							height(node.right));

		/* 3. Get the balance factor of this ancestor
			node to check whether this node became
			unbalanced */
		int balance = getBalance(node);

		// If this node becomes unbalanced, then there
		// are 4 cases Left Left Case
		if (balance > 1 && insert.key.compareTo(node.left.key) < 0) {
			System.out.println("Imbalance occured at inserting at ISBN " + insert.key + "; fixed in LeftLeft Rotation");
			return rightRotate(node);
		}

		// Right Right Case
		if (balance < -1 && insert.key.compareTo(node.right.key) > 0) {
			System.out.println("Imbalance occured at inserting at ISBN " + insert.key + "; fixed in RightRight Rotation");
			return leftRotate(node);
		}

		// Left Right Case
		if (balance > 1 && insert.key.compareTo(node.left.key) > 0) {
			System.out.println("Imbalance occured at inserting at ISBN " + insert.key + "; fixed in LeftRight Rotation");
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		// Right Left Case
		if (balance < -1 && insert.key.compareTo(node.right.key) < 0) {
			System.out.println("Imbalance occured at inserting at ISBN " + insert.key + "; fixed in RightLeft Rotation");
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		/* return the (unchanged) node pointer */
		return node;
	}

	// A utility function to print inorder traversal
	// of the tree.
	// The function also prints height of every node
	void inOrder(Node node) {
		if (node != null) {
			inOrder(node.left);
			System.out.println(node.key + " ");
			inOrder(node.right);
		}
	}
	
	static Scanner scan;
	static Book book;
	static int index = 0;
	public static void readFile(AVLTree tree) {
		File file = new File("input.txt");
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(scan.hasNextLine()) {
			Scanner scan2 = new Scanner(scan.nextLine());
			while(scan2.hasNext()) {
				String word = scan2.next();
				String word2 = scan2.next();
				String word3 = scan2.next();
				book = new Book(word, word2, word3);
				Node node = new Node(book);
				tree.root = tree.insert(tree.root, node);
//				System.out.println(word + " " + word2 + " " + word3);
			}
		}
	}

	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		readFile(tree);

		System.out.println("Inorder traversal of constructed tree is : ");
		tree.inOrder(tree.root);
	}
}
