package TreeOfSegments.Part1.Lesson1.Problem1;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int n = input.nextInt();
        int m = input.nextInt();

        SegmentTreeSum segmentTree = new SegmentTreeSum(n);
        for (int i = 0; i < n; i++) {
            long x = input.nextLong();
            segmentTree.set(i, x);
        }

        for (int j = 0; j < m; j++) {
            int operation = input.nextInt();
            if (operation == 1) {
                int i = input.nextInt();
                long v = input.nextLong();
                segmentTree.set(i, v);
            } else {
                int l = input.nextInt();
                int r = input.nextInt();
                System.out.println(segmentTree.sum(l, r));
            }
        }

    }


    // дерево отрезков на сумму
    static class SegmentTreeSum {
        private int size;
        public long[] tree;

        public SegmentTreeSum(int n) {
            size = 1;
            while (size < n) {
                size = size * 2;
            }
            tree = new long[2 * size - 1];
        }

        private long sum(int l, int r, int x, int lx, int rx) {
            if (rx <= l || lx >= r) {
                return 0;
            }
            if (lx >= l && rx <= r) {
                return tree[x];
            }

            int m = (lx + rx) / 2;
            long sumLeft = sum(l, r, 2 * x + 1, lx, m);
            long sumRight = sum(l, r, 2 * x + 2, m, rx);

            return sumLeft + sumRight;
        }


        public long sum(int l, int r) {
            return sum(l, r, 0, 0, size);
        }


        /**
         * Вставить по индексу i (в массиве) значение v
         *
         * @param i
         * @param v
         */
        public void set(int i, long v) {
            set(i, v, 0, 0, size);
        }

        /**
         * @param i  индекс, по которому нужно поменять значение элемента
         * @param v  значение
         * @param x  номер текущего узла
         * @param lx левая граница этого узла
         * @param rx правая граница этого узла
         */
        private void set(int i, long v, int x, int lx, int rx) {
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

            tree[x] = tree[2 * x + 1] + tree[2 * x + 2];
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