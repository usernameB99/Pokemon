import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Battle {

    public static void main(String[] args) {

        Pokedex pokedex = new Pokedex();
        pokedex.loadPokemon();
        Scanner sc = new Scanner(System.in);
        //int chosenAttack;
        boolean cont = true;


        Battle battle = new Battle();

        System.out.println("|");

        //pokedex.printPikachu();

        do {

            pokedex.printStart();
            pokedex.printPokemonList();
            System.out.println();
            System.out.println();
            System.out.println("CHOOSE YOUR POKEMON (by nr or name)");
            System.out.print("Input: ");
            String input = sc.nextLine();

            Pokemon playerPokemon = pokedex.choosePokemon(input);
            Pokemon cpuPokemon = null;
            while (cpuPokemon == null) {
                cpuPokemon = pokedex.chooseRandomPokemon();
            }

            System.out.println("you have chosen " + playerPokemon.getPokemonName());
            System.out.println("cpu has chosen " + cpuPokemon.getPokemonName());
            System.out.println("   - - - - - FIGHT - - - - -");

            //whosfirst

            boolean playersTurn = battle.playerFirst(playerPokemon, cpuPokemon);
            do {

                if (playersTurn) {
                    System.out.println("     - - - Players turn - - -");
                    battle.calculateDamage(playerPokemon, cpuPokemon, battle.chooseAttack(playerPokemon));
                    playersTurn = false;
                } else {
                    System.out.println("     - - - cpu turn - - -");
                    battle.calculateDamage(cpuPokemon, playerPokemon, battle.randomAttack());
                    playersTurn = true;
                }

            } while (battle.checkHps(playerPokemon, cpuPokemon));


            System.out.println("wanna play again?\n1.) continue\n2.) quit");
            int resume = sc.nextInt();
            if (resume == 1) {
                cont = true;
                sc.nextLine();
            } else {
                cont = false;
            }
        } while (cont);
        System.out.println("Player one has left the Arena...");
        pokedex.printPikachu();

//        if (pokedex.playerFirst(playerPokemon, cpuPokemon)){               //verbesserung: a=cpu b=player oder b=cpu a =player
//            System.out.println("     - - - Player begins - - -");
//
//            do {
//                System.out.println("* choose your attack");
//                System.out.println("Attack 1: " + playerPokemon.getPrimaryAttack().getAttackName() + " power: " + playerPokemon.getPrimaryAttack().getPower());
//                System.out.println("Attack 2: " + playerPokemon.getSecondaryAttack().getAttackName() + " power: " + playerPokemon.getSecondaryAttack().getPower());
//                chosenAttack = sc.nextInt();
//
//                pokedex.calculateDamageByPlayer(playerPokemon, cpuPokemon, chosenAttack);  //player attacks
//
//                pokedex.checkHps(playerPokemon, cpuPokemon);                               // check hp
//
//                pokedex.calculateDamageByCpu(playerPokemon, cpuPokemon);                  // cpu attacks
//
//                pokedex.checkHps(playerPokemon, cpuPokemon);                              // check hp
//
//            } while (cont);
//        } else {
//            System.out.println("     - - - cpu begins - - -");
//
//            do {
//                pokedex.calculateDamageByCpu(playerPokemon, cpuPokemon);                  // cpu attacks
//                pokedex.checkHps(playerPokemon, cpuPokemon);                              // check hp
//
//                System.out.println("* choose your attack");
//                System.out.println("Attack 1: " + playerPokemon.getPrimaryAttack().getAttackName() + " | power: " + playerPokemon.getPrimaryAttack().getPower());
//                System.out.println("Attack 2: " + playerPokemon.getSecondaryAttack().getAttackName() + " | power: " + playerPokemon.getSecondaryAttack().getPower());
//
//                chosenAttack = sc.nextInt();
//                pokedex.calculateDamageByPlayer(playerPokemon, cpuPokemon, chosenAttack);  //player attacks
//
//                pokedex.checkHps(playerPokemon, cpuPokemon);                               // check hp
//
//            } while (cont);
//        }
    }

    public boolean checkHps(Pokemon playerPokemon, Pokemon cpuPokemon){    //verbesserung: kampf relevante methoden in battle

        System.out.println("- " + playerPokemon.getPokemonName() + " HP = " + playerPokemon.getHp() + " | " + cpuPokemon.getPokemonName() +  " HP = " + cpuPokemon.getHp() + " -");

        if(playerPokemon.getHp() <= 0){
            System.out.println("computer won");
            return false;
        }
        else if (cpuPokemon.getHp() <= 0){
            System.out.println("player won");
            return false;
        }
        return true;

    }

    public int randomAttack(){
        Random r = new Random();
        int randomAttack = r.nextInt(2) + 1;
        return randomAttack;
    }


    public void calculateDamage(Pokemon attackingPokemon, Pokemon defendingPokemon,int chosenAttack) {

        double power;
        double attack = attackingPokemon.getAttack();
        double defense = defendingPokemon.getDefense();
        double hp = defendingPokemon.getHp();

        if (chosenAttack == 1) {
            power = attackingPokemon.getPrimaryAttack().getPower();
            System.out.println(attackingPokemon.getPokemonName() + " uses " + attackingPokemon.getPrimaryAttack().getAttackName());
        } else {
            power = attackingPokemon.getSecondaryAttack().getPower();
            System.out.println(attackingPokemon.getPokemonName() + " uses " + attackingPokemon.getSecondaryAttack().getAttackName());
        }
            double damage = power * (attack / defense) * (1/10.0);
            hp -= damage;
            defendingPokemon.setHp((int) hp);


    }

    public int chooseAttack(Pokemon pokemon){
        Scanner sc = new Scanner(System.in);

        System.out.println("* choose your attack");
        System.out.println("Attack 1: " + pokemon.getPrimaryAttack().getAttackName() + " power: " + pokemon.getPrimaryAttack().getPower());
        System.out.println("Attack 2: " + pokemon.getSecondaryAttack().getAttackName() + " power: " + pokemon.getSecondaryAttack().getPower());
        int chosenAttack = sc.nextInt();

        return chosenAttack;
    }

    public boolean playerFirst(Pokemon playerPokemon, Pokemon cpuPokemon){
        boolean playerFirst;

        if(playerPokemon.getSpeed() > cpuPokemon.getSpeed()){
            playerFirst = true;
        } else if (playerPokemon.getSpeed() == cpuPokemon.getSpeed()){
            Random r = new Random();
            playerFirst = r.nextBoolean();
        } else{
            playerFirst = false;
        }
        return playerFirst;
    }

}
