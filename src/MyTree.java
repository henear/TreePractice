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
		rootright.right.left = new TreeNode(13);
		rootright.right.left.right = new TreeNode(14);
		System.out.println(preorder(root));
		System.out.println(inorder(root));
		System.out.println(postorder(root));
		BFS(root);
		System.out.println(numTrees(1));
		// Iterative and Recursive solution of the has Path Sum problem
		System.out.println(hasPathSumI(root, 2));
		System.out.println(hasPathSumR(root, 2));
		hasPathSumIIR(root, 2);
		System.out.println(getHeight(root));
		levelPrint(root);
		zigZag(root);
	}

	// post order traversal
	public static List<Integer> postorder(TreeNode root) {
		ArrayList<Integer> mylist = new ArrayList<>();
		if(root != null){			
			mylist.addAll(preorder(root.left));
			mylist.addAll(preorder(root.right));
			mylist.add(root.val);
		}
		return mylist;
	}

	// pre order traversal
	public static List<Integer> preorder(TreeNode root){
		ArrayList<Integer> mylist = new ArrayList<>();
		if(root != null){
			mylist.add(root.val);
			mylist.addAll(preorder(root.left));
			mylist.addAll(preorder(root.right));
		}
		return mylist;
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

	public static class NodeWLevel {
		TreeNode dest;
		int level;

		public NodeWLevel(TreeNode dest, int level) {
			this.dest = dest;
			this.level = level;
		}
	}
}
