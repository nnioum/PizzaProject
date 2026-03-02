package request.handler.admin;

import exception.NotFoundException;
import exception.ValidationException;
import request.handler.CommandHandler;
import request.handler.admin.sub_command.AdminCommand;
import request.handler.admin.sub_command.AdminSubCommandHandler;

import java.util.Arrays;

public class AdminCommandHandler extends CommandHandler {

    @Override
    public void handle(String... commandWords) throws ValidationException, NotFoundException {
        if (commandWords.length == 0) {
            System.out.println("Не введена команда для admin");
            return;
        }
        String command = commandWords[0];
        AdminCommand adminCommand = AdminCommand.fromString(command);
        if (adminCommand == null) {
            throw new ValidationException("Некорректная команда: " + command);
        }
        String[] restParameters = Arrays.copyOfRange(commandWords, 1, commandWords.length);
        AdminSubCommandHandler adminSubCommandHandler = adminCommand.getAdminSubCommandHandler();
        adminSubCommandHandler.handle(restParameters);
    }
}
