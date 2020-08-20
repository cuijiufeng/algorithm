//给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其层序遍历: 
//
// [
//     [1],
//     [3,2,4],
//     [5,6]
//]
// 
//
// 
//
// 说明: 
//
// 
// 树的深度不会超过 1000。 
// 树的节点总数不会超过 5000。 
// Related Topics 树 广度优先搜索 
// 👍 106 👎 0
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

// Definition for a Node.
class Node {
    public List<Node> children;
    public int val;

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }

    public Node() {}
};

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null){
            return new ArrayList<>();
        }
        Queue<List<Node>> queue = new LinkedBlockingQueue<>();
        queue.add(Arrays.asList(root));
        List<List<Integer>> list = new ArrayList<>();
        while (!queue.isEmpty()){
            List<Node> poll = queue.poll();
            list.add(poll.stream().map(node -> node.val).collect(Collectors.toList()));
            List<Node> t = new ArrayList<>();
            for (Node node : poll) {
                if (node.children != null){
                    for (Node child : node.children) {
                        t.add(child);
                    }
                }
            }
            if (!t.isEmpty()){
                queue.add(t);
            }
        }
        return list;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
