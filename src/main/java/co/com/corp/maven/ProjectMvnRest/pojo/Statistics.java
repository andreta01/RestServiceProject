package co.com.corp.maven.ProjectMvnRest.pojo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class of Statistics to be return in Json
 * @author Andreta
 *
 */
@XmlRootElement
public class Statistics {

	private double sum;
	private double avg;
	private double max;
	private double min;
	private long count;

	public Statistics() {
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}
