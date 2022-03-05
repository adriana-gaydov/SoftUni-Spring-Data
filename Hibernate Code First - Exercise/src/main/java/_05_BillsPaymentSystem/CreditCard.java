package _05_BillsPaymentSystem;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "credit_card")
public class CreditCard extends BillingDetail {

    @Column(name = "card_type", nullable = false, length = 50)
    private String cardType;

    @Column(name = "expiration_month", nullable = false)
    private byte expirationMonth;

    @Column(name = "expiration_year", nullable = false)
    private short expirationYear;

    public CreditCard() {
    }

    public CreditCard(int number, User owner, String cardType, byte expirationMonth, short expirationYear) {
        super(number, owner);
        this.cardType = cardType;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public byte getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(byte expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public short getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(short expirationYear) {
        this.expirationYear = expirationYear;
    }
}
