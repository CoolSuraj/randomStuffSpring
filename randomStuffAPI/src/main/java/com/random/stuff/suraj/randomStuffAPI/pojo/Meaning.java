package com.random.stuff.suraj.randomStuffAPI.pojo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Meaning {
	
	private String partOfSpeech;
	private List<Defination> definitions;
}
