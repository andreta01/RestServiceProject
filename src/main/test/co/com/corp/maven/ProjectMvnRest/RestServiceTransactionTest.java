
package co.com.corp.maven.ProjectMvnRest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.TimeZone;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import co.com.corp.maven.ProjectMvnRest.pojo.Statistics;
import co.com.corp.maven.ProjectMvnRest.stack.Stack;

public class RestServiceTransactionTest {

	private RestServiceTransaction restServiceTransaction;
	private static Stack pile = Stack.getInstance();

	@Before
	public void setUp() {
		restServiceTransaction = new RestServiceTransaction();
	}

	@Test
	public void testPostRestServiceFail() throws IOException, java.text.ParseException {
		pile.delete();
		String str = "{\"amount\":12.3,\"timestamp\":\"1478192204000\"}";
		InputStream incomingData = new ByteArrayInputStream(str.getBytes());
		Response resp = restServiceTransaction.addTransaction(incomingData);
		assertEquals(204, resp.getStatus());
		assertTrue(pile.getList().isEmpty());
	}

	@Test
	public void testPostRestServiceSuccess() throws IOException {
		pile.delete();
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTimeZone(TimeZone.getTimeZone("UNIX"));
		String str = "{\"amount\":12.3,\"timestamp\":\"" + currentDate.getTimeInMillis() + "\"}";
		InputStream incomingData = new ByteArrayInputStream(str.getBytes());
		Response resp = restServiceTransaction.addTransaction(incomingData);
		assertEquals(201, resp.getStatus());
		assertEquals(1, pile.getList().size());
		
		str = "{\"amount\":8.3,\"timestamp\":\"" + currentDate.getTimeInMillis() + "\"}";
		incomingData = new ByteArrayInputStream(str.getBytes());
		resp = restServiceTransaction.addTransaction(incomingData);
		assertEquals(201, resp.getStatus());
		assertEquals(2, pile.getList().size());
		
		Statistics statistics = restServiceTransaction.getStadisctics();
		assertTrue(statistics.getSum()==20.6);
		assertTrue(statistics.getAvg()==10.3);
		assertTrue(statistics.getMax()==12.3);
		assertTrue(statistics.getMin()==8.3);
		assertTrue(statistics.getCount()==2);
	}

}