
/**
 * 
 * Nolan Mckay
 * April 13th, 2018
 * Class of linked listed Courses
 */

import java.util.NoSuchElementException;
public class CourseList {
	private CourseNode head;
	private int size = 0;
	/**
	 * Default constructor 
	 */
	public CourseList() {
		this.head = null;
	}

	/**
	 * copies one courseList to another
	 * @param copy is the courselist being copied
	 */
	public CourseList(CourseList copy) {
		if(this.head != null) {
			this.head = new CourseNode(copy.head);
			CourseNode next = new CourseNode(copy.head.next);
			this.head.next = next;
		}
		else { this.head = null;}
	}

	/**
	 * Adds a course to the start of the courseList
	 * @param add is the course being added
	 */
	public void addToStart(Course add) {
		CourseNode cn = new CourseNode(add,head);
		head = cn;
		size++;
	}

	/**
	 * Inserts a course at the index, adds one to the size
	 * @param add is the course being added
	 * @param index is where the course will be added
	 */
	public void insertAtIndex(Course add, int index) {
		try {
			//if index out of range, throw nosuchelement exception
			if (index < 0 || index >= size) {
				throw new NoSuchElementException();
			} else {

				CourseNode holder = head;
				CourseNode next = new CourseNode();
				if (index == 0) {
					CourseNode insert = new CourseNode(add, head);
					head = insert;
				} else {
					int i = 0;
					// holder should get node at index -1
					while (i < index - 1) {
						next = holder.getNext();
						holder = next;
						i++;
					}
					CourseNode adding = new CourseNode(add, holder.getNext());
					holder.setNext(adding);
				}
				size++;
			}
		} catch (NoSuchElementException ne) {
			//close program
			System.exit(0);
		}
	}

	/**
	 * deletes a courseNode from the course list at index
	 * @param index where the coursenode will be deleted
	 */
	public void deleteFromIndex(int index) {
		try {
			if (index < 0 || index >= size) {
				throw new NoSuchElementException();
			} else {
				if(index == 0) {
					deleteFromStart();
				}
				//get the head of the courselist
				else {
					CourseNode holder = head;
					CourseNode next = new CourseNode();
					int i = 0;
					// holder should get node at index -1
					while (i < index - 1) {
						next = holder.getNext();
						holder = next;
						i++;
					}
					next = holder.getNext();
					holder.setNext(next.getNext());
					next.setNext(null);
					size--;
				}
			}
		} catch (NoSuchElementException ne) {
			System.exit(0);
		}
	}

	/**
	 * deletes a node from the start of the list
	 */
	public void deleteFromStart() {
		if(head != null) {
			CourseNode newHead = new CourseNode();
			newHead = head.getNext();
			head.setNext(null);
			head = newHead;
			size--;
		}
	}

	/**
	 * replaces a node at an index
	 * @param replace is the course to take the spot at index
	 * @param index is where the course will be replaced
	 */
	public void replaceAtIndex(Course replace, int index) {
		if (index < 0 || index >= size) {
			return;
		} else {
			if(index == 0) {
				CourseNode nnew = new CourseNode(replace, head.next);
				head.setNext(null);
				head = nnew;
			}
			else {
				CourseNode holder = head;
				CourseNode next = new CourseNode();
				int i = 0;
				// holder should get node at index -1
				while (i < index - 1) {
					next = holder.getNext();
					holder = next;
					i++;
				}
				//next should be at index
				next = holder.getNext();
				CourseNode nnew = new CourseNode(replace, next.next);
				holder.setNext(nnew);
				if (next.next != null) {
					next.next.setNext(null);
				}
			}
		}
	}

	/**
	 * method to find a CourseNode for a course Id
	 * @param findMe is the course wanted to be found
	 * @return returns the coursenode 
	 */
	public CourseNode find(String findMe) {
		CourseNode i = head;
		String same = "";
		int j = 0;
		while (i != null) {
			same = i.getCourse().getCourseID();
			if (same.equals(findMe)) {
				//System.out.println("Found at node index:" + j);
				return i;
			}
			j++;
			i = i.getNext();
		}
		return null;
	}

	/**
	 * Method to check if a courselist has a course corresponding to a courseId
	 * @param findMe is the course id name wanted to be found
	 * @return returns true if found, false otherwise
	 */
	public boolean contains(String findMe) {
		CourseNode i = head;
		String same = "";
		int j = 0;
		while (i != null) {
			same = i.getCourse().getCourseID();
			if (same.equals(findMe)) {
				//System.out.println("Found at node index:" + j);
				return true;
			}
			j++;
			i = i.getNext();
		}
		return false;
	}

	/**
	 * checks to see if two courelists have similar courses
	 * @param compare is the coureList being compared to
	 * @return true if they are equals, false otherwise
	 */
	public boolean equals(CourseList compare) {
		CourseNode thisH = this.head;
		CourseNode compareH = compare.head;
		while(thisH != null) {
			Course thisC = thisH.course;
			while(compareH !=null) {
				Course compareC = compareH.course;
				if(thisC.equals(compareC)) {
					return true;
				}
				compareH = compareH.next;
			}
			thisH = thisH.next;
		}
		return false;
	}

	/**
	 * Inner Class, 
	 * @author Nolan
	 *
	 */
	public class CourseNode{
		private Course course;
		private CourseNode next;

		/**
		 * default constructor 
		 */
		CourseNode(){
			this.course = null;
			this.next = null;
		}
		/**
		 * parameterized constructor 
		 * @param c is the course for the node
		 * @param next is the next coursenode
		 */
		CourseNode(Course c, CourseNode next){

			this.course = new Course(c,c.getCourseID());
			this.next = next;
		}
		/**
		 * copy constructor 
		 * @param copy is the courseNode being copied
		 */
		public CourseNode(CourseNode copy){
			if(this !=null || this.next != null) {
				String holder = copy.course.getCourseID();
				this.course = new Course(copy.course,holder);
				this.next = copy.next;
			}
		}
		/**
		 * method to clone a courseNode
		 */
		public CourseNode clone() {
			CourseNode cNew = new CourseNode(this);
			return cNew;
		}
		/**
		 * method to set a course
		 * @param c what the course will be set to
		 */
		public void setCourse(Course c) {
			this.course = c;
		}
		/**
		 * setter
		 * @param cn what the next coursenode will be set to
		 */
		public void setNext(CourseNode cn) {
			this.next = cn;
		}
		/**
		 * getter
		 * @return the next course node
		 */
		public CourseNode getNext() {
			return this.next;
		}

		/**
		 * getter
		 * @return the course at this coursenode
		 */
		public Course getCourse() {
			return this.course;
		}
	} //end of inner class

	/**
	 * gets the head of the courselist
	 * @return the head
	 */
	public CourseNode getHead() {
		return this.head;
	}

	/**
	 * gets the size of the courselist
	 * @return the size
	 */
	public int getSize() {
		return this.size;
	}
	/**
	 * method to print off the courses in the courseList
	 */
	public void printOut() {
		CourseNode cn = head;
		while(cn != null) {
			System.out.println(cn.course.toString() + "\n");
			cn = cn.next;
		}
	}
	public Course getNextCourse(CourseNode cn) {
		CourseNode next = cn.next;
		Course nextC = next.course;
		return nextC;
	}
}