package com.sqs.reggie.dto;

import com.sqs.reggie.entity.Dish;
import com.sqs.reggie.entity.DishFlavor;
import com.sqs.reggie.entity.Dish;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
