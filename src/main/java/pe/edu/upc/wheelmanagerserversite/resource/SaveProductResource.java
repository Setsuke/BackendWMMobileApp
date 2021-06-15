package pe.edu.upc.wheelmanagerserversite.resource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveProductResource {

    @Column(unique = true)
    private int rating;

    @Column(unique = true)
    private int units_int_stock;

    @NotNull
    @Size(max = 25)
    @Column(unique = true)
    private String name;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Size(max = 20)
    @Column(unique = true)
    private  Double price;

    @Lob
    @Column(name = "picture", nullable = false)
    private String picture;
}
