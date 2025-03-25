package hibertest.tableperclass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@Table(name = "BANK_ACCOUNT")
public class BankAccount extends BillingDetails {

    private int account;

    @Column(name = "bank_name")
    private String bankName;

    private String swift;
}