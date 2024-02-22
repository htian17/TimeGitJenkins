package time;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {

	//good,bad,boundary total seconds
	@Test
	public void testGetTotalSecondsGood() {
		int seconds = Time.getTotalSeconds("05:05:05:006");
		assertTrue("The seconds were not calculated properly", seconds==18305);
	}

	@Test
	public void testGetTotalSecondsBad() {
		assertThrows(StringIndexOutOfBoundsException.class,()->{Time.getTotalSeconds("10:00");});
	}

    @Test
    public void testGetTotalSecondsBoundary() {
        int seconds = Time.getTotalSeconds("00:00:00:000");
        assertEquals(0, seconds, "The seconds were not calculated properly for zero time");
    }
	
    //good bad boundary for seconds
    @Test
    public void testGetSecondsGood() {
        int seconds = Time.getSeconds("12:34:56:789");
        assertEquals(56, seconds, "The seconds were not extracted properly");
    }
    
    @Test
    public void testGetSecondsBad() {
        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            Time.getSeconds("12:34");
        });
    }

    @Test
    public void testGetSecondsBoundary() {
        int seconds = Time.getSeconds("00:00:00:000");
        assertEquals(0, seconds, "The seconds were not extracted properly for zero time");
    }
   
    // good, bad, boundary totalminutes
    @Test
    public void testGetTotalMinutesGood() {
        int minutes = Time.getTotalMinutes("02:30:45:678");
        assertEquals(30, minutes, "The minutes were not calculated properly");
    }


    @Test
    public void testGetTotalMinutesBad() {
        assertThrows(NumberFormatException.class, () -> {
            Time.getTotalMinutes("invalid");
        });
    }	
    
    @Test
    public void testGetTotalMinutesBoundary() {
        int minutes = Time.getTotalMinutes("00:00:01:000");
        assertEquals(0, minutes, "The minutes were not calculated properly for zero time");
    }


    
    //good, bad, boundary totalhours
    
    @ParameterizedTest
    @ValueSource(strings = {"00:01:00:000", "12:30:45:123"})
    void testGetTotalHours(String candidate) {
        if (candidate.equals("00:01:00:000")) {
            int hours = Time.getTotalHours(candidate);
            assertEquals(0, hours, "The hours were not calculated properly for zero time");
        } else {
            int expectedHours = Integer.parseInt(candidate.split(":")[0]);
            int hours = Time.getTotalHours(candidate);
            assertEquals(expectedHours, hours, "The hours were not calculated properly");
        }
    }

    @Test
    public void testGetTotalHoursBad() {
        assertThrows(NumberFormatException.class, () -> {
            Time.getTotalHours("invalid");
        });
    }
    
    //good, bad, boundary for testGetMilliseconds
    @Test
    public void testGetMillisecondsGood() {
        int milliseconds = Time.getMilliseconds("12:05:05:006");
        assertEquals(6, milliseconds, "The milliseconds were not calculated properly");
    }
    
    @Test
    public void testGetMillisecondsBad() {
        assertThrows(NumberFormatException.class, () -> {
            Time.getMilliseconds("12:05:05:abc");
        });
    }

    @Test
    public void testGetMillisecondsBoundary() {
        int milliseconds = Time.getMilliseconds("12:05:05:999");
        assertEquals(999, milliseconds, "The milliseconds were not calculated properly for maximum time");
    }

    


}
