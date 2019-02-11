import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**Defines the schedule for the regular season**/
public class RegularSeason {
	
	//Data structure to hold full season schedule
	HashMap<String, ArrayList<Game>> schedule = new HashMap<String, ArrayList<Game>>();
	
	int weeks_played = 0;	//number of weeks that have past in the season
	
	//Mappings to help determine each individual teams' opponents for the season
	private HashMap<ArrayList<Team>, ArrayList<Team>> innerConferenceDiv = 
			new HashMap<ArrayList<Team>, ArrayList<Team>>();
	private HashMap<ArrayList<Team>, ArrayList<Team>> outerConferenceDiv = 
			new HashMap<ArrayList<Team>, ArrayList<Team>>();
	private HashMap<Team, ArrayList<Team>> divisional = new HashMap<Team, ArrayList<Team>>();
	private HashMap<ArrayList<Team>, ArrayList<ArrayList<Team>>> otherConferenceDiv = 
			new HashMap<ArrayList<Team>, ArrayList<ArrayList<Team>>>();	
	private HashMap<Team, ArrayList<Team>> remaining = new HashMap<Team, ArrayList<Team>>();
	
	//Holds the week types for each week of the final schedule
	private HashMap<Integer, WeekType> week_types = new HashMap<Integer, WeekType>();
	
	public RegularSeason() {		
		
		//Pairings of divisions for schedule
		pairNFCDivisions();
		pairAFCDivisions();
		pairBetweenConferences();
		
		//sets the remaining matchups between teams
		remainingMatchups(Teams.nfc_west);
		remainingMatchups(Teams.nfc_east);
		remainingMatchups(Teams.nfc_north);
		remainingMatchups(Teams.nfc_south);
		remainingMatchups(Teams.afc_west);
		remainingMatchups(Teams.afc_east);
		remainingMatchups(Teams.afc_north);
		remainingMatchups(Teams.afc_south);
		
		setDivisionOpponents();	//sets the divisional opponent list for each team
		
		setWeekTypes();	//sets the week types for each week
		setFinalSchedule(); //sets the completed schedule for season
		
	}
	
	
	
	/**Pairs NFC Division matchups**/
	private void pairNFCDivisions() {
		
		Random rand = new Random();	//random generator
		
		ArrayList<Team> div1 = Teams.nfc_west, div2 = null, div3 = null, div4 = null;
		
		otherConferenceDiv.put(Teams.nfc_east, new ArrayList<ArrayList<Team>>());
		otherConferenceDiv.put(Teams.nfc_west, new ArrayList<ArrayList<Team>>());
		otherConferenceDiv.put(Teams.nfc_south, new ArrayList<ArrayList<Team>>());
		otherConferenceDiv.put(Teams.nfc_north, new ArrayList<ArrayList<Team>>());
		
		int option = rand.nextInt(3);
		
		//different possibilities of pairings
		switch(option) {
		
		case 0:
			div2 = Teams.nfc_north;
			div3 = Teams.nfc_east;
			div4 = Teams.nfc_south;
			break;
			
		case 1:
			div2 = Teams.nfc_east;
			div3 = Teams.nfc_south;
			div4 = Teams.nfc_north;
			break;
			
		case 2:
			div2 = Teams.nfc_south;
			div3 = Teams.nfc_east;
			div4 = Teams.nfc_north;
			break;
		
		}
		
		innerConferenceDiv.put(div1, div2);
		innerConferenceDiv.put(div2, div1);
		innerConferenceDiv.put(div3, div4);
		innerConferenceDiv.put(div4, div3);
		
		//ordering of these matter due to setup of games
		//for example, div1 has div3 in its 0 index,
		//therefore, div3 must have div1 in its 0 index
		otherConferenceDiv.get(div1).add(div3);
		otherConferenceDiv.get(div1).add(div4);
		otherConferenceDiv.get(div2).add(div4);
		otherConferenceDiv.get(div2).add(div3);
		otherConferenceDiv.get(div3).add(div1);
		otherConferenceDiv.get(div3).add(div2);
		otherConferenceDiv.get(div4).add(div2);
		otherConferenceDiv.get(div4).add(div1);
		
	}
	
