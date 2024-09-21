package graphs.lesson1.problemA;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int t = input.nextInt();
        StringBuilder answer = new StringBuilder();

        Map<Integer,Integer> edges = new HashMap<>();


        while (t > 0) {
            int n = input.nextInt();
            int m = input.nextInt();

            String currentAnswer = "YES";

            for (int i = 0; i < m; i++) {
                int u = input.nextInt();
                int v = input.nextInt();

                if (u == v) {
                    // петля
                    currentAnswer = "NO";
                    break;
                }

                if(edges.containsKey(v)){
                    if(edges.get(v) == u){
                        // кратное ребро
                        currentAnswer = "NO";
                        break;
                    }
                }

                edges.put(v,u);
            }


            answer.append(currentAnswer).append("\n");
            t--;
        }

        System.out.println(answer);
    }

    static class Reader extends PrintWriter {
        private BufferedReader r;
        private StringTokenizer st;
        // standard input

        public Reader() {
            this(System.in, System.out);
        }

        public Reader(InputStream i, OutputStream o) {
            super(o);
            r = new BufferedReader(new InputStreamReader(i));
        }
        // USACO-style file input

        public Reader(String problemName) throws IOException {
            super(problemName + ".out");
            r = new BufferedReader(new FileReader(problemName));
        }

        // returns null if no more input
        String nextLine() {
            String str = "";
            try {
                str = r.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        public String next() {
            try {
                while (st == null || !st.hasMoreTokens()) {
                    st = new StringTokenizer(r.readLine());
                }
                return st.nextToken();
            } catch (Exception e) {
            }
            return null;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {

            return Long.parseLong(next());
        }
    }
}
