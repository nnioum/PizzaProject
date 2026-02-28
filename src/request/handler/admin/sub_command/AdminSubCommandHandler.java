package request.handler.admin.sub_command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AdminSubCommandHandler {
    protected final Map<String, ParamsSpec> paramsSpecByCommand = new HashMap<>();

    public abstract void handle(String... subWord);

    protected Map<String, String> parseParams(String... subLineBlocks) {
        return Arrays.stream(subLineBlocks)
                .map(s -> s.split("="))
                .collect(Collectors.toMap(a -> a[0], a -> a[1]));
    }
}