	/**Pairs AFC Division matchups**/
	private void pairAFCDivisions() {
		
		Random rand = new Random();	//random generator
		
		ArrayList<Team> div1 = Teams.afc_west, div2 = null, div3 = null, div4 = null;
		
		otherConferenceDiv.put(Teams.afc_east, new ArrayList<ArrayList<Team>>());
		otherConferenceDiv.put(Teams.afc_west, new ArrayList<ArrayList<Team>>());
		otherConferenceDiv.put(Teams.afc_south, new ArrayList<ArrayList<Team>>());
		otherConferenceDiv.put(Teams.afc_north, new ArrayList<ArrayList<Team>>());
		
		int option = rand.nextInt(3);
		
		//different possibilities of pairings
		switch(option) {
		
		case 0:
			div2 = Teams.afc_north;
			div3 = Teams.afc_east;
			div4 = Teams.afc_south;
			break;
			
		case 1:
			div2 = Teams.afc_east;
			div3 = Teams.afc_south;
			div4 = Teams.afc_north;
			break;
			
		case 2:
			div2 = Teams.afc_south;
			div3 = Teams.afc_east;
			div4 = Teams.afc_north;
			break;
		
		}
		
		innerConferenceDiv.put(div1, div2);
		innerConferenceDiv.put(div2, div1);
		innerConferenceDiv.put(div3, div4);
		innerConferenceDiv.put(div4, div3);
		
		//ordering of these matter due to setup of games
		//for example, div1 has div3 in its 0 index,
		//therefore, div3 must have div1 in its 0 index
		otherConferenceDiv.get(div1).add(div3);
		otherConferenceDiv.get(div1).add(div4);
		otherConferenceDiv.get(div2).add(div4);
		otherConferenceDiv.get(div2).add(div3);
		otherConferenceDiv.get(div3).add(div1);
		otherConferenceDiv.get(div3).add(div2);
		otherConferenceDiv.get(div4).add(div2);
		otherConferenceDiv.get(div4).add(div1);
		
	}
	
	/**Pairs divisions from NFC to divisions in AFC**/
	private void pairBetweenConferences() {
		
		Random rand = new Random();	//random generator
		
		ArrayList<ArrayList<Team>> temp = new ArrayList<ArrayList<Team>>();
		temp.add(Teams.afc_east);
		temp.add(Teams.afc_north);
		temp.add(Teams.afc_south);
		temp.add(Teams.afc_west);
		
		ArrayList<ArrayList<Team>> permuteAFC = new ArrayList<ArrayList<Team>>();
		
		//randomly permutes order of afc divisions to be matched with nfc divisions
		for (int i = 0; i < 4; i++) {
			
			int index = rand.nextInt(temp.size());
			
			permuteAFC.add(temp.get(index));
			temp.remove(index);
			
		}
		
		//pairs the nfc divisions to afc divisions
		outerConferenceDiv.put(Teams.nfc_west, permuteAFC.get(0));
		outerConferenceDiv.put(Teams.nfc_east, permuteAFC.get(1));
		outerConferenceDiv.put(Teams.nfc_north, permuteAFC.get(2));
		outerConferenceDiv.put(Teams.nfc_south, permuteAFC.get(3));
		
		outerConferenceDiv.put(permuteAFC.get(0), Teams.nfc_west);
		outerConferenceDiv.put(permuteAFC.get(1), Teams.nfc_east);
		outerConferenceDiv.put(permuteAFC.get(2), Teams.nfc_north);
		outerConferenceDiv.put(permuteAFC.get(3), Teams.nfc_south);
		
		
	}
	
