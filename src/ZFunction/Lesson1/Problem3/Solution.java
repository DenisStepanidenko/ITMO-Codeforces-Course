package ZFunction.Lesson1.Problem3;

import java.io.*;
import java.util.*;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {


        int q = input.nextInt();
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < q; i++) {
            String t = input.nextLine();
            String p = input.nextLine();
            solve(t, p, answer);
        }
        System.out.println(answer);
    }

    private static void solve(String text, String pattern, StringBuilder answer) {
        int count = 0;
        StringBuilder indexes = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            boolean flag = false;
            int j = i;
            int patternIndex = 0;
            while (text.charAt(j) == pattern.charAt(patternIndex) || (pattern.charAt(patternIndex) == '?')) {
                if (patternIndex == pattern.length() - 1) {
                    flag = true;
                    break;
                }
                j++;
                patternIndex++;
                if (j >= text.length()) {
                    break;
                }
            }
            if (flag) {
                count++;
                indexes.append(i).append(" ");
            }
        }
        answer.append(count).append("\n");
        answer.append(indexes).append("\n");
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
