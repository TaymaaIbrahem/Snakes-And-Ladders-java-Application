package model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.DeserializationException;

import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import Utils.PlayerColor;
import Utils.PlayerObject;




public class SysData {

	private static SysData instance;
	private HashMap<Integer,Question> allQuestions =new HashMap<Integer,Question>();
	private HashMap<Integer,Game> allHistory =new HashMap<Integer,Game>();
	private SysData() {}

	public static SysData getInstance() {
		if (instance == null)
			instance = new SysData();
		return instance;
	}


	//getters and setters
	public HashMap<Integer, Question> getAllQuestions() {
		
		return allQuestions;
	}

	public void setAllQuestions(HashMap<Integer, Question> allQuestions) {
		this.allQuestions = allQuestions;
	}

	public HashMap<Integer, Game> getAllHistory() {
		return allHistory;
	}

	public void setAllHistory(HashMap<Integer, Game> allHistory) {
		this.allHistory = allHistory;
	}

	//import questions from json method
	public void importQuestionsFromJSON(String path) {
		try (FileReader reader = new FileReader(new File(path))) {
			JsonObject doc = (JsonObject) Jsoner.deserialize(reader);
			JsonArray questionsArray = (JsonArray) doc.get("questions");

			int errors = 0;
			for (Object questionObject : questionsArray) {
				if (questionObject instanceof JsonObject) {
					JsonObject questionJson = (JsonObject) questionObject;
					
					//taking the values of the question's variables
					String questionText = (String) questionJson.get("question");
					JsonArray answersJson = (JsonArray) questionJson.get("answers");
					int correctAnswerNum = Integer.parseInt((String) questionJson.get("correct_ans"));
					int questionDifficulty = Integer.parseInt((String) questionJson.get("difficulty"));
					

					
					//creating new question
					model.Question question = new model.Question(
							questionText,
							jsonArrayToStringList(answersJson),
							correctAnswerNum , questionDifficulty
							);
					allQuestions.put(question.getQuestionNumber(), question);
				
					//print to make sure it worked as we desire
					System.out.println("Imported Question: " + question);
				}
			}

			System.out.println((errors == 0) ? "Questions imported successfully!" :
				String.format("Questions imported with %d errors!", errors));
		}catch (IOException | DeserializationException e) {
			e.printStackTrace();
		}
	}



