package request.handler.admin.sub_command.handler;

import admin.controller.DoughController;
import exception.NotFoundException;
import exception.ValidationException;
import admin.model.Dough;
import request.handler.admin.sub_command.AdminSubCommandHandler;
import request.handler.admin.sub_command.ParamsSpec;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DoughCommandHandler extends AdminSubCommandHandler {
    private final DoughController doughController = new DoughController();

    public DoughCommandHandler() {
        ParamsSpec createParamsSpec = new ParamsSpec();
        createParamsSpec.addRequiredParams(List.of("--name", "--price"));
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
            System.out.println("Не указана команда для base");
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
                doughController.create(params.get("--name"), params.get("--price"));
                System.out.println("Создана основа " + params.get("--name"));
                break;

            case "edit":
                Set<String> editRequiredParams = paramsSpec.getRequiredParams();
                for (String param : editRequiredParams) {
                    if (!params.containsKey(param)) {
                        System.out.println("Параметр " + param + " обязательный");
                        return;
                    }
                }
                doughController.update(params.get("--name"), params.get("--new-name"), params.get("--price"));
                System.out.println("Изменена основа " + params.get("--name"));
                break;

            case "delete":
                Set<String> deleteRequiredParams = paramsSpec.getRequiredParams();
                for (String param : deleteRequiredParams) {
                    if (!params.containsKey(param)) {
                        System.out.println("Параметр " + param + " обязательный");
                        return;
                    }
                }
                doughController.delete(params.get("--name"));
                System.out.println("Удалена основа " + params.get("--name"));
                break;

            case "get":
                Set<String> getRequiredParams = paramsSpec.getRequiredParams();
                for (String param : getRequiredParams) {
                    if (!params.containsKey(param)) {
                        System.out.println("Параметр " + param + " обязательный");
                        return;
                    }
                }
                Dough dough = doughController.getByName(params.get("--name"));
                if (dough == null) {
                    System.out.println("Ингредиент " + dough + "не найдена");
                }
                System.out.println(dough);
                break;

            case "list":
                List<String> allNames = doughController.getAllNames();
                for (String name : allNames) {
                    System.out.println(name);
                }
                break;

            default:
                System.out.println("Не найдета команда");
        }
    }
}
