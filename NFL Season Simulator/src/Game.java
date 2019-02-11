import java.util.ArrayList;
import java.util.Random;

/**Defines a game that takes place**/
public class Game {

	Team team1, team2, winner;
	public int team1_score = 0, team2_score = 0;	//scores for each team
	
	//represents team records at the time this game was played
	private int team1_wins, team1_losses, team1_ties, team2_wins, team2_losses, team2_ties;
	
	//represents the number of touchdowns each qb produced during this game
	int team1_qb_tds, team2_qb_tds;
	
	boolean OT = false;
	private boolean played = false;
	
	public Game(Team t1, Team t2) {
		team1 = t1;
		team2 = t2;
	}
	
	/**Simulates the playing of an NFL game, using
	 * the fact that, on average, and NFL team will
	 * have 12 possessions during any game, and will
	 * score, on average, about 22 points a game. Also
	 * taken into account, are team's offensive ratings,
	 * defensive ratings, and records. This allows for
	 * more likely game outcomes.**/
	public void playGame() {
		Random rand = new Random();
		
		//holds the outcome possibility for each team's drives
		ArrayList<Score> team1_drive_probability = new ArrayList<Score>();
		ArrayList<Score> team2_drive_probability = new ArrayList<Score>();
		
		//Initializes team scoring possibilities with the probability
		//that any given drive has a 1/4 chance of resulting in a TD,
		//a 1/12 chance of a field goal, and 2/3 chance of no score
		for (int i = 0; i < 3; i++) {
			team1_drive_probability.add(Score.TOUCHDOWN);
			team2_drive_probability.add(Score.TOUCHDOWN);
		}
		team1_drive_probability.add(Score.FIELD_GOAL);
		team2_drive_probability.add(Score.FIELD_GOAL);
		for (int i = 0; i < 8; i++) {
			team1_drive_probability.add(Score.NONE);
			team2_drive_probability.add(Score.NONE);
		}
		
		//Adjusts probabilities based off of team's
		//offensive and defensive rankings
		if ((team1.offRanking - team2.defRanking) <= -20) {
			team1_drive_probability.add(Score.TOUCHDOWN);
			team1_drive_probability.remove(Score.NONE);
		}
		else if ((team1.offRanking - team2.defRanking) <= -10) {
			team1_drive_probability.add(Score.FIELD_GOAL);
			team1_drive_probability.remove(Score.NONE);
		}
		else if ((team1.offRanking - team2.defRanking) >= 15) {
			team1_drive_probability.add(Score.FIELD_GOAL);
			team1_drive_probability.remove(Score.TOUCHDOWN);
		}
		
		if ((team2.offRanking - team1.defRanking) <= -20) {
			team2_drive_probability.add(Score.TOUCHDOWN);
			team2_drive_probability.remove(Score.NONE);
		}
		else if ((team2.offRanking - team1.defRanking) <= -10) {
			team2_drive_probability.add(Score.FIELD_GOAL);
			team2_drive_probability.remove(Score.NONE);
		}
		else if ((team2.offRanking - team1.defRanking) >= 15) {
			team2_drive_probability.add(Score.FIELD_GOAL);
			team2_drive_probability.remove(Score.TOUCHDOWN);
		}
		
		//Trending teams and win patterns typically start to
		//form around week 6
		if (team1.totalGamesPlayed >= 6) {
			
			//adjusts drive probabilities based off of season trends
			if ((team1.totalWinPercentage() - team2.totalWinPercentage()) >= .300) {
				team1_drive_probability.add(Score.FIELD_GOAL);
				team1_drive_probability.remove(Score.NONE);
			}
			else if ((team2.totalWinPercentage() - team1.totalWinPercentage()) >= .300) {
				team2_drive_probability.add(Score.FIELD_GOAL);
				team2_drive_probability.remove(Score.NONE);
			}
			
		}
		
		int drive_result;
		//simulates 10 drives of both teams
		for (int i = 0; i < 10; i++) {
			
			//drive result for team1
			drive_result = rand.nextInt(12);
			Score t1drive = team1_drive_probability.get(drive_result);
			
			switch(t1drive) {
			
			case TOUCHDOWN:
				int temp = rand.nextInt(3);
				if (temp == 0 || temp == 1) {	
					team1.getQB().score();	//there is a 2/3 chance that the TD scored was by the QB
					team1_qb_tds++;	//increments qb td count for the gams
				}
				team1_score += 7;
				break;
				
			case FIELD_GOAL:
				team1_score += 3;
				break;
			
			case NONE:
				break;
			
			}
			
			
			//drive result for team1
			drive_result = rand.nextInt(12);
			Score t2drive = team2_drive_probability.get(drive_result);
			
			switch(t2drive) {
			
			case TOUCHDOWN:
				int temp = rand.nextInt(3);
				if (temp == 0 || temp == 1) {	
					team2.getQB().score();	//there is a 2/3 chance that the TD scored was by the QB
					team2_qb_tds++;	//increments qb td count for the gams
				}
				team2_score += 7;
				break;
				
			case FIELD_GOAL:
				team2_score += 3;
				break;
			
			case NONE:
				break;
			
			}
			
		}
		
		//decides the winner of the game
		if (team1_score > team2_score) {
			winner = team1;
			team1.won(team2, this);
			team2.lost(team1, this);
			played = true;
			updateRecords();
		}
		else if (team2_score > team1_score) {
			winner = team2;
			team2.won(team1, this);
			team1.lost(team2, this);
			played = true;
			updateRecords();
		}
		else { //OT period needed
			
			if (Playoffs.playoffsStarted) {
				playPlayoffOT();	//playoff OT period which cannot end in a draw
			}
			else {
				playOT();	//regular season OT period which can end in a tie
			}
			
		}
		
	}
	
	
	/**Simulates a regular season overtime session, in which
	 * the game can end in a tie**/
	private void playOT() {
		Random rand = new Random();
		OT = true;
		
		Team curr;
		int coin_toss = rand.nextInt(2);
		
		//decides which team gets the ball first in OT
		if (coin_toss == 0) {
			curr = team1;
		}
		else {
			curr = team2;
		}
		
		boolean firstOTDrive = true, playedSecondDrive = false;
		int drive_result;
		
		for (int i = 0; i < 4; i++) {	//typically, there will be at most 4 total OT drives
			
			drive_result = rand.nextInt(10);	//result of drive
			
			if (drive_result == 0 || drive_result == 1 ||
					drive_result == 2) {	//about 3/10 chance a td scored and game is automatically over
				if (curr == team1) {
					team1_score += 6;
					
					int qbscore = rand.nextInt(3);
					if (qbscore == 0 || qbscore == 1) {	
						team1.getQB().score();	//there is a 2/3 chance that the TD scored was by the QB
						team1_qb_tds++;	//increments qb td count for the gams
					}				
					
					winner = team1;
					team1.won(team2, this);
					team2.lost(team1, this);
					played = true;
					updateRecords();
					return;
				}
				else if (curr == team2) {
					team2_score += 6;
					
					int qbscore = rand.nextInt(3);
					if (qbscore == 0 || qbscore == 1) {	
						team2.getQB().score();	//there is a 2/3 chance that the TD scored was by the QB
						team2_qb_tds++;	//increments qb td count for the gams
					}
					
					winner = team2;
					team2.won(team1, this);
					team1.lost(team2, this);
					played = true;
					updateRecords();
					return;
				}
				
			}
			
			else if (drive_result == 3 || drive_result == 4 ||
					drive_result == 5 || drive_result == 6 || drive_result == 7) {	//about 5/10 chance a field goal was made
				if (curr == team1) {
					team1_score += 3;				
				}
				else if (curr == team2) {
					team2_score += 3;
				}
			}
			
			if (!firstOTDrive) {
				playedSecondDrive = true;	//second drive has been played at this point
			}
			
			if (playedSecondDrive) {	//both teams have possessed the ball at least once
				if (team1_score > team2_score) {	//team who makes first field goal after first drive wins
					winner = team1;
					team1.won(team2, this);
					team2.lost(team1, this);
					played = true;
					updateRecords();
					return;
				}
				else if (team2_score > team1_score) {
					winner = team2;
					team2.won(team1, this);
					team1.lost(team2, this);
					played = true;
					updateRecords();
					return;
				}
			}
			
			firstOTDrive = false;	//first drive has now been played
			//change of possession
			if (curr == team1) {
				curr = team2;
			}
			else {
				curr = team1;
			}
			
		}
		
		//if winner still not decided, game ends in a tie
		winner = null;
		team1.tied(team2, this);
		team2.tied(team1, this);
		played = true;
		updateRecords();
		
	}
	
