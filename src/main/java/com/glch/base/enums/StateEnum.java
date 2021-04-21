package com.glch.base.enums;


import java.util.List;

public class StateEnum extends IntEnum {
	private static final long serialVersionUID = -6587269338586923495L;
	
	public static final StateEnum DISABLED = new StateEnum(20, "disabled");
	public static final StateEnum ENABLED = new StateEnum(10, "enable");
	public static final StateEnum DELETE = new StateEnum(-1, "delete");
	
	private StateEnum(int v, String name) {
		super(v, name);
	}
	/*
	 * 必须有无参构造
	 */
	public StateEnum(){
		this(1, "enable");
	}

	public static List<StateEnum> getEnumList(){
		return (List<StateEnum>) getEnumList(StateEnum.class);
	}
	
	public static StateEnum getEnum(Integer v){
		if(v == null){
			return null;
		}
		return (StateEnum)getEnum(StateEnum.class, v);
	}
}
