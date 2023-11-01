public class Pokemon implements Cloneable{

    private int id;
    private String pokemonName;
    private String type1;
    private String type2;
    private int total;
    private int hp;
    private int attack;
    private int defense;
    private int spAttack;
    private int spDefense;
    private int speed;
    private Attack primaryAttack;
    private Attack secondaryAttack;


    public Pokemon(int id, String pokemonName, String type1, String type2, int total, int hp, int attack, int defense, int spAttack, int spDefense, int speed, Attack primaryAttack, Attack secondaryAttack) {
        this.id = id;
        this.pokemonName = pokemonName;
        this.type1 = type1;
        this.type2 = type2;
        this.total = total;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.spAttack = spAttack;
        this.spDefense = spDefense;
        this.speed = speed;
        this.primaryAttack = primaryAttack;
        this.secondaryAttack = secondaryAttack;
    }
    public Pokemon(Pokemon pkmn) {
        this.id = pkmn.id;
        this.pokemonName = pkmn.pokemonName;
        this.type1 = pkmn.type1;
        this.type2 = pkmn.type2;
        this.total = pkmn.total;
        this.hp = pkmn.hp;
        this.attack = pkmn.attack;
        this.defense = pkmn.defense;
        this.spAttack = pkmn.spAttack;
        this.spDefense = pkmn.spDefense;
        this.speed = pkmn.speed;
        this.primaryAttack = pkmn.primaryAttack;
        this.secondaryAttack = pkmn.secondaryAttack;
    }
    public Pokemon(Pokemon pkmn, Attack primaryAttack, Attack secondaryAttack){
        this.id = pkmn.getId();
        this.primaryAttack = primaryAttack;
        this.secondaryAttack = secondaryAttack;

    }

    @Override
    public Pokemon clone(){
        return new Pokemon(this.id, this.pokemonName,this.type1,this.type2,this.total,this.hp,this.attack,this.defense,this.spAttack,this.spDefense,this.speed,this.primaryAttack,this.secondaryAttack);
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getId() {
        return id;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }

    public int getTotal() {
        return total;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpAttack() {
        return spAttack;
    }

    public int getSpDefense() {
        return spDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public Attack getPrimaryAttack() {
        return primaryAttack;
    }

    public Attack getSecondaryAttack() {
        return secondaryAttack;
    }

    public void setPrimaryAttack(Attack primaryAttack) {
        this.primaryAttack = primaryAttack;
    }

    public void setSecondaryAttack(Attack secondaryAttack) {
        this.secondaryAttack = secondaryAttack;
    }
}
