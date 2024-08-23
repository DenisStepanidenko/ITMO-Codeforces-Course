package TreeOfSegments.Part2.Lesson2.problemB;

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
            } else {
                int l = input.nextInt();
                int r = input.nextInt();
                answer.append(segmentTree.sum(l, r)).append("\n");
            }
        }

        System.out.println(answer);
    }

    static class SegmentTree {
        public final long MOD = 1000_000_007;
        public int size;
        Pair[] tree;

        public SegmentTree(int n) {
            size = 1;
            while (size < n) {
                size *= 2;
            }

            tree = new Pair[2 * size - 1];
            //Arrays.fill(tree, new Pair(0, 0));
            build(0, 0, size);
        }

        private void build(int x, int lx, int rx) {
            if (rx - lx == 1) {
                tree[x] = new Pair(1, 1);
            } else {
                int m = (lx + rx) / 2;
                build(2 * x + 1, lx, m);
                build(2 * x + 2, m, rx);

                tree[x] = new Pair(1 , tree[2 * x + 1].sum + tree[2 * x + 2].sum);
            }
        }


        private long sum(int l, int r, int x, int lx, int rx) {
            if (r <= lx || rx <= l) {
                return 0;
            }

            if (l <= lx && rx <= r) {
                return tree[x].sum;
            }

            int m = (lx + rx) / 2;
            long leftSum = sum(l, r, 2 * x + 1, lx, m);
            long rightSum = sum(l, r, 2 * x + 2, m, rx);
            return ((leftSum + rightSum) * tree[x].mult) % MOD;
        }

        public long sum(int l, int r) {
            return sum(l, r, 0, 0, size);
        }


        private void modify(int l, int r, long v, int x, int lx, int rx) {
            if (r <= lx || rx <= l) {
                return;
            }

            if (l <= lx && rx <= r) {
                tree[x].mult *= v;
                tree[x].mult %= MOD;

                tree[x].sum *= v;
                tree[x].sum %= MOD;
                return;
            }

            int m = (lx + rx) / 2;

            modify(l, r, v, 2 * x + 1, lx, m);
            modify(l, r, v, 2 * x + 2, m, rx);

            tree[x].sum = ((tree[2 * x + 1].sum + tree[2 * x + 2].sum) * tree[x].mult) % MOD;
        }

        public void modify(int l, int r, long v) {
            modify(l, r, v, 0, 0, size);
        }
    }

    static class Pair {
        public long mult;
        public long sum;

        public Pair(long mult, long sum) {
            this.mult = mult;
            this.sum = sum;
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
