package TreeOfSegments.Part1.Lesson2.problemD;




import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int n = input.nextInt();
        int m = input.nextInt();

        FindFirstElemMoreOrEqualsThen segmentTree = new FindFirstElemMoreOrEqualsThen(n);

        for (int i = 0; i < n; i++) {
            int ai = input.nextInt();
            segmentTree.set(i, ai);
        }

        StringBuilder answer = new StringBuilder();

        for (int j = 0; j < m; j++) {
            int var = input.nextInt();

            if (var == 1) {
                int i = input.nextInt();
                int v = input.nextInt();
                segmentTree.set(i, v);
            } else {
                int x = input.nextInt();
                int l = input.nextInt();
                answer.append(segmentTree.firstAbove(x, l)).append("\n");
            }
        }

        System.out.println(answer);
    }


    static class FindFirstElemMoreOrEqualsThen {
        private int size;
        private int[] tree;


        public FindFirstElemMoreOrEqualsThen(int n) {
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

            tree[x] = Math.max(tree[2 * x + 1], tree[2 * x + 2]);
        }


        public void set(int i, int v) {
            set(i, v, 0, 0, size);
        }

        public int firstAbove(int v, int l) {
            return firstAbove(v, l, 0, 0, size);
        }

        private int firstAbove(int v, int l, int x, int lx, int rx) {
            if (tree[x] < v || rx <= l) {
                return -1;
            }
            if (rx - lx == 1) {
                return lx;
            }
            int m = (lx + rx) / 2;

            int res = firstAbove(v, l, 2 * x + 1, lx, m);
            if (res == -1) {
                res = firstAbove(v, l, 2 * x + 2, m, rx);
            }

            return res;
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
