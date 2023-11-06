package com.example.timer.service;

import java.util.List;

import com.example.timer.model.NafathInfo;

public interface NafathInfoService {

	List<NafathInfo> get();

	NafathInfo get(int id);

	void save(NafathInfo employee);

	void delete(int id);

}
