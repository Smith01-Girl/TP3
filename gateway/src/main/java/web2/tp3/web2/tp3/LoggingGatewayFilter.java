package web2.tp3.web2.tp3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Component
public class LoggingGatewayFilter implements HandlerFilterFunction<ServerResponse, ServerResponse> {

    private static final Logger log = LoggerFactory.getLogger(LoggingGatewayFilter.class);


    @Override
    public ServerResponse filter(ServerRequest request, HandlerFunction<ServerResponse> next) throws
            Exception {
        // Log request details
        log.info("Incoming Request: Method={}, URI={}, path={}",
                request.method(), request.uri(),request.path());
        // Process the request and get the response
        ServerResponse response = next.handle(request);
        // Log response details
        log.info("Outgoing Response: Status={}",
                response.statusCode());
        return response;
    }
}