	/**Sets the remaining schedule for the teams in specified division**/
	private void remainingMatchups(ArrayList<Team> div) {
		
		Random rand = new Random();	//random generator		
		boolean div1_done = false, div2_done = false;
		
		Team team_in_division = div.get(0);
		ArrayList<Team> div1 = otherConferenceDiv.get(div).get(0);
		ArrayList<Team> div2 = otherConferenceDiv.get(div).get(1);
		
		//checks if division has entries already
		if (remaining.containsKey(team_in_division)) {
			
			//checks if div1 matchups created
			for (Team t: div1) {
				if (remaining.get(team_in_division).contains(t)) {
					div1_done = true;
				}
			}
			
			//checks if div2 matchups created
			for (Team t: div2) {
				if (remaining.get(team_in_division).contains(t)) {
					div2_done = true;
				}
			}
			
		}
		
		//does work for matchups for div1 if not done
		if (!div1_done) {
			
			//creates copy of div1
			ArrayList<Team> div1_copy = new ArrayList<Team>();
			for (Team t: div1) {
				div1_copy.add(t);
			}
			
			ArrayList<Team> permuteDiv1 = new ArrayList<Team>();
			
			//permutes ordering of div1 teams
			for (int i = 0; i < 4; i++) {
				
				int index = rand.nextInt(div1_copy.size());
				
				permuteDiv1.add(div1_copy.get(index));
				div1_copy.remove(index);
			}
			
			for (Team t: div) {
				if (!remaining.containsKey(t)) {
					remaining.put(t, new ArrayList<Team>());
				}
			}
			for (Team t: div1) {
				if (!remaining.containsKey(t)) {
					remaining.put(t, new ArrayList<Team>());
				}
			}
			
			//creates the matchups
			for (int i = 0; i < 4; i++) {
			
				remaining.get(div.get(i)).add(permuteDiv1.get(i));
				remaining.get(permuteDiv1.get(i)).add(div.get(i));
			
			}
			
		}
		
		//does work for matchups for div2 if not done
		if (!div2_done) {
			
			//creates copy of div2
			ArrayList<Team> div2_copy = new ArrayList<Team>();
			for (Team t: div2) {
				div2_copy.add(t);
			}
			
			ArrayList<Team> permuteDiv2 = new ArrayList<Team>();
			
			//permutes ordering of div2 teams
			for (int i = 0; i < 4; i++) {
				
				int index = rand.nextInt(div2_copy.size());
				
				permuteDiv2.add(div2_copy.get(index));
				div2_copy.remove(index);
			}
			
			for (Team t: div2) {
				if (!remaining.containsKey(t)) {
					remaining.put(t, new ArrayList<Team>());
				}
			}
			
			//creates the matchups
			for (int i = 0; i < 4; i++) {
			
				remaining.get(div.get(i)).add(permuteDiv2.get(i));
				remaining.get(permuteDiv2.get(i)).add(div.get(i));
			
			}
			
		}
		
	}
	
	
	/**Sets the division opponents for each team**/
	private void setDivisionOpponents() {
		
		for (Team t: Teams.allTeams) {
			
			divisional.put(t, new ArrayList<Team>());
			
			switch(t.getDivision()) {
			
			case NFC_WEST:
				//adds division opponents
				for (Team opp: Teams.nfc_west) {
					if (t != opp) {
						divisional.get(t).add(opp);
						divisional.get(t).add(opp);	//play division opponents twice
					}
				}
				break;
				
			case NFC_EAST:
				for (Team opp: Teams.nfc_east) {
					if (t != opp) {
						divisional.get(t).add(opp);
						divisional.get(t).add(opp);
					}
				}
				break;
				
			case NFC_NORTH:
				for (Team opp: Teams.nfc_north) {
					if (t != opp) {
						divisional.get(t).add(opp);
						divisional.get(t).add(opp);
					}
				}
				break;
				
			case NFC_SOUTH:
				for (Team opp: Teams.nfc_south) {
					if (t != opp) {
						divisional.get(t).add(opp);
						divisional.get(t).add(opp);
					}
				}
				break;
				
			case AFC_WEST:
				for (Team opp: Teams.afc_west) {
					if (t != opp) {
						divisional.get(t).add(opp);
						divisional.get(t).add(opp);
					}
				}
				break;
				
			case AFC_EAST:
				for (Team opp: Teams.afc_east) {
					if (t != opp) {
						divisional.get(t).add(opp);
						divisional.get(t).add(opp);
					}
				}
				break;
				
			case AFC_NORTH:
				for (Team opp: Teams.afc_north) {
					if (t != opp) {
						divisional.get(t).add(opp);
						divisional.get(t).add(opp);
					}
				}
				break;
				
			case AFC_SOUTH:
				for (Team opp: Teams.afc_south) {
					if (t != opp) {
						divisional.get(t).add(opp);
						divisional.get(t).add(opp);
					}
				}
				break;
			
			}
			
		}
		
	}
	
	
	/**Sets the week type of each week during the regular season**/
	private void setWeekTypes() {
		
		Random rand = new Random();
		
		WeekType[] wt = new WeekType[16];
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		//populates temp with indices 0-15
		for (int i = 0; i < 15; i++) {
			temp.add(i);
		}
		
		//sets the divisional weeks
		for (int i = 0; i < 5; i++) {
			int index = rand.nextInt(temp.size());
			wt[temp.get(index)] = WeekType.DIVISIONAL;
			temp.remove(index);
		}
		
		wt[15] = WeekType.DIVISIONAL; //last week of schedule must have all divisional games
		
		//sets intra conference opponent weeks
		for (int i = 0; i < 4; i++) {
			int index = rand.nextInt(temp.size());
			wt[temp.get(index)] = WeekType.CONFERENCE_OPPONENT;
			temp.remove(index);
		}
		
		//sets outer conference opponent weeks
		for (int i = 0; i < 4; i++) {
			int index = rand.nextInt(temp.size());
			wt[temp.get(index)] = WeekType.OUTER_CONFERENCE_OPPONENT;
			temp.remove(index);
		}
		
		//sets remaining weeks for the last type of opponents
		for (int i = 0; i < 2; i++) {
			int index = rand.nextInt(temp.size());
			wt[temp.get(index)] = WeekType.OTHER;
			temp.remove(index);
		}
		
		//updates the week_types mapping
		for (int i = 0; i < 16; i++) {
			week_types.put((i+1), wt[i]);
		}
	 
	}
	
	
	/**Returns true if specified team is already
	 * scheduled for a game in the specified week**/
	private boolean teamScheduledThisWeek(Team team, String week) {
		
		for (Game g: schedule.get(week)) {
			if (g.team1 == team || g.team2 == team) {
				return true;
			}
		}
		
		return false;
		
	}
	
