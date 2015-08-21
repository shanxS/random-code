// repeated number at k distance

import java.util.HashSet;
import java.util.Set;

public class Main
{
    public static void main(String[] er)
    {
        int[] ar = {1, 2, 3, 4, 1, 2, 3, 4};

        (new DistanceDuplicat()).findInDistance(ar, 4);
    }
}

class DistanceDuplicat
{
    public void findInDistance(int[] ar, int k)
    {
        Set<Integer> elem = new HashSet<>();

        int cursor = 0;
        for(int i=1; i<=k && i<ar.length; ++i)
        {
            elem.add(ar[i]);
        }

        while (cursor + k < ar.length)
        {
            elem.remove(ar[cursor]);
            elem.add(ar[cursor+k]);

            if (elem.contains(ar[cursor]))
            {
                System.out.print("present " + ar[cursor]);
                return;
            }

            ++cursor;
        }

        System.out.print("no dup");
    }

}