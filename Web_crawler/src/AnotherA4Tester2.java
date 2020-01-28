import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * A modified tester for 2019 Fall's COMP 250 A4
 *
 * This code is distributed in the hope that it will be useful, but without any warranty. So please
 * do not take this test seriously. You are good to go if you passed Tester.
 *
 * @author Zhangyuan Nie, with modification by Mike Gao and Jacob Tian
 * @version 1e
 */
public class AnotherA4Tester2 {
    public static class Tester {
        /* Switch this to true to enable color on your terminal */
        static final boolean forceEnableColor = true;

        private static String RESET = "\u001B[0m";
        private static String RED = "\u001B[31m";
        private static String GREEN = "\u001B[32m";
        private static String YELLOW = "\u001B[33m";
        private static String BLUE = "\u001B[34m";
        private static String MAGENTA = "\u001B[35m";
        private static String CYAN = "\u001B[36m";

        ArrayList<Integer> notOks = new ArrayList<Integer>();

        int total = 0;
        int ok = 0;

        int bench = 0;
        int s = 0;
        int a = 0;
        int b = 0;
        int d = 0;

        void run(char t) {
            run(t, false);
        }

        /**
         * A method that is manually obfuscated with love UwU
         */
        int run(char t, boolean o) {
            return !o & ++total > bench ? t == 'e' ? notOks.add(total) ? t : d : ++ok
                : t == 's' & bench++ <= total ? ++s + ++ok
                : t == 'a' ? ++a + ++ok
                : t < 'c' & notOks.add(total) ? ++b + ++ok
                : t > 'b' && t <= 'd' ? ++d
                : s;
        }

        public void printResults() {
            start("RESULTS");
            info("There are maybe like " + total + " test cases that run.");
            if (total == ok) {
                info(GREEN + "They all passed!  " + RESET + rainbow("CONGRATULATION!"));
            } else {
                info(RED + "However, " + (total - ok) + " of them failed." + RESET);
            }
            if (bench > 0) {
                info();
                info(bench + " of those tests check the speed.");
                if (s > 0) {
                    info("  " + s + " of them seems excellent");
                }
                if (a > 0) {
                    info("  " + a + " of them seems great");
                }
                if (b > 0) {
                    info("  " + b + " of them seems good");
                }
                if (d > 0) {
                    info("  " + d + " of them may be a little bit slow");
                }
                if (bench - s - a - b - d > 0) {
                    info("  " + (bench - s - a - b - d) + " of them simply failed");
                }
                info("See this part as a joke, since the measuring is far from scientific");
            }
            end("END OF TESTS");
        }

        public static void disableColor() {
            RESET = RED = GREEN = YELLOW = BLUE = MAGENTA = CYAN = "";
        }

        /**
         * Make your string rainbow-colored with 8bit ANSI codes.
         */
        public static String rainbow(String str) {
            String[] colors = {RED, YELLOW, GREEN, CYAN, BLUE, MAGENTA};
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (char c : str.toCharArray()) {
                sb.append(colors[i]).append(c);
                if (++i == colors.length) {
                    i = 0;
                }
            }
            sb.append(RESET);
            return sb.toString();
        }

        public static void info() {
            System.out.println("┃ ");
        }

        public static void info(String str) {
            System.out.println("┃ " + str);
        }

        public static void info(String str, Object... args) {
            System.out.println("┃ " + String.format(str, args));
        }

        public static void start(String str) {
            System.out.println("┏━━━━━ " + str);
        }

        public static void end(String str) {
            System.out.println("┗━━━━━ " + str + '\n');
        }

        public static char end(boolean cond) {
            if (cond) {
                goodEnd("Passed");
                return 'a';
            }
            badEnd("Failed");
            return 'e';

        }

        public static void badEnd(String str) {
            System.out.println("┗" + RED + "████" + RESET + "━ " + str + '\n');
        }

        public static void warnEnd(String str) {
            System.out.println("┗" + YELLOW + "████" + RESET + "━ " + str + '\n');
        }

        public static void goodEnd(String str) {
            System.out.println("┗" + GREEN + "████" + RESET + "━ " + str + '\n');
        }
    }

    /**
     * That's bad, but it works lol
     */
    static class WrapLong {
        long l = -1;
    }

