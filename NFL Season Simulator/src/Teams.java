import java.util.ArrayList;

/**List of all NFL Teams**/
public class Teams {

	//NFC West
	final static Team Seahawks = new Team("Seahawks", "Seattle", "SEA", Conference.NFC, Division.NFC_WEST,
			new QuarterBack("Russell Wilson"), 6, 11);
	
	final static Team Niners = new Team("49ers", "San Francisco", "SF", Conference.NFC, Division.NFC_WEST,
			new QuarterBack("Jimmy Garoppolo"), 23, 24);
	
	final static Team Rams = new Team("Rams", "Los Angeles", "LAR", Conference.NFC, Division.NFC_WEST,
			new QuarterBack("Jared Goff"), 3, 17);
	
	final static Team Cardinals = new Team("Cardinals", "Arizona", "ARI", Conference.NFC, Division.NFC_WEST,
			new QuarterBack("Josh Rosen"), 32, 27);
	
	
	//NFC East
	final static Team Redskins = new Team("Redskins", "Washington", "WAS", Conference.NFC, Division.NFC_EAST,
			new QuarterBack("Alex Smith"), 29, 14);
	
	final static Team Giants = new Team("Giants", "New York", "NYG", Conference.NFC, Division.NFC_EAST,
			new QuarterBack("Eli Manning"), 20, 23);
	
	final static Team Cowboys = new Team("Cowboys", "Dallas", "DAL", Conference.NFC, Division.NFC_EAST,
			new QuarterBack("Dak Prescott"), 24, 4);
	
	final static Team Eagles = new Team("Eagles", "Philadelphia", "PHI", Conference.NFC, Division.NFC_EAST,
			new QuarterBack("Carson Wentz"), 17, 16);
	
	
	//NFC South
	final static Team Saints = new Team("Saints", "New Orleans", "NO", Conference.NFC, Division.NFC_SOUTH,
			new QuarterBack("Drew Brees"), 2, 8);
	
	final static Team Buccaneers = new Team("Buccaneers", "Tampa Bay", "TB", Conference.NFC, Division.NFC_SOUTH,
			new QuarterBack("Ryan Fitzpatrick"), 13, 30);
	
	final static Team Panthers = new Team("Panthers", "Carolina", "CAR", Conference.NFC, Division.NFC_SOUTH,
			new QuarterBack("Cam Newton"), 18, 21);
	
	final static Team Falcons = new Team("Falcons", "Atlanta", "ATL", Conference.NFC, Division.NFC_SOUTH,
			new QuarterBack("Matt Ryan"), 11, 25);
	
	
	//NFC North
	final static Team Packers = new Team("Packers", "Green Bay", "GB", Conference.NFC, Division.NFC_NORTH,
			new QuarterBack("Aaron Rodgers"), 12, 22);
	
	final static Team Lions = new Team("Lions", "Detroit", "DET", Conference.NFC, Division.NFC_NORTH,
			new QuarterBack("Matthew Stafford"), 26, 19);
	
	final static Team Bears = new Team("Bears", "Chicago", "CHI", Conference.NFC, Division.NFC_NORTH,
			new QuarterBack("Mitchell Trubisky"), 9, 3);
	
	final static Team Vikings = new Team("Vikings", "Minnesota", "MIN", Conference.NFC, Division.NFC_NORTH,
			new QuarterBack("Kirk Cousins"), 16, 7);
	
	
	//AFC West
	final static Team Chiefs = new Team("Chiefs", "Kansas City", "KC", Conference.AFC, Division.AFC_WEST,
			new QuarterBack("Patrick Mahomes"), 1, 29);
	
	final static Team Chargers = new Team("Chargers", "Los Angeles", "LAC", Conference.AFC, Division.AFC_WEST,
			new QuarterBack("Phillip Rivers"), 5, 9);
	
	final static Team Broncos = new Team("Broncos", "Denver", "DEN", Conference.AFC, Division.AFC_WEST,
			new QuarterBack("Case Keenum"), 22, 12);
	
	final static Team Raiders = new Team("Raideres", "Oakland", "OAK", Conference.AFC, Division.AFC_WEST,
			new QuarterBack("Derek Carr"), 28, 31);
	
	
	//AFC EAST
	final static Team Patriots = new Team("Patriots", "New England", "NE", Conference.AFC, Division.AFC_EAST,
			new QuarterBack("Tom Brady"), 8, 10);
	
	final static Team Bills = new Team("Bills", "Buffalo", "BUF", Conference.AFC, Division.AFC_EAST,
			new QuarterBack("Josh Allen"), 31, 18);
	
	final static Team Dolphins = new Team("Dolphins", "Miami", "MIA", Conference.AFC, Division.AFC_EAST,
			new QuarterBack("Ryan Tannehill"), 25, 26);
	
