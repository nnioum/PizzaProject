package request.handler.admin.sub_command.handler;

import request.handler.admin.sub_command.AdminSubCommandHandler;

import java.util.*;

public class IngredientCommandHandler extends AdminSubCommandHandler {
    private final Set<String> createRequiredParams = Set.of("--name", "--price");
    private final Set<String> createOptionalParams = Set.of();

    private final Set<String> editRequiredParams = Set.of("--name");
    private final Set<String> editOptionalParams = Set.of("--new-name", "--price");

    private final Set<String> deleteRequiredParams = Set.of("--name");
    private final Set<String> deleteOptionalParams = Set.of();

    private final Set<String> listRequiredParams = Set.of();
    private final Set<String> listOptionalParams = Set.of();

    @Override
    public void handle(String... subWords) {//добавить s
        if (subWords.length == 0) {
            System.out.println("Не указана команда");
            return;
        }
        String resourcesCommand = subWords[0];
        String[] words = Arrays.copyOfRange(subWords, 1, subWords.length);
        Map<String, String> commands = new HashMap<>();
        for (String command : words) {
            String[] blocks = command.split("=");
            String key = blocks[0], value = blocks[1];
            commands.put(key, value);
        }

        switch (resourcesCommand) {
            case "create":
                String name = null, price = null;
                for (String requiredKey : createRequiredParams) {
                    if (commands.containsKey(requiredKey)) {

                    }
                }
        }
    }
}
