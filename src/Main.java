import exception.ApplicationException;
import request.handler.BaseCommand;
import request.handler.CommandHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static admin.data.GlobalData.IS_RUNNING;
import static admin.data.GlobalData.WELCOME_MESSAGE;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println(WELCOME_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        while (IS_RUNNING) {
            try {
                String line = scanner.nextLine();
                String[] blocks = splitCommandLine(line).toArray(new String[0]);
                if (blocks.length == 0) {
                    System.out.println("Команда не найдена");
                    continue;
                }
                String command = blocks[0];
                BaseCommand baseCommand = BaseCommand.fromString(command);
                if (baseCommand == null) {
                    System.out.println("Некорректная команда: " + command);
                    continue;
                }
                CommandHandler commandHandler = baseCommand.getCommandHandler();
                String[] subBlocks = Arrays.copyOfRange(blocks, 1, blocks.length);
                commandHandler.handle(subBlocks);
            } catch (ApplicationException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private static List<String> splitCommandLine(String line) {
        List<String> tokens = new ArrayList<>();
        if (line == null || line.isBlank()) {
            return tokens;
        }

        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
        boolean escaping = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (escaping) {
                current.append(c);
                escaping = false;
                continue;
            }

            if (inQuotes && c == '\\') {
                escaping = true;
                continue;
            }

            if (c == '"') {
                inQuotes = !inQuotes;
                continue;
            }

            if (!inQuotes && Character.isWhitespace(c)) {
                if (current.length() > 0) {
                    tokens.add(current.toString());
                    current.setLength(0);
                }
                continue;
            }

            current.append(c);
        }

        if (current.length() > 0) {
            tokens.add(current.toString());
        }

        return tokens;
    }
}