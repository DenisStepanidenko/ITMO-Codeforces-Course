package TreeOfSegments.Part2.Lesson2.problemE;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {

    }


    static class SegmentTree {
        public int size;
        Pair[] tree;

        public SegmentTree(int n) {
            size = 1;
            while (size < n) {
                size *= 2;
            }

            tree = new Pair[2 * size - 1];

            for (int i = 0; i < 2 * size - 1; i++) {
                tree[i] = new Pair(0, 0);
            }
        }

        private void set(int l, int r, long v, int x, int lx, int rx) {
            if (r <= lx || rx <= l) {
                return;
            }
            if (l <= lx && rx <= r) {
                tree[x].set = v;
                tree[x].min = v;
                return;
            }
        }

        public void set(int l, int r, long v) {
            set(l, r, v, 0, 0, size);
        }
    }


    static class Pair {
        public long set;
        public long min;

        public Pair(long set, long min) {
            this.set = set;
            this.min = min;
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
