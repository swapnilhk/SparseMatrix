package LinkedList;

import java.util.NoSuchElementException;

public class LinkedList <T>{
	private Node<T> first;
	private Node<T> curr;
	private Node<T> last;
	
	public LinkedList(T n){
		first = new Node<T>(n);
		curr = first;
		last = first;
	}
	
	public boolean hasCurr(){
		if(curr == null)
			return false;
		else return true;
	}
	
	public boolean hasNext(){
		if(curr != null && curr.next != null)
			return true;
		else return false;
	}
	
	public void next(){
		if(curr != null){
			curr = curr.next;
		}
		else new OutOfBoundException();
	}
	
	public void addBefore(T ob1, T ob2)throws NoSuchElementException{
		Node<T> curr = first;
		do{
			if(curr.data == ob1){
				Node<T> n = new Node<T>(ob2);
				n.next = curr;
				n.prev = curr.prev;
				if(curr == first)
					first = n;
				else
					curr.prev.next = n;
				curr.prev = n;
				return;
			}
			curr = curr.next;
		}while(curr != null);
		throw new NoSuchElementException();
	}
	
	public void addAfter(T ob1, T ob2)throws NoSuchElementException{
		Node<T> curr = first;
		do{
			if(curr.data == ob1){
				Node<T> n = new Node<T>(ob2);
				n.prev = curr;
				n.next = curr.next;				
				curr.next = n;
				if(curr == last)
					last = n;
				else
					curr.next.prev = n;
				curr.next = n;
				return;
			}
			curr = curr.next;
		}while(curr != null);
		throw new NoSuchElementException();
	}
	
	public void addAtEnd(T ob){
		Node<T> n = new Node<T>(ob);		
		last.next = n;
		n.prev = last;
		last = n;
	}
	
	public void addInPlaceOf(T ob1, T ob2){
		Node<T> curr = first;
		do{
			if(curr.data == ob1){
				Node<T> n = new Node<T>(ob2);
				n.prev = curr.prev;
				n.next = curr.next;
				if(curr == first)
					first = n;
				else
					curr.prev.next = n;
				if(curr == last)
					last = n;
				else
					curr.next.prev = n;
				curr.next = curr.prev = null;
				return;
			}
			curr = curr.next;
		}while(curr != null);
		throw new NoSuchElementException();
	}
	
	public void remove(T ob1){
		Node<T> curr = first;
		do{
			if(curr.data == ob1){
				if(curr == first)
					first = curr.next;
				else
					curr.prev.next = curr.next;
				if(curr == last)
					last = curr.prev;
				else
					curr.next.prev = curr.prev;
				curr.prev = curr.next = null;
				return;
			}
			curr = curr.next;
		}while(curr != null);
		throw new NoSuchElementException();
	}	

	public T curr() {
		if(curr == null)
			return null;
		else
			return curr.data;
	}

	public void resetCurr() {
		this.curr = first;
	}
}
