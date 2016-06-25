package simpledrawingconsole;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CanvasUTest {

    @Test
    public void constructorCreatesCanvasWithExpectedBorder() {
        int width = 20;
        int height = 4;
        Canvas canvas = new Canvas(width, height);

        String expectedCanvas =
                "----------------------\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------\n";

        assertEquals(expectedCanvas, canvas.toString());
    }

    @Test
    public void addingLineWithStartAndEndPointTheSamePlacesOnePoint() {
        int width = 5;
        int height = 5;
        Canvas canvas = new Canvas(width, height);

        canvas.addLine(new Vector(3, 3), new Vector(3, 3));

        String expectedCanvas =
                "-------\n" +
                        "|     |\n" +
                        "|     |\n" +
                        "|  x  |\n" +
                        "|     |\n" +
                        "|     |\n" +
                        "-------\n";

        assertEquals(expectedCanvas, canvas.toString());
    }

    @Test
    public void addingVerticalLineWithinBordersUpdatesCanvas() {
        int width = 3;
        int height = 10;
        Canvas canvas = new Canvas(width, height);

        canvas.addLine(new Vector(1, 2), new Vector(1, 8));

        String expectedCanvas =
                "-----\n" +
                        "|   |\n" +
                        "|x  |\n" +
                        "|x  |\n" +
                        "|x  |\n" +
                        "|x  |\n" +
                        "|x  |\n" +
                        "|x  |\n" +
                        "|x  |\n" +
                        "|   |\n" +
                        "|   |\n" +
                        "-----\n";

        assertEquals(expectedCanvas, canvas.toString());
    }
}
