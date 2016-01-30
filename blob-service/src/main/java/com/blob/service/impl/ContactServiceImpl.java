package com.blob.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.blob.dao.ContactDao;
import com.blob.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Resource
	ContactDao contactDao;
	
	@Override
	public Long getContactCount() {
		return contactDao.count();
	}

}
