import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class CodFishing
{
    static class Info
    {
        int x;
        int y;
        int value;
    }

    static class SortByValue implements Comparator<Info>
    {
        public int compare(Info a, Info b)
        {
            return a.value - b.value;
        }
    }

    private static void store_info(BufferedReader br, Info[] array) throws IOException
    {
        int i;
        String line;

        for(i = 0; i < array.length; i++)
        {
            line = br.readLine();
            Info info = new Info();

            info.x = Integer.parseInt(line.split(" ", 3)[0]);
            info.y = Integer.parseInt(line.split(" ", 3)[1]);
            info.value = Integer.parseInt(line.split(" ", 3)[2]);

            array[i] = info;
        }

        Arrays.sort(array, new SortByValue());
    }

    static int calculate_distance(Info boat, Info spot)
    {
        return Math.abs(boat.x - spot.x) + Math.abs(boat.y - spot.y);
    }

    static void resolve(Info[] boats, Info[] spots, int[] output)
    {
        int i = 0, j, k = 0;
        int distance, total_rating = 0, total_fish = 0, total_distance = 0, boats_with_spot = 0;
        int n = (boats.length - spots.length) + 1;
        int aux = n;
        int[][] distances = new int[spots.length][boats.length];
        int[][] ratings = new int[spots.length][boats.length];

        if(boats.length < spots.length)
            i = spots.length - boats.length;

        while(i < spots.length)
        {
            for(j = k; j < boats.length; j++)
            {
                if(i == 0 && n > 0)
                {
                    distance = calculate_distance(boats[j], spots[i]);

                    if (j > 0)
                    {
                        distances[i][j] = Math.min(distance, distances[i][j - 1]);

                        if (distance >= distances[i][j - 1])
                            ratings[i][j] = Math.min(ratings[i][j - 1], boats[j].value);
                        else
                            ratings[i][j] = boats[j].value;
                    }
                    else
                    {
                        distances[i][j] = distance;
                        ratings[i][j] = boats[j].value;
                    }
                    n--;
                }
                else if(i != 0 && n > 0)
                {
                    distance = calculate_distance(boats[j], spots[i]);

                    if(j == k)
                    {
                        distances[i][j] = distance + distances[i - 1][j - 1];
                        ratings[i][j] = boats[j].value + ratings[i - 1][j - 1];
                    }
                    else
                    {
                        distances[i][j] = Math.min(distance + distances[i - 1][j - 1], distances[i][j - 1]);

                        if (distance + distances[i - 1][j - 1] >= distances[i][j - 1])
                            ratings[i][j] = Math.min(ratings[i][j - 1], boats[j].value + ratings[i - 1][j - 1]);
                        else
                            ratings[i][j] = boats[j].value + ratings[i - 1][j - 1];
                    }
                    n--;
                }
                else if(spots.length > boats.length)
                {
                    total_rating += boats[j].value;
                    total_fish += spots[i].value;
                    total_distance += calculate_distance(boats[j], spots[i]);
                    boats_with_spot++;
                    break;
                }
                else
                    break;
            }
            if(boats.length >= spots.length)
                total_fish += spots[i].value;
            else if(boats_with_spot == boats.length)
            {
                ratings[spots.length - 1][boats.length-1] = total_rating;
                distances[spots.length - 1][boats.length - 1] = total_distance;
            }
            n = aux;
            k++;
            i++;
        }

        output[0] = total_fish;
        output[1] = distances[spots.length - 1][boats.length - 1];
        output[2] = ratings[spots.length - 1][boats.length - 1];
    }

    public static void main(String[] args) throws IOException
    {
        String line;
        int[] output = new int[3];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        line = br.readLine();
        int num_boats = Integer.parseInt(line.split(" ", 2)[0]);
        int num_spots = Integer.parseInt(line.split(" ", 2)[1]);

        Info[] boats = new Info[num_boats];
        Info[] spots = new Info[num_spots];

        store_info(br, boats);
        store_info(br, spots);

        resolve(boats, spots, output);

        System.out.println(output[0] + " " + output[1] + " " + output[2]);
    }
}