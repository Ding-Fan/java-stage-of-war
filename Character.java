public class Character {
    protected int health;
    protected int attackMin;
    protected int attackMax;
    protected String label;

    public Character(int health, int attackMin, int attackMax) {
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
