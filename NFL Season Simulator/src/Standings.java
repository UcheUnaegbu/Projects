import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**Class to represent and update the team Standings throughout the season**/
public class Standings {
	
	//represent the teams standings within their respective conferences
	public static Team[] afc_standings = new Team[16];
	public static Team[] nfc_standings = new Team[16];
	
	//represent the teams standings within their respective divisions
	public static Team[] afc_west_standings = new Team[4];
	public static Team[] afc_east_standings = new Team[4];
	public static Team[] afc_north_standings = new Team[4];
	public static Team[] afc_south_standings = new Team[4];
	public static Team[] nfc_west_standings = new Team[4];
	public static Team[] nfc_east_standings = new Team[4];
	public static Team[] nfc_north_standings = new Team[4];
	public static Team[] nfc_south_standings = new Team[4];
	
	/**Initializes the standings at the beginning of the season**/
	public static void initStandings() {
		
		for (int i = 0; i < 16; i++) {
			afc_standings[i] = Teams.afc.get(i);
			nfc_standings[i] = Teams.nfc.get(i);
		}
		
		for (int i = 0; i < 4; i++) {
			afc_west_standings[i] = Teams.afc_west.get(i);
			afc_east_standings[i] = Teams.afc_east.get(i);
			afc_south_standings[i] = Teams.afc_south.get(i);
			afc_north_standings[i] = Teams.afc_north.get(i);
			
			nfc_west_standings[i] = Teams.nfc_west.get(i);
			nfc_east_standings[i] = Teams.nfc_east.get(i);
			nfc_south_standings[i] = Teams.nfc_south.get(i);
			nfc_north_standings[i] = Teams.nfc_north.get(i);
			
		}
		
	}
	
	/**Updates the standings as the season progresses**/
	public static void updateStandings() {
		
		updateDivisionStandings();	//updates divisional standings first
		updateConferenceStandings();	//continues to update conference standings
		
	}
	
	/**Sorts a single division's standings
	 * Handles tie breakers accordingly**/
	private static void updateSingleDivisionStandings(Team[] division) {
		
		//sorts each divisions standings by bubble sort
		for (int i = 3; i > 0; i--) {
			
			for (int j = 0; j < i; j++) {
				
				Team t1 = division[j], t2 = division[j+1];
				
				//compares total win percentages
				if (t1.totalWinPercentage() < t2.totalWinPercentage()) {
					division[j] = t2;
					division[j+1] = t1;
				}
				
				//case where teams have identical total win percentages
				else if (t1.totalWinPercentage() == t2.totalWinPercentage()) {
					
					//checks head-to-head win percentages
					ArrayList<Team> temp1 = new ArrayList<Team>();
					ArrayList<Team> temp2 = new ArrayList<Team>();
					temp1.add(t2);
					temp2.add(t1);
					
					double t1_h2h = percentageAgainstOpponents(t1, temp1);
					double t2_h2h = percentageAgainstOpponents(t2, temp2);
					
					//compares head to head percentages
					if (t1_h2h < t2_h2h) {
						division[j] = t2;
						division[j+1] = t1;
					}
					
					//checks if head to head was equal
					else if (t1_h2h == t2_h2h) {
						
						//checks division win percentages
						if (t1.divisionWinPercentage() < t2.divisionWinPercentage()) {
							division[j] = t2;
							division[j+1] = t1;
						}
						//division win percentages the same
						else if (t1.divisionWinPercentage() == t2.divisionWinPercentage()) {
							
							ArrayList<Team> common = findCommonOpponents(t1, t2);	//finds common opponents between teams
							
							//calculates percentages for commons games
							double t1_comm_perc = percentageAgainstOpponents(t1, common);
							double t2_comm_perc = percentageAgainstOpponents(t2, common);
							
							//compares common games percentages
							if (t1_comm_perc < t2_comm_perc) {
								division[j] = t2;
								division[j+1] = t1;
							}
							
							//if common games percentage is equal
							else if (t1_comm_perc == t2_comm_perc) {
								
								//checks conference win percentages
								if (t1.conferenceWinPercentage() < t2.conferenceWinPercentage()) {
									division[j] = t2;
									division[j+1] = t1;
								}
								
							}
							
						}
											
					}
					
				}
				
			}
			
		}
		
	}
	
