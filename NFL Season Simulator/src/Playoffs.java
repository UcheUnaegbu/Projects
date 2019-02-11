import java.util.ArrayList;

/**Class that represents the playoff
 * schedule, which is determined by
 * the results of the regular season**/
public class Playoffs {

	//fields for the 6 teams that seeded into the 
	//playoffs for each conference
	private Team[] afc_seed = new Team[6];
	private Team[] nfc_seed = new Team[6];
	
	//wild card games
	private Game[] afc_wildCard = new Game[2];
	private Game[] nfc_wildCard = new Game[2];
	
	//divisional games
	private Game[] afc_divisional = new Game[2];
	private Game[] nfc_divisional = new Game[2];
	
	//conference championship games
	private Game afc_championship, nfc_championship;
	
	//superbowl
	private Game superbowl;
	
	public static boolean playoffsStarted = false;
	
	boolean wildCardPlayed = false, divisionalPlayed = false, championshipPlayed = false, sbPlayed = false;
	
	public Playoffs() {
		playoffsStarted = true;
	}
	
	/**Appropriately sets the playoff seedings for each conference**/
	public void setPlayoffSeedings() {
		
		setConferenceSeedings(Standings.afc_standings, afc_seed);	//afc seedings
		setConferenceSeedings(Standings.nfc_standings, nfc_seed);	//nfc seedings
		
	}
	
	/**Sets the seedings for an individual conference**/
	private void setConferenceSeedings(Team[] conference, Team[] seedings) {
		
		ArrayList<Team> divisionWinners = new ArrayList<Team>();
		ArrayList<Team> wildCard = new ArrayList<Team>();
		
		for (int i = 0; i < 16; i++) {
			
			Team team = conference[i];
			
			//checks if all division winners decided
			if (divisionWinners.size() == 4) {
				
				//checks if wild card spots filled
				if (wildCard.size() == 2) {
					break;
				}
				
				wildCard.add(team);	//adds the team as a wild card team
				
			}
			
			else if (!divisionWinnerDecided(team, divisionWinners)) {
				divisionWinners.add(team);	//add team to division winners if division winner not yet decided
			}
			
			else if (wildCard.size() != 2) {
				wildCard.add(team);	//adds team as wildcard team if both wild card teams not decided
			}
			
		}
		
		//sets the seedings accordingly
		for (int i = 0; i < 4; i++) {
			seedings[i] = divisionWinners.get(i);
			seedings[i].seeding = i+1;
		}
		for (int i = 0; i < 2; i++) {
			seedings[i+4] = wildCard.get(i);
			seedings[i+4].seeding = i+5;
		}
		
	}
	
	
	/**Returns true if division winner for the specified
	 * team's division was already decided in the playoff
	 * picture**/
	private boolean divisionWinnerDecided(Team team, ArrayList<Team> divisionWinners) {
		
		for (Team divWinner: divisionWinners) {
			if (team.getDivision() == divWinner.getDivision()) {
				return true;
			}
		}
		
		return false;
		
	}
	
	
	/**Sets the wild card games.
	 * From each conference, the 3rd seeded
	 * team will play the 6th seeded team. The
	 * 4th seeded team will play the 5th seeded team**/
	public void setWildCardGames() {
		
		afc_wildCard[0] = new Game(afc_seed[2], afc_seed[5]);
		afc_wildCard[1] = new Game(afc_seed[3], afc_seed[4]);
		
		nfc_wildCard[0] = new Game(nfc_seed[2], nfc_seed[5]);
		nfc_wildCard[1] = new Game(nfc_seed[3], nfc_seed[4]);
		
	}
	
	
	/**Plays the wild card round of the playoffs**/
	public void playWildCardRound() {
		
		for (int i = 0; i < 2; i++) {
			afc_wildCard[i].playGame();
			nfc_wildCard[i].playGame();
		}
		
		wildCardPlayed = true;
		
	}
	
