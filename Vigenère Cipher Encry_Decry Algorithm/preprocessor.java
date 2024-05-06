public class preprocessor 
{
	private String initial_string;
	private String preprocessed_string;
		
	public preprocessor(String str) /* constructure method */
	{
		this.initial_string = str;
	}

	public void preprocess() /* constructure method */
	{
		// do not edit this method
		capitalize();
		clean();
	}
	
	/* 
		Since the letter ı cannot be handled as invalid in 
		the clean method, İ put a special check here
	*/
	private void capitalize() 
	{
	    char[] chars = this.initial_string.toCharArray();

	    for (int i = 0; i < chars.length; i++) 
	    {
	        if (chars[i] == 'ı') 
	        {
	            chars[i] = 'İ'; /* The reason ı translated it to İ is only to be perceived as a mistake. */
	        } 

	        else 
	        {
	            chars[i] = Character.toUpperCase(chars[i]);
	        }
	    }
	    this.initial_string = new String(chars);
	}

	private void clean() /* we clean letters that are not suitable for the table */
	{
		this.preprocessed_string = this.initial_string.replaceAll("[^A-Z]", "");
	}
	
	public String get_preprocessed_string() 
	{
		return this.preprocessed_string;
	}
}