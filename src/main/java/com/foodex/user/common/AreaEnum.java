package com.foodex.user.common;

public enum AreaEnum {
	CHENNAI_MEDAVAKKAM(100100), CHENNAI_PALLIKARANAI(100101);
	
	private int value;
	
    private AreaEnum(int value){
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
