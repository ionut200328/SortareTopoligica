import java.util.*;
public class TopologicSort {
    private static ArrayList<ArrayList<Integer>>ListaAdiacenta=new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<Integer>>getListaAdiacenta()
    {
        return ListaAdiacenta;
    }
    private static Vector<Vector<Integer>>AdjencyMatrix;

    void createListaAdiacenta()
    {
        AdjencyMatrix=Frame.AdjencyMatrix();
        if(AdjencyMatrix.size()==0)
        {
            return;
        }
        ListaAdiacenta.clear();
        for(int i=0;i<AdjencyMatrix.size();i++)
        {
            ArrayList<Integer>list=new ArrayList<Integer>();
            for(int j=0;j<AdjencyMatrix.size();j++)
            {
                if(AdjencyMatrix.get(i).get(j)==1)
                {
                    list.add(j);
                }
            }
            ListaAdiacenta.add(list);
        }
    }
    boolean isCiclic() {
        int V = ListaAdiacenta.size();
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];

        for (int i = 0; i < V; i++) {
            Stack<Integer> stack = new Stack<>();
            stack.push(i);
            int j;
            while (!stack.isEmpty()) {
                j = stack.peek();
                if (!visited[j]) {
                    visited[j] = true;
                    recStack[j] = true;
                }
                boolean isDone = true;
                for (int k = 0; k < ListaAdiacenta.get(j).size(); k++) {
                    if (!visited[ListaAdiacenta.get(j).get(k)]) {
                        stack.push(ListaAdiacenta.get(j).get(k));
                        isDone = false;
                        break;
                    } else if (recStack[ListaAdiacenta.get(j).get(k)]) {
                        return true;
                    }
                }
                if (isDone) {
                    recStack[stack.pop()] = false;
                }
            }

        }
        return false;
    }



    void printListaAdiacenta()
    {
        for(int i=0;i<ListaAdiacenta.size();i++)
        {
            System.out.print((i+1)+": ");
            for(int j=0;j<ListaAdiacenta.get(i).size();j++)
            {
                System.out.print((ListaAdiacenta.get(i).get(j)+1)+" ");
            }
            System.out.println();
        }
    }
    ArrayList<Integer>PTDF()
    {
        ArrayList<Integer>PTDF=new ArrayList<Integer>();
        int[]indegree=new int[AdjencyMatrix.size()];
        for(int i=0;i<AdjencyMatrix.size();i++)
        {
            for(int j=0;j<AdjencyMatrix.size();j++)
            {
                if(AdjencyMatrix.get(i).get(j)==1)
                {
                    indegree[j]++;
                }
            }
        }
        Queue<Integer>q=new LinkedList<Integer>();
        for(int i=0;i<AdjencyMatrix.size();i++)
        {
            if(indegree[i]==0)
            {
                q.add(i);
            }
        }
        while(!q.isEmpty())
        {
            int u=q.poll();
            PTDF.add(u);
            for(int i=0;i<ListaAdiacenta.get(u).size();i++)
            {
                int v=ListaAdiacenta.get(u).get(i);
                if(--indegree[v]==0)
                {
                    q.add(v);
                }
            }
        }
        return PTDF;
    }
}
