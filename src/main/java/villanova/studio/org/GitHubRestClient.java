package villanova.studio.org;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GitHubRestClient {

    private static final String REQUEST_PARAM_TO_GET_INCLUDING_CLOSED_ISSUES = "?state=closed";
    private static final String HOST_URI = "/repos/SoftwareStudio-Spring2017/githubapi-issues-harshinimitta/issues";

    public String requestIssues(String username, String password,
            boolean getClosedIssues) throws Exception {
        HttpHost target = new HttpHost("api.github.com", 443, "https");
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
        if (!getClosedIssues) {
            url = HOST_URI;
        }
        else {
            url = HOST_URI + REQUEST_PARAM_TO_GET_INCLUDING_CLOSED_ISSUES;
        }
        HttpGet httpget = new HttpGet(url);
        try {

            response = httpclient.execute(target, httpget, localContext);
            System.out.println(response.getStatusLine());

            HttpEntity entity = response.getEntity();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(entity.getContent()));
            jsonContent = reader.readLine();
            EntityUtils.consume(entity);

        }
        catch (ClientProtocolException e) {
            System.out.println("Exception occurred while executing http client request");
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