package simpledrawingconsole;

public class Canvas {

    private final int width;
    private final int height;
    private final char[][] canvas;

    public Canvas(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Cannot make canvas with non positive width or height");
        }

        this.width = width;
        this.height = height;

        canvas = new char[height + 2][];
        fillInitialCanvasIncludingBorder(width, height);
    }

    public void addLine(Vector vector1, Vector vector2) {
        // The logic below should actually be able to handle diagonal lines but part of the brief was to not yet support this.
        if (isDiagonalLine(vector1, vector2)) {
            throw new IllegalArgumentException("Cannot add diagonal lines only vertical or horizontal");
        }

        char character = 'x';

        Vector directionVector = vector2.subtract(vector1).simplify();

        Vector vector = vector1;
        addVectorPointInsideCanvas(vector, character);
        while (!vector.equals(vector2)) {
            vector = vector.add(directionVector);
            addVectorPointInsideCanvas(vector, character);
        }
    }

    public void addRectangle(Vector topCorner1, Vector bottomCorner1) {
        Vector topCorner2 = new Vector(bottomCorner1.getX(), topCorner1.getY());
        Vector bottomCorner2 = new Vector(topCorner1.getX(), bottomCorner1.getY());

        addLine(topCorner1, topCorner2);
        addLine(topCorner1, bottomCorner2);
        addLine(bottomCorner1, bottomCorner2);
        addLine(topCorner2, bottomCorner1);
    }

    public void fill(Vector vector, char colour) {
        int y = vector.getY();
        int x = vector.getX();
        if (canvas[y][x] != ' ') {
            return;
        }

        canvas[y][x] = colour;

        fill(new Vector(x + 1, y), colour);
        fill(new Vector(x - 1, y), colour);
        fill(new Vector(x, y + 1), colour);
        fill(new Vector(x, y - 1), colour);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (char[] line : canvas) {
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.toString();
    }

    private void fillInitialCanvasIncludingBorder(int width, int height) {
        char[] horizontalBorder = horizontalLine('-', width + 2).toCharArray();
        canvas[0] = horizontalBorder;
        canvas[height + 1] = horizontalBorder;

        char[] centerLine = ("|" + horizontalLine(' ', width) + "|").toCharArray();
        for (int line = 1; line <= height; line++) {
            canvas[line] = centerLine.clone();
        }
    }

    private String horizontalLine(char character, int width) {
        StringBuilder lineBuilder = new StringBuilder();
        for (int w = 0; w < width; w++) {
            lineBuilder.append(character);
        }
        return lineBuilder.toString();
    }

    private boolean isDiagonalLine(Vector vector1, Vector vector2) {
        boolean isHorizontalLine = vector1.getY() == vector2.getY();
        boolean isVerticalLine = vector1.getX() == vector2.getX();
        return !isHorizontalLine && !isVerticalLine;
    }

    private void addVectorPointInsideCanvas(Vector vector, char character) {
        int y = vector.getY();
        int x = vector.getX();

        if (x > 0 && x <= width && y > 0 && y <= height) {
            canvas[y][x] = character;
        }
    }
}
