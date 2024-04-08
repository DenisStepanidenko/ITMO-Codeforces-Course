package ZFunction.Lesson3.Problem1;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        String text = input.nextLine();
        StringBuilder answer = getEffectiveZFunction(text);
        System.out.println(answer);
    }


    /**
     * Методы, который эффективно вычисляет z-функцию строки
     */
    public static StringBuilder getEffectiveZFunction(String s) {
        int r = 0;
        int l = 0;

        int[] zValue = new int[s.length()];
        zValue[0] = 0;
        StringBuilder answer = new StringBuilder();
        answer.append(0).append(" ");
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
            answer.append(zValue[i]).append(" ");
            // нужно пересчитать l и r
            if (r < i + zValue[i] - 1) {
                r = i + zValue[i] - 1;
                l = i;
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
