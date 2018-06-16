package co.com.corp.maven.ProjectMvnRest.stack;

/**
 * Class to handle the information in the pile
 * 
 * @author Andreta
 *
 */
public class Node {

	private double amount;
	private long timestamp;
	private Node next;

	public Node() {
		this.amount = 0;
		this.next = null;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

}
