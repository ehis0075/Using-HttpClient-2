package net.branchandbound.linkvalidator;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static javax.xml.transform.OutputKeys.VERSION;

public class LinkValidatorAsync {


    private static HttpClient client;

    public static void main(String[] args) throws Exception {

        // httpClient configurations
        //1. setting the http version
//        HttpClient.newBuilder()
//                  .version(HttpClient.Version.HTTP_2)

        //priority settings
//        HttpClient.newBuilder()
//                .priority(1);
//
//        //Redirect
//        HttpClient.newBuilder()
//                .followRedirects(HttpClient.Redirect.NORMAL); // follow redirect
//
//        //HttpClient.Redirect.NEVER
//        //HttpClient.Redirect.ALWAYS
//        //HttpClient.Redirect.NORMAL
//
//        //CONNECTION TIMEOUT
//        HttpClient.newBuilder()
//                .connectTimeout(Duration.ofSeconds(3));
//
//        //custom executor
//        Executor exec = Executors.newCachedThreadPool();
//
//        HttpClient.newBuilder()
//                 .executor(exec);
//
//
//        //BodyPublisher
//        HttpRequest.newBuilder(URI.create("WWW.GTH.COM"))
//                .POST(HttpRequest.BodyPublishers.ofString("payload"))
//                .build();
//
//        //headers
//        HttpRequest.newBuilder(URI.create("..."))
//                .header("Accept", "text/html")
//                .build();
//
//        //cookies
//      //  HttpClient.Builder::setCookieHandler(CookieHandler handler)
//
//        //using the cookieManager class
//        HttpClient.newBuilder()
//                .cookieHandler(
//                        new CookieManager(null,
//                                CookiePolicy.ACCEPT_ALL)
//                )
//                .build();
//

        //using cookies

        CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);

        var client = HttpClient.newBuilder()
                               .cookieHandler(cookieManager)
                               .build();

        HttpResponse res = client.send(
                HttpRequest.newBuilder(URI.create("https://www.google.com"))
                           .build(),
        HttpResponse.BodyHandlers.discarding());

        System.out.println(res);

        List<HttpCookie> cookieList = cookieManager.getCookieStore().getCookies();

        System.out.println(cookieList);

        List<URI> uris = cookieManager.getCookieStore().getURIs();

        System.out.println(uris);







//
//        //former
//
//        client = HttpClient.newBuilder()
//                           .connectTimeout(Duration.ofSeconds(3))
//                           .followRedirects(HttpClient.Redirect.NORMAL)
//                           .build();
//
//        var requests =
//                Files.lines(Path.of("urls.txt"))
//                     .map(LinkValidatorAsync::validateLink)
//                     .collect(Collectors.toList());
//
//        requests.stream()
//                .map(CompletableFuture::join)
//                .forEach(System.out::println);
//    }
//
//    private static CompletableFuture<String> validateLink(String link) {
//        HttpRequest request = HttpRequest.newBuilder(URI.create(link))
//                                .timeout(Duration.ofSeconds(2))
//                                .GET()
//                                .build();
//
//        return client.sendAsync(request, HttpResponse.BodyHandlers.discarding())
//                     .thenApply(LinkValidatorAsync::responseToString)
//                     .exceptionally(e -> String.format("%s -> %s", link, false));
//
//    }
//
//    private static String responseToString(HttpResponse<Void> response) {
//        int status = response.statusCode();
//        boolean success = status >= 200 && status <= 299;
//        return String.format("%s -> %s (status: %s)", response.uri(), success, status);
   }

}
