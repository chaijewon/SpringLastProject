package com.sist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.mapper.FoodMapper;
import com.sist.vo.FoodVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService{
    private final FoodMapper mapper;
    

	@Override
	public List<FoodVO> foodListData(int start, int end) {
		// TODO Auto-generated method stub
		return mapper.foodListData(start, end);
	}

	@Override
	public int foodTotalPage() {
		// TODO Auto-generated method stub
		return mapper.foodTotalPage();
	}

	@Override
	public FoodVO foodDetailData(int no) {
		// TODO Auto-generated method stub
		mapper.foodHitIncrement(no);
		return mapper.foodDetailData(no);
	}

	@Override
	public List<FoodVO> foodHit7Data() {
		// TODO Auto-generated method stub
		return mapper.foodHit7Data();
	}
    
    
}
