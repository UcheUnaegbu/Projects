/**Class representing QB stats and Team stats**/
public class Stats {
	
	//fields that will hold and represent the ordering of qbs' stats and teams' stats
	private static Team[] qb_rankings = new Team[32];
	private static Team[] offensive_rankings = new Team[32];
	private static Team[] defensive_rankings = new Team[32];
	
	/**Initializes QB rankings
	 * qb_rankings will actually store the team that has
	 * the qb with the specific ranking. however,
	 * when info is printed and stats updated, will use
	 * the actual qb**/
	private static void initQBRankings() {
		
		for (int i = 0; i < 32; i++) {
			qb_rankings[i] = Teams.allTeams.get(i);
		}
		
	}
	
	/**Initializes Team Offensive Rankings**/
	private static void initOffensiveRankings() {
		
		for (int i = 0; i < 32; i++) {
			
			for (Team t: Teams.allTeams) {
				if (t.offRanking == i+1) {
					offensive_rankings[i] = t;
				}
			}
			
		}
		
	}
	
	/**Initializes Team Defensive Rankings**/
	private static void initDefensiveRankings() {
		
		for (int i = 0; i < 32; i++) {
			
			for (Team t: Teams.allTeams) {
				if (t.defRanking == i+1) {
					defensive_rankings[i] = t;
				}
			}
			
		}
		
	}
	
	/**Prints the QB rankings**/
	public static void printQBRankings() {
		
		System.out.println("| QB Rankings | \n");
		
		for (int i = 0; i < 32; i++) {
			
			int rank = i+1;
			
			String result = "" + rank + ". ";
			result += qb_rankings[i].getQB().name;
			result += " (" + qb_rankings[i].abbr + ")";
			result += " - " + qb_rankings[i].getQB().tds + " TDs"; 
			
			System.out.println(result);
			
		}
		
		System.out.println("\n");
		
	}
	
	/**Prints team offensive rankings**/
	public static void printOffensiveRankings() {
		
		System.out.println("| Offensive Rankings | \n");
		
		for (int i = 0; i < 32; i++) {
			
			int rank = i+1;
			
			String result = "" + rank + ". ";
			result += offensive_rankings[i].abbr;
			result += " - " + offensive_rankings[i].PPG() + " PTS/G"; 
			
			System.out.println(result);
			
		}
		
		System.out.println("\n");
		
	}
	
	/**Prints team Defensive rankings**/
	public static void printDefensiveRankings() {
		
		System.out.println("| Defensive Rankings | \n");
		
		for (int i = 0; i < 32; i++) {
			
			int rank = i+1;
			
			String result = "" + rank + ". ";
			result += defensive_rankings[i].abbr;
			result += " - " + defensive_rankings[i].APPG() + " PTS/G"; 
			
			System.out.println(result);
			
		}
		
		System.out.println("\n");
		
	}
	
	/**Updates the QB rankings list as the season progresses**/
	private static void updateQBRankings() {
		
		//sorts the list using bubble sort
		for (int i = 31; i > 0; i--) {
			
			for (int j = 0; j < i; j++) {
				
				if (qb_rankings[j].getQB().tds < qb_rankings[j+1].getQB().tds) {
					Team temp = qb_rankings[j];
					qb_rankings[j] = qb_rankings[j+1];
					qb_rankings[j+1] = temp;
				}
				
			}
			
		}
		
	}
	
	/**Updates the offensive rankings list as the season progresses**/
	private static void updateOffensiveRankings() {
		
		//sorts the list using bubble sort
		for (int i = 31; i > 0; i--) {
			
			for (int j = 0; j < i; j++) {
				
				if (offensive_rankings[j].PPG() < offensive_rankings[j+1].PPG()) {
					Team temp = offensive_rankings[j];
					offensive_rankings[j] = offensive_rankings[j+1];
					offensive_rankings[j+1] = temp;
				}
				
			}
			
		}
		
		//Ensure the teams' actual offensive rankings are adjusted
		//and reflected as the season progresses
		for (int i = 0; i < 32; i++) {
			offensive_rankings[i].offRanking = i+1;
		}
		
	}
	
	/**Updates the defensive rankings list as the season progresses**/
	private static void updateDefensiveRankings() {
		
		//sorts the list using bubble sort
		for (int i = 31; i > 0; i--) {
			
			for (int j = 0; j < i; j++) {
				
				if (defensive_rankings[j].APPG() > defensive_rankings[j+1].APPG()) {
					Team temp = defensive_rankings[j];
					defensive_rankings[j] = defensive_rankings[j+1];
					defensive_rankings[j+1] = temp;
				}
				
			}
			
		}
		
		//Ensure the teams' actual defensive rankings are adjusted
		//and reflected as the season progresses
		for (int i = 0; i < 32; i++) {
			defensive_rankings[i].defRanking = i+1;
		}
		
	}
	
	/**Initializes all stats and rankings**/
	public static void initRankings() {
		initQBRankings();
		initOffensiveRankings();
		initDefensiveRankings();
	}
	
	/**Uodates all stats and rankings**/
	public static void updateRankings() {
		updateQBRankings();
		updateOffensiveRankings();
		updateDefensiveRankings();
	}
	
}