	/**Sets divisional round games. For each
	 * conference, the 1st seeded team will play the
	 * lowest available seed left in contention. The
	 * 2nd seeded team will play the other**/
	public void setDivisionalGames() {
		
		if (afc_wildCard[0].winner.seeding > afc_wildCard[1].winner.seeding) {
			afc_divisional[0] = new Game(afc_seed[0], afc_wildCard[0].winner);
			afc_divisional[1] = new Game(afc_seed[1], afc_wildCard[1].winner);
		}
		else {
			afc_divisional[0] = new Game(afc_seed[0], afc_wildCard[1].winner);
			afc_divisional[1] = new Game(afc_seed[1], afc_wildCard[0].winner);
		}
		
		if (nfc_wildCard[0].winner.seeding > nfc_wildCard[1].winner.seeding) {
			nfc_divisional[0] = new Game(nfc_seed[0], nfc_wildCard[0].winner);
			nfc_divisional[1] = new Game(nfc_seed[1], nfc_wildCard[1].winner);
		}
		else {
			nfc_divisional[0] = new Game(nfc_seed[0], nfc_wildCard[1].winner);
			nfc_divisional[1] = new Game(nfc_seed[1], nfc_wildCard[0].winner);
		}
		
	}
	
	
	/**Plays the divisional round of the playoffs**/
	public void playDivisionalRound() {
		
		for (int i = 0; i < 2; i++) {
			afc_divisional[i].playGame();
			nfc_divisional[i].playGame();
		}
		
		divisionalPlayed = true;
		
	}
	
	
	/**Sets the conference championship games. For
	 * each conference, the last 2 remaining teams
	 * will play each other**/
	public void setChampionshipGames() {
		
		afc_championship = new Game(afc_divisional[0].winner, afc_divisional[1].winner);
		nfc_championship = new Game(nfc_divisional[0].winner, nfc_divisional[1].winner);
		
	}
	
	/**Plays the conference championship games**/
	public void playChampionshipGames() {
		
		afc_championship.playGame();
		nfc_championship.playGame();
		
		championshipPlayed = true;
		
	}
	
	/**Sets the teams who will play in the SuperBowl.
	 * NFC Champions vs. AFC Champions**/
	public void setSuperBowl() {
		superbowl = new Game(afc_championship.winner, nfc_championship.winner);
	}
	
	/**Plays the SuperBowl**/
	public void playSuperBowl() {
		superbowl.playGame();
		
		sbPlayed = true;
	}
	
	/**String representation of playoff bracket**/
	public String playoffBracket() {
		
		//first afc wild card game slot
		String result = firstAFCWildCardGame();
		
		result += "---------------------- \n";	
		result += "                     | \n";		
		result += "                     -------- ";
		
		//first afc divisional game slot
		result += firstAFCDivisionalGame();
		
		result += "                               ---------------------- \n";
		result += "                                                     | \n";
		result += "                                                     -------- ";
		
		//afc championship game slot
		result += AFCChampionshipGame();
		
		result += "                                                     | \n";
		result += "                               ---------------------- \n";
		result += "                              ";
		
		//second afc divisional game slot
		result += secondAFCDivisionalGame();
		
		result += "                     | \n";
		result += "---------------------- \n";
		
		//second afc wild card game slot
		result += secondAFCWildCardGame();
		
		result += "                                               ************** \n";
		result += "                                               * Super Bowl *\n";
		result += "                                               ************** \n\n";
		result += "                                               ";
		
		//superbowl game slot
		result += superBowlGame();
		
		//first nfc wild card game slot
		result += firstNFCWildCardGame();
		
		result += "---------------------- \n";	
		result += "                     | \n";		
		result += "                     -------- ";
		
		//first nfc divisional game slot
		result += firstNFCDivisionalGame();
		
		result += "                               ---------------------- \n";
		result += "                                                     | \n";
		result += "                                                     -------- ";
		
		//nfc championship game slot
		result += NFCChampionshipGame();
		
		result += "                                                     | \n";
		result += "                               ---------------------- \n";
		result += "                              ";
		
		//second nfc divisional game slot
		result += secondNFCDivisionalGame();
		
		result += "                     | \n";
		result += "---------------------- \n";
		
		//second nfc wild card game slot
		result += secondNFCWildCardGame();
		result += "\n";
		
		return result;
		
	}
	
