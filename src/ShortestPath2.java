import java.util.Vector;

/*
*  This Code calculate the shortest distanc from accident point to nearest hospital considering only distance factor
*
* */

public class ShortestPath2 {

    static float INF = (float) 1e7;

    float[][] dis ;
    float[][] Next ;
    int[] hospital;

    public ShortestPath2(int V,float[][] graph,int[] hospital){

        dis = new float[V][V];
        Next = new float[V][V];
        this.initialise(V,graph);
        this.hospital = hospital;
        this.floydWarshall(V);
    }

    void initialise(int V, float[][] graph) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                this.dis[i][j] = graph[i][j];

                if (graph[i][j] == INF)
                    this.Next[i][j] = -1;
                else
                    this.Next[i][j] = j;
            }
        }
    }

    void floydWarshall(int V) {
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (this.dis[i][k] == INF || this.dis[k][j] == INF)
                        continue;

                    if (this.dis[i][j] > this.dis[i][k] + this.dis[k][j]) {
                        this.dis[i][j] = this.dis[i][k] + this.dis[k][j];
                        this.Next[i][j] = this.Next[i][k];
                    }
                }
            }
        }
    }


    void printPath(int start, int end){

        int u,v;
        u = start -1;
        v= end-1;
        if (this.Next[u][v] == -1)
            return;

        Vector<Integer> path = new Vector<Integer>();
        path.add(u);

        while (u != v) {
            u = (int) this.Next[u][v];
            path.add(u);
        }

        int n = path.size();
        System.out.println("Optimal path to the hospiatl from "+start+" is :");
        for (int i = 0; i < n - 1; i++)
            System.out.print(path.get(i) +1+ " -> ");
        System.out.print(path.get(n - 1) +1+ "\n");
    }

    void printShortestPath(int node){
        int n = this.hospital.length;
        float min=this.dis[node-1][this.hospital[0]-1];
        int flag=0;
        for (int i = 0; i < n; i++) {
            if(min>this.dis[node-1][this.hospital[i]-1]){
                min = this.dis[node-1][this.hospital[i]-1];
                flag =i;
            }
        }
        System.out.println("The Optimal hospital from "+node+" is \""+this.hospital[flag]+"\"");
        this.printPath(node,this.hospital[flag]);

    }

    public static void main(String[] args) {
        int V = 13;
        float dist[][] = {{0, INF, 1, INF, 5, INF, INF, INF, 6, 2, INF, 3, INF},
                {INF, 0, INF, INF, 4, INF, INF, INF, INF, INF, 10, INF, INF},
                {1, INF, 0, 5, INF, INF, INF, INF, INF, INF, INF, INF, INF},
                {INF, INF, 5, 0, INF, INF, INF, INF, INF, 3, INF, INF, INF},
                {5,4,INF,INF,0,7,INF,INF,INF,INF,2,INF,INF},
                {INF,INF,INF,INF,7,0,INF,INF,INF,2,INF,INF,INF},
                {INF,INF,INF,INF,INF,INF,0,INF,3,INF,INF,INF,INF},
                {INF,INF,INF,INF,INF,INF,INF,0,INF,INF,6,INF,INF},
                {6,INF,INF,INF,INF,INF,3,INF,0,INF,INF,INF,4},
                {2,INF,INF,3,INF,2,INF,INF,INF,0,INF,INF,INF},
                {INF,10,INF,INF,2,INF,INF,6,INF,INF,0,INF,INF},
                {3,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,0,INF},
                {INF,INF,INF,INF,INF,INF,INF,INF,INF,4,INF,INF,INF,0}};
        int[]hospital = {1,2,6};


        ShortestPath2 test = new ShortestPath2(V,dist,hospital);
        test.printShortestPath(8);
        //System.out.println(test.dis[7][0]+" "+test.dis[7][1]+" "+test.dis[7][5]);


    }



}
