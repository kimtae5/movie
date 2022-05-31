package com.kt5.movie.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonBooleanFormatVisitor.Base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
//다른Entity에 포함될수 있다.라는 의미의 어노테이션
//여러 개로 구성되어 있지만 하나의 값 처럼 사용한다는 의미입니다.
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "movie") //연관 관계시 항상 주의
public class MovieImage extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long inum;
	private String uuid;
	private String imgName;
	private String path;
	@ManyToOne(fetch = FetchType.LAZY) //무조건 lazy로
	//JPA에서 Movie 객체를 연결하지만 데이터베이스 
	private Movie movie;
}
