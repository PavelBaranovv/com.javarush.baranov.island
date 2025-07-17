package simulation.entities;

public abstract class Entity {
    private boolean isAlive = true;

    public abstract int getSize();
    public abstract int getWeight();
    public abstract String getEmoji();

    public boolean isAlive() {
        return isAlive;
    }
    void die() {
        isAlive = false;
    }
}
