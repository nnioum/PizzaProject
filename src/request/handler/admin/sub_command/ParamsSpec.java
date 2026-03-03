package request.handler.admin.sub_command;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParamsSpec {
    private final Set<String> requiredParams = new HashSet<>();
    private final Set<String> optionalParams = new HashSet<>();

    public void addRequiredParams(List<String> params){
        requiredParams.addAll(params);
    }

    public void addOptionalParams(List<String> params){
        optionalParams.addAll(params);
    }

    public Set<String> getRequiredParams() {
        return requiredParams;
    }

    public Set<String> getOptionalParams() {
        return optionalParams;
    }
}
