package villanova.studio.org;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class IssueTest {

    Issue issue1 = new Issue();
    Issue issue2 = new Issue();
    Issue defaultIssue;

    @Test
    public void testEqualsWhenEqual() {
        issue1.setId(20);
        issue2.setId(20);
        assertTrue(issue1.equals(issue2));
    }

    @Test
    public void testEqualsWhenNotEqual() {
        issue1.setId(20);
        issue2.setId(21);
        assertFalse(issue1.equals(issue2));
    }

    @Test
    public void testHashcodeRepeatedInvocation() {
        int hashcode1 = issue1.hashCode();
        int hashcode2 = issue1.hashCode();
        assertEquals(hashcode1, hashcode2);
    }

    @Test
    public void testHashcode() {
        issue1.setId(40);
        issue2.setId(40);
        assertEquals(issue1.hashCode(), issue2.hashCode());
    }

    @Test
    public void testToStringDefaultIssue() {
        defaultIssue = new Issue();
        String result = "Issue [number=0, id=0, state=null, title=null, body=null, createdAt=null, "
                + "closedAt=null, user=null, assignee=null]";
        assertEquals(result, defaultIssue.toString());
    }

    @Test
    public void testToStringPartiallyPopulated() {
        defaultIssue = new Issue();
        defaultIssue.setId(100);
        defaultIssue.setNumber(2);
        defaultIssue.setTitle("Hw2");
        defaultIssue.setBody("First Issue");
        defaultIssue.setState("Open");
        String result = "Issue [number=2, id=100, state=Open, title=Hw2, body=First Issue, createdAt=null, "
                + "closedAt=null, user=null, assignee=null]";
        assertEquals(result, defaultIssue.toString());
    }

    @Test
    public void testToStringFullyPopulated() throws ParseException {
        Issue issueFullPopulated = new Issue();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MMdd'T'HH:mm:ss'Z'");
        User user = new User();
        user.setId(8963);
        user.setLogin("mitta");
        issueFullPopulated.setId(1000);
        issueFullPopulated.setNumber(3);
        issueFullPopulated.setTitle("Hw2");
        issueFullPopulated.setBody("Full Issue");
        issueFullPopulated.setState("Open");
        issueFullPopulated
                .setCreatedAt(dateFormat.parse("2017-02-06T14:51:09Z"));
        issueFullPopulated
                .setClosedAt(dateFormat.parse("2017-02-06T14:51:09Z"));
        issueFullPopulated.setUser(user);
        issueFullPopulated.setAssignee(user);

        String result = "Issue [number=3, id=1000, state=Open, title=Hw2, body=Full Issue, createdAt=Wed Jan 25 14:51:09 EST 2017, "
                + "closedAt=Wed Jan 25 14:51:09 EST 2017, user=User [login=mitta, id=8963], assignee=User [login=mitta, id=8963]]";
        assertEquals(result, issueFullPopulated.toString());
    }

}
