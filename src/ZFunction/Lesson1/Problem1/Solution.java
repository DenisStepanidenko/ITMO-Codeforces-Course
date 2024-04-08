package ZFunction.Lesson1.Problem1;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {

    static Reader input = new Reader();

    /**
     * Наидлиннейший палиндромный префикс
     * Задана строка s
     * Найдите длину её наиболее длинного префикса, который является палиндромом
     */

    public static void main(String[] args) {
        int t = input.nextInt();
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String current = input.nextLine();
            int ans = solve(current);
            answer.append(ans).append("\n");
        }
        System.out.println(answer);
    }

    private static int solve(String current) {
        int max = 1;
        for (int i = 1; i < current.length(); i++) {
            boolean flag = true;
            for (int j = 0; j <= i; j++) {
                if (current.charAt(j) != current.charAt(i - j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (i + 1 > max) {
                    max = i + 1;
                }
            }
        }
        return max;
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
