package sk.ph.model.check;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "config")
public class Config extends PanacheEntity {
    public String key;
    public String value;

    @Override
    public String toString() {
        return "Config{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", id=" + id +
                '}';
    }
}
