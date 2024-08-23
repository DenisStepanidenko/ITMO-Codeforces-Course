package TreeOfSegments.Part1.Lesson2.problemB;



import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int n = input.nextInt();
        int m = input.nextInt();

        FindKOneSegmentTree segmentTree = new FindKOneSegmentTree(n);

        for (int i = 0; i < n; i++) {
            int ai = input.nextInt();
            segmentTree.set(i, ai);
        }

        StringBuilder answer = new StringBuilder();

        for (int j = 0; j < m; j++) {
            int var = input.nextInt();

            if (var == 2) {
                int k = input.nextInt();
                answer.append(segmentTree.findK(k)).append("\n");
            } else {
                int i = input.nextInt();
                segmentTree.set(i);
            }
        }

        System.out.println(answer);
    }


    static class FindKOneSegmentTree {
        private int size;
        private int[] tree;


        public FindKOneSegmentTree(int n) {
            size = 1;

            while (size < n) {
                size *= 2;
            }

            tree = new int[2 * size - 1];
        }

        public void set(int i, int v, int x, int lx, int rx) {
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

        public int findK(int k, int x, int lx, int rx) {
            if (rx - lx == 1) {
                return lx;
            }

            int m = (lx + rx) / 2;

            if (k < tree[2 * x + 1]) {
                return findK(k, 2 * x + 1, lx, m);
            } else {
                return findK(k - tree[2 * x + 1], 2 * x + 2, m, rx);
            }
        }

        public int findK(int k) {
            return findK(k, 0, 0, size);
        }

        public void set(int i, int v) {
            set(i, v, 0, 0, size);
        }

        public void set(int i) {
            set(i, 0, 0, size);
        }

        public void set(int i, int x, int lx, int rx) {
            if (rx - lx == 1) {
                if (tree[x] == 0) {
                    tree[x] = 1;
                } else {
                    tree[x] = 0;
                }
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
