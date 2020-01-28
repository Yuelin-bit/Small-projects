//package assignments2019.a3;
import java.lang.reflect.Array;
import java.util.*;


public class TesterOld {

    private enum Option {
        UNIFORM,
        SHAPE,
        RANDOM,
    };

    //   uniformly spaced points
    public static ArrayList<Datum> makeDataPoints1D(int low, int high, int step) throws Exception{
        ArrayList<Datum> points = new ArrayList<Datum>();
        int[] x = new int[1];
        for (int x1=low; x1 < high; x1 += step) {
            x[0] = x1;
            points.add( new Datum(x));
        }

        return points;
    }

    public static ArrayList<Datum> makeDataPoints2D_uniform(int width, int step){
        ArrayList<Datum> points = new ArrayList<Datum>();
        int[] x = new int[2];
        for (int x1 = -width; x1 < width; x1 += step)
            for (int x2 = -width; x2 < width; x2 += step) {
                x[0] = x1;
                x[1] = x2;
                points.add( new Datum(x));
            }
        return points;
    }

    public static ArrayList<Datum> makeDataPoints2D_circle(double radius){
        ArrayList<Datum> points = new ArrayList<Datum>();
        int[] x = new int[2];
        int bound = (int) radius + 1;
        double r;
        for (int x1 = -bound; x1 < bound; x1 ++)
            for (int x2 = -100; x2 < bound; x2 ++) {
                x[0] = x1;
                x[1] = x2;
                r = Math.sqrt(1.0*x1*x1 + x2*x2);
                if (( r <= radius ) && (r > radius-1))
                    points.add( new Datum(x));
            }

        return points;
    }

    public static ArrayList<Datum> makeDataPoints3D_sphere(double radius){
        ArrayList<Datum> points = new ArrayList<Datum>();
        int[] x = new int[3];
        int bound = (int) radius + 1;
        double distFromOrigin;
        for (int x1= -bound; x1 < bound; x1 ++)
            for (int x2= -bound; x2 < bound; x2++) {
                for (int x3= -bound; x3 < bound; x3++) {
                    x[0] = x1;
                    x[1] = x2;
                    x[2] = x3;
                    distFromOrigin = Math.sqrt(1.0*x1*x1 + x2*x2 + x3*x3);
                    if (( distFromOrigin <= radius ) && (distFromOrigin > radius-1))
                        points.add( new Datum(x));
                }
            }
        return points;
    }

    private static ArrayList<Datum> makeDataPointsRandom(int[] lows, int[] highs, int numpoints, long seed, boolean allow_duplicates) throws Exception {
        if (lows.length != highs.length) {
            throw new Exception();
        }

        Collection<Datum> points;
        if (allow_duplicates) {
            points = new ArrayList<>();
        } else {
            points = new HashSet<>();
        }
        Random r = new Random(seed);
        for (int n = 0; n < numpoints; n++) {
            int[] x = new int[lows.length];
            for (int i = 0; i < lows.length; i++) {
                x[i] = r.nextInt((highs[i]-lows[i])) + lows[i];
            }
            points.add(new Datum(x));
        }
        return new ArrayList<Datum>(points);
    }

    public static long minDistSquaredBruteForce(ArrayList<Datum> points, Datum query) {
        long tmpDistSqr, minDistSqr = ((long) Integer.MAX_VALUE) * Integer.MAX_VALUE;
        Datum a = null;
        for (Datum d : points) {
            tmpDistSqr = KDTree.distSquared(query, d );
            if (tmpDistSqr < minDistSqr) {
                minDistSqr = tmpDistSqr;
                a=d;
            }
        }
        System.out.println(a);
        return minDistSqr;
    }

