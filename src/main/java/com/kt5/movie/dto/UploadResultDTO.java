package com.kt5.movie.dto;

import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadResultDTO {

	//private static final long serialVersionUID = 1L;
	private String fileName;
	private String uuid;
	private String uploadPath;
	
	//이미지 경로를 리턴해주는 메서드
	public String getImageURL() {
		try {
			//파일에 한글이 있을경우 를 대비해서 UTF-8로 인코딩
			return URLEncoder.encode(uploadPath + "/" + uuid + fileName, "UTF-8");
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return "";
	}	
}
