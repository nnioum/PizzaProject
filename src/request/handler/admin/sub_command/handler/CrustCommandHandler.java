package request.handler.admin.sub_command.handler;

import admin.controller.CrustController;
import admin.model.Crust;
import exception.NotFoundException;
import exception.ValidationException;
import request.handler.admin.sub_command.AdminSubCommandHandler;
import request.handler.admin.sub_command.ParamsSpec;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CrustCommandHandler extends AdminSubCommandHandler {

    private final CrustController crustController = new CrustController();

    public CrustCommandHandler() {
        ParamsSpec createParamsSpec = new ParamsSpec();
        createParamsSpec.addRequiredParams(List.of("--name", "--ingredients", "--allowed-pizzas"));
        paramsSpecByCommand.put("create", createParamsSpec);

        ParamsSpec editParamsSpec = new ParamsSpec();
        editParamsSpec.addRequiredParams(List.of("--name"));
        paramsSpecByCommand.put("edit", editParamsSpec);

        ParamsSpec deleteParamsSpec = new ParamsSpec();
        deleteParamsSpec.addRequiredParams(List.of("--name"));
        paramsSpecByCommand.put("delete", deleteParamsSpec);

        ParamsSpec getParamsSpec = new ParamsSpec();
        getParamsSpec.addRequiredParams(List.of("--name"));
        paramsSpecByCommand.put("get", getParamsSpec);

        ParamsSpec listParamsSpec = new ParamsSpec();
        paramsSpecByCommand.put("list", listParamsSpec);
    }

    @Override
    public void handle(String... subWords) throws ValidationException, NotFoundException {
        if (subWords.length == 0) {
            System.out.println("Не указана команда для crust");
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
                Set<String> ingredients = Set.of(params.get("--ingredients").split(","));
                Set<String> allowedPizzas = Set.of(params.get("--allowed-pizzas").split(","));
                crustController.create(params.get("--name"), params.get("--price"), ingredients, allowedPizzas);
                System.out.println("Создан бортик " + params.get("--name"));
                break;

            case "edit":
                Set<String> editRequiredParams = paramsSpec.getRequiredParams();
                for (String param : editRequiredParams) {
                    if (!params.containsKey(param)) {
                        System.out.println("Параметр " + param + " обязательный");
                        return;
                    }
                }
                Set<String> ingredientsToUpdate = params.containsKey("--ingredients") ?
                        Set.of(params.get("--ingredients").split(",")) : null;
                Set<String> allowedPizzasToUpdate = params.containsKey("--allowed-pizzas") ?
                        Set.of(params.get("--allowed-pizzas").split(",")) : null;
                crustController.update(params.get("--name"), params.get("--price"), params.get("--new-name"), ingredientsToUpdate, allowedPizzasToUpdate);
                System.out.println("Изменен бортик " + params.get("--name"));
                break;

            case "delete":
                Set<String> deleteRequiredParams = paramsSpec.getRequiredParams();
                for (String param : deleteRequiredParams) {
                    if (!params.containsKey(param)) {
                        System.out.println("Параметр " + param + " обязательный");
                        return;
                    }
                }
                crustController.delete(params.get("--name"));
                System.out.println("Удален бортик " + params.get("--name"));
                break;

            case "get":
                Set<String> getRequiredParams = paramsSpec.getRequiredParams();
                for (String param : getRequiredParams) {
                    if (!params.containsKey(param)) {
                        System.out.println("Параметр " + param + " обязательный");
                        return;
                    }
                }
                Crust crust = crustController.getByName(params.get("--name"));
                System.out.println(crust);
                break;

            case "list":
                Set<String> listRequiredParams = paramsSpec.getRequiredParams();
                for (String param : listRequiredParams) {
                    if (!params.containsKey(param)) {
                        System.out.println("Параметр " + param + " обязательный");
                        return;
                    }
                }
                List<String> allNames = crustController.getAllNames();
                for (String name : allNames) {
                    System.out.println(name);
                }
                break;

            default:
                System.out.println("Не найдета команда");
        }
    }
}
