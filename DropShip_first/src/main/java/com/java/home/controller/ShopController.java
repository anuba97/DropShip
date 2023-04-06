package com.java.home.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.home.service.ShopService;
import com.java.vo.ArtistVo;
import com.java.vo.WorkVo;

@Controller
@RequestMapping("shop/")
public class ShopController {
	
	@Autowired
	ShopService shopservice;
	
	@Autowired
	WorkVo workVo;
	
	@Autowired
	ArtistVo artistVo;
	
	
	//////////////////↓  Work(작품) 관련 ↓         /////////////////////////
		
	@GetMapping("painting_list")	// 그림 작품 페이지로 이동
	public String painting_list(@RequestParam(defaultValue = "1") int page, Model model) {  // 이주소로 들어갔을 때 무조건 페이지가 1로뜨게 한다는것.
		Map<String, Object> map = shopservice.selectWorkList(page);
		model.addAttribute("map", map);
		//System.out.println("컨트롤러 DB에서 가져온 map :" + map);
		return "home/shop/painting_list";
	}
	
	@GetMapping("best_list")	// 베스트	작품 페이지로 이동
	public String best_list(WorkVo workVo, Model model) {
		List<WorkVo> list = shopservice.selectWorkBest();
		//System.out.println("controller에서 보내는 list"+list);
		model.addAttribute("list",list);
		//System.out.println("DB에서 받아온 list 값"+list);
		return "home/shop/best_list";
	}
	
	@GetMapping("painting_item")	// 작품 구매창 페이지로 이동
	public String painting_item(int work_id, int artist_id, Model model) {
		workVo = shopservice.selectWorkBuy(work_id); 
		List<WorkVo> list = shopservice.selectWorkArtistAll(artist_id);
		artistVo = shopservice.selectArtistAll(artist_id);
		
		//System.out.println("controller에서 받은 list : "+list);
		model.addAttribute("workVo", workVo); // 작품1개 객체
		model.addAttribute("list", list); // 작품전체 배열  --- 이렇게 보내는 이유는 painting_item에서 forEach를 사용하기위해서 이렇게 사용함
		model.addAttribute("artistVo", artistVo);  // 작가 전체 객체
		return "home/shop/painting_item";
	}
	
	@PostMapping("compare2")  //상품비교
	public String compare2(@RequestParam("work_id_list") String workIds, WorkVo workVo, Model model) {
		System.out.println("넘어온 작품id 리스트 : " + workIds);
	    
		String[] workIdsArr = workIds.split(",");
	    int work_id_first = Integer.parseInt(workIdsArr[0]);
	    int work_id_second = Integer.parseInt(workIdsArr[1]);
	    
	    System.out.println("work_id_first : "+ work_id_first);
	    System.out.println("work_id_second : "+ work_id_second);
	    
	    List<Integer> compare_work_id_list = new ArrayList<>();
	    compare_work_id_list.add(work_id_first);
	    compare_work_id_list.add(work_id_second);

		List<WorkVo> compareWorkVoList = shopservice.selectWorkCompare(compare_work_id_list);
		
		for (WorkVo compareWorkVo : compareWorkVoList) {
			System.out.println(" 아이디값으로 불러온거: "+ compareWorkVo.getId());
		}
		
		
		model.addAttribute("compareWorkVoList", compareWorkVoList);
		
		return "home/shop/compare2";
	}
	//////////////////↓  Artist(작가) 관련 ↓         /////////////////////////
	
	@GetMapping("artist_list")	  // 작가별 페이지로 이동
	public String artist_list(@RequestParam(defaultValue = "1") int page, Model model) {
		Map<String, Object> map = shopservice.selectArtistList(page);
		model.addAttribute("map",map);
		//System.out.println("컨트롤러 DB에서 가져온 map : " + map);
		return "home/shop/artist_list";
	}
	
	@GetMapping("artist_view")	  // 작가별 상세페이지
	public String artist_view(int artist_id, Model model) {
		artistVo = shopservice.selectArtistAll(artist_id); 
		List<WorkVo> list = shopservice.selectWorkArtistAll(artist_id);  
		model.addAttribute("artistVo", artistVo); // 작가 전체  
		model.addAttribute("list",list); // 작가의 작품들  
		//System.out.println("DB에서 받은 artistVo : "+artistVo);
		System.out.println("DB에서 받은 list : "+list);
		
		return "home/shop/artist_view";
	} 
	
	@GetMapping("genre_list")    // 사진 인화
	public String genre_list(Model model) {
		return "home/shop/genre_list";
	} 
	
	@GetMapping("shop/compare")  // 나의 마일리지
	public String compare(Model model) {
		return "shop/compare";
	}
	@GetMapping("shop/compare2")  // 나의 마일리지
	public String compare2(Model model) {
		return "shop/compare2";
	}
	
	
	

	
	
	
}
