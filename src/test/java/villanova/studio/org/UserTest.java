package villanova.studio.org;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserTest {

    User user1 = new User();
    User user2 = new User();

    @Test
    public void testEqualsWhenEqual() {
        user1.setId(20);
        user2.setId(20);
        assertTrue(user1.equals(user2));
    }

    @Test
    public void testEqualsWhenNotEqual() {
        user1.setId(20);
        user2.setId(21);
        assertFalse(user1.equals(user2));

    }

    @Test
    public void testHashcodeRepeatedInvocation() {
        int hashcode1 = user1.hashCode();
        int hashcode2 = user2.hashCode();
        assertEquals(hashcode1, hashcode2);
    }

    @Test
    public void testHashcode() {
        user1.setId(40);
        user2.setId(40);
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    public void testToStringDefaultUser() {
        User defaultUser = new User();
        String result = "User [login=null, id=0]";
        assertEquals(result, defaultUser.toString());
    }

    @Test
    public void testToStringPartiallyPopulated() {
        User userPartiallyPopulated = new User();
        userPartiallyPopulated.setLogin("Harshini");
        String result = "User [login=Harshini, id=0]";
        assertEquals(result, userPartiallyPopulated.toString());
    }

    @Test
    public void testToStringFullyPopulated() {
        User userFullyPopulated = new User();
        userFullyPopulated.setId(10);
        userFullyPopulated.setLogin("Harshini");
        String result = "User [login=Harshini, id=10]";
        assertEquals(result, userFullyPopulated.toString());
    }
}
