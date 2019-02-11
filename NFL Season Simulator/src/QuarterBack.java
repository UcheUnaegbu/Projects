/**Class to define an NFL Quarterback**/
public class QuarterBack {

	String name;
	int tds = 0;
	
	public QuarterBack(String nameIn) {
		
		name = nameIn;
		
	}
	
	public void score() {
		
		//QB total tds for the season should not be
		//affected by the tds they may score in the
		//playoffs
		if (!Playoffs.playoffsStarted) {
			tds++;
		}
	}
	
}
