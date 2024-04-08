package ZFunction.Lesson1.Problem4;

import java.io.*;
import java.util.*;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {


        int t = input.nextInt();
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String s = input.nextLine();
            String p = input.nextLine();

            int q = solve(s, p);
            answer.append(q).append("\n");
        }
        System.out.println(answer);
    }

    private static int solve(String s, String p) {

        int answer = (s.length() * (s.length() + 1)) / 2;

        Set<Pair> allPairs = new HashSet<>();
        // нужно найти вхождение строки p в t
        for (int i = 0; i < s.length(); i++) {
            // попробуем выделить строку p, начиная с элемента

            int j = i;
            int patternIndex = 0;
            boolean flag = false;
            while (s.charAt(j) == p.charAt(patternIndex)) {
                if (patternIndex == p.length() - 1) {
                    flag = true;
                    break;
                }
                j++;
                patternIndex++;
                if (j >= s.length()) {
                    break;
                }
            }


            if (flag) {
//                System.out.println(i);
//                System.out.println(j);
                int q = 1; // эта строка [j,patternIndex]

//                q += i;
//                q += (s.length() - 1) - j;
                // теперь проверим всех от i до j
                for (int m = 0; m < i; m++) {
                    // будем смотреть за подстроками [m , patternIndex]
                    Pair current = new Pair(m, j);
                    if (!allPairs.contains(current)) {
                        allPairs.add(current);
                        q++;
                    }
                }

                // теперь посмотрим на подстроки от j до patternIndex + i
                for (int m = j + 1; m < s.length(); m++) {
                    // будем смотреть за подстроками [j , m]
                    Pair current = new Pair(i, m);
                    if (!allPairs.contains(current)) {
                        allPairs.add(current);
                        q++;
                    }
                }

                // теперь нужно посчитать подстроки вида [j-i , patternIndex + i]
                for (int m = 0; m < i; m++) {
                    for (int k = j + 1; k < s.length(); k++) {
                        Pair current = new Pair(m, k);
                        if (!allPairs.contains(current)) {
                            allPairs.add(current);
                            q++;
                        }
                    }
                }
                answer -= q;
            }

        }


        return answer;
    }


    public static class Pair {
        int i; // начало подстроки
        int j; // конец подстроки

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public int getJ() {
            return j;
        }

        public void setJ(int j) {
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return i == pair.i && j == pair.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }

    private static List<String> generatedSubstrings(String s) {
        List<String> allSubstrings = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if ((i + j) < s.length()) {
                    StringBuilder currentSubstring = new StringBuilder();
                    for (int m = i; m <= i + j; m++) {
                        currentSubstring.append(s.charAt(m));
                    }

                    allSubstrings.add(currentSubstring.toString());
                }
            }
        }
        return allSubstrings;
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
