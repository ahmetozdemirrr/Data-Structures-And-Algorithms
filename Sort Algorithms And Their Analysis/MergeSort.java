public class MergeSort extends SortAlgorithm 
{
	public MergeSort(int input_array[]) 
    {
		super(input_array);
	}
	
    private void merge(int left, int middle, int right) 
    {
        int  sizeOfLeftSide = middle - left + 1;
        int sizeOfRightSide = right - middle;

        int  leftSide[] = new int[sizeOfLeftSide];
        int rightSide[] = new int[sizeOfRightSide];

        /* We fill the array into two local arrays based on the indexes */

        for (int i = 0; i < sizeOfLeftSide; ++i)
        {
            leftSide[i] = arr[left + i];
        }
        
        for (int j = 0; j < sizeOfRightSide; ++j)
        {
            rightSide[j] = arr[middle + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;

        while (i < sizeOfLeftSide && j < sizeOfRightSide) 
        {
            comparison_counter++;

            /* 
                here we found smaller element at current index 
                than we assign this element to the actual array
            */

            if (leftSide[i] <= rightSide[j])
            {
                arr[k] = leftSide[i];
                i++;
            } 

            else 
            {
                arr[k] = rightSide[j];
                j++;
            }
            k++;
        }

        /*
            because of the previous loop, if there are extra elements 
            left on one side, we assign them directly to the actual array 
            because these elements will already be large elements
        */

        while (i < sizeOfLeftSide) 
        {
            arr[k] = leftSide[i];
            i++;
            k++;
        }

        while (j < sizeOfRightSide) 
        {
            arr[k] = rightSide[j];
            j++;
            k++;
        }
    }

    private void sort(int left, int right) 
    {
        if (left < right) 
        {
            int middle = (left + right) / 2; /* we find the middle index if this condition is met */

            /* recursive calls break the array into smaller chunks, for both sides (left and right) */
            sort(left, middle);
            sort(middle + 1, right);

            merge(left, middle, right); /* calling merge method for mergeing */
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
    	System.out.print("Merge Sort\t=>\t");
    	super.print();
    }
}
