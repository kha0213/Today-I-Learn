package com.kaoni.ch06;

public class MainHistogram {
	private static String longText = "What is Lorem Izzpsum Lorem Ipsum is simply dummy text of the printing and typesetting industry Lorem Ipsum has been the industrys standard "
			+ "dummy text ever since the s when an unknown printer took a galley of type and scrambled it to make a type specimen book It has survived not only five centuries "
			+ "but also the leap into electronic typesettzzing remainiqqng essentially unchanged It was popularised in the s with the release of Letraset sheets containing Lorem "
			+ "Ipsum passages and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum Why do we use it It is a long established "
			+ "fact that a reader will be distjracted by the readable content qqof a page when looking at its layout The point of using Lorem Ipsum is that it has a moreorless norma"
			+ "l distribution of letters as opposed to usingqq Content here content here makqqing it look like readable English Many desktop publishing packages and web page editors "
			+ "now use Lorem Ipsum as their default model text and a search for lorem ipsumqqqq will uncover many web sites still in their infancy Various versions have evolved over"
			+ " the years sometimes by accident sometimeszz on purpose injected humour and the like";
	 
	
	public static void main(String[] args) {
		// 객체 생성
		EnglishHistogram histogramClass = new EnglishHistogram();
		// histogram을 위한 배열 추출
		int[] histogram = histogramClass.stringToIntArray(longText);
		
		histogramClass.printHistogram(histogram);
		
	}
}
