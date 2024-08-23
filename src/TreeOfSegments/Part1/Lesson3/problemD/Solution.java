package TreeOfSegments.Part1.Lesson3.problemD;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int n = input.nextInt();
        Map<Integer, Integer> numbersLeft = new HashMap<>();
        Map<Integer, Integer> numbersRight = new HashMap<>();
        SegmentTree segmentTree = new SegmentTree(2 * n);
        int[] answer = new int[n];

        int[] numbers = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            int x = input.nextInt();

            numbers[2 * n - 1 - i] = x;
            if (numbersLeft.containsKey(x)) {
                int left = numbersLeft.get(x);
                int right = i;

                int sum = segmentTree.sum(left, right);
                segmentTree.set(left, 0);
                answer[x - 1] = sum - 1;
            } else {
                numbersLeft.put(x, i);
                segmentTree.set(i, 1);
            }
        }

        segmentTree = new SegmentTree(2 * n);

        for (int i = 0; i < 2 * n; i++) {
            int x = numbers[i];


            if (numbersRight.containsKey(x)) {
                int left = numbersRight.get(x);
                int right = i;

                int sum = segmentTree.sum(left, right);
                segmentTree.set(left, 0);
                answer[x - 1] += sum - 1;
            } else {
                numbersRight.put(x, i);
                segmentTree.set(i, 1);
            }
        }


        for (Integer x : answer) {
            System.out.println(x + " ");
        }
    }


    static class SegmentTree {
        private int size;
        private int[] tree;

        public SegmentTree(int n) {
            size = 1;
            while (size < n) {
                size *= 2;
            }

            tree = new int[2 * size - 1];
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

        private int sum(int l, int r, int x, int lx, int rx) {
            if (rx <= l || r <= lx) {
                return 0;
            }

            if (lx >= l && rx <= r) {
                return tree[x];
            }

            int m = (lx + rx) / 2;

            int leftSum = sum(l, r, 2 * x + 1, lx, m);
            int rightSum = sum(l, r, 2 * x + 2, m, rx);

            return leftSum + rightSum;
        }


        public int sum(int l, int r) {
            return sum(l, r, 0, 0, size);
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