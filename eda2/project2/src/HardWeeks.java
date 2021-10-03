import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class HardWeeks
{
    static class Task
    {
        int value;
        int week;
    }

    public static void resolve(int num_tasks, int limit, ArrayList<ArrayList<Integer>> adj, int[] result)
    {
        int i;
        int week = 1;
        int nt = 0;
        int max_num_tasks = 0;
        int hard_weeks = 0;
        int[] parents = new int[num_tasks];

        for(i = 0; i < num_tasks; i++)
        {
            ArrayList<Integer> temp = adj.get(i);

            for (int node : temp)
                parents[node]++;
        }

        Queue<Task> queue = new LinkedList<>();

        for(i = 0; i < num_tasks; i++)
        {
            if(parents[i] == 0)
            {
                Task t = new Task();
                t.value = i;
                t.week = week;
                queue.add(t);
            }
        }

        while (!queue.isEmpty())
        {
            Task u = queue.poll();

            for(int node : adj.get(u.value))
            {
                if (--parents[node] == 0)
                {
                    Task v = new Task();
                    v.value = node;
                    v.week = u.week + 1;
                    queue.add(v);
                }
            }

            if(u.week == week)
                nt++;
            else
            {
                if(nt > max_num_tasks)
                    max_num_tasks = nt;

                if(nt > limit)
                    hard_weeks++;

                nt = 1;
                week++;
            }
        }

        if(nt > max_num_tasks)
            max_num_tasks = nt;

        if(nt > limit)
            hard_weeks++;

        result[0] = max_num_tasks;
        result[1] = hard_weeks;
    }

    public static void main(String[] args) throws IOException
    {
        int i, u, v;
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        line = br.readLine();

        int num_tasks = Integer.parseInt(line.split(" ")[0]);
        int num_precedences = Integer.parseInt(line.split(" ")[1]);
        int limit = Integer.parseInt(line.split(" ")[2]);

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(num_tasks);

        for(i = 0; i < num_tasks; i++)
            adj.set(i, new ArrayList<>());

        while(num_precedences > 0)
        {
            line = br.readLine();
            u = Integer.parseInt(line.split(" ")[0]);
            v = Integer.parseInt(line.split(" ")[1]);
            adj.get(u).add(v);
            num_precedences--;
        }

        int[] result = new int[2];

        resolve(num_tasks, limit, adj, result);

        System.out.println(result[0] + " " + result[1]);
    }
}