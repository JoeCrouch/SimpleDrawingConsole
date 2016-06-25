package simpledrawingconsole;

public class Vector {
    private final int x;
    private final int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector add(Vector vector) {
        return new Vector(x + vector.x, y + vector.y);
    }

    public Vector subtract(Vector vector) {
        return new Vector(x - vector.x, y - vector.y);
    }

    public Vector simplify() {
        int greatestCommonMultiple = gcm(x, y);
        return new Vector(x / greatestCommonMultiple, y / greatestCommonMultiple);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector vector = (Vector) o;

        return x == vector.x && y == vector.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    private int gcm(int x, int y) {
        int gcm = y == 0 ? x : gcm(y, x % y);
        gcm = gcm == 0 ? 1 : gcm;
        return Math.abs(gcm);
    }
}
