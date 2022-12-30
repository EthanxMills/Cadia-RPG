package Gameplay;
import java.util.ArrayList;
import java.util.Scanner;

import Classes.Character;
import Classes.Goblin;

public class GameLoop {
    Scanner scan = new Scanner(System.in);
    Account playerAccount;
    ArrayList<Character> party;
    
    public GameLoop(Account playerAccount){
        this.playerAccount = playerAccount;
        party = playerAccount.partyMembers;
    }

    public void encounter1(){
        //encounter 1
        ArrayList<Character> enemies = new ArrayList<>();
        System.out.println("You encounter a goblin!");
        Character goblin = new Goblin();

        enemies.add(goblin);
        
        // determine move order
        ArrayList<Character> moveOrder = moveOrder(playerAccount.partyMembers, enemies);
        int i = 0;

        while(true){
            //see if the next entity in the move order is an ally or enemy
            
            if(party.contains(moveOrder.get(i))){
                allyTurn(moveOrder.get(i), enemies.get(0));
            } else {
                enemyTurn(moveOrder.get(i));
            }

            //Make it so that trading blows is actually a loss for the player
            if(isDead(playerAccount.partyMembers.get(0))){
                System.out.println("You have perished!");
                party.remove(moveOrder.get(i));
                moveOrder.remove(moveOrder.get(i));
                System.exit(0);
            }
            if(isDead(goblin)){
                System.out.println("You have defeated the goblin!");
                enemies.remove(moveOrder.get(i));
                moveOrder.remove(moveOrder.get(i));
                break;
            }
            
            i++;
            if(i == moveOrder.size()){ i = 0; } //loop the turn order for drawn out battles
        }
        System.out.println("PLACE HOLDER FOR EXP AND GOLD GAIN");
    }

    // determine move order by checking speeds of all party members and enemies
    public ArrayList<Character> moveOrder(ArrayList<Character> partyMembers, ArrayList<Character> enemies){
        ArrayList<Character> moveOrder = new ArrayList<>();

        // add all party members & enemies to move order then sort from high to low by speed
        for (Character entity : partyMembers){
            moveOrder.add(entity);
        }
        for (Character entity : enemies){
            moveOrder.add(entity);
        }

        moveOrder.sort((a, b) -> b.SPD - a.SPD);

        return moveOrder;
    }

    public void allyTurn(Character ally, Character enemy){
        options();
            int choice = scan.nextInt();
            if (choice == 1) {
                System.out.println("You attack the goblin!");
                //attack goblin
                attack(ally, enemy);
            }
    }

    public void enemyTurn(Character entity){
        attack(entity, party.get(0)); //enemies attack in party order by default
    }

    // display options for the player
    public void options(){
        System.out.println("\n  1. Attack");
        System.out.println("  2. Use Item");
    }

    // conduct an attack
    public void attack(Character attacker, Character defender){
        // check the attacker's AD and AP and select the higher one
        int damage = attacker.AD;
        int defense = defender.ARM;

        if (attacker.AP > attacker.AD){
            damage = attacker.AP;
            defense = defender.MR;
        }
        
        // calculate damage
        damage -= defense;

        if (damage < 0){ damage = 0; }
        defender.HP -= damage;
        System.out.println(attacker.name + " attacks " + defender.name + " for " + damage + " damage!");
        isDead(defender);
    }

    // method to check if an entity is dead, will be ran after every attack
    public boolean isDead(Character entity){
        if (entity.HP <= 0){
            return true;
        } else{ 
            return false;}
    }
    
}
