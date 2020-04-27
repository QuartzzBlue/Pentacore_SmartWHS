package com.controller;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;

public class SolutionController {
	
	public void ConnectR() throws Exception{
		RConnection c = new RConnection();
		c.setStringEncoding("utf8");
		System.out.println("Connection OK");
		c.eval("source('C:/R/workspace/day04/remote.R',encoding='UTF-8')");
		double x = 10.23;
		double y = 5.23;
		REXP rexp = c.eval("r1("+x+","+y+")");
		//REXP re = c.eval("");//R에 접속 후 스크립트 날리겠다 
		double result = rexp.asDouble();
		System.out.println("Result: "+result);
		c.close();
	}
	
}
