package com.example.james.myapplication.activities;

import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.AccountCollection;

import junit.framework.Assert;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static junit.framework.Assert.assertTrue;

//import org.junit.Test;

public class AccountTestMain extends BaseStripeFunctionalTestMain {
    public void testAccountRetrieve() throws StripeException {
        Account retrievedAccount = Account.retrieve();
        assertTrue(Pattern.matches("\\A.*@stripe.com\\z", retrievedAccount.getEmail()));
    }

    public void testGetAllExternalAccounts() throws StripeException {
        Account account = Account.create(BaseStripeFunctionalTestMain.defaultManagedAccountParams);
        Assert.assertNotNull(account);

        Map<String, Object> accountParams = new HashMap<String, Object>();
        accountParams.put("limit", 3);
        AccountCollection accountCollection = Account.all(accountParams);

        Assert.assertNotNull(accountCollection);
    }
}
