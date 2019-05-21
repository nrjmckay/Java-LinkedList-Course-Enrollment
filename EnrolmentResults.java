
/**
 * 
 * Nolan Mckay
 * 
 * April 13th, 2018
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class EnrolmentResults {

	/**
	 * The main method will create 2 courseLists, add all the courses from the syllabus to it.
	 * @param args
	 */
	public static void main(String[] args) {
		CourseList c1 = new CourseList();
		ArrayList<Course> cList = new ArrayList<Course>();
		Scanner sc = null;

		try {
			File syl = new File("Syllabus.txt");
			sc = new Scanner(syl);
		} catch (Exception e) {
			System.out.println("Exception e");
		}
		//get the input from the syllabus file, and then add it to an arraylist and the courseList
		while (sc.hasNext()) {
			String id = sc.next();
			String name = sc.next();
			double cred = sc.nextDouble();
			sc.nextLine();
			String pre = sc.nextLine();
			pre = pre.replaceAll("P" + "\\s+", "");
			if(pre.equals("P"))
				pre = "";
			String co = sc.nextLine();
			co = co.replaceAll("C" + "\\s+", "");
			if(co.equals("C"))
				co = "";
			Course aa = new Course(id, name, cred, pre, co);
			c1.addToStart(aa);
			cList.add(aa);
			if (sc.hasNext()) {
				//get rid of extra lines in the file
				//System.out.println("extraLine");
				sc.nextLine();
			}
		}
		//c1.printOut();
		System.out.println("Courses available:");
		for(Course c: cList) {
			System.out.println(c.toString() + "\n");
		}
		sc.close();

		

		/*create courses for the requested.
		 *  A student can get in a class if pre-req is in finished
		 *  and co-req is in requested or finished
		 *  */
		canEnrol(c1, cList);
		System.out.println("Thanks for using"
				+ ". Goodbye human.");

	}
	/**
	 *method to check if a student can enrol in a class
	 *user input is user to get the file 
	 *c1 & cList have the same items
	 * @param c1 is the CourseList made
	 * @param cList is the course arraylist 
	 */
	public static void canEnrol(CourseList c1, ArrayList<Course> cList) {
		//sc1 is used to get input, while sc2 is used to open the file
		Scanner sc1 = new Scanner(System.in);
		System.out.println("Enter the request file (i.e Request.txt)");
		String userFile = sc1.nextLine();
		//String userFile = "Request.txt";
		File file = new File(userFile);
		try {
			Scanner sc2 = new Scanner(file);
			//two arrays to hold the course ids
			ArrayList<String> finished = new ArrayList<String>();
			ArrayList<String> requested = new ArrayList<String>();
			/*CourseList clFin = new CourseList();
			CourseList clReq = new CourseList();*/
			int i = 0;
			while (sc2.hasNextLine()) {
				String line = sc2.nextLine();
				if (line.equals("Requested")) {
					i = 1;
				} 
				else if (line.equals("Finished")) { 
					i =0;
				} 
				else {
					if (i == 0) {
						finished.add(line);
					} else {
						requested.add(line);
					}
				}
			}
			System.out.println("Finished:");
			for(String fi: finished) {
				System.out.println(fi);
			}
			System.out.println("Requested:");
			for(String re: requested) {
				System.out.println(re);
			}
			System.out.println("\n");
			sc2.close();

			/* Each course has a max of 1 prereq and a max of 1 coreq
			 * */
			if (requested.size() == 0) {
				System.out.println("No requested courses");
			}
			else {
				for (String reqString : requested) {
					boolean req = false;
					boolean fin = false;
					for (Course search : cList) {
						if (reqString.equals(search.courseID)) {
							String preq = search.getPreReq();
							String cor = search.getCoReq();
							//check for Co-req in requested
							for(String reqco: requested) {
								if (preq.equals(reqco)) {
									fin = true;
								}
								if (cor.equals(reqco)) {
									req = true;
								}
							}
							for (String f : finished) {
								if (preq.equals(f)||preq.equals("")) {
									fin = true;
								}
								if (cor.equals(f)||cor.equals("")) {
									req = true;
								}
							}
						}

					}
					if (fin == true && req == true) {
						System.out.println("Student can enrol in " + reqString);

					} else {
						System.out.println("Student cannot enrol in " + reqString
								+ " as he/she doesn't have the sufficient background needed");
					}
				}
			}
			int inc = 0;
			//Check if user can join the courses
			while (inc < 2) {
				inc++;
				System.out.println("Enter a Course IDs to see if you can join with the classes you have already finished.");

				String in = sc1.nextLine().toUpperCase();
				if(c1.contains(in)) {
					boolean req = false;
					boolean fin = false;

					//search again to see if the user can join that class
					for (Course search : cList) {
						if (in.equals(search.courseID)) {
							String preq = search.getPreReq();
							String cor = search.getCoReq();
							for (String f : finished) {
								if (preq.equals(f) ||preq.equals("")) {
									fin = true;
								}
								if (cor.equals(f)||cor.equals("")) {
									req = true;
								}
							}
						}

					}
					if (fin == true && req == true) {
						System.out.println("Student could enrol in " + in);

					} else {
						System.out.println(
								"Student could not enrol in " + in + " as he/she doesn't have the sufficient background needed");
					}
				}else {System.out.println("The course " + in + " is not offered.");}

			}

			
			int c = 0;
			while (c < 2) {
				System.out.println("Look up a course in the syllabus.");
				String input = sc1.nextLine().toUpperCase();
				if(c1.contains(input)) {
					Course cour = c1.find(input).getCourse();
					System.out.println(cour.toString());
				}
				else {System.out.println(input + " is not in the syllabus.");}
				c++;
			}

			//close scanner 
			sc1.close();
		} catch (FileNotFoundException e) {
			System.out.println("That was not a valid file.");
		}
		catch (Exception e2) {
			System.out.println("General Exception");

		}
	}

}