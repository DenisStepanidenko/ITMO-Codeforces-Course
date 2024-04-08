package ZFunction.Lesson2.Problem1;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        String s = input.nextLine();
        StringBuilder answer = getZFunction(s);
        System.out.println(answer);

    }


    // тут неэффективный алгоритм
    public static StringBuilder getZFunction(String s) {
        int[] zValueOfFunction = new int[s.length()];
        zValueOfFunction[0] = 0; // по определению
        for (int i = 1; i < s.length(); i++) {
            // сейчас нам нужно найти наидлинейший общий префикс строки s и суффикса s[i,...,s.length-1]
            int max = 0;
            int j = 0;
            int suffexIndex = i;
            while (suffexIndex <= s.length() - 1 && (s.charAt(j) == s.charAt(suffexIndex))) {
                max++;
                j++;
                suffexIndex++;
            }
            zValueOfFunction[i] = max;
        }

        StringBuilder answer = new StringBuilder();
        for (int x : zValueOfFunction) {
            answer.append(x).append(" ");
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
