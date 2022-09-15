package com.sqs.reggie.dto;

import com.sqs.reggie.entity.Setmeal;
import com.sqs.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
