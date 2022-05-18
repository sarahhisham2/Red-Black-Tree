public class RedBlackTree {

	private Node root;
	public Node TNULL;
	
	RedBlackTree()
	{
		root = null;
	}
	
	public void insertion(int data)
    {
        Node node= new Node(data);
        this.root = insertionHandling(root, node);
        Node parent= new Node();
        parent = null;
        Node grandparent=new Node();
        grandparent = null ;
          while ( (node!=null) &&(node!=root) &&(node.parent!=null)&&(node.parent.color==true) && (node.color!= false))
        {
            parent=node.parent;
            grandparent=(node.parent).parent;
            if(parent==grandparent.left)
            {
            	Node uncle = new Node();
            	uncle = grandparent.right;
                //case 1
                if( (uncle != null) &&(uncle.color == true))
                {
                    grandparent.color=true;
                    parent.color=false;
                    uncle.color=false;
                    node=grandparent;
                }

                else
                {
                    //case 2
                    if(node==parent.right)
                    {
                        leftRotate(parent);
                        node=parent;
                        parent=node.parent;
                    }
                    
                    //case 3
                    rightRotate(grandparent);
                 
                    
                    boolean temp = parent.color;
                    parent.color = grandparent.color;
                    grandparent.color = temp;
                    node=parent;
                }
            }
            else
            {
                Node  uncle=grandparent.left;
                //case 1
                if((uncle !=null) && (uncle.color))
                {

                    grandparent.color=true;
                    parent.color=false;
                    uncle.color=false;
                    node=grandparent;
                }
                else
                {
                    //case 2
                    if(node== parent.left)
                    {
                        rightRotate(parent);
                        node=parent;
                        parent=node.parent;
                    }

                    //case 3
                    leftRotate(grandparent);
                    boolean temp = parent.color;
                    parent.color = grandparent.color;
                    grandparent.color = temp;            
                    node=parent;

                }


            }
        }
        root.color=false;
    }
	
	public Node insertionHandling(Node root, Node newNode)
    {

        if (root == null)
            return newNode;

        if (newNode.data < root.data)
        {
            root.left  = insertionHandling(root.left, newNode);
            root.left.parent = root;
        }
        else if (newNode.data > root.data)
        {
            root.right = insertionHandling(root.right, newNode);
            root.right.parent = root;
        }
        return root;

    }
    
   public  void leftRotate(Node p)
    {
        if(p.right==null)
        {
            return ;
        }
        else
        {
            Node y=p.right;
            if(y.left!=null)
            {
                p.right=y.left;
                y.left.parent=p;
            }
            else
            {
                p.right=null;
            }
            if(p.parent!=null)
            {
                y.parent=p.parent;
            }
            if(p.parent==null)
            {
                root=y;
            }
            else
            {
                if(p==p.parent.left)
                {
                    p.parent.left=y;
                }
                else
                {
                    p.parent.right=y;
                }
            }
            y.left=p;
            p.parent=y;
        }
    }
    
   private void fixDelete(Node x) 
   {
		Node s;
		while (x != root && x.color == false) 
		{
			if (x == x.parent.left) {
				s = x.parent.right;
				if (s.color == true) {
					// case 3.1
					s.color = false;
					x.parent.color = true;
					leftRotate(x.parent);
					s = x.parent.right;
				}

				if (s.left.color == false && s.right.color == false) 
				{
					// case 3.2
					s.color = true;
					x = x.parent;
				} 
				else 
				{
					if (s.right.color == false)
					{
						// case 3.3
						s.left.color = false;
						s.color = true;
						rightRotate(s);
						s = x.parent.right;
					} 

					// case 3.4
					s.color = x.parent.color;
					x.parent.color = false;
					s.right.color = false;
					leftRotate(x.parent);
					x = root;
				}
			}
			else 
			{
				s = x.parent.left;
				if (s.color == true) {
					// case 3.1
					s.color = false;
					x.parent.color = true;
					rightRotate(x.parent);
					s = x.parent.left;
				}

				if (s.right.color == false && s.right.color == false)
				{
					// case 3.2
					s.color = true;
					x = x.parent;
				} 
				else 
				{
					if (s.left.color == false)
					{
						// case 3.3
						s.right.color = false;
						s.color = true;
						leftRotate(s);
						s = x.parent.left;
					} 

					// case 3.4
					s.color = x.parent.color;
					x.parent.color = false;
					s.left.color = false;
					rightRotate(x.parent);
					x = root;
				}
			} 
		}
		x.color = false;
	}
   
    void rightRotate(Node p)
    {
        if(p.left==null)
        {
            return ;
        }
        else
        {
            Node y=p.left;
            if(y.right!=null)
            {
                p.left=y.right;
                y.right.parent=p;
            }
            else
            {
                p.left=null;
            }
            if(p.parent!=null)
            {
                y.parent=p.parent;
            }
            if(p.parent==null)
            {
                root=y;
            }
            else
            {
                if(p==p.parent.left)
                {
                    p.parent.left=y;
                }
                else
                {
                     p.parent.right=y;
                }
            }
            y.right=p;
            p.parent=y;
        }
    }
    
    public void rbTransplant(Node u, Node v){
		if (u.parent == null) {
			root = v;
		} else if (u == u.parent.left){
			u.parent.left = v;
		} else {
			u.parent.right = v;
		}
		
	}
    public Node minimum(Node node) {
		while (node.left != TNULL) {
			node = node.left;
		}
		return node;
	}

    public void deleteNodeHelper(Node node, int key) {
		// find the node containing key
		Node z = TNULL;
		Node x, y;
		while (node != TNULL){
			if (node.data == key) {
				z = node;
			}

			if (node.data <= key) {
				node = node.right;
			} else {
				node = node.left;
			}
		}

		if (z == TNULL) {
			System.out.println("Couldn't find key in the tree");
			return;
		} 

		y = z;
		boolean yOriginalColor = y.color;
		if (z.left == TNULL) {
			x = z.right;
			rbTransplant(z, z.right);
		} else if (z.right == TNULL) {
			x = z.left;
			rbTransplant(z, z.left);
		} else {
			y = minimum(z.right);
			yOriginalColor = y.color;
			x = y.right;
			if (y.parent == z) {
				x.parent = y;
			} else {
				rbTransplant(y, y.right);
				y.right = z.right;
				y.right.parent = y;
			}

			rbTransplant(z, y);
			y.left = z.left;
			y.left.parent = y;
			y.color = z.color;
		}
		if (yOriginalColor == false){
			fixDelete(x);
		}
	}
    public void Delete(int data) {
		deleteNodeHelper(this.root, data);
	}


private void inOrder(Node r)
{
    if (r != null)
    {
        inOrder(r.left);
        char c = 'B';
        if (r.color == false)
            c = 'R';
        System.out.print(r.data +""+c+" ");
        inOrder(r.right);
    }
}
public void  inOrder()
{
	 inOrder(root);
}

public Node getRoot() {
	return root;
}
public void setRoot(Node root) {
	this.root = root;
}

public boolean isEmpty() {
	return root == null;
}
public int getDepth() {
    return this.getDepth(this.root);
}
private int getDepth(Node node) {
    if (node != null) {
        int right_depth;
        int left_depth = this.getDepth(node.left);
        return left_depth > (right_depth = this.getDepth(node.right)) ? left_depth + 1 : right_depth + 1;
    }
    return 0;
}
public void clearTree(){
	root=null;
}

public boolean contains(int  data) {
	return contains(root, data);
}
private boolean contains(Node root, int data) {
	if (root == null)
		return false;

	if (root.data > data )
		return contains(root.left, data);
	else if (root.data < data)
		return contains(root.right, data);
	else
		return true;

}
}