import java.lang.reflect.Field;
public class test {
	
	
		private static void moveAndActionTester() 
		{	
			
			// Create a 2x2 board.	
			Tile tm11 = new Tile(-1,1);
			Tile t01 = new Tile(0,1);
			Tile t11 = new Tile(1,1);
			Tile t21 = new Tile(2,1);
			Tile tm10 = new Tile(-1,0);
			Tile t00 = new Tile(0,0);
			Tile t10 = new Tile(1,0);
			Tile t20 = new Tile(2,0);
			Tile tm1m1 = new Tile(-1,-1);
			Tile t0m1 = new Tile(0,-1);
			Tile t1m1 = new Tile(1,-1);
			Tile t2m1 = new Tile(2,-1);
			Tile t22 = new Tile(2,2);
			Tile t12 = new Tile(1,2);
			Tile t02 = new Tile(0,2);
			
			ListOfUnits lou11 = getFieldListOfUnitFromTile(t11);
			ListOfUnits lou22 = getFieldListOfUnitFromTile(t22);
			ListOfUnits lou12 = getFieldListOfUnitFromTile(t12);
			ListOfUnits loum1m1 = getFieldListOfUnitFromTile(tm1m1);
			
			// tm1m1是岳林队队大本营！！
			Unit []岳林队 = new Unit[8];
			// t22是宇婧队队大本营！！！
			Unit []宇婧队 = new Unit[8]; 
			
			try {
				岳林队[0] = new Worker(tm1m1, 50, "岳林方");
				岳林队[1] = new Settler(tm1m1, 20, "岳林方");
				岳林队[2] = new Archer(tm1m1, 40, "岳林方");
				岳林队[3] = new Warrior(tm1m1, 100, "岳林方");
				岳林队[4] = new Worker(tm1m1, 100, "岳林方");
				岳林队[5] = new Settler(tm1m1, 40, "岳林方");
				岳林队[6] = new Archer(tm1m1, 80, "岳林方");
				岳林队[7] = new Warrior(tm1m1, 200, "岳林方");
				/*岳林队[8] = new Worker(tm1m1, 50, "岳林方");
				岳林队[9] = new Settler(tm1m1, 20, "岳林方");
				岳林队[10] = new Archer(tm1m1, 40, "岳林方");
				岳林队[11] = new Warrior(tm1m1, 100, "岳林方");
				岳林队[12] = new Worker(tm1m1, 100, "岳林方");
				岳林队[13] = new Settler(tm1m1, 40, "岳林方");
				岳林队[14] = new Archer(tm1m1, 80, "岳林方");
				岳林队[15] = new Warrior(tm1m1, 200, "岳林方");*/
				
				宇婧队[0] = new Worker(t22, 55, "宇婧方");
				宇婧队[1] = new Settler(t22, 15, "宇婧方");
				宇婧队[2] = new Archer(t22, 45, "宇婧方");
				宇婧队[3] = new Warrior(t22, 90, "宇婧方");
				宇婧队[4] = new Worker(t22, 110, "宇婧方");
				宇婧队[5] = new Settler(t22, 30, "宇婧方");
				宇婧队[6] = new Archer(t22, 90, "宇婧方");
				宇婧队[7] = new Warrior(t22, 180, "宇婧方");
			}
			catch (Exception e) {
				System.out.println("你队Unit（及其subclass）构造器（或者构造器所使用队方法）有问题！！！");
				return;
			}
			//System.out.println(loum1m1.size());
			// move 岳林队 to an empty tile t12
			for (Unit u : 岳林队)
				if (u.moveTo(t12)) {
					System.out.println("你的距离判断有问题！！");
					return;
				}
			for (Unit u : 岳林队)
				if (!u.moveTo(t00)) {
					System.out.println("你的距离判断有问题！！");
					return;
				}
			
			for (Unit u : 岳林队)
				if (!u.moveTo(t11)) {
					System.out.println("你的距离判断有问题！！");
					return;
				}
			/*System.out.println(lou11.size());
			for(int i = 0; i<lou11.size();i++)
				System.out.print(lou11.get(i).getHP()+"  ");*/
				if (宇婧队[7].moveTo(t11)) {
					System.out.println("敌人不能在同一个方格！！");
					return;
				}
			
				if (宇婧队[7].moveTo(t02)) {
					System.out.println("Warrior距离有问题！！");
					return;
				}
			for (Unit u : 岳林队)
				if (!u.moveTo(t11)) {
					System.out.println("你的距离判断有问题！！");
					return;
				}
		/*	System.out.println(lou11.size());
			for(int i = 0; i<lou11.size();i++)
				System.out.print(lou11.get(i).getHP()+"  ");*/
			岳林队[0].takeAction(t02);
			if(t02.isImproved()==true) {System.out.println("Worker工作方式错误！！"); return;}
			岳林队[0].takeAction(t11);
			if(t11.isImproved()==false) {System.out.println("Worker工作方式错误！！");return;}
			
			岳林队[1].takeAction(t02);
			if(t02.isImproved()==true) {System.out.println("Settelr工作方式错误！！");return;}
			岳林队[1].takeAction(t11);
			if(t11.isImproved()==false) {System.out.println("Settler工作方式错误！！");return;}
			//System.out.println(lou11.size());
			//岳林队[9].takeAction(t11);
			
		//	if(lou11.size()!=15)  System.out.println("Settler工作方式错误！！");
			
			宇婧队[2].takeAction(tm1m1);
			宇婧队[2].takeAction(tm1m1);
			宇婧队[2].takeAction(tm1m1);
			宇婧队[2].takeAction(tm1m1);
			宇婧队[2].takeAction(tm1m1);
			宇婧队[2].takeAction(t11);
			if(岳林队[2].getHP()!=40) {System.out.println("Archer工作方式(换箭)错误！！");return;}
			/*System.out.println(lou11.size());
			for(int i=0;i<8;i++)
			System.out.print(岳林队[i].getHP()+"   ");*/
			宇婧队[6].takeAction(t11);
			
			for(int i=0;i<8;i++)
			/*System.out.print(岳林队[i].getHP()+"   ");
			System.out.println("  ");
			System.out.println(lou11.indexOf(岳林队[5]));*/
			if(岳林队[2].getHP()!=26.5) {System.out.println("检查archer，注意防御10%加成，并且注意伤害没有提高5%");return;}
			
			
			
			宇婧队[0].takeAction(t22);
			
			
			
			
		//	宇婧队[2].moveTo(t02);
			宇婧队[2].takeAction(t11); 
			if(岳林队[2].getHP()!=12.325) {System.out.println("检查archer，并且注意伤害提高5%");return;}
			/*System.out.println(lou11.size());
			for(int i = 0; i<lou11.size();i++)
				System.out.print(lou11.get(i).getHP()+"  ");*/
			宇婧队[2].takeAction(t11);
			
			/*System.out.println(lou11.size());
			for(int i = 0; i<lou11.size();i++)
				System.out.print(lou11.get(i).getHP()+"  ");*/
			if(t11.removeUnit(岳林队[2])==true) {System.out.println("检查你的removeUnit");return;}
			
				/*System.out.println(lou22.size());
			for(int i = 0; i<lou22.size();i++)
				System.out.print(lou22.get(i).getHP()+"  ");*/
			岳林队[7].moveTo(t12);
			宇婧队[7].takeAction(t12);
			/*System.out.println(lou12.size());
			for(int i = 0; i<lou12.size();i++)
				System.out.print(lou12.get(i).getHP()+"  ");*/
			if(岳林队[7].getHP()!=183.2)  {System.out.println("检查你的护甲函数");return;}
			
			System.out.println("你通过了我的小测试");
		}
			
			
			
