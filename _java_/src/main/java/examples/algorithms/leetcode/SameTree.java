package examples.algorithms.leetcode;

/**
 * https://leetcode.com/problems/same-tree/
 * <p>
 * Даны корни двух бинарных деревьев. Проверить, являются ли деревья одинаковыми.
 * Они считаются одинаковыми, если имеют одинаковую структуру и одинаковые значения в узлах
 */
public class SameTree {

    /**
     * Главная идея алгоритма (DFS):
     * <p>
     * Мы рекурсивно сравниваем два дерева. Деревья считаются одинаковыми, если:
     * <p>
     * - Оба корня null → деревья равны.
     * <p>
     * - Только один из корней null → деревья разные.
     * <p>
     * - Значения в корнях не равны → деревья разные.
     * <p>
     * - Рекурсивно проверяем левое и правое поддеревья.
     * <p>
     * Этот алгоритм является жадным и выполняется за O(n), где n — количество узлов.
     * <p>
     * Пространственная сложность O(h), где h — высота дерева. O(log n) для сбалансированных деревьев и O(n) для вырожденных (например, список).
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        // Если оба узла null, деревья идентичны
        if (p == null && q == null) {
            return true;
        }

        // Если только один из узлов null или значения различаются, деревья разные
        if (p == null || q == null || p.val != q.val) {
            return false;
        }

        // Рекурсивно проверяем левое и правое поддеревья
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        TreeNode p = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode q = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(isSameTree(p, q));

        p = new TreeNode(1, new TreeNode(3), new TreeNode(2));
        System.out.println(isSameTree(p, q));
    }
}

// Definition for a binary tree node.
class TreeNode {
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
