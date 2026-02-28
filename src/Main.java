import request.handler.BaseCommand;
import request.handler.CommandHandler;

import java.util.Arrays;
import java.util.Scanner;

import static data.GlobalData.IS_RUNNING;
import static data.GlobalData.WELCOME_MESSAGE;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println(WELCOME_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        while (IS_RUNNING) {
            String line = scanner.nextLine();
            String[] blocks = line.split(" ");
            String command = blocks[0];
            BaseCommand baseCommand = BaseCommand.fromString(command);
            if (baseCommand == null) {
                System.out.println("Некорректная команда: " + command);
                continue;
            }
            CommandHandler commandHandler = baseCommand.getCommandHandler();
            String[] subBlocks = Arrays.copyOfRange(blocks, 1, blocks.length);
            commandHandler.handle(subBlocks);
        }
    }
}