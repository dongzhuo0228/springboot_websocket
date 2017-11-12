package com.example;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.mapping.Column;
import org.hibernate.sql.ordering.antlr.ColumnMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.mapper.UserMapper;
import com.github.pagehelper.PageHelper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringHelloworldApplicationTests {
     
	/*
	 @Resource  
	    private ColumnMapper columnMapper;  
	    @Test  
	    public void test(){  
	        PageHelper.startPage(1, 20); // 核心分页代码  
	        List<Column> cls =  columnMapper.selectAll();  
	        for (Column column : cls) {  
	            System.out.println(column.getBrcoName());  
	        }  
	    } */ 
	
	@Autowired
	private UserMapper userDao;
	@Test
	public void contextLoads() {
	}

}
