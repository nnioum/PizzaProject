package request.handler.admin.sub_command.handler;

import admin.controller.PizzaController;
import exception.NotFoundException;
import exception.ValidationException;
import admin.model.Pizza;
import request.handler.admin.sub_command.AdminSubCommandHandler;
import request.handler.admin.sub_command.ParamsSpec;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PizzaCommandHandler extends AdminSubCommandHandler {
    private final PizzaController pizzaController = new PizzaController();

    public PizzaCommandHandler() {
        ParamsSpec createParamsSpec = new ParamsSpec();
        createParamsSpec.addRequiredParams(List.of("--name", "--dough", "--ingredients"));
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
            System.out.println("Не указана команда для pizza");
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
                pizzaController.create(params.get("--name"), params.get("--dough"), params.get("--ingredients"));
                System.out.println("Создана пицца " + params.get("--name"));
                break;

            case "edit":
                Set<String> editRequiredParams = paramsSpec.getRequiredParams();
                for (String param : editRequiredParams) {
                    if (!params.containsKey(param)) {
                        System.out.println("Параметр " + param + " обязательный");
                        return;
                    }
                }
                pizzaController.update(params.get("--name"), params.get("--new-name"),
                        params.get("--dough"), params.get("--ingredients"));
                System.out.println("Изменена пицца " + params.get("--name"));
                break;

            case "delete":
                Set<String> deleteRequiredParams = paramsSpec.getRequiredParams();
                for (String param : deleteRequiredParams) {
                    if (!params.containsKey(param)) {
                        System.out.println("Параметр " + param + " обязательный");
                        return;
                    }
                }
                pizzaController.delete(params.get("--name"));
                System.out.println("Удалена пицца " + params.get("--name"));
                break;

            case "get":
                Set<String> getRequiredParams = paramsSpec.getRequiredParams();
                for (String param : getRequiredParams) {
                    if (!params.containsKey(param)) {
                        System.out.println("Параметр " + param + " обязательный");
                        return;
                    }
                }
                Pizza pizza = pizzaController.getByName(params.get("--name"));
                if (pizza == null) {
                    System.out.println("Пицца " + pizza + "не найдена");
                }
                System.out.println(pizza);
                break;

            case "list":
                Set<String> listRequiredParams = paramsSpec.getRequiredParams();
                for (String param : listRequiredParams) {
                    if (!params.containsKey(param)) {
                        System.out.println("Параметр " + param + " обязательный");
                        return;
                    }
                }
                List<String> allNames = pizzaController.getAllNames();
                for (String name : allNames) {
                    System.out.println(name);
                }
                break;

            default:
                System.out.println("Не найдета команда");
        }
    }
}
