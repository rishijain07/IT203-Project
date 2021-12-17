public class Test {
    static float INF = (float) 1e7;
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

        ShortestPath test1 = new ShortestPath(V,dist,volume,capacity,hospital);
        ShortestPath2 test2 = new ShortestPath2(V,dist,hospital);

        // Considering only distance Factor
        System.out.println("\" ONLY distance factor is considered \"");
        test2.printShortestPath(8);

        System.out.println();
        System.out.println();

        //Considering both distance and traffic factor
       System.out.println("\"BOTH distance and traffic factor is considered \"");
       test1.printOptimalPath(8);
    }
}
