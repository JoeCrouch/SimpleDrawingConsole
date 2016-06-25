package simpledrawingconsole;

public class Canvas {

    private final char[][] canvas;

    public Canvas(int width, int height) {
        canvas = new char[height + 2][];
        fillInitialCanvasIncludingBorder(width, height);
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

    public void addLine(Vector vector1, Vector vector2) {
        char character = 'x';

        Vector directionVector = new Vector(0, 1);

        Vector vector = vector1;
        canvas[vector.getY()][vector.getX()] = character;
        while (!vector.equals(vector2)) {
            vector = vector.add(directionVector);
            canvas[vector.getY()][vector.getX()] = character;
        }
    }
}
