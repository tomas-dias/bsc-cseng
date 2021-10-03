import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;


class MazyLuck
{
    // class to represent a corridor
    static class Corridor
    {
        int current_room, next_room, gold_coins;

        Corridor()
        {
            current_room = 0;
            next_room = 0;
            gold_coins = 0;
        }
    }

    // creates a maze
    static class Maze
    {
        int R, C;
        Corridor[] corridor;

        Maze(int r, int c)
        {
            R = r;
            C = c;
            corridor = new Corridor[c];
            for (int i = 0; i < c; ++i)
                corridor[i] = new Corridor();
        }
    }

    static boolean BellmanFord(Maze maze)
    {
        int i, j, r, c, g;
        int R = maze.R;
        int C = maze.C;

        int[] distances = new int[R];

        // initialize
        for (i = 0; i < R; ++i)
            distances[i] = Integer.MAX_VALUE;
        distances[0] = 0;

        // relax
        for (i = 1; i < R; i++)
        {
            for (j = 0; j < C; j++)
            {
                r = maze.corridor[j].current_room;
                c = maze.corridor[j].next_room;
                g = maze.corridor[j].gold_coins;
                if (distances[r] != Integer.MAX_VALUE && distances[r] + g < distances[c])
                    distances[c] = distances[r] + g;
            }
        }

        // check for negative-weight cycles
        for (j = 0; j < C; j++)
        {
            r = maze.corridor[j].current_room;
            c = maze.corridor[j].next_room;
            g = maze.corridor[j].gold_coins;
            if (distances[r] != Integer.MAX_VALUE && distances[r] + g < distances[c])
                return false;
        }

        return distances[R - 1] >= 0;
    }

    public static void main(String[] args) throws IOException
    {
        int i, r, c;
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        line = br.readLine();

        int rooms = Integer.parseInt(line.split(" ")[0]);
        int corridors = Integer.parseInt(line.split(" ")[1]);

        Maze maze = new Maze(rooms, corridors);

        for(i = 0; i < corridors; i++)
        {
            line = br.readLine();
            r = Integer.parseInt(line.split(" ")[0]);
            c = Integer.parseInt(line.split(" ")[1]);
            maze.corridor[i].current_room = r;
            maze.corridor[i].next_room = c;
            if(line.split(" ")[2].equals("B"))
                maze.corridor[i].gold_coins = Integer.parseInt(line.split(" ")[3]);
            else
                maze.corridor[i].gold_coins = - Integer.parseInt(line.split(" ")[3]);
        }

        if(BellmanFord(maze))
            System.out.println("no");
        else
            System.out.println("yes");
    }
}
