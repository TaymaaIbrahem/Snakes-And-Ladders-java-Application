package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Game;

class GameDateTest {
	//Test ID = 16
	@Test
	void testGameDate() {
		Game g1 = new Game("15/02/2024", 1, null);
		String date = g1.getGameDate();
		assertEquals(date, "15/02/2024");
		
	}

}
