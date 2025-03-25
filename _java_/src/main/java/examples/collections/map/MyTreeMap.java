package examples.collections.map;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Моя реализация TreeMap для доступа к дереву элементов через рефлексию
 */
public class MyTreeMap<V, K> extends TreeMap<V, K> {


    public MyTreeMap() {
        super();
    }

    public MyTreeMap(Map<? extends V, ? extends K> m) {
        super(m);
    }

    public MyTreeMap(Comparator<? super V> comparator) {
        super(comparator);
    }

    public MyTreeMap(SortedMap<V, ? extends K> m) {
        super(m);
    }

    /**
     * Вложенный класс Entry, используемый в TreeMap для хранения дерева элементов
     */
    private Class<?> entryClass;

    {
        //Ищем рефлексией класс Entry и записываем его в наше поле entryClass
        for (Class<?> c : this.getClass().getSuperclass().getDeclaredClasses()) {
            if (c.getSimpleName().equals("Entry")) {
                entryClass = c;
            }
        }
    }

    /**
     * Получить через рефлексию корень дерева (поле root)
     */
    private Object getRoot() {
        Field field;
        try {
            Class<?> hashMapClass = this.getClass().getSuperclass();

            field = hashMapClass.getDeclaredField("root");
            field.setAccessible(true);
            return field.get(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Получаем через рефлексию поле key из класса Entry
     */
    private Object getKey(Object node) {
        return getNodeFieldByName(node, "key");
    }

    /**
     * Получаем через рефлексию поле value из класса Entry
     */
    private Object getValue(Object node) {
        return getNodeFieldByName(node, "value");
    }

    /**
     * Получаем через рефлексию поле left из класса Entry
     */
    private Object getLeft(Object node) {
        return getNodeFieldByName(node, "left");
    }

    /**
     * Получаем через рефлексию поле right из класса Entry
     */
    private Object getRight(Object node) {
        return getNodeFieldByName(node, "right");
    }

    /**
     * Получаем через рефлексию поле parent из класса Entry
     */
    private Object getParent(Object node) {
        return getNodeFieldByName(node, "parent");
    }

    /**
     * Получаем через рефлексию поле color из класса Entry
     */
    private boolean getColor(Object node) {
        return (boolean) getNodeFieldByName(node, "color");
    }


    private Object getNodeFieldByName(Object node, String fieldName) {
        if (node == null) {
            return null;
        }
        Field field;
        try {
            field = entryClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(node);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Преобразуем в строку все элементы дерева
     */
    @Override
    public String toString() {
        Object root = getRoot();
        BinaryTreeModel treeModel = new BinaryTreeModel("root " + nodeToString(root));
        fillModel(treeModel, root);
        return traversePreOrder(treeModel);
    }

    private void fillModel(BinaryTreeModel node, Object root) {
        if (root == null) {
            return;
        }

        Object left = getLeft(root);
        node.setLeft(new BinaryTreeModel("left " + nodeToString(left)));

        Object right = getRight(root);
        node.setRight(new BinaryTreeModel("right " + nodeToString(right)));

        fillModel(node.getLeft(), left);
        fillModel(node.getRight(), right);
    }

    private String nodeToString(Object node) {
        if (node == null) {
            return "null";
        } else {
            return "{c=" + (getColor(node) ? "BLACK" : "RED")
                    + ", k=" + getKey(node)
                    + ", v=" + getValue(node)
                    + "}";
        }
    }

    private static String traversePreOrder(BinaryTreeModel root) {

        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.getValue());

        String pointerRight = "└──";
        String pointerLeft = (root.getRight() != null) ? "├──" : "└──";

        traverseNodes(sb, "", pointerLeft, root.getLeft(), root.getRight() != null);
        traverseNodes(sb, "", pointerRight, root.getRight(), false);

        return sb.toString();
    }

    private static void traverseNodes(StringBuilder sb, String padding, String pointer, BinaryTreeModel node,
                                      boolean hasRightSibling) {

        if (node != null) {

            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.getValue());

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "└──";
            String pointerLeft = (node.getRight() != null) ? "├──" : "└──";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), false);

        }

    }

    private static class BinaryTreeModel {

        private Object value;
        private BinaryTreeModel left;
        private BinaryTreeModel right;

        public BinaryTreeModel(Object value) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public BinaryTreeModel getLeft() {
            return left;
        }

        public void setLeft(BinaryTreeModel left) {
            this.left = left;
        }

        public BinaryTreeModel getRight() {
            return right;
        }

        public void setRight(BinaryTreeModel right) {
            this.right = right;
        }

    }
}
