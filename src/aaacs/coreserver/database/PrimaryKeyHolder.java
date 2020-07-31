package aaacs.coreserver.database;

import java.io.Serializable;

/**
 * @author Ahmed A. Abd-Allah
 * Created on Oct 29, 2006
 *
 * A very thin, full-of-holes wrapper around primary keys. The user sees 
 * primary keys as Strings, but the underlying form is something else 
 * (a long). Scrambling is NO LONGER IMPLEMENTED at the Core Server; it
 * is left for the outer layers to handle since it is a display issue.
 */
public class PrimaryKeyHolder implements Serializable
{
	// ----- Static Data Members ----------------------------------------------
	/**
	 * Serialization Version Number
	 */
	private static final long serialVersionUID = 1000L;
	/**
	 * RESERVED KEYS:
	 * All primary keys are assumed to start at a certain value, below which is
	 * considered reserved. The actual values are set at the database layer.
	 * These reserved keys are used in many different locations. DO NOT CHANGE 
	 * THEIR VALUES WITHOUT CHANGING THE VALUES WHEN CREATING THE DATABASE 
	 * (SEE THE CREATEDATABASE.SQL SCRIPT).
	 * First we define the limits.
	 */
	public static final long KEY_LOWEST_RESERVED = 1;
	public static final long KEY_HIGHEST_RESERVED = 999;
	/**
	 * The first group is used to refer to specific records that do
	 * NOT refer to a real object in truth. This is as opposed to a
	 * record that was entered in by the user: it refers to a real
	 * object.
	 * (One exception: KEY_UNINITIALIZED but its purpose is obvious.) 
	 * This group is useful for foreign keys.
	 */
	public static final long KEY_UNINITIALIZED = 1;
	public static final long KEY_DEFAULT_VALUES_RECORD = 101;
	public static final long KEY_DELIBERATELY_UNKNOWN_RECORD = 102;
	public static final long KEY_DELIBERATELY_NO_RECORD = 103; // Avoid this when possible!
	/**
	 * The second group is used whilst searching for different keys.
	 * They are also useful for foreign keys.
	 */
	public static final long KEY_MATCHES_ALL_KEYS = 901;
	public static final long KEY_MATCHES_RESERVED_KEYS = 902;
	public static final long KEY_MATCHES_USER_KEYS = 903;

	/*
	 * LEFT HERE FOR HISTORICAL & JUST-IN-CASE REVIEW; THIS CODE IS OBSOLETE.
	 * Matching primary key objects for external use
	 *
	public static final String LOWEST_RESERVED = safeSimpleScramble(KEY_LOWEST_RESERVED);
	public static final String UNINITIALIZED = safeSimpleScramble(KEY_UNINITIALIZED);
	public static final String DEFAULT_VALUES_RECORD = safeSimpleScramble(KEY_DEFAULT_VALUES_RECORD);
	public static final String DELIBERATELY_UNKNOWN_RECORD = safeSimpleScramble(KEY_DELIBERATELY_UNKNOWN_RECORD);
	public static final String DELIBERATELY_NO_RECORD = safeSimpleScramble(KEY_DELIBERATELY_NO_RECORD);
	public static final String MATCHES_ALL_RECORDS = safeSimpleScramble(KEY_MATCHES_ALL_KEYS);
	public static final String MATCHES_RESERVED_RECORDS = safeSimpleScramble(KEY_MATCHES_RESERVED_KEYS);
	public static final String MATCHES_USER_RECORDS = safeSimpleScramble(KEY_MATCHES_USER_KEYS);
	public static final String HIGHEST_RESERVED = safeSimpleScramble(KEY_HIGHEST_RESERVED);
	 */
	
	// ----- Static Methods ---------------------------------------------------
	
	public static boolean isUserSuppliedPrimaryKey(long idkLong)
	{
		if (idkLong > KEY_HIGHEST_RESERVED)
			return true;
		return false;
	}
	
