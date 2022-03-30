package beans.transactional.controller;

import beans.transactional.service.TransactionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactional")
@RequiredArgsConstructor
public class TransactionalController {

    private final TransactionalService service;

    @GetMapping("validate2WritesSeemsOk")
    public void validate2WritesSeemsOk() {
        service.validate2WritesSeemsOk();
    }

    @GetMapping("validate2TransactionsArePresent")
    public void validate2TransactionsArePresent() {
        service.validate2TransactionsArePresent();
    }

    @GetMapping("validate1TransactionIsPresent")
    public void validate1TransactionIsPresent() {
        service.validate1TransactionIsPresent();
    }

    @GetMapping("validateTransactionRequiresNewIsNotHonoredWhenUsingTHIS")
    public void validateTransactionRequiresNewIsNotHonoredWhenUsingTHIS() {
        service.validateTransactionRequiresNewIsNotHonoredWhenUsingTHIS();
    }

    @GetMapping("validateTransactionRequiresNewIsHonoredWhenUsingSELF")
    public void validateTransactionRequiresNewIsHonoredWhenUsingSELF() {
        service.validateTransactionRequiresNewIsHonoredWhenUsingSELF();
    }

    @GetMapping("validateModifyWithLock/{id}")
    public void validateModifyWithLock(@PathVariable("id") Integer id) {
        service.validateModifyWithLock(id);
    }
}
