package request.handler.help;

import request.handler.CommandHandler;

import static admin.data.GlobalData.*;

public class HelpCommandHandler extends CommandHandler {
    @Override
    public void handle(String... commandWords) {
        if (commandWords.length == 0) {
            System.out.println(
                    HELP_BASE +
                    HELP_ADMIN + HELP_ADMIN_DOUGH +
                    HELP_ADMIN_INGREDIENT +
                    HELP_ADMIN_CRUST +
                    HELP_ADMIN_PIZZA +
                    HELP_ORDER + HELP_PIZZA_ORDER
            );
        } else if (commandWords[0].equals("admin")) {
            if (commandWords.length == 1) {
                System.out.println(HELP_ADMIN);
            } else {
                String command = commandWords[1];
                switch (command) {
                    case "dough":
                        System.out.println(HELP_ADMIN_DOUGH);
                        break;
                    case "ingredient":
                        System.out.println(HELP_ADMIN_INGREDIENT);
                        break;
                    case "crust":
                        System.out.println(HELP_ADMIN_CRUST);
                        break;
                    case "pizza":
                        System.out.println(HELP_ADMIN_PIZZA);
                        break;
                    default:
                        System.out.println("Некорректная команда: " + command);
                }
            }
        } else if (commandWords[0].equals("order")) {
            System.out.println(HELP_ORDER);
        } else if (commandWords[0].equals("pizza-order")) {
            System.out.println(HELP_PIZZA_ORDER);
        } else {
            System.out.println("Некорректная команда: " + commandWords[0]);
        }
    }
}
