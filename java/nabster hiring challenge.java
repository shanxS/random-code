// nabster hiring challenge
/*
* Panda has a thing for palindromes. Hence he was a given a problem by his master. The master will give Panda an array of strings S having N strings. Now Panda has to select the Palin Pairs from the given strings .

A Palin Pair is defined as :

(i,j) is a Palin Pair if Si = reverse(Sj) and i < j

Panda wants to know how many such Palin Pairs are there in S.
Please help him in calculating this.

Input:

The first line contains N, the number of strings present in S.
Then N strings follow.

Output:

Output the query of Panda in single line.

Constraints:

1 ≤ N ≤ 100000
1 ≤ |Si| ≤ 10 (length of string)

The string consists of Upper and Lower case alphabets only.
*
* */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main
{
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private static String[] strings;
    private static long[] bkwdHash;
    private static Map<Long, List<Integer>> fwdHashIndicies;
    private final static int prime = 137;

    public static void main(String[] er)
    {
        try
        {
            Integer T = Integer.parseInt(bufferedReader.readLine());

            strings = new String[T];
            bkwdHash = new long[T];
            fwdHashIndicies = new HashMap<>();

            for (int i=0; i<T; ++i)
            {
                String input = bufferedReader.readLine();
                strings[i] = input;
                bkwdHash[i] = generateBkwdHash(input);
                addfwdHash(input, i, generateFwdHash(input));
            }

            int count = 0;
            for (int i=0; i<strings.length; ++i)
            {
                long hash = bkwdHash[i];
                List<Integer> indices = fwdHashIndicies.get(hash);
                if (indices != null)
                {
                    for (int index : indices)
                    {
                        if (index > i && isPalin(i, index))
                        {
                            ++count;
                        }
                    }
                }
            }

            System.out.print(count);
        }
        catch (Exception e)
        {
            System.out.print("exception in buffered reader " + e.getMessage());
        }

    }

    private static boolean isPalin(int i1, int i2)
    {
        String s1 = strings[i1];
        String s2 = strings[i2];

        if (s1.length() != s2.length())
        {
            return false;
        }

        int c1 = 0;
        int c2 = s2.length()-1;
        while (c1 >= c2)
        {
            if (s1.charAt(c1) != s2.charAt(c2))
            {
                return false;
            }

            ++c1;
            --c2;
        }

        return true;
    }

    private static void addfwdHash(String input, int index, long hash)
    {
        List<Integer> indices = fwdHashIndicies.get(hash);
        if (indices == null)
        {
            indices = new ArrayList<>();
            fwdHashIndicies.put(hash, indices);
        }
        indices.add(index);
    }

    private static long generateBkwdHash(String input)
    {
        long hash = prime;

        for (int i=input.length()-1; i>=0; --i)
        {
            hash = input.charAt(i) + (prime*hash);
            hash = hash%Long.MAX_VALUE;
        }

        return hash;
    }

    private static long generateFwdHash(String input)
    {
        long hash = prime;

        for (int i=0; i<input.length(); ++i)
        {
            hash = input.charAt(i) + (prime*hash);
            hash = hash%Long.MAX_VALUE;
        }

        return hash;
    }
}
