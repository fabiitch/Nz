package com.fabiitch.nz.screens.base;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
public  @interface TestScreenGroup {
    String SEPARATOR = "\\.";

    String group() default "";
}
