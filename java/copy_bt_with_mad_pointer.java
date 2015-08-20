// copy bt with mad pointer
import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {10,20,30,40,null,50,null,
        null, null, null, null, null, 60};

        BTUtil util = new BTUtil();

        Tree tree = util.makeFor(ar);
        Map<Integer, Integer> fromTo = new HashMap<>();
        fromTo.put(40,20);
        fromTo.put(50, 40);
        fromTo.put(30, 10);

        util.connectMad(tree, fromTo);

        util.print(tree.head);
        System.out.print(tree.head);

        System.out.println("----------");
        BTCopier btc = new BTCopier();
        Node copy = btc.copy(tree.head);
        util.print(copy);
        System.out.print(copy);
    }
}

class BTCopier
{
    public Node copy(Node head)
    {
        copyNodes(head);
        connectMad(head);

        Node copyHead = head.getLeft();
        ripTrees(head, copyHead);

        return copyHead;
    }

    private void ripTrees(Node orig, Node copy)
    {
        if (orig == null)
        {
            return;
        }

        orig.setLeft(copy.getLeft());

        if (orig.getRight() != null)
        {
            copy.setRight(orig.getRight().getLeft());
            ripTrees(orig.getRight(), copy.getRight());
        }

        if (copy.getLeft() != null)
        {
            Node origLeft = copy.getLeft();
            copy.setLeft(copy.getLeft().getLeft());
            ripTrees(origLeft, copy.getLeft());
        }

    }

    private void connectMad(Node node)
    {
        if (node == null)
        {
            return;
        }

        Node copyNode = node.getLeft();
        if (node.getMad() != null)
        {
            copyNode.setMad(node.getMad().getLeft());
        }

        connectMad(copyNode.getLeft());
        connectMad(node.getRight());
    }

    private void copyNodes(Node node)
    {
        if (node == null)
        {
            return;
        }

        Node copyNode = new Node(node.getValue());
        copyNode.setLeft(node.getLeft());
        node.setLeft(copyNode);

        copyNodes(copyNode.getLeft());
        copyNodes(node.getRight());
    }
}

class BTUtil
{
    public void connectMad (Tree tree, Map<Integer, Integer> fromTo)
    {
        for(Map.Entry<Integer, Integer> entry : fromTo.entrySet())
        {
            Node from = tree.nodeMap.get(entry.getKey());
            Node to = tree.nodeMap.get(entry.getValue());

            from.setMad(to);
        }
    }

    public Tree makeFor(Integer[] ar)
    {
        Map<Integer, Node> nodeMap = new HashMap<>();

        Node[] nodes = new Node[ar.length];
        nodes[0] = new Node(ar[0]);

        nodeMap.put(ar[0], nodes[0]);

        for (int i=1; i<ar.length; ++i)
        {
            if (ar[i] == null)
            {
                continue;
            }

            Node parent = nodes[getParentIndex(i)];
            if (i%2 == 0)
            {
                parent.setRight(new Node(ar[i]));
                nodes[i] = parent.getRight();
            }
            else
            {
                parent.setLeft(new Node(ar[i]));
                nodes[i] = parent.getLeft();
            }

            nodeMap.put(ar[i], nodes[i]);
        }

        return new Tree(nodes[0], nodeMap);
    }

    public void print(Node node)
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
        System.out.print(" | ");
        if (node.getMad() != null)
        {
            System.out.print(node.getMad().getValue());
        }
        System.out.println();

        print(node.getLeft());
        print(node.getRight());
    }

    private int getParentIndex(int i)
    {
        return (i-1)/2;
    }
}

class Tree
{
    Node head;
    Map<Integer, Node> nodeMap;

    public Tree(Node head, Map<Integer, Node> nodeMap)
    {
        this.head = head;
        this.nodeMap = nodeMap;
    }
}

class Node
{
    private Node left, right, mad;
    private Integer value;

    public Node(int v)
    {
        value = v;
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

    public Node getMad()
    {
        return mad;
    }

    public void setMad(Node mad)
    {
        this.mad = mad;
    }
}