package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

//序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
//
// 设计一个算法来序列化和反序列化二叉搜索树。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化
//为最初的二叉搜索树。 
//
// 编码的字符串应尽可能紧凑。 
//
// 注意：不要使用类成员/全局/静态变量来存储状态。 你的序列化和反序列化算法应该是无状态的。 
// Related Topics 树 
// 👍 102 👎 0

class Deserialize1 {
    public static void main(String[] args) {
        Codec codec = new Deserialize1().new Codec();
        TreeNode root = codec.deserialize("41,37,44,24,39,42,48,1,35,38,40,null,43,46,49,0,2,30,36,null,null,null,null,null,null,45,47,null,null,null,null,null,4,29,32,null,null,null,null,null,null,3,9,26,null,31,34,null,null,7,11,25,27,null,null,33,null,6,8,10,16,null,null,null,28,null,null,5,null,null,null,null,null,15,19,null,null,null,null,12,null,18,20,null,13,17,null,null,22,null,14,null,null,21,23");
        System.out.println(codec.serialize(root));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
//Definition for a binary tree node.
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "{" +
                    "\nval=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    class Codec {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            Queue<List<TreeNode>> queue = new LinkedBlockingQueue<>();
            queue.add(Arrays.asList(root));
            List<TreeNode> t;
            while (!queue.isEmpty()) {
                List<TreeNode> poll = queue.poll();
                t = new ArrayList<>();
                for (TreeNode treeNode : poll) {
                    if (treeNode != null) {
                        t.add(treeNode.left);
                        t.add(treeNode.right);
                        list.add(treeNode.val);
                    } else {
                        t.add(null);
                        t.add(null);
                        list.add(null);
                    }
                }
                for (TreeNode treeNode : t) {
                    if (treeNode != null) {
                        queue.add(t);
                        break;
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i) + ((i == list.size() - 1) ? "" : ","));
            }
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] split = data.split(",");
            TreeNode[] objects = new TreeNode[split.length];
            for (int i = 0; i < split.length; i++) {
                objects[i] = split[i].equals("null") ? null : new TreeNode(Integer.parseInt(split[i]));
            }
            for (int i = 0; i < objects.length; i++) {
                if (objects[i] != null) {
                    objects[i].left = ((2 * i + 1) < objects.length) ? objects[2 * i + 1] : null;
                    objects[i].right = ((2 * i + 2) < objects.length) ? objects[2 * i + 2] : null;
                }
            }
            return objects[0];
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)
}