	final static Team Jets = new Team("Jets", "New York", "NYJ", Conference.AFC, Division.AFC_EAST,
			new QuarterBack("Sam Darnold"), 21, 28);
	
	
	//AFC South
	final static Team Texans = new Team("Texans", "Houston", "HOU", Conference.AFC, Division.AFC_SOUTH,
			new QuarterBack("Deshaun Watson"), 10, 6);
	
	final static Team Colts = new Team("Colts", "Indianapolis", "IND", Conference.AFC, Division.AFC_SOUTH,
			new QuarterBack("Andrew Luck"), 7, 13);
	
	final static Team Titans = new Team("Titans", "Tennessee", "TEN", Conference.AFC, Division.AFC_SOUTH,
			new QuarterBack("Marcus Mariota"), 27, 2);
	
	final static Team Jaguars = new Team("Jaguars", "Jacksonville", "JAX", Conference.AFC, Division.AFC_SOUTH,
			new QuarterBack("Blake Bortles"), 30, 5);
	
	
	//AFC North
	final static Team Ravens = new Team("Ravens", "Baltimore", "BAL", Conference.AFC, Division.AFC_NORTH,
			new QuarterBack("Joe Flacco"), 14, 1);
	
	final static Team Steelers = new Team("Steelers", "Pittsburgh", "PIT", Conference.AFC, Division.AFC_NORTH,
			new QuarterBack("Ben Reothlisberger"), 4, 15);
	
	final static Team Bengals = new Team("Bengals", "Cincinnati", "CIN", Conference.AFC, Division.AFC_NORTH,
			new QuarterBack("Andy Dalton"), 15, 32);
	
	final static Team Browns = new Team("Browns", "Cleveland", "CLE", Conference.AFC, Division.AFC_NORTH,
			new QuarterBack("Baker Mayfield"), 19, 20);
	
	
	
	
	
	//Lists that teams should be added to
	final static ArrayList<Team> nfc = new ArrayList<Team>();
	final static ArrayList<Team> nfc_west = new ArrayList<Team>();
	final static ArrayList<Team> nfc_south = new ArrayList<Team>();
	final static ArrayList<Team> nfc_north = new ArrayList<Team>();
	final static ArrayList<Team> nfc_east = new ArrayList<Team>();
	
	final static ArrayList<Team> afc = new ArrayList<Team>();
	final static ArrayList<Team> afc_west = new ArrayList<Team>();
	final static ArrayList<Team> afc_south = new ArrayList<Team>();
	final static ArrayList<Team> afc_north = new ArrayList<Team>();
	final static ArrayList<Team> afc_east = new ArrayList<Team>();
	
	final static ArrayList<Team> allTeams = new ArrayList<Team>();
	
	
	//Adds teams to correct lists
	public static void initialize() {
		
		allTeams.add(Bears);
		allTeams.add(Packers);
		allTeams.add(Lions);
		allTeams.add(Vikings);
		
		allTeams.add(Redskins);
		allTeams.add(Eagles);
		allTeams.add(Giants);
		allTeams.add(Cowboys);
		
		allTeams.add(Niners);
		allTeams.add(Seahawks);
		allTeams.add(Rams);
		allTeams.add(Cardinals);
		
		allTeams.add(Saints);
		allTeams.add(Panthers);
		allTeams.add(Buccaneers);
		allTeams.add(Falcons);
		
		allTeams.add(Patriots);
		allTeams.add(Jets);
		allTeams.add(Dolphins);
		allTeams.add(Bills);
		
		allTeams.add(Ravens);
		allTeams.add(Browns);
		allTeams.add(Steelers);
		allTeams.add(Bengals);
		
		allTeams.add(Texans);
		allTeams.add(Colts);
		allTeams.add(Titans);
		allTeams.add(Jaguars);
		
		allTeams.add(Chiefs);
		allTeams.add(Chargers);
		allTeams.add(Broncos);
		allTeams.add(Raiders);
		
		
		for (Team t: allTeams) {
			
			switch(t.getDivision()) {
			
			case NFC_EAST:
				nfc.add(t);
				nfc_east.add(t);
				break;
				
			case NFC_WEST:
				nfc.add(t);
				nfc_west.add(t);
				break;
				
			case NFC_NORTH:
				nfc.add(t);
				nfc_north.add(t);
				break;
				
			case NFC_SOUTH:
				nfc.add(t);
				nfc_south.add(t);
				break;
				
			case AFC_EAST:
				afc.add(t);
				afc_east.add(t);
				break;
				
			case AFC_WEST:
				afc.add(t);
				afc_west.add(t);
				break;
				
			case AFC_NORTH:
				afc.add(t);
				afc_north.add(t);
				break;
				
			case AFC_SOUTH:
				afc.add(t);
				afc_south.add(t);
				break;
			
			}
			
		}
	}
	
}
