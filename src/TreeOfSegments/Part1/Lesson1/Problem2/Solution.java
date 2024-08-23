package TreeOfSegments.Part1.Lesson1.Problem2;

import java.io.*;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int n = input.nextInt();
        int m = input.nextInt();

        SegmentTreeMin segmentTreeMin = new SegmentTreeMin(n);

        for (int i = 0; i < n; i++) {
            int x = input.nextInt();
            segmentTreeMin.set(i, x);
        }

        for (int j = 0; j < m; j++) {
            int operation = input.nextInt();
            if (operation == 1) {
                int i = input.nextInt();
                int v = input.nextInt();
                segmentTreeMin.set(i, v);
            } else {
                int l = input.nextInt();
                int r = input.nextInt();
                System.out.println(segmentTreeMin.min(l, r));
            }
        }
    }

    // дерево отрезков на минимум
    static class SegmentTreeMin {
        private int size;
        int[] tree;

        public SegmentTreeMin(int n) {
            size = 1;
            while (size < n) {
                size *= 2;
            }

            tree = new int[2 * size - 1];
        }

        public int min(int l, int r, int x, int lx, int rx) {
            if (rx <= l || lx >= r) {
                return Integer.MAX_VALUE;
            }
            if (lx >= l && rx <= r) {
                return tree[x];
            }

            int m = (lx + rx) / 2;

            int minLeft = min(l, r, 2 * x + 1, lx, m);
            int minRight = min(l, r, 2 * x + 2, m, rx);

            return Math.min(minLeft, minRight);
        }


        public int min(int l, int r) {
            return min(l, r, 0, 0, size);
        }


        private void set(int i, int v, int x, int lx, int rx) {
            if (rx - lx == 1) {
                tree[x] = v;
                return;
            }
            int m = (lx + rx) / 2;
            if (i < m) {
                set(i, v, 2 * x + 1, lx, m);
            } else {
                set(i, v, 2 * x + 2, m, rx);
            }
            tree[x] = Math.min(tree[2 * x + 1], tree[2 * x + 2]);
        }

        public void set(int i, int v) {
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
