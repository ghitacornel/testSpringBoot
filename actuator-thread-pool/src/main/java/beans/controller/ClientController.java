package beans.controller;

import beans.clients.feign.FeignContract;
import beans.clients.model.ClientResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "client")
@RequiredArgsConstructor
class ClientController {

    private final FeignContract feignContract;

    @GetMapping("ok")
    public ClientResponseDto getRequest() {
        return feignContract.invoke("OK");
    }

}
