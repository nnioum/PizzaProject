package request.handler;

import request.handler.admin.AdminCommandHandler;
import request.handler.exit.ExitCommandHandler;
import request.handler.help.HelpCommandHandler;
import request.handler.order.OrderCommandHandler;
import request.handler.order_pizza.OrderPizzaCommandHandler;

public enum BaseCommand {
    ADMIN("admin", new AdminCommandHandler()),
    EXIT("exit", new ExitCommandHandler()),
    HELP("help", new HelpCommandHandler()),
    ORDER("order", new OrderCommandHandler()),
    ORDER_PIZZA("order-pizza", new OrderPizzaCommandHandler());

    private final String commandName;
    private final CommandHandler commandHandler;

    BaseCommand(String commandName, CommandHandler commandHandler) {
        this.commandName = commandName;
        this.commandHandler = commandHandler;
    }

    public String getCommandName() {
        return commandName;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public static BaseCommand fromString(String command) {
        for (BaseCommand c : values()) {
            if (c.commandName.equals(command)) {
                return c;
            }
        }
        return null;
    }


}
