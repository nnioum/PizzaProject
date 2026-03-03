package request.handler.admin.sub_command;

import request.handler.admin.sub_command.handler.DoughCommandHandler;
import request.handler.admin.sub_command.handler.IngredientCommandHandler;
import request.handler.admin.sub_command.handler.PizzaCommandHandler;

public enum AdminCommand {
    INGREDIENT("ingredient", new IngredientCommandHandler()),
    BASE("base", new DoughCommandHandler()),
    PIZZA("pizza", new PizzaCommandHandler());

    private final String command;
    private final AdminSubCommandHandler adminSubCommandHandler;

    AdminCommand(String command, AdminSubCommandHandler adminSubCommandHandler) {
        this.command = command;
        this.adminSubCommandHandler = adminSubCommandHandler;
    }

    public String getCommand() {
        return command;
    }

    public AdminSubCommandHandler getAdminSubCommandHandler() {
        return adminSubCommandHandler;
    }

    public static AdminCommand fromString(String command) {
        for (AdminCommand c : values()) {
            if (c.command.equals(command)) {
                return c;
            }
        }
        return null;
    }
}
