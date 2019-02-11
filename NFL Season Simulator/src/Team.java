import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**Class that provides an implementation for an NFL team **/
public class Team {
	
	String name, abbr, city;	//basic info of team
	
	private Conference conf;	//team's conference
	private Division div;		//and division
	
	//initial offensive and defensive rankings
	int offRanking;
	int defRanking;
	
	private QuarterBack qb;		//team's qb
	HashMap<String, Game> schedule = new HashMap<String, Game>();	//team's schedule for the season
	
	Integer seeding = null;		//playoff seeding if qualified
	
	//fields to determine record of team
	int totalWins = 0;
	int totalLosses = 0;
	int totalTies = 0;
	int divWins = 0;
	int divLosses = 0;
	int divTies = 0;
	int confWins = 0;
	int confLosses = 0;
	int confTies = 0;
	
	//fields to help determine and update offensive and defensive rankings
	int totalPointsScored = 0;
	int totalPointsAllowed = 0;
	
	//total games played in general/division/conference
	int totalGamesPlayed = 0;
	int divGamesPlayed = 0;
	int confGamesPlayed = 0;
	
	public Team(String nameIn, String cityIn, String abbrIn,
				Conference c, Division d, QuarterBack qbIn,
				int offR, int defR) {
		
		name = nameIn;
		abbr = abbrIn;
		city = cityIn;
		conf = c;
		div = d;
		qb = qbIn;
		offRanking = offR;
		defRanking = defR;
		
	}
	
	/**Returns conference**/
	public Conference getConference() {
		return conf;
	}
	/**Returns division**/
	public Division getDivision() {
		return div;
	}
	/**Returns qb**/
	public QuarterBack getQB() {
		return qb;
	}
	
	/**Calculates team's points per game**/
	public double PPG() {
		
		/*Sets up the decimal format so that it includes up to 1 decimal places*/
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.CEILING);
		
		String temp;
		double result;
		
		if (totalGamesPlayed == 0) {
			return 0;
		}
		else {
			temp = df.format((double)totalPointsScored/(double)totalGamesPlayed);
			result = Double.valueOf(temp);
		}
		