	/**Adds a divisional game for the specified team on the specified week**/
	private void addDivisionalGame(Team team, String week) {
		
		Random rand = new Random();
		int index = rand.nextInt(divisional.get(team).size());	//chooses random divisional opponent
		Team opponent = divisional.get(team).get(index);
		
		//ensures team has not been scheduled the current week
		while (teamScheduledThisWeek(opponent, week)) {
			index++;
			if (index == divisional.get(team).size()) {
				index = 0;
			}
			opponent = divisional.get(team).get(index);	//updates the opponent
		}
		
		Game g = new Game(team, opponent); //creates a game between the 2 teams
		
		//adds the game to the league schedule, and team schedules
		schedule.get(week).add(g);
		team.schedule.put(week, g);
		opponent.schedule.put(week, g);
		
		//ensures the matchup does not repeat
		divisional.get(team).remove(opponent);
		divisional.get(opponent).remove(team);
	}
	
	/**Adds conference opponent games to the specified week.
	 * The index represents the team that the first listed
	 * team in the division of Team t will play in
	 * their conference opponent list**/
	private void addConferenceOpponentGames(Team team, String week, int oppIndex) {
		
		//sets opponent indices for rest of teams in division
		int opp2 = oppIndex + 1;
		if (opp2 == 4) {
			opp2 = 0;
		}
		
		int opp3 = opp2 + 1;
		if (opp3 == 4) {
			opp3 = 0;
		}
		
		int opp4 = opp3 + 1;
		if (opp4 == 4) {
			opp4 = 0;
		}
		
		ArrayList<Team> div = null;
		
		//gets the team's division
		switch(team.getDivision()) {
		
		case NFC_WEST:
			div = Teams.nfc_west;
			break;
			
		case NFC_EAST:
			div = Teams.nfc_east;
			break;
			
		case NFC_SOUTH:
			div = Teams.nfc_south;
			break;
			
		case NFC_NORTH:
			div = Teams.nfc_north;
			break;
			
		case AFC_WEST:
			div = Teams.afc_west;
			break;
			
		case AFC_EAST:
			div = Teams.afc_east;
			break;
			
		case AFC_SOUTH:
			div = Teams.afc_south;
			break;
			
		case AFC_NORTH:
			div = Teams.afc_north;
			break;
		
		}
		
		int opponentIndex = oppIndex;
		
		for (int i = 0; i < 4; i++) {
			
			Team divTeam = div.get(i);
			Team oppTeam = innerConferenceDiv.get(div).get(opponentIndex);	//appropriate opponent team
			
			Game g = new Game (divTeam, oppTeam);	//creates game between the teams
			
			//adds the game to the league schedule, as well as individual team's schedule
			schedule.get(week).add(g);
			divTeam.schedule.put(week, g);
			oppTeam.schedule.put(week, g);
			
			//updates the index to use
			if (opponentIndex == oppIndex) {
				opponentIndex = opp2;
			}
			else if (opponentIndex == opp2) {
				opponentIndex = opp3;
			}
			else if (opponentIndex == opp3) {
				opponentIndex = opp4;
			}
			
		}
		
	}
	
	
	/**Adds outer conference opponent games to the specified week.
	 * The index represents the team that the first listed
	 * team in the division of Team t will play in
	 * their outer conference opponent list**/
	private void addOuterConferenceOpponentGames(Team team, String week, int oppIndex) {
		
		//sets opponent indices for rest of teams in division
		int opp2 = oppIndex + 1;
		if (opp2 == 4) {
			opp2 = 0;
		}
		
		int opp3 = opp2 + 1;
		if (opp3 == 4) {
			opp3 = 0;
		}
		
		int opp4 = opp3 + 1;
		if (opp4 == 4) {
			opp4 = 0;
		}
		
		ArrayList<Team> div = null;
		
		//gets the team's division
		switch(team.getDivision()) {
		
		case NFC_WEST:
			div = Teams.nfc_west;
			break;
			
		case NFC_EAST:
			div = Teams.nfc_east;
			break;
			
		case NFC_SOUTH:
			div = Teams.nfc_south;
			break;
			
		case NFC_NORTH:
			div = Teams.nfc_north;
			break;
			
		case AFC_WEST:
			div = Teams.afc_west;
			break;
			
		case AFC_EAST:
			div = Teams.afc_east;
			break;
			
		case AFC_SOUTH:
			div = Teams.afc_south;
			break;
			
		case AFC_NORTH:
			div = Teams.afc_north;
			break;
		
		}
		
		int opponentIndex = oppIndex;
		
		for (int i = 0; i < 4; i++) {
			
			Team divTeam = div.get(i);
			Team oppTeam = outerConferenceDiv.get(div).get(opponentIndex);	//appropriate opponent team
			
			Game g = new Game (divTeam, oppTeam);	//creates game between the teams
			
			//adds the game to the league schedule, as well as individual team's schedule
			schedule.get(week).add(g);
			divTeam.schedule.put(week, g);
			oppTeam.schedule.put(week, g);
			
			//updates the index to use
			if (opponentIndex == oppIndex) {
				opponentIndex = opp2;
			}
			else if (opponentIndex == opp2) {
				opponentIndex = opp3;
			}
			else if (opponentIndex == opp3) {
				opponentIndex = opp4;
			}
			
		}
		
	}
	
