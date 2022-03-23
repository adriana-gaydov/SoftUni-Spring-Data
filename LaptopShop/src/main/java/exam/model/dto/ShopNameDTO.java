package exam.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class ShopNameDTO {

    @Size(min = 4)
    private String name;

    public String getName() {
        return name;
    }
}
