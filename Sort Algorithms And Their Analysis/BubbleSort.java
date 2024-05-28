public class BubbleSort extends SortAlgorithm 
{
	public BubbleSort(int input_array[]) 
    {
		super(input_array);
	}
	
    @Override
    public void sort() 
    {
        int sizeOfArray = arr.length; /* sizeof array */
        boolean hasSwapProcess; /* to prevent unnecessary continuation of the loop */

        for (int i = 0; i < sizeOfArray - 1; i++) 
        {
            hasSwapProcess = false;

            for (int j = 0; j < sizeOfArray - i - 1; j++) 
            {
                comparison_counter++;

                if (arr[j] > arr[j + 1])
                {
                    swap(j, j + 1);
                    hasSwapProcess = true;
                }
            }

            if (!hasSwapProcess) /* if there are no swap processes, then the order is already correct */
            {
                break;     
            }
        }
    }
    
    @Override
    public void print() 
    {
    	System.out.print("Bubble Sort\t=>\t");
    	super.print();
    }
}
