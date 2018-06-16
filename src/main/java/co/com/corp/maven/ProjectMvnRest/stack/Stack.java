package co.com.corp.maven.ProjectMvnRest.stack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Class to handle the methods of the pile
 * @author Andreta
 *
 */
public class Stack {

	private static Stack instance = null;

	private Node beginin;
	private int size;
	private static final int TRANSACTION_TIME = 60000;

	/**
	 * Method that returns the single instance
	 * @return
	 */
	public static Stack getInstance() {
		if (instance == null) {
			instance = new Stack();
		}
		return instance;
	}

	protected Stack() {
		beginin = null;
		size = 0;
	}

	public boolean isEmpty() {
		return beginin == null;
	}

	public int getSize() {
		return size;
	}

	/**
	 * Method that add a new node information
	 * @param timestamp
	 * @param amount
	 */
	public void newNode(long timestamp, double amount) {
		Node newNode = new Node();
		newNode.setTimestamp(timestamp);
		newNode.setAmount(amount);
		if (isEmpty()) {
			beginin = newNode;
		} else {
			newNode.setNext(beginin);
			beginin = newNode;
		}
		size++;
	}

	/**
	 * Method that removes a object of the pile
	 */
	public void remove() {
		if (!isEmpty()) {
			beginin = beginin.getNext();
			size--;
		}
	}

	/**
	 * Method that returns the top of the pile
	 * @return
	 * @throws Exception
	 */
	public long top() throws Exception {
		if (!isEmpty()) {
			return beginin.getTimestamp();
		} else {
			throw new Exception("The pile is empty.");
		}
	}

	/**
	 * Method that search an object with the timestamp
	 * @param ref
	 * @return
	 */
	public boolean search(long ref) {
		Node aux = beginin;
		boolean exists = false;
		while (exists != true && aux != null) {
			if (ref == aux.getTimestamp()) {
				exists = true;
			} else {
				aux = aux.getNext();
			}
		}
		return exists;
	}

	/**
	 * Method that removes an object with an specific timestamp
	 * @param timestamp
	 */
	public void remove(long timestamp) {
		if (search(timestamp)) {
			Node pileAux = null;
			while (timestamp != beginin.getTimestamp()) {
				Node temp = new Node();
				temp.setTimestamp(beginin.getTimestamp());
				temp.setAmount(beginin.getAmount());
				if (pileAux == null) {
					pileAux = temp;
				} else {
					temp.setNext(pileAux);
					pileAux = temp;
				}
				remove();
			}
			remove();
			while (pileAux != null) {
				newNode(pileAux.getTimestamp(), pileAux.getAmount());
				pileAux = pileAux.getNext();
			}
			pileAux = null;
		}
	}

	/**
	 * Method that edits an object in the pile
	 * @param ref
	 * @param timestamp
	 * @param amount
	 */
	public void edit(int ref, long timestamp, double amount) {
		if (search(ref)) {
			Node pileAux = null;
			while (ref != beginin.getTimestamp()) {
				Node temp = new Node();
				temp.setTimestamp(beginin.getTimestamp());
				temp.setAmount(beginin.getAmount());
				if (pileAux == null) {
					pileAux = temp;
				} else {
					temp.setNext(pileAux);
					pileAux = temp;
				}
				remove();
			}
			beginin.setTimestamp(timestamp);
			beginin.setAmount(amount);
			while (pileAux != null) {
				newNode(pileAux.getTimestamp(), pileAux.getAmount());
				pileAux = pileAux.getNext();
			}
			pileAux = null;
		}
	}

	/**
	 * Method that deletes the pile
	 */
	public void delete() {
		beginin = null;
		size = 0;
	}

	/**
	 * Method that prints the list
	 */
	public void list() {
		Node aux = beginin;
		while (aux != null) {
			System.out.println("|\t" + aux.getTimestamp() + " / " + aux.getAmount() + "\t|");
			System.out.println("-------------------------");
			aux = aux.getNext();
		}
	}

	/**
	 * Methods that returns the list of the nodes
	 * @return  List<Node> 
	 */
	public List<Node> getList() {
		Node aux = beginin;
		List<Node> nodeList = new ArrayList<Node>();
		while (aux != null) {
			nodeList.add(aux);
			aux = aux.getNext();
		}
		return nodeList;
	}

	/**
	 * Method that removes the old timestamp in the list
	 */
	public void removeOld() {
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTimeZone(TimeZone.getTimeZone("UNIX"));
		Node aux = beginin;
		while (aux != null) {
			if ((aux.getTimestamp() - currentDate.getTimeInMillis()) > TRANSACTION_TIME) {
				remove(aux.getTimestamp());
			}
			aux = aux.getNext();
		}
	}
}
