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
                        "----------------------";

        assertEquals(expectedCanvas, canvas.toString());
    }
}
