package sk.ph.model.check;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "check_book")
public class CheckBook extends PanacheEntity {
    public BigDecimal amount;
    public String name;

    @Override
    public String toString() {
        return "CheckBook{" +
                "amount=" + amount +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
