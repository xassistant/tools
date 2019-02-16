package arithDemo;

import java.util.ArrayList;

/**
 * 二叉树节点结构
 * 
 * @author heartraid
 */
class BSTNode<E extends Comparable<E>> {
	/** 结点关键字 */
	E key = null;
	/** 直接父亲结点 */
	BSTNode<E> parent = null;
	/** 结点左子树的根节点 */
	BSTNode<E> lchild = null;
	/** 结点右子树的根节点 */
	BSTNode<E> rchild = null;

	BSTNode(E k) {
		this.key = k;
	}

}

/**
 * 二叉查找树 Binary Search Tree(BST)
 * 
 * @author heartraid
 * 
 */
public class BST<E extends Comparable<E>> {
	/** 树根 */
	private BSTNode<E> root = null;

	public BST() {
	}

	/**
	 * BST 查询关键字
	 * 
	 * @param key
	 *            关键字
	 * @return 查询成功/true, 查询失败/false
	 */
	public boolean search(E key) {
		System.out.print("搜索关键字[" + key + "]：");
		if (key == null || root == null) {
			System.out.println("搜索失败");
			return false;
		} else {
			System.out.print("搜索路径[");
			if (searchBST(root, key) == null) {
				return false;
			} else
				return true;

		}
	}

	/**
	 * BST插入关键字
	 * 
	 * @param key
	 *            关键字
	 * @return 插入成功/true, 插入失败/false
	 */
	public boolean insert(E key) {
		System.out.print("插入关键字[" + key + "]：");
		if (key == null)
			return false;
		if (root == null) {
			System.out.println("插入到树根。");
			root = new BSTNode<E>(key);
			return true;
		} else {
			System.out.print("搜索路径[");
			return insertBST(root, key);
		}
	}

	public boolean delete(E key) {
		System.out.print("删除关键字[" + key + "]：");
		if (key == null || root == null) {
			System.out.println("删除失败");
			return false;
		} else {
			System.out.print("搜索路径[");

			// 定位到树中待删除的结点
			BSTNode<E> nodeDel = searchBST(root, key);
			if (nodeDel == null) {
				return false;
			} else {
				// nodeDel的右子树为空,则只需要重接它的左子树
				if (nodeDel.rchild == null) {

					BSTNode<E> parent = nodeDel.parent;
					if (parent.lchild.key.compareTo(nodeDel.key) == 0)
						parent.lchild = nodeDel.lchild;
					else
						parent.rchild = nodeDel.lchild;
				}
				// 左子树为空,则重接它的右子树
				else if (nodeDel.lchild == null) {
					BSTNode<E> parent = nodeDel.parent;
					if (parent.lchild.key.compareTo(nodeDel.key) == 0)
						parent.lchild = nodeDel.rchild;
					else
						parent.rchild = nodeDel.rchild;
				}
				// 左右子树均不空
				else {
					BSTNode<E> d = nodeDel;
					// 先找nodeDel的左结点s
					BSTNode<E> candidate = nodeDel.lchild;
					// 然后再向s的右尽头定位(这个结点将替代nodeDel)，其中q一直定位在s的直接父亲结点
					while (candidate.rchild != null) {
						candidate = candidate.rchild;
					}
					candidate.parent.rchild = null;
					nodeDel.key = candidate.key;

				}
				return true;
			}
		}
	}

	/**
	 * 递归查找关键子
	 * 
	 * @param node
	 *            树结点
	 * @param key
	 *            关键字
	 * @return 查找成功，返回该结点，否则返回null。
	 */
	private BSTNode<E> searchBST(BSTNode<E> node, E key) {
		if (node == null) {
			System.out.println("].  搜索失败");
			return null;
		}
		System.out.print(node.key + " —>");
		// 搜索到关键字
		if (node.key.compareTo(key) == 0) {
			System.out.println("].  搜索成功");
			return node;
		}
		// 在左子树搜索
		else if (node.key.compareTo(key) > 0) {
			return searchBST(node.lchild, key);
		}
		// 在右子树搜索
		else {
			return searchBST(node.rchild, key);
		}
	}

	/**
	 * 递归插入关键字
	 * 
	 * @param node
	 *            树结点
	 * @param key
	 *            树关键字
	 * @return true/插入成功，false/插入失败
	 */
	private boolean insertBST(BSTNode<E> node, E key) {
		System.out.print(node.key + " —>");
		// 在原树中找到相同的关键字，无需插入。
		if (node.key.compareTo(key) == 0) {
			System.out.println("].  搜索有相同关键字，插入失败");
			return false;
		} else {
			// 搜索node的左子树
			if (node.key.compareTo(key) > 0) {
				// 如果当前node的左子树为空，则将新结点key node插入到左孩子处
				if (node.lchild == null) {
					System.out.println("].  插入到" + node.key + "的左孩子");
					BSTNode<E> newNode = new BSTNode<E>(key);
					node.lchild = newNode;
					newNode.parent = node;
					return true;
				}
				// 如果当前node的左子树存在，则继续递归左子树
				else
					return insertBST(node.lchild, key);
			}
			// 搜索node的右子树
			else {
				if (node.rchild == null) {
					System.out.println("].  插入到" + node.key + "的右孩子");
					BSTNode<E> newNode = new BSTNode<E>(key);
					node.rchild = newNode;
					newNode.parent = node;
					return true;
				} else
					return insertBST(node.rchild, key);
			}
		}

	}

	/**
	 * 得到BST根节点
	 * 
	 * @return BST根节点f
	 */
	public BSTNode<E> getRoot() {
		return this.root;
	}

	/**
	 * 非递归中序遍历BST
	 */
	public void InOrderTraverse() {
		if (root == null)
			return;
		BSTNode<E> node = root;
		ArrayList<BSTNode<E>> stack = new ArrayList<BSTNode<E>>();
		stack.add(node);
		while (!stack.isEmpty()) {
			while (node.lchild != null) {
				node = node.lchild;
				stack.add(node);
			}
			if (!stack.isEmpty()) {
				BSTNode<E> topNode = stack.get(stack.size() - 1);
				System.out.print(topNode.key + " ");
				stack.remove(stack.size() - 1);
				if (topNode.rchild != null) {
					node = topNode.rchild;
					stack.add(node);
				}
			}
		}

	}

	/**
	 * 测试
	 */
	public static void main(String[] args) {
		BST<Integer> tree = new BST<Integer>();
		tree.insert(new Integer(100));
		tree.insert(new Integer(52));
		tree.insert(new Integer(166));
		tree.insert(new Integer(74));
		tree.insert(new Integer(11));
		tree.insert(new Integer(13));
		tree.insert(new Integer(66));
		tree.insert(new Integer(121));

		// tree.search(new Integer(11));
		// tree.InOrderTraverse();

		tree.delete(new Integer(52));
		// tree.InOrderTraverse();

	}

}