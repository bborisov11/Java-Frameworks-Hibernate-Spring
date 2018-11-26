package spring.advancedquerying.models.entities;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

public class Game {

    private Long id;
    private String title;
    private String trailer;
    private Double size;
    private BigDecimal price;
    private String description;
    private Date releaseDate;
}
