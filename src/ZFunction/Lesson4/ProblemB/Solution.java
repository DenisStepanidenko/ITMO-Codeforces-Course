package ZFunction.Lesson4.ProblemB;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int q = input.nextInt();
        StringBuilder answer = new StringBuilder();
        while (q > 0) {
            String s = input.nextLine();
            String t = input.next();
            int k = solve(s, t);
            answer.append(k).append("\n");
            q--;
        }
        System.out.println(answer);
    }

    private static int solve(String s, String t) {
        String newS = t + "$" + s + s;
        return getEffectiveZFunction(newS, t.length());
    }

    public static int getEffectiveZFunction(String s, int length) {
        int r = 0;
        int l = 0;

        int[] zValue = new int[s.length()];
        zValue[0] = 0;
        for (int i = 1; i < s.length(); i++) {
            if (i <= r) {
                // нужно посмотреть на симметричное значение z[i-l]
                if (zValue[i - l] < r - i + 1) {
                    zValue[i] = zValue[i - l];
                } else {
                    zValue[i] = r - i + 1;
                    while (zValue[i] + i < s.length() && (s.charAt(zValue[i]) == s.charAt(zValue[i] + i))) {
                        zValue[i]++;
                    }
                }
            } else {
                // нужно посчитать наивно
                while (zValue[i] + i < s.length() && (s.charAt(zValue[i]) == s.charAt(zValue[i] + i))) {
                    zValue[i]++;
                }
            }

            // нужно пересчитать l и r
            if (r < i + zValue[i] - 1) {
                r = i + zValue[i] - 1;
                l = i;
            }
        }

        int answer = -1;

        for (int i = length + 1; i < zValue.length; i++) {
            if (zValue[i] == length) {
                answer = i - length - 1;
                break;
            }
        }

        return answer;

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