	//helper methods for playoff bracket
	private String superBowlGame() {
		
		String result;
		
		if (!championshipPlayed) {
			result = "      TBD";
		}
		else {
			result = superbowl.team1.seeding + ". " + superbowl.team1.abbr + " " + superbowl.team1.record() + "  ";
		}
		
		if (sbPlayed && superbowl.winner == superbowl.team1) {
			result += "W  *SUPERBOWL CHAMPIONS*";
		}
		
		result += "\n";
		
		if (sbPlayed) {
			result += "                                               ";
			
			result += superbowl.team1.getQB().name + " {" + superbowl.team1_qb_tds + " TDs} \n";
			result += "                                                      " + superbowl.team1_score + "\n\n";
			
			result += "                                                      VS ";
			
			if (superbowl.OT) {
				result += "(OT)";
			}
			
			result += "\n\n";
			
			result += "                                                      " + superbowl.team2_score + "\n";
			result += "                                               ";
			result += superbowl.team2.seeding + ". " + superbowl.team2.abbr + " " + superbowl.team2.record() + "  ";
			
			if (superbowl.winner == superbowl.team2) {
				result += "W  *SUPERBOWL CHAMPIONS*";
			}
			
			result += "\n";
			result += "                                               ";
			result += superbowl.team2.getQB().name + " {" + superbowl.team2_qb_tds + " TDs} \n";			
			
		}
		
		else if (championshipPlayed) {
			result += "\n";
			result += "                                                      VS ";
			
			result += "\n\n";
			
			result += "                                               ";
			result += superbowl.team2.seeding + ". " + superbowl.team2.abbr + " " + superbowl.team2.record() + " \n ";
		}
		
		else {
			result += "\n";
			result += "                                                      VS ";
			
			result += "\n\n";
			
			result += "                                                     TBD \n";
		}
		
		return result;
	}
	
	private String secondAFCDivisionalGame() {
		
		String result = afc_seed[1].seeding + ". " + afc_seed[1].abbr +
				" (" +  afc_seed[1].totalWins + "-" + afc_seed[1].totalLosses +
				"-" + afc_seed[1].totalTies + ")  ";
		
		if (divisionalPlayed && afc_seed[1] == afc_divisional[1].winner) {
			result += "W";
		}
		
		result += "\n";
		result += "                              ";
		
		if (divisionalPlayed) {
			result += afc_divisional[1].team1.getQB().name + " {" + afc_divisional[1].team1_qb_tds + " TDs} \n";
			result += "                                     " + afc_divisional[1].team1_score + "\n\n";
			result += "                                     VS ";
			
			if (afc_divisional[1].OT) {
				result += "(OT)";
			}
			
			result += "\n\n";
			result += "                                     " + afc_divisional[1].team2_score + "\n";
			result += "                              " + afc_divisional[1].team2.seeding + ". " +
					afc_divisional[1].team2.abbr + " " + afc_divisional[1].team2.record() + "  ";
			
			if (afc_divisional[1].winner == afc_divisional[1].team2) {
				result += "W";
			}
			result += "\n";
			result += "                     -------- " + afc_divisional[1].team2.getQB().name + 
					" {" + afc_divisional[1].team2_qb_tds + " TDs} \n";
		}
		
		else if (wildCardPlayed) {
			
			result += "\n                                     VS \n\n";
			result += "                     -------- ";;
			result += afc_divisional[1].team2.seeding + ". " + afc_divisional[1].team2.abbr +
					" (" +  afc_divisional[1].team2.totalWins + "-" + afc_divisional[1].team2.totalLosses +
					"-" + afc_divisional[1].team2.totalTies + ")  \n";
			
		}
		
		else {
			result += "\n                                      VS \n\n";
			result += "                     --------         TBD \n";
		}
		
		return result;
		
	}
	
	private String secondNFCDivisionalGame() {
		
		String result = nfc_seed[1].seeding + ". " + nfc_seed[1].abbr +
				" (" +  nfc_seed[1].totalWins + "-" + nfc_seed[1].totalLosses +
				"-" + nfc_seed[1].totalTies + ")  ";
		
		if (divisionalPlayed && nfc_seed[1] == nfc_divisional[1].winner) {
			result += "W";
		}
		
		result += "\n";
		result += "                              ";
		
		if (divisionalPlayed) {
			result += nfc_divisional[1].team1.getQB().name + " {" + nfc_divisional[1].team1_qb_tds + " TDs} \n";
			result += "                                     " + nfc_divisional[1].team1_score + "\n\n";
			result += "                                     VS ";
			
			if (nfc_divisional[1].OT) {
				result += "(OT)";
			}
			
			result += "\n\n";
			result += "                                     " + nfc_divisional[1].team2_score + "\n";
			result += "                              " + nfc_divisional[1].team2.seeding + ". " +
					nfc_divisional[1].team2.abbr + " " + nfc_divisional[1].team2.record() + "  ";
			
			if (nfc_divisional[1].winner == nfc_divisional[1].team2) {
				result += "W";
			}
			result += "\n";
			result += "                     -------- " + nfc_divisional[1].team2.getQB().name + 
					" {" + nfc_divisional[1].team2_qb_tds + " TDs} \n";
		}
		
		else if (wildCardPlayed) {
			
			result += "\n                                     VS \n\n";
			result += "                     -------- ";;
			result += nfc_divisional[1].team2.seeding + ". " + nfc_divisional[1].team2.abbr +
					" (" +  nfc_divisional[1].team2.totalWins + "-" + nfc_divisional[1].team2.totalLosses +
					"-" + nfc_divisional[1].team2.totalTies + ")  \n";
			
		}
		
		else {
			result += "\n                                      VS \n\n";
			result += "                     --------         TBD \n";
		}
		
		return result;
		
	}
	
