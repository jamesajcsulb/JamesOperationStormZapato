//package com.example.james.myapplication;
//
//import static junit.framework.Assert.assertTrue;
//import static org.junit.Assert.assertTrue;
//
//import com.stripe.BaseStripeFunctionalTest;
//import com.stripe.exception.StripeException;
//import com.stripe.model.Account;
//import com.stripe.model.AccountCollection;
//import com.stripe.model.AccountCollectionhttps;//://github.com/stripe/stripe-java;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.regex.Pattern;
//
//import junit.framework.Assert;
//
//import org.junit.Test;
//
////import org.junit.Test;
//
//public class AccountTest extends BaseStripeFunctionalTest {
//    @Test
//    public void testAccountRetrieve() throws StripeException {
//        Account retrievedAccount = Account.retrieve();
//        assertTrue(Pattern.matches("\\A.*@stripe.com\\z", retrievedAccount.getEmail()));
//    }
//
//    @Test
//    public void testGetAllExternalAccounts() throws StripeException {
//        Account account = Account.create(defaultManagedAccountParams);
//        Assert.assertNotNull(account);
//
//        Map<String, Object> accountParams = new HashMap<String, Object>();
//        accountParams.put("limit", 3);
//        AccountCollection accountCollection = Account.all(accountParams);
//
//        Assert.assertNotNull(accountCollection);
//    }
//}
