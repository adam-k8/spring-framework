package com.adam.k8.setInject;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by xsls on 2019/8/26.
 */
public class TulingImportSelector implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{"com.adam.k8.setInject.InstE"};
	}
}