	/*
	 * 	Here are two laughably silly functions for 'scrambling' the underlying longs used
	 * to represent primary keys...the idea is to make it moderately difficult for someone
	 * to randomly enter in a new ID with the hope of touching data they shouldn't be
	 * touching.
	 */
	/*
	public static String mySimpleScramble(long unscrambledNumber)
		throws CoreServerException
	{
		if (!scrambling)
		{
			return String.valueOf(unscrambledNumber);
		}
		
		if (unscrambledNumber < KEY_LOWEST_RESERVED)
			throw new CoreServerException("database.InvalidUnscrambledKey");

		// Input:  BB   BB   BB   BB    : an 8 byte integer value (spaced out for explanation purposes only)
		// Output: NNNNINNNNINNNNINNNNI : a 20 character string
		
		// Now consider every two bytes of the input as four nibbles: nnnn nnnn nnnn nnnn
		// Input:  4 nibbles:    nnnn
		// Output: 5 characters: NNNNI
		// if n==0 ==> N is random uppercase letter --> corresponding position in I is 0
		// if n!=0 ==> N is nth letter of English alphabet, uppercase --> " " " " " is 1
		Random generator = new Random(unscrambledNumber);
		char chars[] = new char[20];
		long fourNibblesMask = 0xFFFF000000000000L;
		short fourNibbles;
		int fourNibblesShiftAmount = 48, oneNibbleMask, oneNibbleShiftAmount;
		byte significantNibblesMask, significantNibbles;
		
		for (int startInChars=0; startInChars<20; startInChars += 5)
		{
			fourNibbles = 
				(short) ((unscrambledNumber & fourNibblesMask) >>> fourNibblesShiftAmount); // highlight four nibbles
			//System.out.printf( "Four Nibbles [%x]: %s\n", fourNibblesMask, Long.toBinaryString(fourNibbles) );

			oneNibbleMask = 0x0000F000;
			oneNibbleShiftAmount = 12;
			significantNibbles = 0;
			significantNibblesMask = (byte) 0x08;
			for (int j=0; j<4; j++)
			{
				byte nibble = (byte) ((fourNibbles & oneNibbleMask) >>> oneNibbleShiftAmount);
				//System.out.printf("(%x) %d ", oneNibbleMask, nibble);
				if (nibble == 0)
					chars[startInChars + j] = (char) (generator.nextInt(26) + 'A');
				else
				{
					chars[startInChars + j] = (char) (nibble + 'A');
					significantNibbles = (byte) (significantNibbles | significantNibblesMask);
				}
				oneNibbleMask = oneNibbleMask >>> 4;
				significantNibblesMask = (byte) (significantNibblesMask >>> 1);
				oneNibbleShiftAmount -= 4;
			}
			//System.out.println();
			chars[startInChars+4] = (char) (significantNibbles + 'A');
			fourNibblesMask = (long) (fourNibblesMask >>> 16);
			fourNibblesShiftAmount -= 16;
		}

		char c;
		for (int i=0; i<5; i++)
		{
			c = chars[9-i]; chars[9-i] = chars[i]; chars[i] = c;
			c = chars[19-i]; chars[19-i] = chars[10+i]; chars[10+i] = c;
		}
		return scrambledKeyPrefix + new String(chars);
	}
	
	protected static String safeMySimpleScramble(long unscrambledNumber)
	{
		try
		{
			return mySimpleScramble(unscrambledNumber);
		}
		catch(Exception e)
		{
			return null;
		}
	}

	public static long mySimpleDescramble(String scrambledString)
		throws CoreServerException
	{
		try
		{
			if (!scrambling)
			{
				Long.parseLong(scrambledString);
			}
	
			if (!scrambledString.startsWith(scrambledKeyPrefix))
				throw new CoreServerException("database.InvalidScrambledKey");

			byte significantNibblesMask, significantNibbles;
			long result = 0;
			char[] chars = scrambledString.substring(scrambledKeyPrefix.length()).toCharArray();
			int nibbleShiftAmount = 60;
	
			char c;
			for (int i=0; i<5; i++)
			{
				c = chars[9-i]; chars[9-i] = chars[i]; chars[i] = c;
				c = chars[19-i]; chars[19-i] = chars[10+i]; chars[10+i] = c;
			}
	
			for (int startInChars=0; startInChars<20; startInChars += 5)
			{
				significantNibbles = (byte) (chars[startInChars+4] - 'A');
				significantNibblesMask = (byte) 0x08;
				//System.out.printf( "significantNibblesMask: %x\n", significantNibbles );
				for (int j=0; j<4; j++)
				{
					//System.out.println( "Nibble: " + fiveChars[j] );
	
					if ( (significantNibbles & significantNibblesMask) > 0 )
					{
						result = result | ((long) (chars[startInChars+j] - 'A')) << nibbleShiftAmount;
					}
					nibbleShiftAmount -= 4;
					significantNibblesMask = (byte) (significantNibblesMask >>> 1);
				}
			}
			return result;
		}
		catch (Exception e)
		{
			throw new CoreServerException("database.InvalidScrambledKey");
		}
	}
	*/
	
	// ----- Instance members -------------------------------------------------
	
