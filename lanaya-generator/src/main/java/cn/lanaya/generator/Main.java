package cn.lanaya.generator;

import cn.lanaya.generator.config.BasicConfiguration;
import cn.lanaya.generator.config.ExtendGenerator;

public class Main {

	public static void main(String[] args) {
		BasicConfiguration config = new BasicConfiguration("generator.properties");
		ExtendGenerator extend = new ExtendGenerator();
		config.generate(extend);
//		config.generate(null);
		System.out.println("-------------------代码生成完成------------------");
	}

}