	private String AFCChampionshipGame() {
		
		String result;
		
		if (!divisionalPlayed) {
			result = "TBD";
		}
		else {
			result = afc_championship.team1.seeding + ". " + afc_championship.team1.abbr + " " + 
					afc_championship.team1.record() + "  ";
		}
		
		if (championshipPlayed && afc_championship.winner == afc_championship.team1) {
			result += "W  *AFC CHAMPIONS*";
		}
		
		result += "\n";
		
		if (championshipPlayed) {
			result += "                                                              ";
			result += afc_championship.team1.getQB().name + " {" + afc_championship.team1_qb_tds + " TDs} \n";
			result += "                                                                      ";
			result += afc_championship.team1_score + "\n\n";
			
			result += "                                                                      VS ";
			
			if (afc_championship.OT) {
				result += "(OT)";
			}
			
			result += "\n\n";
			
			result += "                                                                      " + 
					afc_championship.team2_score + "\n";
			result += "                                                              " + 
					afc_championship.team2.seeding + ". " + afc_championship.team2.abbr + " " + 
					afc_championship.team2.record() + "  ";
			
			if (championshipPlayed && afc_championship.winner == afc_championship.team2) {
				result += "W  *AFC CHAMPIONS*";
			}
			
			result += "\n";
			result += "                                                     -------- ";
			result += afc_championship.team2.getQB().name + " {" + afc_championship.team2_qb_tds + " TDs} \n";

		}
		
		else if (divisionalPlayed) {
			result +=  "\n";
			
			result += "                                                                      VS ";
			
			result += "\n\n";
			
			result += "                                                     -------- " + 
					afc_championship.team2.seeding + ". " + afc_championship.team2.abbr + " " + 
					afc_championship.team2.record() + "  ";
			
			result += "\n";
		}
		
		else {
			result +=  "\n";
			
			result += "                                                              VS ";
			
			result += "\n\n";
			
			result += "                                                     -------- TBD";
			result += "\n";
		}
		
		return result;
		
	}
	
	private String NFCChampionshipGame() {
		
		String result;
		
		if (!divisionalPlayed) {
			result = "TBD";
		}
		else {
			result = nfc_championship.team1.seeding + ". " + nfc_championship.team1.abbr + " " + 
					nfc_championship.team1.record() + "  ";
		}
		
		if (championshipPlayed && nfc_championship.winner == nfc_championship.team1) {
			result += "W  *NFC CHAMPIONS*";
		}
		
		result += "\n";
		
		if (championshipPlayed) {
			result += "                                                              ";
			result += nfc_championship.team1.getQB().name + " {" + nfc_championship.team1_qb_tds + " TDs} \n";
			result += "                                                                      ";
			result += nfc_championship.team1_score + "\n\n";
			
			result += "                                                                      VS ";
			
			if (nfc_championship.OT) {
				result += "(OT)";
			}
			
			result += "\n\n";
			
			result += "                                                                      " + 
					nfc_championship.team2_score + "\n";
			result += "                                                              " + 
					nfc_championship.team2.seeding + ". " + nfc_championship.team2.abbr + " " + 
					nfc_championship.team2.record() + "  ";
			
			if (championshipPlayed && nfc_championship.winner == nfc_championship.team2) {
				result += "W  *NFC CHAMPIONS*";
			}
			
			result += "\n";
			result += "                                                     -------- ";
			result += nfc_championship.team2.getQB().name + " {" + nfc_championship.team2_qb_tds + " TDs} \n";

		}
		
		else if (divisionalPlayed) {
			result +=  "\n";
			
			result += "                                                                      VS ";
			
			result += "\n\n";
			
			result += "                                                     -------- " + 
					nfc_championship.team2.seeding + ". " + nfc_championship.team2.abbr + " " + 
					nfc_championship.team2.record() + "  ";
			
			result += "\n";
		}
		
		else {
			result +=  "\n";
			
			result += "                                                              VS ";
			
			result += "\n\n";
			
			result += "                                                     -------- TBD";
			result += "\n";
		}
		
		return result;
		
	}
	
