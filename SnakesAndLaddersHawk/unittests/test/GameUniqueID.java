package test;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import model.Game;

public class GameUniqueID {

	@Test
	public void test() {
		
		Game g1 = new Game("14/02/2024", 1, null);
		Game g2 = new Game("15/02/2024", 1, null);
		Boolean isUnique =( g1.getGameID()!=g2.getGameID());
		assertEquals("the ID`s are unique", true, isUnique);
	}

}
