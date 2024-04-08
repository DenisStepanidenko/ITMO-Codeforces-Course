package ZFunction.Lesson1.Problem2;

import java.io.*;
import java.util.*;

public class Solution {
    static Reader input = new Reader();

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
        // сгенерировать все подстроки строки
        List<String> allSubstrings = new ArrayList<>();

        for (int i = 0; i < current.length(); i++) {
            for (int j = 0; j < current.length() - 1; j++) {
                if ((i + j) < current.length()) {
                    StringBuilder currentSubstring = new StringBuilder();
                    for (int m = i; m <= i + j; m++) {
                        currentSubstring.append(current.charAt(m));
                    }

                    allSubstrings.add(currentSubstring.toString());
                }
            }
        }

        // сгенерируем все префиксы
        List<String> allPrefix = new ArrayList<>();
        for (int i = 0; i < current.length() - 1; i++) {
            StringBuilder currentPrefix = new StringBuilder();
            for (int j = 0; j <= i; j++) {
                currentPrefix.append(current.charAt(j));
            }
            allPrefix.add(currentPrefix.toString());
        }


        // сгенерируем все суффиксы
        List<String> allSuffixes = new ArrayList<>();
        for (int i = 1; i <= current.length() - 1; i++) {
            StringBuilder currentPrefix = new StringBuilder();
            for (int j = i; j <= current.length() - 1; j++) {
                currentPrefix.append(current.charAt(j));
            }
            allSuffixes.add(currentPrefix.toString());
        }

        int answer = 0;
        for (String currentSubstring : allSubstrings) {
            if (allPrefix.contains(currentSubstring) && allSuffixes.contains(currentSubstring)) {
            } else if (allPrefix.contains(currentSubstring)) {
                answer++;
            } else if (allSuffixes.contains(currentSubstring)) {
                answer++;
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
