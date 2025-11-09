package sk.ph.model.check;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "check")
public class Check extends PanacheEntity {
    public BigDecimal amount;
    public BigDecimal evaluation;

    @ManyToOne
    @JoinColumn(name = "check_book_id", referencedColumnName = "id")
    public CheckBook checkBook;

    @Override
    public String toString() {
        return "Check{" +
                "amount=" + amount +
                ", evaluation=" + evaluation +
                ", checkBook=" + checkBook +
                ", id=" + id +
                '}';
    }
}
