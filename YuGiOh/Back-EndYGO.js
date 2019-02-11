"use strict";

/**Duel Monsters card game based off of Television show Yu-Gi-Oh!
Two duelers battle one another with a deck of Monster cards, each
having a set amount of attack and defense points. Duelers start
out with 4000 life points. First player to reach 0 life points
loses.
**/

/*Defines a player of the game*/
function Player() {

    this.lifePoints = 4000;
    this.hand = new Hand();
    this.deck;
    this.field = new Field();

    /*subtracts life points from player*/
    this.subtractLP = function(amount) {
        this.lifePoints -= amount;
    }

    /*draws a card from the deck;
    removes card from deck
    adds card to player hand*/
    this.draw = function() {
        this.hand.add(this.deck.remove());
    }

    /*Player chooses their deck*/
    this.chooseDeck = function(option) {

        switch(option) {
            case 0:
                this.deck = yugiDeck.shuffle();
                break;
            case 1:
                this.deck = kaibaDeck.shuffle();
                break;
            case 2:
                this.deck = pegasusDeck.shuffle();
                break;
            case 3:
                this.deck = randomDeck().shuffle();
                break;
            case 4:
                this.deck = randomMonsters().shuffle();
                break;
        }

    }

}



/*Defines a dual monster*/
function DualMonster(name, atk, def, stars) {

    this.canAttack;

    this.name = name;

    this.atk = atk;
    this.def = def;
    this.stars = stars;

}



/*The deck in which the player is playing from*/
function Deck() {

    this.monsters = [];
    this.isEmpty = true;

    /*adds monster to deck*/
    this.add = function(monster) {
        this.monsters.push(monster);

        if (this.isEmpty = true) {
            this.isEmpty = false;   /*deck is no longer empty*/
        }

    }

    /*removes monster from deck*/
    this.remove = function() {
        return this.monsters.pop();

        /*deck is empty if it has no monsters*/
        if (this.monsters.length == 0) {
            this.isEmpty = true;
        }
    }

    /*returns all the monsters in the deck*/
    this.getMonsters = function() {
        return this.monsters;
    }

    /*concantenates this deck with another deck*/
    this.concatDeck = function(deck) {
        this.monsters = (this.monsters).concat(deck.getMonsters())
    }

    /*shuffles the deck*/
    this.shuffle = function() {

        let deckCopy = [];
        let shuffledDeck = new Deck();
        let size = this.monsters.length;
        let x;

        /*creates copy of the deck*/
        for (let i = 0; i < size; i++) {
            deckCopy.push(this.monsters[i]);
        }

        let i = 0;

        while (i < size) {

            x = Math.floor(Math.random() * deckCopy.length);

            /*puts random monster in position in deck*/
            shuffledDeck.add(deckCopy[x]);

            delete deckCopy[x]; /*prevents monster from being chosen again*/

            /*shifts elements*/
            for (let j = x; j < (deckCopy.length - 1); j++) {
                deckCopy[j] = deckCopy[j+1];
            }

            deckCopy.pop();
            i++;

        }

        return shuffledDeck;

    }

}



/*The cards present in a player's hand*/
function Hand() {

    this.monsters = [];

    /*adds monster to hand*/
    this.add = function(monster) {
        this.monsters.push(monster);
    }

    /*removes monster from player's hand*/
    this.remove = function(monster) {

        /*creates copy of monster*/
        let temp = new DualMonster(monster.name, monster.atk,
                                  monster.def, monster.stars);

        for (let i = 0; i < this.monsters.length; i++) {

            /*removes monster*/
            if (this.monsters[i] == monster) {
                delete this.monsters[i];

                /*shifts monsters in hand*/
                for (let j = i; j < (this.monsters.length - 1); j++) {
                    this.monsters[j] = this.monsters[j+1];
                }

                /*removes extra monster from end of array*/
                this.monsters.pop();
                break;

            }

        }

        return temp;

    }

    /*returns all the monsters in the player's hand*/
    this.getMonsters = function() {
        return this.monsters;
    }

}



