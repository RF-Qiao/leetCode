package com.feng;


import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

/**
 * 规范：
 * 先题号  在标签  题目 难易程度
 * 例子
 * 思路
 */
public class twoAdd {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
    }

    /**
     * 1. 数组 两数之和 简单
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 暴力求解
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
            return res;
        }
        int temp = 0;
        HashMap<Object, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            temp = target - nums[i];
            if (map.containsKey(temp)) {
                res[0] = i;
                res[1] = map.get(temp);
            }
            map.put(nums[i], i);
        }
        return res;
    }

    /**
     * 9. 字符串 回文数 简单
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * <p>
     * 123  321
     */
    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        StringBuilder builder = new StringBuilder(s);
        StringBuilder builder1 = builder.reverse();
        String s1 = new String(builder1);
        if (s.equals(s1)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 13. 字符串 罗马转整数 简单
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     * <p>
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     * <p>
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 先建立一个HashMap将I至M七个罗马符号及其对应的数字值存储下来，然后将字符串转换为int整数数组。初始化一个sum，
     * 从左向右遍历到倒数第二位，如果a[i]大于a[i+1]，则加上a[i]，否则减去a[i]。注意最后加上最后一位的a[s.length()-1]
     * 输入: s = "III"
     * 输出: 3
     */
    public int romanToInt(String s) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("I", 1);
        hashMap.put("V", 5);
        hashMap.put("X", 10);
        hashMap.put("L", 50);
        hashMap.put("C", 100);
        hashMap.put("D", 500);
        hashMap.put("M", 1000);
        int[] nums = new int[100];
        for (int i = 0; i < s.length(); i++) {
            nums[i] = hashMap.get(s.substring(i, i + 1));
        }
        int sum = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                sum = sum - nums[i];
            } else {
                sum = sum + nums[i];
            }
        }
        sum += nums[s.length() - 1];
        return sum;
    }

    /**
     * 14.字符串 最长公共前缀  简单
     * 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null && strs.length == 0) {
            return "";
        }
        //第一个数组的长度
        int lengthFirst = strs[0].length();
        int length = strs.length;
        for (int i = 0; i < lengthFirst; i++) {
            char charAt = strs[0].charAt(i);
            for (int j = 0; j < length; j++) {
                if (i == strs[j].length() || charAt != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    /**
     * 20. 栈 有效的括号 简单
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 每个右括号都有一个对应的相同类型的左括号。
     * "{[]}"
     * "{[]}"
     * 3 4 8 6
     * "()"
     *
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') stack.push(c);
            else if (stack.size() == 0) return false;
            else if (c == ')' && stack.peek() != '(') return false;
            else if (c == '}' && stack.peek() != '{') return false;
            else if (c == ']' && stack.peek() != '[') return false;
            else stack.pop();
        }
        return stack.empty();
    }

    /**
     * 21. 链表 合并两个有序链表 简单
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /**
     * 26. 数组 删除有序数组中的重复项 简单
     * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，
     * 使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
     * 输入：nums = [1,1,2]
     * 输出：2, nums = [1,2,_]
     */
    public int removeDuplicates(int[] nums) {
        HashSet<Object> set = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            set.add(i);
        }
        return set.size();
    }

    /**
     * 27.数组 移除元素 简单
     * 输入：nums = [3,2,2,3], val = 3
     * 输出：2, nums = [2,2]
     * 思路：  遍历  把所非val的值过滤掉。
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int k = 0;
        for (int i : nums) {
            if (i != val) {
                nums[k++] = i;
            }
        }
        return k;
    }

    /**
     * 35. 数组 二分查找 搜索插入位置 简单
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * <p>
     * 请必须使用时间复杂度为 O(log n) 的算法。
     * 输入: nums = [1,3,5,6], target = 5
     * 输出: 2
     * 思路： 遍历数组，如果改数组的值大于或等于目标值就返回改数组下表，如果都没有 则 返回该数组的长度。
     */
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i <= nums.length - 1; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }


    /**
     * 66.数组 加1 简单
     * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一
     * 输入：digits = [1,2,3]
     * 输出：[1,2,4]
     *
     * @param digits
     * @return 思路：  判断是否为9  如果不为9加1 返回 如果为9 变为0   然后前一位加1
     */
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    /**
     * 88.数组 合并两个有序数组 简单
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * 输出：[1,2,2,3,5,6]
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m-- + n-- - 1;
        while (m >= 0 && n >= 0) {
            nums1[p--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
        while (n >= 0) {
            nums1[p--] = nums2[n--];
        }
    }

    /**
     * 88 题第二中解法  优化
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < nums2.length; i++) {
            nums1[m++] = nums2[i];
        }
        Arrays.sort(nums1);
    }

    /**
     * 100 树 相同的树 简单
     * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
     *
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     *
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null && q==null){
            return true;
        }
        if (p!=null && q!=null && p.val==q.val ){
            return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
        }else {
            return false;
        }
    }

    /**
     * 121. 数组 股票的最大利润 简单
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            res = Math.max(prices[i] - min, res);
        }
        return res;
    }

    /**
     * 136.数组 只出现一次的数字 简单
     * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * <p>
     * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
     * <p>
     * 输入：nums = [2,2,1]
     * 输出：1
     * 思路：创建一个集合，遍历数组，集合中没有遍历的元素就直接添加，如果有就把集合中元素删除 代码
     */
    public int singleNumber(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i <= nums.length - 1; i++) {
            if (!list.contains(nums[i])) {
                list.add(nums[i]);
            } else {
                list.remove((Integer) nums[i]);
            }
        }
        return list.get(0);
    }

    /**
     * 169.数组 多数元素 简单
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * 输入：nums = [3,2,3]
     * 输出：3
     * 思路：从第一个数开始count=1，遇到相同的就加1，遇到不同的就减1，减到0就重新换个数开始计数，总能找到最多的那个
     */
    public int majorityElement(int[] nums) {
        int k = 1;
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == res) {
                k++;
            } else {
                k--;
                if (k == 0) {
                    res = nums[i + 1];
                }
            }
        }
        return res;
    }

    /**
     * 217.数组 存在重复数组 简单
     * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        HashSet<Object> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        return set.size() < nums.length ? true : false;
    }

    /**
     * 219.数组  存在重复元素 II 简单
     * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，
     * 满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
     * 输入：nums = [1,2,3,1], k = 3
     * 输出：true
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 268.数组 丢失的数字 简单
     * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
     * 输入：nums = [3,0,1]
     * 输出：2
     */
    public int missingNumber(int[] nums) {
        int sum = 0;
        int res = 0;
        for (int i = 0; i < nums.length + 1; i++) {
            sum += i;
        }
        for (int j = 0; j < nums.length; j++) {
            res += nums[j];
        }
        return sum - res;
    }

    /**
     * 414.数组 第三大的数 简单
     * 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
     * 输入：[3, 2, 1]
     * 输出：1     1 2  3
     */
    public int thirdMax(int[] nums) {
        TreeSet<Object> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        Object[] array = set.toArray();
        if (set.size() < 3) {
            return (Integer) array[array.length - 1];
        }
        return (Integer) array[array.length - 3];
    }

    /**
     * 455.数组 贪心  分发饼干 简单
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
     * <p>
     * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；
     * 并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，
     * 我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     * 输入: g = [1,2,3], s = [1,1]
     * 输出: 1
     */
    public int findContentChildren(int[] g, int[] s) {
        int child = 0;
        int cookie = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        while (child < g.length && cookie < s.length) {
            if (g[child] <= s[cookie]) {
                child++;
            }
            cookie++;
        }
        return child;
    }

    /**
     * 94.树 94. 二叉树的中序遍历 简单
     * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root!=null|| !stack.isEmpty()){
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root=root.right;
        }

        return list;
    }
}
