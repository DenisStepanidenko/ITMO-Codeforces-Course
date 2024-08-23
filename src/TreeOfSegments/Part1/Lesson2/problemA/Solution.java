package TreeOfSegments.Part1.Lesson2.problemA;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int n = input.nextInt();
        int m = input.nextInt();
        MaxSegmentTree segmentTree = new MaxSegmentTree(n);

        for (int i = 0; i < n; i++) {
            int ai = input.nextInt();
            segmentTree.set(i, ai);
        }

        StringBuilder answer = new StringBuilder();
        answer.append(segmentTree.maxSegment()).append("\n");

        for (int j = 0; j < m; j++) {
            int i = input.nextInt();
            int v = input.nextInt();

            segmentTree.set(i, v);
            answer.append(segmentTree.maxSegment()).append("\n");
        }

        System.out.println(answer);
    }


    // Дерево отрезков на поиск отрезка с максимальной суммой
    static class MaxSegmentTree {
        private int size;
        private Node[] tree;

        public long maxSegment() {
            return tree[0].getSeg();
        }

        public MaxSegmentTree(int n) {
            size = 1;
            while (size < n) {
                size *= 2;
            }

            tree = new Node[2 * size - 1];
            Arrays.fill(tree, new Node(0, 0, 0, 0));
        }

        public void set(int i, int v, int x, int lx, int rx) {
            if (rx - lx == 1) {
                tree[x] = createOneElement(v);
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

        private Node combine(Node node, Node node1) {
            long seg = Math.max(node.getSeg(), Math.max(node1.getSeg(), node.getSuf() + node1.getPref()));
            long pref = Math.max(node.getPref(), node.getSum() + node1.getPref());
            long suf = Math.max(node1.getSuf(), node1.getSum() + node.getSuf());
            long sum = node.getSum() + node1.getSum();

            return new Node(seg, pref, suf, sum);
        }

        private Node createOneElement(int v) {
            if (v < 0) {
                return new Node(0, 0, 0, v);
            }
            return new Node(v, v, v, v);
        }

        public void set(int i, int v) {
            set(i, v, 0, 0, size);
        }

    }

    static class Node {
        private long seg;
        private long pref;
        private long suf;
        private long sum;

        public Node(long seg, long pref, long suf, long sum) {
            this.seg = seg;
            this.pref = pref;
            this.suf = suf;
            this.sum = sum;
        }

        public long getSeg() {
            return seg;
        }

        public long getPref() {
            return pref;
        }

        public long getSuf() {
            return suf;
        }

        public long getSum() {
            return sum;
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
