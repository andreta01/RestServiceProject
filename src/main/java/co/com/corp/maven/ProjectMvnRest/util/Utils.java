package co.com.corp.maven.ProjectMvnRest.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import com.google.gson.Gson;

import co.com.corp.maven.ProjectMvnRest.pojo.Statistics;
import co.com.corp.maven.ProjectMvnRest.pojo.Transaction;
import co.com.corp.maven.ProjectMvnRest.stack.Node;

/**
 * Util class
 * 
 * @author Andreta
 *
 */
public class Utils {

	private Utils() {

	}

	/**
	 * Method that transform Json object to Transaction object
	 * 
	 * @param incomingData
	 * @return Transaction object
	 * @throws IOException
	 */
	public static Transaction jsonTransformer(InputStream incomingData) throws IOException {
		Transaction transaction = new Transaction();
		StringBuilder builder = new StringBuilder();
		String line = null;
		String resp = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));

		while ((line = in.readLine()) != null) {
			builder.append(line);
			resp = resp + line.toString();
		}
		Gson gson = new Gson();
		transaction = gson.fromJson(resp, Transaction.class);
		return transaction;
	}

	/**
	 * Method to calculate the time between two dates
	 * 
	 * @param timeStamp
	 * @return long
	 */
	public static long getOldTime(long timeStamp) {
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTimeZone(TimeZone.getTimeZone("UNIX"));
		Calendar transactionDate = Calendar.getInstance();
		transactionDate.setTimeZone(TimeZone.getTimeZone("UNIX"));
		transactionDate.setTimeInMillis(timeStamp);
		return currentDate.getTimeInMillis() - transactionDate.getTimeInMillis();
	}

	/**
	 * Method to calculate the statistics
	 * @param list
	 * @return
	 */
	public static Statistics getStatistics(List<Node> list) {
		Statistics statistics  = new Statistics ();
		double sum = 0;
		double max = 0;
		if (!list.isEmpty()) {
			double min = list.get(0).getAmount();
			for (Node node : list) {
				sum = sum + node.getAmount();
				if (max < node.getAmount())
					max = node.getAmount();
				if (min > node.getAmount())
					min = node.getAmount();
			}
			double avg = sum / list.size();
			statistics.setSum(sum);
			statistics.setAvg(avg);
			statistics.setMax(max);
			statistics.setMin(min);
			statistics.setCount(list.size());
		}
		return statistics;
	}
}
