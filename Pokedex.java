import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class Pokedex {

    private static Random randomizer = new Random();

    public static final String CSVPOKEMON = "C:/Users/Codersbay/IdeaProjects/Pokemon/src/2023-03-13-Pokemon.csv";

    public static final String CSVATTACK = "C:/Users/Codersbay/IdeaProjects/Pokemon/src/2023-04-03-Attacks.csv";

    public HashMap<Integer, Attack> attackMap = new HashMap<>();

    public static HashMap<Integer, Pokemon> pokeMap = new HashMap<>();
    private int randomPrimary, randomSecondary;


    public void loadPokemon() {

        try {
            loadAttacks();
        } catch (IOException e) {
            System.out.println("Load Attacks went wrong .. :(");
        }

        BufferedReader pkmnReader = null;
        try {
            pkmnReader = new BufferedReader(new FileReader(CSVPOKEMON));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            pkmnReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String currentLine;
        try {
            currentLine = pkmnReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (currentLine != null) {
           
            String[] currPkmnData = currentLine.split(";");

            if (currPkmnData.length <= 11) {
                int id = Integer.parseInt(currPkmnData[0]);
                String name = currPkmnData[1];
                String type1 = currPkmnData[2];
                String type2;
                if (currPkmnData[3].isEmpty()) {
                    type2 = null;
                } else {
                    type2 = currPkmnData[3];
                }
                int total = Integer.parseInt(currPkmnData[4]);
                int hp = Integer.parseInt(currPkmnData[5]);
                int attack = Integer.parseInt(currPkmnData[6]);
                int defense = Integer.parseInt(currPkmnData[7]);
                int spAttack = Integer.parseInt(currPkmnData[8]);
                int spDefense = Integer.parseInt(currPkmnData[9]);
                int speed = Integer.parseInt(currPkmnData[10]);

                randomPrimary = randomizer.nextInt(217) + 1;
                randomSecondary = randomizer.nextInt(217) + 1;

                Pokemon pokemon = new Pokemon(id, name, type1, type2, total, hp, attack, defense, spAttack, spDefense, speed, attackMap.get(randomPrimary), attackMap.get(randomSecondary));
                pokeMap.put(id, pokemon);

                try {
                    currentLine = pkmnReader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void loadAttacks() throws IOException {  //loadAttacksFromCsv

        BufferedReader attackReader = new BufferedReader(new FileReader(CSVATTACK));

        attackReader.readLine();
        String currentLine;
        while ((currentLine = attackReader.readLine()) != null) {

            String[] currAttackData = currentLine.split(";");

            if (currAttackData.length < 9) {
                int id = Integer.parseInt(currAttackData[0]);
                String attackName = currAttackData[1];
                String effect = currAttackData[2];
                String type = currAttackData[3];
                String kind = currAttackData[4];
                int power = Integer.parseInt(currAttackData[5]);
                String accuracy = currAttackData[6];
                int pp = Integer.parseInt(currAttackData[7]);

                Attack attack = new Attack(id,attackName,effect,type,kind,power,accuracy,pp);
                attackMap.put(id, attack);
            }
        }
    }

    public static boolean isNumber(String input) {
        try {
            // Versuche den String in eine Zahl zu parsen
            Double.parseDouble(input);
            return true; // Wenn das erfolgreich ist, ist es eine Zahl
        } catch (NumberFormatException e) {
            return false; // Wenn eine Ausnahme ausgelöst wird, ist es kein Zahl
        }
    }

    public Pokemon choosePokemon(String input){  //return pokemon.clone()

        if(isNumber(input)){
            return pokeMap.get(Integer.parseInt(input)).clone();
        } else{
            for (Pokemon pokemon : pokeMap.values()) {                              //verbesserung suche über key mit name (2. hash map mit selber abbildung)
                if (pokemon.getPokemonName().equalsIgnoreCase(input)) {
                    return pokemon.clone();
                }
            }
        }
        return null;
    }

    public Pokemon chooseRandomPokemon() {   //return pokemon.clone()

        Random r = new Random();

        int randomIndex = -1;
        try {
            randomIndex = r.nextInt(pokeMap.size());
            return pokeMap.get(randomIndex).clone();
        } catch (Exception e) {
            System.out.println("Hier ist was schiefgegangen"); // Textmessage -> kein Abbruch
            return null;
        }
    }

    public void printPokemonList(){

        int count = 0;

        for (Integer key : pokeMap.keySet()) {
            Pokemon pokemon = pokeMap.get(key);
            String pokemonName = pokemon.getPokemonName();

            System.out.printf("%-4s - %-12s", key, pokemonName);
            count++;

            if (count % 8 == 0) {
                System.out.println();
            }
        }
    }

    public void printStart(){

        System.out.println("                                                                       ,'\\");
        System.out.println("                                         _.----.        ____         ,'  _\\   ___    ___     ____");
        System.out.println("                                     _,-'       `.     |    |  /`.   \\,-'    |   \\  /   |   |    \\  |`.");
        System.out.println("                                     \\      __    \\    '-.  | /   `.  ___    |    \\/    |   '-.   \\ |  |");
        System.out.println("                                      \\.    \\ \\   |  __  |  |/    ,','_  `.  |          | __  |    \\|  |");
        System.out.println("                                        \\    \\/   /,' _`.|      ,' / / / /   |          ,' _`.|     |  |");
        System.out.println("                                         \\     ,-'/  /   \\    ,'   | \\/ / ,`.|         /  /   \\  |     |");
        System.out.println("                                          \\    \\ |   \\_/  |   `-.  \\    `'  /|  |    ||   \\_/  | |\\    |");
        System.out.println("                                           \\    \\ \\      /       `-.`.___,-' |  |\\  /| \\      /  | |   |");
        System.out.println("                                            \\    \\ `.__,'|  |`-._    `|      |__| \\/ |  `.__,'|  | |   |");
        System.out.println("                                             \\_.-'       |__|    `-._ |              '-.|     '-.| |   |");
        System.out.println("                                                                     `'                            '-._|");
        System.out.println(         );
    }

public void printPikachu(){

    System.out.println("⢰⣶⣶⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
    System.out.println("⠀⢻⣿⣿⡏⠉⠓⠦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀");
    System.out.println("⠀⠀⢹⣿⡇⠀⠀⠀⠈⠙⠲⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⡴⠖⢾⣿⣿⣿⡟");
    System.out.println("⠀⠀⠀⠹⣷⠀⠀⠀⠀⠀⠀⠀⠙⠦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⠶⠚⠋⠁⠀⠀⣸⣿⣿⡟⠀");
    System.out.println("⠀⠀⠀⠀⠹⣇⠀⠀⠀⠀⠀⠀⠀⠀⠈⠓⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡴⠖⠋⠁⠀⠀⠀⠀⠀⠀⠀⣿⣿⠏⠀⠀");
    System.out.println("⠀⠀⠀⠀⠀⠙⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢦⡀⠀⠀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⠀⣀⡤⠖⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⡿⠃⠀⠀⠀");
    System.out.println("⠀⠀⠀⠀⠀⠀⠈⢳⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⡟⠁⠀⠀⠀⠀");
    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠙⢦⡀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠋⠀⠀⠀⠀⠀⠀");
    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣦⣠⡿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⡄⠀⠀⢀⡴⠟⠁⠀⠀⠀⠀⠀⠀⠀");
    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣦⠾⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠏⠀⠀⠀⠀⣠⣴⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡴⣶⣦⡀⠀⠀⠀⠀⠀⠹⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡏⠀⠀⠀⠀⠀⣯⣀⣼⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣄⣬⣿⡇⠀⠀⠀⠀⠀⠀⠘⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠁⠀⠀⠀⠀⠀⠻⣿⡿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠿⠿⠟⠀⠀⠀⠀⠀⠀⠀⠀⢹⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀");
    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⢀⡇⠀⢀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣷⣶⠤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⡀⠀⠀⠀⠀⠀⠀⠀⠀");
    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⢸⢁⡾⠋⠉⠉⠙⢷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⠞⠋⠉⠛⢶⡄⠀⠀⠘⡇⠀⠀⠀⠀⠀⠀⠀⠀");
    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⣿⠸⣇⠀⠀⠀⠀⣸⠇⠀⠀⠀⠀⠀⢀⣠⠤⠴⠶⠶⣤⡀⠀⠀⠀⠀⠀⠀⣇⠀⠀⠀⠀⢀⡇⠀⠀⠀⢿⠀⠀⠀⠀⠀⠀⠀⠀");
    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⢿⠀⠉⠳⠶⠶⠞⠁⠀⠀⠀⠀⠀⠀⢾⡅⠀⠀⠀⠀⠈⣷⠀⠀⠀⠀⠀⠀⠙⠷⢦⡤⠴⠛⠁⠀⠀⠀⢸⡀⠀⠀⠀⠀⠀⠀⠀");
    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠈⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣤⡀⠀⠀⣠⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡇⠀⠀⠀⠀⠀⠀⠀");
    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠛⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣇⠀⠀⠀⠀⠀⠀⠀");
    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⠀⠀⠀⠀⠀⠀⠀");
    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀");

    }



}
