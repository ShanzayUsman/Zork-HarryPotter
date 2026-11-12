package org.uob.a1;

import java.util.Scanner; 

public class Game {  
    public static void main(String args[]) {

        Inventory inventory = new Inventory();
        Score Scoring = new Score(50);

        String[] Myingredients = new String[5]; //array for the last puzzle, to check if ingredients were added in the correct order
        String[] correctOrder = new String[5]; //stores the correct order of ingredients
        correctOrder[0] = "syrup of hellebore";
        correctOrder[1] = "stewed mandrake";
        correctOrder[2] = "powdered moonstone";
        correctOrder[3] = "phoenix ashes";
        correctOrder[4] = "porcupine quills";

        for (int i = 0; i < 5; i++) {
            Myingredients[i] = ""; //initialises array with empty string
        }
        
        // room declarations
        Room Astronomy = new Room("Astronomy Tower", "You stand at the highest point of Hogwarts castle, the Astronomy tower. The ceiling opens to a sky full of twinkling stars. In the centre stands a brass telescope, to which is hanging a folded note.", 'A', new Position(2, 0));
        Room HeadMaster = new Room("HeadMaster's Office", "You are in Professor Dumbledore's Office. A handwritten note lies beside a half-eaten lemon sherbet.", 'H', new Position(0, 2));
        Room Library = new Room("Library", "You are in the library. A single unrolled scroll lies on a study table. It seems to contain instructions on the performance of the lumos charm", 'L', new Position(4, 2));
        Room CommonRoom = new Room("Gryffindor Common Room", "You are in the gryffindor common room. There is a clutter of books on the oak table in front of the fireplace. A leather-bound book looks amiss among the others.", 'C', new Position(2, 2));
        Room Forbidden = new Room("Forbidden Corridor", "The corridor is covered in cobwebs and dust. Mischievous giggles bounce off the walls. Peeves the Poltergeist can be seen swirling in the air in the darkness of the shadows. An archway to the east leads to the Dungeons.", 'F', new Position(2, 3));
        Room Dungeon = new Room("Dungeon", "It is too dark to move further inside the dungeon. It seems you need a light source.", 'D', new Position(3, 3));
        Room Potions = new Room("Potions Classroom", "You are standing in the potions classroom. A cupboard stands ajar across the room. A dark ominous doorway to the north leads to the Forbidden corridor. An unlit stove can also be seen in the corner.", 'P', new Position(2, 4));
        Room Kitchen = new Room("Magical Kitchen", "In the kitchen, the house elves are hard at work. You see Dobby who ushers you towards him.", 'K', new Position(4, 4));
        Room Greenhouse = new Room("Herbology Greenhouse", "You stand in the Greenhouse. The air is thick with the strange scent of magical plants. An exit goes south.", 'G', new Position(2, 6));
        Room SecretChamber = new Room("Secret Chamber", "You are in the secretchamber. A stone wall blocks your path. On it are etched the words 'For those unprepared, this path is not to be tread. Return only when the four essences are gathered- only then shall the fifth reveal itself'.", 'S', new Position(2, 7));
        Room Staircase = new Room("Staircase", "You can follow the staircase north to the Astronomy Tower", 's', new Position(2, 1));
        Room hallway1 = new Room("Hallway", "There are two doorways to the east and west of the hallway in which you stand.", 'h', new Position(1, 2));
        Room hallway2 = new Room("Hallway", "There are two doorways to the east and west of the hallway in which you stand.", 'h', new Position(3, 2));
        Room hallway3 = new Room("Hallway", "You can follow this hallway to the Dungeons, the Potions classroom, or to the Magical Kitchen.", 'h', new Position(3, 4));
        Room hallway4 = new Room("Hallway", "This hallway leads to the Herbology Greenhouse.", 'h', new Position(2, 5));

        // room connections
        Astronomy.setSouth(Staircase);
        
        Staircase.setNorth(Astronomy);
        Staircase.setSouth(CommonRoom);
        
        CommonRoom.setNorth(Staircase);
        CommonRoom.setSouth(Forbidden);
        CommonRoom.setEast(hallway2);
        CommonRoom.setWest(hallway1);
        
        hallway1.setWest(HeadMaster);
        hallway1.setEast(CommonRoom);
        
        hallway2.setWest(CommonRoom);
        hallway2.setEast(Library);
        hallway2.setSouth(Dungeon);
        
        Forbidden.setNorth(CommonRoom);
        Forbidden.setSouth(Potions);
        Forbidden.setEast(Dungeon);
        
        HeadMaster.setEast(hallway1);
        
        Library.setWest(hallway2);
        
        Dungeon.setNorth(hallway2);
        Dungeon.setSouth(hallway3);
        Dungeon.setWest(Forbidden);
        
        Potions.setNorth(Forbidden);
        
        Potions.setSouth(hallway4);
        Potions.setEast(hallway3);
        
        hallway3.setNorth(Dungeon);
        hallway3.setEast(Kitchen);
        hallway3.setWest(Potions);
        
        Kitchen.setWest(hallway3);
        
        hallway4.setNorth(Potions);
        hallway4.setSouth(Greenhouse);
        
        Greenhouse.setNorth(hallway4);
        Greenhouse.setSouth(SecretChamber);
        
        SecretChamber.setNorth(Greenhouse);

        
        boolean playing = true;
        Map map = new Map(5, 8);
        map.placeRoom(Potions.getPosition(), Potions.getSymbol());
        Room currentRoom = Potions;
        Scanner inputDevice = new Scanner(System.in);

        //setting the game world
        System.out.println("You are a student at the Hogwarts School of Witchcraft and Wizardy. Professor Slughorn has assigned you the task of brewing the Draught of Wisdom. Explore the castle to collect clues and ingredients to help you make your potion. Your potion must be brewed in the potions classroom. You have until midnight. Goodluck!");

        // in game variables
        boolean mandrake = false;
        boolean quills = false;
        boolean password = false;
        boolean lightused = false;
        boolean syrup = false;
        boolean fouringredients = false;
        boolean notetaken = false;
        boolean allingredients = false;
        boolean talkPeeves = false;
        boolean talkDobby = false;
        boolean answered = false;
        boolean noteRead = false;
        boolean bookread = false;
        boolean ashes = false;
        boolean fluxweed = false;
        boolean cauldrontaken = false;
        boolean cupboardchecked = false;
        boolean moonstone = false;
        boolean cauldronused = false;
        boolean ashesused = false;
        boolean moonstoneused = false;
        boolean quillsused = false;
        boolean syrupused = false;
        boolean mandrakeused = false;
        boolean potionfailed = false;
        int ingredientsused = 0;
        int movesleft = 120;

        while (playing) {
            String input = inputDevice.nextLine().toLowerCase().trim();

            //different cases for different user entered commands
            if (input.equals("move north") || input.equals("move south") || input.equals("move east") || 
                input.equals("move west") || input.equals("look") || input.equals("help") || 
                input.equals("quit") || input.equals("map") || input.equals("inventory") || 
                input.equals("score") || input.equals("restart potion")) {
                
                switch (input) { //changes rooms if direction is valid
                    case "move north":
                        if (currentRoom.getNorth() != null) {
                            currentRoom = currentRoom.getNorth();
                            System.out.println("You have moved north to the " + currentRoom.getName());
                            Scoring.visitRoom();
                            map.placeRoom(currentRoom.getPosition(), currentRoom.getSymbol());
                        } else {
                            System.out.println("There is no room to the north");
                        }
                        break;
                    
                    case "move south":
                        if (currentRoom.getSouth() != null) {
                            currentRoom = currentRoom.getSouth();
                            System.out.println("You have moved south to the " + currentRoom.getName());
                            Scoring.visitRoom();
                            map.placeRoom(currentRoom.getPosition(), currentRoom.getSymbol());
                        } else {
                            System.out.println("There is no room to the south");
                        }
                        break;
            
                    case "move west":
                        if (currentRoom.getWest() != null) {
                            currentRoom = currentRoom.getWest();
                            System.out.println("You have moved West to the " + currentRoom.getName());
                            Scoring.visitRoom();
                            map.placeRoom(currentRoom.getPosition(), currentRoom.getSymbol());
                        } else {
                            System.out.println("There is no room to the West");
                        }
                        break;
            
                    case "move east":
                        if (currentRoom.getEast() != null) {
                            currentRoom = currentRoom.getEast();
                            System.out.println("You have moved East to the " + currentRoom.getName());
                            Scoring.visitRoom();
                            map.placeRoom(currentRoom.getPosition(), currentRoom.getSymbol());
                        } else {
                            System.out.println("There is no room to the East");
                        }
                        break;

                    case "restart potion": //last puzzle case, allows user to attempt to make the correct potion multiple times
                        if (potionfailed == true) {
                            inventory.addItem("porcupine quills");
                            inventory.addItem("phoenix ashes");
                            inventory.addItem("powdered moonstone");
                            inventory.addItem("stewed mandrake");
                            inventory.addItem("syrup of hellebore");
                            syrupused = false;
                            mandrakeused = false;
                            ashesused = false;
                            moonstoneused = false;
                            quillsused = false;
                            ingredientsused = 0;
                            potionfailed = false;
                            for (int i = 0; i < 5; i++) {
                                Myingredients[i] = "";
                            }
                            System.out.println("The potion has been reset. Add the ingredients in the correct order");
                        } else {
                            System.out.println("You can still attempt to brew the potion");
                        }
                        break;

                    case "inventory":
                        System.out.println(inventory.displayInventory());
                        break;
            
                    case "look": //outputs a different description depending on where you are in the game and in the room
                        if (currentRoom == Potions && allingredients == false) {
                            System.out.println(Potions.getDescription());
                        } else if (currentRoom == Potions && allingredients == true) {
                            System.out.println("You are in the potions classroom. A stove is lit up ready for use.");
                        } else if (currentRoom == Astronomy) {
                            System.out.println(Astronomy.getDescription());
                        } else if (currentRoom == Forbidden) {
                            System.out.println(Forbidden.getDescription());
                        } else if (currentRoom == Dungeon && lightused == false) {
                            System.out.println(Dungeon.getDescription());
                        } else if (currentRoom == Dungeon && lightused == true && syrup == false) {
                            System.out.println("A small, dusty vial containing a thick liquid sits on a stone shelf. The label reads 'Syrup of Hellebore'.");
                        } else if (currentRoom == Dungeon && lightused == true && syrup == true) {
                            System.out.println("You are in the dungeons. The stone shelf that once held the vial is now empty. There is nothing else of notice.");
                        } else if (currentRoom == SecretChamber && fouringredients == false) {
                            System.out.println(SecretChamber.getDescription());
                        } else if (currentRoom == SecretChamber && fouringredients == true) {
                            System.out.println("The stone wall that once blocked your path is now gone. In the centre of the room, there is a small flask containing phoenix ashes.");
                        } else if (currentRoom == CommonRoom && notetaken == false) {
                            System.out.println(CommonRoom.getDescription());
                        } else if (currentRoom == CommonRoom && notetaken == true) {
                            System.out.println("You are in the cozy Gryffindor common room. The only notable feature is the stairwell to the north and two exits to the east and west");
                        } else if (currentRoom == Library) {
                            System.out.println(Library.getDescription());
                        } else if (currentRoom == Greenhouse && mandrake == false) {
                            System.out.println(Greenhouse.getDescription() + " Among the many pots, there is one with a stewed mandrake floating in a murky liquid. You can faintly hear its cries.");
                        } else if (currentRoom == Greenhouse && mandrake == true) {
                            System.out.println(Greenhouse.getDescription());
                        } else if (currentRoom == HeadMaster && password == false) {
                            System.out.println("A stone gargoyle blocks the entrance to the headmaster's office. Say the password to get through");
                        } else if (currentRoom == HeadMaster && password == true) {
                            System.out.println(HeadMaster.getDescription());
                        } else if (currentRoom == Kitchen && quills == false) {
                            System.out.println(Kitchen.getDescription());
                        } else if (currentRoom == Kitchen && quills == true) {
                            System.out.println("In the kitchen, the house elves are hard at work");
                        } else {
                            System.out.println(currentRoom.getDescription());
                        }
                        break;
                    
                    case "help":
                        System.out.println("valid commands are <move direction><look feature><look><look item><quit><help><score><map><take item><inventory><use item><read item><talk to person>");
                        break;
            
                    case "quit":
                        System.out.println("GAME OVER!");
                        System.out.println("Score: " + Scoring.getScore());
                        playing = false;
                        break;
                    
                    case "map":
                        System.out.println(map.display());
                        break;
            
                    case "score":
                        System.out.println("Score: " + Scoring.getScore());
                        break;
                }
            } else if (currentRoom == HeadMaster && password == false && 
                      (input.equals("lemon sherbet") || input.equals("say lemon sherbet") || input.equals("answer lemon sherbet"))) {
                System.out.println("The statue gargoyle comes to life and jumps aside." + currentRoom.getDescription());
                password = true;
                Scoring.solvePuzzle();
            } else if (input.startsWith("talk")) { //cases for talking to various people
                if (currentRoom == Forbidden) {
                    if (talkPeeves == false) {
                        if (input.equals("talk to peeves") || input.equals("talk to peeves the poltergeist") || input.equals("talk")) {
                            System.out.println("oo a little ickie firstie in the forbidden corridor? Peeves knows a secret and you don't! Saw that sneaky filch trying to get into Dumbledore's office, He was. Gargoyle wouldn't budge til he said... what was it?Something sweet... something yellow? Some lemon sherbet would help jog my memory. Those Fred and George raided my stash!");
                            talkPeeves = true;
                        } else {
                            System.out.println("talk to who?");
                        }
                    } else if (talkPeeves == true) {
                        System.out.println("Lemon sherbet! That was it! Now scram before i tell Filch you're here");
                    }
                } else if (currentRoom == Kitchen) {
                    if (input.equals("talk to dobby")) {
                        if (talkDobby == false) {
                            System.out.println("Oh my! Dobby is delighted to see that Sir has come to see him in the kitchens! Dobby has three new pairs of socks now! ... What's that? Sir is preparing a Draught of Wisdom and needs an ingredient? Dobby would be honoured to help! Here! Dobby extracted these *porcupine quills* himself! Take them Sir, they are an essential ingredient in your potion");
                            talkDobby = true;
                        } else if (talkDobby == true) {
                            if (quills == false) {
                                System.out.println("Take them sir, I insist");
                            } else if (quills == true) {
                                System.out.println("Dobby is delighted to have been of assistance!");
                            }
                        }
                    } else {
                        System.out.println("talk to who?");
                    }
                } else {
                    System.out.println("Invalid command");
                }
            } else if (input.equals("moon") || input.equals("answer moon") || input.equals("the moon") || input.equals("answer the moon")) { //solving the riddle puzzle
                if (currentRoom == Astronomy) {
                    if (noteRead) {
                        if (!answered) {
                            System.out.println("A small compartment below the telescope opens up and reveals a small pouch of powdered moonstone");
                            answered = true;
                            Scoring.solvePuzzle();
                        } else {
                            System.out.println("You have already answered the riddle");
                        }
                    } else {
                        System.out.println("read the note first");
                    }
                } else {
                    System.out.println("Invalid command");
                }
            } else if (input.startsWith("take ")) { //checks if item can be added to inventory
                if (currentRoom == Potions) {
                    if (cupboardchecked == true) {
                        if (input.substring(5).equals("cauldron")) {
                            if (cauldrontaken == false) {
                                inventory.addItem("cauldron");
                                cauldrontaken = true;
                                System.out.println("Item added to inventory");
                            } else {
                                System.out.println("You already took the cauldron");
                            }
                        } else if (input.substring(5).equals("fluxweed")) {
                            if (fluxweed == false) {
                                inventory.addItem("fluxweed");
                                fluxweed = true;
                                System.out.println("Item added to inventory");
                            } else {
                                System.out.println("You already took the fluxweed");
                            }
                        } else {
                            System.out.println("There is no object here that can be taken");
                        }
                    } else if (cupboardchecked == false && (input.substring(5).equals("cauldron") || input.substring(5).equals("fluxweed"))) {
                        System.out.println("look in the cupboard first");
                    }else{ System.out.println("Invalid command");
                         }
                } else if (currentRoom == Astronomy) {
                    if (input.substring(5).equals("moonstone") || input.substring(5).equals("powdered moonstone") || 
                        input.substring(5).equals("the moonstone") || input.substring(5).equals("the powdered moonstone")) {
                        if (answered == true) {
                            if (moonstone == false) {
                                inventory.addItem("powdered moonstone");
                                moonstone = true;
                                System.out.println("Item added to inventory");
                            } else if (moonstone == true) {
                                System.out.println("You already took the moonstone");
                            }
                        } else if (answered == false) {
                            System.out.println("Answer the riddle first");
                        }
                    } else {
                        System.out.println("Can not take this item");
                    }
                } else if (currentRoom == Dungeon) {
                    if (input.substring(5).equals("syrup") || input.substring(5).equals("syrup of hellebore")) {
                        if (lightused == true) {
                            if (syrup == false) {
                                inventory.addItem("syrup of hellebore");
                                syrup = true;
                                System.out.println("Item added to inventory");
                            } else {
                                System.out.println("You already took the Syrup");
                            }
                        } else {
                            System.out.println("Find a light source first");
                        }
                    } else {
                        System.out.println("Can not add this item to inventory");
                    }
                } else if (currentRoom == SecretChamber) {
                    if (input.substring(5).equals("ashes") || input.substring(5).equals("phoenix ashes")) {
                        if (fouringredients == true) {
                            if (ashes == false) {
                                inventory.addItem("phoenix ashes");
                                ashes = true;
                                System.out.println("Item added to inventory");
                                allingredients = true;
                            } else {
                                System.out.println("You have already taken the phoenix ashes");
                            }
                        } else {
                            System.out.println("Path is blocked");
                        }
                    } else {
                        System.out.println("Can not add item to inventory");
                    }
                } else if (currentRoom == CommonRoom) {
                    if (input.substring(5).equals("note") || input.substring(5).equals("paper")) {
                        if (bookread == true) {
                            if (notetaken == false) {
                                inventory.addItem("note");
                                System.out.println("Item added to inventory");
                                notetaken = true;
                            } else {
                                System.out.println("Note has already been taken");
                            }
                        } else {
                            System.out.println("Look at the book first");
                        }
                    } else {
                        System.out.println("Can not add this item to inventory");
                    }
                } else if (currentRoom == Greenhouse) {
                    if (input.substring(5).equals("mandrake") || input.substring(5).equals("stewed mandrake") || input.substring(5).equals("pot")) {
                        if (mandrake == false) {
                            inventory.addItem("stewed mandrake");
                            mandrake = true;
                            System.out.println("Item added to inventory");
                        } else {
                            System.out.println("item has already been taken");
                        }
                    } else {
                        System.out.println("Can not add this item to inventory");
                    }
                } else if (currentRoom == Kitchen) {
                    if (input.substring(5).equals("porcupine quills") || input.substring(5).equals("quills")) {
                        if (quills == false) {
                            inventory.addItem("porcupine quills");
                            quills = true;
                            System.out.println("Item added to inventory");
                        } else {
                            System.out.println("You have already taken the porcupine quills");
                        }
                    } else {
                        System.out.println("Can not add this item to inventory");
                    }
                }else{ System.out.println("Invalid command");
                     }
            } else if (input.startsWith("read ")) { //read commands and cases
                if (currentRoom == HeadMaster) {
                    if (input.substring(5).equals("note")) {
                        if (password == true) {
                            System.out.println("Four pillars must combine to reveal the fifth: the quills of the guardian, the dust of the silver orb, the shadow's syrup and the earth's tears. Only then will the phoenix rise from its ashes. But beware of the fluxweed harvested under the new moon, for it shall lead to your doom...");
                        } else {
                            System.out.println("Invalid command");
                        }
                    } else {
                        System.out.println("Invalid command");
                    }
                } else if (currentRoom == Library) {
                    if (input.substring(5).equals("scroll")) {
                        System.out.println("Lumos (the wand lighting charm): This charm lights the tip of the caster's wand, allowing them to see in the dark. It simply requires concentration, and for the caster to say the word 'lumos'. The counter-charm for it is 'nox', the wand-extingushing charm.");
                    } else {
                        System.out.println("Invalid command");
                    }
                } else if (currentRoom == Astronomy) {
                    if (input.substring(5).equals("note") || input.substring(5).equals("riddle") || input.substring(5).equals("paper")) {
                        System.out.println("I am a sphere, though I never spin. I am always full, yet sometimes thin. I steal my light, and never share, but glow so bright, with no one there. What am I?");
                        noteRead = true;
                    } else {
                        System.out.println("Invalid command");
                    }
                }else{ System.out.println("Can not read this item. Maybe you should try adding it to your inventory");
                     }
                
            } else if (input.equals("lumos") || input.equals("say lumos")) { //case of lumos. Puzzle is solved
                if (currentRoom == Dungeon) {
                    if (!lightused) {
                        lightused = true;
                        Scoring.solvePuzzle();
                        System.out.println("Light illuminates from the tip of your wand. Ahead of you there is a stone shelf holding a small vial of a thick syrup. It is labelled 'Syrup of Hellebore'");
                    } else {
                        System.out.println("You have already used the charm!");
                    }
                } else {
                    System.out.println("can not use this charm here");
                }

                
            } else if (input.startsWith("look ")) { //look cases for fixed features and inventory items
                String item = input.substring(5);
                if (item.equals("cauldron")){ 
                    if (inventory.hasItem("cauldron") >=0){
                        System.out.println("You can use this cauldron to brew your potion. Use it on the stove once you collect all the ingredients");
                    }else{ System.out.println("item is not in inventory");
                         }
                }else if (item.equals("phoenix ashes")){
                    if(inventory.hasItem("phoenix ashes")>=0){
                        System.out.println("The secret fifth ingredient! You can now head over to the potions classroom to brew your potion!");
                    }else{System.out.println("Item is not in inventory");
                         }
                }else if (item.equals("porcupine quills")){
                    if(inventory.hasItem("porcupine quills")>=0){
                        System.out.println("An ingredient you  may need for your potion");
                    }else{System.out.println("Item is not in inventory");
                         }
                }else if(item.equals("syrup of hellebore")||item.equals("syrup")){
                    if (inventory.hasItem("syrup of hellebore")>=0){
                        System.out.println("An ingredient you may need for your potion");
                    }else{ System.out.println("Item is not in inventory");
                         }
                }else if(item.equals("stewed mandrake")){
                    if (inventory.hasItem("stewed mandrake")>=0){
                        System.out.println("An ingredient you may need for your potion");
                    }else{ System.out.println("Item is not in inventory");
                         }
                }else if(item.equals("powdered moonstone")){
                    if (inventory.hasItem("powdered moonstone")>=0){
                        System.out.println("An ingredient you may need for your potion");
                    }else{ System.out.println("Item is not in inventory");
                         }
                }else if(item.equals("fluxweed")){
                    if (inventory.hasItem("fluxweed")>=0){
                        System.out.println("An ingredient you may need for your potion");
                    }else{ System.out.println("Item is not in inventory");
                         }
                }else if (item.equals("note")){
                    if (inventory.hasItem("note")>=0){
                        System.out.println("There is writing on the note: 1.Add a splash of the syrup of Hellebore 2. Follow it with a single stewed mandrake 3. Wait five seconds before adding the powdered moonstone 4. Add ---(*ingredient number four is smudged*) 5.Add a handful of porcupine quills and your draught of wisdom will be ready!");
                    }else{ System.out.println("Item is not in inventory");
                         }

                } else if (currentRoom == Potions) {
                    if (item.equals("cupboard") || item.equals("in cupboard") || item.equals("at cupboard")) {
                        cupboardchecked = true;
                        if (fluxweed == true && cauldrontaken == true) {
                            System.out.println("The cupboard is empty");
                        } else if (fluxweed == true && cauldrontaken == false) {
                            System.out.println("There is a cauldron inside");
                        } else if (fluxweed == false && cauldrontaken == true) {
                            System.out.println("There is a pack of fluxweed in the cupboard");
                        } else if (!fluxweed && !cauldrontaken) {
                            System.out.println("There is a cauldron in the cupboard. Next to it is a small packet of fluxweed");
                        }
                    } else if (item.equals("stove") || item.equals("at stove")) {
                        if (allingredients == true) {
                            System.out.println("The stove is lit and ready for use");
                        } else {
                            System.out.println("Professor Slughorn says you should collect all the ingredients before brewing your potion");
                        }
                    } else {
                        System.out.println("Invalid command");
                    }
                } else if (currentRoom == Astronomy) {
                    if (item.equals("telescope") || item.equals("at telescope")) {
                        System.out.println("The telescope points towards the starry sky. A note is hung on the eye piece");
                    } else {
                        System.out.println("Invalid command");
                    }
                } else if (currentRoom == CommonRoom) {
                    if (item.equals("book") || item.equals("at book") || item.equals("in book")) {
                        bookread = true;
                        if (notetaken) {
                            System.out.println("The note that was previously in the book has been taken");
                        } else {
                            System.out.println("A note seems to be placed inside the book");
                        }
                    } else {
                        System.out.println("Invalid command");
                    }
                } else if (currentRoom == Greenhouse) {
                    if (item.equals("pots") || item.equals("at pots")) {
                        if (mandrake) {
                            System.out.println("The pots contain strange magical plants. The stewed mandrake pot lies empty.");
                        } else {
                            System.out.println("The pots contain strange magical plants. One of them contains a stewed mandrake.");
                        }
                    } else {
                        System.out.println("Invalid command");
                    }
                } else if (currentRoom == Dungeon) {
                    if (item.equals("shelf") || item.equals("at shelf")) {
                        if (syrup) {
                            System.out.println("The shelf is empty");
                        } else {
                            System.out.println("The shelf holds a vial containing a thick, syrup like liquid. It is labelled 'syrup of hellebore'");
                        }
                    } else {
                        System.out.println("Invalid command");
                    }
                }
                    
            } else if (input.startsWith("use ")) { //using items from inventory
                String item = input.substring(4).trim();
                if (currentRoom == Potions) {
                    if (potionfailed) {
                        System.out.println("You failed to make the potion. Type 'restart potion' in order to try again.");
                    } else if (allingredients) {
                        if (item.equals("cauldron")) {
                            if (!cauldronused) {
                                inventory.removeItem("cauldron");
                                cauldronused = true;
                                System.out.println("The cauldron has been placed on the stove. You can now start adding ingredients");
                            } else {
                                System.out.println("You have already used the cauldron");
                            }
                        } else if (cauldronused) { //can only add ingredients once cauldron has been put down on the stove
                            if (item.equals("fluxweed")) {
                                System.out.println("You add the fluxweed to the cauldron... *BOOM*! The potion explodes! The fluxweed was a dangerous ingredient! YOU LOSE! YOUR FINAL SCORE: " + Scoring.getScore());
                                potionfailed = true;
                                playing = false;
                            } else if (item.equals("porcupine quills")) {
                                if (quillsused) {
                                    System.out.println("You have already added the porcupine quills");
                                } else {
                                    inventory.removeItem("porcupine quills");
                                    quillsused = true;
                                    ingredientsused++;
                                    System.out.println("The potion begins to shimmer");
                                    Myingredients[ingredientsused - 1] = item;
                                }
                            } else if (item.equals("powdered moonstone")) {
                                if (moonstoneused) {
                                    System.out.println("You have already added the powdered moonstone");
                                } else {
                                    inventory.removeItem("powdered moonstone");
                                    moonstoneused = true;
                                    ingredientsused++;
                                    System.out.println("The potion thickens noticeably");
                                    Myingredients[ingredientsused - 1] = item;
                                }
                            } else if (item.equals("phoenix ashes")) {
                                if (ashesused) {
                                    System.out.println("You have already added the phoenix ashes");
                                } else {
                                    inventory.removeItem("phoenix ashes");
                                    ashesused = true;
                                    ingredientsused++;
                                    System.out.println("The potion is now glowing with a soft light");
                                    Myingredients[ingredientsused - 1] = item;
                                }
                            } else if (item.equals("syrup of hellebore")) {
                                if (syrupused) {
                                    System.out.println("You have already added the syrup of hellebore");
                                } else {
                                    inventory.removeItem("syrup of hellebore");
                                    syrupused = true;
                                    ingredientsused++;
                                    System.out.println("The potion erupts in golden flames");
                                    Myingredients[ingredientsused - 1] = item;
                                }
                            } else if (item.equals("stewed mandrake")) {
                                if (mandrakeused) {
                                    System.out.println("You have already added the stewed mandrake");
                                } else {
                                    inventory.removeItem("stewed mandrake");
                                    mandrakeused = true;
                                    ingredientsused++;
                                    System.out.println("The potion glows with a soft light");
                                    Myingredients[ingredientsused - 1] = item;
                                }
                            } else {
                                System.out.println("Invalid command. *When using an ingredient, make sure to use the full name. Refer to your inventory*");
                            }
                        } else {
                            System.out.println("You must first use the cauldron in order to start adding ingredients");
                        }
                    } else {
                        System.out.println("You must collect all ingredients first");
                    }
                } else {
                    System.out.println("Invalid command. *potion must be brewed in the potions classroom* *When using an ingredient, make sure to use the full name. Refer to your inventory*");
                }
            } else {
                System.out.println("invalid command");
            }

            // Checks if all ingredients and if they are in the correct order
            if (ingredientsused == 5 && !potionfailed) {
                int i = 0;
                boolean correct = true;
                while (correct && i < 5) {
                    if (Myingredients[i].equals(correctOrder[i])) {
                        i++;
                    } else {
                        correct = false;
                        potionfailed = true;
                    }
                }
                if (potionfailed && playing) { //checks if the user added fluxweed or just didn't add the ingredients in the correct order
                    System.out.println("You failed to make the potion. Type 'restart potion' to try again");
                } else {
                    System.out.println("Success! You have brewed the draught of wisdom perfectly! Professor Slughorn is proud");
                    playing = false;
                    Scoring.solvePuzzle();
                }
            }

            // Check if player has collected the first four ingredients
            if (moonstone && quills && syrup && mandrake) {
                fouringredients = true;
                Scoring.solvePuzzle();
            }
            movesleft--;

            if (movesleft == 0){ //checks if the player has moves left after every new command
                System.out.println("It is midnight! You failed to make the potion on time!");
                playing = false;
                System.out.println("YOUR FINAL SCORE : " + Scoring.getScore());
            }

            if (movesleft%10 == 0){ //reminds player of moves after every ten moves
                System.out.println("Hurry! It's almost midnight! MOVES LEFT : " + movesleft);
            }
        }
        inputDevice.close();
    }
}

