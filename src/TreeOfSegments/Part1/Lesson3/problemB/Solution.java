package TreeOfSegments.Part1.Lesson3.problemB;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int n = input.nextInt();

        SegmentTree segmentTree = new SegmentTree(n);
        for (int i = 0; i < n; i++) {
            segmentTree.set(i, 1);
        }

        var arr = new int[n];
        for (int i = 0; i < n; i++) {
            int x = input.nextInt();
            arr[i] = x;
        }
        int[] answer = new int[n];
        int countOfZero = 0;
        for (int i = n - 1; i >= 0; i--) {
            int x = arr[i];
            int number = segmentTree.find((n - countOfZero) - x - 1) + 1;
            countOfZero++;
            answer[i] = number;
            segmentTree.set(number - 1, 0);
        }

        StringBuilder answerToString = new StringBuilder();
        for (int i = 0; i < answer.length; i++) {
            answerToString.append(answer[i]).append(" ");
        }

        System.out.println(answerToString);
    }

    static class SegmentTree {
        private int size;
        private int[] tree;

        public int getSize() {
            return size;
        }

        public SegmentTree(int n) {
            size = 1;
            while (size < n) {
                size *= 2;
            }

            tree = new int[2 * size - 1];
        }

        private int find(int k, int x, int lx, int rx) {
            if (rx - lx == 1) {
                return lx;
            }

            int m = (lx + rx) / 2;
            if (k < tree[2 * x + 1]) {
                return find(k, 2 * x + 1, lx, m);
            } else {
                return find(k - tree[2 * x + 1], 2 * x + 2, m, rx);
            }
        }


        public int find(int k) {
            return find(k, 0, 0, size);
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

            tree[x] = tree[2 * x + 1] + tree[2 * x + 2];
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