/*The cards present on the player's field*/
function Field() {

    this.monsters = [];

    /*adds monster to the field*/
    this.add = function(monster) {
        return this.monsters.push(monster);
    }

    /*removes a monster from the field*/
    this.sendToGraveyard = function(monster) {

        for (let i = 0; i < this.monsters.length; i++) {

            /*deletes monster*/
            if (this.monsters[i] == monster) {
                delete this.monsters[i];

                /*shifts monsters*/
                for (let j = i; j < (this.monsters.length - 1); j++) {
                    this.monsters[j] = this.monsters[j+1];
                }

                /*pops off extra monster at end of array*/
                this.monsters.pop();
                break;

            }

        }

    }

    /*returns all the monsters on the field*/
    this.getMonsters = function() {
        return this.monsters;
    }

}



/*****************************************************************************

DUAL MONSTERS

******************************************************************************/

/*Yugi Muto's Deck*/

const mysticalElf = new DualMonster("Mystical Elf", 800, 2000, 4);
const feralImp = new DualMonster("Feral Imp", 1300, 1400, 4);
const wingedDragon = new DualMonster("Winged Dragon", 1400, 1200, 4);
const summonedSkull = new DualMonster("Summoned Skull", 2500, 1200, 6);
const beaverWarrior = new DualMonster("Beaver Warrior", 1200, 1500, 4);
const darkMagician = new DualMonster("Dark Magician", 2500, 2100, 7);
const gaiaFierce = new DualMonster("Gaia the Fierce Knight", 2300, 2100, 7);
const curseOfDragon = new DualMonster("Curse of Dragon", 2000, 1500, 5);
const celticGuardian = new DualMonster("Celtic Guardian", 1400, 1200, 4);
const mammothGraveyard = new DualMonster("Mammoth Graveyard", 1200, 800, 3);
const greatWhite = new DualMonster("Great White", 1600, 800, 4);
const silverFang = new DualMonster("Silver Fang", 1200, 800, 3);
const giantSoldier = new DualMonster("Giant Soldier of Stone", 1300, 2000, 3);
const dragonZombie = new DualMonster("Dragon Zombie", 1600, 0, 3);
const domaAngel = new DualMonster("Doma the Angel of Silence", 1600, 1400, 5);
const ansatsu = new DualMonster("Ansatsu", 1700, 1200, 5);
const wittyPhantom = new DualMonster("Witty Phantom", 1400, 1300, 4);
const clawReacher = new DualMonster("Claw Reacher", 1000, 800, 3);
const mysticClown = new DualMonster("Mystic Clown", 1500, 1000, 4);
const ancientElf = new DualMonster("Ancient Elf", 1450, 1200, 4);
const magicalGhost = new DualMonster("Magical Ghost", 1300, 1400, 4);
const sternMystic = new DualMonster("The Stern Mystic", 1500, 1200, 4);
const wallIllusion = new DualMonster("Wall of Illusion", 1000, 1850, 4);
const neo = new DualMonster("Neo the Magic Swordsman", 1700, 1000, 4);
const baron = new DualMonster("Baron of the Fiend Sword", 1550, 800, 4);
const treasure = new DualMonster("Man-Eating Treasure Chest", 1600, 1000, 4);
const sorcererDoomed = new DualMonster("Sorcerer of the Doomed", 1450, 1200, 4);
const trapMaster = new DualMonster("Trap Master", 500, 1100, 3);
const manEaterBug = new DualMonster("Man-Eater Bug", 450, 600, 2);

