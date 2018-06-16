package co.com.corp.maven.ProjectMvnRest.stack;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StackTest {

	private static Stack pile = Stack.getInstance();

	@Test
	public void shouldCreatePile() {

		pile.newNode(42385, 45000);
		pile.newNode(16234, 55000);
		pile.newNode(12654, 66000);
		pile.newNode(87984, 70000);
		pile.newNode(84520, 30000);
		assertTrue(pile.getList().size() == 5);
		pile.remove(16234);
		assertTrue(pile.getList().size() == 4);
		pile.delete();
		assertTrue(pile.isEmpty());

	}

}
