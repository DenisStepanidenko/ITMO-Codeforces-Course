package TreeOfSegments.Part1.Lesson1.Problem3;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int n = input.nextInt();
        int m = input.nextInt();

        SegmentTreeCountMin segmentTreeCountMin = new SegmentTreeCountMin(n);

        for (int i = 0; i < n; i++) {
            int x = input.nextInt();
            segmentTreeCountMin.set(i, x);
        }

        for (int j = 0; j < m; j++) {
            int operation = input.nextInt();
            if (operation == 1) {
                int i = input.nextInt();
                int v = input.nextInt();
                segmentTreeCountMin.set(i, v);
            } else {
                int l = input.nextInt();
                int r = input.nextInt();
                Pair pair = segmentTreeCountMin.calc(l, r);
                System.out.println(pair.min + " " + pair.countMin);
            }
        }
    }

    static class SegmentTreeCountMin {
        private int size;

        Pair[] tree;

        public SegmentTreeCountMin(int n) {
            size = 1;
            while (size < n) {
                size *= 2;
            }

            tree = new Pair[2 * size - 1];
            Arrays.fill(tree, new Pair(0, 0));
        }

        public void set(int i, int v, int x, int lx, int rx) {
            if (rx - lx == 1) {
                tree[x] = new Pair(v, 1);
                return;
            }

            int m = (lx + rx) / 2;
            if (i < m) {
                set(i, v, 2 * x + 1, lx, m);
            } else {
                set(i, v, 2 * x + 2, m, rx);
            }

            tree[x] = combine(tree[2 * x + 1], tree[2 * x + 2]);
        }

        private Pair combine(Pair a, Pair b) {
            if (a.min < b.min) {
                return a;
            } else if (a.min > b.min) {
                return b;
            }

            return new Pair(a.min, a.countMin + b.countMin);
        }


        public void set(int i, int v) {
            set(i, v, 0, 0, size);
        }

        private Pair calc(int l, int r, int x, int lx, int rx) {
            if (rx <= l || r <= lx) {
                return new Pair(Integer.MAX_VALUE, 0);
            }
            if (lx >= l && rx <= r) {
                return tree[x];
            }

            int m = (lx + rx) / 2;
            Pair pairLeft = calc(l, r, 2 * x + 1, lx, m);
            Pair pairRight = calc(l, r, 2 * x + 2, m, rx);

            return combine(pairRight, pairLeft);
        }

        public Pair calc(int l, int r) {
            return calc(l, r, 0, 0, size);
        }

    }


    static class Pair {
        public Pair(int min, int countMin) {
            this.min = min;
            this.countMin = countMin;
        }

        public int min;
        public int countMin;

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
