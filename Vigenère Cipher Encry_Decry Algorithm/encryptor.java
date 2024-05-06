import java.util.Map;

public class encryptor 
{
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text;
	private String cipher_text = "";
	
	public encryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) /* constructor method */
	{
		this.map = _map;
		this.key = _key;
		this.plain_text = text;
	}
	
	public void encrypt() 
	{
		// do not edit this method
		generate_keystream();
		generate_cipher_text();
	}
	
	private void generate_keystream() 
	{
		while (keystream.length() < plain_text.length())
		{
			keystream += key; /* equalise lenght of both side */
		}
		keystream = keystream.substring(0, plain_text.length()); /* we cut off the excess part to equalise it to the length of the plaintext */
	}
	
	private void generate_cipher_text() 
	{
		for (int i = 0; i < plain_text.length(); i++) 
		{
			/* the values in the matrix are taken for the co-indexes */
			char plainCharacter = plain_text.charAt(i);
			char keyCharacter = keystream.charAt(i);

			cipher_text += map.get(plainCharacter).get(keyCharacter); /* encryption in progress */
		}
	}

	public String get_keystream() 
	{
		return this.keystream;
	}
	
	public String get_cipher_text() 
	{
		return this.cipher_text;
	}
}

