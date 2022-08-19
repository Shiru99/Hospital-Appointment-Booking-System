package get.sterlite.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class Hello {
	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}
}