    public static boolean testQuery(int[] q, ArrayList<Datum> points,  KDTree tree) {
        Datum query = new Datum(q);
        Datum nearestPoint = tree.nearestPoint(query);
        System.out.println("\nQuery point is " + query);
        System.out.println("Nearest data point is " + nearestPoint);
        System.out.println("Minimum squared distance to nearest point is " + KDTree.distSquared(query, nearestPoint) );
        System.out.println("Minimum squared distance to nearest point is " + minDistSquaredBruteForce(points, query ) + " (brute force)");
        if (KDTree.distSquared(query, nearestPoint) == minDistSquaredBruteForce(points, query)) {
            System.out.println("Test passed.");
            return true;
        }
        else{
            System.out.println("Test failed.");
            return false;
        }
    }

    public static void testIterator(KDTree tree, ArrayList<Datum> points){
        Iterator<Datum> it = tree.iterator();
        int [] raw_points = new int[points.size()];
        int [] raw_points_set =  new int[tree.size()];
        if (points.get(0).x.length > 1) {
            System.out.println("Warning!  This Tester only checks for the validity of the order of the points of the iterator for 1D case.");
        }
        else {
            for (int i = 0; i < points.size(); i++)
                raw_points[i] = points.get(i).x[0];
            //sorting the array
            Arrays.sort(raw_points);
            //removing duplicates
            raw_points_set[0] = raw_points[0];
            int j = 0;
            for (int i = 1; i < raw_points.length ; i++) {
                if (raw_points_set[j] != raw_points[i]) {
                    j++;
                    raw_points_set[j] = raw_points[i];
                }

            }
        }
        int ct = 0;
        boolean ordering_flag=true;
        while (it.hasNext()) {
            int iterval = it.next().x[0];
            //System.out.println(iterval+" - "+raw_points_set[ct]);
            if(raw_points_set[ct] != iterval)
                ordering_flag=false;
            ct++;
        }
        System.out.println("Size is " + Integer.valueOf(tree.size()) + " & iterator count is " + Integer.valueOf(ct));

        if (points.get(0).x.length==1)
        {
            if(ordering_flag)
                System.out.println("The ordering of the datapoints encountered by the iterator is CORRECT. " +
                                   "\nFor a more robust check of the ordering keep the option RANDOM.");
            else
                System.out.println("The ordering of the datapoints encountered by the iterator is INCORRECT.");

        }

    }

    public static void printTreeParameters(KDTree tree) {
        System.out.println( "Height of tree is " + tree.height() );
        System.out.println( "Number of leaves in tree is " + tree.size() );
        System.out.println( "Number of nodes in tree is " + tree.countNodes());
    }

