package request.handler.admin.sub_command.handler;

import controller.IngredientController;
import request.handler.admin.sub_command.AdminSubCommandHandler;
import request.handler.admin.sub_command.ParamsSpec;

import java.util.*;

public class IngredientCommandHandler extends AdminSubCommandHandler {
    private final IngredientController ingredientController = new IngredientController();

    public IngredientCommandHandler() {
        ParamsSpec createParamsSpec = new ParamsSpec();
        createParamsSpec.addRequiredParams(List.of("--name", "--price"));
        paramsSpecByCommand.put("create", createParamsSpec);

        ParamsSpec editParamsSpec = new ParamsSpec();
        editParamsSpec.addRequiredParams(List.of("--name"));
        editParamsSpec.addOptionalParams(List.of("--new-name", "--price"));
        paramsSpecByCommand.put("edit", editParamsSpec);

        ParamsSpec deleteParamsSpec = new ParamsSpec();
        deleteParamsSpec.addRequiredParams(List.of("--name"));
        paramsSpecByCommand.put("delete", deleteParamsSpec);

        ParamsSpec getParamsSpec = new ParamsSpec();
        getParamsSpec.addRequiredParams(List.of("--name"));
        paramsSpecByCommand.put("get", getParamsSpec);
    }

    @Override
    public void handle(String... subWords) {//добавить s
        if (subWords.length == 0) {
            System.out.println("Не указана команда");
            return;
        }
        String resourcesCommand = subWords[0];
        String[] paramsBlocks = Arrays.copyOfRange(subWords, 1, subWords.length);
        Map<String, String> params = parseParams(paramsBlocks);
        ParamsSpec paramsSpec = paramsSpecByCommand.get(resourcesCommand);

        switch (resourcesCommand) {
            case "create":
                Set<String> requiredParams = paramsSpec.getRequiredParams();
                for (String param : requiredParams) {
                    if (!params.containsKey(param)) {
                        System.out.println("Параметр " + param + " обязательный");
                        return;
                    }
                }
//                ingredientController.create();

        }
    }
}
