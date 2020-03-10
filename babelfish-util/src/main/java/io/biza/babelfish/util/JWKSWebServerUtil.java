package io.biza.babelfish.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;
import io.biza.babelfish.oidc.payloads.JWKS;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWKSWebServerUtil {

  public static HttpServer serveJwks(int port, JWKS jwks) throws IOException {
    
    ObjectMapper mapper = new ObjectMapper();
    
    LOG.info("Creating a JWKS HTTP server on port {} with content of {}", port, mapper.writeValueAsString(jwks));
    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

    server.createContext("/jwks.json", httpExchange -> {
      LOG.info("Serving jwks content with: {}", mapper.writeValueAsString(jwks));
      byte response[] = mapper.writeValueAsString(jwks).getBytes("UTF-8");
      httpExchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
      httpExchange.sendResponseHeaders(200, response.length);
      OutputStream out = httpExchange.getResponseBody();
      out.write(response);
      out.close();
    });

    server.start();
    return server;
  }

}
