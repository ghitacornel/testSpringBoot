package beans.rest.exception;

import beans.rest.exceptions.CustomBusinessException;
import beans.rest.exceptions.CustomBusinessExceptionMarked;
import org.springframework.stereotype.Service;

@Service
public class BusinessExceptionService {

    public void executeAndRaiseBusinessException() {
        throw new CustomBusinessException();
    }

    public void executeAndRaiseBusinessExceptionMarked() {
        throw new CustomBusinessExceptionMarked();
    }

}
