public class MainClass {

	public static void main(String[] args) {
		 RedBlackTree tree = new RedBlackTree();
		 	tree.insertion(50); 
		    tree.insertion(70);
		    tree.insertion(60);
		    tree.insertion(20);
		    tree.insertion(65);
		    tree.inOrder();
		    tree.Delete(60);
		    System.out.println("after 70");
		    tree.inOrder();
		    tree.Delete(20);
		    System.out.println("after 20");
		    

		    tree.inOrder();
		   
	}

}