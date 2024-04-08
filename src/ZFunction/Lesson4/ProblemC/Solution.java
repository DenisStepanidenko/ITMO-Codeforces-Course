package ZFunction.Lesson4.ProblemC;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int q = input.nextInt();
        StringBuilder answer = new StringBuilder();
        while (q > 0) {
            String text = input.nextLine();
            solve(text, answer);
            q--;
        }
        System.out.println(answer);
    }

    private static void solve(String text, StringBuilder answer2) {
        int[] answer = new int[text.length() + 1];
        int[] zValue = getZFunction(text + "$" + text);
        for (int i = text.length(); i < zValue.length; i++) {
            answer[zValue[i]]++;
        }

        for (int i = text.length() - 1; i >= 1; i--) {
            answer[i] += answer[i + 1];
        }

        for (int i = 1; i < text.length() + 1; i++) {
            answer2.append(answer[i]).append(" ");
        }
        answer2.append("\n");
    }

    private static int[] getZFunction(String s) {
        // здесь нужно вычислить z-функцию
        int[] zValue = new int[s.length()];
        int l = 0;
        int r = 0;

        for (int i = 1; i < s.length(); i++) {
            if (i <= r) {
                if (zValue[i - l] < r - i + 1) {
                    zValue[i] = zValue[i - l];
                } else {
                    zValue[i] = r - i + 1;
                    while (i + zValue[i] < s.length() && (s.charAt(zValue[i]) == s.charAt(i + zValue[i]))) {
                        zValue[i]++;
                    }
                }
            } else {
                while (i + zValue[i] < s.length() && (s.charAt(zValue[i]) == s.charAt(i + zValue[i]))) {
                    zValue[i]++;
                }
            }

            if (r < i + zValue[i] - 1) {
                r = i + zValue[i] - 1;
                l = i;
            }
        }
        return zValue;
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
