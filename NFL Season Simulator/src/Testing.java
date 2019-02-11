/**Class for testing**/
public class Testing {

	public static void main(String[] args) {
		
		Teams.initialize();
		Stats.initRankings();
		Standings.initStandings();
		RegularSeason r = new RegularSeason();
		
		System.out.print(r.toString(1));
		System.out.print(r.toString(2));
		System.out.print(r.toString());
		
		
		
	}

}
