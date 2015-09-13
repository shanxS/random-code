public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {10, 5, 20, 1, 9, 15, 18};
        BST bst = new BST();

        for (Integer a : ar)
        {
            bst.insert(a);
        }

        BST.print(bst.getHead());

        DepthFinder df = new DepthFinder();
        df.findDepth(bst.getHead(), 15);

    }
}

class DepthFinder
{
    private boolean done;
    private Integer target;

    public void findDepth(Node head, Integer target)
    {
        done = false;
        this.target = target;
        printFor(head, 0);
    }

    private void printFor(Node node, Integer thisDepth)
    {
        if (done || node == null)
        {
            return;
        }

        if (node.getValue() == target)
        {
            System.out.println(thisDepth);
            done = true;

            return;
        }

        printFor(node.getLeft(), thisDepth + 1);
        printFor(node.getRight(), thisDepth + 1);
    }
}

class BST
{
    private Node head;

    public BST()
    {
        head = null;
    }

    public void insert(Integer v)
    {
        if (head == null)
        {
            head = new Node(v);
        }
        else
        {
            insert(head, v);
        }
    }

    private void insert(Node node, Integer v)
    {
        if (node.getValue() > v)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), v);
            }
            else
            {
                node.setLeft(new Node(v));
            }
        }
        else if (node.getValue() < v)
        {
            if (node.getRight() != null)
            {
                insert(node.getRight(), v);
            }
            else
            {
                node.setRight(new Node(v));
            }
        }
    }

    static public void print(Node node)
    {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " - ");
        if (node.getLeft() != null)
        {
            System.out.print(node.getLeft().getValue());
        }
        System.out.print(", ");
        if (node.getRight() != null)
        {
            System.out.print(node.getRight().getValue());
        }
        System.out.println();

        print(node.getLeft());
        print(node.getRight());
    }

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Node left, right;
    private Integer value;

    public Node(Integer v)
    {
        this.value = v;
        this.left = null;
        this.right = null;
    }

    public Integer getValue()
    {
        return value;
    }

    public Node getLeft()
    {
        return left;
    }

    public void setLeft(Node left)
    {
        this.left = left;
    }

    public Node getRight()
    {
        return right;
    }

    public void setRight(Node right)
    {
        this.right = right;
    }
}