
package co.com.corp.maven.ProjectMvnRest;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.com.corp.maven.ProjectMvnRest.pojo.Statistics;
import co.com.corp.maven.ProjectMvnRest.pojo.Transaction;
import co.com.corp.maven.ProjectMvnRest.stack.Stack;
import co.com.corp.maven.ProjectMvnRest.util.Utils;

/**
 * RestService class 
 * @author Andreta
 *
 */
@Path("/")
public class RestServiceTransaction {
	
	private static final int TRANSACTION_TIME = 60000;
	
	private static Stack pile = Stack.getInstance();

	/**
	 * Method POST to obtain the transaction
	 * @param incomingData
	 * @return Response
	 * @throws IOException
	 */
	@POST
	@Path("/transactions")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTransaction(InputStream incomingData) throws IOException {
		Transaction transaction = Utils.jsonTransformer(incomingData);
		long calendar = Utils.getOldTime(transaction.getTimestamp());
		if (calendar < TRANSACTION_TIME ) {
			pile.newNode(transaction.getTimestamp(), transaction.getAmount());
			return Response.status(201).build();
		} else {
			return Response.status(204).build();
		}
	}

	/**
	 * Method GET to get the Statistics
	 * @param incomingData
	 * @return Statistics
	 * @throws IOException
	 */
	@GET
	@Path("/stadistics")
	@Produces(MediaType.APPLICATION_JSON)
	public Statistics getStadisctics() throws IOException {
		pile.removeOld();
		Statistics statistics = Utils.getStatistics(pile.getList());
		return statistics;
	}
}
