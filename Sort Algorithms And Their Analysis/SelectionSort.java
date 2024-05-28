public class SelectionSort extends SortAlgorithm 
{
	public SelectionSort(int input_array[]) 
    {
		super(input_array);
	}

    @Override
    public void sort() 
    {
        int sizeOfArray = arr.length; /* sizeof array */

        for (int i = 0; i < sizeOfArray - 1; i++) 
        {
            int minIndex = i; /* we assume the minimum element at the index i */

            for (int j = i + 1; j < sizeOfArray; j++) 
            {
                comparison_counter++;

                if (arr[j] < arr[minIndex]) /* if we see smaller number in the array then we assign new minIndex as j */
                {
                    minIndex = j;
                }
            }

            if (minIndex != i) 
            {
                swap(minIndex, i);   
            }
        }
    }

    @Override
    public void print() 
    {
    	System.out.print("Selection Sort\t=>\t");
    	super.print();
    }
}
