import java.util.*;
public class MyTree {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		

		TreeNode root = new TreeNode(0);
		TreeNode rootleft = new TreeNode(1);
		TreeNode rootright = new TreeNode(2);
		root.left = rootleft;
		root.right = rootright;
		rootleft.left = new TreeNode(3);
		rootleft.right = new TreeNode(4);
		rootright.left = new TreeNode(5);
		rootright.right = new TreeNode(6);
		rootleft.left.left = new TreeNode(7);
		rootleft.left.right = new TreeNode(8);
		rootleft.right.left = new TreeNode(9);
		rootleft.right.right = new TreeNode(10);
		rootright.left.left = new TreeNode(11);
		rootright.left.right = new TreeNode(12);
		rootright.right.left = new TreeNode(15);
		rootright.right.left.right = new TreeNode(14);
		rootright.right.left.left = new TreeNode(-28);
		TreeNode BSTroot = new TreeNode(8);
		BSTroot.right = new TreeNode(20);		
		BSTroot.left = new TreeNode(3);
		BSTroot.right.right = new TreeNode(29);
		BSTroot.right.left = new TreeNode(17);
		BSTroot.left.right = new TreeNode(6);
		BSTroot.left.left = new TreeNode(1);

		
		System.out.println("Smallest in a Tree: " + SmallestinTree(root));
		System.out.println("dfas" + largestValues(root));
		System.out.println("preorder: " +  preorder(root));
		System.out.println("inorder: " + inorder(root));
		System.out.println("inorder Iterative: " + inorderI(root));
		System.out.println("postorder: " + postorder(root));
		System.out.println("BSADFASFD "  + BSTroot.right.left);
		System.out.println("Iterative DFS: ");
		DFS(root);
		System.out.println("Recursive DFS: ");		
		DFSR(root);
		System.out.println();
		BFS(root);
		BFSI(root);
		System.out.println(numTrees(1));
		// Iterative and Recursive solution of the has Path Sum problem
		System.out.println(hasPathSumI(root, 2));
		System.out.println(hasPathSumR(root, 2));
		hasPathSumIIR(root, 2);
		System.out.println("iterative get height: " + getHeight(root));
		
		System.out.println("recursion get height: " + getHeightR(root));
		levelPrint(root);
		zigZag(root);
		System.out.println("Sum of left leaves is: " + sumOfLeftLeaves(BSTroot));
		System.out.println("Min dif is " + MinDif(BSTroot));
		System.out.println("number of nodes is " + countNodes(root));
		System.out.println("Sum of left leave is " + sumOfLeftLeaves(root));
		System.out.println("Unitree boolean is " + uniTree(root));
		System.out.println("Count of unitree is " + countUniTree(root));

		// Binary Tree Operations
		String serializeResult = serialize(root);
		System.out.println("Serialization of the tree: " + serializeResult);
		System.out.println("Start of deserializaton: ");
		System.out.println("Root value: " + deserialize(serializeResult).val);
		System.out.println("Left value: " + deserialize(serializeResult).left.val);
		System.out.println("Right value: " + deserialize(serializeResult).right.val);


		// Binary Search Tree
		TreeNode BSTRoot = new TreeNode(0);
		BSTRoot.left = new TreeNode(-2);
		BSTRoot.right = new TreeNode(2);
		BSTRoot.left.left = new TreeNode(-4);
		BSTRoot.left.right = new TreeNode(-1);
		BSTRoot.right.right = new TreeNode(5);
		BSTRoot.right.left = new TreeNode(1);
				
		System.out.println(SmallestBST(BSTRoot));
		System.out.println(SecondSmall(BSTRoot));
		System.out.println(LargestBST(BSTRoot));
		System.out.println(SecondLarge(BSTRoot));
		System.out.println("Search 2 in BST: " + SearchVal(BSTRoot, 2));
		System.out.println("Search 14 in BST: " + SearchVal(BSTRoot, 14));
		System.out.println("Add 28 in BST");
		
		System.out.println("Before adding, the in order BST is " + inorderI(BSTRoot));
		TreeNode newBST = addNodeBST(BSTRoot, 28);
		System.out.println("After adding, the in order BST is " + inorderI(newBST));
		TreeNode deleted = deleteNodeBST(newBST, 2);
		System.out.println("After adding, the in order BST is " + inorderI(deleted));

