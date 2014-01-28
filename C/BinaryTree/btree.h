typedef struct node{
    int data;
    struct node* left;
    struct node* right;
} node;

int isInTree(struct node* current, int target);
struct node* newBtree(int data);
void insert(struct node* head, int newData);
int count(struct node* head);
int maxDepth(struct node* head);
int minValue(struct node* head);
void printTree(struct node* head);
void printPostorder(struct node* head);
int hasPathSum(struct node* head, int sum);
void destroy(struct node* head);

struct node* newBtree(int data){
    struct node* head = (struct node*)malloc(sizeof(node));
    head -> data = data;
    head -> left = NULL;
    head -> right = NULL;
    return head;
}
int isInTree(struct node* current, int target){
    if (current = NULL){
        return INT_MAX;
    }
    else if(current -> data == target){
        return 1;
    }else if(current -> data > target){
        return isInTree(current -> left, target);         
    }else{
        return isInTree(current -> right,target);
    }
}

void insert(struct node* head, int newData){
    if(newData <= head -> data){
        if(head -> left == NULL){
            head -> left = newBtree(newData);
        }else{
            insert(head -> left, newData); 
        } 
    }else{
        if(head -> right == NULL){
            head -> right = newBtree(newData); 
        }else{
            insert(head -> right, newData); 
        } 
    }
}

int count(struct node* head){
    if(head -> left == NULL && head -> right == NULL){
        return 1; 
    }else if(head -> left != NULL && head -> right != NULL){
        return count(head -> left) + count(head -> right) + 1;
    }else if(head -> left != NULL){
        return count(head -> left) + 1; 
    }else{
        return count(head -> right) + 1; 
    }
}

void destroy(struct node* head){
    if(head == NULL){
       free(head);
       return;
    }
    if(head -> left != NULL){
        destroy(head -> left); 
    }
    if(head -> right != NULL){
        destroy(head -> right); 
    }
    free(head);
    return;
}

int maxDepth(struct node* head){
    if(head == NULL)
        return 0;
    int leftDepth = maxDepth(head -> left) + 1;
    int rightDepth = maxDepth(head -> right) + 1;
    if(leftDepth > rightDepth){
        return leftDepth; 
    }else{
        return rightDepth; 
    }
}

int minValue(struct node* head){
    if(head -> left == NULL){
        return head -> data; 
    }else{
        return minValue(head -> left); 
    }
}

void printTree(struct node* head){
    if(head -> left != NULL){
        printTree(head -> left); 
    }
    printf("%i ", head -> data);
    if(head -> right != NULL){
        printTree(head -> right); 
    }
}

void printPostorder(struct node* head){
    if(head -> left != NULL){
        printPostorder(head -> left);  
    }
    if(head -> right != NULL){
        printPostorder(head -> right); 
    }
    printf("%i ", head -> data);
}

int hasPathSum(struct node* head, int sum){
    sum -= head -> data;
    if(sum == 0 && head -> right == NULL && head -> left == NULL){
        return 1;
    }

    if(head -> left != NULL){
        if(hasPathSum(head -> left, sum)){
            return 1;
        }
    }
    if(head -> right != NULL){
        if(hasPathSum(head -> right, sum) == 1){
            return 1; 
        }
    }
    return 0;
}
