package mini.totobank.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String accountNumber;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String grade;

    @Column
    private Integer balance;

    public void deposit(Integer money) {
        this.balance += money;
    }

    public void withdraw(Integer money) throws Exception {
        if (this.balance < money) throw new Exception("잔액 부족");
        this.balance -= money;
    }

}