const yugiDeck = new Deck();
yugiDeck.add(mysticalElf);
yugiDeck.add(feralImp);
yugiDeck.add(wingedDragon);
yugiDeck.add(summonedSkull);
yugiDeck.add(beaverWarrior);
yugiDeck.add(darkMagician);
yugiDeck.add(gaiaFierce);
yugiDeck.add(curseOfDragon);
yugiDeck.add(celticGuardian);
yugiDeck.add(mammothGraveyard);
yugiDeck.add(greatWhite);
yugiDeck.add(silverFang);
yugiDeck.add(giantSoldier);
yugiDeck.add(dragonZombie);
yugiDeck.add(domaAngel);
yugiDeck.add(ansatsu);
yugiDeck.add(wittyPhantom);
yugiDeck.add(clawReacher);
yugiDeck.add(mysticClown);
yugiDeck.add(ancientElf);
yugiDeck.add(magicalGhost);
yugiDeck.add(sternMystic);
yugiDeck.add(wallIllusion);
yugiDeck.add(neo);
yugiDeck.add(baron);
yugiDeck.add(treasure);
yugiDeck.add(sorcererDoomed);
yugiDeck.add(trapMaster);
yugiDeck.add(manEaterBug);



/*Seto Kaiba's Deck*/

const aAssault = new DualMonster("A-Assault Core", 1900, 200, 4);
const bBuster = new DualMonster("B-Buster Drake", 1500, 1800, 4);
const cCrush = new DualMonster("C-Crush Wyvern", 1200, 2000, 4);
const heavyMechA = new DualMonster("Heavy Mech Support Armor", 500, 500, 3);
const xHead = new DualMonster("X-Head Cannon", 1800, 1500, 4);
const yDragon = new DualMonster("Y-Dragon Head", 1500, 1600, 4);
const zMetal = new DualMonster("Z-Metal Tank", 1500, 1300, 3);
const heavyMechP = new DualMonster("Heavy Mech Support Platform", 500, 500, 3);
const blueEyes = new DualMonster("Blue-Eyes White Dragon", 3000, 2500, 8);
const kaiserGlider = new DualMonster("Kaiser Glider", 2400, 2200, 6);
const lordD = new DualMonster("Lord of D.", 1200, 1100, 4);
const vampLord = new DualMonster("Vampire Lord", 2000, 1500, 5);
const battleOx = new DualMonster("Enraged Battle Ox", 1700, 1000, 4);
const desFeralImp = new DualMonster("Des Feral Imp", 1600, 1800, 4);
const darkClown = new DualMonster("Peten the Dark Clown", 500, 1200, 3);
const ipp = new DualMonster("Interplanetarypurplythorny Dragon", 2200, 1100, 5);
const blizzardDragon = new DualMonster("Blizzard Dragon", 1800, 1000, 4);
const keeperShrine = new DualMonster("Keeper of the Shrine", 0, 2100, 4);
const lusterDragon = new DualMonster("Luster Dragon", 1900, 1600, 4);
const aToZ = new DualMonster("A-to-Z-Dragon Buster Cannon", 4000, 4000, 10);
const abcDragon = new DualMonster("ABC-Dragon Buster", 3000, 2800, 8);
const xyzDragon = new DualMonster("XYZ-Dragon Cannon", 2800, 2600, 8);
const xyDragon = new DualMonster("XY-Dragon Cannon", 2200, 1900, 6);
const xzTank = new DualMonster("XZ-Tank Cannon", 2400, 2100, 6);

const kaibaDeck = new Deck();
kaibaDeck.add(aAssault);
kaibaDeck.add(bBuster);
kaibaDeck.add(cCrush);
kaibaDeck.add(heavyMechA);
kaibaDeck.add(xHead);
kaibaDeck.add(yDragon);
kaibaDeck.add(zMetal);
kaibaDeck.add(heavyMechP);
kaibaDeck.add(blueEyes);
kaibaDeck.add(kaiserGlider);
kaibaDeck.add(vampLord);
kaibaDeck.add(lordD);
kaibaDeck.add(battleOx);
kaibaDeck.add(desFeralImp);
kaibaDeck.add(darkClown);
kaibaDeck.add(ipp);
kaibaDeck.add(blizzardDragon);
kaibaDeck.add(keeperShrine);
kaibaDeck.add(lusterDragon);
kaibaDeck.add(abcDragon);
kaibaDeck.add(xyzDragon);
kaibaDeck.add(aToZ);
kaibaDeck.add(xyDragon);
kaibaDeck.add(xzTank);



