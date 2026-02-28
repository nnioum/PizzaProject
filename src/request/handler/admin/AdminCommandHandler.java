package request.handler.admin;

import request.handler.CommandHandler;
import request.handler.admin.sub_command.AdminCommand;
import request.handler.admin.sub_command.AdminSubCommandHandler;

import java.util.Arrays;

public class AdminCommandHandler extends CommandHandler {

    @Override
    public void handle(String... commandWords) {
        String command = commandWords[0];
        AdminCommand adminCommand = AdminCommand.fromString(command);
        if(adminCommand==null){
            System.out.println("Некорректная команда: "+command);
            return;
        }
        String[] restParameters = Arrays.copyOfRange(commandWords, 1, commandWords.length);
        AdminSubCommandHandler adminSubCommandHandler = adminCommand.getAdminSubCommandHandler();
        adminSubCommandHandler.handle();
    }
}
