package com.xfatur;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@SelectPackages({ "com.xfatur.service.preco" })
//@IncludeTags("dev")
@SuiteDisplayName("Testing all the class")
@Suite
public class TestAll {

}