/*Maximillion Pegasus's Deck*/

const relinquished = new DualMonster("Relinquished", 0, 0, 1);
const redArchery = new DualMonster("Red Archery Girl", 1400, 1500, 4);
const ryuRan = new DualMonster("Ryu-Ran", 2200, 2600, 7);
const illusion = new DualMonster("Illusionist Faceless Mage", 1200, 2200, 5);
const rogueDoll = new DualMonster("Rogue Doll", 1600, 1000, 4);
const uraby = new DualMonster("Uraby", 1500, 800, 4);
const aquaMadoor = new DualMonster("Aqua Madoor", 1200, 2000, 4);
const toonAlligator = new DualMonster("Toon Alligator", 800, 1600, 4);
const haneHane = new DualMonster("Hane-Hane", 450, 500, 2);
const sonicBird = new DualMonster("Sonic Bird", 1400, 1000, 4);
const jigenBakudan = new DualMonster("Jigen Bakudan", 200, 1000, 2);
const maskDarkness = new DualMonster("Mask of Darkness", 900, 400, 2);
const witchForest = new DualMonster("Witch of the Black Forest", 1100, 1200, 4);
const mukaMuka = new DualMonster("Muka Muka", 600, 300, 2);
const dreamClown = new DualMonster("Dream Clown", 1200, 900, 3);
const armedNinja = new DualMonster("Armed Ninja", 300, 300, 1);
const hiroShadow = new DualMonster("Hiro's Shadow Scout", 650, 500, 2);
const blueEyesToon = new DualMonster("Blue-Eyes Toon Dragon", 3000, 2500, 8);
const toonSkull = new DualMonster("Toon Summoned Skull", 2500, 1200, 6);
const mangaRyu = new DualMonster("Manga Ryu-Ran", 2200, 2600, 7);
const toonMermaid = new DualMonster("Toon Mermaid", 1400, 1500, 4);

const pegasusDeck = new Deck();
pegasusDeck.add(giantSoldier);
pegasusDeck.add(manEaterBug);
pegasusDeck.add(relinquished);
pegasusDeck.add(redArchery);
pegasusDeck.add(ryuRan);
pegasusDeck.add(illusion);
pegasusDeck.add(rogueDoll);
pegasusDeck.add(uraby);
pegasusDeck.add(aquaMadoor);
pegasusDeck.add(toonAlligator);
pegasusDeck.add(haneHane);
pegasusDeck.add(sonicBird);
pegasusDeck.add(jigenBakudan);
pegasusDeck.add(maskDarkness);
pegasusDeck.add(witchForest);
pegasusDeck.add(mukaMuka);
pegasusDeck.add(dreamClown);
pegasusDeck.add(armedNinja);
pegasusDeck.add(hiroShadow);
pegasusDeck.add(blueEyesToon);
pegasusDeck.add(toonSkull);
pegasusDeck.add(mangaRyu);
pegasusDeck.add(toonMermaid);


/*Deck full of all monsters from the 3 duelers*/
const allMonsters = new Deck();
allMonsters.concatDeck(yugiDeck);
allMonsters.concatDeck(kaibaDeck);
allMonsters.concatDeck(pegasusDeck);



/*****************************************************************************

Choosing a Deck

******************************************************************************/

/*Picks a random deck between the three duelers*/
function randomDeck() {

    /*generates a random integer from 0 to 2*/
    let rand = Math.floor(Math.random() * 3);

    /*returns deck based off of random number generated*/
    switch(rand) {
        case 0:
            return yugiDeck;
        case 1:
            return kaibaDeck;
        case 2:
            return pegasusDeck;
    }

}

