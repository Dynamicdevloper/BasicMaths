//{ Driver Code Starts
// Initial Template for Java

import java.util.*;
// Position this line where user code will be pasted.

class GFG {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            List<List<Integer>> adj = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                List<Integer> temp = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    int x = sc.nextInt();
                    temp.add(x);
                }
                adj.add(temp);
            }

            Solution obj = new Solution();
            System.out.println(obj.countPaths(n, adj));
        }
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution {

    static int countPaths(int n, List<List<Integer>> roads) {
    
        List<List<int[]>> graph=new ArrayList<>();
        for(int i=0;i<n;i++) graph.add(new ArrayList<int[]>());
        for(int i=0;i<roads.size();i++){
            graph.get(roads.get(i).get(0)).add(new int[]{roads.get(i).get(1),roads.get(i).get(2)});
            graph.get(roads.get(i).get(1)).add(new int[]{roads.get(i).get(0),roads.get(i).get(2)});
        }
        long dist[]=new long[n];
        Arrays.fill(dist,Long.MAX_VALUE);
        dist[0]=0;
        int cnt[]=new int[n];
        cnt[0]=1;
        PriorityQueue<long[]> pq=new PriorityQueue<long[]>((a,b)->{
            return (int)(a[0]-b[0]);
        });
        pq.add(new long[]{0,0});
        while(!pq.isEmpty()){
            long node[]=pq.poll();
            long dst=node[0];
            int sr=(int)node[1];
            for(int []nbr:graph.get(sr)){
                int nr=nbr[0];
                long wt=nbr[1];
                if(dist[nr]>dst+wt){
                    dist[nr]=dst+wt;
                    pq.add(new long[]{dst+wt,nr});
                    cnt[nr]=cnt[sr];
                }
                else if(dist[nr]==dst+wt){
                    cnt[nr]=(cnt[nr]+cnt[sr])%(int)(1e9+7);
                }
            }
        }
        return cnt[n-1];
    }
    
}












