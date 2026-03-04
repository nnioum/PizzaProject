package request.handler.order.sub_command;


import request.handler.order.sub_command.handler.*;

public enum OrderCommand {
    CREATE("create", new CreateCommandHandler()),
    EDIT("edit", new EditCommandHandler()),
    GET("get", new GetCommandHandler()),
    LIST("list", new ListCommandHandler()),
    SUBMIT("submit", new SubmitCommandHandler());


    private final String command;
    private final OrderSubCommandHandler orderSubCommandHandler;

    OrderCommand(String command, OrderSubCommandHandler orderSubCommandHandler) {
        this.command = command;
        this.orderSubCommandHandler = orderSubCommandHandler;
    }

    public String getCommand() {
        return command;
    }

    public OrderSubCommandHandler getOrderSubCommandHandler() {
        return orderSubCommandHandler;
    }

    public static OrderCommand fromString(String command) {
        for (OrderCommand c : values()) {
            if (c.command.equals(command)) {
                return c;
            }
        }
        return null;
    }
}
