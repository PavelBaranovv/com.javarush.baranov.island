package simulation.entities;

public class Plant extends Entity {
    private final int size;
    public int weight;

    public Plant(int size, int weight) {
        this.size = size;
        this.weight = weight;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}
