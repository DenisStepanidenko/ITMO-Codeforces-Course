package TreeOfSegments.Part2.Lesson2.problemC;

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
                segmentTree.or(l, r, v);
            } else {
                int l = input.nextInt();
                int r = input.nextInt();
                answer.append(segmentTree.and(l, r)).append("\n");
            }
        }

        System.out.println(answer);


    }

    static class Pair {
        public long or; // modify
        public long and; // calc

        public Pair(long or, long and) {
            this.or = or;
            this.and = and;
        }
    }

    static class SegmentTree {
        public int size;
        Pair[] tree;

        public final long NEUTRAL_ELEMENT = (1 << 30) - 1;

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

        private void or(int l, int r, long v, int x, int lx, int rx) {
            if (r <= lx || rx <= l) {
                return;
            }

            if (l <= lx && rx <= r) {
                tree[x].or = tree[x].or | v;
                tree[x].and = tree[x].and | v;
                return;
            }
            int m = (lx + rx) / 2;
            or(l, r, v, 2 * x + 1, lx, m);
            or(l, r, v, 2 * x + 2, m, rx);

            tree[x].and = (tree[2 * x + 1].and & tree[2 * x + 2].and) | tree[x].or;


        }

        private long and(int l, int r, int x, int lx, int rx) {
            if (r <= lx || rx <= l) {
                return NEUTRAL_ELEMENT;
            }

            if (l <= lx && rx <= r) {
                return tree[x].and;
            }

            int m = (lx + rx) / 2;
            long leftAnd = and(l, r, 2 * x + 1, lx, m);
            long rightAnd = and(l, r, 2 * x + 2, m, rx);

            return (leftAnd & rightAnd) | tree[x].or;
        }

        public long and(int l, int r) {
            return and(l, r, 0, 0, size);
        }

        public void or(int l, int r, long v) {
            or(l, r, v, 0, 0, size);
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
