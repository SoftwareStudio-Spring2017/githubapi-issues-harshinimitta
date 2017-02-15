package villanova.studio.org;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class IssueParserTest {

    IssueParser issueParser = new IssueParser();
    String fileContents = "";

    @Before
    public void testSetUp() {
        File file = new File("sample-output.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currentLine = null;
            while ((currentLine = br.readLine()) != null) {
                fileContents += currentLine;
            }
            System.out.println(fileContents);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testparseIssues() throws ParseException {
        if (fileContents.length() != 0) {
            List<Issue> issues = issueParser.parseIssues(fileContents);
            assertEquals(2, issues.size());
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss'Z'");

            Issue issueOne = issues.get(0);
            assertEquals(205607777, issueOne.getId());
            assertEquals(3, issueOne.getNumber());
            assertEquals("Json Issue", issueOne.getTitle());
            assertEquals("open", issueOne.getState());
            assertEquals("Issue3 created as part of Hw2", issueOne.getBody());
            assertEquals(dateFormat.parse("2017-02-06T14:56:05Z"),
                    issueOne.getCreatedAt());
            assertNull(issueOne.getClosedAt());
            assertEquals(19966199, issueOne.getUser().getId());
            assertEquals("harshinimitta", issueOne.getAssignee().getLogin());

            Issue issueTwo = issues.get(1);
            assertEquals(205607558, issueTwo.getId());
            assertEquals(2, issueTwo.getNumber());
            assertEquals("Rest Client Issue", issueTwo.getTitle());
            assertEquals("open", issueTwo.getState());
            assertEquals("Issue2 created as part of Hw2", issueTwo.getBody());
            assertEquals(dateFormat.parse("2017-02-06T14:55:20Z"),
                    issueTwo.getCreatedAt());
            assertNull(issueTwo.getClosedAt());
            assertEquals(19966199, issueTwo.getUser().getId());
            assertEquals("harshinimitta", issueTwo.getAssignee().getLogin());
        }
    }

}
