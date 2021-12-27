package com.pjrc.junitmockito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NutritionsDto {
	public int carbohydrates;
    public int protein;
    public double fat;
    public int calories;
    public double sugar;
}
