package beans.rest.exception;

import beans.rest.exceptions.CustomBusinessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class BusinessExceptionService {

    public void executeAndRaiseBusinessException() {
        throw new CustomBusinessException();
    }

}
