package package2;
import java.util.NoSuchElementException;

import interfaces.DirectlyRelatable;
public class Course implements DirectlyRelatable, Cloneable{
	
	String courseID;
	String courseName;
	double credit;
	String preReqID;
	String coReqID;
	/**
	 * @param None
	 * @return void
	 */
	public Course() {
		courseID=null;
		courseName=null;
		credit=0.0;
		preReqID=null;
		coReqID=null;
	}
	/**
	 * @param String course ID
	 * @param String name of the course
	 * @param double number of credits for the course
	 * @param String name of the course's prerequisit
	 * @param name of the course's corequisit
	 * @return void
	 */
	//parameterized constructor
	public Course(String i,String n,double c,String pr,String cr) {
		courseID=i;
		courseName=n;
		credit=c;
		preReqID=pr;
		coReqID=cr;
	}
	/**
	 * @param Course the course you wish to copy
	 * @return course
	 */
	//copyConstructor
	public Course(Course b) {
		courseID=b.courseID;
		courseName=b.courseName;
		credit=b.credit;
		preReqID=b.preReqID;
		coReqID=b.coReqID;
	}
	/**
	 * returns a clone of the Course object, but of type Object
	 * @param None
	 * @return Object
	 */
	//clone method 
	public Object clone() throws CloneNotSupportedException {
		Course t= new Course(this.courseID,this.courseName,this.credit,this.preReqID,this.coReqID);
		return t;
	}
	/**
	 * returns a string with the courses parameters
	 * @param None
	 * @return String
	 */
	public String toString() {
		return "course ID: "+courseID+" / courseName: "+courseName+" / credits: "+credit+" / prerequisites: "+preReqID+" / corequisites: "+coReqID;
	}
	
	/**
	 * returns wether the object being passed is equal to the object calling or not
	 * @param Object object that you wish to compare to
	 * @return boolean
	 */
	// equls method !!!!!!maake further testing!!!!!!!!!!///!!!!//////
	public boolean equals(Object b) {
		if(b==null)
			return false;
		if(this==b)
			return true;
		//if(!this.equals(b))
			//return false;
		if(b.getClass()!=this.getClass())
			return false;
		Course t =(Course)b;
		if(!(this.courseName.equals(t.courseName)))
			return false;
		if(this.credit!=t.credit)
			return false;
		if(!(this.preReqID.equals(t.preReqID)))
			return false;
		if(!(this.coReqID.equals(t.coReqID)))
			return false;
		return true;
	}
	/**
	 * returns wether one of the courses is a prerequisit or corequisit of each other
	 * @param Course 
	 * @return boolean
	 */
	public boolean isDirectlyRelatable(Course c) {
		if(this.preReqID.equalsIgnoreCase(c.getcourseID()))
			return true;
		if(this.coReqID.equalsIgnoreCase(c.getcourseID()))
			return true;
		if(this.getcourseID().equalsIgnoreCase(c.preReqID))
			return true;
		if(this.getcourseID().equalsIgnoreCase(c.coReqID))
			return true;
		return false;
	}
	
	//accessor methods
	public String getcourseID() {
		return courseID;
	}
	public String getCourseName() {
		return courseName;
	}
	public double getCredit() {
		return credit;
	}
	public String getpreReqID() {
		return preReqID;
	}
	
	//mutator methods
	public void setcourseID(String a) {
		 courseID=a;
	}
	public void setCourseName(String a) {
		 courseName=a;
	}
	public void setCredit(double a) {
		 credit=a;
	}
	public void setpreReqID(String a) {
		 preReqID=a;
	}
	
	
	
	
	
	
	
	
	
	
}
