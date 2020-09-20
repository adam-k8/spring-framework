package com.adam.k8.config;

import com.adam.k8.compent.TulingDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xsls on 2019/7/7.
 */
@Configuration
@ComponentScan(basePackages = {"com.adam.k8.compent"})
public class MainConfig {


	@Bean
	public TulingDataSource tulingDataSource() {
		TulingDataSource tulingDataSource = new TulingDataSource();
		tulingDataSource.setFlag(1);
		return tulingDataSource;
	}

	@Bean
	public TulingDataSource tulingDataSource2() {
		TulingDataSource tulingDataSource = new TulingDataSource();
		tulingDataSource.setFlag(2);
		return tulingDataSource;
	}
}
