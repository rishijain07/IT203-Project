
import java.util.Vector;


/*
 *  This Code calculate the shortest distanc from accident point to nearest hospital considering both distance factor
 * and traffic conditions
 *
 * */

public class ShortestPath{

    static float INF = (float) 1e7;

    float[][] dis ;
    float[][] Next ;
    int[] hospital;

    public ShortestPath(int V,float[][] dist, float[][] vol,float[][] cap,int[] hospital){

        this.dis = new float[V][V];
        this.Next = new float[V][V];
        this.hospital = hospital;

        float [][] deg = new float[V][V];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                deg[i][j] = (float) vol[i][j]/cap[i][j];
            }
        }

        float[][] weight = new float[V][V];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                weight[i][j] =  (deg[i][j]*dist[i][j]);
            }
        }

        this.initialise(V,weight);
        this.floydWarshall(V);

       /* for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if(weight[i][j]==INF)
                    System.out.print("INF ");
                else
                    System.out.print(weight[i][j]+" ");
            }
            System.out.println();
        }*/

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

    void printOptimalPath(int node){
        int n = this.hospital.length;
        float min=this.dis[node-1][this.hospital[0]-1];
        int flag= 0;
        for (int i = 0; i < n; i++) {
            if(min>this.dis[node-1][this.hospital[i]-1]){
                min = this.dis[node-1][this.hospital[i]-1];
                flag = i;
            }
        }
        System.out.println("The closest hospital from "+node+" is \""+this.hospital[flag]+"\"");
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

        float[][] volume = {{0, INF, 800, INF, 1200, INF, INF, INF, 1100, 800, INF, 1000, INF},
                {INF, 0, INF, INF, 1300, INF, INF, INF, INF, INF, 1700, INF, INF},
                {800, INF, 0, 1100, INF, INF, INF, INF, INF, INF, INF, INF, INF},
                {INF, INF, 1100, 0, INF, INF, INF, INF, INF, 990, INF, INF, INF},
                {1200,1300,INF,INF,0,1500,INF,INF,INF,INF,1250,INF,INF},
                {INF,INF,INF,INF,1500,0,INF,INF,INF,900,INF,INF,INF},
                {INF,INF,INF,INF,INF,INF,0,INF,1250,INF,INF,INF,INF},
                {INF,INF,INF,INF,INF,INF,INF,0,INF,INF,1100,INF,INF},
                {1100,INF,INF,INF,INF,INF,1250,INF,0,INF,INF,INF,1100},
                {800,INF,INF,990,INF,900,INF,INF,INF,0,INF,INF,INF},
                {INF,1700,INF,INF,1250,INF,INF,1100,INF,INF,0,INF,INF},
                {1000,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,0,INF},
                {INF,INF,INF,INF,INF,INF,INF,INF,INF,1100,INF,INF,INF,0}};

        float[][] capacity = {{0, INF, 1000, INF, 1500, INF, INF, INF, 1300, 1100, INF, 1200, INF},
                {INF, 0, INF, INF, 1200, INF, INF, INF, INF, INF, 2000, INF, INF},
                {1000, INF, 0, 1200, INF, INF, INF, INF, INF, INF, INF, INF, INF},
                {INF, INF, 1200, 0, INF, INF, INF, INF, INF, 1000, INF, INF, INF},
                {1500,1200,INF,INF,0,1800,INF,INF,INF,INF,1400,INF,INF},
                {INF,INF,INF,INF,1800,0,INF,INF,INF,1100,INF,INF,INF},
                {INF,INF,INF,INF,INF,INF,0,INF,1500,INF,INF,INF,INF},
                {INF,INF,INF,INF,INF,INF,INF,0,INF,INF,1600,INF,INF},
                {1300,INF,INF,INF,INF,INF,1500,INF,0,INF,INF,INF,1400},
                {1100,INF,INF,1000,INF,1100,INF,INF,INF,0,INF,INF,INF},
                {INF,2000,INF,INF,1400,INF,INF,1600,INF,INF,0,INF,INF},
                {1200,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,0,INF},
                {INF,INF,INF,INF,INF,INF,INF,INF,INF,1400,INF,INF,INF,0}};

        int hospital[]= {1,2,6};


        ShortestPath test = new ShortestPath(V,dist,volume,capacity,hospital);

        test.printOptimalPath(8);






    }




}