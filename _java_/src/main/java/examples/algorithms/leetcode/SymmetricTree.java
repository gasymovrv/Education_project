package examples.algorithms.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/symmetric-tree
 * <p>
 * Дан корень бинарного дерева. Проверить является ли оно зеркальным самому себе (симметричным относительно центра)
 */
public class SymmetricTree {

    /**
     * Решение очень похоже на {@link SameTree},
     * но в методе {@link #isMirror} мы меняем местами левое и правое поддеревья
     */
    public static boolean isSymmetric(TreeNode root) {
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode t1, TreeNode t2) {
        // Если оба узла null, деревья идентичны
        if (t1 == null && t2 == null) {
            return true;
        }

        // Если только один из узлов null или значения различаются, деревья разные
        if (t1 == null || t2 == null || t1.val != t2.val) {
            return false;
        }

        // Рекурсивно проверяем левое и правое поддеревья, но сравниваем правое с левым (зеркальность)
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    /**
     * Итеративное решение (BFS с очередью)
     * <p>
     * Сложность итеративного решения такая же: O(n) по времени и O(h) по памяти.
     * <p>
     * Оно удобнее, если дерево глубокое и рекурсивный стек мог бы переполниться.
     */
    public static boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();

            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null || t1.val != t2.val) return false;

            queue.offer(t1.left);
            queue.offer(t2.right);
            queue.offer(t1.right);
            queue.offer(t2.left);
        }

        return true;
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(
                1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(2, new TreeNode(4), new TreeNode(3))
        );

        System.out.println(isSymmetric(t));
        System.out.println(isSymmetric2(t));
    }
}
