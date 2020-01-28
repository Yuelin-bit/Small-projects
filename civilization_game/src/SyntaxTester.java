import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SyntaxTester {
    private static void testFnNames(String className, ArrayList<String> correctMethods, ArrayList<String> correctConstructors) throws Exception {
        if (classExists(className)) // Proceed only if the class exists
        {
            Class theClass = null;
            try {
                theClass = Class.forName(className); // get the class e.g. Tile or Worker

            }catch (Exception e)
            {
            	throw new Exception("Constructor or method checking failed!"); 
            }

            Method[] studentMethods = theClass.getDeclaredMethods(); // get the public methods
            Constructor[] studentConstr = theClass.getConstructors(); // get the constructor methods

            ArrayList<String> s_studentMethods = new ArrayList< String >();
            ArrayList< String > s_studentConstr = new ArrayList< String >();

            for( Method method : studentMethods ) s_studentMethods.add ( method.toString() );
            for( Constructor constr : studentConstr ) s_studentConstr.add ( constr.toString() );
            
            removeStaticKeyword( s_studentMethods );

            evaluate(correctConstructors, s_studentConstr, true, className);
            evaluate(correctMethods, s_studentMethods, false, className);

        }
        else
            throw new Exception("Class " + className + " does not exist.");
    }
    
    private static void testClassFields() throws Exception 
    {
    	String []classList = {"Settler", "Worker", "Archer", "Warrior"};
    	
    	// Check if all required classes exist
    	boolean allRequiredClassesExist = true;
    	for (String s : classList) allRequiredClassesExist &= classExists(s);
    	allRequiredClassesExist &= classExists("Tile");
    	allRequiredClassesExist &= classExists("Unit");
    	allRequiredClassesExist &= classExists("MilitaryUnit");
    	allRequiredClassesExist &= classExists("ListOfUnits");
    	
    	if (!allRequiredClassesExist) 
    	{
    		throw new Exception("Field checking cannot proceed. Please implement all the classes first.");
    	}
    	
    	Class _Unit = null;
    	Class _MilitaryUnit = null;
    	Class _Tile = null;
    	Class _ListOfUnits = null;
    	Class []_Units = new Class[4]; // Class Settler, Worker, Archer, Warrior
    	
    	try {
    		// get the classes
    		_Unit = Class.forName("Unit");
    		_MilitaryUnit = Class.forName("MilitaryUnit");
            _Tile = Class.forName("Tile"); 
            _ListOfUnits = Class.forName("ListOfUnits");
            for (int i = 0; i < 4; i++)
            	_Units[i] = Class.forName(classList[i]);
           
        }catch (Exception e)
        {
            throw new Exception("Field checking failed!"); // We shouldn't be here
        }
    	
    	Field []tilef = _Tile.getDeclaredFields();
    	Field []unitf = _Unit.getDeclaredFields();
    	Field []milUnitf = _MilitaryUnit.getDeclaredFields();
    	Field []workerf = _Units[1].getDeclaredFields();
        Field []archerf = _Units[2].getDeclaredFields();
        
        if (unitf == null || unitf.length != 4)
        	throw new Exception("Field checking cannot proceed. Class Unit not as per specification. Incorrect fields.");
                
        if (tilef == null || tilef.length != 5)
        	throw new Exception("Field checking cannot proceed. Class Tile not as per specification. Incorrect fields.");
        
        if (milUnitf == null || milUnitf.length != 3)
        	throw new Exception("Field checking cannot proceed. Class MilitaryUnit not as per specification. Incorrect fields.");
                	
        if (workerf == null || archerf == null || workerf.length != 1 || archerf.length != 1) {
        	throw new Exception("Field checking cannot proceed. Class Worker or Archer not as per specification. Incorrect fields.");
        }
        	
        if (!(workerf[0].getType().equals(int.class) && archerf[0].getType().equals(int.class))) 
        {
        	throw new Exception("Field checking cannot proceed. Class Worker or Archer not as per specification. Incorrect fields.");
        }
        
        { // check Tile
        	int nInts = 2;
        	int nBools = 2;
        	int nListOfUnit = 1; 
        	for (Field f : tilef) {
        		if (f.getType().equals(int.class))
        			nInts--;
        		if (f.getType().equals(boolean.class))
        			nBools--;
        		if (f.getType().equals(_ListOfUnits))
        			nListOfUnit--;
        	}
        	
        	if (nInts != 0 || nBools != 0 || nListOfUnit != 0)
        		throw new Exception("Field checking cannot proceed. Class Tile not as per specification. Incorrect fields.");
        }
        
        {
        	int nInt = 1;
        	int nDouble = 1;
        	int nString = 1;
        	int nTile = 1;
        	for (Field f : unitf) {
        		if (f.getType().equals(_Tile))
        			nTile--;
        		if (f.getType().equals(double.class))
        			nDouble--;
        		if (f.getType().equals(int.class))
        			nInt--;
        		if (f.getType().equals(String.class))
        			nString--;
        	}
        	
        	if (nInt != 0 || nDouble != 0 || nTile != 0 || nString != 0)
        		throw new Exception("Field checking cannot proceed. Class Unit not as per specification. Incorrect fields.");
        }
        
        {
        	int nInt = 2;
        	int nDouble = 1;
        	for (Field f : milUnitf) {
        		if (f.getType().equals(double.class))
        			nDouble--;
        		if (f.getType().equals(int.class))
        			nInt--;
        	}
        	
        	if (nInt != 0 || nDouble != 0)
        		throw new Exception("Field checking cannot proceed. Class MilitaryUnit not as per specification. Incorrect fields.");
        }
    }
 

    private static void evaluate(ArrayList<String> correct, ArrayList<String> student, boolean constr, String className) throws Exception {
        for (String correctString : correct) //we only care about testable methods
        {
            if (!student.contains(correctString)) //signature cannot be found
            {	
            	String errStr;
                if (constr) {
                    errStr = "Error with constructor: " + correctString + " in class: " + className + ". Constructor cannot be found or has wrong signature.";
                } else {
                    errStr = "Error with method: " + correctString + " in class: " + className + ". Method cannot be found or has wrong signature.";
                }

                throw new Exception(errStr);
            }
        }
    }
    private static boolean classExists(String className) {
        try {
            Class.forName( className );
            return true;
        }
//        catch(ClassNotFoundException e) { return false; }
//        catch( NoClassDefFoundError e ){ return false; }
        catch( Exception e ){ return false; }
    }

    private static void removeStaticKeyword( ArrayList< String > arr )
    {
        for( int i = 0; i < arr.size(); i++ )
        {
            String[] tokens = arr.get( i ).split( " " ); //tokenize based on space delimiter

            String answer = ""; //build the answer from empty string

            for( int j = 0; j < tokens.length; j++ )
            {
                if( !tokens[ j ].equals( "static" ) ) answer += ( tokens[ j ] + ( j == tokens.length - 1 ? "" : " " ) ); //also add spaces
            }
            arr.remove( i ); //replace the string in place
            arr.add( i,answer );
            //System.out.println( Arrays.toString( tokens ) );
        }
    }

    public static void main(String[] args)
    {	
    	try {
    	// UNIT
        ArrayList<String> correctFnNamesUnit = new ArrayList<String>( Arrays.asList(
                "public final Tile Unit.getPosition()",
                "public final double Unit.getHP()",
                "public final java.lang.String Unit.getFaction()",
                "public boolean Unit.moveTo(Tile)",
                "public void Unit.receiveDamage(double)",
                "public abstract void Unit.takeAction(Tile)",
                "public boolean Unit.equals(java.lang.Object)"
                ) );
        ArrayList<String> correctConstNamesUnit = new ArrayList<String>(Arrays.asList( "public Unit(Tile,double,int,java.lang.String)" ));
        testFnNames("Unit", correctFnNamesUnit, correctConstNamesUnit);
        
      //SETTLER
        ArrayList<String> correctFnNamesSettler = new ArrayList<String>( Arrays.asList(
                "public void Settler.takeAction(Tile)",
                "public boolean Settler.equals(java.lang.Object)") );
        ArrayList<String> correctConstNamesSettler = new ArrayList<String>(Arrays.asList( "public Settler(Tile,double,java.lang.String)" ));
        testFnNames("Settler", correctFnNamesSettler, correctConstNamesSettler);
        
        // WORKER
        ArrayList<String> correctFnNamesWorker = new ArrayList<String>( Arrays.asList(
                "public void Worker.takeAction(Tile)",
                "public boolean Worker.equals(java.lang.Object)") );
        ArrayList<String> correctConstNamesWorker = new ArrayList<String>(Arrays.asList( "public Worker(Tile,double,java.lang.String)" ));
        testFnNames("Worker", correctFnNamesWorker, correctConstNamesWorker);
        
        // MILITARYUNIT
        ArrayList<String> correctFnNamesMilitaryUnit = new ArrayList<String>( Arrays.asList(
                "public void MilitaryUnit.takeAction(Tile)",
                "public void MilitaryUnit.receiveDamage(double)") ); //might need to change all recieveDamage to doubles now? Unsure atm
        ArrayList<String> correctConstNamesMilitaryUnit = new ArrayList<String>(Arrays.asList( "public MilitaryUnit(Tile,double,int,java.lang.String,double,int,int)" ));
        testFnNames("MilitaryUnit", correctFnNamesMilitaryUnit, correctConstNamesMilitaryUnit);

        //WARRIOR
        ArrayList<String> correctFnNamesWarrior = new ArrayList<String>( Arrays.asList(
                "public boolean Warrior.equals(java.lang.Object)") );
        ArrayList<String> correctConstNamesWarrior = new ArrayList<String>(Arrays.asList( "public Warrior(Tile,double,java.lang.String)" ));
        testFnNames("Warrior", correctFnNamesWarrior, correctConstNamesWarrior);
        
        // ARCHER
        ArrayList<String> correctFnNamesArcher = new ArrayList<String>( Arrays.asList(
                "public void Archer.takeAction(Tile)",
                "public boolean Archer.equals(java.lang.Object)") );
        ArrayList<String> correctConstNamesArcher = new ArrayList<String>(Arrays.asList( "public Archer(Tile,double,java.lang.String)" ));
        testFnNames("Archer", correctFnNamesArcher, correctConstNamesArcher);
        
        // LISTOFUNITS
        ArrayList<String> correctFnNamesLoU = new ArrayList<String>( Arrays.asList(
        		"public void ListOfUnits.add(Unit)",
                "public Unit ListOfUnits.get(int)",
                "public boolean ListOfUnits.remove(Unit)",
                "public int ListOfUnits.indexOf(Unit)",
                "public int ListOfUnits.size()",
                "public Unit[] ListOfUnits.getUnits()",
                "public MilitaryUnit[] ListOfUnits.getArmy()") );
        ArrayList<String> correctConstNamesLoU = new ArrayList<String>(Arrays.asList( "public ListOfUnits()" ));
        testFnNames("ListOfUnits", correctFnNamesLoU, correctConstNamesLoU);
        
        // TILE
        ArrayList<String> correctFnNamesTile = new ArrayList<String>( Arrays.asList(
        		"public boolean Tile.isImproved()",
                "public double Tile.getDistance(Tile,Tile)",
                "public int Tile.getY()",
                "public int Tile.getX()",
                "public void Tile.foundCity()",
//                "public Unit[] Tile.getUnit()", //not in the assignment?
                "public Unit Tile.selectWeakEnemy(java.lang.String)",
                "public boolean Tile.removeUnit(Unit)",
                "public boolean Tile.addUnit(Unit)",
                "public boolean Tile.isCity()",
                "public void Tile.buildImprovement()") );
        ArrayList<String> correctConstNamesTile = new ArrayList<String>(Arrays.asList( "public Tile(int,int)" ));
        testFnNames("Tile", correctFnNamesTile, correctConstNamesTile);
        
        
        testClassFields();
        }
        catch (Exception e) {
        	System.out.println(e.getMessage());
        }
    }
}