	private String firstAFCDivisionalGame() {
		
		String result = afc_seed[0].seeding + ". " + afc_seed[0].abbr +
				" (" +  afc_seed[0].totalWins + "-" + afc_seed[0].totalLosses +
				"-" + afc_seed[0].totalTies + ")  ";
		
		if (divisionalPlayed && afc_seed[0] == afc_divisional[0].winner) {
			result += "W";
		}
		
		result += "\n";
		result += "                              ";
		
		if (divisionalPlayed) {
			result += afc_divisional[0].team1.getQB().name + " {" + afc_divisional[0].team1_qb_tds + " TDs} \n";
			result += "                                     " + afc_divisional[0].team1_score + "\n\n";
			result += "                                     VS ";
			
			if (afc_divisional[0].OT) {
				result += "(OT)";
			}
			
			result += "\n\n";
			result += "                                     " + afc_divisional[0].team2_score + "\n";
			result += "                              " + afc_divisional[0].team2.seeding + ". " +
					afc_divisional[0].team2.abbr + " " + afc_divisional[0].team2.record() + "  ";
			
			if (afc_divisional[0].winner == afc_divisional[0].team2) {
				result += "W";
			}
			result += "\n";
			result += "                              " + afc_divisional[0].team2.getQB().name + 
					" {" + afc_divisional[0].team2_qb_tds + " TDs} \n";
		}
		
		else if (wildCardPlayed) {
			
			result += "\n                                     VS \n\n";
			result += "                              ";
			result += afc_divisional[0].team2.seeding + ". " + afc_divisional[0].team2.abbr +
					" (" +  afc_divisional[0].team2.totalWins + "-" + afc_divisional[0].team2.totalLosses +
					"-" + afc_divisional[0].team2.totalTies + ")  \n";
			
		}
		
		else {
			result += "\n                                      VS \n\n";
			result += "                                      TBD \n";
		}
		
		return result;
		
	}
	
	private String firstNFCDivisionalGame() {
		
		String result = nfc_seed[0].seeding + ". " + nfc_seed[0].abbr +
				" (" +  nfc_seed[0].totalWins + "-" + nfc_seed[0].totalLosses +
				"-" + nfc_seed[0].totalTies + ")  ";
		
		if (divisionalPlayed && nfc_seed[0] == nfc_divisional[0].winner) {
			result += "W";
		}
		
		result += "\n";
		result += "                              ";
		
		if (divisionalPlayed) {
			result += nfc_divisional[0].team1.getQB().name + " {" + nfc_divisional[0].team1_qb_tds + " TDs} \n";
			result += "                                     " + nfc_divisional[0].team1_score + "\n\n";
			result += "                                     VS ";
			
			if (nfc_divisional[0].OT) {
				result += "(OT)";
			}
			
			result += "\n\n";
			result += "                                     " + nfc_divisional[0].team2_score + "\n";
			result += "                              " + nfc_divisional[0].team2.seeding + ". " +
					nfc_divisional[0].team2.abbr + " " + nfc_divisional[0].team2.record() + "  ";
			
			if (nfc_divisional[0].winner == nfc_divisional[0].team2) {
				result += "W";
			}
			result += "\n";
			result += "                              " + nfc_divisional[0].team2.getQB().name + 
					" {" + nfc_divisional[0].team2_qb_tds + " TDs} \n";
		}
		
		else if (wildCardPlayed) {
			
			result += "\n                                     VS \n\n";
			result += "                              ";
			result += nfc_divisional[0].team2.seeding + ". " + nfc_divisional[0].team2.abbr +
					" (" +  nfc_divisional[0].team2.totalWins + "-" + nfc_divisional[0].team2.totalLosses +
					"-" + nfc_divisional[0].team2.totalTies + ")  \n";
			
		}
		
		else {
			result += "\n                                      VS \n\n";
			result += "                                      TBD \n";
		}
		
		return result;
		
	}
	
