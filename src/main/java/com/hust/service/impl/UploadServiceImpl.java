package com.hust.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

import com.hust.service.UploadService;
import com.hust.util.ExcelReader;

@Service
public class UploadServiceImpl implements UploadService {
	/**
	 * Logger for this class
	 */
	private static final Logger LOG = LoggerFactory.getLogger(UploadServiceImpl.class);

	@Override
	public List<String[]> readDataFromExcel(InputStream is) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("readDataFromExcel(InputStream) - start"); //$NON-NLS-1$
		}
		// TODO Auto-generated method stub
		List<String[]> list = null;
		try {
			list = ExcelReader.read(is);
		} catch (Exception e) {
			LOG.error("readDataFromExcel(InputStream)", e); //$NON-NLS-1$
			return null;
		}
		return list;
	}

}
