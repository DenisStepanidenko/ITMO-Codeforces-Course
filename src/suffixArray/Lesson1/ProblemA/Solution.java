package suffixArray.Lesson1.ProblemA;


import java.io.*;
import java.util.*;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        String s = input.nextLine();
        s = s + "$";
        int n = s.length();
        // нужно построить суффиксный массив
        // алгоритм работает за O(n * logn^2)


        // сначала итерация k = 0
        // ----------------------
        List<PairSymbol> firstIteration = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            firstIteration.add(new PairSymbol(s.charAt(i), i));
        }

        Collections.sort(firstIteration); // сортировка строки из одного символа
        int[] p = new int[n]; // это и будет суффиксный массив
        int[] c = new int[n]; // это будет массив, где будут лежать классы эквивалентности
        for (int i = 0; i < n; i++) {
            p[i] = firstIteration.get(i).position;
        }

        c[p[0]] = 0;
        for (int i = 1; i < firstIteration.size(); i++) {
            if (firstIteration.get(i).symbol != firstIteration.get(i - 1).symbol) {
                c[p[i]] = c[p[i - 1]] + 1;
            } else {
                c[p[i]] = c[p[i - 1]];
            }
        }
        //-------------------
        int k = 0; // 2^k строк сейчас уже отсортировано
        // теперь переход от k -> k+1
        while ((1 << k) < n) {
            List<PairOfPair> pairList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                pairList.add(new PairOfPair(new PairOfTwoNumber(c[i], c[((i + (1 << k)) % n)]), i));
            }

            Collections.sort(pairList);


            // немного переписали суффиксный массив
            for (int i = 0; i < pairList.size(); i++) {
                p[i] = pairList.get(i).position;
            }

            // обновим класс эквивалентностей
            c[p[0]] = 0;
            for (int i = 1; i < pairList.size(); i++) {
                if (pairList.get(i).compareTo(pairList.get(i - 1)) == 0) {
                    c[p[i]] = c[p[i - 1]];

                } else {
                    c[p[i]] = c[p[i - 1]] + 1;
                }
            }

            k++;
        }
        StringBuilder answer = new StringBuilder();
        for (int i : p) {
            answer.append(i).append(" ");
        }
        System.out.println(answer);
    }

    static class PairOfTwoNumber {
        public int firstHalf;
        public int secondHalf;

        public PairOfTwoNumber(int firstHalf, int secondHalf) {
            this.firstHalf = firstHalf;
            this.secondHalf = secondHalf;
        }
    }

    static class PairOfPair implements Comparable<PairOfPair> {
        public PairOfTwoNumber pair;
        public int position;

        public PairOfPair(PairOfTwoNumber pair, int position) {
            this.pair = pair;
            this.position = position;
        }

        @Override
        public int compareTo(PairOfPair o) {
            if (pair.firstHalf < o.pair.firstHalf) {
                return -1;
            } else if (pair.firstHalf == o.pair.firstHalf) {
                if (pair.secondHalf < o.pair.secondHalf) {
                    return -1;
                } else if (pair.secondHalf == o.pair.secondHalf) {
                    return 0;
                }
                return 1;
            } else {
                return 1;
            }
        }
    }

    static class PairSymbol implements Comparable<PairSymbol> {
        public char symbol;
        public int position;

        public PairSymbol(char symbol, int position) {
            this.symbol = symbol;
            this.position = position;
        }

        @Override
        public int compareTo(PairSymbol o) {
            if (symbol < o.symbol) {
                return -1;
            } else if (symbol > o.symbol) {
                return 1;
            }
            return 0;
        }
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

