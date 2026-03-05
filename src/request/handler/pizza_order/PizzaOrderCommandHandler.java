package request.handler.pizza_order;

import exception.NotFoundException;
import exception.ValidationException;
import request.handler.CommandHandler;
import request.handler.order.sub_command.OrderCommand;
import request.handler.pizza_order.sub_command.PizzaOrderCommand;
import request.handler.pizza_order.sub_command.PizzaOrderSubCommandHandler;

import java.util.Arrays;

public class PizzaOrderCommandHandler extends CommandHandler {
    @Override
    public void handle(String... commandWords) throws ValidationException, NotFoundException {
        if (commandWords.length == 0) {
            System.out.println("Не введена команда для pizza-order");
            return;
        }
        String command = commandWords[0];
        PizzaOrderCommand pizzaOrderCommand = PizzaOrderCommand.fromString(command);
        if (pizzaOrderCommand == null) {
            throw new ValidationException("Некорректная команда: " + command);
        }
        String[] restParameters = Arrays.copyOfRange(commandWords, 1, commandWords.length);
        PizzaOrderSubCommandHandler pizzaOrderSubCommandHandler = pizzaOrderCommand.getPizzaOrderSubCommandHandler();
        pizzaOrderSubCommandHandler.handle(restParameters);
    }
}
