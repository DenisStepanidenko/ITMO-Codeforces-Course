package TreeOfSegments.Part1.Lesson3.problemE;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int n = input.nextInt();
        int m = input.nextInt();

        SegmentTree segmentTree = new SegmentTree(n + 1);
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int var = input.nextInt();
            if (var == 1) {
                int l = input.nextInt();
                int r = input.nextInt();
                long v = input.nextLong();

                segmentTree.set(l, v);
                segmentTree.set(r, v * (-1));

            } else {
                int index = input.nextInt();
                answer.append(segmentTree.sum(0, index + 1)).append(" ");
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


        private void set(int i, long v, int x, int lx, int rx) {
            if (rx - lx == 1) {
                tree[x] += v;
                return;
            }

            int m = (lx + rx) / 2;

            if (i < m) {
                set(i, v, 2 * x + 1, lx, m);
            } else {
                set(i, v, 2 * x + 2, m, rx);
            }

            tree[x] = tree[2 * x + 1] + tree[2 * x + 2];
        }

        private long sum(int l, int r, int x, int lx, int rx) {
            if (rx <= l || r <= lx) {
                return 0;
            }

            if (lx >= l && rx <= r) {
                return tree[x];
            }

            int m = (lx + rx) / 2;

            long leftSum = sum(l, r, 2 * x + 1, lx, m);
            long rightSum = sum(l, r, 2 * x + 2, m, rx);

            return leftSum + rightSum;
        }


        public long sum(int l, int r) {
            return sum(l, r, 0, 0, size);
        }

        public void set(int i, long v) {
            set(i, v, 0, 0, size);
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
