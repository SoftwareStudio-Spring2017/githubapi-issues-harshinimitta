package villanova.studio.org;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GitHubRestClient {

    private static final String SCHEME = "https";
    private static final String HOSTNAME = "api.github.com";
    //private static final String REQUEST_PARAM_TO_GET_INCLUDING_CLOSED_ISSUES = "state=closed";
    private static final String HOST_URI_PART1 = "/repos/SoftwareStudio-Spring2017/githubapi-issues-";

    public String requestIssues(String username, String password,
            Map<String, String> paramMap) throws Exception {
        HttpHost target = new HttpHost(HOSTNAME, 443, SCHEME);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(target.getHostName(), target.getPort()),
                new UsernamePasswordCredentials(username, password));
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider).build();
        CloseableHttpResponse response = null;
        String jsonContent = "";
        String url = "";
        AuthCache authCache = new BasicAuthCache();
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(target, basicAuth);
        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAuthCache(authCache);
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setPath(HOST_URI_PART1+username+"/issues");
        if (paramMap != null) {
            for (String key : paramMap.keySet()) {
                uriBuilder.setParameter(key, paramMap.get(key));
            }
        }
        URI uri = uriBuilder.build();
        HttpGet httpget = new HttpGet(uri);
        try {
            response = httpclient.execute(target, httpget, localContext);
            System.out.println("Response: "+response.getStatusLine());

            HttpEntity entity = response.getEntity();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(entity.getContent()));
            jsonContent = reader.readLine();
            EntityUtils.consume(entity);
        }
        catch (ClientProtocolException e) {
            System.out.println(
                    "Exception occurred while executing http client request");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("Exception occurred while reading the response");
            e.printStackTrace();
        }
        finally {
            if (response != null) {
                response.close();
            }
            if (httpclient != null) {
                httpclient.close();
            }
        }
        return jsonContent;
    }
}
