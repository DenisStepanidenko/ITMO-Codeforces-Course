package ZFunction.Lesson4.ProblemE;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        String s = input.nextLine();
        String t = input.nextLine();

        StringBuilder answer = new StringBuilder();
        solve(s, t, answer);
        System.out.println(answer);
    }

    private static void solve(String s, String t, StringBuilder answer) {
        if (s.length() != t.length()) {
            answer.append("No");
            return;
        }
        if (s.equals(t)) {
            answer.append("Yes").append("\n");
            answer.append(0);
            return;
        }
        String newString = t + s;
        int[] zValue = getZFunction(newString);

        for (int i = s.length(); i < zValue.length; i++) {
            if (((i + zValue[i]) == s.length() + t.length())) {

                int position = s.length() - (s.length() + t.length() - 1 - i) - 1;
                String firstSubstring = s.substring(0, i - s.length());
                String secondSubstring = new StringBuilder(t.substring(t.length() + s.length() - i)).reverse().toString();
                if (firstSubstring.equals(secondSubstring)) {
                    answer.append("Yes").append("\n");
                    answer.append(position);
                } else {
                    answer.append("No");
                }
                return;
            }
        }
        answer.append("No");
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

