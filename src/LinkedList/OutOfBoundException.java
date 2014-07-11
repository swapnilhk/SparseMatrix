package LinkedList;

public class OutOfBoundException extends Exception{
	public String toString(){
		return "Attempted access of element out of bound of LinkedList";
	}
}