	/**Simulates a playoff overtime session, in which
	 * cannot end in a tie**/
	private void playPlayoffOT() {
		Random rand = new Random();
		OT = true;
		
		Team curr;
		int coin_toss = rand.nextInt(2);
		
		//decides which team gets the ball first in OT
		if (coin_toss == 0) {
			curr = team1;
		}
		else {
			curr = team2;
		}
		
		boolean firstOTDrive = true, playedSecondDrive = false;
		int drive_result;
		
		while (true) {	//OT session will continue indefinitely, until winner decided
			
			drive_result = rand.nextInt(10);	//result of drive
			
			if (drive_result == 0 || drive_result == 1 ||
					drive_result == 2) {	//about 3/10 chance a td scored and game is automatically over
				if (curr == team1) {
					team1_score += 6;
					
					int qbscore = rand.nextInt(3);
					if (qbscore == 0 || qbscore == 1) {	
						team1.getQB().score();	//there is a 2/3 chance that the TD scored was by the QB
						team1_qb_tds++;	//increments qb td count for the gams
					}				
					
					winner = team1;
					team1.won(team2, this);
					team2.lost(team1, this);
					played = true;
					updateRecords();
					return;
				}
				else if (curr == team2) {
					team2_score += 6;
					
					int qbscore = rand.nextInt(3);
					if (qbscore == 0 || qbscore == 1) {	
						team2.getQB().score();	//there is a 2/3 chance that the TD scored was by the QB
						team2_qb_tds++;	//increments qb td count for the gams
					}
					
					winner = team2;
					team2.won(team1, this);
					team1.lost(team2, this);
					played = true;
					updateRecords();
					return;
				}
				
			}
			
			else if (drive_result == 3 || drive_result == 4 ||
					drive_result == 5 || drive_result == 6 || drive_result == 7) {	//about 5/10 chance a field goal was made
				if (curr == team1) {
					team1_score += 3;				
				}
				else if (curr == team2) {
					team2_score += 3;
				}
			}
			
			if (!firstOTDrive) {
				playedSecondDrive = true;	//second drive has been played at this point
			}
			
			if (playedSecondDrive) {	//both teams have possessed the ball at least once
				if (team1_score > team2_score) {	//team who makes first field goal after first drive wins
					winner = team1;
					team1.won(team2, this);
					team2.lost(team1, this);
					played = true;
					updateRecords();
					return;
				}
				else if (team2_score > team1_score) {
					winner = team2;
					team2.won(team1, this);
					team1.lost(team2, this);
					played = true;
					updateRecords();
					return;
				}
			}
			
			firstOTDrive = false;	//first drive has now been played
			//change of possession
			if (curr == team1) {
				curr = team2;
			}
			else {
				curr = team1;
			}
			
		}
		
	}
	
