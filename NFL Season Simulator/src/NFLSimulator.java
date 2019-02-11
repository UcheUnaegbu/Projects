import java.util.Scanner;

/**Simulates the NFL season. Schedule will differ
 * from season to season. Additionally, rankings and standings
 * are updated as the Simulator progresses. Rankings and
 * Standings are taken into consideration when
 * two teams play one another.**/
public class NFLSimulator {

	private static RegularSeason regSeason;
	private static Playoffs playoffs;
	private static boolean mvpSet = false;
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Teams.initialize();  //initializes teams and rankings
		Stats.initRankings();  //initializes rankings
		Standings.initStandings();  //initializes standings
		
		regSeason = new RegularSeason();  //creates the schedule for the season
		
		System.out.println("\nWelcome to the NFL Simulator!\n");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Advancing to Week 1...\n");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(regSeason.toString(1));	//prints the schedule for the first week of the season
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		regularSeasonPrompt();	//prompts the user to choose an option

	}
	
	/**Prompts the user to select an option. Handles
	 * acceptable options for the regular season up
	 * until all 16 weeks have been played.**/
	private static void regularSeasonPrompt() {
		
		if (regSeason.weeks_played == 16) {
			weekSixteenPrompt();	//week 16 will have a different prompt, including advancement to playoffs
		}
		
		String prompt = "\nChoose an option:\n\n";
		int option;
		
		prompt += ("1. Play all the games in the current week\n");
		prompt += ("2. Simulate all games up to a specified week\n");
		prompt += ("3. Simulate the rest of the regular season\n\n");
		
		prompt += ("4. View a team's schedule\n");
		prompt += ("5. View the games for a specified week within the regular season\n\n");
		
		prompt += ("6. View Standings\n\n");
		
		prompt += ("7. View QB Rankings\n");
		prompt += ("8. View Offensive Rankings\n");
		prompt += ("9. View Defensive Rankings\n\n");
		
		System.out.print(prompt);
		
		option = scanner.nextInt();
		
		System.out.print("\n");
		
		switch(option) {
		
		case 1:
			regSeason.playWeek();
			System.out.println(regSeason.toString(regSeason.weeks_played));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			promptAfterPlayingWeek(); 	//prompt slightly changes after game has been played
			
			break;
			
		case 2:
			
			int selectedWeek;
			
			while (true) {
				
				System.out.println("\nSelect a week (1-16). Select 0 to cancel.\n");
				
				selectedWeek = scanner.nextInt();
	
				//cancels the request
				if (selectedWeek == 0) {
					regularSeasonPrompt();
				}
				
				//ensures an appropriate week is selected
				if (selectedWeek >= 1 && selectedWeek <=16) {
					break;
				}
				else {
					System.out.print("\nInvalid input.\n");
					try {
						Thread.sleep(1700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			regSeason.playUpToWeek(selectedWeek);
			System.out.print("\n");
			System.out.println(regSeason.toString(selectedWeek));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			regularSeasonPrompt();
			
			break;
			
		case 3:
			regSeason.playRestOfSeason();;
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			regularSeasonPrompt();
			
			break;
			
		case 4:
			
			Team selectedTeam;
			
			String teams = "AFC West:\n";
			int counter = 1;
			
			for (Team t: Teams.afc_west) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}		
			teams += "\nAFC East:\n";
			for (Team t: Teams.afc_east) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nAFC North:\n";
			for (Team t: Teams.afc_north) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nAFC South:\n";
			for (Team t: Teams.afc_south) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC West:\n";
			for (Team t: Teams.nfc_west) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC East:\n";
			for (Team t: Teams.nfc_east) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC North:\n";
			for (Team t: Teams.nfc_north) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC South:\n";
			for (Team t: Teams.nfc_south) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			
			while (true) {
				System.out.println("Select a team (1-32). Select 0 to cancel.\n");		
				System.out.print("\n"+ teams + "\n\n");
				
				int temp = scanner.nextInt();
				System.out.print("\n");
				
				if (temp == 0) {
					regularSeasonPrompt();
				}
				else if (temp == 1) {
					selectedTeam = Teams.Chiefs;
					break;
				}
				else if (temp == 2) {
					selectedTeam = Teams.Chargers;
					break;
				}
				else if (temp == 3) {
					selectedTeam = Teams.Broncos;
					break;
				}
				else if (temp == 4) {
					selectedTeam = Teams.Raiders;
					break;
				}
				else if (temp == 5) {
					selectedTeam = Teams.Patriots;
					break;
				}
				else if (temp == 6) {
					selectedTeam = Teams.Jets;
					break;
				}
				else if (temp == 7) {
					selectedTeam = Teams.Dolphins;
					break;
				}
				else if (temp == 8) {
					selectedTeam = Teams.Bills;
					break;
				}
				else if (temp == 9) {
					selectedTeam = Teams.Ravens;
					break;
				}
				else if (temp == 10) {
					selectedTeam = Teams.Browns;
					break;
				}
				else if (temp == 11) {
					selectedTeam = Teams.Steelers;
					break;
				}
				else if (temp == 12) {
					selectedTeam = Teams.Bengals;
					break;
				}
				else if (temp == 13) {
					selectedTeam = Teams.Texans;
					break;
				}
				else if (temp == 14) {
					selectedTeam = Teams.Colts;
					break;
				}
				else if (temp == 15) {
					selectedTeam = Teams.Titans;
					break;
				}
				else if (temp == 16) {
					selectedTeam = Teams.Jaguars;
					break;
				}
				else if (temp == 17) {
					selectedTeam = Teams.Niners;
					break;
				}
				else if (temp == 18) {
					selectedTeam = Teams.Seahawks;
					break;
				}
				else if (temp == 19) {
					selectedTeam = Teams.Rams;
					break;
				}
				else if (temp == 20) {
					selectedTeam = Teams.Cardinals;
					break;
				}
				else if (temp == 21) {
					selectedTeam = Teams.Redskins;
					break;
				}
				else if (temp == 22) {
					selectedTeam = Teams.Eagles;
					break;
				}
				else if (temp == 23) {
					selectedTeam = Teams.Giants;
					break;
				}
				else if (temp == 24) {
					selectedTeam = Teams.Cowboys;
					break;
				}
				else if (temp == 25) {
					selectedTeam = Teams.Bears;
					break;
				}
				else if (temp == 26) {
					selectedTeam = Teams.Packers;
					break;
				}
				else if (temp == 27) {
					selectedTeam = Teams.Lions;
					break;
				}
				else if (temp == 28) {
					selectedTeam = Teams.Vikings;
					break;
				}
				else if (temp == 29) {
					selectedTeam = Teams.Saints;
					break;
				}
				else if (temp == 30) {
					selectedTeam = Teams.Panthers;
					break;
				}
				else if (temp == 31) {
					selectedTeam = Teams.Buccaneers;
					break;
				}
				else if (temp == 32) {
					selectedTeam = Teams.Falcons;
					break;
				}
				else {
					System.out.println("\nInvalid input.");
					try {
						Thread.sleep(1700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			System.out.print(selectedTeam.scheduleToString());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			regularSeasonPrompt();
			break;
			
		case 5:
			
			int selectedWeek2;
			
			while (true) {
				
				System.out.println("\nSelect a week (1-16). Select 0 to cancel.\n");
				
				selectedWeek2 = scanner.nextInt();
	
				//cancels the request
				if (selectedWeek2 == 0) {
					regularSeasonPrompt();
				}
				
				//ensures an appropriate week is selected
				if (selectedWeek2 >= 1 && selectedWeek2 <=16) {
					break;
				}
				else {
					System.out.print("\nInvalid input.\n");
					try {
						Thread.sleep(1700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			System.out.print("\n");
			System.out.println(regSeason.toString(selectedWeek2));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			regularSeasonPrompt();
			
			break;
			
		case 6:
			
			Standings.printStandings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			regularSeasonPrompt();
			break;
			
		case 7:
			
			Stats.printQBRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			regularSeasonPrompt();
			break;
			
		case 8:
			
			Stats.printOffensiveRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			regularSeasonPrompt();
			break;
			
		case 9:
			
			Stats.printDefensiveRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			regularSeasonPrompt();
			break;
			
		default:
			
			System.out.print("Invalid input.\n");
			regularSeasonPrompt();
			break;
		}
		
	}
	
	/**Shows the prompts after a week has been played**/
	private static void promptAfterPlayingWeek() {
		
		if (regSeason.weeks_played == 16) {
			weekSixteenPrompt();	//week 16 will have a different prompt, including advancement to playoffs
		}
		
		String prompt = "\nChoose an option:\n\n";
		int option;
		
		int nextWeek = regSeason.weeks_played + 1;
		
		prompt += ("1. Advance to Week " + nextWeek + "\n");
		prompt += ("2. Simulate all games up to a specified week\n");
		prompt += ("3. Simulate the rest of the regular season\n\n");
		
		prompt += ("4. View a team's schedule\n");
		prompt += ("5. View the games for a specified week within the regular season\n\n");
		
		prompt += ("6. View Standings\n\n");
		
		prompt += ("7. View QB Rankings\n");
		prompt += ("8. View Offensive Rankings\n");
		prompt += ("9. View Defensive Rankings\n\n");
		
		System.out.print(prompt);
		
		option = scanner.nextInt();
		
		System.out.print("\n");
		
		switch(option) {
		
		case 1:
			
			System.out.println(regSeason.toString(nextWeek));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			regularSeasonPrompt(); 	//prompt changes once a new week starts
			
			break;
			
		case 2:
			
			int selectedWeek;
			
			while (true) {
				
				System.out.println("\nSelect a week (1-16). Select 0 to cancel.\n");
				
				selectedWeek = scanner.nextInt();
	
				//cancels the request
				if (selectedWeek == 0) {
					promptAfterPlayingWeek();
				}
				
				//ensures an appropriate week is selected
				if (selectedWeek >= 1 && selectedWeek <=16) {
					break;
				}
				else {
					System.out.print("\nInvalid input.\n");
					try {
						Thread.sleep(1700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			regSeason.playUpToWeek(selectedWeek);
			System.out.print("\n");
			System.out.println(regSeason.toString(selectedWeek));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			regularSeasonPrompt();
			
			break;
			
		case 3:
			regSeason.playRestOfSeason();;
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			regularSeasonPrompt();
			
			break;
			
		case 4:
			
			Team selectedTeam;
			
			String teams = "AFC West:\n";
			int counter = 1;
			
			for (Team t: Teams.afc_west) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}		
			teams += "\nAFC East:\n";
			for (Team t: Teams.afc_east) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nAFC North:\n";
			for (Team t: Teams.afc_north) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nAFC South:\n";
			for (Team t: Teams.afc_south) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC West:\n";
			for (Team t: Teams.nfc_west) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC East:\n";
			for (Team t: Teams.nfc_east) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC North:\n";
			for (Team t: Teams.nfc_north) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC South:\n";
			for (Team t: Teams.nfc_south) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			
			while (true) {
				System.out.println("Select a team (1-32). Select 0 to cancel.\n");		
				System.out.print("\n"+ teams + "\n\n");
				
				int temp = scanner.nextInt();
				System.out.print("\n");
				
				if (temp == 0) {
					promptAfterPlayingWeek();
				}
				else if (temp == 1) {
					selectedTeam = Teams.Chiefs;
					break;
				}
				else if (temp == 2) {
					selectedTeam = Teams.Chargers;
					break;
				}
				else if (temp == 3) {
					selectedTeam = Teams.Broncos;
					break;
				}
				else if (temp == 4) {
					selectedTeam = Teams.Raiders;
					break;
				}
				else if (temp == 5) {
					selectedTeam = Teams.Patriots;
					break;
				}
				else if (temp == 6) {
					selectedTeam = Teams.Jets;
					break;
				}
				else if (temp == 7) {
					selectedTeam = Teams.Dolphins;
					break;
				}
				else if (temp == 8) {
					selectedTeam = Teams.Bills;
					break;
				}
				else if (temp == 9) {
					selectedTeam = Teams.Ravens;
					break;
				}
				else if (temp == 10) {
					selectedTeam = Teams.Browns;
					break;
				}
				else if (temp == 11) {
					selectedTeam = Teams.Steelers;
					break;
				}
				else if (temp == 12) {
					selectedTeam = Teams.Bengals;
					break;
				}
				else if (temp == 13) {
					selectedTeam = Teams.Texans;
					break;
				}
				else if (temp == 14) {
					selectedTeam = Teams.Colts;
					break;
				}
				else if (temp == 15) {
					selectedTeam = Teams.Titans;
					break;
				}
				else if (temp == 16) {
					selectedTeam = Teams.Jaguars;
					break;
				}
				else if (temp == 17) {
					selectedTeam = Teams.Niners;
					break;
				}
				else if (temp == 18) {
					selectedTeam = Teams.Seahawks;
					break;
				}
				else if (temp == 19) {
					selectedTeam = Teams.Rams;
					break;
				}
				else if (temp == 20) {
					selectedTeam = Teams.Cardinals;
					break;
				}
				else if (temp == 21) {
					selectedTeam = Teams.Redskins;
					break;
				}
				else if (temp == 22) {
					selectedTeam = Teams.Eagles;
					break;
				}
				else if (temp == 23) {
					selectedTeam = Teams.Giants;
					break;
				}
				else if (temp == 24) {
					selectedTeam = Teams.Cowboys;
					break;
				}
				else if (temp == 25) {
					selectedTeam = Teams.Bears;
					break;
				}
				else if (temp == 26) {
					selectedTeam = Teams.Packers;
					break;
				}
				else if (temp == 27) {
					selectedTeam = Teams.Lions;
					break;
				}
				else if (temp == 28) {
					selectedTeam = Teams.Vikings;
					break;
				}
				else if (temp == 29) {
					selectedTeam = Teams.Saints;
					break;
				}
				else if (temp == 30) {
					selectedTeam = Teams.Panthers;
					break;
				}
				else if (temp == 31) {
					selectedTeam = Teams.Buccaneers;
					break;
				}
				else if (temp == 32) {
					selectedTeam = Teams.Falcons;
					break;
				}
				else {
					System.out.println("\nInvalid input.");
					try {
						Thread.sleep(1700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			System.out.print(selectedTeam.scheduleToString());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			promptAfterPlayingWeek();
			break;
			
		case 5:
			
			int selectedWeek2;
			
			while (true) {
				
				System.out.println("\nSelect a week (1-16). Select 0 to cancel.\n");
				
				selectedWeek2 = scanner.nextInt();
	
				//cancels the request
				if (selectedWeek2 == 0) {
					promptAfterPlayingWeek();
				}
				
				//ensures an appropriate week is selected
				if (selectedWeek2 >= 1 && selectedWeek2 <=16) {
					break;
				}
				else {
					System.out.print("\nInvalid input.\n");
					try {
						Thread.sleep(1700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			System.out.print("\n");
			System.out.println(regSeason.toString(selectedWeek2));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			promptAfterPlayingWeek();
			
			break;
			
		case 6:
			
			Standings.printStandings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			promptAfterPlayingWeek();
			break;
			
		case 7:
			
			Stats.printQBRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			promptAfterPlayingWeek();
			break;
			
		case 8:
			
			Stats.printOffensiveRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			promptAfterPlayingWeek();
			break;
			
		case 9:
			
			Stats.printDefensiveRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			promptAfterPlayingWeek();
			break;
			
		default:
			
			System.out.print("Invalid input.\n");
			promptAfterPlayingWeek();
			break;
		}
	}
	
	/**Prompts the user to select an option. Handles
	 * acceptable options for week 16 of the
	 * regular season.**/
	private static void weekSixteenPrompt() {
		
		String prompt = "\nChoose an option:\n\n";
		int option;
		
		prompt += ("1. Advance to Playoffs\n\n");
		
		prompt += ("2. View a team's schedule\n");
		prompt += ("3. View the games for a specified week within the regular season\n\n");
		
		prompt += ("4. View Standings\n\n");
		
		prompt += ("5. View QB Rankings\n");
		prompt += ("6. View Offensive Rankings\n");
		prompt += ("7. View Defensive Rankings\n\n");
		
		System.out.print(prompt);
		
		option = scanner.nextInt();
		
		System.out.print("\n");
		
		switch(option) {
		
		case 1:
			
			playoffs = new Playoffs();	//starts the playoffs
			playoffs.setPlayoffSeedings();
			playoffs.setWildCardGames();
			
			System.out.println(playoffs.playoffBracket());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			wildCardPrompt(); 	//prompt changes for the playoffs
			
			break;
				
		case 2:
			
			Team selectedTeam;
			
			String teams = "AFC West:\n";
			int counter = 1;
			
			for (Team t: Teams.afc_west) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}		
			teams += "\nAFC East:\n";
			for (Team t: Teams.afc_east) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nAFC North:\n";
			for (Team t: Teams.afc_north) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nAFC South:\n";
			for (Team t: Teams.afc_south) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC West:\n";
			for (Team t: Teams.nfc_west) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC East:\n";
			for (Team t: Teams.nfc_east) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC North:\n";
			for (Team t: Teams.nfc_north) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC South:\n";
			for (Team t: Teams.nfc_south) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			
			while (true) {
				System.out.println("Select a team (1-32). Select 0 to cancel.\n");		
				System.out.print("\n"+ teams + "\n\n");
				
				int temp = scanner.nextInt();
				System.out.print("\n");
				
				if (temp == 0) {
					weekSixteenPrompt();
				}
				else if (temp == 1) {
					selectedTeam = Teams.Chiefs;
					break;
				}
				else if (temp == 2) {
					selectedTeam = Teams.Chargers;
					break;
				}
				else if (temp == 3) {
					selectedTeam = Teams.Broncos;
					break;
				}
				else if (temp == 4) {
					selectedTeam = Teams.Raiders;
					break;
				}
				else if (temp == 5) {
					selectedTeam = Teams.Patriots;
					break;
				}
				else if (temp == 6) {
					selectedTeam = Teams.Jets;
					break;
				}
				else if (temp == 7) {
					selectedTeam = Teams.Dolphins;
					break;
				}
				else if (temp == 8) {
					selectedTeam = Teams.Bills;
					break;
				}
				else if (temp == 9) {
					selectedTeam = Teams.Ravens;
					break;
				}
				else if (temp == 10) {
					selectedTeam = Teams.Browns;
					break;
				}
				else if (temp == 11) {
					selectedTeam = Teams.Steelers;
					break;
				}
				else if (temp == 12) {
					selectedTeam = Teams.Bengals;
					break;
				}
				else if (temp == 13) {
					selectedTeam = Teams.Texans;
					break;
				}
				else if (temp == 14) {
					selectedTeam = Teams.Colts;
					break;
				}
				else if (temp == 15) {
					selectedTeam = Teams.Titans;
					break;
				}
				else if (temp == 16) {
					selectedTeam = Teams.Jaguars;
					break;
				}
				else if (temp == 17) {
					selectedTeam = Teams.Niners;
					break;
				}
				else if (temp == 18) {
					selectedTeam = Teams.Seahawks;
					break;
				}
				else if (temp == 19) {
					selectedTeam = Teams.Rams;
					break;
				}
				else if (temp == 20) {
					selectedTeam = Teams.Cardinals;
					break;
				}
				else if (temp == 21) {
					selectedTeam = Teams.Redskins;
					break;
				}
				else if (temp == 22) {
					selectedTeam = Teams.Eagles;
					break;
				}
				else if (temp == 23) {
					selectedTeam = Teams.Giants;
					break;
				}
				else if (temp == 24) {
					selectedTeam = Teams.Cowboys;
					break;
				}
				else if (temp == 25) {
					selectedTeam = Teams.Bears;
					break;
				}
				else if (temp == 26) {
					selectedTeam = Teams.Packers;
					break;
				}
				else if (temp == 27) {
					selectedTeam = Teams.Lions;
					break;
				}
				else if (temp == 28) {
					selectedTeam = Teams.Vikings;
					break;
				}
				else if (temp == 29) {
					selectedTeam = Teams.Saints;
					break;
				}
				else if (temp == 30) {
					selectedTeam = Teams.Panthers;
					break;
				}
				else if (temp == 31) {
					selectedTeam = Teams.Buccaneers;
					break;
				}
				else if (temp == 32) {
					selectedTeam = Teams.Falcons;
					break;
				}
				else {
					System.out.println("\nInvalid input.");
					try {
						Thread.sleep(1700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			System.out.print(selectedTeam.scheduleToString());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			weekSixteenPrompt();;
			break;
			
		case 3:
			
			int selectedWeek2;
			
			while (true) {
				
				System.out.println("\nSelect a week (1-16). Select 0 to cancel.\n");
				
				selectedWeek2 = scanner.nextInt();
	
				//cancels the request
				if (selectedWeek2 == 0) {
					weekSixteenPrompt();
				}
				
				//ensures an appropriate week is selected
				if (selectedWeek2 >= 1 && selectedWeek2 <=16) {
					break;
				}
				else {
					System.out.print("\nInvalid input.\n");
					try {
						Thread.sleep(1700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			System.out.print("\n");
			System.out.println(regSeason.toString(selectedWeek2));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			weekSixteenPrompt();
			
			break;
			
		case 4:
			
			Standings.printStandings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			weekSixteenPrompt();
			break;
			
		case 5:
			
			Stats.printQBRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			weekSixteenPrompt();
			break;
			
		case 6:
			
			Stats.printOffensiveRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			weekSixteenPrompt();
			break;
			
		case 7:
			
			Stats.printDefensiveRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			weekSixteenPrompt();
			break;
			
		default:
			
			System.out.print("Invalid input.\n");
			weekSixteenPrompt();
			break;
		}
		
	}

	private static void wildCardPrompt() {
		
		String prompt = "\nChoose an option:\n\n";
		int option;
		
		prompt += ("1. Play Wild Card Round\n");
		prompt += ("2. Simulate up to Divisional Round Champions\n");
		prompt += ("3. Simulate up to Conference Champions\n");
		prompt += ("4. Simulate up to Super Bowl Champion\n\n");
		
		prompt += ("5. View a team's schedule\n");
		prompt += ("6. View the games for a specified week within the regular season\n\n");
		
		prompt += ("7. View Standings\n\n");
		
		prompt += ("8. View QB Rankings\n");
		prompt += ("9. View Offensive Rankings\n");
		prompt += ("10. View Defensive Rankings\n\n");
		
		System.out.print(prompt);
		
		option = scanner.nextInt();
		
		System.out.print("\n");
		
		switch(option) {
		
		case 1:
			
			playoffs.playWildCardRound();
			playoffs.setDivisionalGames();
			System.out.println(playoffs.playoffBracket());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			divisionalRoundPrompt(); 	//prompt changes for the divisional round
			
			break;
			
		case 2:
			
			playoffs.playWildCardRound();
			playoffs.setDivisionalGames();
			playoffs.playDivisionalRound();
			playoffs.setChampionshipGames();
			
			System.out.println(playoffs.playoffBracket());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			championshipRoundPrompt(); 	//prompt changes for the championship round
			
			break;
			
		case 3:
			
			playoffs.playWildCardRound();
			playoffs.setDivisionalGames();
			playoffs.playDivisionalRound();
			playoffs.setChampionshipGames();
			playoffs.playChampionshipGames();
			playoffs.setSuperBowl();
			
			System.out.println(playoffs.playoffBracket());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			superBowlPrompt(); 	//prompt changes for the sb
			
			break;
			
		case 4:
			
			playoffs.playWildCardRound();
			playoffs.setDivisionalGames();
			playoffs.playDivisionalRound();
			playoffs.setChampionshipGames();
			playoffs.playChampionshipGames();
			playoffs.setSuperBowl();
			playoffs.playSuperBowl();
			
			System.out.println(playoffs.playoffBracket());
			System.out.println("\n");
			playoffs.printSuperBowlCongratulations();
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			endingPrompt(); 	//prompt changes for the end of the season
			
			break;
				
		case 5:
			
			Team selectedTeam;
			
			String teams = "AFC West:\n";
			int counter = 1;
			
			for (Team t: Teams.afc_west) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}		
			teams += "\nAFC East:\n";
			for (Team t: Teams.afc_east) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nAFC North:\n";
			for (Team t: Teams.afc_north) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nAFC South:\n";
			for (Team t: Teams.afc_south) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC West:\n";
			for (Team t: Teams.nfc_west) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC East:\n";
			for (Team t: Teams.nfc_east) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC North:\n";
			for (Team t: Teams.nfc_north) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC South:\n";
			for (Team t: Teams.nfc_south) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			
			while (true) {
				System.out.println("Select a team (1-32). Select 0 to cancel.\n");		
				System.out.print("\n"+ teams + "\n\n");
				
				int temp = scanner.nextInt();
				System.out.print("\n");
				
				if (temp == 0) {
					wildCardPrompt();
				}
				else if (temp == 1) {
					selectedTeam = Teams.Chiefs;
					break;
				}
				else if (temp == 2) {
					selectedTeam = Teams.Chargers;
					break;
				}
				else if (temp == 3) {
					selectedTeam = Teams.Broncos;
					break;
				}
				else if (temp == 4) {
					selectedTeam = Teams.Raiders;
					break;
				}
				else if (temp == 5) {
					selectedTeam = Teams.Patriots;
					break;
				}
				else if (temp == 6) {
					selectedTeam = Teams.Jets;
					break;
				}
				else if (temp == 7) {
					selectedTeam = Teams.Dolphins;
					break;
				}
				else if (temp == 8) {
					selectedTeam = Teams.Bills;
					break;
				}
				else if (temp == 9) {
					selectedTeam = Teams.Ravens;
					break;
				}
				else if (temp == 10) {
					selectedTeam = Teams.Browns;
					break;
				}
				else if (temp == 11) {
					selectedTeam = Teams.Steelers;
					break;
				}
				else if (temp == 12) {
					selectedTeam = Teams.Bengals;
					break;
				}
				else if (temp == 13) {
					selectedTeam = Teams.Texans;
					break;
				}
				else if (temp == 14) {
					selectedTeam = Teams.Colts;
					break;
				}
				else if (temp == 15) {
					selectedTeam = Teams.Titans;
					break;
				}
				else if (temp == 16) {
					selectedTeam = Teams.Jaguars;
					break;
				}
				else if (temp == 17) {
					selectedTeam = Teams.Niners;
					break;
				}
				else if (temp == 18) {
					selectedTeam = Teams.Seahawks;
					break;
				}
				else if (temp == 19) {
					selectedTeam = Teams.Rams;
					break;
				}
				else if (temp == 20) {
					selectedTeam = Teams.Cardinals;
					break;
				}
				else if (temp == 21) {
					selectedTeam = Teams.Redskins;
					break;
				}
				else if (temp == 22) {
					selectedTeam = Teams.Eagles;
					break;
				}
				else if (temp == 23) {
					selectedTeam = Teams.Giants;
					break;
				}
				else if (temp == 24) {
					selectedTeam = Teams.Cowboys;
					break;
				}
				else if (temp == 25) {
					selectedTeam = Teams.Bears;
					break;
				}
				else if (temp == 26) {
					selectedTeam = Teams.Packers;
					break;
				}
				else if (temp == 27) {
					selectedTeam = Teams.Lions;
					break;
				}
				else if (temp == 28) {
					selectedTeam = Teams.Vikings;
					break;
				}
				else if (temp == 29) {
					selectedTeam = Teams.Saints;
					break;
				}
				else if (temp == 30) {
					selectedTeam = Teams.Panthers;
					break;
				}
				else if (temp == 31) {
					selectedTeam = Teams.Buccaneers;
					break;
				}
				else if (temp == 32) {
					selectedTeam = Teams.Falcons;
					break;
				}
				else {
					System.out.println("\nInvalid input.");
					try {
						Thread.sleep(1700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			System.out.print(selectedTeam.scheduleToString());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			wildCardPrompt();
			break;
			
		case 6:
			
			int selectedWeek2;
			
			while (true) {
				
				System.out.println("\nSelect a week (1-16). Select 0 to cancel.\n");
				
				selectedWeek2 = scanner.nextInt();
	
				//cancels the request
				if (selectedWeek2 == 0) {
					wildCardPrompt();
				}
				
				//ensures an appropriate week is selected
				if (selectedWeek2 >= 1 && selectedWeek2 <=16) {
					break;
				}
				else {
					System.out.print("\nInvalid input.\n");
					try {
						Thread.sleep(1700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			System.out.print("\n");
			System.out.println(regSeason.toString(selectedWeek2));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			wildCardPrompt();
			
			break;
			
		case 7:
			
			Standings.printStandings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			wildCardPrompt();
			break;
			
		case 8:
			
			Stats.printQBRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			wildCardPrompt();
			break;
			
		case 9:
			
			Stats.printOffensiveRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			wildCardPrompt();
			break;
			
		case 10:
			
			Stats.printDefensiveRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			wildCardPrompt();
			break;
			
		default:
			
			System.out.print("Invalid input.\n");
			wildCardPrompt();
			break;
		}
		
	}
	
	
	private static void divisionalRoundPrompt() {
		
		String prompt = "\nChoose an option:\n\n";
		int option;
		
		prompt += ("1. Play Divisional Round\n");
		prompt += ("2. Simulate up to Conference Champions\n");
		prompt += ("3. Simulate up to Super Bowl Champion\n\n");
		
		prompt += ("4. View a team's schedule\n");
		prompt += ("5. View the games for a specified week within the regular season\n\n");
		
		prompt += ("6. View Standings\n\n");
		
		prompt += ("7. View QB Rankings\n");
		prompt += ("8. View Offensive Rankings\n");
		prompt += ("9. View Defensive Rankings\n\n");
		
		System.out.print(prompt);
		
		option = scanner.nextInt();
		
		System.out.print("\n");
		
		switch(option) {
		
		case 1:
			
			playoffs.playDivisionalRound();
			playoffs.setChampionshipGames();
			
			System.out.println(playoffs.playoffBracket());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			championshipRoundPrompt(); 	//prompt changes for the chapionship round
			
			break;
			
		case 2:
			
			playoffs.playDivisionalRound();
			playoffs.setChampionshipGames();
			playoffs.playChampionshipGames();
			
			System.out.println(playoffs.playoffBracket());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			superBowlPrompt(); 	//prompt changes for the superbowl
			
			break;
			
		case 3:
			
			playoffs.playDivisionalRound();
			playoffs.setChampionshipGames();
			playoffs.playChampionshipGames();
			playoffs.setSuperBowl();
			playoffs.playSuperBowl();
			
			System.out.println(playoffs.playoffBracket());
			System.out.println("\n");
			playoffs.printSuperBowlCongratulations();
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			endingPrompt();		//end prompt of simulation
			
			break;
				
		case 4:
			
			Team selectedTeam;
			
			String teams = "AFC West:\n";
			int counter = 1;
			
			for (Team t: Teams.afc_west) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}		
			teams += "\nAFC East:\n";
			for (Team t: Teams.afc_east) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nAFC North:\n";
			for (Team t: Teams.afc_north) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nAFC South:\n";
			for (Team t: Teams.afc_south) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC West:\n";
			for (Team t: Teams.nfc_west) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC East:\n";
			for (Team t: Teams.nfc_east) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC North:\n";
			for (Team t: Teams.nfc_north) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC South:\n";
			for (Team t: Teams.nfc_south) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			
			while (true) {
				System.out.println("Select a team (1-32). Select 0 to cancel.\n");		
				System.out.print("\n"+ teams + "\n\n");
				
				int temp = scanner.nextInt();
				System.out.print("\n");
				
				if (temp == 0) {
					divisionalRoundPrompt();
				}
				else if (temp == 1) {
					selectedTeam = Teams.Chiefs;
					break;
				}
				else if (temp == 2) {
					selectedTeam = Teams.Chargers;
					break;
				}
				else if (temp == 3) {
					selectedTeam = Teams.Broncos;
					break;
				}
				else if (temp == 4) {
					selectedTeam = Teams.Raiders;
					break;
				}
				else if (temp == 5) {
					selectedTeam = Teams.Patriots;
					break;
				}
				else if (temp == 6) {
					selectedTeam = Teams.Jets;
					break;
				}
				else if (temp == 7) {
					selectedTeam = Teams.Dolphins;
					break;
				}
				else if (temp == 8) {
					selectedTeam = Teams.Bills;
					break;
				}
				else if (temp == 9) {
					selectedTeam = Teams.Ravens;
					break;
				}
				else if (temp == 10) {
					selectedTeam = Teams.Browns;
					break;
				}
				else if (temp == 11) {
					selectedTeam = Teams.Steelers;
					break;
				}
				else if (temp == 12) {
					selectedTeam = Teams.Bengals;
					break;
				}
				else if (temp == 13) {
					selectedTeam = Teams.Texans;
					break;
				}
				else if (temp == 14) {
					selectedTeam = Teams.Colts;
					break;
				}
				else if (temp == 15) {
					selectedTeam = Teams.Titans;
					break;
				}
				else if (temp == 16) {
					selectedTeam = Teams.Jaguars;
					break;
				}
				else if (temp == 17) {
					selectedTeam = Teams.Niners;
					break;
				}
				else if (temp == 18) {
					selectedTeam = Teams.Seahawks;
					break;
				}
				else if (temp == 19) {
					selectedTeam = Teams.Rams;
					break;
				}
				else if (temp == 20) {
					selectedTeam = Teams.Cardinals;
					break;
				}
				else if (temp == 21) {
					selectedTeam = Teams.Redskins;
					break;
				}
				else if (temp == 22) {
					selectedTeam = Teams.Eagles;
					break;
				}
				else if (temp == 23) {
					selectedTeam = Teams.Giants;
					break;
				}
				else if (temp == 24) {
					selectedTeam = Teams.Cowboys;
					break;
				}
				else if (temp == 25) {
					selectedTeam = Teams.Bears;
					break;
				}
				else if (temp == 26) {
					selectedTeam = Teams.Packers;
					break;
				}
				else if (temp == 27) {
					selectedTeam = Teams.Lions;
					break;
				}
				else if (temp == 28) {
					selectedTeam = Teams.Vikings;
					break;
				}
				else if (temp == 29) {
					selectedTeam = Teams.Saints;
					break;
				}
				else if (temp == 30) {
					selectedTeam = Teams.Panthers;
					break;
				}
				else if (temp == 31) {
					selectedTeam = Teams.Buccaneers;
					break;
				}
				else if (temp == 32) {
					selectedTeam = Teams.Falcons;
					break;
				}
				else {
					System.out.println("\nInvalid input.");
					try {
						Thread.sleep(1700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			System.out.print(selectedTeam.scheduleToString());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			divisionalRoundPrompt();
			break;
			
		case 5:
			
			int selectedWeek2;
			
			while (true) {
				
				System.out.println("\nSelect a week (1-16). Select 0 to cancel.\n");
				
				selectedWeek2 = scanner.nextInt();
	
				//cancels the request
				if (selectedWeek2 == 0) {
					divisionalRoundPrompt();
				}
				
				//ensures an appropriate week is selected
				if (selectedWeek2 >= 1 && selectedWeek2 <=16) {
					break;
				}
				else {
					System.out.print("\nInvalid input.\n");
					try {
						Thread.sleep(1700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			System.out.print("\n");
			System.out.println(regSeason.toString(selectedWeek2));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			divisionalRoundPrompt();
			
			break;
			
		case 6:
			
			Standings.printStandings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			divisionalRoundPrompt();
			break;
			
		case 7:
			
			Stats.printQBRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			divisionalRoundPrompt();
			break;
			
		case 8:
			
			Stats.printOffensiveRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			divisionalRoundPrompt();
			break;
			
		case 9:
			
			Stats.printDefensiveRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			divisionalRoundPrompt();
			break;
			
		default:
			
			System.out.print("Invalid input.\n");
			divisionalRoundPrompt();
			break;
		}
		
	}
	
	
	private static void championshipRoundPrompt() {
		
		String prompt = "\nChoose an option:\n\n";
		int option;
		
		prompt += ("1. Play Conference Champion Games\n");
		prompt += ("2. Simulate up to Super Bowl Champion\n\n");
		
		prompt += ("3. View a team's schedule\n");
		prompt += ("4. View the games for a specified week within the regular season\n\n");
		
		prompt += ("5. View Standings\n\n");
		
		prompt += ("6. View QB Rankings\n");
		prompt += ("7. View Offensive Rankings\n");
		prompt += ("8. View Defensive Rankings\n\n");
		
		System.out.print(prompt);
		
		option = scanner.nextInt();
		
		System.out.print("\n");
		
		switch(option) {
		
		case 1:
			
			playoffs.playChampionshipGames();
			playoffs.setSuperBowl();
			
			System.out.println(playoffs.playoffBracket());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			superBowlPrompt(); 	//prompt changes for the sb
			
			break;
			
		case 2:
			
			playoffs.playChampionshipGames();
			playoffs.setSuperBowl();
			playoffs.playSuperBowl();
			
			System.out.println(playoffs.playoffBracket());
			System.out.println("\n");
			playoffs.printSuperBowlCongratulations();
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			endingPrompt(); 
			
			break;
				
		case 3:
			
			Team selectedTeam;
			
			String teams = "AFC West:\n";
			int counter = 1;
			
			for (Team t: Teams.afc_west) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}		
			teams += "\nAFC East:\n";
			for (Team t: Teams.afc_east) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nAFC North:\n";
			for (Team t: Teams.afc_north) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nAFC South:\n";
			for (Team t: Teams.afc_south) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC West:\n";
			for (Team t: Teams.nfc_west) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC East:\n";
			for (Team t: Teams.nfc_east) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC North:\n";
			for (Team t: Teams.nfc_north) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC South:\n";
			for (Team t: Teams.nfc_south) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			
			while (true) {
				System.out.println("Select a team (1-32). Select 0 to cancel.\n");		
				System.out.print("\n"+ teams + "\n\n");
				
				int temp = scanner.nextInt();
				System.out.print("\n");
				
				if (temp == 0) {
					championshipRoundPrompt();
				}
				else if (temp == 1) {
					selectedTeam = Teams.Chiefs;
					break;
				}
				else if (temp == 2) {
					selectedTeam = Teams.Chargers;
					break;
				}
				else if (temp == 3) {
					selectedTeam = Teams.Broncos;
					break;
				}
				else if (temp == 4) {
					selectedTeam = Teams.Raiders;
					break;
				}
				else if (temp == 5) {
					selectedTeam = Teams.Patriots;
					break;
				}
				else if (temp == 6) {
					selectedTeam = Teams.Jets;
					break;
				}
				else if (temp == 7) {
					selectedTeam = Teams.Dolphins;
					break;
				}
				else if (temp == 8) {
					selectedTeam = Teams.Bills;
					break;
				}
				else if (temp == 9) {
					selectedTeam = Teams.Ravens;
					break;
				}
				else if (temp == 10) {
					selectedTeam = Teams.Browns;
					break;
				}
				else if (temp == 11) {
					selectedTeam = Teams.Steelers;
					break;
				}
				else if (temp == 12) {
					selectedTeam = Teams.Bengals;
					break;
				}
				else if (temp == 13) {
					selectedTeam = Teams.Texans;
					break;
				}
				else if (temp == 14) {
					selectedTeam = Teams.Colts;
					break;
				}
				else if (temp == 15) {
					selectedTeam = Teams.Titans;
					break;
				}
				else if (temp == 16) {
					selectedTeam = Teams.Jaguars;
					break;
				}
				else if (temp == 17) {
					selectedTeam = Teams.Niners;
					break;
				}
				else if (temp == 18) {
					selectedTeam = Teams.Seahawks;
					break;
				}
				else if (temp == 19) {
					selectedTeam = Teams.Rams;
					break;
				}
				else if (temp == 20) {
					selectedTeam = Teams.Cardinals;
					break;
				}
				else if (temp == 21) {
					selectedTeam = Teams.Redskins;
					break;
				}
				else if (temp == 22) {
					selectedTeam = Teams.Eagles;
					break;
				}
				else if (temp == 23) {
					selectedTeam = Teams.Giants;
					break;
				}
				else if (temp == 24) {
					selectedTeam = Teams.Cowboys;
					break;
				}
				else if (temp == 25) {
					selectedTeam = Teams.Bears;
					break;
				}
				else if (temp == 26) {
					selectedTeam = Teams.Packers;
					break;
				}
				else if (temp == 27) {
					selectedTeam = Teams.Lions;
					break;
				}
				else if (temp == 28) {
					selectedTeam = Teams.Vikings;
					break;
				}
				else if (temp == 29) {
					selectedTeam = Teams.Saints;
					break;
				}
				else if (temp == 30) {
					selectedTeam = Teams.Panthers;
					break;
				}
				else if (temp == 31) {
					selectedTeam = Teams.Buccaneers;
					break;
				}
				else if (temp == 32) {
					selectedTeam = Teams.Falcons;
					break;
				}
				else {
					System.out.println("\nInvalid input.");
					try {
						Thread.sleep(1700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			System.out.print(selectedTeam.scheduleToString());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			championshipRoundPrompt();
			break;
			
		case 4:
			
			int selectedWeek2;
			
			while (true) {
				
				System.out.println("\nSelect a week (1-16). Select 0 to cancel.\n");
				
				selectedWeek2 = scanner.nextInt();
	
				//cancels the request
				if (selectedWeek2 == 0) {
					championshipRoundPrompt();
				}
				
				//ensures an appropriate week is selected
				if (selectedWeek2 >= 1 && selectedWeek2 <=16) {
					break;
				}
				else {
					System.out.print("\nInvalid input.\n");
					try {
						Thread.sleep(1700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			System.out.print("\n");
			System.out.println(regSeason.toString(selectedWeek2));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			championshipRoundPrompt();
			
			break;
			
		case 5:
			
			Standings.printStandings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			championshipRoundPrompt();
			break;
			
		case 6:
			
			Stats.printQBRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			championshipRoundPrompt();
			break;
			
		case 7:
			
			Stats.printOffensiveRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			championshipRoundPrompt();
			break;
			
		case 8:
			
			Stats.printDefensiveRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			championshipRoundPrompt();
			break;
			
		default:
			
			System.out.print("Invalid input.\n");
			championshipRoundPrompt();
			break;
		}
		
	}
	
	
	private static void superBowlPrompt() {
		
		String prompt = "\nChoose an option:\n\n";
		int option;
		
		prompt += ("1. MVP Winner Announcement\n");
		prompt += ("2. Play Super Bowl\n\n");
		
		prompt += ("3. View a team's schedule\n");
		prompt += ("4. View the games for a specified week within the regular season\n\n");
		
		prompt += ("5. View Standings\n\n");
		
		prompt += ("6. View QB Rankings\n");
		prompt += ("7. View Offensive Rankings\n");
		prompt += ("8. View Defensive Rankings\n\n");
		
		System.out.print(prompt);
		
		option = scanner.nextInt();
		
		System.out.print("\n");
		
		switch(option) {
		
		case 1:
			
			if (!mvpSet) {
				MVP.setMVP();
				mvpSet = true;
			}
			System.out.println();
			MVP.printMVPMessage();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			superBowlPrompt(); 	//prompt changes for the sb
			
			break;
			
		case 2:
			
			playoffs.playSuperBowl();
			
			System.out.println(playoffs.playoffBracket());
			System.out.println("\n");
			playoffs.printSuperBowlCongratulations();
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			endingPrompt(); 
			
			break;
				
		case 3:
			
			Team selectedTeam;
			
			String teams = "AFC West:\n";
			int counter = 1;
			
			for (Team t: Teams.afc_west) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}		
			teams += "\nAFC East:\n";
			for (Team t: Teams.afc_east) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nAFC North:\n";
			for (Team t: Teams.afc_north) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nAFC South:\n";
			for (Team t: Teams.afc_south) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC West:\n";
			for (Team t: Teams.nfc_west) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC East:\n";
			for (Team t: Teams.nfc_east) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC North:\n";
			for (Team t: Teams.nfc_north) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC South:\n";
			for (Team t: Teams.nfc_south) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			
			while (true) {
				System.out.println("Select a team (1-32). Select 0 to cancel.\n");		
				System.out.print("\n"+ teams + "\n\n");
				
				int temp = scanner.nextInt();
				System.out.print("\n");
				
				if (temp == 0) {
					superBowlPrompt();
				}
				else if (temp == 1) {
					selectedTeam = Teams.Chiefs;
					break;
				}
				else if (temp == 2) {
					selectedTeam = Teams.Chargers;
					break;
				}
				else if (temp == 3) {
					selectedTeam = Teams.Broncos;
					break;
				}
				else if (temp == 4) {
					selectedTeam = Teams.Raiders;
					break;
				}
				else if (temp == 5) {
					selectedTeam = Teams.Patriots;
					break;
				}
				else if (temp == 6) {
					selectedTeam = Teams.Jets;
					break;
				}
				else if (temp == 7) {
					selectedTeam = Teams.Dolphins;
					break;
				}
				else if (temp == 8) {
					selectedTeam = Teams.Bills;
					break;
				}
				else if (temp == 9) {
					selectedTeam = Teams.Ravens;
					break;
				}
				else if (temp == 10) {
					selectedTeam = Teams.Browns;
					break;
				}
				else if (temp == 11) {
					selectedTeam = Teams.Steelers;
					break;
				}
				else if (temp == 12) {
					selectedTeam = Teams.Bengals;
					break;
				}
				else if (temp == 13) {
					selectedTeam = Teams.Texans;
					break;
				}
				else if (temp == 14) {
					selectedTeam = Teams.Colts;
					break;
				}
				else if (temp == 15) {
					selectedTeam = Teams.Titans;
					break;
				}
				else if (temp == 16) {
					selectedTeam = Teams.Jaguars;
					break;
				}
				else if (temp == 17) {
					selectedTeam = Teams.Niners;
					break;
				}
				else if (temp == 18) {
					selectedTeam = Teams.Seahawks;
					break;
				}
				else if (temp == 19) {
					selectedTeam = Teams.Rams;
					break;
				}
				else if (temp == 20) {
					selectedTeam = Teams.Cardinals;
					break;
				}
				else if (temp == 21) {
					selectedTeam = Teams.Redskins;
					break;
				}
				else if (temp == 22) {
					selectedTeam = Teams.Eagles;
					break;
				}
				else if (temp == 23) {
					selectedTeam = Teams.Giants;
					break;
				}
				else if (temp == 24) {
					selectedTeam = Teams.Cowboys;
					break;
				}
				else if (temp == 25) {
					selectedTeam = Teams.Bears;
					break;
				}
				else if (temp == 26) {
					selectedTeam = Teams.Packers;
					break;
				}
				else if (temp == 27) {
					selectedTeam = Teams.Lions;
					break;
				}
				else if (temp == 28) {
					selectedTeam = Teams.Vikings;
					break;
				}
				else if (temp == 29) {
					selectedTeam = Teams.Saints;
					break;
				}
				else if (temp == 30) {
					selectedTeam = Teams.Panthers;
					break;
				}
				else if (temp == 31) {
					selectedTeam = Teams.Buccaneers;
					break;
				}
				else if (temp == 32) {
					selectedTeam = Teams.Falcons;
					break;
				}
				else {
					System.out.println("\nInvalid input.");
					try {
						Thread.sleep(1700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			System.out.print(selectedTeam.scheduleToString());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			superBowlPrompt();
			break;
			
		case 4:
			
			int selectedWeek2;
			
			while (true) {
				
				System.out.println("\nSelect a week (1-16). Select 0 to cancel.\n");
				
				selectedWeek2 = scanner.nextInt();
	
				//cancels the request
				if (selectedWeek2 == 0) {
					superBowlPrompt();
				}
				
				//ensures an appropriate week is selected
				if (selectedWeek2 >= 1 && selectedWeek2 <=16) {
					break;
				}
				else {
					System.out.print("\nInvalid input.\n");
					try {
						Thread.sleep(1700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			System.out.print("\n");
			System.out.println(regSeason.toString(selectedWeek2));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			superBowlPrompt();
			
			break;
			
		case 5:
			
			Standings.printStandings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			superBowlPrompt();
			break;
			
		case 6:
			
			Stats.printQBRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			superBowlPrompt();
			break;
			
		case 7:
			
			Stats.printOffensiveRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			superBowlPrompt();
			break;
			
		case 8:
			
			Stats.printDefensiveRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			superBowlPrompt();
			break;
			
		default:
			
			System.out.print("Invalid input.\n");
			superBowlPrompt();
			break;
		}
		
	}
	
	private static void endingPrompt() {
		
		String prompt = "\nChoose an option:\n\n";
		int option;
		
		prompt += ("1. End Simulation\n");
		prompt += ("2. MVP Winner Announcement\n\n");
		
		prompt += ("3. View a team's schedule\n");
		prompt += ("4. View the games for a specified week within the regular season\n\n");
		
		prompt += ("5. View Standings\n\n");
		
		prompt += ("6. View QB Rankings\n");
		prompt += ("7. View Offensive Rankings\n");
		prompt += ("8. View Defensive Rankings\n\n");
		
		System.out.print(prompt);
		
		option = scanner.nextInt();
		
		System.out.print("\n");
		
		switch(option) {
		
		case 1:
			
			System.out.println("\nThank you for using the NFL Simulator!");
			System.exit(0);
			
			break;
		
		case 2:
			
			if (!mvpSet) {
				MVP.setMVP();
				mvpSet = true;
			}
			System.out.println();
			MVP.printMVPMessage();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			endingPrompt(); 
			
			break;
				
		case 3:
			
			Team selectedTeam;
			
			String teams = "AFC West:\n";
			int counter = 1;
			
			for (Team t: Teams.afc_west) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}		
			teams += "\nAFC East:\n";
			for (Team t: Teams.afc_east) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nAFC North:\n";
			for (Team t: Teams.afc_north) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nAFC South:\n";
			for (Team t: Teams.afc_south) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC West:\n";
			for (Team t: Teams.nfc_west) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC East:\n";
			for (Team t: Teams.nfc_east) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC North:\n";
			for (Team t: Teams.nfc_north) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			teams += "\nNFC South:\n";
			for (Team t: Teams.nfc_south) {
				teams += counter + ". " + t.toString() + "\n";
				counter++;
			}
			
			while (true) {
				System.out.println("Select a team (1-32). Select 0 to cancel.\n");		
				System.out.print("\n"+ teams + "\n\n");
				
				int temp = scanner.nextInt();
				System.out.print("\n");
				
				if (temp == 0) {
					endingPrompt();
				}
				else if (temp == 1) {
					selectedTeam = Teams.Chiefs;
					break;
				}
				else if (temp == 2) {
					selectedTeam = Teams.Chargers;
					break;
				}
				else if (temp == 3) {
					selectedTeam = Teams.Broncos;
					break;
				}
				else if (temp == 4) {
					selectedTeam = Teams.Raiders;
					break;
				}
				else if (temp == 5) {
					selectedTeam = Teams.Patriots;
					break;
				}
				else if (temp == 6) {
					selectedTeam = Teams.Jets;
					break;
				}
				else if (temp == 7) {
					selectedTeam = Teams.Dolphins;
					break;
				}
				else if (temp == 8) {
					selectedTeam = Teams.Bills;
					break;
				}
				else if (temp == 9) {
					selectedTeam = Teams.Ravens;
					break;
				}
				else if (temp == 10) {
					selectedTeam = Teams.Browns;
					break;
				}
				else if (temp == 11) {
					selectedTeam = Teams.Steelers;
					break;
				}
				else if (temp == 12) {
					selectedTeam = Teams.Bengals;
					break;
				}
				else if (temp == 13) {
					selectedTeam = Teams.Texans;
					break;
				}
				else if (temp == 14) {
					selectedTeam = Teams.Colts;
					break;
				}
				else if (temp == 15) {
					selectedTeam = Teams.Titans;
					break;
				}
				else if (temp == 16) {
					selectedTeam = Teams.Jaguars;
					break;
				}
				else if (temp == 17) {
					selectedTeam = Teams.Niners;
					break;
				}
				else if (temp == 18) {
					selectedTeam = Teams.Seahawks;
					break;
				}
				else if (temp == 19) {
					selectedTeam = Teams.Rams;
					break;
				}
				else if (temp == 20) {
					selectedTeam = Teams.Cardinals;
					break;
				}
				else if (temp == 21) {
					selectedTeam = Teams.Redskins;
					break;
				}
				else if (temp == 22) {
					selectedTeam = Teams.Eagles;
					break;
				}
				else if (temp == 23) {
					selectedTeam = Teams.Giants;
					break;
				}
				else if (temp == 24) {
					selectedTeam = Teams.Cowboys;
					break;
				}
				else if (temp == 25) {
					selectedTeam = Teams.Bears;
					break;
				}
				else if (temp == 26) {
					selectedTeam = Teams.Packers;
					break;
				}
				else if (temp == 27) {
					selectedTeam = Teams.Lions;
					break;
				}
				else if (temp == 28) {
					selectedTeam = Teams.Vikings;
					break;
				}
				else if (temp == 29) {
					selectedTeam = Teams.Saints;
					break;
				}
				else if (temp == 30) {
					selectedTeam = Teams.Panthers;
					break;
				}
				else if (temp == 31) {
					selectedTeam = Teams.Buccaneers;
					break;
				}
				else if (temp == 32) {
					selectedTeam = Teams.Falcons;
					break;
				}
				else {
					System.out.println("\nInvalid input.");
					try {
						Thread.sleep(1700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			System.out.print(selectedTeam.scheduleToString());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			endingPrompt();
			break;
			
		case 4:
			
			int selectedWeek2;
			
			while (true) {
				
				System.out.println("\nSelect a week (1-16). Select 0 to cancel.\n");
				
				selectedWeek2 = scanner.nextInt();
	
				//cancels the request
				if (selectedWeek2 == 0) {
					endingPrompt();
				}
				
				//ensures an appropriate week is selected
				if (selectedWeek2 >= 1 && selectedWeek2 <=16) {
					break;
				}
				else {
					System.out.print("\nInvalid input.\n");
					try {
						Thread.sleep(1700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			System.out.print("\n");
			System.out.println(regSeason.toString(selectedWeek2));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			endingPrompt();
			
			break;
			
		case 5:
			
			Standings.printStandings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			endingPrompt();
			break;
			
		case 6:
			
			Stats.printQBRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			endingPrompt();
			break;
			
		case 7:
			
			Stats.printOffensiveRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			endingPrompt();
			break;
			
		case 8:
			
			Stats.printDefensiveRankings();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			endingPrompt();
			break;
			
		default:
			
			System.out.print("Invalid input.\n");
			endingPrompt();
			break;
		}
		
	}
		
}
