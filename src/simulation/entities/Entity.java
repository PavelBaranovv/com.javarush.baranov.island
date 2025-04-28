package simulation.entities;

public abstract class Entity {
    private boolean isAlive = true;

    public abstract int getSize();
    public abstract int getWeight();

    public boolean isAlive() {
        return isAlive;
    };
    void kill() {
        isAlive = false;
    };
}