	/**Adds games from the "other" category for the specified
	 * team's division during the specified week**/
	private void addOtherGames(Team team, String week, int oppIndex) {
		
		ArrayList<Team> div = null, oppDiv = null;
		
		//gets the team's division
		switch(team.getDivision()) {
		
		case NFC_WEST:
			div = Teams.nfc_west;
			break;
			
		case NFC_EAST:
			div = Teams.nfc_east;
			break;
			
		case NFC_SOUTH:
			div = Teams.nfc_south;
			break;
			
		case NFC_NORTH:
			div = Teams.nfc_north;
			break;
			
		case AFC_WEST:
			div = Teams.afc_west;
			break;
			
		case AFC_EAST:
			div = Teams.afc_east;
			break;
			
		case AFC_SOUTH:
			div = Teams.afc_south;
			break;
			
		case AFC_NORTH:
			div = Teams.afc_north;
			break;
		
		}
		
		oppDiv = otherConferenceDiv.get(div).get(oppIndex);	//sets opponent division
		
		//sets the games for each team in division
		for (Team t: div) {
			
			Team opponent = remaining.get(t).get(0);
			
			//ensures the opponent team is pasrt of the opponent division
			if (!oppDiv.contains(opponent)) {
				opponent = remaining.get(t).get(1);
			}		
			
			Game g = new Game(t, opponent);	//creates game between the teams
			
			//adds the game to the schedule
			schedule.get(week).add(g);
			t.schedule.put(week, g);
			opponent.schedule.put(week, g);
		}
		
	}
	
		
	/**Sets the final schedule. Updating the full schedule week by week,
	 * as well as updating each individual team's week by week schedule**/
	private void setFinalSchedule() {
		
		Random rand = new Random();
		
		//sets random indices that will help to randomly, but systematically, choose
		//matchups during each week
		int randConf = rand.nextInt(4);
		int randOuterConf = rand.nextInt(4);
		int randOther = rand.nextInt(2);
		
		//sets schedule week by week
		for (int i = 1; i <= 16; i++) {
			
			String week = "Week " + i;
			schedule.put(week, new ArrayList<Game>());	//creates a week for games to be played in
			
			//handles the switching of opponents of same category of game type based on previous week
			if (i > 1) {
				
				switch(week_types.get(i - 1)) {
				
				case DIVISIONAL:
					break;
					
				case CONFERENCE_OPPONENT:
					randConf++;	//increments index
					
					if (randConf == 4) {
						randConf = 0;
					}
					break;
					
				case OUTER_CONFERENCE_OPPONENT:
					randOuterConf++;	//increments index
					
					if (randOuterConf == 4) {
						randOuterConf = 0;
					}
					break;
					
				case OTHER:
					//index either 0 or 1 depending on which it started on
					if (randOther == 0) {
						randOther = 1;
					}
					else {
						randOther = 0;
					}
					break;
				
				}
				
			}
			
			//begins to generate games for the week
			for (Team t: Teams.allTeams) {
				
				//checks if team is already scheduled this week
				if (!teamScheduledThisWeek(t, week)) {
					
					switch(week_types.get(i)) {
					
					case DIVISIONAL:
						addDivisionalGame(t, week);
						break;
						
					case CONFERENCE_OPPONENT:					
						addConferenceOpponentGames(t, week, randConf);
						break;
						
					case OUTER_CONFERENCE_OPPONENT:					
						addOuterConferenceOpponentGames(t, week, randOuterConf);
						break;
						
					case OTHER:					
						addOtherGames(t, week, randOther);
						break;
					
					}
					
				}
				
			}
			
		}
		
		//randomize the order teams appear in the schedule for the week
		for (String week: schedule.keySet()) {
			
			ArrayList<Game> updated = new ArrayList<Game>();
			ArrayList<Game> copy = new ArrayList<Game>();
			
			copy.addAll(schedule.get(week));	//copies all games from the week
			
			//adds random game to updated list
			while (copy.size() > 0) {
				int temp = rand.nextInt(copy.size());
				updated.add(copy.get(temp));
				copy.remove(temp);
			}
			
			schedule.replace(week, updated);
			
		}
		
	}
	
