package examples.algorithms;

/**
 * Binary Search Tree (BST) — это особый тип бинарного дерева, в котором выполняются следующие правила:
 * <p>
 * - У каждого узла есть не более двух детей: левый и правый.
 * <p>
 * - Все значения в левом поддереве узла меньше, чем значение самого узла.
 * <p>
 * - Все значения в правом поддереве узла больше, чем значение самого узла.
 * <p>
 * <br/>
 * Пример задачи:
 * Дан корень BST дерева, вернуть минимальную разницу по модулю между любыми узлами в дереве
 */
class BinarySearchTree {
    private Integer prev = null; // Предыдущий узел при обходе дерева
    private int minDiff = Integer.MAX_VALUE; // Минимальная разница

    public int getMinimumDifference(TreeNode root) {
        inOrderTraversal(root); // Запускаем обход дерева в порядке in-order
        return minDiff; // Возвращаем минимальную найденную разницу
    }

    /**
     * Главная идея алгоритма:
     * <p>
     * Поскольку данное дерево является Binary Search Tree (BST), его in-order
     * (симметричный) обход даёт отсортированную последовательность значений узлов.
     * Если мы обходим дерево этим способом, то минимальная разница обязательно
     * будет между двумя последовательными элементами.
     * Временная и пространственная сложность:
     * <p>
     * Временная сложность: O(N), где N — количество узлов в дереве (так как мы
     * проходим по каждому узлу один раз).
     * Пространственная сложность: O(H), где H — высота дерева (в худшем случае O(N) для несбалансированного дерева,
     * в среднем O(logN) для сбалансированного).
     * <p>
     * Этот алгоритм проходит дерево в порядке in-order (LNR - Left, Node, Right),
     * сохраняя предыдущее значение и вычисляя минимальную разницу. Это гарантирует,
     * что минимальная разница будет найдена эффективно.
     */
    private void inOrderTraversal(TreeNode node) {
        if (node == null)
            return; // Базовый случай: дошли до пустого узла

        // Рекурсивный вызов для левого поддерева
        inOrderTraversal(node.left);

        // Если есть предыдущий узел, обновляем минимальную разницу
        if (prev != null) {
            minDiff = Math.min(minDiff, node.val - prev);
        }
        prev = node.val; // Обновляем предыдущий узел

        // Рекурсивный вызов для правого поддерева
        inOrderTraversal(node.right);
    }

    static class TreeNode {
        int val; // Значение узла
        TreeNode left; // Левый потомок
        TreeNode right; // Правый потомок

        // Конструктор без параметров
        public TreeNode() {
        }

        // Конструктор с одним значением
        public TreeNode(int val) {
            this.val = val;
        }

        // Конструктор с полными параметрами (значение + левый и правый потомки)
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        // Создание узлов дерева
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);

        // Построение дерева
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;

        // Создание объекта решения и вызов метода для поиска минимальной разницы
        var solution = new BinarySearchTree();
        int minDiff = solution.getMinimumDifference(node1);

        // Вывод минимальной разницы
        System.out.println("Минимальная разница: " + minDiff);
    }
}

