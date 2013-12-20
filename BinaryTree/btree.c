#include <stdio.h>
#include <limits.h>
#include <stdlib.h>
#include "btree.h"

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
    printf("%i\n", maxDepth(head));
    printf("%i\n", minValue(head));
    printTree(head);
    printf("\n");
    destroy(head);
    head = newBtree(4);
    insert(head, 2);
    insert(head, 1);
    insert(head, 3);
    insert(head, 5);
    printPostorder(head);
    printf("\n");
    destroy(head);
    head = newBtree(5);
    insert(head, 4);
    insert(head, 8);
    insert(head, 11);
    insert(head, 13);
    insert(head, 4);
    insert(head, 7);
    insert(head, 2);
    insert(head, 1);
    printTree(head);
    printf("\n");
    printPostorder(head);
    printf("\n");
    if(hasPathSum(head, 20) == 1){
        printf("worked3"); 
    }
    return 0;
}
