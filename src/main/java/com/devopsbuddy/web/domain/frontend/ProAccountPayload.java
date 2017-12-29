package com.devopsbuddy.web.domain.frontend;

/**
 * Created by Jnwanya on
 * Fri, 29 Dec, 2017.
 */
public class ProAccountPayload extends BasicAccountPayload {

    private String cardNumber;
    private String cardCode;
    private String cardYear;
    private String cardMonth;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardYear() {
        return cardYear;
    }

    public void setCardYear(String cardYear) {
        this.cardYear = cardYear;
    }

    public String getCardMonth() {
        return cardMonth;
    }

    public void setCardMonth(String cardMonth) {
        this.cardMonth = cardMonth;
    }
}
