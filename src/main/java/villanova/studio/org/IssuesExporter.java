package villanova.studio.org;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IssuesExporter {

    public static void main(String[] args) {
        IssuesExporter issuesExporter = new IssuesExporter();
        issuesExporter.run();
    }

    private void run() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter your Github Username");
            String login = sc.nextLine();
            System.out.println("Enter your Github password");
            String password = sc.nextLine();
            List<Issue> issuesList = createIssueList();
            System.out.println(issuesList.size());
            writeToFile(issuesList);
        }
    }

    private void writeToFile(List<Issue> issuesList) {
        PrintWriter pw = null;
        File outputFile = new File("./issues.txt");
        try {
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            pw = new PrintWriter(new FileWriter(outputFile));
            for (Issue issue : issuesList) {
                pw.println(issue.toString());
            }
        }
        catch (Exception e) {
            System.out.println("Error occurred while writing to a file");
            e.printStackTrace();
        }
        finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    private List<Issue> createIssueList() {
        Issue issue1 = new Issue();
        Issue issue2 = new Issue();
        Issue issue3 = new Issue();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss'Z'");
        List<Issue> issues = new ArrayList<Issue>();

        User user1 = new User();
        user1.setId(123);
        user1.setLogin("harshini");

        User user2 = new User();
        user2.setId(1234);
        user2.setLogin("mitta");

        issue1.setId(205607777);
        issue1.setNumber(3);
        issue1.setState("Closed");
        issue1.setTitle("Issue1");
        issue1.setAssignee(user1);
        issue1.setUser(user1);

        try {
            issue1.setClosedAt(dateFormat.parse("2017-02-06T14:51:09Z"));
            issue1.setCreatedAt(dateFormat.parse("2017-02-06T14:51:09Z"));
            issue1.setBody("Body");
            issue2.setId(205607558);
            issue2.setNumber(2);
            issue2.setState("Open");
            issue2.setTitle("Issue2");
            issue2.setAssignee(user1);
            issue2.setUser(user2);
            issue2.setClosedAt(dateFormat.parse("2017-02-07T14:51:09Z"));
            issue2.setCreatedAt(dateFormat.parse("2017-02-08T14:51:09Z"));
            issue2.setBody("Body");

            issue3.setId(2302895);
            issue3.setNumber(1);
            issue3.setState("Open");
            issue3.setTitle("Issue3");
            issue3.setAssignee(user2);
            issue3.setUser(user1);
            issue3.setClosedAt(dateFormat.parse("2017-01-07T14:51:09Z"));
            issue3.setCreatedAt(dateFormat.parse("2017-12-08T14:51:09Z"));
            issue3.setBody("Body");
            issues.add(issue1);
            issues.add(issue2);
            issues.add(issue3);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return issues;
    }

}