	// There is a problem here: the generated value is not being filled into
	// the field after creation... ? As a result, we do not use this class
	// as an embedded class for EmbeddedId's (an EJB 3.0 annotation). This
	// would make the BaseEntity class more elegant, but since I've gone two
	// days without making it work, oh well. It looks like a problem with
	// the Sun 1 Application server - the generated value is recognized but
	// not passed back up after creating a new record. Note that if we do
	// NOT use an embedded class for the primary key (see BaseEntity for
	// that), then everything works fine. Just not as elegantly.
	//
	// @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="qidk")
	//
	/**
	 * We use a long to represent the primary key. From the JSE 6 Tutorial:
	 * "The long data type is a 64-bit two's complement integer. It has a 
	 * minimum value of -9,223,372,036,854,775,808 and a maximum value of 
	 * 9,223,372,036,854,775,807 (inclusive)."
	 */
	/*
	private long pkey;

	public PrimaryKeyHolder()
	{
		markAsUninitialized();
	}
	
	public PrimaryKeyHolder(String s)
	{
		parsePrimaryKey(s);
	}
	*/
	// available only to this class
	/*
	private PrimaryKeyHolder(long inPkey)
	{
		pkey = inPkey;
		if (pkey < KEY_LOWEST_RESERVED)
			markAsUninitialized();
	}

	public PrimaryKeyHolder(PrimaryKeyHolder pk)
	{
		pkey = pk.pkey;
	}
	*/
	/**
	 * Avoid these functions when possible. They should not even be public,
	 * but due to the inability of S1AS to deal with EmbeddedId's autogenerated
	 * values, this class has been ultimately compromised. So the getter and
	 * setter need to be exposed to BaseEntity, and this is not good.<br/>
	 * Instead, try to use toString() and parsePrimaryKey(String). 
	 */
	/*
	public long getPkey() {
		return pkey;
	}
	public void setPkey(long idk) {
		this.pkey = idk;
	}
	*/
	/**
	 * @return A scrambled version of the primary key. If scrambling is turned
	 * off, then a string representation of the underlying type.
	 */
	/*
	public String toString()
	{
		try
		{
			return simpleScramble(pkey); // i.e. support (de)scrambling
		}
		catch (Exception e) {}
		return null;
	}

	public void parsePrimaryKey(String possibleKey)
	{
		try
		{
			// pkey = Long.parseLong(possibleKey); // i.e. don't support (de)scrambling
			pkey = simpleDescramble(possibleKey); // i.e. support (de)scrambling
			if (pkey < KEY_LOWEST_RESERVED)
				markAsUninitialized();
		}
		catch (Exception e)
		{
			markAsUninitialized();
		}		
	}
	*/
	/*
	 * Is this function necessary?
	public String toUnscrambledString()
	{
		return Long.toString(pkey);
	}
	*/
	/*
	public int hashCode()
	{
		return (new Long(pkey)).hashCode();
	}
	
	public boolean equals(Object entity)
	{
		return (((PrimaryKeyHolder) entity).pkey == pkey);
	}
	
	public void markAsUninitialized()
	{
		pkey = KEY_UNINITIALIZED;
	}
	
	public void markAsMatchesAll()
	{
		pkey = KEY_MATCHES_ALL_KEYS;
	}
	
	public boolean isUninitialized()
	{
		return (pkey == KEY_UNINITIALIZED);
	}

	public boolean representsDefaultValuesRecord()
	{
		return (pkey == KEY_DEFAULT_VALUES_RECORD);
	}
	
	public boolean representsDeliberatelyUnknownRecord()
	{
		return (pkey == KEY_DELIBERATELY_UNKNOWN_RECORD);
	}
	
	public boolean representsDeliberatelyNoRecord()
	{
		return (pkey == KEY_DELIBERATELY_NO_RECORD);
	}
	
	public boolean matchesAllKeys()
	{
		return (pkey == KEY_MATCHES_ALL_KEYS);
	}
	
	public boolean matchesReservedKeys()
	{
		return (pkey == KEY_MATCHES_RESERVED_KEYS);
	}
	
	public boolean matchesUserKeys()
	{
		return (pkey == KEY_MATCHES_USER_KEYS);
	}
	
	public boolean isUserKey()
	{
		return (pkey > KEY_HIGHEST_RESERVED);
	}
	*/
}

/*
_ _ _ _ _ _ _ _   _ _ _ _ _ _ _ _
0 ? 1 ? ? R ? ?   ? ? ? ? ? ? ? ?
0 ? 2 ? ? R R ?   ? ? ? ? ? ? ? ?
0 ? 3 ? ? R R ?   R ? ? ? ? ? ? ?
0 ? 4 ? ? R R ?   R R ? ? ? ? ? ?
0 ? 5 ? ? R R ?   R R ? R ? ? ? ?
0 ? 6 ? ? R R ?   R R ? R R ? ? ?
0 ? 7 ? ? R R ?   R R ? R R ? R ?
0 ? 8 ? ? R R ?   R R ? R R ? R R
_ _ _ _ _ _ _ _   _ _ _ _ _ _ _ _

"scrambling"
- determine number of digits (should be up to eight only as long as the ID's are really longs)
- create a string buffer
- put the number of digits in the third byte
- put zero in the first byte
- starting from the sixth byte, put the digits IN REVERSE ORDER two by two into the buffer 
skipping one byte between every pair of digits. Before putting each digit in, add 
its position to it then mod 10.

 */


