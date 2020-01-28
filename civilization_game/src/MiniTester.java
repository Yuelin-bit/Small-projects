import java.lang.reflect.*;

public class MiniTester {
	private static ListOfUnits getFieldListOfUnitFromTile(Tile t)
	{	
		ListOfUnits l = null;
		
		try {
			Field []fields = Tile.class.getDeclaredFields();
			Field lou = null;
		
			for (Field f : fields) {
				if (f.getType().equals(ListOfUnits.class)) {
					lou = f;
				}
			}
		
			if (lou == null) {
				System.out.println("ListOfUnit field not found in class Tile");
				return null;
			}
		
			lou.setAccessible(true);
			l = (ListOfUnits)lou.get(t);
		}
		catch (Exception e) {
			System.out.println("ListOfUnit field not found in class Tile");
		}
		
		return l; 
	}
	
	private static void moveAndActionTester() 
	{	
		// Create a 2x2 board.
		Tile t11 = new Tile(1,1);
		Tile t12 = new Tile(1,2);
		Tile t21 = new Tile(2,1);
		Tile t22 = new Tile(2,2);
		
		// clan Waterbender 
		Unit []waterBenders = new Unit[4];
		// clan Firebender
		Unit []fireBenders = new Unit[4]; 
		
		try {
			waterBenders[0] = new Worker(t11, 50, "Waterbender");
			waterBenders[1] = new Settler(t11, 20, "Waterbender");
			waterBenders[2] = new Archer(t11, 40, "Waterbender");
			waterBenders[3] = new Warrior(t11, 100, "Waterbender");
				
			fireBenders[0] = new Worker(t22, 55, "Firebender");
			fireBenders[1] = new Settler(t22, 15, "Firebender");
			fireBenders[2] = new Archer(t22, 45, "Firebender");
			fireBenders[3] = new Warrior(t22, 90, "Firebender");
		}
		catch (Exception e) {
			System.out.println("Check constructors for Settler, Worker, Archer, Warrior");
			return;
		}
		
		// move waterBenders to an empty tile t12
		for (Unit u : waterBenders)
			if (!u.moveTo(t12)) {
				System.out.println("Unit cannot move to an empty tile.");
				return;
			}
		
		// get the private field ListOfUnits from a tile object
		ListOfUnits lou11 = getFieldListOfUnitFromTile(t11);
		// Check if all units are removed from t11
		if (!(lou11 != null && lou11.size() == 0)) {
			System.out.println("Units are not removed after it has been moved from a tile.");
			return;
		}
		
		ListOfUnits lou12 = getFieldListOfUnitFromTile(t12);
		// check if all units are added to t12
		if (!(lou12 != null && lou12.size() == 4)) {
			System.out.println("Units are not added to the new tile after it has been moved.");
			return;
		}
		
		// now try to move firebenders onto waterbenders, this should fail for Archers and Warriors.
		for (int i = 0; i < 4; i++) {
			if (i < 2 && !fireBenders[i].moveTo(t12)) {
				System.out.println("Settler and Worker should be able to move to a Tile with enemy army present.");
				return;
			}
			if (i >= 2 && fireBenders[i].moveTo(t12)) {
				System.out.println("MilitaryUnits should not be able to move to a Tile with enemy army present.");
				return;
			}
		}
		
		// Check size of tile t12
		if (lou12.size() != 6) {
			System.out.println("Units did not move to the new tile.");
			return;
		}
		
		// Move back Firebender Settler and Worker to t22
		if (!(fireBenders[0].moveTo(t22) && fireBenders[1].moveTo(t22))) {
			System.out.println("Settler and Worker should be able to move to any Tile.");
			return;
		}
		
		ListOfUnits lou22 = getFieldListOfUnitFromTile(t22);
		
		if (lou22.size() != 4) {
			System.out.println("Units did not move to the new tile.");
			return;
		}
		
		System.out.println("moveTo() tests passed.");

		// all waterbenders are now in t12 and all firebenders are in t22.

		// Check takeAction Worker in WaterBender
        waterBenders[0].takeAction(t12); // should append jobs by 1 and improve tile12
        Field []fields = waterBenders[0].getClass().getDeclaredFields();
        Field privateFieldJobs = fields[0];
        privateFieldJobs.setAccessible(true);
        try {
            if ((int)privateFieldJobs.get(waterBenders[0]) != 1 || (!(t12.isImproved()))) {
                System.out.println("Wrong implementation of takeAction method in class Worker");
                return;
            }
        } catch (Exception e) {
            System.out.println("Unable to find private field jobs in Worker");
            return;
        }
        // set jobs back to 0
        try {
            privateFieldJobs.set(waterBenders[0], 0);
        } catch (Exception e) {
            System.out.println("Unable to find private field jobs in Worker");
            return;
        }

        // Check takeAction Settler in WaterBender
        waterBenders[1].takeAction(t12); // should build city and remove unit from tile
        try {
            if (!(t12.isCity()) || (lou12.size() != 3)) {
                System.out.println("Wrong implementation of takeAction method in class Settler");
                return;
            }
        } catch (Exception e) {
            System.out.println("Unable to find private field jobs in Settler");
            return;
        }
        
        // add Settler back to tile
		try {
        	waterBenders[1] = new Settler(t12, 20, "Waterbender");
        } catch (Exception e) {
            System.out.println("Settler and Worker should be able to move to any Tile.");
            return;
		}
        
        // Remove improvement and city from tile12
        try {
            fields = t12.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getType() == boolean.class) {
                    field.setAccessible(true);
                    field.set(t12, false);
                }
            }
        } catch (Exception e) {
            System.out.println("Cannot find required boolean fields in class Tile");
            return;
        }

		// Check takeAction for clan Archer (fireBender) on t12 (waterBenders): should reduce arrows by 1, reduce settler's hp in waterBenders from 20.0 to 5.0
        fields = fireBenders[2].getClass().getDeclaredFields();
        Field privateFieldArrows = fields[0];
        privateFieldArrows.setAccessible(true);
        Unit enemy = t12.selectWeakEnemy("FireBender");
        if (!(enemy instanceof Settler)) {
            System.out.println("Check impementation of function selectWeakEnemy in class Tile.");
            return;
        }
        fireBenders[2].takeAction(t12);
        try {
            if ((int)privateFieldArrows.get(fireBenders[2]) != 4 || (double)enemy.getHP() != 5.0) {
                System.out.println("Check implementation of takeAction in class Archer");
                return;
            }
        } catch (Exception e) {
            System.out.println("Unable to find private field arrows in Archer." + e.getMessage());
            return;
        }
        // Set number of arrows back to 5 and settler in waterBender's hp back to 20
        try {
            privateFieldArrows.set(fireBenders[2], 5);
            fields = enemy.getClass().getSuperclass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getType() == double.class) {
                    field.setAccessible(true);
                    field.set(enemy, 20.0);
                }
            }
        } catch (Exception e) {
            System.out.println("Unable to find private field health point in Unit");
            return;
        }

        // Check takeAction for clan Warrior (fireBender) on t12 (waterBenders): should reduce settler's hp in waterBenders from 20 to 0.0
        fireBenders[3].takeAction(t12);
        if ((double)enemy.getHP() != 0.0) {
            System.out.println("Check implementation of takeAction in class Warrior");
            return;
        }
        // Set settler in waterBender's hp back to 20
        try {
            fields = enemy.getClass().getSuperclass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getType() == double.class) {
                    field.setAccessible(true);
                    field.set(enemy, 20.0);
                }
            }
        } catch (Exception e) {
            System.out.println("Unable to find private field health points");
            return;
        }

		// attacking empty tile t11 with archer and warrior in fireBenders. Should not throw nullPtrException
        try {
            for (int i = 2; i < 4; i++) {
                fireBenders[i].takeAction(t11);
            }
        } catch (Exception e) {
            System.out.println("Attacking an empty tile should not throw an exception!");
            return;
        }

        // End MOVE TEST.
		System.out.println("takeAction() tests passed.");
	}
	
	private static void listOfUnitsTester() {
		Tile t22 = new Tile(2,2);
				
		// clan Firebender
		Unit []fireBenders = new Unit[4]; 
		//firePeople (clan Firebender + 7 of the same worker(fireWorker))
		Unit[] firePeople =  new Unit[11];
		Unit fireWorker;
		try {
			fireBenders[0] = new Worker(t22, 55, "Firebender");
			fireBenders[1] = new Settler(t22, 15, "Firebender");
			fireBenders[2] = new Archer(t22, 45, "Firebender");
			fireBenders[3] = new Warrior(t22, 90, "Firebender");
			fireWorker = new Worker(t22, 66, "Firebender");
		}
		catch (Exception e) {
			System.out.println("Check constructors for Settler, Worker, Archer, Warrior");
			return;
		}
				
		ListOfUnits lou22 = getFieldListOfUnitFromTile(t22);

		//LIST OF UNITS TEST
				
		//at start fire order is 0-worker,1-settler,2-archer,3-warrior

				
		//getUnits test
		try {
			firePeople = lou22.getUnits();
		} catch(Exception e) {
			System.out.println("List of Units, getUnits function has failed.");
			return;
		}
				
		//get test
		if (!firePeople[0].equals(fireBenders[0])) {
			System.out.println("Incorrect Unit gotten, should be the Worker.");
			return;
		} else if (!firePeople[1].equals(fireBenders[1])) {
			System.out.println("Incorrect Unit gotten, should be the Settler.");
			return;
		} else if (!firePeople[2].equals(fireBenders[2])) {
			System.out.println("Incorrect Unit gotten, should be the Archer.");
			return;
		} else if (!firePeople[3].equals(fireBenders[3])) {
			System.out.println("Incorrect Unit gotten, should be the Warrior.");
			return;
		}
				
		//getArmy test
		Unit[] fireArmy =  new Unit[2];
		try {
			fireArmy = lou22.getArmy();
		}catch(Exception e) {
			System.out.println("List of Units, getArmy function has failed.");
			return;
		}
				
		//0-archer,1-warrior
		if (fireArmy.length != 2) {
			System.out.println("List of Units, getArmy returns incorrect number of units.");
			return;
		}
		if (!fireArmy[0].equals(fireBenders[2])) {
			System.out.println("List of Units, getArmy returns incorrect unit.");
			return;
		} else if (!fireArmy[1].equals(fireBenders[3])) {
			System.out.println("List of Units, getArmy returns incorrect unit.");
			return;
		}
		
		//indexOf test
		int indexArcher = -1;
		int indexWorker = -1;
		try {
			indexArcher = lou22.indexOf(firePeople[2]);
			indexWorker = lou22.indexOf(firePeople[0]);
		} catch(Exception e) {
			System.out.println("List of Units, IndexOf failed.");
			return;
		}
		if (indexArcher != 2) {
			System.out.println("IndexOf returned incorrect position of a unit.");
			return;
		}
		if (indexWorker != 0) {
			System.out.println("IndexOf returned incorrect position of a unit.");
			return;
		}
				
		//add test 
		try {
			lou22.add(fireWorker);
		} catch(Exception e) {
			System.out.println("List of Units, add function has failed.");
			return;
		}
		try {
			firePeople = lou22.getUnits();
		} catch(Exception e) {
			System.out.println("List of Units, getUnits failed.");
			return;
		}
		if(!firePeople[4].equals(fireWorker)) {
			System.out.println("ListOfUnit add() is incorrect.");
			return;
		}
				
		//add resize test
		try {
			for (int i = 0; i < 5;i++) {
				lou22.add(fireWorker);
			}
		} catch(Exception e) {
			System.out.println("List of Units, add function failed.");
			return;
		}
		if (lou22.size() != 11) {
			System.out.println("List of Units, failed to resize correctly.");
			return;
		}
				
		//remove test, should only remove 66 hp workers
		try {
			lou22.remove(fireWorker);
		} catch(Exception e) {
			System.out.println("List of Units, remove function failed.");
			return;
		}
		if (lou22.size() != 10) {
			System.out.println("Remove failed to decrease size of List of Units.");
			return;
		}
				
		try {
			for (int i = 0; i < 6;i++) {
				lou22.remove(fireWorker);
			}
		}catch (Exception e) {
			System.out.println("List of Units, remove function failed.");
			return;
		}
		if (lou22.size() != 4) {
			System.out.println("Remove has failed to decrease size of List of Units.");
			return;
		}		
				
		//END OF LIST OF UNITS TESTS
		System.out.println("List of Units tests passed.");
	}
	
	private static void crashTest() {
		System.out.print("Did your code crash");
		Archer arch = null;
		Worker work = null;
		Settler setl = null;
		Warrior warr = null;
		try {
			Tile t = new Tile(1,2);
			arch = new Archer(t, 10.0, "CuteRhino");
			work = new Worker(t, 10.0, "CuteRhino");
			setl = new Settler(t, 10.0, "CuteRhino");
			warr = new Warrior(t, 10.0, "CuteRhino");
		} catch(Exception e) {
			System.out.println("...Not today!");
			return;
		}
		
		try {
			System.out.print(" doing equals() test");
			arch.equals(arch);
			work.equals(work);
			setl.equals(setl);
			warr.equals(warr);
		} catch(StackOverflowError e) {
			System.out.println("...Yes! Check proper use of this and super!");
			return;
		}
		catch(Exception e) {
			System.out.println("...Not today!");
			return;
		}
		
		try {
			System.out.print(" or doing receiveDamage() test");
			arch.receiveDamage(5);
			work.receiveDamage(5);
			setl.receiveDamage(5);
			warr.receiveDamage(5);
		}catch(StackOverflowError e) {
			System.out.println("...Yes! Check proper use of this and super!");
			return;
		}
		catch(Exception e) {
			System.out.println("...Not today!");
			return;
		}
		
		System.out.println("...Not today!");		
	}

	
	public static void main(String[] args) {
		crashTest();
		listOfUnitsTester();
		moveAndActionTester();
	}

}