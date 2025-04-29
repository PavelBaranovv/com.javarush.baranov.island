package simulation.utils.position;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position addVector(Vector vector) {
        return new Position(x + vector.getDx(), y + vector.getDy());
    }

    public Position limit(Position minPos, Position maxPos) {
        if (minPos.x > maxPos.x || minPos.y > maxPos.y) {
            throw new IllegalArgumentException("maxPos can't be greater than maxPos");
        }

        int limitedX = Math.max(minPos.x, Math.min(maxPos.x, this.x));
        int limitedY = Math.max(minPos.y, Math.min(maxPos.y, this.y));

        return new Position(limitedX, limitedY);
    }
}
