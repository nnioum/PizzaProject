package request.handler;

import request.handler.admin.AdminCommandHandler;
import request.handler.exit.ExitCommandHandler;
import request.handler.help.HelpCommandHandler;

public enum BaseCommands {
    ADMIN("admin", new AdminCommandHandler()),
    EXIT("exit", new ExitCommandHandler()),
    HELP("help", new HelpCommandHandler());

    private final String commandName;
    private final CommandHandler commandHandler;

    BaseCommands(String commandName, CommandHandler commandHandler) {
        this.commandName = commandName;
        this.commandHandler = commandHandler;
    }

    public String getCommandName() {
        return commandName;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public static BaseCommands fromString(String command) {
        for (BaseCommands c : values()) {
            if (c.commandName.equals(command)) {
                return c;
            }
        }
        return null;
    }


}
