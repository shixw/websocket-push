package cc.shixw.wsp.wsp.web;

import cc.shixw.wsp.wsp.WspWebServerApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WspWebServerApplication.class);
	}

}

