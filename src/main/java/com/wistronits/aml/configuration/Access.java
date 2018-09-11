package com.wistronits.aml.configuration;

import java.lang.annotation.*;

/**
 * @author haibokong
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Access {
	String[] authorities() default {};
	String[] roles() default {};
	String operation() default "";
}