/*Chooses 25 random monsters to populate deck*/
function randomMonsters() {

    let randDeck = new Deck();
    let monsterCopy = [];
    let x;

    /*creates copy of array full of all monsters*/
    for (let i = 0; i < allMonsters.getMonsters().length; i++) {
        monsterCopy.push(allMonsters.getMonsters()[i]);
    }

    /*loop will stop once 25 cards are in deck*/
    while ((randDeck.getMonsters()).length < 25) {

        x = Math.floor(Math.random() * (monsterCopy.length));

        /*adds monster to deck*/
        randDeck.add(monsterCopy[x]);

        delete monsterCopy[x]; /*keeps monster from being chosen again*/

        /*shifts elements to the left to cover undefined spot*/
        for (let i = x; i < (monsterCopy.length - 1); i++) {

            monsterCopy[i] = monsterCopy[i+1];

        }

        monsterCopy.pop(); /*decreases array size*/

    }

    return randDeck;

}



/*****************************************************************************

GAME FUNCTIONS

******************************************************************************/

/*handles event of monster attacking another*/
function attack(p1, p2, attacker, target) {

    let aatk = attacker.atk;
    let tatk = target.atk;
    let tdef = target.def;

    /*checks if target is in attack mode*/
    if (target.canAttack) {

        if (aatk > tatk) {
            p2.subtractLP(aatk - tatk);         /*subtracts lp from p2*/
            p2.field.sendToGraveyard(target);   /*sends card to graveyard*/
        }

        else if (aatk < tatk) {
            p1.subtractLP(tatk - aatk);
            p1.field.sendToGraveyard(attacker);
        }

        else {
            p1.field.sendToGraveyard(attacker);
            p2.field.sendToGraveyard(target);
        }

    }

    /*target in defense mode*/
    else {

        if (aatk > tdef) {
            p2.field.sendToGraveyard(target);   /*sends card to graveyard*/
        }

        else if (aatk < tdef) {
            p1.subtractLP(tdef - aatk);
            p1.field.sendToGraveyard(attacker);
        }

        else {
            p1.field.sendToGraveyard(attacker);
            p2.field.sendToGraveyard(target);
        }

    }

}

/*alters card count for specified player*/
function reduceCardCount(dueler, cards) {

    switch (dueler) {

        case "player":
            let duelerCards = document.getElementById("duelerCards");
            duelerCards.innerHTML = "Cards Left: " + cards;
            break;

        case "cpu":
            let cpuCards = document.getElementById("cpuCards");
            cpuCards.innerHTML = "Cards Left: " + cards;
            break;

    }

}


/*draws a card for a player's turn*/
function playerTurnDraw(pos) {

        let playerHand = document.getElementById("playerHand");

        /*space for monster*/
        let monsterSpace = document.createElement("DIV");
        monsterSpace.style.float = "left";

        let monster = document.createElement("IMG");
        monster.setAttribute("src",
            ("images/" + player.hand.getMonsters()[pos].name + ".jpg"));
        monster.setAttribute("height", "150");
        monster.setAttribute("width", "120");

        let monsterATK = document.createElement("P");
        monsterATK.style.fontSize = "12px";
        let monsterDEF = document.createElement("P");
        monsterDEF.style.fontSize = "12px";

        monsterATK.innerHTML = "ATK: " + player.hand.getMonsters()[pos].atk;
        monsterDEF.innerHTML = "DEF: " + player.hand.getMonsters()[pos].def;

        playerHand.appendChild(monsterSpace);
        monsterSpace.appendChild(monster);
        monsterSpace.appendChild(monsterATK);
        monsterSpace.appendChild(monsterDEF);

}

