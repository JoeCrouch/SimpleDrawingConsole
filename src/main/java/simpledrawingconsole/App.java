package simpledrawingconsole;

import com.google.common.collect.ImmutableMap;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class App {

    public static final String COMMAND_REQUEST = "enter command: ";
    public static final String NULL_CANVAS_MESSAGE = "Canvas must first be initiated try: C 10 10";

    private final Scanner in;
    private final PrintStream out;

    private Canvas canvas;
    private boolean quit = false;

    private final Map<String, Command> commands = new ImmutableMap.Builder<String, Command>()
            .put("C", inputs -> canvas = new Canvas(parseInt(inputs[0]), parseInt(inputs[1])))
            .put("L", (inputs) -> canvas.addLine(
                    new Vector(parseInt(inputs[0]), parseInt(inputs[1])),
                    new Vector(parseInt(inputs[2]), parseInt(inputs[3]))
            ))
            .put("R", (inputs) -> canvas.addRectangle(
                    new Vector(parseInt(inputs[0]), parseInt(inputs[1])),
                    new Vector(parseInt(inputs[2]), parseInt(inputs[3]))
            ))
            .put("B", (inputs) -> canvas.fill(
                    new Vector(parseInt(inputs[0]), parseInt(inputs[1])),
                    inputs[2].charAt(0)
            ))
            .put("Q", (inputs) -> quit = true)
            .build();

    public App(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public void run() {
        while (!quit) {
            System.out.print(COMMAND_REQUEST);
            String input = in.nextLine();

            String[] splitInput = input.split(" ");
            String commandName = splitInput[0].toUpperCase();
            String[] commandInputs = Arrays.copyOfRange(splitInput, 1, splitInput.length);

            Command command = commands.get(commandName);
            if (command == null) {
                printAvailableMethodsErrorMessage(commandName);
            } else if (canvas == null && !commandName.equals("C") && !commandName.equals("Q")) {
                out.println(NULL_CANVAS_MESSAGE);
            } else {
                try {
                    command.execute(commandInputs);
                    if (!quit) {
                        out.println(canvas.toString());
                    }
                } catch (Exception e) {
                    out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                }
            }
        }
    }

    private void printAvailableMethodsErrorMessage(String invalidCommand) {
        StringBuilder stringBuilder = new StringBuilder(invalidCommand + " is not a valid command. Try one of: \n");
        for (String commandName : commands.keySet()) {
            stringBuilder.append(commandName).append("\n");
        }
        out.println(stringBuilder.toString());
    }

    public static void main(String[] args) {
        App app = new App(new Scanner(System.in), System.out);
        app.run();
    }
}