		System.out.println("Max Path Sum: " + maxPathSum(root));
		System.out.println("Min Path Sum: " + minPathSum(root));
		System.out.println(longestIncreasingSeq(root));
	}

	public static String serialize(TreeNode tr){
		if(tr == null){
			return "X";
		}else{
			StringBuilder sb = new StringBuilder();
			Stack<TreeNode> st = new Stack<>();
			st.push(tr);
			while(st.size() > 0){
				TreeNode curr = st.pop();
				sb.append(curr.val);
				sb.append(",");
				if(curr.right == null){
					sb.append("X,");
				}
				if(curr.left == null){
					sb.append("X,");
				}
				if(curr.right != null){
					st.push(curr.right);
				}
				if(curr.left != null){
					st.push(curr.left);
				}

			}
			sb.setLength(sb.length()-1);
			return sb.toString();
		}
	}

	public static TreeNode rdeserialize(List<String> l){
		if(l.get(0).equals("X")){
			l.remove(0);
			return null;
		}
		TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
		l.remove(0);
		root.left = rdeserialize(l);
		root.right = rdeserialize(l);
		return root;
	}

	public static TreeNode deserialize(String result){
		if(result.length() == 1 && result.charAt(0) == 'X'){
			return null;
		}else{
			String[] allData = result.split(",");
			List<String> data_list = new LinkedList<String>(Arrays.asList(allData));
			return rdeserialize(data_list);
		}
	}

	public static TreeNode deleteNodeBST(TreeNode root, int val){
		if(root == null){
			return null;
		}else{
			if(root.val < val){
				root.right = deleteNodeBST(root.right, val);
			}else if(root.val > val){
				root.left = deleteNodeBST(root.left, val);
			}else{
				if(root.left == null && root.right == null){
					return null;
				}else if(root.left == null && root.right != null){
					return root.right;
				}else if(root.left != null && root.right == null){
					return root.left;
				}else{
					root.val = findMax(root.left);
					root.left = deleteNodeBST(root.left, root.val);
				}
			}
			return root;
		}
	}

	public static int findMax(TreeNode root){
        while(root.right != null){
            root = root.right;
        }
        return root.val;
    }

	public static TreeNode addNodeBST(TreeNode root, int val){
		if(root == null){
			return new TreeNode(val);
		}
		if(val < root.val){
			root.left = addNodeBST(root.left, val);
		}else if(val > root.val){
			root.right = addNodeBST(root.right, val);
		}
		return root;
	}

	public static boolean SearchVal(TreeNode root, int val){
		if(root == null){
			return false;
		}else if(root.val == val){
			return true;
		}else{
			if(root.val < val){
				return SearchVal(root.right, val);
			}else{
				return SearchVal(root.left, val);
			}
		}
	}

	public static int SmallestinTree(TreeNode root){
		int result = Integer.MAX_VALUE;
		return SmallestinTreehelper(root, result);
		
	}

	public static int SmallestinTreehelper(TreeNode root, int result){
		if(root == null){
			return result;
		}else{
			result = Math.min(result, root.val);			
			return Math.min(result, Math.min(SmallestinTreehelper(root.left, result), SmallestinTreehelper(root.right, result)));
		}
	} 
	
	public static int longestIncreasingSeq(TreeNode root) {
		if(root == null) {
			return 1;
		}else {
			if(root.left == null && root.right == null) {
				return 1;
			}else if(root.left != null && root.right == null) {
				if(root.left.val > root.val) {
					return 1 + longestIncreasingSeq(root.left);
				}else {
					return 1;
				}				
			}else if(root.left == null && root.right != null) {
				if(root.right.val > root.val) {
					return 1 + longestIncreasingSeq(root.left);
				}else {
					return 1;
				}
			}else {
				if(root.left.val > root.val && root.right.val > root.val) {
					return 1 + Math.max(longestIncreasingSeq(root.left), longestIncreasingSeq(root.right));
				}else if(root.left.val <= root.val && root.right.val > root.val) {
					return 1 + longestIncreasingSeq(root.right);
				}else if(root.left.val > root.val && root.right.val <= root.val) {
					return 1 + longestIncreasingSeq(root.left);
				}else {
					return 1;
				}
			}
		}
	}
	
	public static int minPathSum(TreeNode root) {
		if(root == null) {
			return 0;
		}else {
			return Math.min(minPathSum(root.left), minPathSum(root.right)) + root.val;
		}
	}
	
	public static int maxPathSum(TreeNode root) {
		
		if(root == null) {
			return 0;
			
		}else {
			return Math.max(maxPathSum(root.left), maxPathSum(root.right)) + root.val;
		}
	}	
	
	public static int SecondLarge(TreeNode BSTRoot) {
		if(BSTRoot.right != null) {
			TreeNode slowPtr = BSTRoot;
			TreeNode fastPtr = BSTRoot.right;
			while(fastPtr.right != null) {
				slowPtr = fastPtr;
				fastPtr = fastPtr.right;
			}
			return slowPtr.val;
		}else {
			return LargestBST(BSTRoot.left);
		}
	}
	
	public static int SecondSmall(TreeNode BSTRoot) {
		if(BSTRoot.left != null) {
			TreeNode fastPtr = BSTRoot.left;
			TreeNode slowPtr = BSTRoot;
			while(fastPtr.left != null) {
				
				slowPtr = fastPtr;
				fastPtr = fastPtr.left;
			}
			return slowPtr.val;
		}else {
			return SmallestBST(BSTRoot.right);
		}		
	}

	public static int LargestBST(TreeNode root) 	
	{
		if(root.right == null) {
			return root.val;
		}else {
			while(root.right != null) {
				root = root.right;
			}
			return root.val;
		}
	}

	public static int SmallestBST(TreeNode root) 	
	{
		if(root.left == null) {
			return root.val;
		}else {
			while(root.left != null) {
				root = root.left;
			}
			return root.val;
		}
	}

	// post order traversal
	public static int countUniTree(TreeNode root){
		if (root == null){
			return 0;
		}else{
			if (uniTree(root)){
				return 1 + countUniTree(root.left) + countUniTree(root.right);
			}else{
				return countUniTree(root.left) + countUniTree(root.right);
			}
		}
	}

	public static List<Integer> postorder(TreeNode root) {
		LinkedList<Integer> result = new LinkedList<>();
	    Deque<TreeNode> stack = new ArrayDeque<>();
	    TreeNode p = root;
	    while(!stack.isEmpty() || p != null) {
	        if(p != null) {
	            stack.push(p);
	            result.addFirst(p.val);  // Reverse the process of preorder
	            p = p.right;             // Reverse the process of preorder
	        } else {
	            TreeNode node = stack.pop();
	            p = node.left;           // Reverse the process of preorder
	        }
	    }
	    return result;
	}

	// pre order traversal	
	public static List<Integer> preorder(TreeNode root){
		ArrayList<Integer> myList = new ArrayList<>();
		
		if(root != null){
			myList.add(root.val);
			myList.addAll(preorder(root.left));
			myList.addAll(preorder(root.right));
		}
		return myList;
	}
	
	
	//in order traversal
	public static List<Integer> inorder(TreeNode root){
		ArrayList<Integer> mylist = new ArrayList<Integer>();
		if(root != null){
			mylist.addAll(inorder(root.left));
			mylist.add(root.val);
			mylist.addAll(inorder(root.right));
		}
		return mylist;
	}

	public static List<Integer> inorderI(TreeNode root){
		ArrayList<Integer> myList = new ArrayList<Integer>();
		Stack<TreeNode> st = new Stack<>();
		while(root != null){
			st.push(root);
			root = root.left;

		}
		while(st.size() > 0){
			TreeNode curr = st.pop();
			myList.add(curr.val);
			curr = curr.right;
			while(curr != null){
				st.push(curr);
				curr = curr.left;
			}
		}
		return myList;
	}

	public static void BFSI(TreeNode root){
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Queue<TreeNode> q = new LinkedList<>();
		if(root == null){
			return;
		}else{
			q.add(root);
			while(q.size() > 0){
				ArrayList<Integer> curLevel = new ArrayList<>();
				int l = q.size();
				for(int i = 0; i < l; i++){
					TreeNode curr = q.poll();
					curLevel.add(curr.val);
					if(curr.left != null){
						q.add(curr.left);
					}
					if(curr.right != null){
						q.add(curr.right);
					}
				}
				result.add(curLevel);
			}
		}
		System.out.println("Iterative BFS");
		System.out.println(result);	
	}

	public static void BFS(TreeNode root){
		Queue<NodeWLevel> toVisit = new LinkedList<>();
		int[] maxNode = new int[10];
		for(int i = 0; i < maxNode.length; i++){
			maxNode[i] = Integer.MIN_VALUE;
		}
		toVisit.add(new NodeWLevel(root, 0));
		while(!toVisit.isEmpty()){
			NodeWLevel cur = toVisit.poll();
			int curVal = cur.dest.val;
			int curLevel = cur.level;
			maxNode[curLevel] = Math.max(maxNode[curLevel], curVal);
			if(cur.dest.left!=null){
				toVisit.add(new NodeWLevel(cur.dest.left, cur.level+1));
			}
			if(cur.dest.right!=null){						
				toVisit.add(new NodeWLevel(cur.dest.right, cur.level+1));
			}
		}
		System.out.println(Arrays.toString(maxNode));
	}

	public static boolean uniTree(TreeNode root){
		boolean b = true;
		HashSet<Integer> h = new HashSet<>();
		Queue<TreeNode> q = new LinkedList<>();
		h.add(root.val);
		q.add(root);
		while(q.size()>0&&b){
			System.out.println(h.toString());
			TreeNode cur = q.poll();
			if(cur.left!=null){
				q.add(cur.left);
				if(h.add(cur.left.val)){
					b = false;
					break;
				}
			}
			if(cur.right!=null){
				q.add(cur.right);
				if(h.add(cur.right.val)){
					b = false;
					break;
				}
			}
		}
		return b;
	}
	
	public static boolean hasPathSumI(TreeNode root, int sum){
		Stack<TreeNode> stack = new Stack<>();
		Stack<Integer> sums = new Stack<>();

		stack.push(root);
		sums.push(sum);
		while(!stack.isEmpty()&&root!=null){

			TreeNode top = stack.pop();
			int value = sums.pop();
			if(top.left==null&&top.right==null&&top.val==value){
				return true;
			}

			if(top.left!=null){
				stack.push(top.left);
				sums.push(value-top.val);
			} if(top.right!=null){
				stack.push(top.right);
				sums.push(value-top.val);
			}

		}
		return false;
	}

	public static boolean hasPathSumR(TreeNode root, int sum){
		if(root == null){
			return false;
		}else{
			if(root.left == null && root.right == null ){
				return sum == root.val;
			}else{
				return hasPathSumR(root.left, sum - root.val) ||
						hasPathSumR(root.right, sum - root.val);
			}
		}
	}

	public static List<List<String>> hasPathSumIIR(TreeNode root, int sum){

		return null;
	}
	//Thoughts:
		//For a new integer, say 5, consider the root,
		//If root is 1, it is same as 0 at left and 1~4 at right
		//If root is 2, it is same as 1 at left and 1~3 at right so on so forth
	public static int numTrees(int n){
		int[] result = new int[n+1];
		result[0] = 1;
		if(n < 3){

			for(int i = 1; i < result.length; i++){
				result[i] = i; 
			}
		}else{

			result[1] = 1;
			result[2] = 2;
			for(int i = 3; i <=n; i++){
				for(int j = 0; j <i;j++){
					result[i]+= result[j]*result[i-j-1];
				}
			}
		}
		return result[n];
	}

	
	public static int getHeightR(TreeNode root){
		if(root == null){
			return -1;
		}
		else{
			return getHeightRHelper(root);
		}
	}
	
	public static int getHeightRHelper(TreeNode root){
		if(root == null){
			return 0;
		} else {
			return 1 + Math.max(getHeightRHelper(root.left), 
					getHeightRHelper(root.right));
		}
	}
	
	public static int getHeight(TreeNode root){
		Queue<NodeWLevel> q = new LinkedList<>();
		q.add(new NodeWLevel(root, 1));
		int max = 1;
		while(q.size()!=0){			
			NodeWLevel curr = q.poll();
			max = Math.max(max, curr.level);
			if(curr.dest.left!=null){
				q.add(new NodeWLevel(curr.dest.left, curr.level+1));
			}
			if(curr.dest.right!=null){
				q.add(new NodeWLevel(curr.dest.right, curr.level+1));
			}
		}
		return max;
	}
	
	public static int sumOfLeftLeaves(TreeNode root){
		//Do BFS
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		int sum = 0;
		while(q.size() > 0 ){
			TreeNode cur = q.poll();
			if(cur.left != null){
				q.add(cur.left);
				if(cur.left.left == null && cur.left.right == null){
					sum += cur.left.val;
				}
			}
			if(cur.right != null){
				q.add(cur.right);
			}
		}
		return sum;
	}
	
	public static int MinDif(TreeNode root){
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		ArrayList<Integer> vals = new ArrayList<>();
		q.add(root);
		
		int length = 0;
		while(q.size()>0){
			TreeNode cur = q.poll();
			vals.add(cur.val);
			length++;
			if(cur.left!=null){
				q.add(cur.left);
			}
			if(cur.right!=null){
				q.add(cur.right);
			}
		}
		int[] temp = new int[length];
		
		for(int i = 0; i < length; i++){
			temp[i] = vals.get(i);
		}
		Arrays.sort(temp);
		int min = temp[length-1];
		for(int i = 0; i < length -1; i++){
			min = Math.min(temp[i+1] - temp[i] , min);
		}
		return min;
	}

	public static int countNodes(TreeNode root){
		Queue<TreeNode> q = new LinkedList<>();		
		int count = 1;
		while(q.size()>0){
			TreeNode cur = q.poll();
			if(cur.left!=null){
				q.add(cur.left);
				count++;
			}
			if(cur.right!=null){
				q.add(cur.right);
				count++;
			}
		}		
		return count;
	}
	
	public static void levelPrint(TreeNode root){
		Queue<NodeWLevel> q = new LinkedList<>();
		q.add(new NodeWLevel(root, 1));
		String[] allNode = new String[1000];
		for(int i = 0; i< allNode.length;i++){
			allNode[i] = " ";
		}
		while(q.size()!=0){

			NodeWLevel curr = q.poll();
			int value = curr.dest.val;
			int level = curr.level;
			allNode[level] += value + " ";
			if(curr.dest.left!=null){
				q.add(new NodeWLevel(curr.dest.left, curr.level+1));

			}
			if(curr.dest.right!=null){
				q.add(new NodeWLevel(curr.dest.right, curr.level+1));
			}
		}

		int height = 1;
		while(allNode[height]!=" "){
			height++;
		}
		for(int j = 1; j < height; j++){
			String[] curr = allNode[j].split(" ");
			ArrayList<Integer> cur = new ArrayList<Integer>();
			for(int i = 1; i < curr.length; i++){
				cur.add(Integer.parseInt(curr[i]));
			}
			System.out.println(cur.toString());
		}
	}
	
	public static void DFS(TreeNode root){
		Stack<TreeNode> st = new Stack<TreeNode>();
		
		st.add(root);
		while(st.size()!=0){
			
			
			TreeNode cur = st.pop();
			System.out.print(cur.val+" ");
			if(cur.right!=null){
				st.push(cur.right);
			}if(cur.left!=null){
				st.push(cur.left);
			}
		}
		System.out.println();
	}
	
	public static void DFSR(TreeNode root){
		System.out.print(root.val + " ");
		if(root.left!=null){
			DFSR(root.left);
		}if(root.right!=null){
			DFSR(root.right);
		}
	}
	
	
	public static void zigZag(TreeNode root){
		Queue<NodeWLevel> q = new LinkedList<>();
		q.add(new NodeWLevel(root, 1));
		String[] allNode = new String[1000];
		for(int i = 0; i< allNode.length;i++){
			allNode[i] = " ";
		}
		while(q.size()!=0){

			NodeWLevel curr = q.poll();
			int value = curr.dest.val;
			int level = curr.level;
			allNode[level] += value + " ";
			if(curr.dest.left!=null){
				q.add(new NodeWLevel(curr.dest.left, curr.level+1));

			}
			if(curr.dest.right!=null){
				q.add(new NodeWLevel(curr.dest.right, curr.level+1));
			}
		}

		int height = 1;
		while(allNode[height]!=" "){
			height++;
		}
		for(int j = 1; j < height; j++){
			String[] curr = allNode[j].split(" ");
			ArrayList<Integer> cur = new ArrayList<Integer>();
			if(j % 2 == 1){
				for(int i = 1; i < curr.length; i++){
					cur.add(Integer.parseInt(curr[i]));
				}
			}else{
				for(int i = curr.length-1; i >= 1; i--){
					cur.add(Integer.parseInt(curr[i]));
				}
			}
			System.out.println(cur.toString());
		}
	}
	
	public static List<Integer> largestValues(TreeNode root) {
        if(root == null){
            return null;
        }else{
            List<Integer> result = new ArrayList<Integer>();
            TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();
            Queue<NodeLevel> q = new LinkedList<>();
            NodeLevel first = new NodeLevel(root, 0);
            q.add(first);
            while(q.size()>0){
            	
                NodeLevel cur = q.poll();
                int curval = cur.tr.val;
                int curLevel = cur.level;
                if(tm.containsKey(curLevel)){
                    tm.put(curLevel, Math.max(tm.get(curLevel), curval));
                }else{
                    tm.put(curLevel, curval);
                }
                if(cur.tr.left!=null){
                    q.add(new NodeLevel(cur.tr.left, curLevel + 1));
                }if(cur.tr.right!=null){
                    q.add(new NodeLevel(cur.tr.right, curLevel + 1));
                }
            }
            for(int i : tm.keySet()){
                result.add(tm.get(i));
            }
            return result;
        }
    }

	public static class NodeWLevel {
		TreeNode dest;
		int level;

		public NodeWLevel(TreeNode dest, int level) {
			this.dest = dest;
			this.level = level;
		}
	}
	
	public static class NodeLevel{
        TreeNode tr;
        int level;
        public NodeLevel(TreeNode tr, int level){
            this.tr = tr;
            this.level = level;
        }
    }
}
