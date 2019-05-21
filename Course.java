
/**
 * 
 * Nolan Mckay
 * April 13th, 2018
 */
import java.util.Scanner;

/**
 * 
 * class to create a course object, which has a course id, course name, credits, prereq, coreq. No two ids can be the same
 *
 */
public class Course implements DirectlyRelatable{
 String courseID; //no two classes have the same courseID
 String courseName; //always recorded as one word
 double credit;
 String preReqID;
 String coReqID;

 /**
  * default constructor with params
  * @param courseID is the course id, no 2 ids can be the same
  * @param courseName is the course name
  * @param credit is the credits of the course
  * @param preReqID is what is needed to be taken before the class
  * @param coReqID is what is needede to be taken either before or while the class is taken
  */
 public Course(String courseID, String courseName, double credit, String preReqID, String coReqID) {
  this.courseID = courseID;
  this.courseName = courseName;
  this.credit = credit;
  this.preReqID = preReqID;
  this.coReqID = coReqID;
  
 }
 //copy constructor
 /**
  * A course is copied, copy constructor 
  * @param copy is a course which will be copied
  * @param newCourseID is the new course Id 
  */
 public Course(Course copy, String newCourseID) {
  this.courseID = newCourseID;
  this.courseName = copy.courseName;
  this.credit = copy.credit;
  this.preReqID = copy.preReqID;
  this.coReqID = copy.coReqID;
 }
 /**
  * a method to clone a course
  * @param userCourseID will be the courseId for the new course
  * @return returns the new course that is the clone of the called course
  */
 public Course clone(String userCourseID) {
  Scanner sc = new Scanner(System.in);
  System.out.println("Enter a new CourseId");
  userCourseID = sc.next();
  Course c = new Course(this, userCourseID);
  sc.close();
  return c;
 }
 /**
  * prints out the info of the course
  */
 public String toString() {
  return ("Course ID: " + courseID + "\nCourse Name: " + courseName + "\nCredits: " + credit + "\nPreq ID: " + preReqID + "\nCoReq ID: " + coReqID);
 }

 /**
  * Two courses are equal if they have all the same attributes except the course id
  * @param b is the course being compared to by the called on course
  * @return returns true if the two courses have the same attributes
  */
 public boolean equals(Course b) {
  if (this.courseName.equals(b.courseName) && this.credit == b.credit && this.preReqID.equals(b.preReqID)
    && this.coReqID.equals(b.coReqID)) {
   System.out.println("They are the same");
   return true;
  } else {
   System.out.println("They are not the same");
   return false;
  }
 }
 /**
  * gets prereq Id
  * @return prereq id
  */
 public String getPreReq() {
  return this.preReqID;
 }
 /**
  * gets the coreq id
  * @return coreq id
  */
 public String getCoReq() {
  return this.coReqID;
 }

 /**
  * two courses are directly related if they have the same prereq or coreq
  * @return returns true if they are directly related 
  */
 public boolean isDirectlyRelated(Course c) {
  if (this.coReqID.equals(c.courseID) || this.preReqID.equals(c.courseID)) {
   System.out.println("Directly related");
   return true;
  } else {
   System.out.println("Not directly related");
   return false;
  }
 }
 /**
  * gets courseId
  * @return courseId
  */
 public String getCourseID() {
  return this.courseID;
  
 }
}