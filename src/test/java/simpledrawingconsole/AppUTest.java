package simpledrawingconsole;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AppUTest {

    private PrintStream output = Mockito.mock(PrintStream.class);

    @Test
    public void creatingAndEditingCanvasCorrectlyProducesExpectedOutput() {
        String input =
                "C 11 17\n" +
                        "L 1 1 1 3\n" +
                        "R 2 2 10 9\n" +
                        "B 3 3 o\n" +
                        "Q";
        App app = new App(new Scanner(new ByteArrayInputStream(input.getBytes())), output);
        app.run();

        Canvas canvas = new Canvas(11, 17);
        String expectedInitialCanvasString = canvas.toString();
        verify(output, times(1)).println(expectedInitialCanvasString);

        canvas.addLine(new Vector(1, 1), new Vector(1, 3));
        String expectedCanvasWithLineString = canvas.toString();
        verify(output, times(1)).println(expectedCanvasWithLineString);

        canvas.addRectangle(new Vector(2, 2), new Vector(10, 9));
        String expectedCanvasWithRectangleString = canvas.toString();
        verify(output, times(1)).println(expectedCanvasWithRectangleString);

        canvas.fill(new Vector(3, 3), 'o');
        String expectedCanvasWithFillString = canvas.toString();
        verify(output, times(1)).println(expectedCanvasWithFillString);

        verify(output, times(4)).println(anyString());
    }

    @Test
    public void invalidCommandGivesListOfAvailableCommands() {
        String input =
                "Invalid\n" +
                        "Q";

        App app = new App(new Scanner(new ByteArrayInputStream(input.getBytes())), output);
        app.run();

        String expectedErrorMessage =
                "INVALID is not a valid command. Try one of: \n" +
                        "C\n" +
                        "L\n" +
                        "R\n" +
                        "B\n" +
                        "Q\n";

        verify(output, times(1)).println(expectedErrorMessage);
        verify(output, times(1)).println(anyString());
    }

    @Test
    public void nullCanvasErrorThrowsExceptionMessage() {
        String input =
                "L 1 2 1 4\n" +
                        "Q";

        App app = new App(new Scanner(new ByteArrayInputStream(input.getBytes())), output);
        app.run();

        String expectedErrorMessage = "Canvas must first be initiated try: C 10 10";

        verify(output, times(1)).println(expectedErrorMessage);
        verify(output, times(1)).println(anyString());
    }

    @Test
    public void exceptionsArePrinted() {
        String input =
                "C 10 10\n" +
                        "L 1 2 3 4\n" +
                        "Q";

        App app = new App(new Scanner(new ByteArrayInputStream(input.getBytes())), output);
        app.run();

        Canvas canvas = new Canvas(10, 10);
        String expectedInitialCanvasString = canvas.toString();
        verify(output, times(1)).println(expectedInitialCanvasString);

        String expectedErrorMessage = "IllegalArgumentException: Cannot add diagonal lines only vertical or horizontal";
        verify(output, times(1)).println(expectedErrorMessage);

        verify(output, times(2)).println(anyString());
    }
}
