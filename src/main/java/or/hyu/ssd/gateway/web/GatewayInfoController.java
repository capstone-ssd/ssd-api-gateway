package or.hyu.ssd.gateway.web;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayInfoController {

    @GetMapping("/gateway/info")
    public Map<String, Object> info() {
        return Map.of(
                "service", "ssd-api-gateway",
                "status", "UP",
                "description", "SSD MSA API Gateway");
    }
}
