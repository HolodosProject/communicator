package tk.laurenfrost.communicator.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "board")
public class Board implements Serializable {

    @Id
    private String macAddress;

    @OneToMany(mappedBy = "board")
    private List<Food> foodList;

}
