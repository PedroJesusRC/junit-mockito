package com.pjrc.junitmockito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FruityviceDto {
	public String genus;
    public String name;
    public int id;
    public String family;
    public String order;
    public NutritionsDto nutritions;
}
