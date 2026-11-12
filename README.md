# Draught of Wisdom: A Hogwarts Text Adventure

A Zork-style text adventure written in Java, set in the halls of Hogwarts. Professor Slughorn has set you the task of brewing the **Draught of Wisdom** in the Potions Classroom before midnight. To do it you must explore the castle, solve puzzles, gather five ingredients, and add them to the cauldron in the correct order.

You have **120 moves** until midnight. Every command you type counts as a move, including invalid ones, so choose carefully.

## Running the game

The game uses only the Java standard library (Java 8 or later).

From the folder that contains the `org` directory:

```
javac org/uob/a1/*.java
java org.uob.a1.Game
```

The game starts in the Potions Classroom and reads commands from the keyboard. Input is not case-sensitive.

## Commands

| Command | What it does |
| --- | --- |
| `move north` / `south` / `east` / `west` | Move to the connected room in that direction |
| `look` | Describe the current room |
| `look <thing>` | Examine a feature of the room (e.g. `look cupboard`) or an item in your inventory |
| `take <item>` | Pick up an item and add it to your inventory |
| `read <item>` | Read a note or scroll |
| `use <item>` | Use an item (this is how you brew the potion) |
| `talk to <person>` | Speak to a character |
| `inventory` | List what you are carrying |
| `map` | Show the map of the rooms you have visited |
| `score` | Show your current score |
| `restart potion` | Reset the cauldron after a failed brew |
| `help` | List the valid commands |
| `quit` | End the game and show your score |

Some puzzles also take spoken answers, such as typing a password or a spell on its own line. Read the clues carefully.

## The map

The castle is a 5 × 8 grid. Rooms appear on the `map` as you visit them, each shown by its symbol, with `.` for unexplored space.

```
. . A . .      A  Astronomy Tower
. . s . .      s  Staircase
H h C h L      H  Headmaster's Office    L  Library
. . F D .      C  Gryffindor Common Room h  Hallway
. . P h K      F  Forbidden Corridor     D  Dungeon
. . h . .      P  Potions Classroom      K  Magical Kitchen
. . G . .      G  Herbology Greenhouse
. . S . .      S  Secret Chamber
```

## Scoring

- You start with **50 points**.
- Each room you move into costs **1 point**, so wandering aimlessly is penalised.
- Each puzzle you solve earns **10 points**.
- Your final score is shown when the game ends, whether you win, lose, or quit.

## How the game ends

- **Win:** add all five ingredients in the correct order and the potion is a success.
- **Lose:** add the fluxweed to the cauldron and the potion explodes, or run out of moves at midnight.
- **Failed brew:** adding the ingredients in the wrong order does not end the game. Type `restart potion` to get your ingredients back and try again.

## Project structure

| File | Purpose |
| --- | --- |
| `Game.java` | Entry point. Holds the game loop, command parsing, room setup and puzzle logic |
| `Room.java` | A room with a name, description, map symbol, position, and links to its north/south/east/west neighbours |
| `Position.java` | A simple `(x, y)` coordinate on the map grid |
| `Map.java` | A 2D character grid that records and displays the rooms the player has visited |
| `Inventory.java` | A fixed-size inventory (10 items) with add, remove, check and display methods |
| `Score.java` | Tracks the starting score, rooms visited and puzzles solved, and calculates the total |

Rooms are linked to each other as a graph using the `setNorth`, `setSouth`, `setEast` and `setWest` methods, and the player moves by following those links from the current room.

## Walkthrough (spoilers)

<details>
<summary>Click to reveal the full solution</summary>

**The five ingredients, in the order they must be added:**
1. Syrup of hellebore
2. Stewed mandrake
3. Powdered moonstone
4. Phoenix ashes
5. Porcupine quills

**Where to find them:**

- **Stewed mandrake:** Herbology Greenhouse. `take mandrake`.
- **Powdered moonstone:** Astronomy Tower. `read note` to get the riddle, then answer `moon`, then `take moonstone`.
- **Syrup of hellebore:** Dungeon. It is too dark to see, so read the scroll in the Library first, then say `lumos` in the Dungeon and `take syrup`.
- **Porcupine quills:** Magical Kitchen. `talk to dobby`, then `take quills`.
- **Phoenix ashes:** Secret Chamber. These only appear once you hold the other four ingredients. `take ashes`.

**Helpful clues:**

- **Gryffindor Common Room:** `look book`, then `take note` gives you the recipe, with ingredient four smudged out.
- **Forbidden Corridor:** `talk to peeves` reveals the Headmaster's password.
- **Headmaster's Office:** say `lemon sherbet` to get past the gargoyle, then `read note` for a hint about the four ingredients.

**Brewing:**

1. In the Potions Classroom, `look cupboard`, then `take cauldron`. **Do not take the fluxweed.** It is a trap.
2. Once you hold all five ingredients, `use cauldron` to place it on the stove.
3. `use` each ingredient in the order above, using its full name (e.g. `use syrup of hellebore`).

</details>

## Possible future improvements

- Support multi-word synonyms for items so players don't have to guess exact names.
- Move the room and puzzle state out of `Game.java` into dedicated classes to shorten the main method.
- Add a save and load feature.

