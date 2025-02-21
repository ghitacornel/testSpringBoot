package testSpringBoot;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomService {

    private final CustomServiceHelper helper;

    public String returnString() {
        return helper.returnString();
    }

}
