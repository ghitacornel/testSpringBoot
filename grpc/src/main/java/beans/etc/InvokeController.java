package beans.etc;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "invoke")
@RestController
@RequiredArgsConstructor
public class InvokeController {

    private final BusinessService businessService;

    @GetMapping("grpc")
    public ResponseDto invokeService() {
        return businessService.invokeExternal();
    }

}
