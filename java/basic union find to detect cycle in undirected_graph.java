// basic union find to detect cycle in undirected graph

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] er)
    {
        int[][] ar = {
                {0,1,1},
                {1,0,1},
                {1,1,0}
        };

        (new UnionFind()).findLoop(ar);
    }
}

class UnionFind
{
    private Map<Integer, Node> nameMap;
    private int[][] adjMatrix;

    public void findLoop(int[][] adjMatrix)
    {
        this.adjMatrix = adjMatrix;

        createNodes();

        int nodeCount = adjMatrix.length;
        for (int from = 0; from < nodeCount; ++from)
        {
            for (int to=from; to<nodeCount;++to)
            {
                if (adjMatrix[from][to] == 1)
                {
                    if (isLoop(from, to))
                    {
                        System.out.print("loop found in " + from + " " + to);
                        return;
                    }

                    union(from, to);
                }
            }
        }
    }

    private void union(int from, int to)
    {
        Node head1 = getHead(from);
        Node head2 = getHead(to);

        if (head1.nodeCount() < head2.nodeCount())
        {
            head2.setParent(head1);
            head1.setNodeCount(head1.nodeCount() + head2.nodeCount());
        }
        else
        {
            head1.setParent(head2);
            head2.setNodeCount(head1.nodeCount() + head2.nodeCount());
        }
    }

    private Node getHead(int name)
    {
        Node cursor = nameMap.get(name);

        while (cursor.getParent() != null)
        {
            cursor = cursor.getParent();
        }

        return cursor;
    }

    private boolean isLoop(int from, int to)
    {
        Node fromHead = getHead(from);
        Node toHead = getHead(to);

        if (fromHead.getName() == toHead.getName())
        {
            return true;
        }

        return false;
    }

    private void createNodes()
    {
        int count = adjMatrix.length;
        nameMap = new HashMap<Integer, Node>();
        while (count >= 0)
        {
            Node node = new Node(count);
            nameMap.put(count, node);

            --count;
        }
    }
}

class Node
{
    private Integer name, nodeCount;
    private Node parent;

    public Node(Integer name)
    {
        this.name = name;
        nodeCount = 1;
    }

    public Integer getName()
    {
        return name;
    }

    public Integer nodeCount()
    {
        return nodeCount;
    }

    public void setNodeCount(Integer nodeCount)
    {
        this.nodeCount = nodeCount;
    }

    public Node getParent()
    {
        return parent;
    }

    public void setParent(Node parent)
    {
        this.parent = parent;
    }
}