    static HashMap<Integer, Integer> genUnsortedMap(Random random, int size) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int j = 0; j < size; j++) {
            int val = j + random.nextInt((int) (size - j));
            map.put(val, val);
        }
        return map;
    }

    static char testSort(int size, WrapLong ref, int sRef, int aRef) {
        Tester.start("test fastSort with a map of size: " + size);
        HashMap<Integer, Integer> map = genUnsortedMap(new Random(3), size);
        if (ref.l < 0) {
            long startTime = System.nanoTime();
            Sorting.slowSort(map);
            ref.l = System.nanoTime() - startTime;
        }
        long startTime = System.nanoTime();
        try {
            Sorting.fastSort(map);
        } catch (Exception e) {
            e.printStackTrace();
            Tester.badEnd("Exception with fastSort");
            return 'e';
        }
        long ratio = ref.l / (System.nanoTime() - startTime);
        Tester.info(ratio + "x faster than slowSort");
        char res = ratio > sRef ? 's' : ratio > aRef ? 'a' : ratio > 10 ? 'b' : 'd';
        switch (res) {
            case 's':
            case 'a':
                Tester.goodEnd("Nice!");
                return res;
            case 'b':
                Tester.warnEnd("Good enough");
                return res;
            default:
                Tester.badEnd("It's too slow");
                return res;
        }

    }

    static char testRank(String file, String startPt, double eps) {
        Tester.start("Test rank with " + file);
        long startTime = System.nanoTime();

        SearchEngine searchEngine;
        try {
            searchEngine = new SearchEngine(file);
        } catch (Exception e) {
            Tester.badEnd(file + " not found, skipped");
            return 'e';
        }
        try {
            searchEngine.crawlAndIndex(startPt);
            System.out.println("Finished with crawl and index");
        } catch (Exception e) {
            e.printStackTrace();
            Tester.badEnd("Exception with crawlAndIndex");
            return 'e';
        }
        try {
            searchEngine.assignPageRanks(eps);
        } catch (Exception e) {
            e.printStackTrace();
            Tester.badEnd("Exception with assignPageRanks");
            return 'e';
        }
        boolean passed = true;
        for (String url : searchEngine.internet.getVertices()) {
            // Jacob: Round both values so this tester work with a wider range of datasets.
            double ref = searchEngine.parser.getPageRank(url);
            double mine = searchEngine.internet.getPageRank(url);
            if (Math.abs(ref - mine) > eps) {
                Tester.info("Rank not right for %s", url);
                Tester.info("  refs:  " + ref);
                Tester.info("  yours: " + mine);
                passed = false;
            }
        }
        long endTime = System.nanoTime();
        long timeTaken = endTime - startTime;
        // Jacob: print the time spent on this test.
        System.out.format("Time taken: approximately %f s.\n", timeTaken / 1e9);
        return Tester.end(passed);
    }

    public static void main(String[] args) throws Exception {
        if (!Tester.forceEnableColor
                && (System.console() == null || System.getenv().get("TERM") == null)) {
            Tester.disableColor();
        }
        System.out.println(" ╔═════════════════════════════╗");
        System.out.println(" ║                             ║░");
        System.out.println(" ║       " + Tester.rainbow("AnotherA4Tester") + "       ║░");
        System.out.println(" ║                             ║░");
        System.out.println(" ╚═════════════════════════════╝░");
        System.out.println("  ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
        System.out.println();

        Tester tester = new Tester(); // YEs, tHe TesTEr iS MadE Of TEsTeR

        tester.run(testRank("testAcyclic.xml", "siteA", 0.01));
        tester.run(testRank("testPdf.xml", "A", 0.01));
        tester.run(testRank("test.xml", "www.cs.mcgill.ca", 0.001));



        // warm up , let JIT kicks in
        Sorting.fastSort(genUnsortedMap(new Random(1), 2000));

        tester.run(testSort(1000, new WrapLong(), 100, 30), true);
        tester.run(testSort(10000, new WrapLong(), 150, 50), true);
        WrapLong wl = new WrapLong();
        tester.run(testSort(20000, wl, 500, 300), true);
        wl.l *= 60;
        tester.run(testSort(100000, wl, 4800, 3000), true);

        tester.printResults();

        System.out.println("==Mike's Extended Crawl and Index Test==");
        SearchEngine search = new SearchEngine("test.xml");
        search.crawlAndIndex("www.cs.mcgill.ca");
        search.assignPageRanks(0.001);
        ArrayList<String> all = search.wordIndex.get("is");
        ArrayList<String> out = search.getResults("is");
        ArrayList<String> ans = new ArrayList<>();
        ans.add("www.ea.com");
        ans.add("www.cs.mcgill.ca");
        ans.add("www.ubisoft.com");
        ans.add("www.eidos.com");
        ans.add("www.unreal.com");
        System.out.println("All Item: " + all);
        System.out.println("Your Sorted search result: " + out);
        System.out.println("Correct Result: " + ans);
        System.out.println("Your result is: " + (ans.equals(out) ? "Correct" : "Incorrect"));
        System.out.println(all.size() != out.size() ? "Amount of entries not correct, Check for duplicates and/or missing entries in CrawlAndIndex()" : "========================================");

        System.out.println("\n==Testing your search engine against 10,000-node dataset(s) from Wikipedia!==");
        System.out.println("Special thanks to Zoe and Ryan for testing the PageRanks in these dataset(s).");
        System.out.println("Keep in mind that there might well be mistakes in these PageRanks.\n");

        // Jacob: 10,000-node datasets from Wikipedia!
        // Each one of them contain around 100,000 words (might include plenty of duplicates.)
        // Special thanks to the Soup-and-Bagel Task Force (Zoe W., Ryan S., and Jacob T.) for supplying the pageRanks.
        // Of course these values might well be off by a few digits. So don't be alerted!
        tester.run(testRank("Wikipedia_10000_element_bfs-with-pageRank.xml", "https://en.m.wikipedia.org/wiki/James_McGill", 0.005));

        // Uncomment the following line to crash your recursion-based method!
        // This datasheet is generated with depth-first search and is way deeper (and hence way more thrilling) than its bfs counterpart.
        //tester.run(testRank("Wikipedia_10000_element_dfs-with-pageRank.xml", "https://en.m.wikipedia.org/wiki/James_McGill", 0.005));
    }

}
