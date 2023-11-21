package com.example.timer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.timer.dao.NafathInfoDAO;
import com.example.timer.model.NafathInfo;


@Service
public class NafathInfoServiceImpl implements NafathInfoService {
	@Autowired
	private NafathInfoDAO NafathInfoDAO;

	@Transactional
	@Override
	public List<NafathInfo> get() {
		return NafathInfoDAO.get();
	}

	@Transactional
	@Override
	public NafathInfo get(int id) {
		return NafathInfoDAO.get(id);
	}

	@Transactional
	@Override
	public void save(NafathInfo nafathInfo) {
		NafathInfoDAO.save(nafathInfo);
	}

	@Transactional
	@Override
	public void delete(int id) {
		NafathInfoDAO.delete(id);
	}

}