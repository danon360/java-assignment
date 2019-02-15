package package2;

import java.util.NoSuchElementException;


public class CourseList implements Cloneable{
	class CourseNode implements Cloneable{
		private Course course=new Course();
		private CourseNode next;
		
		/**
		 * @param None
		 */
		public CourseNode() {
			course=null;
			next=null;
		}
		
		/**
		 * @param Course , CourseNode
		 */
		//parameterised constructor
		public CourseNode(Course c,CourseNode cn) {
			course=c;
			next=cn;
		}
		/**
		 * @param CourseNode
		 */
		//copy constructor takes in an object course 
		public CourseNode(CourseNode b) {
			
			this.course=new Course(b.course);
			next=new CourseNode(b.next);
		}
		/**
		 * returns a deep copy of the object
		 * @param None
		 * @return CourseNode
		 */
		//clone method 
		public CourseNode clone() throws CloneNotSupportedException {
			CourseNode t=new CourseNode() ;//=(CourseNode)super.clone();
			t.course=(Course)course.clone();
			t.next=new CourseNode(this.next.course,this.next.next);
			
			return t;	
		}
		/**
		 * returns the object Course
		 * @return Course
		 */
		//accessor for cobject
		public Course getCourse() {
			return course;
		}
		/**
		 * return the object CourseNode
		 * @retrun CourseNode
		 */
		//accessor for cnobj
		public CourseNode getNext() {
			return next;
		}
		/**
		 * sets the value of Course
		 * @param Course
		 */
		//mutator for cobject
		public void setCourse(Course s) {
			course=s;
		}
		/**
		 * sets the value of CourseNode 
		 * @param CourseNode
		 */
		//mutator for cnobj
		public void setNext(CourseNode s) {
			next=s;
		}
		
	}
	
	private CourseNode head;
	private int size=0;
	
	/**
	 * @param None
	 */
	public CourseList() {
		CourseNode head;
	}
	
	
	/**
	 * @param Courselist
	 */
	public CourseList(CourseList b) {
		
		head=b.head;
		size=b.size;
	}
	/**
	 * return a clone of the object CourseList, but of type Object
	 * @param None
	 * @return Object
	 * @throws CloneNotSupportedException
	 */
	public Object Clone() throws CloneNotSupportedException {
		CourseList t=new CourseList();
		t.head= (CourseNode) head.clone();
		t.size=this.size;
		return t;
	}
	/**
	 * returns the size of the List
	 * @param None
	 * @return integer "Size"
	 */
	public int getSize() {
		return size;
	}
	/**
	 * adds a courseNode to the start of the list
	 * @param Course
	 * @return void
	 */
	public void addToStart(Course b) {
		head= new CourseNode(b,head);
		size++;
	}
	/**
	 * inserts a Course at the specified index
	 * @param Course the course to be inserted
	 * @param int the index
	 * @return void
	 * @throws NoSuchElementException
	 */
	//insert at index
	public void insertAtIndex(Course b, int i)throws NoSuchElementException{
		
		CourseNode t=head;
		CourseNode n=new CourseNode (b,null);
		//if adding at the start offthe list
		if(i==0) {
			this.addToStart(b);
		}else {
			//if the list is empty and we're not adding at the begining of the list
			if(head==null||i<0 ) {
				throw new NoSuchElementException();
			}
		
			//if given index is larger than array
			if((this.size-1)<(i))
			{throw new NoSuchElementException() ;}
			
			//stops the pointer t right before the given index
			for(int v=0;v<(i-1);v++) {
				t=t.next;
			}
			//after the pointer t is right before the index if we're setting a node at the end of
			//the list,we perform a different function than if we're setting it in the middle.
			if(i==this.size-1) {
				n.next=t.next.next;
				t.next.next=n;
				size++;
				t=null;
			}else {
				n.next=t.next;
				t.next=n;
				size++;
				t=null;
			}
		}
		
	}
	/** deltes from the specified index
	 * @param int the index
	 * @return boolean
	 * @throws NoSuchElementException
	 */
	//method deleteFromIndex
	public boolean deleteFromIndex(int i) throws NoSuchElementException{
		CourseNode t=head;
		if((size-1)<i||i<0)throw new NoSuchElementException();
		
		//if there is no list, it will return true 
		if(head==null) {
			return true;
		}
		//if wer deleting from the start of the list
		if(i==0) {
			head=head.next;
			size--;
			return true;
		}

		if((this.size-1)<i)throw new NoSuchElementException();
		for(int v=0;v<i-1;v++) {
			t=t.next;
		}
		t.next=t.next.next;
		size--;
		return true;
	}
	/**
	 * deletes from the start of the list
	 * @param None 
	 * @return void
	 * @throws NoSuchElementException
	 */
	public void deleteFromStart() {
		if(head==null)throw new NoSuchElementException();
		head=head.next;
		size--;
	}
	
	/**
	 * replaces a course by the provided course at a specified index
	 * @param int the index
	 * @param Course the course that will replace anothe rcourse in the liat
	 * @return void
	 * 
	 */
	public void replaceAtIndex(int i, Course b) {
		CourseNode t=head;
		CourseNode n= new CourseNode(b,null);
		
		if((this.size-1)<i) {
			System.out.println("Index out of bounds!");
			return;
		}
		
		//if we're replacing the first node or if there is only one node in the list. Note that if there is
		//only one node in the list, then the previous condition insures that i==0.
		if(i==0) {
			n.next=head.next;
			head=n;
		}else {
			
			for(int v=0;v<i-1;v++) {
				t=t.next;
			}
			n.next=t.next.next;
			t.next=n;
		}
		
	}
	/**
	 * takes a string as an argument and finds the course name that matches it in the courselist. this method is a security risk.
	 * @param String Name of the course you wish to find in the list.
	 * @return CourseNode
	 * 
	 */
	//this method has a privacy leak, since the head of the list is private, you cannot access the list 
	//except if you have access to head. this method is public and provides you with another pointer to the list.
	public CourseNode find(String id) {
		
		CourseNode t=head;
		
		for(int v=0;v<size;v++) {
			if(t==null) return null;
			if(t.course.courseID.equals(id))return t;
			t=t.next;
		}
		
		return null;
	}
	/**
	 * tells you if a list contains the course you searched for or not.
	 * @param String the name of the course
	 * @return boolean
	 * 
	 */
	public boolean contains(String id) {
		if(this.find(id)==null) return false;
		else return true;
	}
	/**
	 * tells you if two lists are equals or not
	 * @param CourseListthe list you wish to compare the first list to
	 * @return boolean
	 * 
	 */
	public boolean equals(CourseList c) {
		
		if(c==null||this==null||this.size!=c.size) return false;
		
		CourseNode t1=this.head;
		CourseNode t2= c.head;
		
		while(t1!=null&&t2!=null) {
			
			if(!(t1.course.equals(t2.course))) return false;
			t1=t1.next;
			t2=t2.next;
		}
		return true;
	}
	/**
	 * prints the nodes of the list and their index. (returned as a string)
	 * @param None
	 * @return Stirng
	 *
	 */
	public String print() {
		CourseNode t=head;
		String s="";
		if(t==null) return "null";
		int i=0;
		while(t!=null) {
			
			s=s+t.course.courseID+" index: "+i+"  //  ";
			t=t.next;
			i++;
		}
		return s;
	}
	
}