		return result;
	}
	
	/**Calculates team's allowed points per game**/
	public double APPG() {
		
		/*Sets up the decimal format so that it includes up to 1 decimal places*/
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.CEILING);
		
		String temp;
		double result;
		
		if (totalGamesPlayed == 0) {
			return 0;
		}
		else {
			temp = df.format((double)totalPointsAllowed/(double)totalGamesPlayed);
			result = Double.valueOf(temp);
		}
		
		return result;
	}
	
	/**Calculates team's total win percentage**/
	public double totalWinPercentage() {
		
		/*Sets up the decimal format so that it includes up to 3 decimal places*/
		DecimalFormat df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.CEILING);
		
		double adjustedWins = totalWins + (totalTies * .5);	//adjusts wins to include ties
		String temp;
		double result;
		
		if (totalGamesPlayed == 0) {
			return 0;
		}
		
		else {
			
			temp = df.format((double)adjustedWins/(double)totalGamesPlayed);
			result = Double.valueOf(temp);
			
		}
		
		return result;
		
	}
	
	/**Calculates team's division win percentage**/
	public double divisionWinPercentage() {
		
		/*Sets up the decimal format so that it includes up to 3 decimal places*/
		DecimalFormat df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.CEILING);
		
		double adjustedWins = divWins + (divTies * .5);	//adjusts wins to include ties
		String temp;
		double result;
		
		if (divGamesPlayed == 0) {
			return 0;
		}
		
		else {
			
			temp = df.format((double)adjustedWins/(double)divGamesPlayed);
			result = Double.valueOf(temp);
			
		}
		
		return result;
		
	}
	
	/**Calculates team's conference win percentage**/
	public double conferenceWinPercentage() {
		
		/*Sets up the decimal format so that it includes up to 3 decimal places*/
		DecimalFormat df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.CEILING);
		
		double adjustedWins = confWins + (confTies * .5);	//adjusts wins to include ties
		String temp;
		double result;
		
		if (confGamesPlayed == 0) {
			return 0;
		}
		
		else {
			
			temp = df.format((double)adjustedWins/(double)confGamesPlayed);
			result = Double.valueOf(temp);
			
		}
		
		return result;
		
	}
	
	/**Adjusts records after a win**/
	public void won(Team opponent, Game game) {
		
		//Team record should not be affected
		//by games they play in the playoffs
		if (!Playoffs.playoffsStarted) {
		
			totalWins++;
			totalGamesPlayed++;
			
			if (opponent.getDivision() == this.getDivision()) {
				divWins++;
				divGamesPlayed++;
				confWins++;
				confGamesPlayed++;
			}
			else if (opponent.getConference() == this.getConference()) {
				confWins++;
				confGamesPlayed++;
			}
			
			//adds to the total points scored and total points allowed
			if (game.team1 == this) {
				this.totalPointsScored += game.team1_score;
				this.totalPointsAllowed += game.team2_score;
			}
			else {
				this.totalPointsScored += game.team2_score;
				this.totalPointsAllowed += game.team1_score;
			}
		
		}
		
	}
	
	/**Adjusts records after a loss**/
	public void lost(Team opponent, Game game) {
		
		//Team record should not be affected
		//by games they play in the playoffs
		if (!Playoffs.playoffsStarted) {
		
			totalLosses++;
			totalGamesPlayed++;
			
			if (opponent.getDivision() == this.getDivision()) {
				divLosses++;
				divGamesPlayed++;
				confLosses++;
				confGamesPlayed++;
			}
			else if (opponent.getConference() == this.getConference()) {
				confLosses++;
				confGamesPlayed++;
			}
			
			//adds to the total points scored and total points allowed
			if (game.team1 == this) {
				this.totalPointsScored += game.team1_score;
				this.totalPointsAllowed += game.team2_score;
			}
			else {
				this.totalPointsScored += game.team2_score;
				this.totalPointsAllowed += game.team1_score;
			}
		
		}
		
	}
	
	/**Adjusts records after a tie**/
	public void tied(Team opponent, Game game) {
		
		//Team record should not be affected
		//by games they play in the playoffs
		if (!Playoffs.playoffsStarted) {
		
			totalTies++;
			totalGamesPlayed++;
			
			if (opponent.getDivision() == this.getDivision()) {
				divTies++;
				divGamesPlayed++;
				confTies++;
				confGamesPlayed++;
			}
			else if (opponent.getConference() == this.getConference()) {
				confTies++;
				confGamesPlayed++;
			}
			
			//adds to the total points scored and total points allowed
			if (game.team1 == this) {
				this.totalPointsScored += game.team1_score;
				this.totalPointsAllowed += game.team2_score;
			}
			else {
				this.totalPointsScored += game.team2_score;
				this.totalPointsAllowed += game.team1_score;
			}
		
		}
		
	}
	
	/**Returns true if the specified team plays against this team in the current season**/
	public boolean playsAgainst(Team opponent) {
		for (String week: this.schedule.keySet()) {
			Game g = schedule.get(week);
			if (g.team1 == opponent || g.team2 == opponent) {
				return true;
			}
		}
		return false;
	}
	
	/**String representation of the team's schedule**/
	public String scheduleToString() {
		
		String result = "| " + this.toString() + " |" + "\n\n";
		
		for (int i = 1; i <= 16; i++) {
			String week = "Week " + i;
			Game g = this.schedule.get(week);
			
			result += (week + ": " );
			
			if (g.gamePlayed()) {
				
				if (g.winner == this) {
					result += "W  ";
				}
				else if (g.winner == null) {
					result += "T  ";
				}
				else {
					result += "L  ";
				}
				
			}
			
			result += (this.schedule.get(week).toString() + "\n\n");
		}
		
		result += "\n\n";
		
		return result;
		
	}
	
	/**String representation of a team's total record**/
	public String record() {
		
		String result = "(" + totalWins + "-" + totalLosses + "-" + totalTies + ")";
		
		return result;
		
	}
	
	/**String representation of team using its name and city**/
	public String toString() {
		return (this.city + " " + this.name);
	}
}
