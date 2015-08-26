// counting number of bits

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static Integer K, N;
    private static List<Integer> numbers;

    public static void main(String[] er)
    {
        try
        {
            Integer T = Integer.parseInt(bufferedReader.readLine());

            for (int i=0; i<T; ++i)
            {
                String[] input = bufferedReader.readLine().split(" ");
                N = Integer.parseInt(input[0]);
                K = Integer.parseInt(input[1]);

                numbers = new ArrayList<>();
                TreeSet<Integer> counts = new TreeSet<>();
                String[] inputs = bufferedReader.readLine().split(" ");
                for(String s : inputs)
                {
                    counts.add(countFor(Integer.parseInt(s)));
                }

                int count = 0;
                while (K > 0)
                {
                    count += counts.pollLast();
                    --K;
                }
                System.out.println(count);
            }
        }
        catch (Exception e)
        {
            System.out.print("exception in buffered reader " + e.getMessage());
        }

    }

    private static Integer countFor(Integer i)
    {
        Integer count = 0;

        while (i > 0)
        {
            i = i&(i-1);
            ++count;
        }

        return count;
    }
}
