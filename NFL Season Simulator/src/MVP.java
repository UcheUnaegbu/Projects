import java.util.ArrayList;

/**Class that determines the MVP of the league for the Regular Season**/
public class MVP {

	private static QuarterBack league_MVP;
	private static Team MVP_team;
	private static ArrayList<Team> top_teams = new ArrayList<Team>();
	
	/**Sets the league MVP**/
	public static void setMVP() {
		
		//set the top 3 teams in the league
		for (int i = 0; i < 3; i++) {
			top_teams.add(Standings.nfc_standings[i]);
			top_teams.add(Standings.afc_standings[i]);
		}
		
		league_MVP = top_teams.get(0).getQB();
		MVP_team = top_teams.get(0);
		
		//sets the MVP based on who has the most tds
		for (int i = 1; i < 6; i++) {
			
			if (top_teams.get(i).getQB().tds > league_MVP.tds) {
				league_MVP = top_teams.get(i).getQB();
				MVP_team = top_teams.get(i);
			}
			
		}
		
	}
	
	/**Prints out MVP winner message**/
	public static void printMVPMessage() {
		
		System.out.println("Congratulations to " + league_MVP.name + " (" + league_MVP.tds + " Touchdowns)" + 
		" of the " + MVP_team.toString() + " for winning league MVP!");
		
	}
	
}