			// get the private field ListOfUnits from a tile object
			
			// Check if all units are removed from t11
			/*if (!(lou11 != null && lou11.size() == 0)) {
				System.out.println("你通过了我的小测试");
				return;
			}
			
			ListOfUnits lou12 = getFieldListOfUnitFromTile(t12);
			// check if all units are added to t12
			if (!(lou12 != null && lou12.size() == 4)) {
				System.out.println("Units are not added to the new tile after it has been moved.");
				return;
			}
			
			// now try to move 宇婧队 onto 岳林队, this should fail for Archers and Warriors.
			for (int i = 0; i < 4; i++) {
				if (i < 2 && !宇婧队[i].moveTo(t12)) {
					System.out.println("Settler and Worker should be able to move to a Tile with enemy army present.");
					return;
				}
				if (i >= 2 && 宇婧队[i].moveTo(t12)) {
					System.out.println("MilitaryUnits should not be able to move to a Tile with enemy army present.");
					return;
				}
			}
			
			// Check size of tile t12
			if (lou12.size() != 6) {
				System.out.println("Units did not move to the new tile.");
				return;
			}
			
			// Move back 宇婧方 Settler and Worker to t22
			if (!(宇婧队[0].moveTo(t22) && 宇婧队[1].moveTo(t22))) {
				System.out.println("Settler and Worker should be able to move to any Tile.");
				return;
			}
			
			ListOfUnits lou22 = getFieldListOfUnitFromTile(t22);
			
			if (lou22.size() != 4) {
				System.out.println("Units did not move to the new tile.");
				return;
			}
			
			System.out.println("moveTo() tests passed.");

			// all 岳林队 are now in t12 and all 宇婧队 are in t22.

			// Check takeAction Worker in 岳林方
	        岳林队[0].takeAction(t12); // should append jobs by 1 and improve tile12
	        Field []fields = 岳林队[0].getClass().getDeclaredFields();
	        Field privateFieldJobs = fields[0];
	        privateFieldJobs.setAccessible(true);
	        try {
	            if ((int)privateFieldJobs.get(岳林队[0]) != 1 || (!(t12.isImproved()))) {
	                System.out.println("Wrong implementation of takeAction method in class Worker");
	                return;
	            }
	        } catch (Exception e) {
	            System.out.println("Unable to find private field jobs in Worker");
	            return;
	        }
	        // set jobs back to 0
	        try {
	            privateFieldJobs.set(岳林队[0], 0);
	        } catch (Exception e) {
	            System.out.println("Unable to find private field jobs in Worker");
	            return;
	        }

	        // Check takeAction Settler in 岳林方
	        岳林队[1].takeAction(t12); // should build city and remove unit from tile
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
	        岳林队[1] = new Settler(t12, 20, "岳林方");
	        if (!(t12.addUnit(岳林队[1]))) {
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

			// Check takeAction for clan Archer (宇婧方) on t12 (岳林队): should reduce arrows by 1, reduce settler's hp in 岳林队 from 20.0 to 5.0
	        fields = 宇婧队[2].getClass().getDeclaredFields();
	        Field privateFieldArrows = fields[0];
	        privateFieldArrows.setAccessible(true);
	        Unit enemy = t12.selectWeakEnemy("宇婧方");
	        //System.out.println(enemy.getHP());
	        if (!(enemy instanceof Settler)) {
	            System.out.println("Check impementation of function selectWeakEnemy in class MilitaryUnit");
	            return;
	        }
//	        Unit settester = new Settler(t12, 195, "宇婧方9");
//	        settester.takeAction(t12);
	        宇婧队[2].takeAction(t12);
	       //System.out.println(enemy.getHP());
	        
	        try {
	            if ((int)privateFieldArrows.get(宇婧队[2]) != 4 || (double)enemy.getHP() != 5.0) {
	                System.out.println("Check implementation of takeAction in class Archer");
	                return;
	            }
	        } catch (Exception e) {
	            System.out.println("Unable to find private field arrows in Archer." + e.getMessage());
	            return;
	        }
	        // Set number of arrows back to 5 and settler in 岳林方's hp back to 20
	        try {
	            privateFieldArrows.set(宇婧队[2], 5);
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

	        // Check takeAction for clan Warrior (宇婧方) on t12 (岳林队): should reduce settler's hp in 岳林队 from 20 to 0.0
	        宇婧队[3].takeAction(t12);
	        if ((double)enemy.getHP() != 0.0) {
	            System.out.println("Check implementation of takeAction in class Warrior");
	            return;
	        }
	        // Set settler in 岳林方's hp back to 20
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

			// attacking empty tile t11 with archer and warrior in 宇婧队. Should not throw nullPtrException
	        try {
	            for (int i = 2; i < 4; i++) {
	                宇婧队[i].takeAction(t11);
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
					
			// clan 宇婧方
			Unit []宇婧队 = new Unit[4]; 
			//firePeople (clan 宇婧方 + 7 of the same worker(fireWorker))
			Unit[] firePeople =  new Unit[11];
			Unit fireWorker;
			try {
				宇婧队[0] = new Worker(t22, 55, "宇婧方");
				宇婧队[1] = new Settler(t22, 15, "宇婧方");
				宇婧队[2] = new Archer(t22, 45, "宇婧方");
				宇婧队[3] = new Warrior(t22, 90, "宇婧方");
				fireWorker = new Worker(t22, 66, "宇婧方");
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
			if (!firePeople[0].equals(宇婧队[0])) {
				System.out.println("Incorrect Unit gotten, should be the Worker.");
				return;
			} else if (!firePeople[1].equals(宇婧队[1])) {
				System.out.println("Incorrect Unit gotten, should be the Settler.");
				return;
			} else if (!firePeople[2].equals(宇婧队[2])) {
				System.out.println("Incorrect Unit gotten, should be the Archer.");
				
				return;
			} else if (!firePeople[3].equals(宇婧队[3])) {
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
			if (!fireArmy[0].equals(宇婧队[2])) {
				System.out.println("List of Units, getArmy returns incorrect unit.");
				return;
			} else if (!fireArmy[1].equals(宇婧队[3])) {
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
		
		private static void thouShallNotHangTest() {
			System.out.print("Did you hang yourself");
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
			
		}*/
		
		
		public static void main(String[] args) {
			System.out.println("只需检查你最上面的错误（console提示的第一个错误），因为本tester是按顺序执行的，错了一个可能后面都错");
			//thouShallNotHangTest();
			//listOfUnitsTester();
			moveAndActionTester();
		}
		

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
		
	}


