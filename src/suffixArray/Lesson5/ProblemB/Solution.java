package suffixArray.Lesson5.ProblemB;


import java.io.*;
import java.util.*;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        String s = input.nextLine();
        String t = input.nextLine();
        String sNew = s + "5" + t;
        List<int[]> suffixMassiveAndLsp = getSuffixMassive(sNew);
        int[] suffixArray = suffixMassiveAndLsp.get(0);
        int[] lcp = suffixMassiveAndLsp.get(1);

        String maxStr = "";
        int maxLen = Integer.MIN_VALUE;

        for (int i = 2; i < suffixArray.length; i++) {
            // теперь нужно понять, что данные суффиксы относятся к разным классам
            int startFirst = suffixArray[i];
            int startSecond = suffixArray[i - 1];

            if ((startFirst < s.length() && startSecond > s.length()) || (startSecond < s.length() && startFirst > s.length())) {
                int lcpValue = lcp[i];
                if(lcpValue > maxLen){
                    maxLen = lcpValue;
                    maxStr = sNew.substring(startFirst , startFirst + lcpValue);
                }
            }
        }

        System.out.println(maxStr);

    }


    private static List<int[]> getSuffixMassive(String s) {
        s = s + "$";
        int n = s.length();
        // сначала итерация k = 0 - сортируем строки из одного символа
        List<PairOfSymbol> firstIteration = new ArrayList<>();

        // заполняем строки из одного символа
        for (int i = 0; i < n; i++) {
            firstIteration.add(new PairOfSymbol(s.charAt(i), i));
        }

        Collections.sort(firstIteration);

        int[] p = new int[n]; // суффиксный массив (храним индексы)
        int[] c = new int[n]; // массив классов эквивалентностей
        for (int i = 0; i < n; i++) {
            p[i] = firstIteration.get(i).position;
        }
        c[p[0]] = 0;
        for (int i = 1; i < n; i++) {
            if (firstIteration.get(i).symbol == firstIteration.get(i - 1).symbol) {
                c[p[i]] = c[p[i - 1]];
            } else {
                c[p[i]] = c[p[i - 1]] + 1;
            }
        }
        // итерация k = 0 завершена

        int k = 0; // строки длины 2^k мы уже отсортировали

        while ((1 << k) < n) {
            // теперь сдвинем все на 2^k назад
            for (int i = 0; i < n; i++) {
                p[i] = (p[i] - (1 << k) + n) % n; // прибавляем n, так как отрицательные числа плохо берутся по модулю
            }

            // теперь по факту у нас строки отсортированы по второй половине, нужно сортировать по первой
            // напишем сортировку подсчётом для первой половины строк
            p = count_sort(p, c);
            // теперь нужно написать перерасчёт классов эквивалентности
            int[] cNew = new int[n];
            cNew[p[0]] = 0;
            for (int i = 1; i < n; i++) {
                Pair prev = new Pair(c[p[i - 1]], c[(p[i - 1] + (1 << k)) % n]);
                Pair now = new Pair(c[p[i]], c[(p[i] + (1 << k)) % n]);

                if (now.compareTo(prev) == 0) {
                    cNew[p[i]] = cNew[p[i - 1]];
                } else {
                    cNew[p[i]] = cNew[p[i - 1]] + 1;
                }
            }
            c = cNew;
            k++;
        }

        List<int[]> answer = new ArrayList<>();
        answer.add(p);

        // построим массив lcp

        int[] lcp = new int[n];
        k = 0; // столько символов совпало у пары суффиксов (i,j)

        for (int i = 0; i < n - 1; i++) {
            // нужно узнать номер суффикса s[i...n-1] в массиве p
            int pi = c[i];
            int j = p[pi - 1];

            // теперь нужно найти lcp[i] = lcp(s[i...n-1] , s[j...n-1])

            while (s.charAt(i + k) == s.charAt(j + k)) {
                k++;
            }
            lcp[pi] = k;
            k = Math.max(k - 1, 0);
        }

        answer.add(lcp);
        return answer;
    }

    private static int[] count_sort(int[] p, int[] c) {
        // сортировка подсчётом
        int n = p.length;
        int[] counter = new int[n];

        for (int x : c) {
            counter[x]++;
        }

        int[] pNew = new int[n]; // это будет новый суффиксный массив
        int[] position = new int[n]; // это будет массив, чтобы понять в какой место вставлять текущий класс эквивалентостней

        for (int i = 1; i < n; i++) {
            position[i] = position[i - 1] + counter[i - 1];
        }

        for (int x : p) {
            int i = c[x];
            pNew[position[i]] = x;
            position[i]++;
        }

        return pNew;
    }


    static class PairOfSymbol implements Comparable<PairOfSymbol> {
        public Character symbol;
        public int position;

        public PairOfSymbol(Character symbol, int position) {
            this.symbol = symbol;
            this.position = position;
        }

        @Override
        public int compareTo(PairOfSymbol o) {
            if (symbol < o.symbol) {
                return -1;
            } else if (symbol > o.symbol) {
                return 1;
            }
            return 0;
        }
    }

    static class Pair implements Comparable<Pair> {
        public Integer first;
        public Integer second;

        public Pair(Integer first, Integer second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair o) {
            if (first < o.first) {
                return -1;
            } else if (first > o.first) {
                return 1;
            } else {
                if (second < o.second) {
                    return -1;
                } else if (second > o.second) {
                    return 1;
                }
                return 0;
            }
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


