static class Dinic {
        static class Edge { int to; int rev; long cap; Edge(int t, int r, long c){to=t;rev=r;cap=c;} }
        List<Edge>[] g;
        int n, s, t;
        int[] level, it;
        public Dinic(int n, int s, int t) {
            this.n=n; this.s=s; this.t=t;
            g = new List[n];
            for (int i=0;i<n;i++) g[i] = new ArrayList<>();
            level = new int[n]; it = new int[n];
        }
        public void addEdge(int u, int v, long c) {
            g[u].add(new Edge(v, g[v].size(), c));
            g[v].add(new Edge(u, g[u].size()-1, 0));
        }
        boolean bfs() {
            Arrays.fill(level, -1);
            level[s]=0;
            Deque<Integer> dq = new ArrayDeque<>();
            dq.add(s);
            while(!dq.isEmpty()){
                int u = dq.poll();
                for (Edge e : g[u]) if (e.cap>0 && level[e.to]==-1) {
                    level[e.to]=level[u]+1; dq.add(e.to);
                }
            }
            return level[t]!=-1;
        }
        long dfs(int u, long f) {
            if (u==t) return f;
            for (int i=it[u]; i<g[u].size(); ++i){
                it[u]=i;
                Edge e = g[u].get(i);
                if (e.cap>0 && level[e.to]==level[u]+1){
                    long ret = dfs(e.to, Math.min(f, e.cap));
                    if (ret>0){
                        e.cap -= ret;
                        g[e.to].get(e.rev).cap += ret;
                        return ret;
                    }
                }
            }
            return 0;
        }
        public long maxFlow() {
            long flow = 0;
            while (bfs()) {
                Arrays.fill(it, 0);
                long f;
                while ((f = dfs(s, Long.MAX_VALUE/4)) > 0) flow += f;
            }
            return flow;
        }
    }
