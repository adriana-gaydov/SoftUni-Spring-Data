package _05_BillsPaymentSystem;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "bank_account")
public class BankAccount extends BillingDetail {

    @Column(name = "bank_name", nullable = false, length = 80)
    private String bankName;

    @Column(name = "swift_code", nullable = false, length = 20)
    private String swiftCode;

    public BankAccount() {
    }

    public BankAccount(int number, User owner, String bankName, String swiftCode) {
        super(number, owner);
        this.bankName = bankName;
        this.swiftCode = swiftCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
}
