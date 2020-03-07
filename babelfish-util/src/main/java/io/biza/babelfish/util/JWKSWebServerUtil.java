package io.biza.babelfish.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWKSWebServerUtil {

  public static HttpServer serveJwks(int port, String json) throws IOException {
    LOG.info("Creating a JWKS HTTP server on port {} with content of {}", port, json);
    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

    server.createContext("/jwks.json", httpExchange -> {
      LOG.info("Serving jwks content with: {}", json);
      byte response[] = json.getBytes("UTF-8");
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
