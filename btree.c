#include <stdio.h>
#include <limits.h>
#include <stdlib.h>

typedef struct node{
    int data;
    struct node* left;
    struct node* right;
} node;

int isInTree(struct node* current, int target);
struct node* newBtree(int data);
void insert(struct node* head, int newData);
int count(struct node* head);
void destroy(struct node* head);

int main(int argc, char* argv[]){
    struct node* head = newBtree(5);
    insert(head, 6);
    insert(head, 3);
    insert(head, 2);
    insert(head, 1);
    insert(head, 50);
    if(isInTree(head,50) == 1){
        printf("worked1\n"); 
    }else{
        printf("failed1\n"); 
    }
    if(isInTree(head,7) != 1){
        printf("worked2\n"); 
    }else{
        printf("faild2\n"); 
    }
    printf("%i\n", count(head));
    destroy(head);
    return 0;
}

struct node* newBtree(int data){
    struct node* head = malloc(sizeof(node));
    head -> data = data;
    return head;
}
int isInTree(struct node* current, int target){
    if (current == NULL){
        return INT_MAX;
    }
    else if(current -> data == target){
        return 1;
    }else if(current -> data > target){
        return isInTree(current -> left, target);         
    }else if(current -> data < target){
        return isInTree(current -> right,target);
    }
    return INT_MAX;

}

void insert(struct node* head, int newData){
    if(newData < head -> data){
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
    if(head -> left != NULL){
        destroy(head -> left); 
    }
    if(head -> right != NULL){
        destroy(head -> right); 
    }
    free(head);
}