    public static void main(String args[]) throws Exception {
        int[] q;
        ArrayList<Datum> points = null;
        KDTree tree;

        // How many dimensional space?
        //Testing of the iterator is available only for 1D points.
        int numdimensions = 2; // Options: 1, 2, 3
        
        System.out.println("Test case for k = " + Integer.valueOf(numdimensions) + " dimensional case.  (You should try all of k=1,2,3)\n");

        // Data distribution?
        Option op = Option.SHAPE; // Options: Option.UNIFORM, Option.SHAPE, Option.RANDOM,
        
        // Allow duplicates? Hint, Hint...
        boolean allow_duplicates = true; // Options: true, false

        switch(numdimensions) { // dimension
            case 1: // Test for 1D points

                //  data points
                switch(op) {
                    case UNIFORM:
                        points = makeDataPoints1D(-20000, 20000, 10);
                        break;

                    case RANDOM:
                        int[] lows = {-20000};
                        int[] highs = {20000};
                        points = makeDataPointsRandom(lows, highs, 150000, 0, allow_duplicates);
                        break;

                    default:
                        return;
                }

                // construct tree
                tree = new KDTree(points);
                System.out.println("*****Testing for tree validity******");
                printTreeParameters(tree);
                System.out.println("Mean depth is " + Double.valueOf(tree.meanDepth()));
                System.out.print("For reference, the height of a tree with "+ tree.size() + " leaves in general should be around ");
                System.out.printf("%.3f",(Math.log(tree.size())/Math.log(2)));
                System.out.println("\n\nNote : For a valid tree, each internal node should have exactly 2 children with no datapoints stored in it and\n " +
                        "each leaf should have 0 child with exactly one datapoint stored in it. This check is not being done in the tester.");
                //  query points
                int[] qList_1 =  {34, 36, 42, 45, 49, 120, 121, 125, 124, 126, 128};

                // test query

                boolean pass = true;

                System.out.println("\n******Testing for the nearest point finder:*******");
                q = new int[1];
                for (int i = 0; i < qList_1.length; i++) {
                    q[0] = qList_1[i];
                    if (!testQuery(q, points, tree))
                        pass=false;
                }
                if (pass)
                    System.out.println("Passed nearestPointFinder().");
                else
                    System.out.println("Failed nearestPointFinder().");

                //testing the iterator
                System.out.println("\n******Testing for the iterator :******");
                testIterator(tree, points);
                break;

            case 2: // Test for 2D points

                // data points
                switch(op) {
                    case UNIFORM:
                        points = makeDataPoints2D_uniform(1000, 10);
                        break;

                    case SHAPE:
                        points = makeDataPoints2D_circle(30);
                        break;

                    case RANDOM:
                        int[] lows = {-1000, -1000};
                        int[] highs = {1000, 1000};
                        points = makeDataPointsRandom(lows, highs, 35000, 1, allow_duplicates);
                        break;

                    default:
                        return;
                }

                // construct tree
                tree = new KDTree(points);
                printTreeParameters(tree);
                System.out.println("Mean depth is " + Double.valueOf( tree.meanDepth() ) );
                System.out.print("For reference, the height of a tree with "+ tree.size() + " leaves in general should be around ");
                System.out.printf("%.3f",(Math.log(tree.size())/Math.log(2)));
                System.out.println("\n\nNote : For a valid tree, each internal node should have exactly 2 children with no datapoints stored in it and\n " +
                        "each leaf should have 0 child with exactly one datapoint stored in it. This check is not being done in the tester.");
                // query points
                int[] qList_2[] =  {{-2,0}, {234, 536}, {4442, 2451}, {-49, 120}, {121, 125}, {-5, 0}, {-5, -5}};

                // test query
                System.out.println("\nTesting for the nearest point finder:");
                q = new int[2];
                for (int i = 0; i < qList_2.length; i++) {
                    q[0] = qList_2[i][0];
                    q[1] = qList_2[i][1];
                    testQuery(q,points, tree);
                }

                //testing the iterator
                System.out.println("\nTesting for the iterator :");
                testIterator(tree, points);
                break;

            case 3: // 3D points

                // data points
                switch(op) {
                    case SHAPE:
                        points = makeDataPoints3D_sphere(50);
                        break;

                    case RANDOM:
                        int[] lows = {-2, -2, -2};
                        int[] highs = {2, 2, 2};
                        points = makeDataPointsRandom(lows, highs, 10, 2, allow_duplicates);
                        break;

                    default:
                        return;
                }

                // construct tree
                tree = new KDTree(points);
                printTreeParameters(tree);
                System.out.println("Mean depth is " + Double.valueOf(tree.meanDepth()));
                System.out.print("For reference, the height of a tree with "+ tree.size() + " leaves in general should be around ");
                System.out.printf("%.3f", (Math.log(tree.size())/Math.log(2)));
                System.out.println("\n\nNote : For a valid tree, each internal node should have exactly 2 children with no datapoints stored in it and\n " +
                        "each leaf should have 0 child with exactly one datapoint stored in it. This check is not being done in the tester.");

                //  query points
                System.out.println("\nTesting for the nearest point finder:");
                q = new int[3];
                for (int x1= -1; x1 <= 1; x1++) { // make a unit 3x3 cube of query points
                    for (int x2= -1; x2 <= 1; x2++) {
                        for (int x3= -1; x3 <= 1; x3++) {
                            q[0] = x1;
                            q[1] = x2;
                            q[2] = x3;
                            testQuery(q, points, tree);
                        }
                    }
                }
                //testing the iterator
                System.out.println("\nTesting for the iterator :");
                testIterator(tree, points);
                break;

            case 4:
                break;



        }
    }
}