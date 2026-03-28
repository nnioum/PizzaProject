package request.handler.admin.sub_command;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParamsSpec {
    private final Set<String> requiredParams = new HashSet<>();

    public void addRequiredParams(List<String> params) {
        requiredParams.addAll(params);
    }

    public Set<String> getRequiredParams() {
        return requiredParams;
    }
}
