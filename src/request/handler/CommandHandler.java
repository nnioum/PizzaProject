package request.handler;

import exception.NotFoundException;
import exception.ValidationException;

public abstract class CommandHandler {

    public abstract void handle(String... commandWords) throws ValidationException, NotFoundException;
}
