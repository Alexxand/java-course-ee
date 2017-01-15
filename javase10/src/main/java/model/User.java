package model;

import lombok.*;


@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
public class User {
    private String name;
    private String lastName;
    private Role role;
}