	/**Plays all of the games in the current week of the season.
	 * Updates stats and standings on week's end.**/
	public void playWeek() {
		
		int temp = weeks_played + 1;
		String week = "Week " + (temp);
		
		for (Game g: schedule.get(week)) {
			g.playGame();
		}
		
		Stats.updateRankings();
		Standings.updateStandings();
		
		weeks_played++;
		
	}
	
	/**Plays the remainder of the regular season**/
	public void playRestOfSeason() {
		
		for (int i = (weeks_played + 1); i <= 16; i++) {
			playWeek();
		}
		
	}
	
	/**Plays all games up to, but not including, specified week**/
	public void playUpToWeek(int weekNumber) {
		
		for (int i = (weeks_played + 1); i < weekNumber; i++) {
			playWeek();
		}
		
	}
	
	/**String representation of complete regular season schedule**/
	public String toString() {
		
		String result = "";
		
		for (int i = 1; i <= 16; i++) {
			
			result += toString(i);
			
		}
		
		return result;
		
	}
	
	/**String representation of single week within the season schedule**/
	public String toString(int week) {
		
		String result = "";
		String temp_week = "Week " + week;
		
		result += (temp_week + ":" + "\n\n");
		
		for (Game g: schedule.get(temp_week)) {
			result += (g.toString() + "\n\n");
		}
		
		result += ("\n");
		
		return result;
		
	}


}
