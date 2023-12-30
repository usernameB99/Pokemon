public class Attack {

    private int id;
    private String attackName;
    private String effect;
    private String type;
    private String kind;
    private int power;
    private String accuracy;
    private int pp;

    public Attack(int id, String attackName, String effect, String type, String kind, int power, String accuracy, int pp) {
        this.id = id;
        this.attackName = attackName;
        this.effect = effect;
        this.type = type;
        this.kind = kind;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
    }

    public int getId() {
        return id;
    }

    public String getAttackName() {
        return attackName;
    }

    public String getEffect() {
        return effect;
    }

    public String getType() {
        return type;
    }

    public String getKind() {
        return kind;
    }

    public int getPower() {
        return power;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public int getPp() {
        return pp;
    }
}
