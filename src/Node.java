
public class Node {

public int data;
public Boolean color; 		// 1 for red , 0 for black
public Node left;
public Node right;
public Node parent;
public int key;


Node(){}

Node(int item)  
{ 
    data = item; 
    left = null;
    right = null;
    parent =  null; 
    color  = true;   
}

public String getData()
{
	String sdata =Integer.toString(data);
	return sdata;
}











}
