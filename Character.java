public class Character {
    public final String LABEL;
    private int health;
    private int attackMin;
    private int attackMax;

    public Character(String label, int health, int attackMin, int attackMax) {
        this.LABEL = label;
        this.health = health;
        this.attackMin = attackMin;
        this.attackMax = attackMax;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int attack() {
        return (int) (Math.random() * (attackMax - attackMin + 1)) + attackMin;
    }
}
