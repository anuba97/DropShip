package com.java.home.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.java.vo.ArtistVo;
import com.java.vo.OptionVo;
import com.java.vo.Order_Detail_inquireVo;
import com.java.vo.Order_Detail_inquire_viewVo;
import com.java.vo.Order_MemberVo;
import com.java.vo.WorkVo;

@Service
public interface ShopService {
	
	//////////////////↓  Work(작품) 관련 ↓         /////////////////////////
	
	Map<String, Object> selectWorkList(int page);  // 작품 그림작품과 page 불러오기
	
	List<WorkVo> selectWorkBest();	// 작품 베스트 가져오기
	
	List<WorkVo> selectWorkNew();  // 작품 new 가져오기
	
	List<WorkVo> selectWorkArtistAll(String artist_id);  // 작가의 작품들 가져오기

	WorkVo selectWorkBuy(int work_id);  // 작품(구매창) 1개 가져오기

	WorkVo selectWorkOneOrder(int work_id);
	
	
	//////////////////↓  Artist(아티스트) 관련 ↓         /////////////////////////
		
	ArtistVo selectArtistAll(String artist_id);  // 작가 전체 가져오기
	
	Map<String, Object> selectArtistList(int page);  // 작가별 작가와 page 불러오기

	// 회원 주문 시 회원 주문 테이블에 주문 정보 저장
	void insertOrder_Member(int member_id, int delivery_id, Order_MemberVo order_memberVo);

	void insertDelivery();

	int selectDeliverySeq();

	int selectOrderMemberSeq();

	void insertOrder_Detail(int order_member_id, int work_id_int, int option_id, int total_price_int);

	void insertOption(OptionVo optionVo);

	int selectOptionSeq();

	Order_MemberVo selectOrderMemberOne_result(int order_member_id);

	// 회원 마이페이지 주문조회 클릭시
	List<Order_Detail_inquireVo> selectOrderDetailByMemberId(int member_id);

	Order_Detail_inquire_viewVo selectOptionOneInquiryView(int order_member_id_int);

	Map<String, List<Integer>> selectOrderDetail(int order_member_id_int);

	List<OptionVo> selectOptionList(List<Integer> optionIdList);

	Map<String, List<? extends Object>> selectMemberWorkList(List<Integer> workIdList);

	
	
	
}