package request.handler.exit;

import request.handler.CommandHandler;

import static data.GlobalData.IS_RUNNING;

public class ExitCommandHandler extends CommandHandler {
    @Override
    public void handle(String... commandWords) {
        IS_RUNNING = false;
    }
}
