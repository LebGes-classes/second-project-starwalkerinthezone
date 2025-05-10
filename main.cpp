#include <iostream>
#include "main.h"

using namespace std;

int main()
{
	cout << "hello, world!" << endl;
	Node *head = new Node(4, 1, nullptr, nullptr);
	head = insert(2, head);
	cout << "TREE " << head->value << endl
		 << endl;
	printTree(head, 0);
	cout << endl
		 << "END" << endl;
	head = insert(1, head);
	cout << "TREE " << head->value << endl
		 << endl;
	printTree(head, 0);
	cout << endl
		 << "END" << endl;
	head = insert(3, head);
	cout << "TREE " << head->value << endl
		 << endl;
	printTree(head, 0);
	cout << endl
		 << "END" << endl;
	head = insert(10, head);
	cout << endl
		 << "END" << endl;

	cout << "TREE " << head->value << endl
		 << endl;
	printTree(head, 0);
	head = insert(6, head);
	cout << endl
		 << "END" << endl;

	cout << "TREE " << head->value << endl
		 << endl;
	printTree(head, 0);
	head = insert(5, head);
	cout << endl
		 << "END" << endl;

	cout << "TREE " << head->value << endl
		 << endl;
	printTree(head, 0);
	head = insert(8, head);
	cout << endl
		 << "END" << endl;

	cout << "TREE " << head->value << endl
		 << endl;
	printTree(head, 0);
	head = insert(7, head);
	cout << endl
		 << "END" << endl;
	head = insert(9, head);
	head = insert(12, head);
	head = insert(11, head);
	head = insert(13, head);
	printTree(head, 0);

	head = deleteNode(1, head);
	cout << "TREE " << head->value << endl
		 << endl;
	printTree(head, 0);
	cout << endl
		 << "END" << endl;

	Node *node12 = search(head, 12);
	if (node12 != nullptr)
	{
		cout << node12->value;
	}

	return 0;
}

void printTree(Node *node, int depth)
{
	if (node == nullptr)
	{
		return;
	}
	printTree(node->right, depth + 1);
	for (int i = 0; i < depth; i++)
	{
		cout << "    ";
	}
	cout << node->value << " [L" << node->level << "]" << endl;

	printTree(node->left, depth + 1);
}

Node *skew(Node *t)
{
	if (t == nullptr)
	{
		return nullptr;
	}
	else if (t->left == nullptr)
	{
		return t;
	}
	else if (t->left->level == t->level)
	{
		Node *node = new Node(t->value, t->level, t->left->right, t->right);
		Node *node_res = new Node(t->left->value, t->left->level, t->left->left, node);
		return node_res;
	}
	else
	{
		return t;
	}
}

Node *split(Node *t)
{
	if (t == nullptr)
	{
		return nullptr;
	}

	else if (t->right == nullptr || t->right->right == nullptr)
	{
		return t;
	}

	else if (t->level == t->right->right->level)
	{
		Node *node1 = new Node(t->value, t->level, t->left, t->right->left);
		Node *node2 = new Node(t->right->value, t->right->level + 1, node1, t->right->right);
		return node2;
	}
	else
	{
		return t;
	}
}

Node *insert(int x, Node *t)
{
	if (t == nullptr)
	{
		return new Node(x, 1, nullptr, nullptr);
	}
	else if (x < t->value)
	{
		t->left = insert(x, t->left);
	}
	else if (x > t->value)
	{
		t->right = insert(x, t->right);
	}
	t = skew(t);
	t = split(t);
	return t;
}

Node *decreaseLevel(Node *t)
{
	int lm = 0;
	int rm = 0;
	if (t->left != nullptr)
	{
		lm = t->left->level;
	}
	if (t->right != nullptr)
	{
		rm = t->right->level;
	}
	int shouldBe = min(lm, rm) + 1;
	if (shouldBe < t->level)
	{
		t->level = shouldBe;

		if (shouldBe < t->right->level)
		{
			t->right->level = shouldBe;
		}
	}
	return t;
}

bool leaf(Node *t)
{
	if (t->right == nullptr && t->left == nullptr)
	{
		return true;
	}
	return false;
}

Node *successor(Node *t)
{
	t = t->right;
	while (t->left != nullptr)
	{
		t = t->left;
	}
	return t;
}

Node *predecessor(Node *t)
{
	t = t->left;
	while (t->right != nullptr)
	{
		t = t->right;
	}
	return t;
}

Node *deleteNode(int x, Node *t)
{
	if (t == nullptr)
	{
		return t;
	}
	else if (x > t->value)
	{
		t->right = deleteNode(x, t->right);
	}
	else if (x < t->value)
	{
		t->left = deleteNode(x, t->left);
	}
	else
	{
		if (leaf(t))
		{
			return nullptr;
		}
		else if (t->left == nullptr)
		{
			Node *l = successor(t);
			cout << l->value << endl;
			t->right = deleteNode(l->value, t->right);
			t->value = l->value;
		}
		else
		{
			Node *l = predecessor(t);
			t->left = deleteNode(l->value, t->left);
			t->value = l->value;
		}
	}
	t = decreaseLevel(t);
	t = skew(t);
	t->right = skew(t->right);
	if (t->right != nullptr)
	{
		t->right->right = skew(t->right->right);
	}
	t = split(t);
	t->right = split(t->right);
	return t;
}

Node *search(Node *node, int value)
{
	if (node == nullptr)
	{
		return nullptr;
	}
	if (value == node->value)
	{
		return node;
	}
	if (value < node->value)
	{
		return search(node->left, value);
	}
	else
	{
		return search(node->right, value);
	}
}
