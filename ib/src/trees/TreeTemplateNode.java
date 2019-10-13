package trees;

public class TreeTemplateNode<T, V> {

    public V val;
    public T left;
    public T right;

    public TreeTemplateNode(V val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public void addLeft(T left) {
        this.left = left;
    }

    public void addRight(T right) {
        this.right = right;
    }

}
