package ZFunction.Lesson2.Problem2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        // заранее сгенерируем все коды
        long before = System.currentTimeMillis();
        Map<Integer, String> allKods = new HashMap<>();
        //StringBuilder currentString = new StringBuilder();
        Map<Integer, String[]> result = new HashMap<>();
        allKods.put(1, "0");
        allKods.put(2, "0 1");
        //long timeBefore = System.currentTimeMillis();
        result.put(2, new String[]{"0", "1"});
        for (int k = 3; k <= 26; k++) {
            int currentNumber = (int) (Math.pow(2, k - 1) - 1);
            String newCod = allKods.get(k - 1) + " " + "0" + " " + currentNumber + " " + allKods.get(k - 1);
            allKods.put(k, newCod);
            result.put(k, newCod.split(" "));
        }
        long after = System.currentTimeMillis();
        System.out.println(after - before);
        //int t = input.nextInt();
//        StringBuilder answer = new StringBuilder();
//        for (int i = 0; i < t; i++) {
//            int k = input.nextInt();
//            int j = input.nextInt();
//            if (k == 1) {
//                answer.append(0).append("\n");
//            } else {
//                if (j == 0) {
//                    answer.append(0).append("\n");
//                } else {
//                    j--;
//                    String[] temp = allKods.get(k).split(" ");
//                    answer.append(temp[j]).append("\n");
//                }
//            }
//        }
//        System.out.println(answer);
//
    }

    private static int solve(int k, int j) {
        Map<Integer, String> stringsOfGrey = new HashMap<>();
        stringsOfGrey.put(1, "a");

        for (int i = 2; i <= k; i++) {

            String current = stringsOfGrey.get(i - 1) +
                    (char) ('a' + i - 1) +
                    stringsOfGrey.get(i - 1);
            stringsOfGrey.put(i, current);
        }
        return getZFunction(stringsOfGrey.get(k), j, k);
    }

    public static int getZFunction(String s, int j, int count) {
        if (s.charAt(j) != 'a') {
            return 0;
        } else if (j == ((Math.pow(2, count) - 2) / 2)) {
            return j;
        }
        int max = 0;
        for (int i = j; i < s.length(); i++) {
            // сейчас нам нужно найти наидлинейший общий префикс строки s и суффикса s[i,...,s.length-1]
            int k = 0;
            int suffexIndex = i;
            while (suffexIndex <= s.length() - 1 && (s.charAt(k) == s.charAt(suffexIndex))) {
                max++;
                k++;
                suffexIndex++;
            }
            break;
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
