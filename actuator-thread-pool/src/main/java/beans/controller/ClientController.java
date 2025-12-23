package beans.controller;

import beans.clients.feign.FeignContract;
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
    public String ok() {
        return feignContract.invoke("ok") + " processed by app thread " + Thread.currentThread().getName();
    }

    @GetMapping("delay")
    public String delay() {
        return feignContract.invoke("delay") + " processed by app thread " + Thread.currentThread().getName();
    }

}
