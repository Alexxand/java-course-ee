package model;

import lombok.*;

import java.io.InputStream;
import java.sql.Blob;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Gun {
    private int id;
    String name;
    private double caliber;
    private double rate;
    private InputStream image;
}