	// Utility method to convert JsonArray to ArrayList<String>
	private ArrayList<String> jsonArrayToStringList(JsonArray jsonArray) {
		ArrayList<String> stringList = new ArrayList<>();
		for (Object element : jsonArray) {
			if (element instanceof String) {
				stringList.add((String) element);
			}
		}
		return stringList;
	}
	// Utility method to convert JsonArray to ArrayList<Player>
	private ArrayList<Player> jsonArrayToPlayerList(JsonArray playersJson) {
		ArrayList<Player> playerList = new ArrayList<>();
		for (Object element : playersJson) {
			if (element instanceof JsonObject) {
				JsonObject jsonObject = (JsonObject) element;

				String nickname = (String) jsonObject.get("nickname");
				PlayerColor color = PlayerColor.valueOf((String) jsonObject.get("color"));  // Assuming Color is an enum
				String playerObjectStr = (String) jsonObject.get("playerObject");
				PlayerObject playerObject = PlayerObject.valueOf(playerObjectStr);


				Player player = new Player(nickname, color, playerObject);
				playerList.add(player);
				// System.out.println(player.toString());
			}
		}
		return playerList;
	}
	//converting hashmap to arraylist
	private ArrayList<Question>  getQuestionASarray() {
		ArrayList<Question> questionsArray= new ArrayList<>();
		for(Question q : getAllQuestions().values()) {
			questionsArray.add(q);
		}
		return questionsArray;
	}
	//export questions to JSON file
	public void exportQuestionsToJSON() {
	    StringBuilder jsonBuilder = new StringBuilder();
	    jsonBuilder.append("{\n");
	    jsonBuilder.append("  \"questions\": [\n");

	    ArrayList<Question> importedQuestions = getQuestionASarray();

	    // I use the append to guarntee the same order as the customer asked
	    for (int i = 0; i < importedQuestions.size(); i++) {
	        Question question = importedQuestions.get(i);

	        jsonBuilder.append("    {\n");
	        jsonBuilder.append("      \"question\": \"").append(question.getQuestion()).append("\",\n");
	        jsonBuilder.append("      \"answers\": [\n");

	        // Append answers array elements
	        for (int j = 0; j < question.getAnswers().size(); j++) {
	            jsonBuilder.append("        \"").append(question.getAnswers().get(j)).append("\"");

	            if (j < question.getAnswers().size() - 1) {
	                jsonBuilder.append(",\n");
	            } else {
	                jsonBuilder.append("\n");
	            }
	        }

	        jsonBuilder.append("      ],\n");
	        jsonBuilder.append("      \"correct_ans\": \"").append(question.getCorrectAnswerNum()).append("\",\n");
	        jsonBuilder.append("      \"difficulty\": \"").append(question.getQuestionDifficulty()).append("\"\n");
	        jsonBuilder.append("    }");

	        if (i < importedQuestions.size() - 1) {
	            jsonBuilder.append(",");
	        }

	        jsonBuilder.append("\n");
	    }

	    jsonBuilder.append("  ]\n");
	    jsonBuilder.append("}");

	    File file = new File("json/questions.json");
	    file.getParentFile().mkdirs();

	    try (FileWriter writer = new FileWriter(file)) {
	        writer.write(jsonBuilder.toString());
	        System.out.println("Questions data exported successfully!");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	//import history from json method
	public void importHistoryFromJSON(String path) {
		try (FileReader reader = new FileReader(new File(path))) {
			JsonObject doc = (JsonObject) Jsoner.deserialize(reader);
			JsonArray historyArray = (JsonArray) doc.get("history");

			int errors = 0;
			for (Object historyObject : historyArray) {
				if (historyObject instanceof JsonObject) {
					JsonObject historyJson = (JsonObject) historyObject;


					String gameDate = (String) historyJson.get("gameDate");
					String winnerNickName = (String) historyJson.get("winnerNickName");
					int gameDifficulty =  ((BigDecimal)historyJson.get("gameDifficulty")).intValue();
					String gameDuration = (String) historyJson.get("gameDuration");
					                   
					//creating new game class
					model.Game history = new model.Game(
							gameDate,winnerNickName,gameDifficulty,gameDuration,
							jsonArrayToPlayerList((JsonArray) historyJson.get("players"))

							);
					allHistory.put(history.getGameID(), history);
				

					// For demonstration purposes, just printing the question
					System.out.println("Imported History: " + history);
				}
			}

			System.out.println((errors == 0) ? "History imported successfully!" :
				String.format("History imported with %d errors!", errors));
		}catch (IOException | DeserializationException e) {
			e.printStackTrace();
		}
	}

	//export to JSON file
	public void exportHistoryToJSON() {
		JsonArray historyArray = new JsonArray();
		  ArrayList<Game> importedHistory = getHistoryASarray(allHistory);
		 

		for (Game gg : importedHistory) {
			JsonObject historyJson = new JsonObject();

			historyJson.put("gameDate", gg.getGameDate());
			historyJson.put("winnerNickName", gg.getWinnerNickName());
			historyJson.put("gameDifficulty", gg.getGameDifficulty());
			historyJson.put("gameDuration", gg.getGameDuration());

			// Convert the list of players to a JsonArray
			JsonArray playersArray = new JsonArray();
			for (Player player : gg.getPlayers()) {
				JsonObject playerJson = new JsonObject();
				playerJson.put("nickname", player.getNickname());
				playerJson.put("color", player.getColor().name());  // Assuming 'getColor' returns a Color enum
				playerJson.put("playerObject", player.getObject().name());  // Assuming 'getPlayerObject' returns a PlayerObject enum

				playersArray.add(playerJson);
			}
			historyJson.put("players", playersArray);

			historyArray.add(historyJson);
		}

		JsonObject doc = new JsonObject();
		doc.put("history", historyArray);

		File file = new File("json/history.json");
		file.getParentFile().mkdir();

		try (FileWriter writer = new FileWriter(file)) {
			writer.write(Jsoner.prettyPrint(doc.toJson()));
			writer.flush();
			System.out.println("History data exported successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//help method that convert hashmap to arraylist
	public ArrayList<Game> getHistoryASarray(HashMap<Integer, Game> allHistory2) {
		ArrayList<Game> historysArray= new ArrayList<>();
		for(Game h : allHistory.values()) {
			historysArray.add(h);
		}
		return historysArray;

	}

}
