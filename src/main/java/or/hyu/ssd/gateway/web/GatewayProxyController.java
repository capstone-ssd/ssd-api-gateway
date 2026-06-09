package or.hyu.ssd.gateway.web;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class GatewayProxyController {

    private final RestClient restClient = RestClient.builder().build();

    @GetMapping({
            "/auth/**",
            "/members/**",
            "/documents/**",
            "/reviews/**",
            "/ai/**"
    })
    public ResponseEntity<String> proxy(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        String[] segments = requestUri.split("/", 3);

        if (segments.length < 3) {
            return ResponseEntity.notFound().build();
        }

        String servicePrefix = segments[1];
        String downstreamPath = "/" + segments[2];
        String baseUrl = switch (servicePrefix) {
            case "auth" -> "http://ssd-auth-service:8080";
            case "members" -> "http://ssd-member-service:8080";
            case "documents" -> "http://ssd-document-service:8080";
            case "reviews" -> "http://ssd-review-service:8080";
            case "ai" -> "http://ssd-ai-orchestrator:8080";
            default -> null;
        };

        if (baseUrl == null) {
            return ResponseEntity.notFound().build();
        }

        return restClient.get()
                .uri(URI.create(baseUrl + downstreamPath))
                .retrieve()
                .toEntity(String.class);
    }
}
