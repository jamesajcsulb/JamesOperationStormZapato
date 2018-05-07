package com.example.james.myapplication;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.stripe.android.PaymentConfiguration;
import com.stripe.android.Stripe;
import com.stripe.android.exception.StripeException;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;

public class StripeChargeTest extends IntentService {

    public static final String TOKEN_ACTION = "com.stripe.example.service.tokenAction";
    public static final String STRIPE_CARD_LAST_FOUR = "com.stripe.example.service.cardLastFour";
    public static final String STRIPE_CARD_TOKEN_ID = "com.stripe.example.service.cardTokenId";
    public static final String STRIPE_ERROR_MESSAGE = "com.stripe.example.service.errorMessage";

    private static final String EXTRA_CARD_NUMBER = "com.stripe.example.service.extra.cardNumber";
    private static final String EXTRA_MONTH = "com.stripe.example.service.extra.month";
    private static final String EXTRA_YEAR = "com.stripe.example.service.extra.year";
    private static final String EXTRA_CVC = "com.stripe.example.service.extra.cvc";

    public static Intent createTokenIntent(
            @NonNull Activity launchingActivity,
            @Nullable String cardNumber,
            @Nullable Integer month,
            @Nullable Integer year,
            @Nullable String cvc) {
        return new Intent(launchingActivity, StripeChargeTest.class)
                .putExtra(EXTRA_CARD_NUMBER, cardNumber)
                .putExtra(EXTRA_MONTH, month)
                .putExtra(EXTRA_YEAR, year)
                .putExtra(EXTRA_CVC, cvc);
    }

    public StripeChargeTest() {
        super("TokenIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String errorMessage = null;
        Token token = null;
        if (intent != null) {
            String cardNumber = intent.getStringExtra(EXTRA_CARD_NUMBER);
            Integer month = (Integer) intent.getExtras().get(EXTRA_MONTH);
            Integer year = (Integer) intent.getExtras().get(EXTRA_YEAR);
            String cvc = intent.getStringExtra(EXTRA_CVC);

            Card card = new Card(cardNumber, month, year, cvc);

            Stripe stripe = new Stripe(this);
            try {
                token = stripe.createTokenSynchronous(card,
                        PaymentConfiguration.getInstance().getPublishableKey());
            } catch (StripeException stripeEx) {
                errorMessage = stripeEx.getLocalizedMessage();
            }
        }

        Intent localIntent = new Intent(TOKEN_ACTION);
        if (token != null) {
            localIntent.putExtra(STRIPE_CARD_LAST_FOUR, token.getCard().getLast4());
            localIntent.putExtra(STRIPE_CARD_TOKEN_ID, token.getId());
        }

        if (errorMessage != null) {
            localIntent.putExtra(STRIPE_ERROR_MESSAGE, errorMessage);
        }

        // Broadcasts the Intent to receivers in this app.
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }

}
/*
    Card card = new Card(
            "4242424242424242",
            01,
            2019,
            "123"
    );

                card.validateNumber();
                card.validateCVC();

    Stripe stripe = new Stripe(new IntentService("StripeKing") {
        @Override
        protected void onHandleIntent(Intent intent) {

            String errorMessage = null;
            Token token = null;
            try {
                token = stripe.createTokenSynchronous(card,
                        PaymentConfiguration.getInstance().getPublishableKey());

                //Toast.makeText(getContext(), "tokgot", Toast.LENGTH_LONG).show();
                System.out.printf("HELLOOOOOO", token);

                stripeFunction.charge(token);

            } catch (StripeException stripeEx) {
                errorMessage = stripeEx.getLocalizedMessage();
            }
        }
    });
}
*/