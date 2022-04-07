package beans.exceptions.service;

import beans.exceptions.exception.CustomBusinessException;
import beans.exceptions.exception.CustomBusinessExceptionMarked;
import org.springframework.stereotype.Service;

@Service
public class CustomBusinessExceptionService {

    public void executeAndRaiseBusinessException() {
        throw new CustomBusinessException();
    }

    public void executeAndRaiseBusinessExceptionMarked() {
        throw new CustomBusinessExceptionMarked();
    }

}
