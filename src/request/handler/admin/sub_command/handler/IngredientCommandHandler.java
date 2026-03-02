package request.handler.admin.sub_command.handler;

import controller.IngredientController;
import model.Ingredient;
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
            System.out.println("Не указана команда для ingredient");
            return;
        }
        String resourcesCommand = subWords[0];
        String[] paramsBlocks = Arrays.copyOfRange(subWords, 1, subWords.length);
        Map<String, String> params = parseParams(paramsBlocks);
        ParamsSpec paramsSpec = paramsSpecByCommand.get(resourcesCommand);

        switch (resourcesCommand) {
            case "create":
                Set<String> createRequiredParams = paramsSpec.getRequiredParams();
                for (String param : createRequiredParams) {
                    if (!params.containsKey(param)) {
                        System.out.println("Параметр " + param + " обязательный");
                        return;
                    }
                }
                ingredientController.create(params.get("--name"), params.get("--price"));
                System.out.println("Создан ингредиент " + params.get("--name"));
                break;

            case "edit":
                Set<String> editRequiredParams = paramsSpec.getRequiredParams();
                for (String param : editRequiredParams) {
                    if (!params.containsKey(param)) {
                        System.out.println("Параметр " + param + " обязательный");
                        return;
                    }
                }
                ingredientController.update(params.get("--name"), params.get("--new-name"), params.get("--price"));
                System.out.println("Изменен ингредиент " + params.get("--name"));
                break;

            case "delete":
                Set<String> deleteRequiredParams = paramsSpec.getRequiredParams();
                for (String param : deleteRequiredParams) {
                    if (!params.containsKey(param)) {
                        System.out.println("Параметр " + param + " обязательный");
                        return;
                    }
                }
                ingredientController.delete(params.get("--name"));
                System.out.println("Удален ингредиент " + params.get("--name"));
                break;

            case "get":
                Set<String> getRequiredParams = paramsSpec.getRequiredParams();
                for (String param : getRequiredParams) {
                    if (!params.containsKey(param)) {
                        System.out.println("Параметр " + param + " обязательный");
                        return;
                    }
                }
                Ingredient ingredient = ingredientController.getByName(params.get("--name"));
                if (ingredient == null) {
                    System.out.println("Ингредиент " + ingredient + "не найден");
                }
                System.out.println(ingredient);
                break;

            case "list":
                List<String> allNames = ingredientController.getAllNames();
                for (String name : allNames) {
                    System.out.println(name);
                }
                break;

            default:
                System.out.println("Не найдета команда " + resourcesCommand);
        }
    }
}
