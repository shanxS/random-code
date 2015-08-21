// positive number that cant be made from sum of numbers in array

public class Main
{
    public static void main(String[] er)
    {
        int[] ar = {1, 2, 3, 4, 5, 6};
        (new PositiveSum()).find(ar);
    }
}

class PositiveSum
{
    public void find(int[] ar)
    {
        int sum = 1;

        for (int i : ar)
        {
            if (sum < i)
            {
                break;
            }
            else
            {
                sum += i;
            }
        }

        System.out.print(sum);
    }
}