/*adds a monster to the player's field;
removing it from the player's hand*/
function playMonster (dueler, monster, mode) {


    let x = this.field.add(this.hand.remove(monster));

    /*Sets monster in either attack or defense mode*/
    switch(mode) {
        case 0:
            x.canAttack = true;
            break;
        case 1:
            x.canAttack = false;
            break;
    }
}


/*chooses monster to play*/
function chooseMonster(dueler) {

    switch (dueler) {
      case 0:

        let possibleMoves = document.getElementById("possibleMoves");

        /*empties select list*/
        for (let i = 0; i < possibleMoves.length; i++) {
            possibleMoves.remove(i);
        }



        break;

      case 1:
        break;

    }

}


/*executes option of turn*/
function executeOption(dueler, selection) {

    switch(dueler) {

        case 0:

            switch (selection) {
              case "Play Monster":
                  let m = chooseMonster(dueler);

                break;
              case "End Turn":
                break;
            }

    }

}

/*****************************************************************************

GAME IMPLEMENTATION

******************************************************************************/

let player = new Player();
let cpuPlayer = new Player();

/*player first turn*/
function firstTurn() {

    document.getElementById("top").innerHTML = "Your Turn."

    /*draws for beginning of turn*/
    player.draw();

    /*alters cards left*/
    reduceCardCount("player", player.deck.getMonsters().length);

    /*places card in player's hand*/
    playerTurnDraw(player.hand.getMonsters().length - 1);

    /*makes form of possible moves*/
    let moves = document.createElement("DIV");
    moves.setAttribute("id", "moves");
    moves.style.float = "left";
    moves.style.padding = "0px 0px 0px 30px";

    let possibleMoves = document.createElement("SELECT");
    possibleMoves.setAttribute("id", "possibleMoves");
    possibleMoves.setAttribute("size", "2");
    possibleMoves.style.float = "left";

    let endTurn = document.createElement("option");
    endTurn.setAttribute("value", "End Turn");
    endTurn.text = "End Turn";

    let playM = document.createElement("option");
    playM.setAttribute("value", "Play Monster");
    playM.text = "Play Monster";

    let cont = document.createElement("Button");
    cont.setAttribute("id", "continue");
    cont.setAttribute("type", "button");
    cont.style.float = "right";
    cont.style.fontWeight = "bold";

    let text = document.createTextNode("continue");

    cont.appendChild(text);

    document.getElementById("stats").appendChild(moves);
    moves.appendChild(possibleMoves);
    possibleMoves.add(playM);
    possibleMoves.add(endTurn);
    moves.appendChild(cont);

    /*gets player move decision*/
    switch (document.getElementById("possibleMoves").value) {

        case "Play Monster":
        /*    cont.setAttribute("onclick", "window.alert(42);"); */
            break;
        case "End Turn":
        /*    cont.setAttribute("onclick", "window.alert(1)"); */
            break;
        default:
          /*  cont.setAttribute("onclick", "window.alert(3)");*/
            break;

    }

    /*cont.setAttribute("onclick", "executeOption(0, selection)");*/

}

/*inits opponent field*/
function opponentField() {


    let cpu = document.getElementById("cpu");
    cpu.innerHTML = "";
    let cpuFieldTop = document.createElement("P");
    cpuFieldTop.setAttribute("align", "center");
    cpuFieldTop.style.fontWeight = "bold";
    cpuFieldTop.innerHTML = "Opponent's Field";

    cpu.appendChild(cpuFieldTop); /*alters text at top of page*/

    /*sets up cpu field space*/
    let cpuField = document.createElement("DIV");
    cpuField.setAttribute("id", "cpuField");
    cpuField.style.height = "47%";

    let cpuHand = document.createElement("DIV");
    cpuHand.style.height = "47%";
    cpuHand.setAttribute("align", "center");

    let cpuDisk = document.createElement("IMG");
    cpuDisk.setAttribute("src", "images/Duel Disk.jpg");
    cpuDisk.setAttribute("height", "150px");
    cpuDisk.setAttribute("width", "275px");

    cpu.appendChild(cpuField);
    cpu.appendChild(cpuHand);
    cpuHand.appendChild(cpuDisk);

    setTimeout(firstTurn, 3000);

}

