package com.blob.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.blob.dao.ContactDao;

@Service
public class ContactServiceImpl implements IContactService {

	@Resource
	ContactDao contactDao;
	
	@Override
	public Long getContactCount() {
		return contactDao.count();
	}

}
