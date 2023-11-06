package com.example.timer.dao;

import java.util.List;

import com.example.timer.model.NafathInfo;

public interface NafathInfoDAO {

	List<NafathInfo> get();

	NafathInfo get(int id);

	void save(NafathInfo employee);

	void delete(int id);

}
