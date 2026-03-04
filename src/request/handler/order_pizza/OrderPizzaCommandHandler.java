package request.handler.order_pizza;

import exception.NotFoundException;
import exception.ValidationException;
import request.handler.CommandHandler;
import request.handler.order.sub_command.OrderCommand;
import request.handler.order.sub_command.OrderSubCommandHandler;

import java.util.Arrays;

public class OrderPizzaCommandHandler extends CommandHandler {
    @Override
    public void handle(String... commandWords) throws ValidationException, NotFoundException {
        if (commandWords.length == 0) {
            System.out.println("Не введена команда для order");
            return;
        }
        String command = commandWords[0];
        OrderCommand orderCommand = OrderCommand.fromString(command);
        if (orderCommand == null) {
            throw new ValidationException("Некорректная команда: " + command);
        }
        String[] restParameters = Arrays.copyOfRange(commandWords, 1, commandWords.length);
        OrderSubCommandHandler orderSubCommandHandler = orderCommand.getOrderSubCommandHandler();
        orderSubCommandHandler.handle(restParameters);
    }
}
