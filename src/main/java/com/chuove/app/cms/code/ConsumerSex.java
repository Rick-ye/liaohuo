package com.chuove.app.cms.code;

public enum ConsumerSex {
	//通过括号赋值,而且必须带有一个参构造器和一个属性跟方法，否则编译出错
    //赋值必须都赋值或都不赋值，不能一部分赋值一部分不赋值；如果不赋值则不能写构造器，赋值编译也出错
    MALE("option1","男"), 
    FEMALE("option2","女");
    
	private final String key;
    private final String value;

    //构造器默认也只能是private, 从而保证构造函数只能在内部使用
    ConsumerSex(String key, String value) {
    	this.key = key;
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public String getKey(){
    	return key;
    }
    
    public static void main(String[] args) {
		System.out.println(MALE.getKey());
	}
}
