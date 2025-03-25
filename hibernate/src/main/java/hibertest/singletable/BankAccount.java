package hibertest.singletable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@DiscriminatorValue("BA")
public class BankAccount extends BillingDetails {

    private int account;

    @Column(name = "bank_name")
    private String bankName;

    private String swift;
}