package TreeOfSegments.Part2.Lesson1.problemB;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int n = input.nextInt();
        int m = input.nextInt();

        SegmentTree segmentTree = new SegmentTree(n);
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int var = input.nextInt();
            if (var == 1) {
                int l = input.nextInt();
                int r = input.nextInt();
                long v = input.nextLong();
                segmentTree.modify(l, r, v);
            }
            else{
                int index = input.nextInt();
                answer.append(segmentTree.get(index)).append("\n");
            }
        }

        System.out.println(answer);
    }


    static class SegmentTree {
        private int size;
        private long[] tree;

        public SegmentTree(int n) {
            size = 1;
            while (size < n) {
                size *= 2;
            }

            tree = new long[2 * size - 1];
        }


        private void modify(int l, int r, long v, int x, int lx, int rx) {
            if (rx <= l || r <= lx) {
                return;
            }

            if (lx >= l && rx <= r) {
                tree[x] = Math.max(tree[x] , v);
                return;
            }

            int m = (lx + rx) / 2;

            modify(l, r, v, 2 * x + 1, lx, m);
            modify(l, r, v, 2 * x + 2, m, rx);
        }

        private long get(int i, int x, int lx, int rx) {
            if (rx - lx == 1) {
                return tree[x];
            }

            int m = (lx + rx) / 2;

            if (i < m) {
                return Math.max(get(i, 2 * x + 1, lx, m) , tree[x]);
            } else {
                return Math.max(get(i, 2 * x + 2, m, rx) , tree[x]);
            }
        }

        public long get(int i) {
            return get(i, 0, 0, size);
        }

        public void modify(int l, int r, long v) {
            modify(l, r, v, 0, 0, size);
        }
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
