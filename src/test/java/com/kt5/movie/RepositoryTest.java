package com.kt5.movie;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.kt5.movie.model.Member;
import com.kt5.movie.model.Movie;
import com.kt5.movie.model.MovieImage;
import com.kt5.movie.model.Review;
import com.kt5.movie.repository.MemberRepository;
import com.kt5.movie.repository.MovieImageRepository;
import com.kt5.movie.repository.MovieRepository;
import com.kt5.movie.repository.ReviewRepository;



@SpringBootTest
public class RepositoryTest {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private MovieImageRepository movieImageRepository;
	
	
	//@Test
	//여러개의 데이터를 삽입하므로 모두 성공하거나 실패하도록 하기 위해서 추가
	@Commit
	@Transactional
	public void insertMovie() {
		IntStream.rangeClosed(1,100).forEach(i -> {
			Movie movie = Movie.builder().title("Movie...." +i).build();
			System.out.println("------------------------------------------");
			movieRepository.save(movie);
			
			int count = (int)(Math.random() * 5) + 1; //1,2,3,4
			for(int j = 0; j < count; j++){
				MovieImage movieImage = MovieImage.builder().uuid(UUID.randomUUID().toString())
				.movie(movie).imgName("test"+j+".jpg").build();
				movieImageRepository.save(movieImage);
			}
			System.out.println("===========================================");
		});
	}
	
	@Autowired
	private MemberRepository memberRepository;
	//@Test
	public void insertMember() {
		IntStream.rangeClosed(1,100).forEach(i -> {
			Member member = Member.builder().email("r"+i +"@gmail.com").pw("1111")
			.nickname("reviewer"+i).build();
			memberRepository.save(member);
		});
	}

	@Autowired
	private ReviewRepository reviewRepository;
	//@Test
	public void insertMoviewReview() {
		//100개의 리뷰를 등록
		IntStream.range(1,100).forEach(i -> {
			//영화 번호 존재하는 영화번호
			Long mno = (long)(Math.random()*100) + 1;
			//회원 번호
			Long mid = ((long)(Math.random()*100) + 1 );
			
			Movie movie = Movie.builder().mno(mno).build();
			Member member = Member.builder().mid(mid).build();
			
			Review movieReview = Review.builder().member(member).movie(movie)
			.grade((int)(Math.random()* 5) + 1).text("영화 리뷰..."+i).build();
			reviewRepository.save(movieReview);
		});
	}
	
	//@Test
	public void testListPage(){
		PageRequest pageRequest = PageRequest.of(0,10,Sort.by(Sort.Direction.DESC, "mno"));
		Page<Object[]> result = movieRepository.getListPage(pageRequest);
		for (Object[] objects : result.getContent()) {
			System.out.println(Arrays.toString(objects));
		}
	}

	//@Test
	public void testGetMovieWithAll() {
		List<Object[]> result = movieRepository.getMovieAll(1L);
		System.out.println(result);//배열이라 주소가 나옴
		for (Object[] ar : result) {
			System.out.println(Arrays.toString(ar));
		}
	}

	//@Test
	public void testGetMovieReviews() {
		Movie movie = Movie.builder().mno(42L).build();
		List<Review> list = reviewRepository.findByMovie(movie);
		for(Review review : list ) {
			System.out.println(review.getMember().getEmail());
		};
	}

	@Commit
	@Transactional
	@Test
	//2개의 삭제 구문을 사용하므로 @Transactional과 @Commit을 이용해서 동시에 수행되던가 아니면 하나도
	//되지 않도록 처리
	public void testDeleteMember() {
		Long mid = 41L; //Member의 mid
		Member member = Member.builder().mid(mid).build();
		reviewRepository.deleteByMember(member);
		memberRepository.deleteById(mid);
	}







}
