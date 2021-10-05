/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

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

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            ListNode cur = this;
            while (cur != null) {
                str.append(cur.val);
                if (cur.next != null) {
                    str.append("->");
                }
                cur = cur.next;
            }
            return str.toString();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        ListNode node = solution.arrayToListNode(new int[]{4, -2, -4, 0, -2, -2, -1, -2});
        ListNode node = solution.arrayToListNode(new int[]{1,2,3,4,5});
        System.out.println(node);
        System.out.println(solution.reverseBetween(node, 2, 4));
    }

    private ListNode arrayToListNode(int[] array) {
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        for (int num : array) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        return dummyHead.next;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null) return head;
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode leftPrev = dummyHead;
        ListNode rightNext = null;
        ListNode leftNode = null;
        ListNode rightNode = null;
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            count++;
            if (cur.next != null && count == left - 1) {
                leftPrev = cur;
            }
            if (count == right) {
                rightNext = cur.next;
                rightNode = cur;
            }
            if (count == left) {
                leftNode = cur;
            }
            cur = cur.next;
        }
        System.out.println(leftNode.val);
        System.out.println(rightNode.val);
        System.out.println(leftPrev.val);
        System.out.println(rightNext.val);
        //翻转
        cur = leftNode;
        ListNode prev = null;
        while (cur != null && cur != rightNext) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        leftPrev.next = rightNode;
        leftNode.next = rightNext;
        return dummyHead.next;
    }
}