package package2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.Iterator;

public class EnrolmentResults {
	 
	public static boolean verifiedPre(ArrayList<String> finished,ArrayList<String> requested,Course c) {
		// if the course has the same prereq and coreq, then we check for corequisits. then if the coreqs are
		//verified, we return true if not, we return false, so that the message can be printed don in the body.
		if(c.preReqID.equals(c.coReqID)) {
			if(finished.contains(c.coReqID)||requested.contains(c.coReqID))
				return true;
			else return false;
		}
			
		if(finished.contains(c.preReqID))
			return true;
		else
			return false;
	}
	public static boolean currentlyEnrolling(ArrayList<String> finished,ArrayList<String> requested,Course c) {
		if(requested.contains(c.coReqID))
			return true;
		return false;
	}
	public static boolean verifiedCo(ArrayList<String> finished,ArrayList<String> requested,Course c) {
		if(finished.contains(c.coReqID))
			return true;
		return false;
	}

	public static void main(String[] args) {
		
		CourseList L1 =new CourseList();
		Course newcourse=null;
		ArrayList<Course> arlist =new ArrayList<Course>();
		ArrayList<String> requested= new ArrayList<String>();
		ArrayList<String>finished = new ArrayList<String>();
		
		BufferedReader s1=null;
		BufferedReader s2=null;
		String  line=null;
		String courseID=null;
		String coursename=null;
		double credits=0.0;
		String prereqs=null;
		String coreqs=null;
		String str=null;
		
		//creating course list using copy constructor
		CourseList L2= new CourseList(L1);
		
		//dont forget toreplace the _ with spaces in the course names in the list!!!!!!11
		
		
		//opennig file
		try {
			
			 s1= new BufferedReader(new FileReader("Needed Files/Syllabus.txt"));
			 
		}
		catch(FileNotFoundException b) {
			System.out.println("file could not be opened. System will exit.");
			System.exit(0);
		}
		
		try {
			
				line= s1.readLine();
			while(line!=null) {
				
				//skipping any lines that are null or whitespace
				while(line!=null&&line.trim().length()==0) {
					line=s1.readLine();
				}
				System.out.println(line);
				
				
				
				courseID= line.substring(0,line.indexOf("	"));
				//System.out.print("\ncourseID : "+courseID);
				
				
				coursename= line.substring(line.indexOf("	")+1,line.indexOf("	", line.indexOf("	")+1));
				//System.out.print("\ncoursename : "+coursename);
				
				credits= Double.parseDouble(line.substring(line.lastIndexOf("	")+1,line.length()));
				//System.out.print("\ncredits : "+credits);
				
				line=s1.readLine();
				
				
				//collecting the string after the letter P in the file
				String temp=line.substring(line.indexOf("P")+1,line.length());
				
				//if temp is a string of white space, then the prerequisits will be "none"
				prereqs=(temp.trim().length()==0)?"None.":temp.trim();
				//System.out.print("\nprereqs : "+prereqs);
				
				line=s1.readLine();
				
				
				//collecting the string after the letter P in the file
				temp=line.substring(line.indexOf("C")+1,line.length());
				
				
				//if temp is a string of white space, then the prerequisits will be "none"
				coreqs=(temp.trim().length()==0)?"None.":temp.trim();
				//System.out.println("\ncoreqs : "+coreqs);
				
				line=s1.readLine();
				
				newcourse=new Course(courseID,coursename,credits,prereqs,coreqs);
				
				
				if(!arlist.contains(newcourse)) {
					arlist.add(newcourse);
				}
			}
			//closing the stream
			s1.close();
		} catch (IOException e) {
		
		e.printStackTrace();
		}
		
		for(int i=0;i<arlist.size();i++) {
			L1.addToStart(arlist.get(i));
		}
		
		CourseList L3=null;
		try {
			L3=(CourseList)L1.Clone();
		} catch (CloneNotSupportedException e) {
			
			e.printStackTrace();
		}
		
		
		System.out.println("\nWhich request file do you wish to open?");
		Scanner input =new  Scanner(System.in);
		
		try {
			s2= new BufferedReader(new FileReader("Needed Files/"+input.nextLine()+".txt"));
			
			//going through the first line which is "Finished" since we have no use for it.
			s2.readLine();
			
			line=s2.readLine();
			
			//recording the completed classes. the function stops when we reach the line "request" in the file.
			while(!(line.equals("Requested"))){
				
				finished.add(line.trim());
				line=s2.readLine();
				
			}
			//going through the line "requested" since we dont need it.
			line=s2.readLine();
			
			
			//recording the requested classes
			while(line!=null) {
				
				requested.add(line);
				line=s2.readLine();
			}
			
			s2.close();
		} catch (FileNotFoundException e) {
			System.out.println("file could not be opened, System will exit.");
			
			System.exit(0);
		}
		catch(IOException b) {
		 System.out.println("IOException thrown, system could not read the file.\n System will now exit.");
		 System.exit(0);
		}
		
		
		
		if(requested.size()==0) {
			System.out.println("No enrollment courses found.");
		}else {
			for(String st:requested) {
				Course course=L1.find(st).getCourse();
				
				if(EnrolmentResults.verifiedPre(finished,requested,course)) {
					
					if(EnrolmentResults.currentlyEnrolling(finished,requested,course)) {
						System.out.println("Student can enrol in "+course.courseID+" as he/she is enrolling for co-requisite "+course.coReqID);
					}else
						if(EnrolmentResults.verifiedCo(finished,requested,course)) {
							System.out.println("Student can enrol in "+course.courseID+" as he/she has completed the pre-requisite "+course.preReqID+(finished.contains(course.coReqID)?" and "+course.coReqID : ""));
						}
					
						
					
				}
				else System.out.println("Student can't enrol in "+course.courseID+" as he/she doesn't have sufficient background needed.");
				
			}
			//searching for the user entered course id in the list
			String s;
			
			do {
				System.out.println();
				System.out.println("Please enter the course id you wish to search for, type in \"done\" when done ");
				s=input.next().toUpperCase();
				if(s.equals("DONE"))
					break;
				if(!(finished.contains(s)||requested.contains(s)))
					System.out.println("Course could not be found!");
				else {
					//we start counting the iterations from one and the first line in the file is "finished" which we skipped right away here
					int v=2;
					java.util.Iterator<String> fin= finished.iterator();
					java.util.Iterator<String> req= requested.iterator();
					
					if(finished.contains(s)) {	
						while(fin.hasNext()) {
							
							if(fin.next().equals(s)) {
								System.out.print("The Course "+s+" was found after "+v+" iterations.");
								break;
							}
							v++;
						}
					}else if(requested.contains(s)){
						
						v=finished.size()+2;
						while(req.hasNext()) {
							v++;
							if(req.next().equals(s)) {
								System.out.println("The Course "+s+" was found after "+v+" iterations.");
								break;
							}
							
						}
					}else {
						System.out.println("Course could not be found!!");
					}
				}
			}while(true);
		}
			Course t1 = new Course("Soen 287",null,0.0,null,null);
			Course t2 = new Course("ENGR 201",null,0.0,null,null);
			Course t3 = new Course("ENGR 213",null,0.0,null,null);
			
			System.out.println("\nOriginal list:");
			System.out.println(L1.print());
			System.out.println("\nCloned list : \n"+L3.print());
			System.out.println("Size : "+L1.getSize());
			
			//Add to start function
			System.out.println("\nFunction, add to start. adding Soen 287");
			L3.addToStart(t1);
			System.out.println(L3.print());
			System.out.println("Size : "+L3.getSize());
			
			//delete fromstart function
			System.out.println("\nFunction, deleteFromStart. deleting first index");
			L3.deleteFromStart();
			System.out.println(L3.print());
			System.out.println("Size : "+L3.getSize());
			
			//insertAtIndex method
			try {
				System.out.println("\nFunction: InsertAtIndex ( index : Soen 287 at 0, engr 213 at last node)");
				L3.insertAtIndex(t1, 0);
				L3.insertAtIndex(t3,L3.getSize()-1);
				System.out.println(L3.print());
				System.out.println("Size : "+L3.getSize());
			}catch(NoSuchElementException b) {
				System.out.println("\nIndex entered is out of bounds!!");
			}
			
			//delete from index function
			try {
				//IMPORTANT!! note that here we're deleting the first node first, so if you're deleting from a specified
				//index, note that the index will be one less.
				System.out.println("\nFunction: deleteFromIndex ( index :  0, last node)");
				L3.deleteFromIndex(0);
				//remember that if you plug a number here, it will delete the node that has an index 1+the number
				//this doesnt apply when using getSize as getSize is dynamic and changes value after the first operation.
				L3.deleteFromIndex(L3.getSize()-1);
				System.out.println(L3.print());
				System.out.println("Size : "+L3.getSize());
				
			}catch(NoSuchElementException b) {
				System.err.println("Index entered is out of bounds!!");
			}
			//ReplaceAtIndex function
			System.out.println("\nFunction: replaceAtIndex ( index "+t3.courseID+" :  0, "+t1.courseID+" last node)");
			L3.replaceAtIndex(0, t3);
			L3.replaceAtIndex(L3.getSize()-1,t1);
			System.out.println(L3.print());
			System.out.println("Size : "+L3.getSize());
			
			//Find function
			String n="COMP249";
			System.out.println("\nFunction: Find ( Course Name: "+n+")");
			System.out.println("prerequisite of the course : "+L3.find(n).getCourse().preReqID);
			System.out.println("the course after it in the list : "+L3.find(n).getNext().getCourse().courseID);
			System.out.println("Size : "+L3.getSize());
			
			//is directly relatable function
			
			
			
			System.out.println("Proving the original list was not changed : ");
			System.out.println(L1.print());
			
			System.out.println("\nFunctio equals method");
			CourseList L4=null;
			try {
				L4 =(CourseList)L1.Clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			System.out.println("\nList L4 is a clone of the originals list.\n L4 : "+L4.print());
			System.out.println("\nOriginal List : "+L1.print());
			
			System.out.println("\nL4.equals(L1) = "+L4.equals(L1));
			
			L4.deleteFromStart();
			System.out.println("\nChanging L4 (L4.deleteFromStart)\nL4 = "+L4.print());
			
			System.out.println("\nL4.equals(L1) = "+L4.equals(L1));
			
			//Directly relatable method
			System.out.println("Function: is directly relatable Courses : "+L3.find("COMP249").getCourse().courseID+" to " +L3.find("COMP248").getCourse().courseID);
			System.out.println(L3.find("COMP249").getCourse().isDirectlyRelatable(L1.find("COMP248").getCourse()));
		
		
	}

}
