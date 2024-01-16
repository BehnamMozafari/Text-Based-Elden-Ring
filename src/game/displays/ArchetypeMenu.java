package game.displays;

import edu.monash.fit2099.engine.displays.Display;
import game.player.Archetype;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A menu for selecting a combat archetype.
 *
 * @author Josh Ryan
 */
public class ArchetypeMenu {

  /**
   * Display a menu to the user and have them select an option for a combat archetype.
   *
   * @param archetypes the Archetypes that the user can choose from
   * @return the Archetype selected by the user
   */
  public static Archetype archetypeSelector(List<Archetype> archetypes) {
    ArrayList<Character> freeChars = new ArrayList<Character>();
    HashMap<Character, Archetype> keyToArchetypeMap = new HashMap<Character, Archetype>();

    Display display = new Display();

    for (char i = 'a'; i <= 'z'; i++) {
      freeChars.add(i);
    }

    for (char i = 'A'; i <= 'Z'; i++) {
      freeChars.add(i);
    }

    for (Archetype archetype : archetypes) {
      char c = freeChars.get(0);
      ;
      freeChars.remove(Character.valueOf(c));
      keyToArchetypeMap.put(c, archetype);
      display.println(c + ": " + archetype.toString());
    }

    char key;
    do {
      key = display.readChar();
    } while (!keyToArchetypeMap.containsKey(key));

    return keyToArchetypeMap.get(key);
  }

}
