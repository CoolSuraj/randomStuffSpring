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
public class Result {

	private String word;
	private String phonetic;
	private List<Phonetic> phonetics;
	private String origin;
	private List<Meaning> meanings;
	private License license;
	private List<String> sourceUrls;

}