	/**Updates divisional standings**/
	private static void updateDivisionStandings() {
		
		//individually updates each division's standings
		updateSingleDivisionStandings(afc_west_standings);
		updateSingleDivisionStandings(afc_east_standings);
		updateSingleDivisionStandings(afc_south_standings);
		updateSingleDivisionStandings(afc_north_standings);
		updateSingleDivisionStandings(nfc_west_standings);
		updateSingleDivisionStandings(nfc_east_standings);
		updateSingleDivisionStandings(nfc_south_standings);
		updateSingleDivisionStandings(nfc_north_standings);
		
		int counter = 0;
		
		//maintains the ordering of division standings within the conference standings
		for (int i = 0; i < 4; i++) {
			
			afc_standings[i] = afc_west_standings[counter];
			nfc_standings[i] = nfc_west_standings[counter];
			
			counter++;
			
		}
		counter = 0;
		for (int i = 4; i < 8; i++) {
			
			afc_standings[i] = afc_east_standings[counter];
			nfc_standings[i] = nfc_east_standings[counter];
			
			counter++;
			
		}
		counter = 0;
		for (int i = 8; i < 12; i++) {
			
			afc_standings[i] = afc_south_standings[counter];
			nfc_standings[i] = nfc_south_standings[counter];
			
			counter++;
			
		}
		counter = 0;
		for (int i = 12; i < 16; i++) {
			
			afc_standings[i] = afc_north_standings[counter];
			nfc_standings[i] = nfc_north_standings[counter];
			
			counter++;
			
		}
		
	}
	
	/**Updates the standings for a single conference
	 * Tie breakers handled accordingly**/
	private static void updateSingleConferenceStandings(Team[] conference) {
		
		//sorts standings of conference using bubble sort
		for (int i = 15; i > 0; i--) {
			
			for (int j = 0; j < i; j++) {
				
				Team t1 = conference[j], t2 = conference[j+1];
				
				//skips over comparing teams in same division
				//as the division tie breakers/ordering has already
				//been set
				if (t1.getDivision() == t2.getDivision()) {
					continue;
				}
				
				//swap positions if team has lower total win percentage than next team
				if (t1.totalWinPercentage() < t2.totalWinPercentage()) {
					
					conference[j] = t2;
					conference[j+1] = t1;
					
				}
				
				//handles case where teams have same total win percentage
				else if (t1.totalWinPercentage() == t2.totalWinPercentage()) {
					
					//checks head-to-head win percentages
					ArrayList<Team> temp1 = new ArrayList<Team>();
					ArrayList<Team> temp2 = new ArrayList<Team>();
					temp1.add(t2);
					temp2.add(t1);
					
					double t1_h2h = percentageAgainstOpponents(t1, temp1);
					double t2_h2h = percentageAgainstOpponents(t2, temp2);
					
					//compares head to head percentages
					if (t1_h2h < t2_h2h) {
						conference[j] = t2;
						conference[j+1] = t1;
					}
					
					//head to head records the same
					else if (t1_h2h == t2_h2h) {
						
						//swaps if team has lower conference win percentage
						if (t1.conferenceWinPercentage() < t2.conferenceWinPercentage()) {
							
							conference[j] = t2;
							conference[j+1] = t1;
							
						}
						
						//handles case where teams have same conference win percentage
						else if (t1.conferenceWinPercentage() == t2.conferenceWinPercentage()) {
							
							//checks record for common opponents played
							ArrayList<Team> common = findCommonOpponents(t1, t2);
							double team1_comm_perc = percentageAgainstOpponents(t1, common);
							double team2_comm_perc = percentageAgainstOpponents(t2, common);
							
							if (team1_comm_perc < team2_comm_perc) {
								conference[j] = t2;
								conference[j+1] = t1;
							}
							
						}
						
					}
									
				}
				
			}
			
		}
		
	}
	
	/**Updates standings for both conferences**/
	private static void updateConferenceStandings() {
		
		//updates each conference individually
		updateSingleConferenceStandings(afc_standings);
		updateSingleConferenceStandings(nfc_standings);
		
	}
	
	/**Returns win percentage for specified team against a
	 * specific set of opponents**/
	private static double percentageAgainstOpponents(Team team, ArrayList<Team> opponents) {
		
		/*Sets up the decimal format so that it includes up to 3 decimal places*/
		DecimalFormat df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.CEILING);
		
		int numGames = 0;
		int numWins = 0;
		int numTies = 0;
		double adjustedWins;
		
		for (String week: team.schedule.keySet()) {
			Game g = team.schedule.get(week);
			
			Team opponent;
			
			if (g.team1 == team) {
				opponent = g.team2;
			}
			else {
				opponent = g.team1;
			}
			
			//checks if opponent is in list of opponents searching for
			//also checks if game has been played
			if (opponents.contains(opponent) && g.gamePlayed()) {
				
				numGames++;
				
				if (g.winner == team) {
					numWins++;
				}
				else if (g.winner == null) {
					numTies++;
				}
				
			}
			
		}
		
