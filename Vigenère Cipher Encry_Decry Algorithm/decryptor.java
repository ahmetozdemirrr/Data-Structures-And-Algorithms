import java.util.Map;
import java.util.Iterator;

public class decryptor 
{
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text = "";
	private String cipher_text;
	
	public decryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) /* constructor method */
	{
		this.map = _map;
		this.key = _key;
		this.cipher_text = text;
	}

	public void decrypt() 
	{
		// do not edit this method
		generate_keystream();
		generate_plain_text();
	}
	
	private void generate_keystream() 
	{
		while (keystream.length() < cipher_text.length()) 
		{
            keystream += key; /* equalise lenght of both side */
        }
        keystream = keystream.substring(0, cipher_text.length()); /* we cut off the excess part to equalise it to the length of the plaintext */
	}
	
	private void generate_plain_text() 
	{
		// You must use map.get(x).keySet() with an iterator in this method

		for (int i = 0; i < cipher_text.length(); i++) 
		{
			/* we get the current character of the ciphertext and the decryption text */
            char cipherCharacter = cipher_text.charAt(i);
            char keyChararacter = keystream.charAt(i);

            /* we use iterator for this method */
            Iterator<Character> rowIterator = map.get(keyChararacter).keySet().iterator();

            while (rowIterator.hasNext()) /* return as long as the next element exists */
            {
                char rowKey = rowIterator.next();

                /* when a matching value is found, take it and append it to the transcribed text */
                if (map.get(rowKey).get(keyChararacter) == cipherCharacter) 
                {
                    plain_text += rowKey;
                    break;
                }
            }
        }
	}

	public String get_keystream() 
	{
		return this.keystream;
	}
	
	public String get_plain_text() 
	{
		return this.plain_text;
	}
}
