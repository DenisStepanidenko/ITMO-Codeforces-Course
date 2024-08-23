package TreeOfSegments.Part1.Lesson3.problemA;



import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int n = input.nextInt();
        SegmentTree segmentTree = new SegmentTree(n);
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int x = input.nextInt();
            segmentTree.set(x - 1);
            answer.append(segmentTree.sum(x - 1) - 1).append(" ");
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


        private long sum(int l, int x, int lx, int rx) {
            if (rx <= l) {
                return 0;
            }

            if (lx >= l) {
                return tree[x];
            }

            int m = (lx + rx) / 2;
            long sumLeft = sum(l, 2 * x + 1, lx, m);
            long sumRight = sum(l, 2 * x + 2, m, rx);

            return sumLeft + sumRight;

        }

        public long sum(int l) {
            return sum(l, 0, 0, size);
        }


        private void set(int i, int x, int lx, int rx) {
            if (rx - lx == 1) {
                tree[x] = 1;
                return;
            }

            int m = (lx + rx) / 2;

            if (i < m) {
                set(i, 2 * x + 1, lx, m);
            } else {
                set(i, 2 * x + 2, m, rx);
            }

            tree[x] = tree[2 * x + 1] + tree[2 * x + 2];
        }

        public void set(int i) {
            set(i, 0, 0, size);
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
