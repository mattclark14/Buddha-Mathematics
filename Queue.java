public class Queue
{
  public Node front;
  public Node rear;
  
  class Node
  {
    int info;
    int info2;
    Node link;
    
    Node(int i, int j, Node n)
    {
      info = i;
      info2 = j;
      link = n;
    }
  }
  
  public void insertNext(int item, int item2)
  {
    Node temp = new Node(item, item2, null);
    if(rear == null)
    {
      front = rear = temp;
    }
    else
    {
      rear = rear.link = temp;
    }
  }
  
  public void delete()
  {
    //Node current = front;
    //Node previous = null;
    front = front.link;
  }     
}
