public class MyHeap{
	public static void main(String[] args){
		System.out.println("Hello world");
		int maxVal = 8;
		TreeNode[] minHeap = baseHeap(maxVal);

		printTree(minHeap);
		minHeap = addValue(minHeap, 8);

		minHeap = addValue(minHeap, 10);
		minHeap = addValue(minHeap, 11);
		minHeap = addValue(minHeap, 14);
		
		System.out.println("Size of the heap: " + getSize(minHeap));
		removeTop(minHeap);
		System.out.println(searchVal(minHeap, 12));
		System.out.println(searchVal(minHeap, 2));
	}

	public static void removeTop(TreeNode[] currTree){
		printTree(currTree);
		int size = getSize(currTree);
		int lastVal = currTree[size].val;
		currTree[size] = null;
		currTree[1].val = lastVal;
		System.out.println(getSize(currTree));
		int i = 1;
		while(i * 2 + 1 <= size -1 && (currTree[i].val > currTree[2*i].val || currTree[i].val > currTree[2*i+1].val)){
			if(currTree[2*i+1].val > currTree[2*i].val){
				int temp = currTree[i].val;
				currTree[i].val = currTree[2*i].val;
				currTree[2*i].val = temp;
				i = i * 2;

			}else{
				int temp = currTree[i].val;
				currTree[i].val = currTree[2*i+1].val;
				currTree[2*i + 1].val = temp;
				i = i * 2 + 1;

			}
		}
		printTree(currTree);
		
		return;
	}

	public static int getSize(TreeNode[] currTree){
		int sizes = 0;
		for(int i = 1; i < currTree.length; i++){
			if(currTree[i] != null){
				sizes ++;
			}
		}
		return sizes;
	}

	public static boolean searchVal(TreeNode[] currTree, int val){
		for(int i = 1; i < currTree.length; i++){
			if(currTree[i] == null){
				return false;
			}
			if(currTree[i].val == val){
				return true;
			}
		}
		return false;
	}

	public static TreeNode[] addValue(TreeNode[] currTree, int val){
		int count = 0;
		for(int i = 1; i < currTree.length; i++){
			if(null != currTree[i] ){
				count ++;
			}else{
				break;
			}
		}
		double ratio = count * 1.0 / currTree.length;

		// resize the heap
		if(ratio > 0.8){
			TreeNode[] result = new TreeNode[currTree.length * 2];
			for(int i = 1; i < currTree.length; i++){
				result[i] = currTree[i];
			}
			currTree = result;
		}
		
		currTree[count+1] = new TreeNode(val);
		if((count + 1) % 2 == 0){
			currTree[(count+1) / 2].left = currTree[(count+1)];
		}else{
			currTree[(count+1) / 2].right = currTree[(count+1)];
		}

		int i = count + 1;
		while (currTree[i].val < currTree[i/2].val && i > 0){
			int temp = currTree[i].val;
			currTree[i].val = currTree[i/2].val;
			currTree[i/2].val = temp;
			i = i / 2;
		}

		return currTree;

	}

	public static TreeNode[] baseHeap(int maxVal){
		TreeNode[] allData = new TreeNode[2*maxVal];
		for(int i = 0; i < maxVal; i ++){
			allData[i+1] = new TreeNode(i*3);

		}

		for (int i = 1; i < maxVal; i++) {
			if (2 * i <= maxVal){
				allData[i].left = allData[2*i];
			}
			if(2 * i + 1 <= maxVal){
				allData[i].right = allData[2*i+1];
			}

		}
		System.out.println("IN BASE HEAP");
		System.out.println(allData[4].val);
		return allData;
	}

	public static void printTree(TreeNode[] currTree){
		for(int i = 1; i < currTree.length; i++){
			if(currTree[i] != null){
				System.out.print("Curr Node value is " + currTree[i].val);
				if(currTree[i].left != null){
					System.out.print(" Left Child is " + currTree[i].left.val);
				}
				if(currTree[i].right != null){
					System.out.print(" Right Child is " + currTree[i].right.val);
				}
				System.out.println();
			}

		}
	}
	
}