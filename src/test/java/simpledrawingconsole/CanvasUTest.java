package simpledrawingconsole;

import org.junit.Test;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;

public class CanvasUTest {

    @Test
    public void cannotCreateCanvasWithNegativeWidth() {
        try {
            new Canvas(-1, 1);
            fail("Should not be able to create canvas with negative width");
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot make canvas with non positive width or height", e.getMessage());
        }
    }

    @Test
    public void cannotCreateCanvasWithNegativeHeight() {
        try {
            new Canvas(1, -1);
            fail("Should not be able to create canvas with negative height");
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot make canvas with non positive width or height", e.getMessage());
        }
    }

    @Test
    public void cannotCreateCanvasWithZeroWidth() {
        try {
            new Canvas(0, 1);
            fail("Should not be able to create canvas with zero width");
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot make canvas with non positive width or height", e.getMessage());
        }
    }

    @Test
    public void cannotCreateCanvasWithZeroHeight() {
        try {
            new Canvas(1, 0);
            fail("Should not be able to create canvas with zero height");
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot make canvas with non positive width or height", e.getMessage());
        }
    }

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
    public void addingVerticalLineWithinBordersFromTopToBottomUpdatesCanvas() {
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

    @Test
    public void addingVerticalLineWithinBordersFromBottomToTopUpdatesCanvas() {
        int width = 4;
        int height = 10;
        Canvas canvas = new Canvas(width, height);

        canvas.addLine(new Vector(2, 6), new Vector(2, 1));

        String expectedCanvas =
                "------\n" +
                        "| x  |\n" +
                        "| x  |\n" +
                        "| x  |\n" +
                        "| x  |\n" +
                        "| x  |\n" +
                        "| x  |\n" +
                        "|    |\n" +
                        "|    |\n" +
                        "|    |\n" +
                        "|    |\n" +
                        "------\n";

        assertEquals(expectedCanvas, canvas.toString());
    }

    @Test
    public void addingHorizontalLineWithinBordersFromLeftToRightUpdatesCanvas() {
        int width = 10;
        int height = 3;
        Canvas canvas = new Canvas(width, height);

        canvas.addLine(new Vector(1, 2), new Vector(4, 2));

        String expectedCanvas =
                "------------\n" +
                        "|          |\n" +
                        "|xxxx      |\n" +
                        "|          |\n" +
                        "------------\n";

        assertEquals(expectedCanvas, canvas.toString());
    }

    @Test
    public void addingHorizontalLineWithinBordersFromRightToLeftUpdatesCanvas() {
        int width = 10;
        int height = 3;
        Canvas canvas = new Canvas(width, height);

        canvas.addLine(new Vector(4, 2), new Vector(1, 2));

        String expectedCanvas =
                "------------\n" +
                        "|          |\n" +
                        "|xxxx      |\n" +
                        "|          |\n" +
                        "------------\n";

        assertEquals(expectedCanvas, canvas.toString());
    }

    @Test
    public void addingHorizontalLineStartingOutsideFromLeftToRightBordersUpdatesCanvas() {
        int width = 10;
        int height = 10;
        Canvas canvas = new Canvas(width, height);

        canvas.addLine(new Vector(-4, 3), new Vector(3, 3));

        String expectedCanvas =
                "------------\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|xxx       |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "------------\n";

        assertEquals(expectedCanvas, canvas.toString());
    }

    @Test
    public void addingHorizontalLineFinishingOutsideFromRightToLeftBordersUpdatesCanvas() {
        int width = 10;
        int height = 10;
        Canvas canvas = new Canvas(width, height);

        canvas.addLine(new Vector(5, 4), new Vector(-4, 4));

        String expectedCanvas =
                "------------\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|xxxxx     |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "------------\n";

        assertEquals(expectedCanvas, canvas.toString());
    }
}