		//checks if team has no played games against opponents in specified list
		if (numGames == 0) {
			return 0;
		}
		
		adjustedWins = (double)numWins + (double)(.5*numTies);	//gets the adjusted wins
		String temp = df.format(adjustedWins/(double)numGames);
		double result = Double.valueOf(temp);
		
		return result;
		
	}
	
	
	/**Returns arraylist of all teams that both specified teams played that season**/
	private static ArrayList<Team> findCommonOpponents(Team team1, Team team2) {
		
		ArrayList<Team> team1_opponents = new ArrayList<Team>();	//holds unique team1 opponents
		ArrayList<Team> common = new ArrayList<Team>();
		
		//Finds the game team1 played during each week
		for (String week: team1.schedule.keySet()) {
			Game g = team1.schedule.get(week);
			
			Team opponent;
			
			//selects the opponent from the week
			if (g.team1 == team1) {
				opponent = g.team2;
			}
			else {
				opponent = g.team1;
			}
			
			if (!team1_opponents.contains(opponent)) {
				team1_opponents.add(opponent);
			}
			
		}
		
		
		//Goes through team2 opponents
		for (String week: team2.schedule.keySet()) {
			Game g = team2.schedule.get(week);
			
			Team opponent;
			
			//selects the opponent from the week
			if (g.team1 == team2) {
				opponent = g.team2;
			}
			else {
				opponent = g.team1;
			}
			
			//ensures team1 also faced similar team
			if (team1_opponents.contains(opponent) && !common.contains(opponent)) {
				common.add(opponent);
			}
			
		}
		
		return common;
		
	}
	
	
	/**String representation of a team's standings**/
	private static String teamStandings(Team team) {
		
		String result = team.abbr + ": W-L-T ";
		
		result += "(" + team.totalWins + "-" + team.totalLosses + "-" + team.totalTies + ")";
		result += " | PCT " + "(" + team.totalWinPercentage() + ")"; 
		result += " | DIV " + "(" + team.divWins + "-" + team.divLosses + "-" + team.divTies + ")";
		result += " | CONF " + "(" + team.confWins + "-" + team.confLosses + "-" + team.confTies + ")";
		
		return result;
		
	}
	
	/**Prints standings from specified conference**/
	private static void printConferenceStandings(Team[] conference) {
		
		for (int i = 0; i < 16; i++) {
			int standing = i+1;
			System.out.print(standing + ". ");
			
			System.out.println(teamStandings(conference[i]));
		}
		
	}
	
	/**Prints standings from specified division**/
	private static void printDivisionStandings(Team[] division) {
		
		for (int i = 0; i < 4; i++) {
			int standing = i+1;
			System.out.print(standing + ". ");
			
			System.out.println(teamStandings(division[i]));
		}
		
	}
	
	/**Prints both Conference and Divisional Standings**/
	public static void printStandings() {
		
		System.out.print("| AFC STANDINGS | \n\n");
		printConferenceStandings(afc_standings);
		
		System.out.print("\n\n");
		
		System.out.print("| AFC WEST | \n\n");
		printDivisionStandings(afc_west_standings);
		System.out.print("\n");
		System.out.print("| AFC EAST | \n\n");
		printDivisionStandings(afc_east_standings);
		System.out.print("\n");
		System.out.print("| AFC NORTH | \n\n");
		printDivisionStandings(afc_north_standings);
		System.out.print("\n");
		System.out.print("| AFC SOUTH | \n\n");
		printDivisionStandings(afc_south_standings);
		
		System.out.print("\n\n");
		System.out.println("----------------------------------------------------------------------------------");
		System.out.print("\n\n");
		
		System.out.print("| NFC STANDINGS | \n\n");
		printConferenceStandings(nfc_standings);
		
		System.out.print("\n\n");
		
		System.out.print("| NFC WEST | \n\n");
		printDivisionStandings(nfc_west_standings);
		System.out.print("\n");
		System.out.print("| NFC EAST | \n\n");
		printDivisionStandings(nfc_east_standings);
		System.out.print("\n");
		System.out.print("| NFC NORTH | \n\n");
		printDivisionStandings(nfc_north_standings);
		System.out.print("\n");
		System.out.print("| NFC SOUTH | \n\n");
		printDivisionStandings(nfc_south_standings);
		System.out.println("\n");
		
	}
	
}
