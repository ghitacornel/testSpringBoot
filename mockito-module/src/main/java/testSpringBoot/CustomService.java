package testSpringBoot;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomService {

    private final CustomServiceDependency customServiceDependency;

    public String returnString() {
        return customServiceDependency.returnString();
    }

}
