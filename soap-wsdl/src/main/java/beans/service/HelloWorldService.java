package beans.service;

import com.blog.samples.webservices.accountservice.Account;
import com.blog.samples.webservices.accountservice.AccountDetailsRequest;
import com.blog.samples.webservices.accountservice.AccountDetailsResponse;
import com.blog.samples.webservices.accountservice.EnumAccountStatus;
import com.briansjavablog.accounts.Accounts;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService implements Accounts {

    @Override
    public AccountDetailsResponse getAccountDetails(AccountDetailsRequest parameters) {

        Account account = new Account();
        account.setAccountNumber(parameters.getAccountNumber());
        account.setAccountBalance(123);
        account.setAccountName("custom account name");
        account.setAccountStatus(EnumAccountStatus.ACTIVE);

        AccountDetailsResponse response = new AccountDetailsResponse();
        response.setAccountDetails(account);
        return response;

    }
}
