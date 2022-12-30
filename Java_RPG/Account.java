import java.util.ArrayList;

import Classes.Character;
import Classes.Mage;
import Classes.Priest;
import Classes.Rogue;
import Classes.Warrior;

/** 
 * Author: Ethan Miller
 * Date: 12/26/2022
 * 
 * Instantiable class that acts as the player's account
 * - Contains the players gold & party members
 * 
 */

public class Account {
    
    String name; //Player Username
    int gold; //Gold to spend on items or hiring heroes or etc.
    ArrayList<Character> partyMembers; //List of heroes in the party, keeping it array list to avoid null pointer issues with set size array

    public Account(String name, int chosen){
        this.name = name;
        gold = 0; //Everyone Starts at nothing
        partyMembers = new ArrayList<Character>();
        Character Starter;

        //Create the starter character based on the chosen class
        switch (chosen){
            case 1:
                Starter = new Warrior();
                break;
            case 2:
                Starter = new Mage();
                break;
            case 3:
                Starter = new Rogue();
                break;
            case 4:
                Starter = new Priest();
                break;
            default:
                Starter = new Warrior();
                break;
        }

        partyMembers.add(Starter);
    }

}


