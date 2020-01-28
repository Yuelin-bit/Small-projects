import java.time.LocalTime;
import java.util.*;

/**
 * A modified tester for 2019 Fall's COMP 250 A3
 *
 * This code is distributed in the hope that it will be useful, but without any warranty. So please
 * do not take this test seriously. You are good to go if you passed Tester.
 *
 * @author Zhangyuan Nie
 * @version 2
 */
public class AnotherA3Tester {

    /**
     * Switch this to true to enable color if it is not
     */
    static final boolean forceEnableColor = false;

    private static class Print {
        private static String RESET = "\u001B[0m";
        private static String RED = "\u001B[31m";
        private static String GREEN = "\u001B[32m";
        private static String YELLOW = "\u001B[33m";
        private static String BLUE = "\u001B[34m";
        private static String MAGENTA = "\u001B[35m";
        private static String CYAN = "\u001B[36m";

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
                i++;
                if (i == colors.length) {
                    i = 0;
                }
            }
            sb.append(RESET);
            return sb.toString();
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

        public static void end(boolean cond) {
            if (cond) {
                goodEnd("Passed");
            } else {
                badEnd("Failed");
            }
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

    // uniformly spaced points
    public static ArrayList<Datum> makeDataPoints1D(int low, int high, int step) throws Exception {
        ArrayList<Datum> points = new ArrayList<Datum>();
        int[] x = new int[1];
        for (int x1 = low; x1 < high; x1 += step) {
            x[0] = x1;
            points.add(new Datum(x));
        }

        return points;
    }

    public static ArrayList<Datum> makeDataPoints2D_uniform(int width, int step) {
        ArrayList<Datum> points = new ArrayList<Datum>();
        int[] x = new int[2];
        for (int x1 = -width; x1 < width; x1 += step)
            for (int x2 = -width; x2 < width; x2 += step) {
                x[0] = x1;
                x[1] = x2;
                points.add(new Datum(x));
            }
        return points;
    }

    public static ArrayList<Datum> makeDataPoints2D_circle(double radius) {
        ArrayList<Datum> points = new ArrayList<Datum>();
        int[] x = new int[2];
        int bound = (int) radius + 1;
        double r;
        for (int x1 = -bound; x1 < bound; x1++)
            for (int x2 = -100; x2 < bound; x2++) {
                x[0] = x1;
                x[1] = x2;
                r = Math.sqrt(1.0 * x1 * x1 + x2 * x2);
                if ((r <= radius) && (r > radius - 1))
                    points.add(new Datum(x));
            }

        return points;
    }

    public static ArrayList<Datum> makeDataPoints3D_sphere(double radius) {
        ArrayList<Datum> points = new ArrayList<Datum>();
        int[] x = new int[3];
        int bound = (int) radius + 1;
        double distFromOrigin;
        for (int x1 = -bound; x1 < bound; x1++)
            for (int x2 = -bound; x2 < bound; x2++) {
                for (int x3 = -bound; x3 < bound; x3++) {
                    x[0] = x1;
                    x[1] = x2;
                    x[2] = x3;
                    distFromOrigin = Math.sqrt(1.0 * x1 * x1 + x2 * x2 + x3 * x3);
                    if ((distFromOrigin <= radius) && (distFromOrigin > radius - 1))
                        points.add(new Datum(x));
                }
            }
        return points;
    }

    private static ArrayList<Datum> makeDataPointsRandom(int[] lows, int[] highs, int numpoints,
            long seed, boolean allow_duplicates) throws Exception {
        if (lows.length != highs.length) {
            throw new Exception();
        }

        Collection<Datum> points;
        if (allow_duplicates) {
            points = new ArrayList<>();
        } else {
            points = new HashSet<>();
        }
        Random r = new Random();
        for (int n = 0; n < numpoints; n++) {
            int[] x = new int[lows.length];
            for (int i = 0; i < lows.length; i++) {
                x[i] = r.nextInt((highs[i] - lows[i])) + lows[i];
            }
            points.add(new Datum(x));
        }
        return new ArrayList<Datum>(points);
    }

    public static long minDistSquaredBruteForce(ArrayList<Datum> points, Datum query) {
        long tmpDistSqr, minDistSqr = ((long) Integer.MAX_VALUE) * Integer.MAX_VALUE;
        for (Datum d : points) {
            tmpDistSqr = KDTree.distSquared(query, d);
            if (tmpDistSqr < minDistSqr) {
                minDistSqr = tmpDistSqr;
            }
        }
        return minDistSqr;
    }

    public static long[] testQuery(int[] q, ArrayList<Datum> points, KDTree tree) {
        Datum query = new Datum(q);

        long myStart = System.nanoTime();
        Datum nearestPoint = tree.nearestPoint(query);
        long myEnd = System.nanoTime();

        if (nearestPoint == null) {
            Print.info("nearestPoint is not implemented yet");
            return null;
        }
        long myRes = KDTree.distSquared(query, nearestPoint);

        long refStart = System.nanoTime();
        long refRes = minDistSquaredBruteForce(points, query);
        long refEnd = System.nanoTime();

        if (myRes == refRes) {
            long[] res = {myEnd - myStart, refEnd - refStart};
            return res;
        } else {
            Print.info("Query point is " + query);
            Print.info("Nearest data point is " + nearestPoint);
            Print.info("Minimum squared distance to nearest point is " + myRes);
            Print.info("Minimum squared distance to nearest point is " + refRes + " (brute force)");
            return null;
        }
    }

    public static boolean testNearest(int[][] qList, ArrayList<Datum> points, KDTree tree) {
        Print.start("Nearest point test");
        long myTotalTime = 0;
        long refTotalTime = 0;
        for (int[] q : qList) {
            long[] res = testQuery(q, points, tree);
            if (res == null) {
                Print.badEnd("Failed");
                return false;
            }
            myTotalTime += res[0];
            refTotalTime += res[1];
        }
        if (myTotalTime < refTotalTime) {
            Print.info("Your method is %fx faster than brute force",
                    (double) refTotalTime / myTotalTime);
            Print.goodEnd("Passed");
            return true;
        }
        Print.info("Your method is %fx slower than brute force",
                (double) myTotalTime / refTotalTime);
        Print.warnEnd("May be good enough");
        return true;
    }

    public static void ending() {
        if (LocalTime.now().compareTo(LocalTime.of(5, 0, 0)) < 0) {
            System.out.println(
                    "It's a bit late now, maybe you want to go to bed and try again tomorrow? ");
            System.out.println("A full night of sleep can be really helpful sometimes! ~UwU~");
            System.out.println("Working hard is good, but don't forget to take care of yourself!");
        } else {
            System.out.println("End of test cases");
        }
    }

    public static boolean testNode(KDTree.KDNode root) {
        if (root.leaf) {
            if (root.leafDatum == null) {
                Print.info("some leaves do not have a datum");
                return false;
            }
            if (root.lowChild != null || root.highChild != null) {
                Print.info("some leaves do have child");
                return false;
            }
            return true;
        }
        if (root.lowChild == null || root.highChild == null) {
            Print.info("some internal nodes do not have child");
            return false;
        }
        if (root.leafDatum != null) {
            Print.info("some internal nodes do have a datum");
            return false;
        }

        return testNode(root.lowChild) && testNode(root.highChild);
    }

    public static KDTree testConstructor(ArrayList<Datum> points) {
        return testConstructor(points, 0);
    }

    public static KDTree testConstructor(ArrayList<Datum> points, int expectedLeaves) {
        Print.start("Tree constructor test");
        KDTree tree;
        try {
            tree = new KDTree(points);
        } catch (Exception e) {
            e.printStackTrace();
            Print.badEnd("Failed to construct the tree");
            return null;
        }
        int myHeight = tree.height();
        int refHeight = (int) Math.ceil(Math.log(tree.size()) / Math.log(2));
        int myNbNode = tree.countNodes();
        int refNbNode = (expectedLeaves == 0 ? tree.size() : expectedLeaves) * 2 - 1;

        Print.info("A %dd tree with %d nodes of height %d is created", tree.k, myNbNode, myHeight);
        Print.info("Mean depth is " + tree.meanDepth());

        if (tree.size() < 1) {
            Print.badEnd("Your tree is bald, add some leaves");
        } else if (myNbNode != refNbNode) {
            Print.info("Number of leaves in tree is " + tree.size());
            Print.info("Number of nodes in tree is " + myNbNode);
            Print.info("Number of nodes in tree should be " + refNbNode);
            Print.badEnd("Your tree seems to be infested with bugs");
        } else if (!testNode(tree.rootNode)) {
            Print.badEnd("Some nodes of your tree are a bit off");
        } else if (myHeight > refHeight) {
            Print.info("For reference, the height of a tree with %d leaves should be around %.3f",
                    tree.size(), Math.log(tree.size()) / Math.log(2));
            Print.warnEnd("Your tree may not be in its best shape");
        } else {
            Print.goodEnd("Your tree seems to be okay");
        }
        return tree;
    }

    public static void testIterator(KDTree tree, ArrayList<Datum> points) {
        Iterator<Datum> it = tree.iterator();
        int[] raw_points = new int[points.size()];
        int[] raw_points_set = new int[tree.size()];

        Print.start("Iterator test");

        if (points.get(0).x.length == 1) {
            for (int i = 0; i < points.size(); i++)
                raw_points[i] = points.get(i).x[0];
            // sorting the array
            Arrays.sort(raw_points);
            // removing duplicates
            raw_points_set[0] = raw_points[0];
            int j = 0;
            for (int i = 1; i < raw_points.length; i++) {
                if (raw_points_set[j] != raw_points[i]) {
                    j++;
                    raw_points_set[j] = raw_points[i];
                }
            }
        }

        int ct = 0;
        boolean ordering_flag = true;
        while (it.hasNext()) {
            int iterval = it.next().x[0];
            // System.out.println(iterval+" - "+raw_points_set[ct]);
            if (raw_points_set[ct] != iterval)
                ordering_flag = false;
            ct++;
        }

        Print.info("Size is " + tree.size() + " & iterator count is " + ct);

        if (points.get(0).x.length == 1) {
            if (ordering_flag)
                Print.goodEnd(
                        "The ordering of the datapoints encountered by the iterator is CORRECT. ");
            else
                Print.badEnd(
                        "The ordering of the datapoints encountered by the iterator is INCORRECT.");

        } else {
            Print.end("ordering not checked for k > 1");
        }

    }

    public static void testCase(ArrayList<Datum> points, int[][] qList) {
        KDTree tree = testConstructor(points);
        if (tree == null) {
            return;
        }
        testNearest(qList, points, tree);
        testIterator(tree, points);
    }

    /**
     * Easier way to create a new datum
     */
    static Datum dat(int... nb) {
        return new Datum(nb);
    }

    public static void runAdditionalTests() {
        System.out.println("\n### Additional tests, you can ignore those\n");
        ArrayList<Datum> points = new ArrayList<Datum>();
        KDTree tree;

        points.add(dat(0, 0, 0));
        testConstructor(points, 1);

        points.clear();
        for (int i = 0; i < 10; i++) {
            points.add(dat(0, 0, 0));
        }
        points.add(dat(-1, -1, -1));
        testConstructor(points, 2);

        points.clear();
        points.add(dat(2, 3, 1));
        points.add(dat(-1, 0, 9));
        points.add(dat(2, 3, 1));
        points.add(dat(-1, 0, 9));
        points.add(dat(0, -2, 3));
        points.add(dat(0, -2, 2));
        points.add(dat(-1, 0, 9));
        points.add(dat(0, -2, 2));
        points.add(dat(-1, -1, 9));
        points.add(dat(0, -2, 2));
        tree = testConstructor(points, 5);


        Print.start("Additional check for previous tree's constructor");
        Print.end(tree.rootNode.highChild.highChild.leafDatum.equals(dat(-1, 0, 9))
                && tree.rootNode.highChild.lowChild.leafDatum.equals(dat(-1, -1, 9))
                && tree.rootNode.lowChild.lowChild.lowChild.leafDatum.equals(dat(0, -2, 2))
                && tree.rootNode.lowChild.lowChild.highChild.leafDatum.equals(dat(0, -2, 3))
                && tree.rootNode.lowChild.highChild.leafDatum.equals(dat(2, 3, 1))
                && tree.rootNode.splitDim == 2 && tree.rootNode.highChild.splitDim == 1
                && tree.rootNode.lowChild.splitDim == 1
                && tree.rootNode.lowChild.lowChild.splitDim == 2);

        Print.start("Additional check for previous tree's iterator");

        Datum[] expected =
                {dat(0, -2, 2), dat(0, -2, 3), dat(2, 3, 1), dat(-1, -1, 9), dat(-1, 0, 9)};
        int i = 0;
        for (Datum datum : tree) {
            if (!datum.equals(expected[i])) {
                break;
            }
            i++;
        }
        Print.end(i == expected.length);
    }

    public static void main(String args[]) throws Exception {
        if (!forceEnableColor
                && (System.console() == null || System.getenv().get("TERM") == null)) {
            Print.disableColor();
        }
        System.out.println(" ╔═════════════════════════════╗");
        System.out.println(" ║                             ║░");
        System.out.println(" ║       " + Print.rainbow("AnotherA3Tester") + "       ║░");
        System.out.println(" ║                             ║░");
        System.out.println(" ╚═════════════════════════════╝░");
        System.out.println("  ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
        System.out.println();

        ArrayList<Datum> points;
        boolean allow_duplicates = true;

        // k = 1

        int[][] qList1 = {{34}, {36}, {42}, {45}, {49}, {120}, {121}, {125}, {124}, {126}, {128}};

        System.out.println("\n#### Test case for k = 1 op = UNIFORM\n");
        points = makeDataPoints1D(-20000, 20000, 10);
        testCase(points, qList1);

        System.out.println("\n#### Test case for k = 1 op = RANDOM\n");
        int[] lows = {-20000};
        int[] highs = {20000};
        points = makeDataPointsRandom(lows, highs, 150000, 0, allow_duplicates);
        testCase(points, qList1);

        // k = 2

        int[][] qList2 =
                {{-2, 0}, {234, 536}, {4442, 2451}, {-49, 120}, {121, 125}, {-5, 0}, {-5, -5}};

        System.out.println("\n#### Test case for k = 2 op = UNIFORM\n");
        points = makeDataPoints2D_uniform(1000, 10);
        testCase(points, qList2);

        System.out.println("\n#### Test case for k = 2 op = SHAPE\n");
        points = makeDataPoints2D_circle(30);
        testCase(points, qList2);

        System.out.println("\n#### Test case for k = 2 op = RANDOM\n");
        int[] lows2 = {-1000, -1000};
        int[] highs2 = {1000, 1000};
        points = makeDataPointsRandom(lows2, highs2, 35000, 1, allow_duplicates);
        testCase(points, qList2);

        // k = 3

        ArrayList<int[]> qArrList3 = new ArrayList<int[]>();
        for (int x1 = -1; x1 <= 1; x1++) { // make a unit 3x3 cube of query points
            for (int x2 = -1; x2 <= 1; x2++) {
                for (int x3 = -1; x3 <= 1; x3++) {
                    int[] e = {x1, x2, x3};
                    qArrList3.add(e);
                }
            }
        }
        int[][] qList3 = qArrList3.toArray(new int[27][]);

        System.out.println("\n#### Test case for k = 3 op = SHAPE\n");
        points = makeDataPoints3D_sphere(50);
        testCase(points, qList3);

        System.out.println("\n#### Test case for k = 3 op = RANDOM\n");
        int[] lows3 = {-2, -2, -2};
        int[] highs3 = {2, 2, 2};
        points = makeDataPointsRandom(lows3, highs3, 10, 2, allow_duplicates);
        testCase(points, qList3);

        runAdditionalTests();

        ending();
    }
}