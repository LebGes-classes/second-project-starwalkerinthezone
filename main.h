
struct Node
{
	int value;
	int level;
	Node *right;
	Node *left;

	Node(int value, int level, Node *left, Node *right)
	{
		this->value = value;
		this->level = level;
		this->left = left;
		this->right = right;
	}
};

void printTree(Node *node, int depth);
Node *search(Node *node, int value);
Node *deleteNode(int x, Node *t);
Node *insert(int x, Node *t);
