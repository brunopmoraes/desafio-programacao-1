package com.nexaas;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.nexaas.domain.File;
import com.nexaas.exception.FileStorageException;
import com.nexaas.service.FileStorageService;
import com.nexaas.utils.Converter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DesafioProgramacaoApplicationTests {

	@Autowired
	private FileStorageService service;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testTotalGross() throws Exception {

		FileInputStream inputFile = new FileInputStream("uploads/example_input.tab");
		MockMultipartFile file = new MockMultipartFile("file", inputFile);
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		BufferedReader buf = null;

		String sum = service.storeFile(file);

		Assert.assertEquals("R$ 30,00",sum);
	}
}
