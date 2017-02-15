package villanova.studio.org;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class IssueParser {

    public List<Issue> parseIssues(String jsonContent) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .setFieldNamingPolicy(
                        FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        List<Issue> issues = new ArrayList<Issue>();
        Type collectionType = new TypeToken<List<Issue>>() {
        }.getType();
        issues = gson.fromJson(jsonContent, collectionType);
        return issues;

    }
}