/*
Here are two laughably silly functions for 'scrambling' the underlying longs used
to represent primary keys...the idea is to make it moderately difficult for someone
to randomly enter in a new ID with the hope of touching data they shouldn't be
touching. All the while making it *possible* for someone who knows the format to
eyeball the scrambled data and extract the real ID with some ease. The real ID
is embedded in the string in reverse order with a small gap...

Scrambling gives a 24 character string of the following form:

(Assuming an input number with a 1 <= length <= 20)

L w M x f e d c   b a 9 8 y z 7 6   5 4 3 2 1 0 u v

L = 10's digit of length
M = 1's digit of length
S = sum of all digits
0-f= either a digit taken from the number in reverse
order if there are remaining digits to take...
or the (sum * position) % 10 where "position" starts at 
24 on the left going down to 1  

w = S * 23 % 9
x = S * 21 % 9
y = S * 12 % 9
z = S * 11 % 9
u = S * 2 % 9
v = S * 1 % 9

Note: as long as primary keys continue to be modeled as
longs, this algorithm should work since longs are up to
20 digits long. 
*/
/*	private static String OLDscramble(long number)
{
	StringBuffer stringBuffer = new StringBuffer("123456781234567812345678"); // 24 chars
	StringBuffer numberAsStringBuffer = new StringBuffer(Long.toHexString(number));
	int length = numberAsStringBuffer.length();
	int i = 0, sum = 0;
	int charsUsedCounter = 0;

	for (i=0; i<length; i++)
	{
		sum += numberAsStringBuffer.charAt(i);
	}

	stringBuffer.setCharAt(0, (char) ('0' + (length/10)));
	stringBuffer.setCharAt(2, (char) ('0' + (length%10)));

	stringBuffer.setCharAt(1, (char) ('0' + ((sum*23) % 9)));
	stringBuffer.setCharAt(3, (char) ('0' + ((sum*21) % 9)));

	numberAsStringBuffer.reverse();

	for (i=4; i<=23; i++)
	{
		switch (i)
		{
		case 12:
		case 13:
		case 22:
		case 23:
			stringBuffer.setCharAt(i, (char) ('0' + ((sum*(24-i)) % 9)));
			break;
		default:
			if (charsUsedCounter < length)
				stringBuffer.setCharAt(i, numberAsStringBuffer.charAt(charsUsedCounter++));
			else
				stringBuffer.setCharAt(i, (char) ('0' + ((sum*(24-i)) % 10)));
			break;									
		}
	}

	return new String(stringBuffer);
}

private static String OLDdescramble(String string)
{
	int i, j=23, charsUsedCounter = 0;

	if (string == null || string.trim().equals(""))
		throw new NumberFormatException();
	StringBuffer stringBuffer = new StringBuffer(string);
	StringBuffer numberAsStringBuffer = new StringBuffer("000000000000000000000000"); // 24 chars
	int length = ((stringBuffer.charAt(0) - '0')*10) + (stringBuffer.charAt(2) - '0');

	for (i=4; i<=23; i++)
	{
		switch (i)
		{
		case 12:
		case 13:
		case 22:
		case 23:
			// skip
			break;
		default:
			if (charsUsedCounter < length)
			{
				numberAsStringBuffer.setCharAt(j--, stringBuffer.charAt(i));
				charsUsedCounter++;
			}
			break;									
		}
	}

	return Long.toString(Long.parseLong(new String(numberAsStringBuffer), 16));
}

	private static String simpleScramble(long l)
	{
		String stringForm = Long.toString(l, Character.MAX_RADIX);
		String size = Integer.toString( stringForm.length(), Character.MAX_RADIX );
		String returnValue = stringForm;
		String oneCharacter = null;

		Random generator = new Random(l);
		for (int i=0; i<13-stringForm.length(); i++)
		{
			oneCharacter = String.valueOf( (char) (generator.nextInt(26) + 97));
			//oneCharacter = String.valueOf( (char) Math.round(Math.random() * 25 + 97) );
			returnValue = oneCharacter + returnValue;
		}
		returnValue = (new StringBuffer(returnValue)).reverse().toString();
		returnValue = (scrambledKeyPrefix + size + returnValue).toUpperCase();
			
		return returnValue;
	}

	private static long simpleDescramble(String s)
	{
		try
		{
			int size = Character.digit(s.charAt(5), Character.MAX_RADIX);
			String stringForm = 
				(new StringBuffer( s.substring(6) ).reverse()).toString();
			stringForm = stringForm.substring(stringForm.length() - size);
			return Long.parseLong(stringForm, Character.MAX_RADIX);
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			return -1;
		}
	}

*/	