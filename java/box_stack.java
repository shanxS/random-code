// box stack problem

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    private static final int AREA = 1, HT = 0;
    public static void main(String[] er)
    {
        List<Box> boxes = getBoxes();
        List<Box> perm = permuteBoxes(boxes);
        Collections.sort(perm);

        (new LIS()).find(perm);
    }

    private static List<Box> permuteBoxes(List<Box> boxes)
    {
        List<Box> perm = new ArrayList<>();

        for(Box box : boxes)
        {
            perm.add(new Box(box.l, box.b, box.h));
            perm.add(new Box(box.h, box.l, box.b));
            perm.add(new Box(box.b, box.h, box.l));
        }

        return perm;
    }

    public static List<Box> getBoxes()
    {
        List<Box> boxes = new ArrayList<>();

        boxes.add(new Box(4, 6, 7));
        boxes.add(new Box(1, 2, 3));
        boxes.add(new Box(4, 5, 6));
        boxes.add(new Box(10, 12, 32));

        return boxes;
    }
}

class LIS
{
    private int[] dp;
    private final int AREA = 1, HT = 0;
    public void find(List<Box> boxes)
    {
        dp = new int[boxes.size()];
        for(int i=0; i<boxes.size(); ++i)
        {
            dp[i] = boxes.get(i).h;
        }

        Integer max = Integer.MIN_VALUE;
        for (int i=0; i<dp.length; ++i)
        {
            for (int j=0; j<i; ++j)
            {
                if(boxes.get(j).b > boxes.get(i).b
                        && boxes.get(j).l > boxes.get(i).l
                        && (dp[j] + boxes.get(i).h) > dp[i])
                {
                    dp[i] = dp[j] + boxes.get(i).h;
                    max = Math.max(max, dp[i]);
                }
            }
        }

        System.out.print(max);
    }
}

class Box implements Comparable
{
    public int l, b, h;

    public Box(int l, int b, int h)
    {
        this.b = b;
        this.l = l;
        this.h = h;
    }


    @Override
    public int compareTo(Object o)
    {
        Box otherBox = (Box) o;

        int otherArea = otherBox.area();
        int thisArea = area();

        if (otherArea > thisArea)
        {
            return 1;
        }
        else if (otherArea < thisArea)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }

    public int area()
    {
        return b * l;
    }
}