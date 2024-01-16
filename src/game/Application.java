package game;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.displays.ArchetypeMenu;
import game.environments.*;
import game.trading.FingerReaderEnia;
import game.trading.MerchantKale;
import game.trading.Trader;
import game.items.RuneManager;
import game.player.*;
import game.resethandling.ResetManager;
import game.utils.FancyMessage;
import game.utils.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The main class to start the game. Created by:
 *
 * @author Josh Ryan, Gunnraj Dhaliwal, Behnam Mozafari
 */
public class Application {

  public static void main(String[] args) {

    World world = new World(new Display());
    // adding game maps
    GameMap limgraveMap = Utils.createGameMap(Arrays.asList(
        "......................#.............#..........................+++.........",
        "......................#.............#.......................+++++..........",
        "......................#..___....____#.........................+++++........",
        "......................#...........__#............................++........",
        "......................#_____........#.............................+++......",
        "......................#............_#..............................+++.....",
        "......................######...######......................................",
        "...........................................................................",
        "...........................=..........n....................................",
        "........++++......................###___###................................",
        "........+++++++...................________#................................",
        "..........+++.....................#________................................",
        "............+++...................#_______#................................",
        ".............+....................###___###................................",
        "............++......................#___#..................................",
        "..............+...................=........................................",
        "..............++.................................................=.........",
        "..............................................++...........................",
        "..................++++......................+++...............######..##...",
        "#####___######....++...........................+++............#....____....",
        "_____________#.....++++..........................+..............__.....#...",
        "_____________#.....+....++........................++.........._.....__.#...",
        "_____________#.........+..+.....................+++...........###..__###...",
        "_____________#.............++.............................................."));
    world.addGameMap(limgraveMap);

    GameMap stormveilCastleMap = Utils.createGameMap(Arrays.asList(
        "...........................................................................",
        "..................<...............<........................................",
        "...........................................................................",
        "##############################################...##########################",
        "............................#................#.......B..............B......",
        ".....B...............B......#................#.............................",
        "...............................<.........<.................................",
        ".....B...............B......#................#.......B..............B......",
        "............................#................#.............................",
        "#####################..#############...############.####..#########...#####",
        "...............#++++++++++++#................#++++++++++++#................",
        "...............#++++++++++++...<.........<...#++++++++++++#................",
        "...............#++++++++++++..................++++++++++++#................",
        "...............#++++++++++++#................#++++++++++++#................",
        "#####...##########.....#############...#############..#############...#####",
        ".._______........................B......B........................B.....B...",
        "_____..._..____....&&........<..............<..............................",
        ".........____......&&......................................................",
        "...._______..................<..............<....................<.....<...",
        "#####....##...###..#####...##########___###############......##.....####...",
        "+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++",
        "+++++++++++++++++++++++++++....................#+++++++++++++++++++++++++++",
        "+++++++++++++++++++++++++++#....................+++++++++++++++++++++++++++",
        "+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++"));
    world.addGameMap(stormveilCastleMap);

    GameMap roundTableHoldMap = Utils.createGameMap(Arrays.asList(
        "##################",
        "#________________#",
        "#________________#",
        "#________________#",
        "#________________#",
        "#________________#",
        "#________________#",
        "#________________#",
        "#________________#",
        "#________________#",
        "########___#######"));
    world.addGameMap(roundTableHoldMap);

    GameMap bossRoomMap = Utils.createGameMap(Arrays.asList(
        "+++++++++++++++++++++++++",
        ".........................",
        "..=......................",
        ".........................",
        ".........................",
        ".........................",
        ".........................",
        ".........................",
        "+++++++++++++++++++++++++"));
    world.addGameMap(bossRoomMap);

    // Manually adding grounds
    SiteOfLostGrace theFirstStep = new SiteOfLostGrace("The First Step");
    limgraveMap.at(38, 12).setGround(theFirstStep);

    GoldenFogDoor limgraveToRoundTableHold = new GoldenFogDoor(roundTableHoldMap.at(9,9), "Roundtable Hold");
    limgraveMap.at(6, 22).setGround(limgraveToRoundTableHold);

    GoldenFogDoor roundTableHoldToLimgrave = new GoldenFogDoor(limgraveMap.at(6,21), "Limgrave");
    roundTableHoldMap.at(9, 10).setGround(roundTableHoldToLimgrave);

    GoldenFogDoor limgraveToStormveilCastle = new GoldenFogDoor(stormveilCastleMap.at(38,21), "Stormveil Castle");
    limgraveMap.at(29, 1).setGround(limgraveToStormveilCastle);

    GoldenFogDoor stormveilCastleToLimgrave = new GoldenFogDoor(limgraveMap.at(29,2), "Limgrave");
    stormveilCastleMap.at(38, 22).setGround(stormveilCastleToLimgrave);

    GoldenFogDoor stormveilCastleToBossRoom = new GoldenFogDoor(bossRoomMap.at(12,7), "Boss Room");
    stormveilCastleMap.at(1, 1).setGround(stormveilCastleToBossRoom);

    // Creating Managers
    ResetManager resetManager = ResetManager.getInstance();
    RuneManager runeManager = RuneManager.getInstance();
    PlayerManager playerManager = PlayerManager.getInstance();

    // BEHOLD, ELDEN RING
    for (String line : FancyMessage.ELDEN_RING.split("\n")) {
      new Display().println(line);
      try {
        Thread.sleep(200);
      } catch (Exception exception) {
        exception.printStackTrace();
      }
    }

    List<Archetype> archetypeList = new ArrayList<>();
    archetypeList.add(new Bandit());
    archetypeList.add(new Samurai());
    archetypeList.add(new Wretch());
    archetypeList.add(new Astrologer());
    Archetype archetype = ArchetypeMenu.archetypeSelector(archetypeList);

    Player player = new Player("Tarnished", '@', archetype);

    // spawning merchant
    Trader kale = new MerchantKale();
    limgraveMap.at(37, 11).addActor(kale);
    Trader enia = new FingerReaderEnia();
    limgraveMap.at(39, 11).addActor(enia);

    resetManager.registerResettable(player);
    playerManager.setPlayer(player);

    // playerSpawn added so that resetManager works even when the player decides not to ever rest
    int playerSpawnX = 29;
    int playerSpawnY = 3;

    player.setLastRested(limgraveMap.at(playerSpawnX, playerSpawnY));

    world.addPlayer(player, limgraveMap.at(37, 10));
    RuneManager.getInstance().addToActorRunes(player,1000000);
    world.run();
  }
}
