package tk.laurenfrost.communicator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "app_user")
public class AppUser implements Serializable {

    @Id
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "owner")
    private List<Food> foods;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Board board;

}
