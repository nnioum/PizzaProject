package request.handler.order.sub_command;

import exception.NotFoundException;
import exception.ValidationException;
import request.handler.admin.sub_command.ParamsSpec;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class OrderSubCommandHandler {
    protected final Map<String, ParamsSpec> paramsSpecByCommand = new HashMap<>();

    public abstract void handle(String... subWord) throws ValidationException, NotFoundException;

    protected Map<String, String> parseParams(String... subLineBlocks) throws ValidationException {
        for (String block : subLineBlocks) {
            if (!block.contains("=") || block.split("=").length <= 1) {
                throw new ValidationException("Некоректна введена команда " + block);
            }
        }
        return Arrays.stream(subLineBlocks)
                .map(s -> s.split("="))
                .collect(Collectors.toMap(a -> a[0], a -> a[1]));
    }
}