	private String firstAFCWildCardGame() {
		
		String result = afc_wildCard[0].team1.seeding + ". " + afc_wildCard[0].team1.abbr +
				" (" +  afc_wildCard[0].team1.totalWins + "-" + afc_wildCard[0].team1.totalLosses +
				"-" + afc_wildCard[0].team1.totalTies + ")  ";
		
		if (wildCardPlayed) {
			if (afc_wildCard[0].team1 == afc_wildCard[0].winner) {
				result += "W";
			}
			result += "\n";
		}
		else {
			result += "\n\n";
		}
		
		if (wildCardPlayed) {
			
			result += afc_wildCard[0].team1.getQB().name + " {" + afc_wildCard[0].team1_qb_tds + " TDs} \n";
			result += "	" + afc_wildCard[0].team1_score + "\n\n";
			result += "	VS ";
			
			if (afc_wildCard[0].OT) {
				result += "(OT)";
			}
			
			result += "\n\n";
			
			result += "	" + afc_wildCard[0].team2_score + "\n";
			result += afc_wildCard[0].team2.seeding + ". " + afc_wildCard[0].team2.abbr +
					" (" +  afc_wildCard[0].team2.totalWins + "-" + afc_wildCard[0].team2.totalLosses +
					"-" + afc_wildCard[0].team2.totalTies +")  ";
			
			if (afc_wildCard[0].team2 == afc_wildCard[0].winner) {
				result += "W";
			}
			
			result += "\n";
			
			result += afc_wildCard[0].team2.getQB().name + " {" + afc_wildCard[0].team2_qb_tds + " TDs} \n";
			
		}
		
		else {
			result += "	VS \n\n";
			result += afc_wildCard[0].team2.seeding + ". " + afc_wildCard[0].team2.abbr +
					" (" +  afc_wildCard[0].team2.totalWins + "-" + afc_wildCard[0].team2.totalLosses +
					"-" + afc_wildCard[0].team2.totalTies + ") \n";
		}
		
		return result;
		
	}
	
	private String firstNFCWildCardGame() {
		
		String result = nfc_wildCard[0].team1.seeding + ". " + nfc_wildCard[0].team1.abbr +
				" (" +  nfc_wildCard[0].team1.totalWins + "-" + nfc_wildCard[0].team1.totalLosses +
				"-" + nfc_wildCard[0].team1.totalTies + ")  ";
		
		if (wildCardPlayed) {
			if (nfc_wildCard[0].team1 == nfc_wildCard[0].winner) {
				result += "W";
			}
			result += "\n";
		}
		else {
			result += "\n\n";
		}
		
		if (wildCardPlayed) {
			
			result += nfc_wildCard[0].team1.getQB().name + " {" + nfc_wildCard[0].team1_qb_tds + " TDs} \n";
			result += "	" + nfc_wildCard[0].team1_score + "\n\n";
			result += "	VS ";
			
			if (nfc_wildCard[0].OT) {
				result += "(OT)";
			}
			
			result += "\n\n";
			
			result += "	" + nfc_wildCard[0].team2_score + "\n";
			result += nfc_wildCard[0].team2.seeding + ". " + nfc_wildCard[0].team2.abbr +
					" (" +  nfc_wildCard[0].team2.totalWins + "-" + nfc_wildCard[0].team2.totalLosses +
					"-" + nfc_wildCard[0].team2.totalTies +")  ";
			
			if (nfc_wildCard[0].team2 == nfc_wildCard[0].winner) {
				result += "W";
			}
			
			result += "\n";
			
			result += nfc_wildCard[0].team2.getQB().name + " {" + nfc_wildCard[0].team2_qb_tds + " TDs} \n";
			
		}
		
		else {
			result += "	VS \n\n";
			result += nfc_wildCard[0].team2.seeding + ". " + nfc_wildCard[0].team2.abbr +
					" (" +  nfc_wildCard[0].team2.totalWins + "-" + nfc_wildCard[0].team2.totalLosses +
					"-" + nfc_wildCard[0].team2.totalTies + ") \n";
		}
		
		return result;
		
	}
	
