public class QuickSort extends SortAlgorithm 
{
	public QuickSort(int input_array[]) 
    {
		super(input_array);
	}
	
    private int partition(int left, int right)
    {
        int pivot = arr[right]; /* we seletc last elemnt as pivot */
        int index = (left - 1);

        for (int i = left; i < right; i++) 
        {
            comparison_counter++;

            if (arr[i] < pivot) /* ıf current element smaller than pivot we swap index with i */
            {
                index++;

                if (index != i) 
                {
                    swap(index, i);   
                }
            }    
        }

        if ((index + 1) != right) 
        {
            swap(index + 1, right); /* pivot is taken to the centre */
        }
        return index + 1; /* return the new pivot indekx */
    }

    private void sort(int left, int right)
    {
        if (left < right) 
        {
            int partIndex = partition(left, right);

            /* recursive calls break the array into smaller chunks, for both sides (left and right) */
            sort(left, partIndex - 1);
            sort(partIndex + 1, right);
        }
    }

    @Override
    public void sort() 
    {
    	sort(0, arr.length - 1); /* we give the start and end indexes for the sort operation */
    }

    @Override
    public void print() 
    {
    	System.out.print("Quick Sort\t=>\t");
    	super.print();
    }
}
