package request.handler.admin.sub_command;

import exception.NotFoundException;
import exception.ValidationException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AdminSubCommandHandler {
    protected final Map<String, ParamsSpec> paramsSpecByCommand = new HashMap<>();

    public abstract void handle(String... subWord) throws ValidationException, NotFoundException;

    protected Map<String, String> parseParams(String... subLineBlocks) throws ValidationException {
        for (String block : subLineBlocks) {
            if (!block.contains("=") || block.split("=").length <= 1) {
                throw new ValidationException("Некоректна введена команда " + block);
            }
        }
        try {
            return Arrays.stream(subLineBlocks)
                    .map(s -> s.split("="))
                    .peek(a -> {
                        if (a[1].isEmpty()) {
                            throw new IllegalArgumentException("Некоректно передан параметр " + a[0]);
                        }
                    })
                    .collect(Collectors.toMap(a -> a[0], a -> a[1]));
        } catch (IllegalArgumentException e) {
            throw new ValidationException(e.getMessage());
        }

    }
}
