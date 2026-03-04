package request.handler.pizza_order.sub_command;

import request.handler.pizza_order.PizzaOrderCommandHandler;
import request.handler.pizza_order.sub_command.handler.CreatePizzaOrderCommandHandler;
import request.handler.pizza_order.sub_command.handler.EditPizzaOrderCommandHandler;
import request.handler.pizza_order.sub_command.handler.GetPizzaOrderCommandHandler;
import request.handler.pizza_order.sub_command.handler.ListPizzaOrderCommandHandler;

public enum PizzaOrderCommand {
    CREATE("create", new CreatePizzaOrderCommandHandler()),
    EDIT("edit", new EditPizzaOrderCommandHandler()),
    GET("get", new GetPizzaOrderCommandHandler()),
    LIST("list", new ListPizzaOrderCommandHandler());

    private final String command;
    private final PizzaOrderSubCommandHandler pizzaOrderSubCommandHandler;

    PizzaOrderCommand(String command, PizzaOrderSubCommandHandler pizzaOrderSubCommandHandler) {
        this.command = command;
        this.pizzaOrderSubCommandHandler = pizzaOrderSubCommandHandler;
    }

    public String getCommand() {
        return command;
    }

    public PizzaOrderSubCommandHandler getPizzaOrderSubCommandHandler() {
        return pizzaOrderSubCommandHandler;
    }

    public static PizzaOrderCommand fromString(String command) {
        for (PizzaOrderCommand c : values()) {
            if (c.command.equals(command)) {
                return c;
            }
        }
        return null;
    }
}
