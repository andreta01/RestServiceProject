package co.com.corp.maven.ProjectMvnRest.pojo;

/**
 * Class of transactions 
 * @author Andreta
 *
 */
public class Transaction {

	private double amount;
	private long timestamp;

	public Transaction() {

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

	@Override
	public String toString() {
		return "Transaction [amount=" + amount + ", timestamp=" + timestamp + "]";
	}


}
