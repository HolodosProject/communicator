package tk.laurenfrost.communicator.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "board")
public class Board implements Serializable {

    @Id
    private String macAddress;

    @OneToOne(mappedBy = "board")
    private AppUser owner;

}