	private String secondAFCWildCardGame() {
		
		String result = afc_wildCard[1].team1.seeding + ". " + afc_wildCard[1].team1.abbr +
				" (" +  afc_wildCard[1].team1.totalWins + "-" + afc_wildCard[1].team1.totalLosses +
				"-" + afc_wildCard[1].team1.totalTies + ")  ";
		
		if (wildCardPlayed) {
			if (afc_wildCard[1].team1 == afc_wildCard[1].winner) {
				result += "W";
			}
			result += "\n";
		}
		else {
			result += "\n\n";
		}
		
		if (wildCardPlayed) {
			
			result += afc_wildCard[1].team1.getQB().name + " {" + afc_wildCard[1].team1_qb_tds + " TDs} \n";
			result += "	" + afc_wildCard[1].team1_score + "\n\n";
			result += "	VS ";
			
			if (afc_wildCard[1].OT) {
				result += "(OT)";
			}
			
			result += "\n\n";
			
			result += "	" + afc_wildCard[1].team2_score + "\n";
			result += afc_wildCard[1].team2.seeding + ". " + afc_wildCard[1].team2.abbr +
					" (" +  afc_wildCard[1].team2.totalWins + "-" + afc_wildCard[1].team2.totalLosses +
					"-" + afc_wildCard[1].team2.totalTies +")  ";
			
			if (afc_wildCard[1].team2 == afc_wildCard[1].winner) {
				result += "W";
			}
			
			result += "\n";
			
			result += afc_wildCard[1].team2.getQB().name + " {" + afc_wildCard[1].team2_qb_tds + " TDs} \n";
			
		}
		
		else {
			result += "	VS \n\n";
			result += afc_wildCard[1].team2.seeding + ". " + afc_wildCard[1].team2.abbr +
					" (" +  afc_wildCard[1].team2.totalWins + "-" + afc_wildCard[1].team2.totalLosses +
					"-" + afc_wildCard[1].team2.totalTies + ") \n";
		}
		
		return result;
		
	}
	
	private String secondNFCWildCardGame() {
		
		String result = nfc_wildCard[1].team1.seeding + ". " + nfc_wildCard[1].team1.abbr +
				" (" +  nfc_wildCard[1].team1.totalWins + "-" + nfc_wildCard[1].team1.totalLosses +
				"-" + nfc_wildCard[1].team1.totalTies + ")  ";
		
		if (wildCardPlayed) {
			if (nfc_wildCard[1].team1 == nfc_wildCard[1].winner) {
				result += "W";
			}
			result += "\n";
		}
		else {
			result += "\n\n";
		}
		
		if (wildCardPlayed) {
			
			result += nfc_wildCard[1].team1.getQB().name + " {" + nfc_wildCard[1].team1_qb_tds + " TDs} \n";
			result += "	" + nfc_wildCard[1].team1_score + "\n\n";
			result += "	VS ";
			
			if (nfc_wildCard[1].OT) {
				result += "(OT)";
			}
			
			result += "\n\n";
			
			result += "	" + nfc_wildCard[1].team2_score + "\n";
			result += nfc_wildCard[1].team2.seeding + ". " + nfc_wildCard[1].team2.abbr +
					" (" +  nfc_wildCard[1].team2.totalWins + "-" + nfc_wildCard[1].team2.totalLosses +
					"-" + nfc_wildCard[1].team2.totalTies +")  ";
			
			if (nfc_wildCard[1].team2 == nfc_wildCard[1].winner) {
				result += "W";
			}
			
			result += "\n";
			
			result += nfc_wildCard[1].team2.getQB().name + " {" + nfc_wildCard[1].team2_qb_tds + " TDs} \n";
			
		}
		
		else {
			result += "	VS \n\n";
			result += nfc_wildCard[1].team2.seeding + ". " + nfc_wildCard[1].team2.abbr +
					" (" +  nfc_wildCard[1].team2.totalWins + "-" + nfc_wildCard[1].team2.totalLosses +
					"-" + nfc_wildCard[1].team2.totalTies + ") \n";
		}
		
		return result;
		
	}
	
	/**Prints congratulations message to the superbowl winner**/
	public void printSuperBowlCongratulations() {
		
		String result = "Congratulations to the " + superbowl.winner.toString() + " on their ";
		
		if (superbowl.winner == superbowl.team1) {
			result += superbowl.team1_score + " - " + superbowl.team2_score + " ";
		}
		else {
			result += superbowl.team2_score + " - " + superbowl.team1_score + " ";
		}
		
		result += "Super Bowl victory over the ";
		
		if (superbowl.winner == superbowl.team1) {
			result += superbowl.team2.toString() + "!";
		}
		else {
			result += superbowl.team1.toString() + "!\n";
		}
		
		System.out.println(result);
		
	}
	
}