	/**Returns true if the game has been played**/
	public boolean gamePlayed() {
		
		if (played) {
			return true;
		}
		
		return false;
		
	}
	
	
	/**Reflects each team's record at the time this game ended**/
	private void updateRecords() {
		team1_wins = team1.totalWins;
		team1_losses = team1.totalLosses;
		team1_ties = team1.totalTies;
		
		team2_wins = team2.totalWins;
		team2_losses = team2.totalLosses;
		team2_ties = team2.totalTies;
	}
	
	/**String representation of a Game. Score of game is included if game has been played**/
	public String toString() {
		
		String result = "";
		
		if (played) {
			result += ("(" + team1_wins + "-" + team1_losses + "-" + team1_ties + ") ");
			result += (team1.abbr + "   ");
		}
		else {
			result += ("(" + team1.totalWins + "-" + team1.totalLosses + "-" + team1.totalTies + ") ");
			result += (team1.abbr + "   ");
		}
		
		if (played) {
			result += (team1_score + "   ");
		}
		
		result += "VS   ";
		
		if (played) {
			result += (team2_score + "   ");
			result += (team2.abbr);
			result += (" (" + team2_wins + "-" + team2_losses + "-" + team2_ties + ")");
		}
		else {
			result += (team2.abbr);
			result += (" (" + team2.totalWins + "-" + team2.totalLosses + "-" + team2.totalTies + ")");
		}
		
		if (OT) {
			result += "  OT";
		}
		
		if (played) {
			result += "   ------   ";
			result += (team1.getQB().name + " {" + team1_qb_tds + " TDs}, ");
			result += (team2.getQB().name + " {" + team2_qb_tds + " TDs}");
		}
		
		return result;
		
	}
	
}