/*player draws from their deck*/
function playersDraw() {

    let playerCards = document.getElementById("duelerCards");
    let cpuCards = document.getElementById("cpuCards");
    let cpu = document.getElementById("cpu");
    let dueler = document.getElementById("dueler");
    let playerField = document.createElement("DIV");
    let playerHand = document.createElement("DIV");
    let playerTopField = document.createElement("P");
    let playerTopHand = document.createElement("P");
    let top = document.getElementById("top");

    /*player draws 5 cards*/
    player.draw();
    player.draw();
    player.draw();

    /*cpu draws 5 cards*/
    cpuPlayer.draw();
    cpuPlayer.draw();
    cpuPlayer.draw();

    playerCards.innerHTML = "Cards Left: " + player.deck.getMonsters().length;
    cpuCards.innerHTML = "Cards Left: " + cpuPlayer.deck.getMonsters().length;
    cpu.innerHTML = "Opponent has drawn 3 cards.";

    /*creates the space for the player's hand and fieled*/
    playerField.setAttribute("id", "playerField");
    playerField.style.height = "47%";

    playerTopField.setAttribute("align", "center");
    playerTopField.style.fontWeight = "bold";
    playerTopField.innerHTML = "FIELD";
    playerField.appendChild(playerTopField);

    playerHand.setAttribute("id", "playerHand");
    playerHand.style.height = "47%";
    playerHand.style.float = "bottom";

    playerTopHand.setAttribute("align", "center");
    playerTopHand.style.fontWeight = "bold";
    playerTopHand.innerHTML = "HAND";
    playerHand.appendChild(playerTopHand);

    dueler.appendChild(playerField);
    dueler.appendChild(playerHand);

    /*place the cards in player's hand*/
    for (let i = 0; i < 3; i++) {

        /*space for monster*/
        let monsterSpace = document.createElement("DIV");
        monsterSpace.style.float = "left";

        /*actual monster image*/
        let monster = document.createElement("IMG");
        monster.setAttribute("src",
            ("images/" + player.hand.getMonsters()[i].name + ".jpg"));
        monster.setAttribute("height", "150");
        monster.setAttribute("width", "120");

        let monsterATK = document.createElement("P");
        monsterATK.style.fontSize = "12px";
        let monsterDEF = document.createElement("P");
        monsterDEF.style.fontSize = "12px";

        monsterATK.innerHTML = "ATK: " + player.hand.getMonsters()[i].atk;
        monsterDEF.innerHTML = "DEF: " + player.hand.getMonsters()[i].def;

        playerHand.appendChild(monsterSpace);
        monsterSpace.appendChild(monster);
        monsterSpace.appendChild(monsterATK);
        monsterSpace.appendChild(monsterDEF);
    }

    top.innerHTML = "";

    setTimeout(opponentField, 3000);

}

/*prompts players to draw from their deck*/
function promptDraw() {

    let top = document.getElementById("top");
    let cpu = document.getElementById("cpu");
    cpu.innerHTML = "";
    top.innerHTML = "READY! PLAYERS DRAW!";

    setTimeout(playersDraw, 3000);

}

/*initializes cpu deck*/
function initCpuDeck() {

    /*random choice for cpu deck*/
    let x = Math.floor(Math.random() * 5);

    let cpu = document.getElementById("cpu");
    let dueler = document.getElementById("dueler");
    let cpuPoints = document.getElementById("cpuPoints");
    let cpuCards = document.getElementById("cpuCards");

    cpuPlayer.chooseDeck(x);

    cpuPoints.innerHTML = "Life Points: " + cpuPlayer.lifePoints;
    cpuCards.innerHTML = "Cards Left: " + cpuPlayer.deck.getMonsters().length;

    dueler.innerHTML = "";

    switch (x) {
      case 0:
        cpu.innerHTML = "Yugi's Deck Selected!";
        break;
      case 1:
        cpu.innerHTML = "Kaiba's Deck Selected!";
        break;
      case 2:
        cpu.innerHTML = "Pegasus's Deck Selected!";
        break;
      case 3:
        cpu.innerHTML = "Random Deck Selected!";
        break;
      case 4:
        cpu.innerHTML = "Random Monsters Selected!";
        break;

    }

    setTimeout(promptDraw, 3000);

}

