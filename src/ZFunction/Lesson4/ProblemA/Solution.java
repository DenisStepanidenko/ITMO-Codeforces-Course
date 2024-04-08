package ZFunction.Lesson4.ProblemA;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int t = input.nextInt();
        StringBuilder answer = new StringBuilder();
        while (t > 0) {
            String text = input.nextLine();
            //System.out.println(text);
            solve(text, answer);
            t--;
        }
        System.out.println(answer);
    }

    private static void solve(String text, StringBuilder answer) {
        int[] zValue = getZFunction(text);
        for (int i = 0; i < zValue.length; i++) {
            if ((i + zValue[i]) == text.length()) {
                String period = text.substring(0, i);
                answer.append(period).append("\n");
                return;
            }
        }
        answer.append(text).append("\n");
    }

    private static int[] getZFunction(String text) {
        int[] zValue = new int[text.length()];
        int r = 0;
        int l = 0;
        for (int i = 1; i < text.length(); i++) {
            if (i <= r) {
                if (zValue[i - l] < r - i + 1) {
                    zValue[i] = zValue[i - l];
                } else {
                    zValue[i] = r - i + 1;
                    while (zValue[i] + i < text.length() && (text.charAt(zValue[i]) == text.charAt(zValue[i] + i))) {
                        zValue[i]++;
                    }
                }
            } else {
                while (zValue[i] + i < text.length() && (text.charAt(zValue[i]) == text.charAt(zValue[i] + i))) {
                    zValue[i]++;
                }
            }
            if (r < zValue[i] + i - 1) {
                r = zValue[i] + i - 1;
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
