package com.github.fabiitch.nz.demo.internal.selectors;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
public @interface DemoScreen {
    String SEPARATOR = "\\.";

    String group() default "";
}
