package com.zz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zz.dao.RatingDao;
import com.zz.service.RatingService;
@Transactional
@Service 
public class RatingServiceImpl implements RatingService{
	
	@Resource
	private RatingDao ratingDao;
}