/*initializes player deck*/
function initPlayerDeck(deck) {

    player.chooseDeck(deck);

    let cpu = document.getElementById("cpu");
    let dueler = document.getElementById("dueler");
    let duelerPoints = document.getElementById("duelerPoints");
    let duelerCards = document.getElementById("duelerCards");

    /*removes images from dueler space*/
    dueler.removeChild(document.getElementById("yugi"));
    dueler.removeChild(document.getElementById("kaiba"));
    dueler.removeChild(document.getElementById("pegasus"));
    dueler.removeChild(document.getElementById("randd"));
    dueler.removeChild(document.getElementById("randm"));

    duelerPoints.innerHTML = "Life Points: " + player.lifePoints;
    duelerCards.innerHTML = "Cards Left: " + player.deck.getMonsters().length;


    dueler.innerHTML = "Waiting...";
    cpu.innerHTML = "Generating Opponent Deck...";
    setTimeout(initCpuDeck, 3500);

}


/*initial game function*/
function gameStart() {

    /*Very start of duel*/
    let topOfPage = document.getElementById("top");
    topOfPage.removeChild(document.getElementById("start"));
    document.getElementById("top").innerHTML = "Choose Your Deck !";

    /*opens dueler spaces*/
    let duelerSpace = document.getElementById("dueler");
    let cpuSpace = document.getElementById("cpu");
    duelerSpace.setAttribute("class", "well");
    cpuSpace.setAttribute("class", "well");

    /*cpu waiting for dueler to choose deck*/
    cpuSpace.innerHTML = "Waiting...";

    /*Creates duelers' images*/
    let yugi = document.createElement("IMG");
    yugi.setAttribute("src", "images/yugi.png");
    yugi.setAttribute("id", "yugi");
    yugi.setAttribute("width", "150");
    yugi.setAttribute("height", "150");
    yugi.setAttribute("onclick", "initPlayerDeck(0);")
    duelerSpace.appendChild(yugi);

    let kaiba = document.createElement("IMG");
    kaiba.setAttribute("src", "images/kaiba.png");
    kaiba.setAttribute("id", "kaiba");
    kaiba.setAttribute("width", "150");
    kaiba.setAttribute("height", "150");
    kaiba.setAttribute("onclick", "initPlayerDeck(1);");
    duelerSpace.appendChild(kaiba);

    let pegasus = document.createElement("IMG");
    pegasus.setAttribute("src", "images/pegasus2.png");
    pegasus.setAttribute("id", "pegasus");
    pegasus.setAttribute("width", "150");
    pegasus.setAttribute("height", "150");
    pegasus.setAttribute("onclick", "initPlayerDeck(2);");
    duelerSpace.appendChild(pegasus);

    let randd = document.createElement("IMG");
    randd.setAttribute("src", "images/randd.jpg");
    randd.setAttribute("id", "randd");
    randd.setAttribute("width", "150");
    randd.setAttribute("height", "150");
    randd.setAttribute("onclick", "initPlayerDeck(3);");
    duelerSpace.appendChild(randd);

    let randm = document.createElement("IMG");
    randm.setAttribute("src", "images/randm.jpg");
    randm.setAttribute("id", "randm");
    randm.setAttribute("width", "150");
    randm.setAttribute("height", "150");
    randm.setAttribute("onclick", "initPlayerDeck(4);");
    duelerSpace.appendChild(randm);

}
