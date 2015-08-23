// segment tree

public class Main
{
    public static void main(String[] er)
    {
        int[] ar = {1,3,5,7,9,11};
        SegmentTreeUtil util = new SegmentTreeUtil();
        Node tree = util.makeFor(ar);
        util.print(tree);
    }
}

class SegmentTreeUtil
{
    public Node makeFor(int[] ar)
    {
        return makeFor(ar, 0, ar.length-1);
    }

    private Node makeFor(int[] ar, int start, int end)
    {
        if (start == end)
        {
            return new Node(start, end, ar[start]);
        }

        Node thisNode = new Node(start, end, sumFor(ar, start, end));

        int mid = start + ((end-start)/2);
        thisNode.setLeft(makeFor(ar, start, mid));
        thisNode.setRight(makeFor(ar, mid+1, end));

        return thisNode;
    }

    private int sumFor(int[] ar, int start, int end)
    {
        int sum = 0;

        for (int i=start; i<=end; ++i)
        {
            sum += ar[i];
        }

        return sum;
    }

    public void print(Node node)
    {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " (" + node.getStart() + "," + node.getEnd() + ") -  ");
        if (node.getLeft() != null)
        {
            System.out.print(node.getLeft().getValue());
        }
        System.out.print(" | ");
        if (node.getRight() != null)
        {
            System.out.print(node.getRight().getValue());
        }
        System.out.println();

        print(node.getLeft());
        print(node.getRight());
    }
}

class Node
{
    final private int start, end, value;
    Node left, right;

    public Node(int start, int end, int value)
    {
        this.start = start;
        this.end = end;
        this.value = value;
    }

    public void setLeft(Node left)
    {
        this.left = left;
    }

    public void setRight(Node right)
    {
        this.right = right;
    }

    public Node getLeft()
    {
        return left;
    }

    public Node getRight()
    {
        return right;
    }

    public int getStart()
    {
        return start;
    }

    public int getEnd()
    {
        return end;
    }

    public int getValue()
    {
        return value;
    }
}
